package lumaceon.mods.clockworkphase.item.construct.clockwork;

import net.minecraft.item.ItemStack;

public interface IClockwork
{
    public void addTension(ItemStack is, int tension);

    public void removeTension(ItemStack is, int tension);

    public int addTimeSand(ItemStack is, int timeSand);

    public int removeTimeSand(ItemStack is, int timeSand);
}
