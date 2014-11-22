package lumaceon.mods.clockworkphase.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import lumaceon.mods.clockworkphase.handler.TickHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
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
    public MovingObjectPosition getObjectLookedAt() {
        return null;
    }
}
