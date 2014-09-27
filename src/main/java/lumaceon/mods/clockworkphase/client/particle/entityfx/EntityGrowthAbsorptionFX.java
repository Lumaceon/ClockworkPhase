package lumaceon.mods.clockworkphase.client.particle.entityfx;

import lumaceon.mods.clockworkphase.lib.ParticleResourceLocations;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityGrowthAbsorptionFX extends EntityClockworkPhaseFX
{
    public double[] targetLocation = new double[3];

    public EntityGrowthAbsorptionFX(World world, double x, double y, double z, double xTarget, double yTarget, double zTarget)
    {
        super(world, x, y, z);

        targetLocation[0] = xTarget;
        targetLocation[1] = yTarget;
        targetLocation[2] = zTarget;

        this.particleMaxAge = 60;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return ParticleResourceLocations.GROWTH_ABSORPTION;
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if(this.particleAge == 0)
        {
            this.motionX = this.targetLocation[0] - this.posX;
            this.motionY = 5.0F;
            this.motionZ = this.targetLocation[2] - this.posZ;
        }
        else if(this.particleAge > 0)
        {
            this.motionY *= 0.8;
        }

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.posX += this.motionX/10;
        this.posY += (this.motionY - 1.0F)/10;
        this.posZ += this.motionZ/10;
    }

    @Override
    public int getBrightnessForRender(float p_70070_1_)
    {
        return 255;
    }
}
