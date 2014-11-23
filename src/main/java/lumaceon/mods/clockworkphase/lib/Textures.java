package lumaceon.mods.clockworkphase.lib;

import net.minecraft.util.ResourceLocation;

public class Textures
{
    /** Resource References **/
    public static final String RESOURCE_PREFIX = Reference.MOD_ID + ":";

    /** GUIs **/
    public static final ResourceLocation[] EXTRACTOR_GUI =
            {
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/guiExtractorLife.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/guiExtractorLight.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/guiExtractorWater.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/guiExtractorEarth.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/guiExtractorAir.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/guiExtractorFire.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/guiExtractorLunar.png"),
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/guiExtractorDeath.png")
            };
    public static final ResourceLocation[] EXTRACTOR_GUI_ACTIVE =
            {
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/guiExtractorLifeActive.png"),
                    EXTRACTOR_GUI[1],
                    EXTRACTOR_GUI[2],
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/guiExtractorEarthActive.png"),
                    EXTRACTOR_GUI[4],
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/guiExtractorFireActive.png"),
                    EXTRACTOR_GUI[6],
                    new ResourceLocation(Reference.MOD_ID, "textures/gui/guiExtractorDeathActive.png")
            };
    public static final ResourceLocation TIME_WELL_GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiTimeWell.png");

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
    public static final ResourceLocation BLOCK_IN_THE_WAY = new ResourceLocation(Reference.MOD_ID, "textures/particles/blockBlocking.png");
    public static final ResourceLocation TIME_TUNNEL = new ResourceLocation(Reference.MOD_ID, "textures/particles/time_tunnel.png");
    public static final ResourceLocation TIME_TUNNEL_SUB = new ResourceLocation(Reference.MOD_ID, "textures/particles/time_tunnel_sub.png");
    public static final ResourceLocation TIME_SAND_EXTRACTION = new ResourceLocation(Reference.MOD_ID, "textures/particles/timeSandExtraction.png");

    /** Misc Resources **/
    public static final ResourceLocation EXTRACTOR = new ResourceLocation(Reference.MOD_ID, "textures/blocks/extractor.png");
    public static final String STILL_TIME_SAND = RESOURCE_PREFIX + "time_sand_still";
    public static final String FLOWING_TIME_SAND = RESOURCE_PREFIX + "time_sand_flowing";
}
