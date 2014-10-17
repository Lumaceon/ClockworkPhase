package lumaceon.mods.clockworkphase.proxy;

import lumaceon.mods.clockworkphase.eventhandlers.GrowthHandler;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy extends CommonProxy
{
    @Override
    public void registerKeybindings() {}

    @Override
    public void initializeParticleGenerator() {}

    @Override
    public World getStaticWorld(){ return null; }
}
