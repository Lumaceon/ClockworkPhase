package lumaceon.mods.clockworkphase.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

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
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotIron", 250));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockIron", 2250));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotBrass", 200));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotGold", 50));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotThaumium", 350));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotSteel", 750));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotLead", 200));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotCopper", 100));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotTin", 100));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotSilver", 300));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotCopper", 100));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotCobalt", 500));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotArdite", 700));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotManyullyn", 1000));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotAluminium", 200));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotAluminiumBrass", 350));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotAlumite", 500));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotElectrum", 400));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotInvar", 500));

        GameRegistry.addRecipe(new RecipeChronomancersHourglass());
        GameRegistry.addRecipe(new RecipeConstructReassemble());
        GameRegistry.addRecipe(new RecipeClockwork());
    }
}