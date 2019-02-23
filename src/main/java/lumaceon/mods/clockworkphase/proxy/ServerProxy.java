package lumaceon.mods.clockworkphase.proxy;

import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.FMLCommonHandler;
import lumaceon.mods.clockworkphase.handler.TickHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ServerProxy extends CommonProxy
{
    @Override
    public void setRenderingForPlayer(EntityPlayer player) {}

    @Override
    public void registerKeybindings() {}

    @Override
    public void initializeParticleGenerator() {}

    @Override
    public void initializeSideOnlyHandlers()
    {

    }

    @Override
    public void registerModels()
    {

    }

    @Override
    public World getStaticWorld(){ return null; }

    @Override
    public RayTraceResult getObjectLookedAt() {
        return null;
    }
}
