package lumaceon.mods.clockworkphase.recipe;

import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.component.IBaseComponent;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import java.util.ArrayList;

public class RecipeClockwork implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting ic, World world)
    {
        ItemStack item;
        boolean validFound = false;

        for(int n = 0; n < ic.getSizeInventory(); n++)
        {
            item = ic.getStackInSlot(n);
            if(item != null)
            {
                if(item.getItem() instanceof IBaseComponent)
                {
                    validFound = true;
                }
                else
                {
                    return false;
                }
            }
        }

        if(validFound) { return true; }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting ic)
    {
        IBaseComponent componentData;
        ItemStack tempItem;
        ItemStack output;
        ArrayList<ItemStack> components = new ArrayList<ItemStack>(9);

        for(int n = 0; n < ic.getSizeInventory(); n++)
        {
            tempItem = ic.getStackInSlot(n);
            if (tempItem != null)
            {
                if(tempItem.getItem() instanceof IBaseComponent)
                {
                    components.add(tempItem.copy());
                }
            }
        }

        output = new ItemStack(ModItems.clockwork);

        int resultingQuality = 0;
        int resultingSpeed = 0;
        int resultingMemory = 0;

        if(components.size() > 0)
        {
            NBTTagList nbtList = new NBTTagList();
            for(int n = 0; n < components.size(); n++)
            {
                tempItem = components.get(n);

                //NBT data save of the ItemStack
                NBTTagCompound tag = new NBTTagCompound();
                tempItem.writeToNBT(tag);
                nbtList.appendTag(tag);

                componentData = (IBaseComponent)tempItem.getItem();

                if(componentData.isComponentQuality(tempItem))
                {
                    resultingQuality += componentData.getGearQuality(tempItem);
                }

                if(componentData.isComponentSpeedy(tempItem))
                {
                    resultingSpeed += componentData.getGearSpeed(tempItem);
                }

                if(componentData.isComponentMemory(tempItem))
                {
                    resultingMemory += componentData.getMemoryValue(tempItem);
                }
            }
            NBTHelper.setTag(output, NBTTags.INVENTORY_ARRAY, nbtList);
        }

        NBTHelper.setInteger(output, NBTTags.QUALITY, resultingQuality);
        NBTHelper.setInteger(output, NBTTags.SPEED, resultingSpeed);
        NBTHelper.setInteger(output, NBTTags.MEMORY, resultingMemory);

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
        return new ItemStack(ModItems.clockwork);
    }
}
