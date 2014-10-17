package lumaceon.mods.clockworkphase.recipe;

import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.component.ItemClockwork;
import lumaceon.mods.clockworkphase.item.component.ItemMainspring;
import lumaceon.mods.clockworkphase.item.construct.clockwork.IClockwork;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class RecipeConstructReassemble implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting ic, World world)
    {
        ItemStack item;
        boolean mainspring = false;
        boolean clockwork = false;
        boolean construct = false;
        boolean doubles = false;

        boolean alreadyContainsMainspring = false;
        boolean alreadyContainsClockwork = false;

        for(int n = 0; n < ic.getSizeInventory(); n++)
        {
            item = ic.getStackInSlot(n);
            if(item != null)
            {
                if(item.getItem() instanceof IClockwork)
                {
                    if(construct)
                    {
                        doubles = true;
                    }
                    construct = true;

                    if(NBTHelper.getInt(item, NBTTags.MAX_TENSION) != 0)
                    {
                        alreadyContainsMainspring = true;
                    }

                    if(NBTHelper.hasTag(item, NBTTags.CLOCKWORK))
                    {
                        alreadyContainsClockwork = true;
                    }
                }
                else if(item.getItem() instanceof ItemMainspring)
                {
                    if(mainspring)
                    {
                        doubles = true;
                    }
                    mainspring = true;
                }
                else if(item.getItem() instanceof ItemClockwork)
                {
                    if(clockwork)
                    {
                        doubles = true;
                    }
                    clockwork = true;
                }
                else
                {
                    return false;
                }
            }
        }

        if(alreadyContainsMainspring && mainspring)
        {
            return false;
        }

        if(alreadyContainsClockwork && clockwork)
        {
            return false;
        }

        if(doubles) { return false; }
        if((mainspring || clockwork) && construct) { return true; }

        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting ic)
    {
        ItemStack tempItem;
        ItemStack mainspring = null;
        ItemStack clockwork = null;
        ItemStack construct = null;

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

                if(tempItem.getItem() instanceof IClockwork)
                {
                    construct = tempItem.copy();
                }
            }
        }

        ItemStack output = construct;
        if(mainspring != null)
        {
            int currentTension = NBTHelper.getInt(mainspring, NBTTags.TENSION_ENERGY);
            int maxTension = NBTHelper.getInt(mainspring, NBTTags.MAX_TENSION);

            NBTHelper.setInteger(output, NBTTags.TENSION_ENERGY, currentTension);
            NBTHelper.setInteger(output, NBTTags.MAX_TENSION, maxTension);

            if(maxTension / 10 == 0) { output.setItemDamage(output.getMaxDamage()); }
            else { output.setItemDamage(10 - (currentTension / (maxTension / 10))); }
        }

        if(clockwork != null)
        {
            NBTHelper.setInteger(output, NBTTags.QUALITY, NBTHelper.getInt(clockwork, NBTTags.QUALITY));
            NBTHelper.setInteger(output, NBTTags.SPEED, NBTHelper.getInt(clockwork, NBTTags.SPEED));
            NBTHelper.setInteger(output, NBTTags.MEMORY, NBTHelper.getInt(clockwork, NBTTags.MEMORY));

            NBTTagList nbtList = new NBTTagList();
            NBTTagCompound tag = new NBTTagCompound();
            clockwork.writeToNBT(tag);
            nbtList.appendTag(tag);
            NBTHelper.setTagList(output, NBTTags.CLOCKWORK, nbtList);
        }
        return output;
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
