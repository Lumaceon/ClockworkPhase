package lumaceon.mods.clockworkphase.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.client.particle.sequence.ParticleSequence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class ParticleGenerator
{
    public final ParticleSpawner SPAWNER = new ParticleSpawner(this);

    public Minecraft mc;
    public World world;
    public Random random;
    private ArrayList<ParticleSequence> activeSequences = new ArrayList<ParticleSequence>(100);

    public ParticleGenerator(Minecraft minecraft)
    {
        this.mc = minecraft;
    }

    public void spawnParticleSequence(ParticleSequence particleSequence)
    {
        activeSequences.add(particleSequence);
    }

    public void initWorldAndRandom()
    {
        this.world = mc.theWorld;
        this.random = mc.theWorld.rand;
    }

    public void updateParticleSequences()
    {
        for(int n = 0; n < activeSequences.size(); n++)
        {
            if(world == null || !activeSequences.get(n).updateParticleSequence())
            {
                activeSequences.remove(n);
            }
        }
    }

    public EntityFX spawnParticle(EntityFX particle, double cutoff)
    {
        if(canSpawnParticle(particle.posX, particle.posY, particle.posZ, cutoff))
        {
            mc.effectRenderer.addEffect(particle);
            return particle;
        }
        else
        {
            return null;
        }
    }

    private boolean canSpawnParticle(double x, double y, double z, double maxDistance)
    {
        //Null checks
        if(mc == null || Minecraft.getMinecraft().theWorld == null || mc.renderViewEntity == null || mc.effectRenderer == null)
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
