package lumaceon.mods.clockworkphase.lib;

import net.minecraft.util.ResourceLocation;

public class Textures
{
    /** Resource References **/
    public static final String RESOURCE_PREFIX = Reference.MOD_ID + ":";

    /** GUIs **/
    public static final ResourceLocation[] EXTRACTOR_GUI =
            {
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_extractor_life.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_extractor_light.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_extractor_water.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_extractor_earth.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_extractor_air.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_extractor_fire.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_extractor_lunar.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_extractor_death.png")
            };
    public static final ResourceLocation[] EXTRACTOR_GUI_ACTIVE =
            {
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_extractor_life_active.png"),
                    EXTRACTOR_GUI[1],
                    EXTRACTOR_GUI[2],
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_extractor_earth_active.png"),
                    EXTRACTOR_GUI[4],
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_extractor_fire_active.png"),
                    EXTRACTOR_GUI[6],
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_extractor_death_active.png")
            };
    public static final ResourceLocation TIME_WELL_GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_time_well.png");
    public static final ResourceLocation ASSEMBLY_TABLE_GUI_CW = new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_assembly_table_clockwork.png");
    public static final ResourceLocation ASSEMBLY_TABLE_GUI_MS = new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_assembly_table_mainspring.png");
    public static final ResourceLocation ASSEMBLY_TABLE_GUI_ASSEMBLE = new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_assembly_table_assemble.png");

    /**
     * Default Vanilla Particles.
     * These must be bound back into the tessellator before continuing to render.
     */
    public static final ResourceLocation VANILLA_PARTICLES = new ResourceLocation("textures/particle/particles.png");
    public static final ResourceLocation VANILLA_ICONS = new ResourceLocation("textures/gui/icons.png");

    /** Mod Particles **/
    public static final ResourceLocation ELEMENTAL_ATTUNEMENT = new ResourceLocation(Reference.MOD_ID, "textures/particles/growth_absorption.png");
    public static final ResourceLocation GROWTH_ABSORPTION = new ResourceLocation(Reference.MOD_ID, "textures/particles/growth_absorption.png");
    public static final ResourceLocation CLOCKWORK_PICK_SPECIAL = new ResourceLocation(Reference.MOD_ID, "textures/particles/clockwork_pick_special.png");
    public static final ResourceLocation BLOCK_IN_THE_WAY = new ResourceLocation(Reference.MOD_ID, "textures/particles/block_blocking.png");
    public static final ResourceLocation TIME_TUNNEL = new ResourceLocation(Reference.MOD_ID, "textures/particles/time_tunnel.png");
    public static final ResourceLocation TIME_TUNNEL_SUB = new ResourceLocation(Reference.MOD_ID, "textures/particles/time_tunnel_sub.png");
    public static final ResourceLocation TIME_SAND_EXTRACTION = new ResourceLocation(Reference.MOD_ID, "textures/particles/time_sand_extraction.png");

    /** Misc Resources **/
    public static final ResourceLocation EXTRACTOR = new ResourceLocation(Reference.MOD_ID, "textures/blocks/extractor.png");
    public static final String STILL_TIME_SAND = RESOURCE_PREFIX + "blocks/time_sand_still";
    public static final String FLOWING_TIME_SAND = RESOURCE_PREFIX + "blocks/time_sand_flowing";
}
