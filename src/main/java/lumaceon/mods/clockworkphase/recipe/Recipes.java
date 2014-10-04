package lumaceon.mods.clockworkphase.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class Recipes
{
    public static void init()
    {
        ItemStack output;

        output = new ItemStack(ModBlocks.brassBlock);
        GameRegistry.addShapedRecipe(output, "bbb", "bbb", "bbb", 'b', new ItemStack(ModItems.brassIngot));

        output = new ItemStack(ModItems.blandHourglass);
        GameRegistry.addShapedRecipe(output, "ggg", " g ", "ggg", 'g', new ItemStack(Blocks.glass));

        output = new ItemStack(ModItems.mainspring);
        output.setItemDamage(output.getMaxDamage());
        GameRegistry.addShapedRecipe(output, "mmm", "m m", "mmm", 'm', new ItemStack(Items.iron_ingot));

        /** Mainspring addition crafting **/
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotIron", 25));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockIron", 225));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotBrass", 15));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotGold", 5));

        GameRegistry.addRecipe(new RecipeChronomancersHourglass());
        GameRegistry.addRecipe(new RecipeHourglassRepair());
        GameRegistry.addRecipe(new RecipeClockwork());
    }
}