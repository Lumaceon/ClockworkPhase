package lumaceon.mods.clockworkphase.custom;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface IInventoryHelper extends IInventory {

    public NonNullList<ItemStack> getInv();

    @Override
    default int getSizeInventory() {
        return getInv().size();
    }

    @Override
    default boolean isEmpty() {
        for (ItemStack stack : getInv())
            if (!stack.isEmpty())
                return false;
        return true;
    }

    @Override
    default ItemStack getStackInSlot(int index) {
        return getInv().get(index);
    }

    @Override
    default ItemStack decrStackSize(int index, int lossCount) {
        ItemStack is = getStackInSlot(index);
        if (!is.isEmpty())
        {
            if (lossCount >= is.getCount())
            {
                setInventorySlotContents(index, ItemStack.EMPTY);
            }
            else
            {
                is = is.splitStack(lossCount);
                if (is.getCount() == 0)
                {
                    setInventorySlotContents(index, ItemStack.EMPTY);
                }
            }
        }
        return is;
    }

    @Override
    default ItemStack removeStackFromSlot(int index) {
        if (!getInv().get(index).isEmpty())
        {
            ItemStack itemStack = getInv().get(index);
            getInv().set(index, ItemStack.EMPTY);
            return itemStack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    default void setInventorySlotContents(int index, ItemStack is) {
        getInv().set(index, is);

        if (!is.isEmpty() && is.getCount() > this.getInventoryStackLimit())
        {
            is.setCount(this.getInventoryStackLimit());
        }
        this.markDirty();
    }

    @Override
    default void openInventory(EntityPlayer player) {

    }

    @Override
    default void closeInventory(EntityPlayer player) {

    }

    @Override
    default int getField(int id) {
        return 0;
    }

    @Override
    default void setField(int id, int value) {

    }

    @Override
    default int getFieldCount() {
        return 0;
    }

    @Override
    default void clear() {
        getInv().clear();
    }

    @Override
    default String getName() {
        return null;
    }

    @Override
    default boolean hasCustomName() {
        return false;
    }
}
