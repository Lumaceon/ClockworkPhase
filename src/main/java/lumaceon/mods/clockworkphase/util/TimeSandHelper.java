package lumaceon.mods.clockworkphase.util;

import lumaceon.mods.clockworkphase.item.construct.abstracts.ITimeSandSupplier;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class TimeSandHelper
{
    public static int removeTimeSandFromInventory(IInventory inventory, int timeSand)
    {
        ItemStack is;
        int amountRemoved;
        int originalTimeSand = timeSand;
        for(int n = 0; n < inventory.getSizeInventory(); n++)
        {
            is = inventory.getStackInSlot(n);
            if(!is.isEmpty() && is.getItem() instanceof ITimeSandSupplier)
            {
                amountRemoved = ((ITimeSandSupplier)is.getItem()).removeTimeSand(is, timeSand);
                timeSand -= amountRemoved;

                if(timeSand <= 0)
                {
                    return originalTimeSand;
                }
            }
        }
        return originalTimeSand - timeSand;
    }

    public static int getTimeSandFromInventory(IInventory inventory)
    {
        ItemStack is;
        int amountAvailable = 0;
        for(int n = 0; n < inventory.getSizeInventory(); n++)
        {
            is = inventory.getStackInSlot(n);
            if(!is.isEmpty() && is.getItem() instanceof ITimeSandSupplier)
            {
                amountAvailable += ((ITimeSandSupplier)is.getItem()).getTimeSandAvailable(is);
            }
        }
        return amountAvailable;
    }

    /**
     * Attempts to add time sand to the item stack.
     *
     * @param is The item stack to add to.
     * @param timeSand The time sand to add.
     * @param maxTimeSand The max capacity of the item stack.
     * @return The amount of time sand that was able to be added.
     */
    public static int addTimeSand(ItemStack is, int timeSand, int maxTimeSand)
    {
        int currentTimeSand = NBTHelper.getInt(is, NBTTags.INTERNAL_TIME_SAND);

        if(currentTimeSand + timeSand >= maxTimeSand)
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, maxTimeSand);
            return maxTimeSand - currentTimeSand;
        }
        else
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, currentTimeSand + timeSand);
            return timeSand;
        }
    }

    /**
     * Attempts to remove time sand from the item stack.
     *
     * @param is The item stack to remove from.
     * @param timeSand The amount of time sand to remove.
     * @return The amount of time sand that was actually removed.
     */
    public static int removeTimeSand(ItemStack is, int timeSand)
    {
        int currentTimeSand = NBTHelper.getInt(is, NBTTags.INTERNAL_TIME_SAND);

        if(currentTimeSand - timeSand <= 0)
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, 0);
            return currentTimeSand;
        }
        else if(timeSand <= 0)
        {
            return 0;
        }
        else
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, currentTimeSand - timeSand);
            return timeSand;
        }
    }

    /**
     * Gets the time sand within the given item.
     * @param is
     * @return The time sand contained with the item stack.
     */
    public static int getTimeSand(ItemStack is)
    {
        return NBTHelper.getInt(is, NBTTags.INTERNAL_TIME_SAND);
    }
}
