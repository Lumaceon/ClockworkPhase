package lumaceon.mods.clockworkphase.lib;

public class MechanicTweaker
{
    public static final int MAINSPRING_TENSION_MUILTIPLIER = 8;

    public static final int MAX_TENSION = 1000000;
    public static final int MAX_TS_DEFAULT = 1000000;
    public static final int MAX_TIME_SAND_TOOLS = 10000;
    public static final int MAX_TS_TIME_WELL = 100000000;
    public static final int MAX_TS_EXTRACTORS = 1000000;

    public static final int TIME_SAND_FROM_NATURAL_SPAWN = 5000; //Life
    public static final int TIME_SAND_FROM_LIGHT_SECOND = 100; //Light
    public static final int TIME_SAND_FROM_WATER_SECOND = 100; //Water
    public static final int TIME_SAND_FROM_TREE_EXTRACTION = 500; //Earth
    public static final int TIME_SAND_FROM_AIR_SECOND = 100; //Air
    public static final int TIME_SAND_FROM_ONE_FIRE_DAMAGE = 300; //Fire
    public static final int TIME_SAND_FROM_MOON_SECOND = 100; //Lunar
    public static final int TIME_SAND_FROM_DEATH = 2000; //Death

    public static final int PICKAXE_TIME_SAND_INCREMENT = 25;
    public static final int AXE_TIME_SAND_INCREMENT = 100;
    public static final int SHOVEL_TIME_SAND_INCREMENT = 20;
    public static final int SABER_TIME_SAND_INCREMENT_KILL = 500;


    public static final int LIFE_HOURGLASS_TENSION_COST = 500; //Once per tick.
    public static final int LIGHT_HOURGLASS_TENSION_COST = 500; //Once per light.
    public static final int WATER_HOURGLASS_TENSION_COST = 80; //Once per tick.
    public static final int EARTH_HOURGLASS_TENSION_COST = 20; //Once per tick.
    public static final int AIR_HOURGLASS_TENSION_COST = 50; //Once per tick.
    public static final int FIRE_HOURGLASS_TENSION_COST = 120; //Once per tick.
    public static final int LUNAR_HOURGLASS_TENSION_COST = 50; //Once per tick.
    public static final int DEATH_HOURGLASS_TENSION_COST = 250; //Once per tick.

    public static final int CLOCKWORK_ARMOR_TENSION_COST = 250; //Once per hit (Applies to each piece of armor).

    public static final int TENSION_PER_BLOCK_BREAK = 250; //For clockwork tools, applies once each time a block is broken.
    public static final int TENSION_PER_HIT = 500; //For clockwork saber, applies once each time an entity is hit.
    public static final int TIME_SAND_PER_BLOCK_BREAK_PICKAXE = PICKAXE_TIME_SAND_INCREMENT; //For temporal pickaxe, applies once each time a block is broken.
    public static final int TIME_SAND_PER_BLOCK_BREAK_AXE = AXE_TIME_SAND_INCREMENT; //For temporal axe, applies once each time a block is broken.
    public static final int TIME_SAND_PER_BLOCK_BREAK_SHOVEL = SHOVEL_TIME_SAND_INCREMENT; //For temporal shovel, applies once each time a block is broken.
    public static final int TIME_SAND_PER_ENTITY_HIT = SABER_TIME_SAND_INCREMENT_KILL; //For temporal saber, applies once each time an entity is hit.

    public static final int TIME_SAND_CHANCE_FACTOR = 12800000;

    /**
     * For temporal tools, applies once per block conversion to temporal metal.
     * Note that one temporal ingot costs almost 1 bucketsworth (1,000) of time sand.
     */
    public static final int TIME_SAND_COST_TEMPORAL_PICKAXE = 200; //2 Nuggets worth.
    public static final int TIME_SAND_COST_TEMPORAL_AXE = 150; //1.5 Nuggets worth.
    public static final int TIME_SAND_COST_TEMPORAL_SHOVEL = 30; //1.5 Chips worth.

    /** Pocket Watch Module Time Sand Costs **/
    public static final int TIME_SAND_COST_SILK_MODULE = 25; //Silk touch module, once per block silk-touched.
    public static final int TIME_SAND_COST_FURNACE_MODULE = 25; //Furnace module, once per block auto-smelted.
    public static final int TIME_SAND_COST_LIFE_WALK_MODULE = 3; //Life walk module, amount of time sand per health power.
    public static final int TIME_SAND_COST_PART_GRAVITY_MODULE = 2; //Lunar boots, amount of time sand per active tick.
    public static final int TIME_SAND_COST_DEATH_WALK_MODULE = 10; //Death walk module, amount of time sand per death power.

    /** Pocket Watch Module Mechanics **/
    public static final int LIFE_DEFENSE_MAX = 1000; //Life walk module, max life energy it can hold.
    public static final int LIFE_DEFENSE_PER_HEALTH = 50; //Life walk module, amount of life energy per half heart.
    public static final int DEATH_ATTACK_MAX = 1000; //Death walk module, max death power it can hold.
    public static final int DEATH_ATTACK_PER_HEALTH = 50; //Death walk module, amount of death power per half heart.
}