package lumaceon.mods.clockworkphase.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public interface IProxy
{
    public void setRenderingForPlayer(EntityPlayer player);

    public void registerKeybindings();

    public void initializeParticleGenerator();

    public void initializeSideOnlyHandlers();

    public void registerModels();

    public World getStaticWorld();

    public RayTraceResult getObjectLookedAt();
}
