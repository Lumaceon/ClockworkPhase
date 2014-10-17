package lumaceon.mods.clockworkphase.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes
{
    public static void init()
    {
        ItemStack output;

        output = new ItemStack(ModBlocks.brassBlock);
        GameRegistry.addShapedRecipe(output, "bbb", "bbb", "bbb", 'b', new ItemStack(ModItems.brassIngot));

        output = new ItemStack(ModItems.brassIngot);
        output.stackSize = 3;
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, "ingotIron", "ingotIron",  "ingotGold"));

        output = new ItemStack(ModItems.blandHourglass);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "ggg", " g ", "ggg", 'g', "blockGlass"));

        output = new ItemStack(ModItems.mainspring);
        output.setItemDamage(output.getMaxDamage());
        NBTHelper.setInteger(output, NBTTags.MAX_TENSION, 2000);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "mmm", "mbm", "mmm", 'm', "ingotIron", 'b', "blockIron"));

        output = new ItemStack(ModBlocks.disassembler);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bb ", "b b", "bb ", 'b', "ingotBrass"));

        output = new ItemStack(ModBlocks.windingBox);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, " i ", "bib", "bbb", 'b', "ingotBrass", 'i', "ingotIron"));

        output = new ItemStack(ModBlocks.celestialCompass);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bgb", "g g", "bgb", 'b', "blockBrass", 'g', "gearBrass"));

        output = new ItemStack(ModItems.gearBrass);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotBrass", 's', "stickWood"));

        output = new ItemStack(ModItems.gearBronze);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotBronze", 's', "stickWood"));

        output = new ItemStack(ModItems.gearDiamond);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "gemDiamond", 's', "stickWood"));

        output = new ItemStack(ModItems.gearEmerald);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "gemEmerald", 's', "stickWood"));

        output = new ItemStack(ModItems.gearIron);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotIron", 's', "stickWood"));

        output = new ItemStack(ModItems.gearSteel);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotSteel", 's', "stickWood"));

        output = new ItemStack(ModItems.framework);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bib", "iBi", "bib", 'b', "ingotBrass", 'i', "ingotIron", 'B', "blockBrass"));

        /** Mainspring addition crafting **/
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotIron", 250));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockIron", 2250));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotBrass", 350));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockBrass", 3150));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotGold", 50));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotThaumium", 350));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotSteel", 750));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockSteel", 6750));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotLead", 200));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockLead", 1800));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotCopper", 100));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockCopper", 900));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotTin", 100));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockTin", 900));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotBronze", 300));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockBronze", 2700));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotSilver", 300));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockSilver", 2700));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotCobalt", 500));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockCobalt", 4500));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotArdite", 700));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockArdite", 6300));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotManyullyn", 1000));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockManyullyn", 9000));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotAluminium", 200));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockAluminium", 1800));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotAluminiumBrass", 350));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockAluminiumBrass", 3150));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotAlumite", 500));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockAlumite", 4500));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotElectrum", 400));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockElectrum", 3600));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "ingotInvar", 500));
        GameRegistry.addRecipe(new RecipeMainspring(new ItemStack(ModItems.mainspring), "blockInvar", 4500));

        GameRegistry.addRecipe(new RecipeChronomancersHourglass());
        GameRegistry.addRecipe(new RecipeConstructReassemble());
        GameRegistry.addRecipe(new RecipeClockwork());
        GameRegistry.addRecipe(new RecipeClockworkPickaxe());
        GameRegistry.addRecipe(new RecipeClockworkAxe());
        GameRegistry.addRecipe(new RecipeClockworkShovel());
        GameRegistry.addRecipe(new RecipeTimeInfusion());
    }
}