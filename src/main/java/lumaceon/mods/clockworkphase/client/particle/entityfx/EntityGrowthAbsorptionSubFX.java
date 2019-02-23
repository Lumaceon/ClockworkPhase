package lumaceon.mods.clockworkphase.client.particle.entityfx;

import lumaceon.mods.clockworkphase.lib.Textures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityGrowthAbsorptionSubFX extends EntityClockworkPhaseFX
{
    public EntityGrowthAbsorptionSubFX(World world, double x, double y, double z, double xMotion, double yMotion, double zMotion)
    {
        super(world, x, y, z);
        this.setSize(0.2F, 0.2F);
        this.motionX = xMotion;
        this.motionY = yMotion;
        this.motionZ = zMotion;
        this.particleMaxAge = 20;
        this.canCollide = false;
    }

    @Override
	public ResourceLocation getResourceLocation()
    {
        return Textures.GROWTH_ABSORPTION;
    }
}
