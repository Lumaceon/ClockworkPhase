package lumaceon.mods.clockworkphase.inventory;

import lumaceon.mods.clockworkphase.custom.IInventoryHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public class InventoryMainspringAssembly extends InventoryCrafting {

    private final Container container;

    public InventoryMainspringAssembly(Container eventHandlerIn) {
        super(eventHandlerIn, 4, 2);
        container = eventHandlerIn;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = super.decrStackSize(index, count);
        this.container.onCraftMatrixChanged(this);
        return stack;
    }
}

//public class InventoryMainspringAssembly implements IInventoryHelper
//{
//
//    private NonNullList<ItemStack> inventory;
//    private Container eventHandler;
//
//    public InventoryMainspringAssembly(Container eventHandler)
//    {
//        inventory = NonNullList.withSize(8, ItemStack.EMPTY);
//        this.eventHandler = eventHandler;
//    }
//
//    @Override
//    public NonNullList<ItemStack> getInv() {
//        return inventory;
//    }
//
//    @Override
//    public ItemStack decrStackSize(int index, int lossCount)
//    {
//        if (!inventory.get(index).isEmpty())
//        {
//            ItemStack itemstack;
//
//            if (inventory.get(index).getCount() <= lossCount)
//            {
//                itemstack = inventory.get(index);
//                inventory.set(index, ItemStack.EMPTY);
//                this.eventHandler.onCraftMatrixChanged(this);
//                return itemstack;
//            }
//            else
//            {
//                itemstack = inventory.get(index).splitStack(lossCount);
//
//                if (inventory.get(index).getCount() == 0)
//                {
//                    inventory.set(index, ItemStack.EMPTY);
//                }
//
//                this.eventHandler.onCraftMatrixChanged(this);
//                return itemstack;
//            }
//        }
//        else
//        {
//            return ItemStack.EMPTY;
//        }
//    }
//
//    @Override
//    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_)
//    {
//        inventory.set(p_70299_1_, p_70299_2_);
//        this.eventHandler.onCraftMatrixChanged(this);
//    }
//
//    @Override
//    public int getInventoryStackLimit() { return 64; }
//
//    @Override
//    public void markDirty() {}
//
//    @Override
//    public boolean isUsableByPlayer(EntityPlayer p_70300_1_) { return true; }
//
//    @Override
//    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) { return false; }
//
//    @Override
//    public ITextComponent getDisplayName() {
//        return null;
//    }
//}
