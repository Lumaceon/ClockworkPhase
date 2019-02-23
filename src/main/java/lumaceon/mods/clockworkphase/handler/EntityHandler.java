package lumaceon.mods.clockworkphase.handler;

import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import lumaceon.mods.clockworkphase.block.extractor.ExtractorAreas;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.extendeddata.ExtendedWorldData;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.phaseevent.PhaseEventAbstract;
import lumaceon.mods.clockworkphase.registry.MemoryItemRegistry;
import lumaceon.mods.clockworkphase.item.construct.clockwork.ItemClockworkSaber;
import lumaceon.mods.clockworkphase.item.construct.clockwork.ItemTemporalClockworkSaber;
import lumaceon.mods.clockworkphase.item.construct.pocketwatch.ItemPocketWatch;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.network.MessageParticleSpawn;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import lumaceon.mods.clockworkphase.util.InventorySearchHelper;
import lumaceon.mods.clockworkphase.util.Logger;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class EntityHandler
{
    @SubscribeEvent
    public void onEntityHurt(LivingHurtEvent event)
    {
        if(event.getEntityLiving() instanceof EntityPlayer) //Player was attacked
        {
            EntityPlayer player = (EntityPlayer)event.getEntityLiving();
            NonNullList<ItemStack> armor = player.inventory.armorInventory;
            ItemStack[] pocketWatches = InventorySearchHelper.getPocketWatches(player.inventory);
            float newAmount = ISpecialArmor.ArmorProperties.applyArmor(event.getEntityLiving(), armor, event.getSource(), event.getAmount()); //Applies armor to the damage.
            if(newAmount <= 0) { return; }

            if(pocketWatches != null && ItemPocketWatch.doesActiveItemModuleExist(pocketWatches, ModItems.moduleLifeWalk)) //Player has a pocket watch with life step.
            {
                ItemStack lifeWalk = ItemPocketWatch.getItemModuleFromMultiple(pocketWatches, ModItems.moduleLifeWalk);
                double lifeModulePower = NBTHelper.getInt(lifeWalk, NBTTags.MODULE_POWER);
                if(lifeModulePower >= MechanicTweaker.LIFE_DEFENSE_PER_HEALTH) //Enough power is present to reduce damage.
                {
                    if(lifeModulePower / MechanicTweaker.LIFE_DEFENSE_PER_HEALTH >= newAmount) //All damage is ignored.
                    {
                        NBTHelper.setInteger(lifeWalk, NBTTags.MODULE_POWER, (int)(lifeModulePower - MechanicTweaker.LIFE_DEFENSE_PER_HEALTH * newAmount));
                        newAmount = 0;
                    }
                    else //The harm cannot be fully ignored.
                    {
                        NBTHelper.setInteger(lifeWalk, NBTTags.MODULE_POWER, 0);
                        newAmount -= lifeModulePower / MechanicTweaker.LIFE_DEFENSE_PER_HEALTH;
                    }
                }
                event.setAmount(newAmount);
            }
        }

        if(event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer) //Attack was from player
        {
            EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
            ItemStack[] pocketWatches = InventorySearchHelper.getPocketWatches(player.inventory);
            if(pocketWatches != null && ItemPocketWatch.doesActiveItemModuleExist(pocketWatches, ModItems.moduleDeathWalk)) //Player has a pocket watch with death step.
            {
                ItemStack deathWalk = ItemPocketWatch.getItemModuleFromMultiple(pocketWatches, ModItems.moduleDeathWalk);
                double deathModulePower = NBTHelper.getInt(deathWalk, NBTTags.MODULE_POWER);
                if(deathModulePower >= MechanicTweaker.DEATH_ATTACK_PER_HEALTH) //Enough power is present to increase damage
                {
                    NBTHelper.setInteger(deathWalk, NBTTags.MODULE_POWER, 0);
                    event.setAmount((float) (event.getAmount() + (deathModulePower / MechanicTweaker.DEATH_ATTACK_PER_HEALTH)));
                }
            }
        }

        if(event.getSource().isFireDamage()) //For fire extractor.
        {
            ExtractorAreas.ExtractorArea area = ExtractorAreas.getAreasFromWorld(event.getEntity().world, Phases.FIRE).getValidArea(event.getEntity().world, (int) event.getEntityLiving().posX, (int) event.getEntityLiving().posY, (int) event.getEntityLiving().posZ);
            if(area != null)
            {
                TileEntity te = event.getEntity().world.getTileEntity(new BlockPos(area.extractorX, area.extractorY, area.extractorZ));
                if(te instanceof TileEntityExtractor)
                {
                    for(float n = 0.0F; n < event.getAmount(); n++)
                    {
                        ((TileEntityExtractor) te).applyEffect(Phases.FIRE);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event)
    {
        ExtractorAreas.ExtractorArea area = ExtractorAreas.getAreasFromWorld(event.getEntity().world, Phases.DEATH).getValidArea(event.getEntity().world, (int)event.getEntityLiving().posX, (int)event.getEntityLiving().posY, (int)event.getEntityLiving().posZ);
        if(area != null)
        {
            TileEntity te = event.getEntity().world.getTileEntity(new BlockPos(area.extractorX, area.extractorY, area.extractorZ));
            if(te instanceof TileEntityExtractor)
            {
                ((TileEntityExtractor) te).applyEffect(Phases.DEATH);
            }
        }
    }

    @SubscribeEvent
    public void onEntityAttacked(LivingAttackEvent event)
    {
        if(event.getSource().getTrueSource() instanceof EntityPlayer) //Attack was from player
        {
            EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
            if(event.getEntityLiving().getHealth() > 0 && event.getEntityLiving().getHealth() - event.getAmount() <= 0) //Entity was not dead, but attack is fatal
            {
                if(!player.inventory.getCurrentItem().isEmpty() && player.inventory.getCurrentItem().getItem() instanceof ItemClockworkSaber)
                {
                    ItemStack is = player.inventory.getCurrentItem();
                    int memory = NBTHelper.getInt(is, NBTTags.MEMORY);
                    if(memory > 0 && !player.world.isRemote)
                    {
                        int memoryWebPower = (int)(memory * Math.pow(player.experienceLevel + 1.0F, 2.0F));
                        int chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR;
                        if(memoryWebPower > 0)
                        {
                            chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR / memoryWebPower;
                        }

                        if(chance < 1) { chance = 1; }
                        if(player.world.rand.nextInt(chance) == 0)
                        {
                            ((ItemClockworkSaber)is.getItem()).addTimeSand(is, MechanicTweaker.SABER_TIME_SAND_INCREMENT_KILL);
                            PacketHandler.INSTANCE.sendToAllAround(new MessageParticleSpawn(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, 1), new NetworkRegistry.TargetPoint(player.world.provider.getDimension(), event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, 64));
                        }
                    }
                    if(!player.world.isRemote && is.getItem() instanceof ItemTemporalClockworkSaber)
                    {
                        ItemStack result = new ItemStack(ModItems.nuggetTemporal);
                        float f = 0.7F;
                        double d0 = (double)(player.world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d1 = (double)(player.world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d2 = (double)(player.world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        EntityItem entityitem = new EntityItem(player.world, event.getEntity().posX + d0, event.getEntity().posY + d1, event.getEntity().posZ + d2, result);
                        entityitem.setPickupDelay(10);
                        player.world.spawnEntity(entityitem);
                    }
                }
            }

        }
    }

    @SubscribeEvent
    public void onEntitySpawn(LivingSpawnEvent.CheckSpawn event)
    {
        if(!(event.getEntityLiving() instanceof EntityPlayer))
        {
            ExtractorAreas.ExtractorArea area = ExtractorAreas.getAreasFromWorld(event.getEntity().world, Phases.LIFE).getValidArea(event.getEntity().world, (int)event.getEntity().posX, (int)event.getEntity().posY, (int)event.getEntity().posZ);
            if(area != null)
            {
                TileEntity te = event.getEntity().world.getTileEntity(new BlockPos(area.extractorX, area.extractorY, area.extractorZ));
                if(te instanceof TileEntityExtractor)
                {
                    ((TileEntityExtractor) te).addTimeSand(MechanicTweaker.TIME_SAND_FROM_NATURAL_SPAWN);
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityItemDrop(LivingDropsEvent event)
    {
        if(event.getEntityLiving().world != null)
        {
            if(event.getEntityLiving().world.rand.nextInt(99) == 0)
            {
                if(MemoryItemRegistry.memoryItemDrops.size() == 1)
                {
                    event.getDrops().add(new EntityItem(event.getEntityLiving().world, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, new ItemStack(MemoryItemRegistry.memoryItemDrops.get(0))));
                }
                else if(MemoryItemRegistry.memoryItemDrops.size() <= 0)
                {
                    return;
                }
                else
                {
                    event.getDrops().add(new EntityItem(event.getEntityLiving().world, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, new ItemStack(MemoryItemRegistry.memoryItemDrops.get(event.getEntity().world.rand.nextInt(MemoryItemRegistry.memoryItemDrops.size())))));
                }
            }

            if(event.getEntityLiving().world.rand.nextInt(1000) == 0)
            {
                event.getDrops().add(new EntityItem(event.getEntityLiving().world, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, new ItemStack(ModItems.gearChronosphere)));
            }
        }
    }

    @SubscribeEvent
    public void onItemDestroyed(PlayerDestroyItemEvent event)
    {
        ItemStack is = event.getOriginal();
        if(NBTHelper.hasTag(is, NBTTags.TEMPORAL_ITEMS))
        {
            ItemStack[] items = NBTHelper.getInventoryFromNBTTag(is, NBTTags.TEMPORAL_ITEMS);
            ItemStack[] newItems = new ItemStack[items.length - 1];
            ItemStack newItem = items[items.length - 1];
            int amountInNew = 0;

            for(int n = 0; n < newItems.length; n++)
            {
                if(!items[n].isEmpty())
                {
                    newItems[n] = items[amountInNew];
                    amountInNew++;
                }
            }
            NBTHelper.setNBTTagListFromInventory(newItem, NBTTags.TEMPORAL_ITEMS, newItems);
            if(event.getEntityPlayer().inventory.getCurrentItem().isEmpty())
            {
                event.getEntityPlayer().inventory.setInventorySlotContents(event.getEntityPlayer().inventory.currentItem, newItem);
            }
            else if(event.getEntityPlayer().inventory.addItemStackToInventory(newItem))
            {
                return;
            }
            else
            {
                event.getEntityPlayer().world.spawnEntity(new EntityItem(event.getEntityPlayer().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, newItem));
            }
        }
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if(event.getEntityLiving() != null)
        {
            if(MechanicTweaker.PHASE_EVENTS)
            {
                ExtendedWorldData ewd = ExtendedWorldData.get(event.getEntityLiving().world);
                PhaseEventAbstract phaseEvent = ewd.phaseEvent;
                if(phaseEvent != null)
                {
                    phaseEvent.applyEntityEffects(event.getEntityLiving());
                }
            }
        }
    }
}
