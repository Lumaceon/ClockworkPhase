package lumaceon.mods.clockworkphase.client.particle.sequence;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.client.particle.ParticleGenerator;
import net.minecraft.client.Minecraft;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class ParticleSequence
{
    public ParticleGenerator particleGenerator;
    public Random random;
    public int timer = 0;

    public ParticleSequence(ParticleGenerator particleGen)
    {
        this.particleGenerator = particleGen;
        this.random = Minecraft.getMinecraft().theWorld.rand;
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
