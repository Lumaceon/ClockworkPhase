package lumaceon.mods.clockworkphase.proxy;

import lumaceon.mods.clockworkphase.client.particle.ClientTickHandler;
import lumaceon.mods.clockworkphase.client.particle.ParticleGenerator;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
    public static ParticleGenerator particleGenerator;

    public void initializeParticleGenerator()
    {
        particleGenerator = new ParticleGenerator(Minecraft.getMinecraft());
    }
}
