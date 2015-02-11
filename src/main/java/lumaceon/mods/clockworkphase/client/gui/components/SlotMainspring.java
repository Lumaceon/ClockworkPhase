package lumaceon.mods.clockworkphase.client.gui.components;

import lumaceon.mods.clockworkphase.init.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMainspring extends Slot
{
    public SlotMainspring(IInventory inventory, int id, int x, int y)
    {
        super(inventory, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack is)
    {
        return is.getItem().equals(ModItems.mainspring);
    }
}
