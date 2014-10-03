package lumaceon.mods.clockworkphase.recipe;

import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.IDisassemble;
import lumaceon.mods.clockworkphase.item.ItemClockwork;
import lumaceon.mods.clockworkphase.item.ItemMainspring;
import lumaceon.mods.clockworkphase.item.elemental.hourglass.ItemHourglass;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeHourglassRepair implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting ic, World world)
    {
        ItemStack item;
        boolean mainspring = false;
        boolean clockwork = false;
        boolean hourglass = false;
        boolean doubles = false;

        for(int n = 0; n < ic.getSizeInventory(); n++)
        {
            item = ic.getStackInSlot(n);
            if(item != null)
            {
                if(item.getItem() instanceof ItemMainspring)
                {
                    if(mainspring)
                    {
                        doubles = true;
                    }
                    mainspring = true;
                }

                if(item.getItem() instanceof ItemClockwork)
                {
                    if(clockwork)
                    {
                        doubles = true;
                    }
                    clockwork = true;
                }

                if(item.getItem() instanceof ItemHourglass)
                {
                    if(hourglass)
                    {
                        doubles = true;
                    }
                    hourglass = true;
                }
            }
        }

        if(doubles) { return false; }
        if((mainspring || clockwork) && hourglass) { return true; }

        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting ic)
    {
        ItemStack tempItem;
        ItemStack mainspring = null;
        ItemStack clockwork = null;
        ItemStack hourglass = null;

        for(int n = 0; n < ic.getSizeInventory(); n++)
        {
            tempItem = ic.getStackInSlot(n);
            if (tempItem != null)
            {
                if(tempItem.getItem() instanceof ItemMainspring)
                {
                    mainspring = tempItem.copy();
                }

                if(tempItem.getItem() instanceof ItemClockwork)
                {
                    clockwork = tempItem.copy();
                }

                if(tempItem.getItem() instanceof ItemHourglass)
                {
                    hourglass = tempItem.copy();
                }
            }
        }

        tempItem = new ItemStack(hourglass.getItem());
        if(mainspring != null)
        {
            NBTHelper.setInteger(tempItem, NBTTags.MAX_TENSION, NBTHelper.getInt(mainspring, NBTTags.MAX_TENSION));
        }

        if(clockwork != null)
        {
            //Do clockworky stuff.
        }

        return tempItem;
    }

    @Override
    public int getRecipeSize()
    {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return new ItemStack(ModItems.hourglass);
    }
}
