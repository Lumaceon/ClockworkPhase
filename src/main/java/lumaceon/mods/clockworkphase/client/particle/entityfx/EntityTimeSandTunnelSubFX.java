package lumaceon.mods.clockworkphase.client.particle.entityfx;

import lumaceon.mods.clockworkphase.lib.Textures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityTimeSandTunnelSubFX extends EntityClockworkPhaseFX
{
    public EntityTimeSandTunnelSubFX(World world, double x, double y, double z, double xMotion, double yMotion, double zMotion)
    {
        super(world, x, y, z);
        this.motionX = xMotion;
        this.motionY = yMotion;
        this.motionZ = zMotion;
        this.particleMaxAge = 10;
        this.canCollide = false;
    }

    @Override
	public ResourceLocation getResourceLocation()
    {
        return Textures.TIME_TUNNEL;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.particleScale *= 0.93F;
    }
}
