package lumaceon.mods.clockworkphase.lib;

import net.minecraft.util.ResourceLocation;

public class ParticleResourceLocations
{
    /**
     * Default Vanilla Particles.
     * These must be bound back into the tessellator before continuing to render.
     */
    public static final ResourceLocation VANILLA_PARTICLES = new ResourceLocation("textures/particle/particles.png");


    /** Mod Particles **/
    public static final ResourceLocation TIME_ENERGY_EXTRACTOR = new ResourceLocation(Reference.MOD_ID, "textures/particles/time_energy_extractor.png");
    public static final ResourceLocation TIMER_ENERGY_EXTRACTOR_FRAGMENTED = new ResourceLocation(Reference.MOD_ID, "textures/particles/fragmented_time_energy_extractor.png");
    public static final ResourceLocation TIME_BLAST = new ResourceLocation(Reference.MOD_ID, "textures/particles/time_blast.png");
    public static final ResourceLocation GROWTH_ABSORPTION = new ResourceLocation(Reference.MOD_ID, "textures/particles/growth_absorption.png");

    /** Trails **/
}
