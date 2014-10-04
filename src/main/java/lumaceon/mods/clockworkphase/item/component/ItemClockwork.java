package lumaceon.mods.clockworkphase.item.component;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.item.IDisassemble;
import lumaceon.mods.clockworkphase.item.ItemClockworkPhase;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.Logger;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import java.util.List;

public class ItemClockwork extends ItemClockworkPhase implements IDisassemble
{
    public ItemClockwork()
    {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(10);
        this.setNoRepair();
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        if(NBTHelper.getTagList(is, NBTTags.INVENTORY_ARRAY).tagCount() == 0)
        {
            list.add("\u00a7e" + "Empty");
        }
        else
        {
            list.add("Quality: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.QUALITY));
            list.add("Speed: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.SPEED));
            list.add("Memory: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.MEMORY));
            list.add("");
            list.add("Items used: " + "\u00a7e" + NBTHelper.getTagList(is, NBTTags.INVENTORY_ARRAY).tagCount());
        }

    }

    @Override
    public void disassemble(World world, double x, double y, double z, ItemStack is)
    {
        if(!world.isRemote && is.getTagCompound().hasKey(NBTTags.INVENTORY_ARRAY))
        {
            NBTTagList tagList = NBTHelper.getTagList(is, NBTTags.INVENTORY_ARRAY);
            ItemStack[] items = new ItemStack[tagList.tagCount()];

            for (int i = 0; i < tagList.tagCount(); ++i)
            {
                NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                items[i] = ItemStack.loadItemStackFromNBT(tagCompound);
            }

            for(int n = 0; n < items.length; n++)
            {
                if(items[n] != null)
                {
                    world.spawnEntityInWorld(new EntityItem(world, x, y, z, items[n]));
                    is.stackSize = 0;
                }
            }
        }
    }
}
