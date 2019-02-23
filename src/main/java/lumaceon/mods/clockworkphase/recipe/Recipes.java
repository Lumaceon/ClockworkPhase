package lumaceon.mods.clockworkphase.recipe;

import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Reference;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes
{
    public static void init() // TODO 
    {
        initMachineRecipes();
        initPocketWatchAndModules();
        initToolRecipes();
        initMiscRecipes();
        initClockworkComponentRecipes();
        initMetalRecipes();
    }

    public static void initMachineRecipes()
    {
        ItemStack output;

        output = new ItemStack(ModBlocks.timeWell);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "bSb", "ATA", "bSb", 'b', "ingotBrass", 'S', ModItems.temporalCoreSedate, 'T', "ingotTemporal", 'A', ModItems.temporalCoreActive));

        output = new ItemStack(ModBlocks.extractorsElements[0]);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Items.GOLDEN_APPLE)); //Life
        output = new ItemStack(ModBlocks.extractorsElements[1]);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Blocks.TORCH)); //Light
        output = new ItemStack(ModBlocks.extractorsElements[2]);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Items.WATER_BUCKET)); //Water
        output = new ItemStack(ModBlocks.extractorsElements[3]);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', "stone")); //Earth
        output = new ItemStack(ModBlocks.extractorsElements[4]);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Items.FEATHER)); //Air
        output = new ItemStack(ModBlocks.extractorsElements[5]);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Items.FLINT_AND_STEEL)); //Fire
        output = new ItemStack(ModBlocks.extractorsElements[6]);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Items.GHAST_TEAR)); //Lunar
        output = new ItemStack(ModBlocks.extractorsElements[7]);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "btb", "aIa", "btb",
                'b', "ingotBrass", 't', "ingotTemporal", 'a', ModItems.temporalCoreActive, 'I', Items.BONE)); //Death
    }

    public static void initToolRecipes()
    {
        ItemStack output;
        ItemStack framework = new ItemStack(ModItems.framework);

        output = new ItemStack(ModItems.hourglass);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "bSb", "bhb", "bAb",
                'b', "ingotBrass", 'h', ModItems.blandHourglass, 'S', ModItems.temporalCoreSedate, 'A', ModItems.temporalCoreActive));

        output = new ItemStack(ModItems.clockworkPickaxe);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "bfb", " s ", " s ",
                'b', "ingotBrass", 'f', framework, 's', "stickWood"));

        output = new ItemStack(ModItems.clockworkAxe);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "bf ", "bs ", " s ",
                'b', "ingotBrass", 'f', framework, 's', "stickWood"));
        output = new ItemStack(ModItems.clockworkAxe);
        addRecipe(new ShapedOreRecipe(EMPTY, output, " fb", " sb", " s ",
                'b', "ingotBrass", 'f', framework, 's', "stickWood"));

        output = new ItemStack(ModItems.clockworkShovel);
        addRecipe(new ShapedOreRecipe(EMPTY, output, " f ", " s ", " s ",
                'f', new ItemStack(ModItems.framework), 's', "stickWood"));

        output = new ItemStack(ModItems.clockworkSaber);
        addRecipe(new ShapedOreRecipe(EMPTY, output, " b ", "bfb", "bsb",
                'b', "ingotBrass", 'f', framework, 's', "stickWood"));

        output = new ItemStack(ModItems.temporalMultitool);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "s a", " t ", "a s",
                's', ModItems.temporalCoreSedate, 'a', ModItems.temporalCoreActive, 't', "ingotTemporal"));

        output = new ItemStack(ModItems.chronomancerHeadpiece);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "bfb", "b b", "   ",
                'b', "ingotBrass", 'f', framework));

        output = new ItemStack(ModItems.chronomancerChestplate);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "b b", "bfb", "bbb",
                'b', "ingotBrass", 'f', framework));

        output = new ItemStack(ModItems.chronomancerLeggings);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "bfb", "b b", "b b",
                'b', "ingotBrass", 'f', framework));

        output = new ItemStack(ModItems.chronomancerBoots);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "   ", "b b", "bfb",
                'b', "ingotBrass", 'f', framework));

        output = new ItemStack(ModItems.timeTuner);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "b b", "btb", " b ",
                'b', "ingotBrass", 't', "ingotTemporal"));
    }

    public static void initPocketWatchAndModules()
    {
        ItemStack output;

        output = new ItemStack(ModItems.pocketWatch);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "bsb", "tct", "bab",
                'b', "ingotBrass", 's', ModItems.temporalCoreSedate, 'c', Items.CLOCK, 't', "ingotTemporal", 'a', ModItems.temporalCoreActive));

        output = new ItemStack(ModItems.moduleLifeWalk);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "gbg", "tSt", "sss",
                'g', Items.GOLDEN_APPLE, 'b', Items.DIAMOND_BOOTS, 't', "ingotTemporal", 'S', ModItems.temporalCoreSedate, 's', "stone"));

        output = new ItemStack(ModItems.moduleDeathWalk);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "bwb", "tSt", "sss",
                'b', Items.DIAMOND_BOOTS, 'w', Items.DIAMOND_SWORD, 't', "ingotTemporal", 'S', ModItems.temporalCoreSedate, 's', "stone"));

        output = new ItemStack(ModItems.moduleFurnace);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "flf", "tSt", "flf",
                'f', Blocks.FURNACE, 'l', Items.LAVA_BUCKET, 't', "ingotTemporal", 'S', ModItems.temporalCoreSedate));

        output = new ItemStack(ModItems.moduleSilkTouch);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "ses", "tSt", "sbs",
                's', Items.STRING, 'e', Blocks.ENCHANTING_TABLE, 't', "ingotTemporal", 'S', ModItems.temporalCoreSedate, 'b', Blocks.BOOKSHELF));

        output = new ItemStack(ModItems.modulePartialGravity);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "fwf", "tSt", "fwf",
                'f', Items.FEATHER, 'w', Blocks.WOOL, 't', "ingotTemporal", 'S', ModItems.temporalCoreSedate));
    }

    public static void initMiscRecipes()
    {
        ItemStack output;

        output = new ItemStack(ModBlocks.clockworkAssemblyTable);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "www", "wgw", "www", 'w', "plankWood", 'g', "gearIron"));

        output = new ItemStack(ModItems.temporalCoreActive);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "ttt", "rTr", "ttt",
                'T', "ingotTemporal", 't', "nuggetTemporal", 'r', "dustRedstone"));
        output = new ItemStack(ModItems.temporalCoreSedate);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "ttt", "sTs", "ttt",
                'T', "ingotTemporal", 't', "nuggetTemporal", 's', "stone"));

        output = new ItemStack(ModItems.blandHourglass);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "ggg", " g ", "ggg", 'g', "blockGlass"));
        output = new ItemStack(ModBlocks.disassembler);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "bb ", "b b", "bb ", 'b', "ingotBrass"));
        output = new ItemStack(ModBlocks.windingBox);
        addRecipe(new ShapedOreRecipe(EMPTY, output, " i ", "bib", "bbb", 'b', "ingotBrass", 'i', "ingotIron"));

        output = new ItemStack(ModBlocks.celestialCompass);
        addShapedRecipe(output, "dLl", "mCw", "fae",
                'd', Items.BONE, 'L', Items.GOLDEN_APPLE, 'l', Blocks.GLOWSTONE,
                'm', Items.GHAST_TEAR, 'C', Items.CLOCK, 'w', Items.WATER_BUCKET,
                'f', Items.FLINT_AND_STEEL, 'a', Items.FEATHER, 'e', Blocks.STONE);

        output = new ItemStack(ModItems.catalystElements[0]); //Life
        addRecipe(new ShapedOreRecipe(EMPTY, output, "w t", "wTt", "w t",
                'w', Items.WHEAT, 't', Blocks.TORCH, 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[1]); //Light
        addRecipe(new ShapedOreRecipe(EMPTY, output, "g w", "gTw", "g w",
                'g', Blocks.GLOWSTONE, 'w', Blocks.WOOL, 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[2]); //Water
        addRecipe(new ShapedOreRecipe(EMPTY, output, "w l", "wTl", "w l",
                'w', Items.WATER_BUCKET, 'l', Items.LAVA_BUCKET, 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[3]); //Earth
        addRecipe(new ShapedOreRecipe(EMPTY, output, "b a", "bTa", "b a",
                'b', Items.BONE, 'a', Items.STONE_AXE, 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[4]); //Air
        addRecipe(new ShapedOreRecipe(EMPTY, output, "f s", "fTs", "f s",
                'f', Items.FEATHER, 's', "stone", 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[5]); //Fire
        addRecipe(new ShapedOreRecipe(EMPTY, output, "f w", "fTw", "f w",
                'f', Items.FLINT_AND_STEEL, 'w', Items.WATER_BUCKET, 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[6]); //Lunar
        addRecipe(new ShapedOreRecipe(EMPTY, output, "g s", "gTs", "g s",
                'g', "blockGlass", 's', "stone", 'T', "ingotTemporal"));
        output = new ItemStack(ModItems.catalystElements[7]); //Death
        addRecipe(new ShapedOreRecipe(EMPTY, output, "s g", "sTg", "s g",
                's', Items.STONE_SWORD, 'g', Items.GOLDEN_APPLE, 'T', "ingotTemporal"));

        output = new ItemStack(ModItems.mainspring);
        output.setItemDamage(output.getMaxDamage());
        NBTHelper.setInteger(output, NBTTags.MAX_TENSION, 2000);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "mmm", "msm", "mmm", 'm', "ingotIron", 's', "stickWood"));
    }

    public static void initClockworkComponentRecipes()
    {
        ItemStack output;

        output = new ItemStack(ModItems.gearBrass);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "sis", "i i", "sis", 'i', "ingotBrass", 's', "stickWood"));
        output = new ItemStack(ModItems.gearBronze);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "sis", "i i", "sis", 'i', "ingotBronze", 's', "stickWood"));
        output = new ItemStack(ModItems.gearCopper);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "sis", "i i", "sis", 'i', "ingotCopper", 's', "stickWood"));
        output = new ItemStack(ModItems.gearDiamond);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "sis", "i i", "sis", 'i', "gemDiamond", 's', "stickWood"));
        output = new ItemStack(ModItems.gearEmerald);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "sis", "i i", "sis", 'i', "gemEmerald", 's', "stickWood"));
        output = new ItemStack(ModItems.gearIron);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "sis", "i i", "sis", 'i', "ingotIron", 's', "stickWood"));
        output = new ItemStack(ModItems.gearLead);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "sis", "i i", "sis", 'i', "ingotLead", 's', "stickWood"));
        output = new ItemStack(ModItems.gearSilver);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "sis", "i i", "sis", 'i', "ingotSilver", 's', "stickWood"));
        output = new ItemStack(ModItems.gearSteel);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "sis", "i i", "sis", 'i', "ingotSteel", 's', "stickWood"));
        output = new ItemStack(ModItems.gearTemporal);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "sis", "i i", "sis", 'i', "ingotTemporal", 's', "stickWood"));
        output = new ItemStack(ModItems.gearThaumium);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "sis", "i i", "sis", 'i', "ingotThaumium", 's', "stickWood"));
        output = new ItemStack(ModItems.gearTin);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "sis", "i i", "sis", 'i', "ingotTin", 's', "stickWood"));
        output = new ItemStack(ModItems.framework);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "bib", "ibi", "bib", 'b', "ingotBrass", 'i', "ingotIron"));
    }

    public static void initMetalRecipes()
    {
        ItemStack output;

        //Ingot to Block
        output = new ItemStack(ModBlocks.blockTemporal);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "iii", "iii", "iii", 'i', "ingotTemporal"));
        output = new ItemStack(ModBlocks.brassBlock);
        addShapedRecipe(output, "bbb", "bbb", "bbb", 'b', new ItemStack(ModItems.brassIngot));

        //Block to Ingot
        output = new ItemStack(ModItems.brassIngot); output.setCount(9);
        addRecipe(new ShapelessOreRecipe(EMPTY, output, "blockBrass"));
        output = new ItemStack(ModItems.ingotTemporal); output.setCount(9);
        addRecipe(new ShapelessOreRecipe(EMPTY, output, "blockTemporal"));

        //Nugget to Ingot
        output = new ItemStack(ModItems.ingotTemporal);
        addRecipe(new ShapedOreRecipe(EMPTY, output, "iii", "iii", "iii", 'i', "nuggetTemporal"));

        //Ingot to Nugget
        output = new ItemStack(ModItems.nuggetTemporal); output.setCount(9);
        addRecipe(new ShapelessOreRecipe(EMPTY, output, "ingotTemporal"));

        //Misc...
        ItemStack chip = new ItemStack(ModItems.chipTemporal);
        output = new ItemStack(ModItems.nuggetTemporal);
        NonNullList<Ingredient> lst = NonNullList.create();
        for (int i = 0; i < 4; i++)
            lst.add(Ingredient.fromStacks(chip));
        addRecipe(new ShapelessRecipes("", output, lst));
        output = new ItemStack(ModItems.brassIngot); output.setCount(3);
        addRecipe(new ShapelessOreRecipe(EMPTY, output, "ingotIron", "ingotIron", "ingotIron", "ingotGold"));
    }

    private static final ResourceLocation EMPTY = new ResourceLocation("");

    public static void addShapedRecipe(ItemStack output, Object... params) {
        GameRegistry.addShapedRecipe(new ResourceLocation(Reference.MOD_ID, getName(output.getItem())), null, output, params);
    }

    public static void addRecipe(IRecipe recipe) {
        ForgeRegistries.RECIPES.register(recipe.setRegistryName(new ResourceLocation(Reference.MOD_ID, getName(recipe.getRecipeOutput().getItem()))));
    }

    private static TIntSet usedHashes = new TIntHashSet();

    private static String getName(Item item) {
        int hash = item.getRegistryName().hashCode();
        while (usedHashes.contains(hash))
            ++hash;
        usedHashes.add(hash);
        return "clockwork_" + hash;
    }
}