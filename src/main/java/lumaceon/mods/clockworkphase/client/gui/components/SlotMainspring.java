package lumaceon.mods.clockworkphase.client.gui.components;

import lumaceon.mods.clockworkphase.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

public class SlotMainspring extends SlotCrafting {

    public SlotMainspring(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(null, null, inventoryIn, slotIndex, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem().equals(ModItems.mainspring);
    }

    @Override
    protected void onCrafting(ItemStack stack) {
    }

    @Override
    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
        this.onSlotChanged();
        return stack;
    }
}

//public class SlotMainspring extends Slot
//{
//    public SlotMainspring(IInventory inventory, int id, int x, int y)
//    {
//        super(inventory, id, x, y);
//    }
//
//    @Override
//    public boolean isItemValid(ItemStack is)
//    {
//        return is.getItem().equals(ModItems.mainspring);
//    }
//}
