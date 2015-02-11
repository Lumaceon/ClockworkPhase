package lumaceon.mods.clockworkphase.client.gui.components;

import lumaceon.mods.clockworkphase.item.component.generic.IBaseComponent;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotClockworkComponent extends Slot
{
    public SlotClockworkComponent(IInventory inventory, int id, int x, int y)
    {
        super(inventory, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack is)
    {
        return is.getItem() instanceof IBaseComponent;
    }
}
