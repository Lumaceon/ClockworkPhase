package lumaceon.mods.clockworkphase.handler;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import lumaceon.mods.clockworkphase.block.extractor.ExtractorAreas;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.extendeddata.ExtendedPlayerProperties;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.component.base.memory.MemoryItemRegistry;
import lumaceon.mods.clockworkphase.item.construct.clockwork.ItemClockworkSaber;
import lumaceon.mods.clockworkphase.item.construct.clockwork.ItemTemporalClockworkSaber;
import lumaceon.mods.clockworkphase.item.construct.pocketwatch.ItemPocketWatch;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.network.MessageParticleSpawn;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import lumaceon.mods.clockworkphase.util.InventorySearchHelper;
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
        if(event.entityLiving instanceof EntityPlayer) //Player was attacked
        {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            ItemStack[] armor = player.inventory.armorInventory;
            ItemStack[] pocketWatches = InventorySearchHelper.getPocketWatches(player.inventory);
            float newAmount = ISpecialArmor.ArmorProperties.ApplyArmor(event.entityLiving, armor, event.source, event.ammount); //Applies armor to the damage.
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
                event.ammount = newAmount;
            }
        }

        if(event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) //Attack was from player
        {
            EntityPlayer player = (EntityPlayer)event.source.getEntity();
            ItemStack[] pocketWatches = InventorySearchHelper.getPocketWatches(player.inventory);
            if(pocketWatches != null && ItemPocketWatch.doesActiveItemModuleExist(pocketWatches, ModItems.moduleDeathWalk)) //Player has a pocket watch with death step.
            {
                ItemStack deathWalk = ItemPocketWatch.getItemModuleFromMultiple(pocketWatches, ModItems.moduleDeathWalk);
                double deathModulePower = NBTHelper.getInt(deathWalk, NBTTags.MODULE_POWER);
                if(deathModulePower >= MechanicTweaker.DEATH_ATTACK_PER_HEALTH) //Enough power is present to increase damage
                {
                    NBTHelper.setInteger(deathWalk, NBTTags.MODULE_POWER, 0);
                    event.ammount += deathModulePower / MechanicTweaker.DEATH_ATTACK_PER_HEALTH;
                }
            }
        }

        if(event.source.isFireDamage()) //For fire extractor.
        {
            ExtractorAreas.ExtractorArea area = ExtractorAreas.getAreasFromWorld(event.entity.worldObj, Phases.FIRE).getValidArea(event.entity.worldObj, (int) event.entityLiving.posX, (int) event.entityLiving.posY, (int) event.entityLiving.posZ);
            if(area != null)
            {
                TileEntity te = event.entity.worldObj.getTileEntity(area.extractorX, area.extractorY, area.extractorZ);
                if(te != null && te instanceof TileEntityExtractor)
                {
                    for(float n = 0.0F; n < event.ammount; n++)
                    {
                        if(((TileEntityExtractor) te).applyEffect(Phases.FIRE))
                        {
                            event.ammount -= 1.0F;
                        }
                    }

                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event)
    {
        ExtractorAreas.ExtractorArea area = ExtractorAreas.getAreasFromWorld(event.entity.worldObj, Phases.DEATH).getValidArea(event.entity.worldObj, (int)event.entityLiving.posX, (int)event.entityLiving.posY, (int)event.entityLiving.posZ);
        if(area != null)
        {
            TileEntity te = event.entity.worldObj.getTileEntity(area.extractorX, area.extractorY, area.extractorZ);
            if(te != null && te instanceof TileEntityExtractor)
            {
                if(((TileEntityExtractor) te).applyEffect(Phases.DEATH))
                {
                    event.setCanceled(true);
                    event.entityLiving.setHealth(event.entityLiving.getMaxHealth());
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityAttacked(LivingAttackEvent event)
    {
        if(event.source.getEntity() instanceof EntityPlayer) //Attack was from player
        {
            EntityPlayer player = (EntityPlayer)event.source.getEntity();
            if(event.entityLiving.getHealth() - event.ammount <= 0) //Attack is fatal
            {
                if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ItemClockworkSaber)
                {
                    ItemStack is = player.inventory.getCurrentItem();
                    int memory = NBTHelper.getInt(is, NBTTags.MEMORY);
                    if(memory > 0 && !player.worldObj.isRemote)
                    {
                        int memoryWebPower = (int)(memory * Math.pow(player.experienceLevel + 1.0F, 2.0F));
                        int chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR;
                        if(memoryWebPower > 0)
                        {
                            chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR / memoryWebPower;
                        }

                        if(chance < 1) { chance = 1; }
                        if(player.worldObj.rand.nextInt(chance) == 0)
                        {
                            ((ItemClockworkSaber)is.getItem()).addTimeSand(is, MechanicTweaker.SABER_TIME_SAND_INCREMENT_KILL);
                            PacketHandler.INSTANCE.sendToAllAround(new MessageParticleSpawn(event.entity.posX, event.entity.posY, event.entity.posZ, 1), new NetworkRegistry.TargetPoint(player.worldObj.provider.dimensionId, event.entity.posX, event.entity.posY, event.entity.posZ, 64));
                        }
                    }
                    if(!player.worldObj.isRemote && is.getItem() instanceof ItemTemporalClockworkSaber)
                    {
                        ItemStack result = new ItemStack(ModItems.nuggetTemporal);
                        float f = 0.7F;
                        double d0 = (double)(player.worldObj.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d1 = (double)(player.worldObj.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d2 = (double)(player.worldObj.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        EntityItem entityitem = new EntityItem(player.worldObj, event.entity.posX + d0, event.entity.posY + d1, event.entity.posZ + d2, result);
                        entityitem.delayBeforeCanPickup = 10;
                        player.worldObj.spawnEntityInWorld(entityitem);
                    }
                }
            }

        }
    }

    @SubscribeEvent
    public void onEntitySpawn(LivingSpawnEvent.CheckSpawn event)
    {
        if(!(event.entityLiving instanceof EntityPlayer))
        {
            ExtractorAreas.ExtractorArea area = ExtractorAreas.getAreasFromWorld(event.entity.worldObj, Phases.LIFE).getValidArea(event.entity.worldObj, (int)event.entity.posX, (int)event.entity.posY, (int)event.entity.posZ);
            if(area != null)
            {
                TileEntity te = event.entity.worldObj.getTileEntity(area.extractorX, area.extractorY, area.extractorZ);
                if(te != null && te instanceof TileEntityExtractor)
                {
                    ((TileEntityExtractor) te).addTimeSand(MechanicTweaker.TIME_SAND_FROM_NATURAL_SPAWN);
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityConstruction(EntityEvent.EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer && ExtendedPlayerProperties.get((EntityPlayer) event.entity) == null)
        {
            ExtendedPlayerProperties.register((EntityPlayer) event.entity);
        }
    }

    @SubscribeEvent
    public void onEntityItemDrop(LivingDropsEvent event)
    {
        if(event.entityLiving.worldObj != null)
        {
            if(event.entityLiving.worldObj.rand.nextInt(99) == 0)
            {
                if(MemoryItemRegistry.memoryItemDrops.size() == 1)
                {
                    event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(MemoryItemRegistry.memoryItemDrops.get(0))));
                }
                else if(MemoryItemRegistry.memoryItemDrops.size() <= 0)
                {
                    return;
                }
                else
                {
                    event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(MemoryItemRegistry.memoryItemDrops.get(event.entity.worldObj.rand.nextInt(MemoryItemRegistry.memoryItemDrops.size())))));
                }
            }

            if(event.entityLiving.worldObj.rand.nextInt(1000) == 0)
            {
                event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(ModItems.gearChronosphere)));
            }
        }
    }

    @SubscribeEvent
    public void onItemDestroyed(PlayerDestroyItemEvent event)
    {
        ItemStack is = event.original;
        if(NBTHelper.hasTag(is, NBTTags.TEMPORAL_ITEMS))
        {
            ItemStack[] items = NBTHelper.getInventoryFromNBTTag(is, NBTTags.TEMPORAL_ITEMS);
            ItemStack[] newItems = new ItemStack[items.length - 1];
            ItemStack newItem = items[items.length - 1];
            int amountInNew = 0;

            for(int n = 0; n < newItems.length; n++)
            {
                if(items[n] != null)
                {
                    newItems[n] = items[amountInNew];
                    amountInNew++;
                }
            }
            NBTHelper.setNBTTagListFromInventory(newItem, NBTTags.TEMPORAL_ITEMS, newItems);
            if(event.entityPlayer.inventory.getCurrentItem() == null)
            {
                event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, newItem);
            }
            else if(event.entityPlayer.inventory.addItemStackToInventory(newItem))
            {
                return;
            }
            else
            {
                event.entityPlayer.worldObj.spawnEntityInWorld(new EntityItem(event.entityPlayer.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, newItem));
            }
        }
    }

    /**@SubscribeEvent
    public void onPlayerUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if(event.entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            ExtendedPlayerProperties properties = ExtendedPlayerProperties.get(player);
            properties.prevPosX = properties.posX;
            properties.prevPosY = properties.posY;
            properties.prevPosZ = properties.posZ;
            properties.posX = player.posX;
            properties.posY = player.posY;
            properties.posZ = player.posZ;
            ItemStack[] armor = player.inventory.armorInventory;
        }
    }*/
}
