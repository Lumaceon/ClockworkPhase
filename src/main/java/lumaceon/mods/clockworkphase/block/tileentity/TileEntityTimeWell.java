package lumaceon.mods.clockworkphase.block.tileentity;

import lumaceon.mods.clockworkphase.item.construct.abstracts.ITimeSand;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.network.MessageTimeSandCapacitorSync;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;

public class TileEntityTimeWell extends TileEntityTimeSandCapacitor implements IInventory
{
    public ItemStack[] inventory;

    public TileEntityTimeWell()
    {
        inventory = new ItemStack[2];
    }

    @Override
    public int getMaxTimeSandCapacity()
    {
        return MechanicTweaker.MAX_TS_TIME_WELL;
    }

    @Override
    public void updateEntity()
    {
        if(inventory[0] != null && inventory[0].getItem() instanceof ITimeSand)
        {
            if(getTimeSand() + 500 <= getMaxTimeSandCapacity()) //All time sand can be added
            {
                addTimeSand(((ITimeSand)inventory[0].getItem()).removeTimeSand(inventory[0], 500));
            }
            else //Time sand addition goes over max capacity
            {
                addTimeSand(((ITimeSand) inventory[0].getItem()).removeTimeSand(inventory[0], getMaxTimeSandCapacity() - getTimeSand())); //Add only enough to max.
            }
        }

        if(inventory[1] != null && inventory[1].getItem() instanceof ITimeSand && getTimeSand() > 0)
        {
            int timeSand = ((ITimeSand) inventory[1].getItem()).getMaxTimeSand() - ((ITimeSand) inventory[1].getItem()).getTimeSand(inventory[1]);
            if(timeSand < 500)
            {
                ((ITimeSand) inventory[1].getItem()).addTimeSand(inventory[1], removeTimeSand(timeSand));
            }
            else
            {
                ((ITimeSand) inventory[1].getItem()).addTimeSand(inventory[1], removeTimeSand(500));
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        NBTTagCompound compound = new NBTTagCompound();
        if(inventory[0] != null) {inventory[0].writeToNBT(compound);}
        nbt.setTag(NBTTags.INVENTORY_ARRAY, compound);

        compound = new NBTTagCompound();
        if(inventory[1] != null) {inventory[1].writeToNBT(compound);}
        nbt.setTag(NBTTags.INVENTORY_ARRAY + "1", compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        inventory[0] = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag(NBTTags.INVENTORY_ARRAY));
        inventory[1] = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag(NBTTags.INVENTORY_ARRAY + "1"));
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTimeSandCapacitorSync(this));
    }

    @Override
    public int getSizeInventory()
    {
        return 2;
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
        return 1;
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
        return is.getItem() instanceof ITimeSand;
    }
}
