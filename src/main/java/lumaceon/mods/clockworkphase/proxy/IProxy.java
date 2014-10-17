package lumaceon.mods.clockworkphase.proxy;

import net.minecraft.world.World;

public interface IProxy
{
    public void registerKeybindings();

    public void initializeParticleGenerator();

    public World getStaticWorld();
}
