package lumaceon.mods.clockworkphase.lib;

import net.minecraft.util.ResourceLocation;

public class Textures
{
    /** Resource References **/
    public static final String RESOURCE_PREFIX = Reference.MOD_ID + ":";

    public static final String STILL_TIME_SAND = RESOURCE_PREFIX + "time_sand_still";
    public static final String FLOWING_TIME_SAND = RESOURCE_PREFIX + "time_sand_flowing";

    /** GUI Textures **/
    public static final ResourceLocation HOURGLASS_GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/hourglass.png");
    public static final ResourceLocation MULTITOOL_GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/multitool.png");

    /**
     * Default Vanilla Particles.
     * These must be bound back into the tessellator before continuing to render.
     */
    public static final ResourceLocation VANILLA_PARTICLES = new ResourceLocation("textures/particle/particles.png");

    /** Mod Particles **/
    public static final ResourceLocation ELEMENTAL_ATTUNEMENT = new ResourceLocation(Reference.MOD_ID, "textures/particles/growth_absorption.png");
    public static final ResourceLocation GROWTH_ABSORPTION = new ResourceLocation(Reference.MOD_ID, "textures/particles/growth_absorption.png");
    public static final ResourceLocation CLOCKWORK_PICK_SPECIAL = new ResourceLocation(Reference.MOD_ID, "textures/particles/clockwork_pick_special.png");
    public static final ResourceLocation BLOCK_IN_THE_WAY = new ResourceLocation(Reference.MOD_ID, "textures/particles/clockwork_pick_special.png");
}
