package lumaceon.mods.clockworkphase.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import lumaceon.mods.clockworkphase.client.particle.ParticleGenerator;
import lumaceon.mods.clockworkphase.client.settings.Keybindings;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy
{
    public static ParticleGenerator particleGenerator;

    @Override
    public void registerKeybindings()
    {
        ClientRegistry.registerKeyBinding(Keybindings.multitool);
    }

    public void initializeParticleGenerator()
    {
        particleGenerator = new ParticleGenerator(Minecraft.getMinecraft());
    }

    public World getStaticWorld()
    {
        return Minecraft.getMinecraft().theWorld;
    }
}
