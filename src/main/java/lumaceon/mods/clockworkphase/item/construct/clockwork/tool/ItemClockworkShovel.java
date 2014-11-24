package lumaceon.mods.clockworkphase.item.construct.clockwork.tool;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeWell;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.construct.IKeybindAbility;
import lumaceon.mods.clockworkphase.item.construct.ITemporalChange;
import lumaceon.mods.clockworkphase.item.construct.abstracts.ITimeSand;
import lumaceon.mods.clockworkphase.item.construct.abstracts.IClockwork;
import lumaceon.mods.clockworkphase.item.construct.abstracts.IDisassemble;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Textures;
import lumaceon.mods.clockworkphase.network.MessageParticleSpawn;
import lumaceon.mods.clockworkphase.network.MessageTemporalItemChange;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.TensionHelper;
import lumaceon.mods.clockworkphase.util.TimeSandHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemClockworkShovel extends ItemSpade implements IClockwork, IDisassemble, ITimeSand, IKeybindAbility, ITemporalChange
{
    public ItemClockworkShovel(Item.ToolMaterial mat)
    {
        super(mat);
        this.setCreativeTab(ClockworkPhase.instance.creativeTabClockworkPhase);
        this.setMaxStackSize(1);
        this.setMaxDamage(50);
        this.setNoRepair();
        this.setHarvestLevel("shovel", 3);
    }

    @Override
    public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int meta, float f1, float f2, float f3)
    {
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
        return is;
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        int x = (int) Math.floor(entityItem.posX);
        int y = (int) Math.floor(entityItem.posY - 1);
        int z = (int) Math.floor(entityItem.posZ);

        Block targetBlock = entityItem.worldObj.getBlock(x, y, z);

        if (targetBlock == null)
        {
            return false;
        }

        if (!targetBlock.equals(ModBlocks.timeWell))
        {
            return false;
        }

        TileEntity te = entityItem.worldObj.getTileEntity(x, y, z);
        if(te != null && te instanceof TileEntityTimeWell)
        {
            if(!entityItem.worldObj.isRemote)
            {
                int amountRemoved = this.removeTimeSand(entityItem.getEntityItem(), 1000);
                TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                timeWell.addTimeSand(amountRemoved);
                return false;
            }
        }
        return false;
    }

    @Override
    public float func_150893_a(ItemStack is, Block block)
    {
        float efficiency = super.func_150893_a(is, block); if(efficiency == 1.0F) { return efficiency; }
        int tension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY); if(tension <= 0) { return 0.0F; }
        int speed = NBTHelper.getInt(is, NBTTags.SPEED); if(speed <= 0) { return 0.0F; }

        return (float)speed / 20;
    }

    @Override
    public boolean hitEntity(ItemStack is, EntityLivingBase entity1, EntityLivingBase entity2)
    {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack is, World world, Block block, int x, int y, int z, EntityLivingBase entity)
    {
        if ((double)block.getBlockHardness(world, x, y, z) != 0.0D)
        {
            if(is.getItem() instanceof IClockwork)
            {
                int tension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY);
                int quality = NBTHelper.getInt(is, NBTTags.QUALITY); if(quality <= 0) { return false; }
                int speed = NBTHelper.getInt(is, NBTTags.SPEED);
                int memory = NBTHelper.getInt(is, NBTTags.MEMORY);
                float efficiency = (float)speed / (float)quality;
                int tensionCost = (int)Math.round(MechanicTweaker.TENSION_PER_BLOCK_BREAK * Math.pow(efficiency, 2));
                int newTension = tension - tensionCost;

                if(newTension <= 0)
                {
                    this.removeTension(is, tension);
                    return true;
                }

                if(memory > 0 && !world.isRemote)
                {
                    if(this.func_150893_a(is, block) > 1.0F)
                    {
                        if(entity instanceof EntityPlayer)
                        {
                            EntityPlayer player = (EntityPlayer)entity;
                            int memoryWebPower = (int)(memory * Math.pow(player.experienceLevel + 1.0F, 2.0F));
                            int chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR;
                            if(memoryWebPower > 0)
                            {
                                chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR / memoryWebPower;
                            }

                            if(chance < 1) { chance = 1; }
                            if(world.rand.nextInt(chance) == 0)
                            {
                                this.addTimeSand(is, MechanicTweaker.SHOVEL_TIME_SAND_INCREMENT);
                                PacketHandler.INSTANCE.sendToAllAround(new MessageParticleSpawn(x + 0.5, y + 0.5, z + 0.5, 1), new NetworkRegistry.TargetPoint(world.provider.dimensionId, x + 0.5, y + 0.5, z + 0.5, 64));
                            }
                        }
                    }
                }
                this.removeTension(is, tensionCost);
            }
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        list.add("Tension: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.TENSION_ENERGY) + "/" + "\u00a7e" + NBTHelper.getInt(is, NBTTags.MAX_TENSION));
        list.add("Time Sand: " + "\u00A7e" + getTimeSand(is));

        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            int quality = NBTHelper.getInt(is, NBTTags.QUALITY);
            int speed = NBTHelper.getInt(is, NBTTags.SPEED);
            int memory = NBTHelper.getInt(is, NBTTags.MEMORY);
            int memoryWebPower = (int)(memory * Math.pow(player.experienceLevel + 1.0F, 2.0F));
            int chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR;
            if(memoryWebPower > 0)
            {
                chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR / memoryWebPower;
                if(chance < 1) { chance = 1; }
            }

            list.add("");
            list.add("Clockwork Quality: " + "\u00A7e" + quality);
            list.add("Clockwork Speed: " + "\u00A7e" + speed);
            list.add("Memory: " + "\u00A7e" + memory);
            if(memory > 0)
            {
                if(chance < 1000000)
                {
                    list.add("Chance of Extracting Time Sand: 1 in " + chance);
                }
                else
                {
                    list.add("Chance of Extracting Time Sand: 1 in an eternity.");
                }
            }

            list.add("");
        }
        else
        {
            list.add("-Hold shift for details-");
        }
    }

    @Override
    public float getDigSpeed(ItemStack is, Block block, int meta)
    {
        return func_150893_a(is, block);
    }

    @Override
    public void addTension(ItemStack is, int tension)
    {
        TensionHelper.addTension(is, tension);
    }

    @Override
    public void removeTension(ItemStack is, int tension)
    {
        TensionHelper.removeTension(is, tension);
    }

    @Override
    public int getMaxTimeSand()
    {
        return MechanicTweaker.MAX_TIME_SAND_TOOLS;
    }

    @Override
    public int getTimeSand(ItemStack is)
    {
        return TimeSandHelper.getTimeSand(is);
    }

    @Override
    public int addTimeSand(ItemStack is, int timeSand)
    {
        return TimeSandHelper.addTimeSand(is, timeSand, getMaxTimeSand());
    }

    @Override
    public int removeTimeSand(ItemStack is, int timeSand)
    {
        return TimeSandHelper.removeTimeSand(is, timeSand);
    }

    @Override
    public int removeTimeSandFromInventory(IInventory inventory, int timeSand)
    {
        return TimeSandHelper.removeTimeSandFromInventory(inventory, timeSand);
    }

    @Override
    public int getTimeSandFromInventory(IInventory inventory)
    {
        return TimeSandHelper.getTimeSandFromInventory(inventory);
    }

    @Override
    public void disassemble(World world, double x, double y, double z, ItemStack is)
    {
        if(world.isRemote)
        {
            return;
        }

        int maxTension = NBTHelper.getInt(is, NBTTags.MAX_TENSION);

        if(maxTension != 0)
        {
            ItemStack mainspring = new ItemStack(ModItems.mainspring);
            NBTHelper.setInteger(mainspring, NBTTags.MAX_TENSION, maxTension);
            NBTHelper.setInteger(mainspring, NBTTags.TENSION_ENERGY, 0);

            world.spawnEntityInWorld(new EntityItem(world, x, y, z, mainspring));
        }

        if(NBTHelper.hasTag(is, NBTTags.CLOCKWORK))
        {
            NBTTagList tagList = NBTHelper.getTagList(is, NBTTags.CLOCKWORK);
            if(tagList.tagCount() > 0)
            {
                ItemStack clockwork = ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(0));
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, clockwork));
            }
        }

        NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, 0);
        NBTHelper.setInteger(is, NBTTags.MAX_TENSION, 0);
        NBTHelper.setInteger(is, NBTTags.QUALITY, 0);
        NBTHelper.setInteger(is, NBTTags.SPEED, 0);
        NBTHelper.setInteger(is, NBTTags.MEMORY, 0);
        NBTHelper.removeTag(is, NBTTags.CLOCKWORK);
        is.setItemDamage(is.getMaxDamage());
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf('.') + 1));
    }

    @Override
    public String getUnlocalizedName(ItemStack is)
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf('.') + 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister registry)
    {
        this.itemIcon = registry.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    @Override
    public void useTemporalAbility()
    {
        PacketHandler.INSTANCE.sendToServer(new MessageTemporalItemChange());
    }

    @Override
    public Item getItemChangeTo()
    {
        return ModItems.temporalClockworkShovel;
    }
}
