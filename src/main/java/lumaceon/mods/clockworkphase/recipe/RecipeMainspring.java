package lumaceon.mods.clockworkphase.recipe;

import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.Logger;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;

public class RecipeMainspring extends ShapedOreRecipe implements IRecipe
{
    int metalValue;

    public RecipeMainspring(int metalValue, Object... recipe)
    {
        super(new ItemStack(Blocks.dirt), recipe);
        this.metalValue = metalValue;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting ic)
    {
        if(ic.getSizeInventory() < 5)
        {
            return null;
        }

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
        ItemStack mainspring = new ItemStack(ModItems.mainspring);
        NBTHelper.setInteger(mainspring, NBTTags.MAX_TENSION, NBTHelper.getInt(mainspring, NBTTags.MAX_TENSION) + metalValue * 8);
        return mainspring;
    }
}
