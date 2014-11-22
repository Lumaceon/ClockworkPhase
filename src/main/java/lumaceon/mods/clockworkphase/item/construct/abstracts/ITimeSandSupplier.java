package lumaceon.mods.clockworkphase.item.construct.abstracts;

import net.minecraft.item.ItemStack;

public interface ITimeSandSupplier
{
    public int getMaxTimeSand();

    public int getTimeSandAvailable(ItemStack is);

    public int addTimeSand(ItemStack is, int timeSand);

    public int removeTimeSand(ItemStack is, int timeSand);
}
