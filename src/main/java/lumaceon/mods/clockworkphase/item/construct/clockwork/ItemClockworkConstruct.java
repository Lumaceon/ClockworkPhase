package lumaceon.mods.clockworkphase.item.construct.clockwork;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeWell;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.ItemClockworkPhase;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemClockworkConstruct extends ItemClockworkPhase implements IClockwork, IDisassemble
{
    public ItemClockworkConstruct()
    {
        this.setMaxStackSize(1);
        this.setMaxDamage(10);
        this.setNoRepair();
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        int x = (int) Math.floor(entityItem.posX);
        int y = (int) Math.floor(entityItem.posY - 1);
        int z = (int) Math.floor(entityItem.posZ);

        Block targetBlock = entityItem.worldObj.getBlock(x, y, z);
        boolean flag = true;

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
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        list.add("Tension: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.TENSION_ENERGY) + "/" + "\u00a7e" + NBTHelper.getInt(is, NBTTags.MAX_TENSION));
        int timeSand = NBTHelper.getInt(is, NBTTags.INTERNAL_TIME_SAND);
        if(timeSand > 0)
        {
            list.add("Internal Time Sand: " + "\u00A7e" + timeSand);
        }
        list.add("");

        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            list.add("Clockwork Quality: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.QUALITY));
            list.add("Clockwork Speed: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.SPEED));
            list.add("Memory: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.MEMORY));
            list.add("");
        }
        else
        {
            list.add("-Hold shift for details-");
        }
    }

    @Override
    public void addTension(ItemStack is, int tension)
    {
        if(NBTHelper.hasTag(is, NBTTags.MAX_TENSION))
        {
            int maxTension = NBTHelper.getInt(is, NBTTags.MAX_TENSION);
            int currentTension;
            if(!NBTHelper.hasTag(is, NBTTags.TENSION_ENERGY)) { currentTension = 0; }
            else { currentTension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY); }

            if(currentTension + tension >= maxTension)
            {
                NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, maxTension);
            }
            else
            {
                NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, currentTension + tension);
            }

            if(maxTension / 10 == 0) { is.setItemDamage(is.getMaxDamage()); }
            else { is.setItemDamage(10 - (currentTension / (maxTension / 10))); }
        }
    }

    @Override
    public void removeTension(ItemStack is, int tension)
    {
        int maxTension = 0;
        int currentTension;
        if(NBTHelper.hasTag(is, NBTTags.MAX_TENSION)) { maxTension = NBTHelper.getInt(is, NBTTags.MAX_TENSION); }
        if(!NBTHelper.hasTag(is, NBTTags.TENSION_ENERGY)) { currentTension = 0; }
        else { currentTension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY); }

        if(currentTension - tension <= 0)
        {
            NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, 0);
        }
        else
        {
            NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, currentTension - tension);
        }

        if(maxTension / 10 == 0) { is.setItemDamage(is.getMaxDamage()); }
        else { is.setItemDamage(10 - (currentTension / (maxTension / 10))); }
    }

    @Override
    public int addTimeSand(ItemStack is, int timeSand)
    {
        int currentTimeSand = NBTHelper.getInt(is, NBTTags.INTERNAL_TIME_SAND);

        if(currentTimeSand + timeSand >= MechanicTweaker.MAX_TIME_SAND_TOOLS)
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, MechanicTweaker.MAX_TIME_SAND_TOOLS);
            return MechanicTweaker.MAX_TIME_SAND_TOOLS - currentTimeSand;
        }
        else
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, currentTimeSand + timeSand);
            return timeSand;
        }
    }

    @Override
    public int removeTimeSand(ItemStack is, int timeSand)
    {
        int currentTimeSand = NBTHelper.getInt(is, NBTTags.INTERNAL_TIME_SAND);

        if(currentTimeSand - timeSand <= 0)
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, 0);
            return currentTimeSand;
        }
        else if(timeSand <= 0)
        {
            return 0;
        }
        else
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, currentTimeSand - timeSand);
            return timeSand;
        }
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
}
