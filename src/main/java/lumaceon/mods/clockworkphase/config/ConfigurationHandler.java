package lumaceon.mods.clockworkphase.config;

import lumaceon.mods.clockworkphase.lib.GlobalPhaseReference;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{
    public static void init(File configurationFile)
    {
        Configuration config = new Configuration(configurationFile);
        String CATEGORY_HOURGLASS = "hourglasses";
        String CATEGORY_TENSION = "generalTension";
        String CATEGORY_EXTRACTORS = "extractors";
        String CATEGORY_MODULES = "pocketWatchModules";
        String CATEGORY_TOOLS = "toolTimeSandIncrements";

        try
        {
            config.load();

            //Add each configuration value here
            //MechanicTweaker.PHASE_EVENTS = config.get(Configuration.CATEGORY_GENERAL, "EnablePhaseEvents", false, "Enables or disables world-wide events that occur occasionally based on phases.").getBoolean(false);
            GlobalPhaseReference.phaseDuration = config.get(Configuration.CATEGORY_GENERAL, "PhaseDuration", 24000, "The duration of each phase in ticks. 1 Minecraft day = 24,000.").getInt(24000);

            MechanicTweaker.MAX_TENSION = config.get(CATEGORY_TENSION, "MainspringMaxTension", 1000000, "The maximum amount of tension a mainspring can hold").getInt(1000000);
            MechanicTweaker.TENSION_PER_BLOCK_BREAK = config.get(CATEGORY_TENSION, "TensionPerBlockBroken", 250, "The base (1:1 speed/quality) tension cost for each block broken with clockwork tools.").getInt(250);
            MechanicTweaker.TENSION_PER_HIT = config.get(CATEGORY_TENSION, "TensionPerEntityHit", 500, "The base (1:1 speed/quality) tension cost for each entity attacked with the clockwork sword.").getInt(250);
            MechanicTweaker.CLOCKWORK_ARMOR_TENSION_COST = config.get(CATEGORY_TENSION, "CWArmorBaseTensionCost", 250, "The base (1:1 speed/quality) tension cost for each piece of clockwork armor on hit.").getInt(250);

            MechanicTweaker.LIFE_HOURGLASS_TENSION_COST = config.get(CATEGORY_HOURGLASS, "LifeHourglassTensionCost", 500, "The base (1:1 speed/quality) tension cost for each tick the hourglass is active.").getInt(500);
            MechanicTweaker.LIGHT_HOURGLASS_TENSION_COST = config.get(CATEGORY_HOURGLASS, "LightHourglassTensionCost", 500, "The base (1:1 speed/quality) tension cost for each light the hourglass places.").getInt(500);
            MechanicTweaker.WATER_HOURGLASS_TENSION_COST = config.get(CATEGORY_HOURGLASS, "WaterHourglassTensionCost", 80, "The base (1:1 speed/quality) tension cost for each tick the hourglass is active.").getInt(80);
            MechanicTweaker.EARTH_HOURGLASS_TENSION_COST = config.get(CATEGORY_HOURGLASS, "EarthHourglassTensionCost", 20, "The base (1:1 speed/quality) tension cost for each tick the hourglass is active.").getInt(20);
            MechanicTweaker.AIR_HOURGLASS_TENSION_COST = config.get(CATEGORY_HOURGLASS, "AirHourglassTensionCost", 50, "The base (1:1 speed/quality) tension cost for each tick the hourglass is active.").getInt(50);
            MechanicTweaker.FIRE_HOURGLASS_TENSION_COST = config.get(CATEGORY_HOURGLASS, "FireHourglassTensionCost", 120, "The base (1:1 speed/quality) tension cost for each tick the hourglass is active.").getInt(120);
            MechanicTweaker.LUNAR_HOURGLASS_TENSION_COST = config.get(CATEGORY_HOURGLASS, "LunarHourglassTensionCost", 50, "The base (1:1 speed/quality) tension cost for each tick the hourglass is active.").getInt(50);
            MechanicTweaker.DEATH_HOURGLASS_TENSION_COST = config.get(CATEGORY_HOURGLASS, "DeathHourglassTensionCost", 250, "The base (1:1 speed/quality) tension cost for each tick the hourglass is active.").getInt(250);

            MechanicTweaker.TIME_SAND_COST_SILK_MODULE = config.get(CATEGORY_MODULES, "SilkTouchModuleCost", 25, "The time sand cost for each block silk-touched.").getInt(25);
            MechanicTweaker.TIME_SAND_COST_FURNACE_MODULE = config.get(CATEGORY_MODULES, "FurnaceModuleCost", 25, "The time sand cost for each block auto-smelted.").getInt(25);
            MechanicTweaker.TIME_SAND_COST_LIFE_WALK_MODULE = config.get(CATEGORY_MODULES, "LifeWalkModuleCost", 3, "The time sand cost for each 'Life energy' which, by default, is 1% of a full heart (2hp).").getInt(3);
            MechanicTweaker.TIME_SAND_COST_PART_GRAVITY_MODULE = config.get(CATEGORY_MODULES, "PartialGravityModuleCost", 2, "The time sand cost for each tick of partial gravity.").getInt(2);
            MechanicTweaker.TIME_SAND_COST_DEATH_WALK_MODULE = config.get(CATEGORY_MODULES, "DeathWalkModuleCost", 10, "The time sand cost for each 'Death energy' which, by default, is 1% of a full heart (2hp).").getInt(10);

            MechanicTweaker.PICKAXE_TIME_SAND_INCREMENT = config.get(CATEGORY_TOOLS, "PickaxeTimeSandIncrement", 25, "The amount of time sand the pickaxe extracts from a valid broken block on a successful roll.").getInt(25);
            MechanicTweaker.AXE_TIME_SAND_INCREMENT = config.get(CATEGORY_TOOLS, "AxeTimeSandIncrement", 100, "The amount of time sand the axe extracts from a valid broken block on a successful roll.").getInt(100);
            MechanicTweaker.SHOVEL_TIME_SAND_INCREMENT = config.get(CATEGORY_TOOLS, "ShovelTimeSandIncrement", 20, "The amount of time sand the shovel extracts from a valid broken block on a successful roll.").getInt(20);
            MechanicTweaker.SABER_TIME_SAND_INCREMENT_KILL = config.get(CATEGORY_TOOLS, "SwordTimeSandIncrement", 500, "The amount of time sand the sword extracts from a slain entity on a successful roll.").getInt(500);

            MechanicTweaker.TIME_SAND_FROM_NATURAL_SPAWN = config.get(CATEGORY_EXTRACTORS, "ExtractorLifeTimeSandFromSpawn", 5000, "The amount of time sand extracted from a nearby natural spawn.").getInt(5000);
            MechanicTweaker.TIME_SAND_FROM_LIGHT_SECOND = config.get(CATEGORY_EXTRACTORS, "ExtractorLightTimeSandPerTick", 100, "The amount of time sand extracted per tick if nearby area is fully lit.").getInt(100);
            MechanicTweaker.TIME_SAND_FROM_WATER_SECOND = config.get(CATEGORY_EXTRACTORS, "ExtractorWaterTimeSandPerTick", 100, "The amount of time sand extracted per tick if nearby area is all water.").getInt(100);
            MechanicTweaker.TIME_SAND_FROM_TREE_EXTRACTION = config.get(CATEGORY_EXTRACTORS, "ExtractorEarthTimeSandGrowth", 500, "The amount of time sand extracted from a nearby tree growth.").getInt(500);
            MechanicTweaker.TIME_SAND_FROM_AIR_SECOND = config.get(CATEGORY_EXTRACTORS, "ExtractorAirTimeSandPerTick", 100, "The amount of time sand extracted per tick if the extractor is at max height.").getInt(100);
            MechanicTweaker.TIME_SAND_FROM_ONE_FIRE_DAMAGE = config.get(CATEGORY_EXTRACTORS, "ExtractorFireTimeSandFromFireDamage", 300, "The amount of time sand extracted from a nearby entity hurt by fire.").getInt(300);
            MechanicTweaker.TIME_SAND_FROM_MOON_SECOND = config.get(CATEGORY_EXTRACTORS, "ExtractorLunarTimeSandFromSpawn", 100, "The amount of time sand extracted per tick if nighttime.").getInt(100);
            MechanicTweaker.TIME_SAND_FROM_DEATH = config.get(CATEGORY_EXTRACTORS, "ExtractorDeathTimeSandFromDeath", 2000, "The amount of time sand extracted from a nearby death.").getInt(2000);
        }
        catch(Exception e)
        {

        }
        finally
        {
            config.save();
        }
    }
}
