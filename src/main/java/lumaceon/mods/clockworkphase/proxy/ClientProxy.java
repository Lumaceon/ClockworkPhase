package lumaceon.mods.clockworkphase.proxy;

import lumaceon.mods.clockworkphase.client.particle.ParticleGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy
{
    public static ParticleGenerator particleGenerator;

    public void initializeParticleGenerator()
    {
        particleGenerator = new ParticleGenerator(Minecraft.getMinecraft());
    }

    public World getStaticWorld()
    {
        return Minecraft.getMinecraft().theWorld;
    }
}
