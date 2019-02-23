package lumaceon.mods.clockworkphase.client.particle.sequence;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.client.particle.ParticleGenerator;
import lumaceon.mods.clockworkphase.util.Range2I;

import java.util.ArrayList;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class ParticleSequence
{
    public ParticleGenerator particleGenerator;
    public Random random;

    public ArrayList<Range2I> sequencePhases = new ArrayList<Range2I>(5);
    public int timer = 0;

    public ParticleSequence(ParticleGenerator particleGen)
    {
        this.particleGenerator = particleGen;
        if(particleGen.random == null)
        {
            particleGen.random = new Random();
        }
        this.random = particleGen.random;
    }

    /**
     * A method to be overridden for updating the particle sequence. This is where your particles should spawn.
     * Take care to always return true when your timer has finished.
     * @return True if the particle sequence has more to do (ie. timer has not reached 0). False if it has concluded.
     */
    public boolean updateParticleSequence()
    {
        return false;
    }
}
