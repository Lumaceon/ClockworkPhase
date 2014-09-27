package lumaceon.mods.clockworkphase.proxy;

import lumaceon.mods.clockworkphase.eventhandlers.GrowthHandler;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy extends CommonProxy
{
    public void initializeParticleGenerator() {}

    @Override
    public World getStaticWorld()
    {
        //NOOP
        return null;
    }
}
