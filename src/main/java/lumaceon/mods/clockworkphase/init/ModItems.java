package lumaceon.mods.clockworkphase.init;

import lumaceon.mods.clockworkphase.custom.IHasModel;
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
import net.minecraftforge.common.util.EnumHelper;
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
        ingotTemporal = toModel(new ItemTemporalIngot()).register(Names.TEMPORAL_INGOT);
        brassIngot = toModel(new ItemBrassIngot()).register(Names.BRASS_INGOT);
        nuggetTemporal = toModel(new ItemTemporalNugget()).register(Names.TEMPORAL_NUGGET);
        chipTemporal = toModel(new ItemTemporalChip()).register(Names.TEMPORAL_CHIP);

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
        gearBrass = toModel(new ItemGearBrass()).register(Names.GEAR_BRASS);
        gearBronze = toModel(new ItemGearBronze()).register(Names.GEAR_BRONZE);
        gearChronosphere = toModel(new ItemGearChronosphere()).register(Names.GEAR_CHRONOSPHERE);
        gearCopper = toModel(new ItemGearCopper()).register(Names.GEAR_COPPER);
        gearDiamond = toModel(new ItemGearDiamond()).register(Names.GEAR_DIAMOND);
        gearEmerald = toModel(new ItemGearEmerald()).register(Names.GEAR_EMERALD);
        gearIron = toModel(new ItemGearIron()).register(Names.GEAR_IRON);
        gearLead = toModel(new ItemGearLead()).register(Names.GEAR_LEAD);
        gearSilver = toModel(new ItemGearSilver()).register(Names.GEAR_SILVER);
        gearSteel = toModel(new ItemGearSteel()).register(Names.GEAR_STEEL);
        gearTemporal = toModel(new ItemGearTemporal()).register(Names.GEAR_TEMPORAL);
        gearThaumium = toModel(new ItemGearThaumium()).register(Names.GEAR_THAUMIUM);
        gearTin = toModel(new ItemGearTin()).register(Names.GEAR_TIN);
        gearRusty = toModel(new ItemRustyGear()).register(Names.GEAR_RUSTY);
        framework = toModel(new ItemFramework()).register(Names.FRAMEWORK);
        noteBottle = toModel(new ItemNoteBottle()).register(Names.NOTE_BOTTLE);
        preciousCharm = toModel(new ItemPreciousCharm()).register(Names.PRECIOUS_CHARM);
        oldCoin = toModel(new ItemOldCoin()).register(Names.OLD_COIN);

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
        clockworkPickaxe = toModel(new ItemClockworkPickaxe(clockworkMaterial)).register(Names.CLOCKWORK_PICKAXE);
        clockworkAxe = toModel(new ItemClockworkAxe(clockworkMaterial)).register(Names.CLOCKWORK_AXE);
        clockworkShovel = toModel(new ItemClockworkShovel(clockworkMaterial)).register(Names.CLOCKWORK_SHOVEL);
        clockworkSaber = toModel(new ItemClockworkSaber()).register(Names.CLOCKWORK_SABER);

        temporalClockworkPickaxe = toModel(new ItemTemporalClockworkPickaxe(clockworkMaterial)).register(Names.TEMPORAL_CLOCKWORK_PICKAXE);
        temporalClockworkAxe = toModel(new ItemTemporalClockworkAxe(clockworkMaterial)).register(Names.TEMPORAL_CLOCKWORK_AXE);
        temporalClockworkShovel = toModel(new ItemTemporalClockworkShovel(clockworkMaterial)).register(Names.TEMPORAL_CLOCKWORK_SHOVEL);
        temporalClockworkSaber = toModel(new ItemTemporalClockworkSaber()).register(Names.TEMPORAL_CLOCKWORK_SABER);
    }

    public static Item chronomancerHeadpiece;
    public static Item chronomancerChestplate;
    public static Item chronomancerLeggings;
    public static Item chronomancerBoots;
    public static void initClockworkArmor()
    {
        chronomancerHeadpiece = toModel(new ItemClockworkHeadpiece(ItemArmor.ArmorMaterial.DIAMOND, 0, 0)).register(Names.CLOCKWORK_HEADPIECE);
        chronomancerChestplate = toModel(new ItemClockworkChestpiece(ItemArmor.ArmorMaterial.DIAMOND, 0, 1)).register(Names.CLOCKWORK_CHESTPLATE);
        chronomancerLeggings = toModel(new ItemClockworkLeggings(ItemArmor.ArmorMaterial.DIAMOND, 0, 2)).register(Names.CLOCKWORK_LEGGINGS);
        chronomancerBoots = toModel(new ItemClockworkBoots(ItemArmor.ArmorMaterial.DIAMOND, 0, 3)).register(Names.CLOCKWORK_BOOTS);
    }

    public static Item hourglass;
    public static Item[] hourglassElements = new Item[8];
    public static void initHourglasses()
    {
        hourglass = toModel(new ItemHourglass()).register(Names.HOURGLASS);
        hourglassElements[0] = toModel(new ItemHourglassLife()).register(Names.HOURGLASS + "_life");
        hourglassElements[1] = toModel(new ItemHourglassLight()).register(Names.HOURGLASS + "_light");
        hourglassElements[2] = toModel(new ItemHourglassWater()).register(Names.HOURGLASS + "_water");
        hourglassElements[3] = toModel(new ItemHourglassEarth()).register(Names.HOURGLASS + "_earth");
        hourglassElements[4] = toModel(new ItemHourglassAir()).register(Names.HOURGLASS + "_air");
        hourglassElements[5] = toModel(new ItemHourglassFire()).register(Names.HOURGLASS + "_fire");
        hourglassElements[6] = toModel(new ItemHourglassLunar()).register(Names.HOURGLASS + "_lunar");
        hourglassElements[7] = toModel(new ItemHourglassDeath()).register(Names.HOURGLASS + "_death");
    }

    public static Item[] catalystElements = new Item[8];
    public static void initCatalysts()
    {
        catalystElements[0] = toModel(new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_NATURAL_SPAWN)).register(Names.CATALYST + "_life");
        catalystElements[1] = toModel(new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_LIGHT_SECOND)).register(Names.CATALYST + "_light");
        catalystElements[2] = toModel(new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_WATER_SECOND)).register(Names.CATALYST + "_water");
        catalystElements[3] = toModel(new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_TREE_EXTRACTION)).register(Names.CATALYST + "_earth");
        catalystElements[4] = toModel(new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_AIR_SECOND)).register(Names.CATALYST + "_air");
        catalystElements[5] = toModel(new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_ONE_FIRE_DAMAGE)).register(Names.CATALYST + "_fire");
        catalystElements[6] = toModel(new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_MOON_SECOND)).register(Names.CATALYST + "_lunar");
        catalystElements[7] = toModel(new ItemCatalyst(200000 / MechanicTweaker.TIME_SAND_FROM_DEATH)).register(Names.CATALYST + "_death");
    }

    public static Item pocketWatch;
    public static ItemModuleSilkTouch moduleSilkTouch;
    public static ItemModuleFurnace moduleFurnace;
    public static ItemModuleLifeWalk moduleLifeWalk;
    public static ItemModuleDeathWalk moduleDeathWalk;
    public static ItemModulePartialGravity modulePartialGravity;
    public static void initPocketWatchAndModules()
    {
        pocketWatch = toModel(new ItemPocketWatch()).register(Names.POCKET_WATCH);
        moduleSilkTouch = toModel(new ItemModuleSilkTouch()).register(Names.MODULE_SILK_TOUCH);
        moduleFurnace = toModel(new ItemModuleFurnace()).register(Names.MODULE_FURNACE);
        moduleLifeWalk = toModel(new ItemModuleLifeWalk()).register(Names.MODULE_LIFE_WALK);
        moduleDeathWalk = toModel(new ItemModuleDeathWalk()).register(Names.MODULE_DEATH_WALK);
        modulePartialGravity = toModel(new ItemModulePartialGravity()).register(Names.MODULE_PARTIAL_GRAVITY);
    }

    public static Item timeTuner;
    public static Item bugSwatter;
    public static Item temporalMultitool;
    public static void initTools()
    {
        timeTuner = toModel(new ItemTimeTuner()).register(Names.TIME_TUNER);
        bugSwatter = toModel(new ItemBugSwatter()).register(Names.BUG_SWATTER);
        temporalMultitool = toModel(new ItemTemporalMultitool()).register(Names.TEMPORAL_MULTITOOL);
    }

    public static Item mainspring;
    public static Item clockwork;
    public static Item timeSandBucket;
    public static Item blandHourglass;
    public static Item temporalCoreActive;
    public static Item temporalCoreSedate;
    public static void initMiscCraftingItems()
    {
        mainspring = toModel(new ItemMainspring()).register(Names.MAINSPRING);
        clockwork = toModel(new ItemClockwork()).register(Names.CLOCKWORK);
        timeSandBucket = toModel(new ItemTimeSandBucket(ModBlocks.timeSand).setContainerItem(Items.BUCKET)).register(Names.TIME_SAND_BUCKET);
        blandHourglass = toModel(new ItemBlandHourglass()).register(Names.BLAND_HOURGLASS);
        temporalCoreActive = toModel(new ItemActiveTemporalCore()).register(Names.ACTIVE_TEMPORAL_CORE);
        temporalCoreSedate = toModel(new ItemSedateTemporalCore()).register(Names.SEDATE_TEMPORAL_CORE);

//        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(ModFluids.timeSand.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(timeSandBucket), new ItemStack(Items.bucket));
    }

    private static IHasModel toModel(Item block) {
        return (IHasModel) block;
    }
}