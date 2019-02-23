package lumaceon.mods.clockworkphase.client.particle.entityfx;

import lumaceon.mods.clockworkphase.lib.Textures;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityGrowthAbsorptionFX extends EntityClockworkPhaseFX
{
    public double[] targetLocation = new double[3];
    public double[] originLocation = new double[3];

    public EntityGrowthAbsorptionFX(World world, double x, double y, double z, double xTarget, double yTarget, double zTarget)
    {
        super(world, x, y, z);

        originLocation[0] = x;
        originLocation[1] = y;
        originLocation[2] = z;

        targetLocation[0] = xTarget;
        targetLocation[1] = yTarget;
        targetLocation[2] = zTarget;

        this.particleMaxAge = 15;
        this.canCollide = false;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return Textures.GROWTH_ABSORPTION;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        posX = targetLocation[0] - ((targetLocation[0] - originLocation[0]) * ((particleMaxAge - particleAge) / (double)particleMaxAge));
        posY = targetLocation[1] - ((targetLocation[1] - originLocation[1]) * ((particleMaxAge - particleAge) / (double)particleMaxAge));
        posZ = targetLocation[2] - ((targetLocation[2] - originLocation[2]) * ((particleMaxAge - particleAge) / (double)particleMaxAge));

        float mult = 0.05F;
        EntityClockworkPhaseFX particle = new EntityGrowthAbsorptionSubFX(world, posX, posY, posZ, (world.rand.nextFloat() - 0.5) * mult, world.rand.nextFloat() * mult * 0.5, (world.rand.nextFloat() - 0.5) * mult);
        ClientProxy.particleGenerator.spawnParticle(particle, 64.0F);
    }
}
