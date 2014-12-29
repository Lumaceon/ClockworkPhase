package lumaceon.mods.clockworkphase.init;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.item.*;
import lumaceon.mods.clockworkphase.item.component.*;
import lumaceon.mods.clockworkphase.item.component.base.*;
import lumaceon.mods.clockworkphase.item.component.base.memory.*;
import lumaceon.mods.clockworkphase.item.construct.clockworkarmor.ItemClockworkBoots;
import lumaceon.mods.clockworkphase.item.construct.clockworkarmor.ItemClockworkHeadpiece;
import lumaceon.mods.clockworkphase.item.construct.clockworkarmor.ItemClockworkChestpiece;
import lumaceon.mods.clockworkphase.item.construct.clockworkarmor.ItemClockworkLeggings;
import lumaceon.mods.clockworkphase.item.construct.clockwork.ItemClockworkSaber;
import lumaceon.mods.clockworkphase.item.construct.clockwork.ItemTemporalClockworkSaber;
import lumaceon.mods.clockworkphase.item.construct.clockwork.tool.*;
import lumaceon.mods.clockworkphase.item.construct.hourglass.*;
import lumaceon.mods.clockworkphase.item.ItemTemporalIngot;
import lumaceon.mods.clockworkphase.item.construct.pocketwatch.ItemPocketWatch;
import lumaceon.mods.clockworkphase.item.construct.pocketwatch.module.*;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.Names;
import lumaceon.mods.clockworkphase.registry.MemoryItemRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems
{
    public static Item.ToolMaterial clockworkMaterial = EnumHelper.addToolMaterial("clockwork", 3, 10, 20.0F, 0.0F, 0);

    public static void init()
    {
        initMaterials();
        initClockworkComponents();
        initBasicClockworkTools();
        initClockworkArmor();
        initHourglasses();
        initCatalysts();
        initPocketWatchAndModules();
        initTools();
        initMiscCraftingItems();
    }

    public static Item ingotTemporal;
    public static Item brassIngot;
    public static Item nuggetTemporal;
    public static Item chipTemporal;
    public static void initMaterials()
    {
        ingotTemporal = new ItemTemporalIngot().setUnlocalizedName(Names.TEMPORAL_INGOT);
        GameRegistry.registerItem(ingotTemporal, Names.TEMPORAL_INGOT);
        brassIngot = new ItemBrassIngot().setUnlocalizedName(Names.BRASS_INGOT);
        GameRegistry.registerItem(brassIngot, Names.BRASS_INGOT);
        nuggetTemporal = new ItemTemporalNugget().setUnlocalizedName(Names.TEMPORAL_NUGGET);
        GameRegistry.registerItem(nuggetTemporal, Names.TEMPORAL_NUGGET);
        chipTemporal = new ItemTemporalChip().setUnlocalizedName(Names.TEMPORAL_CHIP);
        GameRegistry.registerItem(chipTemporal, Names.TEMPORAL_CHIP);

        OreDictionary.registerOre("ingotBrass", brassIngot);
        OreDictionary.registerOre("ingotTemporal", ingotTemporal);
        OreDictionary.registerOre("nuggetTemporal", nuggetTemporal);
    }

    public static Item gearBrass;
    public static Item gearBronze;
    public static Item gearChronosphere;
    public static Item gearCopper;
    public static Item gearDiamond;
    public static Item gearEmerald;
    public static Item gearIron;
    public static Item gearLead;
    public static Item gearSilver;
    public static Item gearSteel;
    public static Item gearTemporal;
    public static Item gearThaumium;
    public static Item gearTin;
    public static Item gearRusty;
    public static Item framework;
    public static Item noteBottle;
    public static Item preciousCharm;
    public static Item oldCoin;
    public static void initClockworkComponents()
    {
        gearBrass = new ItemGearBrass().setUnlocalizedName(Names.GEAR_BRASS);
        GameRegistry.registerItem(gearBrass, Names.GEAR_BRASS);
        gearBronze = new ItemGearBronze().setUnlocalizedName(Names.GEAR_BRONZE);
        GameRegistry.registerItem(gearBronze, Names.GEAR_BRONZE);
        gearChronosphere = new ItemGearChronosphere().setUnlocalizedName(Names.GEAR_CHRONOSPHERE);
        GameRegistry.registerItem(gearChronosphere, Names.GEAR_CHRONOSPHERE);
        gearCopper = new ItemGearCopper().setUnlocalizedName(Names.GEAR_COPPER);
        GameRegistry.registerItem(gearCopper, Names.GEAR_COPPER);
        gearDiamond = new ItemGearDiamond().setUnlocalizedName(Names.GEAR_DIAMOND);
        GameRegistry.registerItem(gearDiamond, Names.GEAR_DIAMOND);
        gearEmerald = new ItemGearEmerald().setUnlocalizedName(Names.GEAR_EMERALD);
        GameRegistry.registerItem(gearEmerald, Names.GEAR_EMERALD);
        gearIron = new ItemGearIron().setUnlocalizedName(Names.GEAR_IRON);
        GameRegistry.registerItem(gearIron, Names.GEAR_IRON);
        gearLead = new ItemGearLead().setUnlocalizedName(Names.GEAR_LEAD);
        GameRegistry.registerItem(gearLead, Names.GEAR_LEAD);
        gearSilver = new ItemGearSilver().setUnlocalizedName(Names.GEAR_SILVER);
        GameRegistry.registerItem(gearSilver, Names.GEAR_SILVER);
        gearSteel = new ItemGearSteel().setUnlocalizedName(Names.GEAR_STEEL);
        GameRegistry.registerItem(gearSteel, Names.GEAR_STEEL);
        gearTemporal = new ItemGearTemporal().setUnlocalizedName(Names.GEAR_TEMPORAL);
        GameRegistry.registerItem(gearTemporal, Names.GEAR_TEMPORAL);
        gearThaumium = new ItemGearThaumium().setUnlocalizedName(Names.GEAR_THAUMIUM);
        GameRegistry.registerItem(gearThaumium, Names.GEAR_THAUMIUM);
        gearTin = new ItemGearTin().setUnlocalizedName(Names.GEAR_TIN);
        GameRegistry.registerItem(gearTin, Names.GEAR_TIN);
        gearRusty = new ItemRustyGear().setUnlocalizedName(Names.GEAR_RUSTY);
        GameRegistry.registerItem(gearRusty, Names.GEAR_RUSTY);
        framework = new ItemFramework().setUnlocalizedName(Names.FRAMEWORK);
        GameRegistry.registerItem(framework, Names.FRAMEWORK);
        noteBottle = new ItemNoteBottle().setUnlocalizedName(Names.NOTE_BOTTLE);
        GameRegistry.registerItem(noteBottle, Names.NOTE_BOTTLE);
        preciousCharm = new ItemPreciousCharm().setUnlocalizedName(Names.PRECIOUS_CHARM);
        GameRegistry.registerItem(preciousCharm, Names.PRECIOUS_CHARM);
        oldCoin = new ItemOldCoin().setUnlocalizedName(Names.OLD_COIN);
        GameRegistry.registerItem(oldCoin, Names.OLD_COIN);

        OreDictionary.registerOre("gearBrass", gearBrass);
        OreDictionary.registerOre("gearBronze", gearBronze);
        OreDictionary.registerOre("gearCopper", gearCopper);
        OreDictionary.registerOre("gearDiamond", gearDiamond);
        OreDictionary.registerOre("gearEmerald", gearEmerald);
        OreDictionary.registerOre("gearIron", gearIron);
        OreDictionary.registerOre("gearLead", gearLead);
        OreDictionary.registerOre("gearSilver", gearSilver);
        OreDictionary.registerOre("gearSteel", gearSteel);
        OreDictionary.registerOre("gearTemporal", gearTemporal);
        OreDictionary.registerOre("gearThaumium", gearThaumium);
        OreDictionary.registerOre("gearTin", gearTin);

        MemoryItemRegistry.memoryItemDrops.add(gearRusty);
        MemoryItemRegistry.memoryItemDrops.add(noteBottle);
        MemoryItemRegistry.memoryItemDrops.add(preciousCharm);
        MemoryItemRegistry.memoryItemDrops.add(oldCoin);
    }

    public static Item clockworkPickaxe;
    public static Item clockworkAxe;
    public static Item clockworkShovel;
    public static Item clockworkSaber;
    public static Item temporalClockworkPickaxe;
    public static Item temporalClockworkAxe;
    public static Item temporalClockworkShovel;
    public static Item temporalClockworkSaber;
    public static void initBasicClockworkTools()
    {
        clockworkPickaxe = new ItemClockworkPickaxe(clockworkMaterial).setUnlocalizedName(Names.CLOCKWORK_PICKAXE);
        GameRegistry.registerItem(clockworkPickaxe, Names.CLOCKWORK_PICKAXE);
        clockworkAxe = new ItemClockworkAxe(clockworkMaterial).setUnlocalizedName(Names.CLOCKWORK_AXE);
        GameRegistry.registerItem(clockworkAxe, Names.CLOCKWORK_AXE);
        clockworkShovel = new ItemClockworkShovel(clockworkMaterial).setUnlocalizedName(Names.CLOCKWORK_SHOVEL);
        GameRegistry.registerItem(clockworkShovel, Names.CLOCKWORK_SHOVEL);
        clockworkSaber = new ItemClockworkSaber().setUnlocalizedName(Names.CLOCKWORK_SABER);
        GameRegistry.registerItem(clockworkSaber, Names.CLOCKWORK_SABER);

        temporalClockworkPickaxe = new ItemTemporalClockworkPickaxe(clockworkMaterial).setUnlocalizedName(Names.TEMPORAL_CLOCKWORK_PICKAXE);
        GameRegistry.registerItem(temporalClockworkPickaxe, Names.TEMPORAL_CLOCKWORK_PICKAXE);
        temporalClockworkAxe = new ItemTemporalClockworkAxe(clockworkMaterial).setUnlocalizedName(Names.TEMPORAL_CLOCKWORK_AXE);
        GameRegistry.registerItem(temporalClockworkAxe, Names.TEMPORAL_CLOCKWORK_AXE);
        temporalClockworkShovel = new ItemTemporalClockworkShovel(clockworkMaterial).setUnlocalizedName(Names.TEMPORAL_CLOCKWORK_SHOVEL);
        GameRegistry.registerItem(temporalClockworkShovel, Names.TEMPORAL_CLOCKWORK_SHOVEL);
        temporalClockworkSaber = new ItemTemporalClockworkSaber().setUnlocalizedName(Names.TEMPORAL_CLOCKWORK_SABER);
        GameRegistry.registerItem(temporalClockworkSaber, Names.TEMPORAL_CLOCKWORK_SABER);
    }

    public static Item chronomancerHeadpiece;
    public static Item chronomancerChestplate;
    public static Item chronomancerLeggings;
    public static Item chronomancerBoots;
    public static void initClockworkArmor()
    {
        chronomancerHeadpiece = new ItemClockworkHeadpiece(ItemArmor.ArmorMaterial.DIAMOND, 0, 0).setUnlocalizedName(Names.CLOCKWORK_HEADPIECE);
        GameRegistry.registerItem(chronomancerHeadpiece, Names.CLOCKWORK_HEADPIECE);
        chronomancerChestplate = new ItemClockworkChestpiece(ItemArmor.ArmorMaterial.DIAMOND, 0, 1).setUnlocalizedName(Names.CLOCKWORK_CHESTPLATE);
        GameRegistry.registerItem(chronomancerChestplate, Names.CLOCKWORK_CHESTPLATE);
        chronomancerLeggings = new ItemClockworkLeggings(ItemArmor.ArmorMaterial.DIAMOND, 0, 2).setUnlocalizedName(Names.CLOCKWORK_LEGGINGS);
        GameRegistry.registerItem(chronomancerLeggings, Names.CLOCKWORK_LEGGINGS);
        chronomancerBoots = new ItemClockworkBoots(ItemArmor.ArmorMaterial.DIAMOND, 0, 3).setUnlocalizedName(Names.CLOCKWORK_BOOTS);
        GameRegistry.registerItem(chronomancerBoots, Names.CLOCKWORK_BOOTS);
    }

    public static Item hourglass;
    public static Item[] hourglassElements = new Item[8];
    public static void initHourglasses()
    {
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
    }

    public static Item[] catalystElements = new Item[8];
    public static void initCatalysts()
    {
        catalystElements[0] = new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_NATURAL_SPAWN).setUnlocalizedName(Names.CATALYST + "Life");
        GameRegistry.registerItem(catalystElements[0], Names.CATALYST + "Life");
        catalystElements[1] = new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_LIGHT_SECOND).setUnlocalizedName(Names.CATALYST + "Light");
        GameRegistry.registerItem(catalystElements[1], Names.CATALYST + "Light");
        catalystElements[2] = new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_WATER_SECOND).setUnlocalizedName(Names.CATALYST + "Water");
        GameRegistry.registerItem(catalystElements[2], Names.CATALYST + "Water");
        catalystElements[3] = new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_TREE_EXTRACTION).setUnlocalizedName(Names.CATALYST + "Earth");
        GameRegistry.registerItem(catalystElements[3], Names.CATALYST + "Earth");
        catalystElements[4] = new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_AIR_SECOND).setUnlocalizedName(Names.CATALYST + "Air");
        GameRegistry.registerItem(catalystElements[4], Names.CATALYST + "Air");
        catalystElements[5] = new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_ONE_FIRE_DAMAGE).setUnlocalizedName(Names.CATALYST + "Fire");
        GameRegistry.registerItem(catalystElements[5], Names.CATALYST + "Fire");
        catalystElements[6] = new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_MOON_SECOND).setUnlocalizedName(Names.CATALYST + "Lunar");
        GameRegistry.registerItem(catalystElements[6], Names.CATALYST + "Lunar");
        catalystElements[7] = new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_DEATH).setUnlocalizedName(Names.CATALYST + "Death");
        GameRegistry.registerItem(catalystElements[7], Names.CATALYST + "Death");
    }

    public static Item pocketWatch;
    public static ItemModuleSilkTouch moduleSilkTouch;
    public static ItemModuleFurnace moduleFurnace;
    public static ItemModuleLifeWalk moduleLifeWalk;
    public static ItemModuleDeathWalk moduleDeathWalk;
    public static ItemModulePartialGravity modulePartialGravity;
    public static void initPocketWatchAndModules()
    {
        pocketWatch = new ItemPocketWatch().setUnlocalizedName(Names.POCKET_WATCH);
        GameRegistry.registerItem(pocketWatch, Names.POCKET_WATCH);
        moduleSilkTouch = (ItemModuleSilkTouch)new ItemModuleSilkTouch().setUnlocalizedName(Names.MODULE_SILK_TOUCH);
        GameRegistry.registerItem(moduleSilkTouch, Names.MODULE_SILK_TOUCH);
        moduleFurnace = (ItemModuleFurnace)new ItemModuleFurnace().setUnlocalizedName(Names.MODULE_FURNACE);
        GameRegistry.registerItem(moduleFurnace, Names.MODULE_FURNACE);
        moduleLifeWalk = (ItemModuleLifeWalk)new ItemModuleLifeWalk().setUnlocalizedName(Names.MODULE_LIFE_WALK);
        GameRegistry.registerItem(moduleLifeWalk, Names.MODULE_LIFE_WALK);
        moduleDeathWalk = (ItemModuleDeathWalk)new ItemModuleDeathWalk().setUnlocalizedName(Names.MODULE_DEATH_WALK);
        GameRegistry.registerItem(moduleDeathWalk, Names.MODULE_DEATH_WALK);
        modulePartialGravity = (ItemModulePartialGravity)new ItemModulePartialGravity().setUnlocalizedName(Names.MODULE_PARTIAL_GRAVITY);
        GameRegistry.registerItem(modulePartialGravity, Names.MODULE_PARTIAL_GRAVITY);
    }

    public static Item timeTuner;
    public static Item bugSwatter;
    public static Item temporalMultitool;
    public static void initTools()
    {
        timeTuner = new ItemTimeTuner().setUnlocalizedName(Names.TIME_TUNER);
        GameRegistry.registerItem(timeTuner, Names.TIME_TUNER);
        bugSwatter = new ItemBugSwatter().setUnlocalizedName(Names.BUG_SWATTER);
        GameRegistry.registerItem(bugSwatter, Names.BUG_SWATTER);
        temporalMultitool = new ItemTemporalMultitool().setUnlocalizedName(Names.TEMPORAL_MULTITOOL);
        GameRegistry.registerItem(temporalMultitool, Names.TEMPORAL_MULTITOOL);
    }

    public static Item mainspring;
    public static Item clockwork;
    public static Item timeSandBucket;
    public static Item blandHourglass;
    public static Item temporalCoreActive;
    public static Item temporalCoreSedate;
    public static void initMiscCraftingItems()
    {
        mainspring = new ItemMainspring().setUnlocalizedName(Names.MAINSPRING);
        GameRegistry.registerItem(mainspring, Names.MAINSPRING);
        clockwork = new ItemClockwork().setUnlocalizedName(Names.CLOCKWORK);
        GameRegistry.registerItem(clockwork, Names.CLOCKWORK);
        timeSandBucket = new ItemTimeSandBucket(ModBlocks.timeSand).setUnlocalizedName(Names.TIME_SAND_BUCKET).setContainerItem(Items.bucket);
        GameRegistry.registerItem(timeSandBucket, Names.TIME_SAND_BUCKET);
        blandHourglass = new ItemBlandHourglass().setUnlocalizedName(Names.BLAND_HOURGLASS);
        GameRegistry.registerItem(blandHourglass, Names.BLAND_HOURGLASS);
        temporalCoreActive = new ItemActiveTemporalCore().setUnlocalizedName(Names.ACTIVE_TEMPORAL_CORE);
        GameRegistry.registerItem(temporalCoreActive, Names.ACTIVE_TEMPORAL_CORE);
        temporalCoreSedate = new ItemSedateTemporalCore().setUnlocalizedName(Names.SEDATE_TEMPORAL_CORE);
        GameRegistry.registerItem(temporalCoreSedate, Names.SEDATE_TEMPORAL_CORE);

        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(ModFluids.timeSand.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(timeSandBucket), new ItemStack(Items.bucket));
    }
}