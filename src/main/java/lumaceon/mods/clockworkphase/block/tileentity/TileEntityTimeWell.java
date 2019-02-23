package lumaceon.mods.clockworkphase.block.tileentity;

import lumaceon.mods.clockworkphase.custom.IInventoryHelper;
import lumaceon.mods.clockworkphase.item.construct.abstracts.ITimeSand;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileEntityTimeWell extends TileEntityTimeSandCapacitor implements IInventoryHelper, ITickable
{
    public NonNullList<ItemStack> inventory;
//    public ItemStack[] inventory;

    public TileEntityTimeWell()
    {
        inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    }

    @Override
    public NonNullList<ItemStack> getInv() {
        return inventory;
    }

    @Override
    public int getMaxTimeSandCapacity()
    {
        return MechanicTweaker.MAX_TS_TIME_WELL;
    }

    @Override
    public void update()
    {
        if(!inventory.get(0).isEmpty() && inventory.get(0).getItem() instanceof ITimeSand)
        {
            if(getTimeSand() + 500 <= getMaxTimeSandCapacity()) //All time sand can be added
            {
                addTimeSand(((ITimeSand) inventory.get(0).getItem()).removeTimeSand(inventory.get(0), 500));
            }
            else //Time sand addition goes over max capacity
            {
                addTimeSand(((ITimeSand) inventory.get(0).getItem()).removeTimeSand(inventory.get(0), getMaxTimeSandCapacity() - getTimeSand())); //Add only enough to max.
            }
        }

        if(!inventory.get(1).isEmpty() && inventory.get(1).getItem() instanceof ITimeSand && getTimeSand() > 0)
        {
            int timeSand = ((ITimeSand) inventory.get(1).getItem()).getMaxTimeSand() - ((ITimeSand) inventory.get(1).getItem()).getTimeSand(inventory.get(1));
            if(timeSand < 500)
            {
                ((ITimeSand) inventory.get(1).getItem()).addTimeSand(inventory.get(1), removeTimeSand(timeSand));
            }
            else
            {
                ((ITimeSand) inventory.get(1).getItem()).addTimeSand(inventory.get(1), removeTimeSand(500));
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        NBTTagCompound compound = new NBTTagCompound();
        if (!inventory.get(0).isEmpty())
            inventory.get(0).writeToNBT(compound);
        nbt.setTag(NBTTags.INVENTORY_ARRAY, compound);

        compound = new NBTTagCompound();
        if (!inventory.get(1).isEmpty())
            inventory.get(1).writeToNBT(compound);
        nbt.setTag(NBTTags.INVENTORY_ARRAY + "1", compound);
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        inventory.set(0, new ItemStack(nbt.getCompoundTag(NBTTags.INVENTORY_ARRAY)));
        inventory.set(1, new ItemStack(nbt.getCompoundTag(NBTTags.INVENTORY_ARRAY + "1")));
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack is)
    {
        return is.getItem() instanceof ITimeSand;
    }
}
