package lumaceon.mods.clockworkphase.block.tileentity;

import lumaceon.mods.clockworkphase.custom.IInventoryHelper;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;

public class TileEntityClockworkPhaseInventory extends TileEntityClockworkPhase implements IInventoryHelper
{
//    protected ItemStack[] inventory;
    protected NonNullList<ItemStack> inventory;

    @Override
    public NonNullList<ItemStack> getInv() {
        return inventory;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        NBTTagList nbtList = new NBTTagList();
        for (int index = 0; index < inventory.size(); index++)
        {
            if (!inventory.get(index).isEmpty())
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("slot_index", (byte)index);
                inventory.get(index).writeToNBT(tag);
                nbtList.appendTag(tag);
            }
        }
        nbt.setTag(NBTTags.INVENTORY_ARRAY, nbtList);
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        NBTTagList tagList = nbt.getTagList(NBTTags.INVENTORY_ARRAY, 10);
        inventory = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("slot_index");
            if (slotIndex >= 0 && slotIndex < inventory.size())
            {
                inventory.set(slotIndex, new ItemStack(tagCompound));
            }
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack is)
    {
        return true;
    }
}
