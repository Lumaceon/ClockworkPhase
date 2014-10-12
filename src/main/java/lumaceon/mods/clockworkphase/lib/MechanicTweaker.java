package lumaceon.mods.clockworkphase.lib;

public class MechanicTweaker
{
    public static final int MAX_TENSION = 1000000;
    public static final int MEMORY_PER_KILL = 3;
    public static final int MAX_MEMORY_MEMCORE = 299;

    /** Once per tick, giving regeneration **/
    public static final int LIFE_HOURGLASS_TENSION_COST = 500;
    /** Once per light **/
    public static final int LIGHT_HOURGLASS_TENSION_COST = 500;
    /** Once per tick of water breath **/
    public static final int WATER_HOURGLASS_TENSION_COST = 80;
    /** Once per tick of levitation **/
    public static final int EARTH_HOURGLASS_TENSION_COST = 20;
    /** Once per tick of flight **/
    public static final int AIR_HOURGLASS_TENSION_COST = 50;
    /** Once per tick of fire resistance **/
    public static final int FIRE_HOURGLASS_TENSION_COST = 120;
    /** ??? **/
    public static final int LUNAR_HOURGLASS_TENSION_COST = 50;
    /** Once per infused hit **/
    public static final int DEATH_HOURGLASS_TENSION_COST = 250;

    /** For clockwork tools, applies once each time a block is broken **/
    public static final int TENSION_PER_BLOCK_BREAK = 100;
}