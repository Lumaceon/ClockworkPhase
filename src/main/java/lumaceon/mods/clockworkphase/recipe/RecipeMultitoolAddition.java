package lumaceon.mods.clockworkphase.recipe;

import lumaceon.mods.clockworkphase.item.ItemTemporalMultitool;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeMultitoolAddition implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting ic, World world)
    {
        ItemStack item;
        boolean fluxItem = false;
        boolean multitool = false;
        boolean doubles = false;

        for(int n = 0; n < ic.getSizeInventory(); n++)
        {
            item = ic.getStackInSlot(n);
            if(item != null)
            {
                if(item.getItem() instanceof ItemTemporalMultitool)
                {
                    if(multitool)
                    {
                        doubles = true;
                    }
                    multitool = true;

                    ItemStack[] items = NBTHelper.getInventoryFromNBTTag(item, NBTTags.TEMPORAL_ITEMS);
                    if(items != null && items.length >= 20)
                    {
                       return false;
                    }
                }
                else if(item.getItem().getItemStackLimit(item) == 1 && !(item.getItem() instanceof ItemBlock) && !(item.getItem() instanceof ItemTemporalMultitool) && !(item.getItem() instanceof ItemPotion) && !(item.getItem() instanceof ItemArmor) && !(item.getItem() instanceof ItemBucket))
                {
                    if(fluxItem)
                    {
                        doubles = true;
                    }
                    fluxItem = true;

                    if(NBTHelper.hasTag(item, NBTTags.TEMPORAL_ITEMS))
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
        }

        if(doubles) { return false; }
        return fluxItem && multitool;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting ic)
    {
        ItemStack tempItem;
        ItemStack fluxableItem = null;
        ItemStack multitool = null;

        for(int n = 0; n < ic.getSizeInventory(); n++)
        {
            tempItem = ic.getStackInSlot(n);
            if (tempItem != null)
            {
                if(tempItem.getItem() instanceof ItemTemporalMultitool)
                {
                    multitool = tempItem.copy();
                }

                if(tempItem.getItem().getItemStackLimit(tempItem) == 1 && !(tempItem.getItem() instanceof ItemBlock) && !(tempItem.getItem() instanceof ItemTemporalMultitool))
                {
                    fluxableItem = tempItem.copy();
                }
            }
        }

        if(fluxableItem != null && multitool != null)
        {
            ItemStack[] inventory = NBTHelper.getInventoryFromNBTTag(multitool, NBTTags.TEMPORAL_ITEMS);
            ItemStack[] resultInventory;
            if(inventory != null)
            {
                resultInventory = new ItemStack[inventory.length + 1];
                for(int n = 0; n < inventory.length; n++)
                {
                    resultInventory[n] = inventory[n];
                }
            }
            else
            {
                resultInventory = new ItemStack[1];
            }

            resultInventory[resultInventory.length - 1] = fluxableItem;
            NBTHelper.setNBTTagListFromInventory(multitool, NBTTags.TEMPORAL_ITEMS, resultInventory);
            return multitool;
        }
        return null;
    }

    @Override
    public int getRecipeSize()
    {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return null;
    }
}
