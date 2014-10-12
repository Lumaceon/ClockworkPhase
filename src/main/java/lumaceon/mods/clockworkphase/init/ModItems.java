package lumaceon.mods.clockworkphase.init;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.item.*;
import lumaceon.mods.clockworkphase.item.component.*;
import lumaceon.mods.clockworkphase.item.construct.clockwork.tool.ItemClockworkAxe;
import lumaceon.mods.clockworkphase.item.construct.clockwork.tool.ItemClockworkPickaxe;
import lumaceon.mods.clockworkphase.item.construct.clockwork.tool.ItemClockworkShovel;
import lumaceon.mods.clockworkphase.item.construct.mix.hourglass.*;
import lumaceon.mods.clockworkphase.lib.Names;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems
{
    public static Item.ToolMaterial clockworkMaterial = EnumHelper.addToolMaterial("clockwork", 3, 10, 20.0F, 0.0F, 8);

    public static Item bugSwatter;
    public static Item brassIngot;
    public static Item mainspring;
    public static Item clockwork;
    public static Item timeSandBucket;
    public static Item blandHourglass;

    public static Item gearIron;
    public static Item gearBrass;
    public static Item gearBronze;
    public static Item gearSteel;
    public static Item gearDiamond;
    public static Item gearEmerald;
    public static Item oilBucket;
    public static Item framework;
    public static Item memoryCore;

    public static Item hourglass;
    public static Item[] hourglassElements = new Item[8];

    public static Item clockworkPickaxe;
    public static Item clockworkAxe;
    public static Item clockworkShovel;

    public static void init()
    {
        bugSwatter = new ItemBugSwatter().setUnlocalizedName(Names.BUG_SWATTER);
        GameRegistry.registerItem(bugSwatter, Names.BUG_SWATTER);

        brassIngot = new ItemBrassIngot().setUnlocalizedName(Names.BRASS_INGOT);
        GameRegistry.registerItem(brassIngot, Names.BRASS_INGOT);
        OreDictionary.registerOre("ingotBrass", brassIngot);

        mainspring = new ItemMainspring().setUnlocalizedName(Names.MAINSPRING);
        GameRegistry.registerItem(mainspring, Names.MAINSPRING);

        clockwork = new ItemClockwork().setUnlocalizedName(Names.CLOCKWORK);
        GameRegistry.registerItem(clockwork, Names.CLOCKWORK);

        timeSandBucket = new ItemTimeSandBucket(ModBlocks.timeSand).setUnlocalizedName(Names.TIME_SAND_BUCKET).setContainerItem(Items.bucket);
        GameRegistry.registerItem(timeSandBucket, Names.TIME_SAND_BUCKET);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(ModFluids.timeSand.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(timeSandBucket), new ItemStack(Items.bucket));

        blandHourglass = new ItemBlandHourglass().setUnlocalizedName(Names.BLAND_HOURGLASS);
        GameRegistry.registerItem(blandHourglass, Names.BLAND_HOURGLASS);

        gearIron = new ItemGearIron().setUnlocalizedName(Names.GEAR_IRON);
        GameRegistry.registerItem(gearIron, Names.GEAR_IRON);
        OreDictionary.registerOre("gearIron", brassIngot);

        gearBrass = new ItemGearBrass().setUnlocalizedName(Names.GEAR_BRASS);
        GameRegistry.registerItem(gearBrass, Names.GEAR_BRASS);
        OreDictionary.registerOre("gearBrass", brassIngot);

        gearBronze = new ItemGearBronze().setUnlocalizedName(Names.GEAR_BRONZE);
        GameRegistry.registerItem(gearBronze, Names.GEAR_BRONZE);
        OreDictionary.registerOre("gearBronze", brassIngot);

        gearSteel = new ItemGearSteel().setUnlocalizedName(Names.GEAR_STEEL);
        GameRegistry.registerItem(gearSteel, Names.GEAR_STEEL);
        OreDictionary.registerOre("gearSteel", brassIngot);

        gearDiamond = new ItemGearDiamond().setUnlocalizedName(Names.GEAR_DIAMOND);
        GameRegistry.registerItem(gearDiamond, Names.GEAR_DIAMOND);
        OreDictionary.registerOre("gearDiamond", brassIngot);

        gearEmerald = new ItemGearEmerald().setUnlocalizedName(Names.GEAR_EMERALD);
        GameRegistry.registerItem(gearEmerald, Names.GEAR_EMERALD);
        OreDictionary.registerOre("gearEmerald", brassIngot);

        oilBucket = new ItemOilBucket().setUnlocalizedName(Names.OIL_BUCKET);
        GameRegistry.registerItem(oilBucket, Names.OIL_BUCKET);

        framework = new ItemFramework().setUnlocalizedName(Names.FRAMEWORK);
        GameRegistry.registerItem(framework, Names.FRAMEWORK);

        memoryCore = new ItemMemoryCore().setUnlocalizedName(Names.MEMORY_CORE);
        GameRegistry.registerItem(memoryCore, Names.MEMORY_CORE);


        /** Chronomancer's Hourglass **/
        hourglass = new ItemHourglass().setUnlocalizedName(Names.HOURGLASS);
        GameRegistry.registerItem(hourglass, Names.HOURGLASS);

        hourglassElements[0] = new ItemHourglassLife().setUnlocalizedName(Names.HOURGLASS + "Life");
        GameRegistry.registerItem(hourglassElements[0], Names.HOURGLASS + "Life");

        hourglassElements[1] = new ItemHourglassLight().setUnlocalizedName(Names.HOURGLASS + "Light");
        GameRegistry.registerItem(hourglassElements[1], Names.HOURGLASS + "Light");

        hourglassElements[2] = new ItemHourglassWater().setUnlocalizedName(Names.HOURGLASS + "Water");
        GameRegistry.registerItem(hourglassElements[2], Names.HOURGLASS + "Water");

        hourglassElements[3] = new ItemHourglassEarth().setUnlocalizedName(Names.HOURGLASS + "Earth");
        GameRegistry.registerItem(hourglassElements[3], Names.HOURGLASS + "Earth");

        hourglassElements[4] = new ItemHourglassAir().setUnlocalizedName(Names.HOURGLASS + "Air");
        GameRegistry.registerItem(hourglassElements[4], Names.HOURGLASS + "Air");

        hourglassElements[5] = new ItemHourglassFire().setUnlocalizedName(Names.HOURGLASS + "Fire");
        GameRegistry.registerItem(hourglassElements[5], Names.HOURGLASS + "Fire");

        hourglassElements[6] = new ItemHourglassLunar().setUnlocalizedName(Names.HOURGLASS + "Lunar");
        GameRegistry.registerItem(hourglassElements[6], Names.HOURGLASS + "Lunar");

        hourglassElements[7] = new ItemHourglassDeath().setUnlocalizedName(Names.HOURGLASS + "Death");
        GameRegistry.registerItem(hourglassElements[7], Names.HOURGLASS + "Death");
        //END HOURGLASS\\


        clockworkPickaxe = new ItemClockworkPickaxe(clockworkMaterial).setUnlocalizedName(Names.CLOCKWORK_PICKAXE);
        GameRegistry.registerItem(clockworkPickaxe, Names.CLOCKWORK_PICKAXE);

        clockworkAxe = new ItemClockworkAxe(clockworkMaterial).setUnlocalizedName(Names.CLOCKWORK_AXE);
        GameRegistry.registerItem(clockworkAxe, Names.CLOCKWORK_AXE);

        clockworkShovel = new ItemClockworkShovel(clockworkMaterial).setUnlocalizedName(Names.CLOCKWORK_SHOVEL);
        GameRegistry.registerItem(clockworkShovel, Names.CLOCKWORK_SHOVEL);
    }
}