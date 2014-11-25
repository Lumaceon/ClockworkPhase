package lumaceon.mods.clockworkphase.block.blockitems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.item.construct.abstracts.ITimeSandSupplier;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.util.TimeSandHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockTimeWell extends ItemBlock implements ITimeSandSupplier
{
    public ItemBlockTimeWell(Block block)
    {
        super(block);
        this.setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        list.add("Time Sand: " + "\u00A7e" + getTimeSandAvailable(is));
    }

    @Override
    public int getMaxTimeSand()
    {
        return MechanicTweaker.MAX_TS_TIME_WELL;
    }

    @Override
    public int getTimeSandAvailable(ItemStack is)
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
}
