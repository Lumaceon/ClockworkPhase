package lumaceon.mods.clockworkphase.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class ParticleGenerator
{
    protected Minecraft mc;
    protected World world;

    public ParticleGenerator(Minecraft minecraft)
    {
        this.mc = minecraft;
    }

    public EntityFX spawnGrowthAbsorptionParticles(double x, double y, double z)
    {
        if(canSpawnParticle(x, y, z, 64.0D))
        {
            EntityFX particle = new EntityGrowthAbsorptionFX(world, x, y, z);
            mc.effectRenderer.addEffect((EntityFX)particle);
            return (EntityFX)particle;
        }
        else
        {
            return null;
        }
    }

    private boolean canSpawnParticle(double x, double y, double z, double maxDistance)
    {
        //Null checks
        if(mc != null && Minecraft.getMinecraft().theWorld != null && mc.renderViewEntity != null && mc.effectRenderer != null)
        {
            return false;
        }

        //Turns off these particles if particles are set to be minimal, and reduces them if needed.
        int userSettings = mc.gameSettings.particleSetting;

        if((userSettings == 1 && world.rand.nextInt(3) == 0) || userSettings == 2)
        {
            return false;
        }

        //Calculates distance to the particle generation, and returns false if it's too far away.
        double xDistance = mc.renderViewEntity.posX - x;
        double yDistance = mc.renderViewEntity.posY - y;
        double zDistance = mc.renderViewEntity.posZ - z;
        double cutoffDistance = maxDistance;

        if (xDistance * xDistance + yDistance * yDistance + zDistance * zDistance > cutoffDistance * cutoffDistance)
        {
            return false;
        }

        if(world == null)
        {
            world = mc.theWorld;
        }
        return true;
    }
}
