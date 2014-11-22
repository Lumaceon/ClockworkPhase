package lumaceon.mods.clockworkphase.item.construct.abstracts;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface ITimeSand
{
    public int getMaxTimeSand();

    public int getTimeSand(ItemStack is);

    public int addTimeSand(ItemStack is, int timeSand);

    public int removeTimeSand(ItemStack is, int timeSand);

    /**
     * This is to be used before trying to remove time sand from the specific ITimeSand tool.
     * The method will attempt to draw time sand from ITimeSandSupplier items present in the
     * current inventory, returning the deficit.
     * @param inventory The inventory to check for time sand supplying items.
     * @param timeSand The amount of time sand to try and remove.
     * @return The amount that was actually able to be removed.
     */
    public int removeTimeSandFromInventory(IInventory inventory, int timeSand);

    /**
     * The method will check the inventory and return the amount of available time sand
     * for use. Note, this only returns the time sand from ITimeSandSupplier items.
     * @param inventory The inventory to check.
     * @return The amount of time sand available in the inventory.
     */
    public int getTimeSandFromInventory(IInventory inventory);
}
