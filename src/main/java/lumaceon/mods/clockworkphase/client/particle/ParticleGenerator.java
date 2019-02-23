package lumaceon.mods.clockworkphase.client.particle;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.client.particle.entityfx.EntityClockworkPhaseFX;
import lumaceon.mods.clockworkphase.client.particle.sequence.ParticleSequence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
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
        this.world = mc.world;
        this.random = mc.world.rand;
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

    public Particle spawnParticle(EntityClockworkPhaseFX particle, double cutoff)
    {
        if(canSpawnParticle(particle, cutoff))
        {
            mc.effectRenderer.addEffect(particle);
            return particle;
        }
        else
        {
            return null;
        }
    }

    private boolean canSpawnParticle(EntityClockworkPhaseFX particle, double maxDistance)
    {
        //Null checks
        if(mc == null || Minecraft.getMinecraft().world == null || mc.getRenderViewEntity() == null || mc.effectRenderer == null)
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
        double xDistance = mc.getRenderViewEntity().posX - particle.getPoss().getX();
        double yDistance = mc.getRenderViewEntity().posY - particle.getPoss().getY();
        double zDistance = mc.getRenderViewEntity().posZ - particle.getPoss().getZ();
        double cutoffDistance = maxDistance;

        if (xDistance * xDistance + yDistance * yDistance + zDistance * zDistance > cutoffDistance * cutoffDistance)
        {
            return false;
        }

        if(world == null)
        {
            world = mc.world;
        }
        return true;
    }
}
