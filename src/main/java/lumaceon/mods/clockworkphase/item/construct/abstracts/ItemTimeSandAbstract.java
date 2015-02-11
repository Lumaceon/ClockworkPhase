package lumaceon.mods.clockworkphase.item.construct.abstracts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.item.ItemClockworkPhaseGeneric;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.util.TimeSandHelper;
import lumaceon.mods.clockworkphase.util.TimeSandParser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.List;

public abstract class ItemTimeSandAbstract extends ItemClockworkPhaseGeneric implements ITimeSand
{
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        int timeSand = getTimeSand(is);
        if(timeSand > 0)
        {
            list.add(TimeSandParser.getStringForRenderingFromTimeSand(timeSand));
        }
    }

    @Override
    public int getMaxTimeSand()
    {
        return MechanicTweaker.MAX_TS_DEFAULT;
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
}
