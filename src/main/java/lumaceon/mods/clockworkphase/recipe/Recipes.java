package lumaceon.mods.clockworkphase.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
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
        initMachineRecipes();
        initPocketWatchAndModules();
        initToolRecipes();
        initMiscRecipes();
        initClockworkComponentRecipes();
        initMetalRecipes();
        initMainspringRecipes();
        initSpecialRecipes();
    }

    public static void initMachineRecipes()
    {
        ItemStack output;

        output = new ItemStack(ModBlocks.timeWell);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bSb", "ATA", "bSb", 'b', "ingotBrass", 'S', ModItems.temporalCoreSedate, 'T', "ingotTemporal", 'A', ModItems.temporalCoreActive));

        output = new ItemStack(ModBlocks.extractorsElements[0]);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Items.golden_apple)); //Life
        output = new ItemStack(ModBlocks.extractorsElements[1]);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Blocks.torch)); //Light
        output = new ItemStack(ModBlocks.extractorsElements[2]);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Items.water_bucket)); //Water
        output = new ItemStack(ModBlocks.extractorsElements[3]);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', "stone")); //Earth
        output = new ItemStack(ModBlocks.extractorsElements[4]);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Items.feather)); //Air
        output = new ItemStack(ModBlocks.extractorsElements[5]);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Items.flint_and_steel)); //Fire
        output = new ItemStack(ModBlocks.extractorsElements[6]);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Items.ghast_tear)); //Lunar
        output = new ItemStack(ModBlocks.extractorsElements[7]);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Items.bone)); //Death
    }

    public static void initToolRecipes()
    {
        ItemStack output;
        ItemStack framework = new ItemStack(ModItems.framework);

        output = new ItemStack(ModItems.hourglass);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bSb", "bhb", "bAb",
                'b', "ingotBrass", 'h', ModItems.blandHourglass, 'S', ModItems.temporalCoreSedate, 'A', ModItems.temporalCoreActive));

        output = new ItemStack(ModItems.clockworkPickaxe);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bfb", " s ", " s ",
                'b', "ingotBrass", 'f', framework, 's', "stickWood"));

        output = new ItemStack(ModItems.clockworkAxe);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bf ", "bs ", " s ",
                'b', "ingotBrass", 'f', framework, 's', "stickWood"));
        output = new ItemStack(ModItems.clockworkAxe);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, " fb", " sb", " s ",
                'b', "ingotBrass", 'f', framework, 's', "stickWood"));

        output = new ItemStack(ModItems.clockworkShovel);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, " f ", " s ", " s ",
                'f', new ItemStack(ModItems.framework), 's', "stickWood"));

        output = new ItemStack(ModItems.clockworkSaber);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, " b ", "bfb", "bsb",
                'b', "ingotBrass", 'f', framework, 's', "stickWood"));

        output = new ItemStack(ModItems.temporalMultitool);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "s a", " t ", "a s",
                's', ModItems.temporalCoreSedate, 'a', ModItems.temporalCoreActive, 't', "ingotTemporal"));

        output = new ItemStack(ModItems.chronomancerHeadpiece);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bfb", "b b", "   ",
                'b', "ingotBrass", 'f', framework));

        output = new ItemStack(ModItems.chronomancerChestplate);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "b b", "bfb", "bbb",
                'b', "ingotBrass", 'f', framework));

        output = new ItemStack(ModItems.chronomancerLeggings);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bfb", "b b", "b b",
                'b', "ingotBrass", 'f', framework));

        output = new ItemStack(ModItems.chronomancerBoots);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "   ", "b b", "bfb",
                'b', "ingotBrass", 'f', framework));

        output = new ItemStack(ModItems.timeTuner);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "b b", "btb", " b ",
                'b', "ingotBrass", 't', "ingotTemporal"));
    }

    public static void initPocketWatchAndModules()
    {
        ItemStack output;

        output = new ItemStack(ModItems.pocketWatch);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bsb", "tct", "bab",
                'b', "ingotBrass", 's', ModItems.temporalCoreSedate, 'c', Items.clock, 't', "ingotTemporal", 'a', ModItems.temporalCoreActive));

        output = new ItemStack(ModItems.moduleLifeWalk);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "gbg", "tSt", "sss",
                'g', Items.golden_apple, 'b', Items.diamond_boots, 't', "ingotTemporal", 'S', ModItems.temporalCoreSedate, 's', "stone"));

        output = new ItemStack(ModItems.moduleDeathWalk);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bwb", "tSt", "sss",
                'b', Items.diamond_boots, 'w', Items.diamond_sword, 't', "ingotTemporal", 'S', ModItems.temporalCoreSedate, 's', "stone"));

        output = new ItemStack(ModItems.moduleFurnace);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "flf", "tSt", "flf",
                'f', Blocks.furnace, 'l', Items.lava_bucket, 't', "ingotTemporal", 'S', ModItems.temporalCoreSedate));

        output = new ItemStack(ModItems.moduleSilkTouch);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "ses", "tSt", "sbs",
                's', Items.string, 'e', Blocks.enchanting_table, 't', "ingotTemporal", 'S', ModItems.temporalCoreSedate, 'b', Blocks.bookshelf));

        output = new ItemStack(ModItems.modulePartialGravity);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "fwf", "tSt", "fwf",
                'f', Items.feather, 'w', Blocks.wool, 't', "ingotTemporal", 'S', ModItems.temporalCoreSedate));
    }

    public static void initMiscRecipes()
    {
        ItemStack output;

        output = new ItemStack(ModItems.temporalCoreActive);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "ttt", "rTr", "ttt",
                'T', "ingotTemporal", 't', "nuggetTemporal", 'r', "dustRedstone"));
        output = new ItemStack(ModItems.temporalCoreSedate);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "ttt", "sTs", "ttt",
                'T', "ingotTemporal", 't', "nuggetTemporal", 's', "stone"));

        output = new ItemStack(ModItems.blandHourglass);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "ggg", " g ", "ggg", 'g', "blockGlass"));
        output = new ItemStack(ModBlocks.disassembler);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bb ", "b b", "bb ", 'b', "ingotBrass"));
        output = new ItemStack(ModBlocks.windingBox);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, " i ", "bib", "bbb", 'b', "ingotBrass", 'i', "ingotIron"));

        output = new ItemStack(ModBlocks.celestialCompass);
        GameRegistry.addShapedRecipe(output, "dLl", "mCw", "fae",
                'd', Items.bone, 'L', Items.golden_apple, 'l', Blocks.glowstone,
                'm', Items.ghast_tear, 'C', Items.clock, 'w', Items.water_bucket,
                'f', Items.flint_and_steel, 'a', Items.feather, 'e', Blocks.stone);

        output = new ItemStack(ModItems.catalystElements[0]); //Life
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "w t", "wTt", "w t",
                'w', Items.wheat, 't', Blocks.torch, 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[1]); //Light
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "g w", "gTw", "g w",
                'g', Blocks.glowstone, 'w', Blocks.wool, 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[2]); //Water
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "w l", "wTl", "w l",
                'w', Items.water_bucket, 'l', Items.lava_bucket, 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[3]); //Earth
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "b a", "bTa", "b a",
                'b', Items.bone, 'a', Items.stone_axe, 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[4]); //Air
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "f s", "fTs", "f s",
                'f', Items.feather, 's', "stone", 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[5]); //Fire
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "f w", "fTw", "f w",
                'f', Items.flint_and_steel, 'w', Items.water_bucket, 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[6]); //Lunar
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "g s", "gTs", "g s",
                'g', "blockGlass", 's', "stone", 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[7]); //Death
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "s g", "sTg", "s g",
                's', Items.stone_sword, 'g', Items.golden_apple, 'T', "ingotTemporal"));
    }

    public static void initClockworkComponentRecipes()
    {
        ItemStack output;

        output = new ItemStack(ModItems.gearBrass);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotBrass", 's', "stickWood"));
        output = new ItemStack(ModItems.gearBronze);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotBronze", 's', "stickWood"));
        output = new ItemStack(ModItems.gearCopper);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotCopper", 's', "stickWood"));
        output = new ItemStack(ModItems.gearDiamond);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "gemDiamond", 's', "stickWood"));
        output = new ItemStack(ModItems.gearEmerald);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "gemEmerald", 's', "stickWood"));
        output = new ItemStack(ModItems.gearIron);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotIron", 's', "stickWood"));
        output = new ItemStack(ModItems.gearLead);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotLead", 's', "stickWood"));
        output = new ItemStack(ModItems.gearSilver);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotSilver", 's', "stickWood"));
        output = new ItemStack(ModItems.gearSteel);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotSteel", 's', "stickWood"));
        output = new ItemStack(ModItems.gearTemporal);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotTemporal", 's', "stickWood"));
        output = new ItemStack(ModItems.gearThaumium);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotThaumium", 's', "stickWood"));
        output = new ItemStack(ModItems.gearTin);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "sis", "i i", "sis", 'i', "ingotTin", 's', "stickWood"));
        output = new ItemStack(ModItems.framework);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "bib", "ibi", "bib", 'b', "ingotBrass", 'i', "ingotIron"));
    }

    public static void initMetalRecipes()
    {
        ItemStack output;

        //Ingot to Block
        output = new ItemStack(ModBlocks.blockTemporal);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "iii", "iii", "iii", 'i', "ingotTemporal"));
        output = new ItemStack(ModBlocks.brassBlock);
        GameRegistry.addShapedRecipe(output, "bbb", "bbb", "bbb", 'b', new ItemStack(ModItems.brassIngot));

        //Block to Ingot
        output = new ItemStack(ModItems.brassIngot); output.stackSize = 9;
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, "blockBrass"));
        output = new ItemStack(ModItems.ingotTemporal); output.stackSize = 9;
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, "blockTemporal"));

        //Nugget to Ingot
        output = new ItemStack(ModItems.ingotTemporal);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "iii", "iii", "iii", 'i', "nuggetTemporal"));

        //Ingot to Nugget
        output = new ItemStack(ModItems.nuggetTemporal); output.stackSize = 9;
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, "ingotTemporal"));

        //Misc...
        ItemStack chip = new ItemStack(ModItems.chipTemporal);
        output = new ItemStack(ModItems.nuggetTemporal);
        GameRegistry.addShapelessRecipe(output, chip, chip, chip, chip);
        output = new ItemStack(ModItems.brassIngot); output.stackSize = 3;
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, "ingotIron", "ingotIron", "ingotIron", "ingotGold"));
    }

    public static void initMainspringRecipes()
    {
        ItemStack output;
        int multiplier = MechanicTweaker.MAINSPRING_TENSION_MUILTIPLIER;

        output = new ItemStack(ModItems.mainspring);
        output.setItemDamage(output.getMaxDamage());
        NBTHelper.setInteger(output, NBTTags.MAX_TENSION, 2000);
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "mmm", "msm", "mmm", 'm', "ingotIron", 's', "stickWood"));

        GameRegistry.addRecipe(new RecipeMainspring(250 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotIron"));
        GameRegistry.addRecipe(new RecipeMainspring(2250 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockIron"));
        GameRegistry.addRecipe(new RecipeMainspring(350 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotBrass"));
        GameRegistry.addRecipe(new RecipeMainspring(3150 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockBrass"));
        GameRegistry.addRecipe(new RecipeMainspring(2500 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotTemporal"));
        GameRegistry.addRecipe(new RecipeMainspring(100 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotGold"));
        GameRegistry.addRecipe(new RecipeMainspring(700 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotThaumium"));
        GameRegistry.addRecipe(new RecipeMainspring(750 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotSteel"));
        GameRegistry.addRecipe(new RecipeMainspring(6750 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockSteel"));
        GameRegistry.addRecipe(new RecipeMainspring(200 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotLead"));
        GameRegistry.addRecipe(new RecipeMainspring(1800 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockLead"));
        GameRegistry.addRecipe(new RecipeMainspring(100 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotCopper"));
        GameRegistry.addRecipe(new RecipeMainspring(900 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockCopper"));
        GameRegistry.addRecipe(new RecipeMainspring(100 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotTin"));
        GameRegistry.addRecipe(new RecipeMainspring(900 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockTin"));
        GameRegistry.addRecipe(new RecipeMainspring(300 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotBronze"));
        GameRegistry.addRecipe(new RecipeMainspring(2700 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockBronze"));
        GameRegistry.addRecipe(new RecipeMainspring(300 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotSilver"));
        GameRegistry.addRecipe(new RecipeMainspring(2700 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockSilver"));
        GameRegistry.addRecipe(new RecipeMainspring(500 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotCobalt"));
        GameRegistry.addRecipe(new RecipeMainspring(4500 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockCobalt"));
        GameRegistry.addRecipe(new RecipeMainspring(700 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotArdite"));
        GameRegistry.addRecipe(new RecipeMainspring(6300 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockArdite"));
        GameRegistry.addRecipe(new RecipeMainspring(1000 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotManyullyn"));
        GameRegistry.addRecipe(new RecipeMainspring(9000 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockManyullyn"));
        GameRegistry.addRecipe(new RecipeMainspring(200 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotAluminium"));
        GameRegistry.addRecipe(new RecipeMainspring(1800 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockAluminium"));
        GameRegistry.addRecipe(new RecipeMainspring(350 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotAluminiumBrass"));
        GameRegistry.addRecipe(new RecipeMainspring(3150 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockAluminiumBrass"));
        GameRegistry.addRecipe(new RecipeMainspring(500 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotAlumite"));
        GameRegistry.addRecipe(new RecipeMainspring(4500 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockAlumite"));
        GameRegistry.addRecipe(new RecipeMainspring(400 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotElectrum"));
        GameRegistry.addRecipe(new RecipeMainspring(3600 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockElectrum"));
        GameRegistry.addRecipe(new RecipeMainspring(300 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotNickel"));
        GameRegistry.addRecipe(new RecipeMainspring(2700 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockNickel"));
        GameRegistry.addRecipe(new RecipeMainspring(500 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "ingotInvar"));
        GameRegistry.addRecipe(new RecipeMainspring(4500 * multiplier, "iii", "imi", "iii", 'm', ModItems.mainspring, 'i', "blockInvar"));
    }

    public static void initSpecialRecipes()
    {
        //GameRegistry.addRecipe(new RecipeChronomancersHourglass());
        //GameRegistry.addRecipe(new RecipeClockworkPickaxe());
        //GameRegistry.addRecipe(new RecipeClockworkAxe());
        //GameRegistry.addRecipe(new RecipeClockworkShovel());
        GameRegistry.addRecipe(new RecipeConstructReassemble());
        GameRegistry.addRecipe(new RecipeClockwork());
        GameRegistry.addRecipe(new RecipeMultitoolAddition());
        GameRegistry.addRecipe(new RecipePocketWatchModule());
    }
}