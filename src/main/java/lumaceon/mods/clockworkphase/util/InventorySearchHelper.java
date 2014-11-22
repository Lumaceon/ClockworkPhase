package lumaceon.mods.clockworkphase.util;

import lumaceon.mods.clockworkphase.item.construct.pocketwatch.ItemPocketWatch;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class InventorySearchHelper
{
    public static ItemStack[] getPocketWatches(IInventory inventory)
    {
        ItemStack[] result = null;
        if(inventory != null)
        {
            ArrayList<ItemStack> tempList = new ArrayList<ItemStack>(5);
            for(int n = 0; n < inventory.getSizeInventory(); n++)
            {
                if(inventory.getStackInSlot(n) != null && inventory.getStackInSlot(n).getItem() instanceof ItemPocketWatch)
                {
                    tempList.add(inventory.getStackInSlot(n));
                }
            }
            if(!tempList.isEmpty())
            {
                result = new ItemStack[tempList.size()];
                for(int n = 0; n < tempList.size(); n++)
                {
                    result[n] = tempList.get(n);
                }
            }
        }
        return result;
    }
}
