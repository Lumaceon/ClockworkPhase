package lumaceon.mods.clockworkphase.client.particle.entityfx;

import lumaceon.mods.clockworkphase.lib.Textures;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityTimeSandTunnelFX extends EntityClockworkPhaseFX
{
    double originX, originY, originZ;
    double targetX, targetY, targetZ;

    public EntityTimeSandTunnelFX(World world, double x, double y, double z, double targetX, double targetY, double targetZ)
    {
        super(world, x, y, z);
        this.originX = x;
        this.originY = y;
        this.originZ = z;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        this.particleMaxAge = 40;
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

        posX = targetX - ((targetX - originX) * ((particleMaxAge - particleAge) / (double)particleMaxAge));
        posY = targetY - ((targetY - originY) * ((particleMaxAge - particleAge) / (double)particleMaxAge));
        posZ = targetZ - ((targetZ - originZ) * ((particleMaxAge - particleAge) / (double)particleMaxAge));

        EntityClockworkPhaseFX particle = new EntityTimeSandTunnelSubFX(world, prevPosX, prevPosY, prevPosZ, 0, 0, 0);
        ClientProxy.particleGenerator.spawnParticle(particle, 64.0F);
    }
}
