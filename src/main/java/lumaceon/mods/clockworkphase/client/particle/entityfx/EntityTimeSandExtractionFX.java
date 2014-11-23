package lumaceon.mods.clockworkphase.client.particle.entityfx;

import lumaceon.mods.clockworkphase.lib.Textures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityTimeSandExtractionFX extends EntityClockworkPhaseFX
{
    public EntityTimeSandExtractionFX(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        this.motionX = (-0.5F + world.rand.nextFloat()) * 0.2F;
        this.motionY = (-0.5F + world.rand.nextFloat()) * 0.2F;
        this.motionZ = (-0.5F + world.rand.nextFloat()) * 0.2F;
        this.particleMaxAge = 25;
        this.noClip = true;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return Textures.TIME_SAND_EXTRACTION;
    }
}
