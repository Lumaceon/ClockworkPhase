package lumaceon.mods.clockworkphase.recipe;

import lumaceon.mods.clockworkphase.item.construct.pocketwatch.ItemPocketWatch;
import lumaceon.mods.clockworkphase.item.construct.pocketwatch.module.ItemModule;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipePocketWatchModule implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting ic, World world)
    {
        ItemStack item;
        ItemStack pocketWatch = null;
        ItemStack module = null;
        boolean moduleExists = false;
        boolean pocketWatchExists = false;

        for(int n = 0; n < ic.getSizeInventory(); n++)
        {
            item = ic.getStackInSlot(n);
            if(item != null)
            {
                if(item.getItem() instanceof ItemPocketWatch)
                {
                    pocketWatch = item.copy();
                    if(pocketWatchExists)
                    {
                        return false;
                    }
                    pocketWatchExists = true;

                    ItemStack[] items = NBTHelper.getInventoryFromNBTTag(item, NBTTags.POCKET_WATCH_MODULES);
                    if(items != null && items.length >= 20)
                    {
                        return false;
                    }
                }
                else if(item.getItem() instanceof ItemModule)
                {
                    module = item.copy();
                    if(moduleExists)
                    {
                        return false;
                    }
                    moduleExists = true;

                    if(NBTHelper.hasTag(item, NBTTags.POCKET_WATCH_MODULES))
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

        if(pocketWatch != null && module != null && NBTHelper.hasTag(pocketWatch, NBTTags.POCKET_WATCH_MODULES))
        {
            ItemStack[] items = NBTHelper.getInventoryFromNBTTag(pocketWatch, NBTTags.POCKET_WATCH_MODULES);
            for(int n = 0; n < items.length; n++)
            {
                if(items[n] != null)
                {
                    if(items[n].getItem().equals(module.getItem()))
                    {
                        return false;
                    }
                }
            }
        }
        return moduleExists && pocketWatchExists;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting ic)
    {
        ItemStack tempItem;
        ItemStack module = null;
        ItemStack pocketWatch = null;

        for(int n = 0; n < ic.getSizeInventory(); n++)
        {
            tempItem = ic.getStackInSlot(n);
            if (tempItem != null)
            {
                if(tempItem.getItem() instanceof ItemPocketWatch)
                {
                    pocketWatch = tempItem.copy();
                }

                if(tempItem.getItem() instanceof ItemModule)
                {
                    module = tempItem.copy();
                }
            }
        }

        if(module != null && pocketWatch != null)
        {
            ItemStack[] inventory = NBTHelper.getInventoryFromNBTTag(pocketWatch, NBTTags.POCKET_WATCH_MODULES);
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

            resultInventory[resultInventory.length - 1] = module;
            NBTHelper.setNBTTagListFromInventory(pocketWatch, NBTTags.POCKET_WATCH_MODULES, resultInventory);
            return pocketWatch;
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
