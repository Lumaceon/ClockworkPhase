package lumaceon.mods.clockworkphase.item.component;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.item.ItemClockworkPhaseGeneric;
import lumaceon.mods.clockworkphase.item.construct.abstracts.IDisassemble;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import java.util.List;

public class ItemClockwork extends ItemClockworkPhaseGeneric implements IDisassemble
{
    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, World worldIn, List<String> list, ITooltipFlag flagIn)
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
                items[i] = new ItemStack(tagCompound);
            }

            for(int n = 0; n < items.length; n++)
            {
                if(!items[n].isEmpty())
                {
                    world.spawnEntity(new EntityItem(world, x, y, z, items[n]));
                    is.setCount(0);
                }
            }
        }
    }
}
