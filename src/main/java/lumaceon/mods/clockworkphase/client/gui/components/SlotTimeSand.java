package lumaceon.mods.clockworkphase.client.gui.components;

import lumaceon.mods.clockworkphase.item.construct.abstracts.ITimeSand;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTimeSand extends Slot
{
    public SlotTimeSand(IInventory inventory, int id, int x, int y)
    {
        super(inventory, id, x, y);
    }

    public boolean isItemValid(ItemStack is)
    {
        return is.getItem() instanceof ITimeSand;
    }
}
