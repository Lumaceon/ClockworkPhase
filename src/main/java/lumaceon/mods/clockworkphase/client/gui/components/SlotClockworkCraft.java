package lumaceon.mods.clockworkphase.client.gui.components;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

public class SlotClockworkCraft extends SlotCrafting {

    private IInventory craftingMatrix;

    public SlotClockworkCraft(IInventory inventory, IInventory craftingSlots, int slotIndex, int xPosition, int yPosition) {
        super(null, null, inventory, slotIndex, xPosition, yPosition);
        this.craftingMatrix = craftingSlots;
    }

    @Override
    public boolean isItemValid(ItemStack p_75214_1_)
    {
        return false;
    }

    @Override
    protected void onCrafting(ItemStack stack) {
    }

    @Override
    public ItemStack onTake(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
    {
        this.onCrafting(p_82870_2_);
        for(int i = 0; i < 9; i++)
        {
            ItemStack is = this.craftingMatrix.getStackInSlot(i);
            if(!is.isEmpty())
            {
                this.craftingMatrix.decrStackSize(i, 1);
            }
        }
        return p_82870_2_;
    }
}
//public class SlotClockworkCraft extends Slot
//{
//    private IInventory craftingMatrix;
//
//    public SlotClockworkCraft(IInventory inventory, IInventory craftingSlots, int id, int x, int y)
//    {
//        super(inventory, id, x, y);
//        this.craftingMatrix = craftingSlots;
//    }
//
//    @Override
//    public boolean isItemValid(ItemStack p_75214_1_)
//    {
//        return false;
//    }
//
//    @Override
//	public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_)
//    {
//        super.onSlotChange(p_75220_1_, p_75220_2_);
//
//    }
//
//    @Override
//    public ItemStack onTake(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
//    {
//        this.onCrafting(p_82870_2_);
//        for(int i = 0; i < 9; i++)
//        {
//            ItemStack is = this.craftingMatrix.getStackInSlot(i);
//            if(!is.isEmpty())
//            {
//                this.craftingMatrix.decrStackSize(i, 1);
//            }
//        }
//        return p_82870_2_;
//    }
//}
