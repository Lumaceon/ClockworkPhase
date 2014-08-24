package lumaceon.mods.clockworkphase.proxy;

import lumaceon.mods.clockworkphase.client.particle.ParticleGenerator;
import net.minecraft.client.Minecraft;

public class ClientProxy extends CommonProxy
{
    public ParticleGenerator particleGenerator;

    public void initializeParticleGenerator()
    {
        particleGenerator = new ParticleGenerator(Minecraft.getMinecraft());
    }
}
