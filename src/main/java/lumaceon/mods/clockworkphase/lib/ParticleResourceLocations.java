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
    public static final ResourceLocation ELEMENTAL_ATTUNEMENT = new ResourceLocation(Reference.MOD_ID, "textures/particles/growth_absorption.png");
    public static final ResourceLocation GROWTH_ABSORPTION = new ResourceLocation(Reference.MOD_ID, "textures/particles/growth_absorption.png");

    /** Trails **/
}
