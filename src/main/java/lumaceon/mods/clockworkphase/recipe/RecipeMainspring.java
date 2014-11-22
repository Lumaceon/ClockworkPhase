package lumaceon.mods.clockworkphase.recipe;

import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class RecipeMainspring implements IRecipe
{
    ItemStack mainspring;
    String metal;
    int metalValue;

    public RecipeMainspring(ItemStack mainspring, String metal, int metalValue)
    {
        this.mainspring = mainspring.copy();
        this.metal = metal;
        this.metalValue = metalValue;
        this.mainspring.setItemDamage(this.mainspring.getMaxDamage());
    }

    @Override
    public boolean matches(InventoryCrafting ic, World world)
    {
        ItemStack item;
        for(int n = 0; n < ic.getSizeInventory(); n++)
        {
            item = ic.getStackInSlot(n);
            if(item != null)
            {
                if(n == 4 && !item.getItem().equals(this.mainspring.getItem())) //Center components
                {
                    return false;
                }
                if(n != 4)
                {
                    ArrayList<ItemStack> metals = OreDictionary.getOres(this.metal);
                    boolean equal = false;
                    for(int i = 0; i < metals.size() && !equal; i++)
                    {
                        if(metals.get(i).getItem().equals(item.getItem()))
                        {
                            equal = true;
                        }
                    }

                    if(!equal)
                    {
                        return false;
                    }
                }
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting ic)
    {
        ItemStack output = ic.getStackInSlot(4).copy();
        int previousMaxTension = NBTHelper.getInt(output, NBTTags.MAX_TENSION);
        if(!NBTHelper.hasTag(output, NBTTags.MAX_TENSION)) { NBTHelper.setInteger(output, NBTTags.MAX_TENSION, 8 * metalValue); }
        else
        {
            if(previousMaxTension + 8 * metalValue >= MechanicTweaker.MAX_TENSION)
            {
                NBTHelper.setInteger(output, NBTTags.MAX_TENSION, MechanicTweaker.MAX_TENSION);
            }
            else
            {
                NBTHelper.setInteger(output, NBTTags.MAX_TENSION, previousMaxTension + 8 * metalValue);
            }
        }
        if(output.getMaxDamage() == 0)
        {
            output.setItemDamage(0);
        }
        else if(MechanicTweaker.MAX_TENSION / output.getMaxDamage() == 0)
        {
            output.setItemDamage(0);
        }
        else
        {
            output.setItemDamage(output.getMaxDamage() - ((previousMaxTension + 8 * metalValue) / (MechanicTweaker.MAX_TENSION / output.getMaxDamage())));
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
        return mainspring.copy();
    }
}
