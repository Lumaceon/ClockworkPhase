package lumaceon.mods.clockworkphase.lib;

public class MechanicTweaker
{
    public static final int MAX_TENSION = 1000000;
    public static final int MAX_TS_TIME_WELL = 100000000;

    //Values for time sand addition\\
    public static final int PICKAXE_TIME_SAND_INCREMENT = 100;
    public static final int AXE_TIME_SAND_INCREMENT = 200;
    public static final int SHOVEL_TIME_SAND_INCREMENT = 10;

    public static final int MAX_TIME_SAND_TOOLS = 100000;
    //END TIME SAND VALUES\\

    //Once per tick
    public static final int LIFE_HOURGLASS_TENSION_COST = 500;
    //Once per light
    public static final int LIGHT_HOURGLASS_TENSION_COST = 500;
    //Once per tick
    public static final int WATER_HOURGLASS_TENSION_COST = 80;
    //Once per tick
    public static final int EARTH_HOURGLASS_TENSION_COST = 20;
    //Once per tick
    public static final int AIR_HOURGLASS_TENSION_COST = 50;
    //Once per tick
    public static final int FIRE_HOURGLASS_TENSION_COST = 120;
    //Once per tick
    public static final int LUNAR_HOURGLASS_TENSION_COST = 50;
    //Once per tick
    public static final int DEATH_HOURGLASS_TENSION_COST = 250;

    //For clockwork tools, applies once each time a block is broken
    public static final int TENSION_PER_BLOCK_BREAK = 100;
    //For clockwork saber, applies once each time an entity is hit
    public static final int TENSION_PER_HIT = 500;
}