package lumaceon.mods.clockworkphase.block.tileentity;

import lumaceon.mods.clockworkphase.item.windable.IWindable;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityClockworkPhaseInventory extends TileEntityClockworkPhase implements IInventory
{
    protected ItemStack[] inventory;

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        NBTTagList nbtList = new NBTTagList();
        for (int index = 0; index < inventory.length; index++)
        {
            if (inventory[index] != null)
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("slot_index", (byte)index);
                inventory[index].writeToNBT(tag);
                nbtList.appendTag(tag);
            }
        }
        nbt.setTag(NBTTags.INVENTORY_ARRAY, nbtList);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        NBTTagList tagList = nbt.getTagList(NBTTags.INVENTORY_ARRAY, 0);
        inventory = new ItemStack[getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("slot_index");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int index, int lossCount)
    {
        ItemStack is = getStackInSlot(index);
        if (is != null)
        {
            if (lossCount >= is.stackSize)
            {
                setInventorySlotContents(index, null);
            }
            else
            {
                is = is.splitStack(lossCount);
                if (is.stackSize == 0)
                {
                    setInventorySlotContents(index, null);
                }
            }
        }
        return is;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (inventory[index] != null)
        {
            ItemStack itemStack = inventory[index];
            inventory[index] = null;
            return itemStack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack is)
    {
        inventory[index] = is;

        if (is != null && is.stackSize > this.getInventoryStackLimit())
        {
            is.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    @Override
    public String getInventoryName()
    {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory() { }

    @Override
    public void closeInventory() { }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack is)
    {
        if(is != null && is.getItem() instanceof IWindable)
        {
            return true;
        }
        return false;
    }
}
