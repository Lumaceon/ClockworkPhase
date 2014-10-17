package lumaceon.mods.clockworkphase.client.particle.entityfx;

import lumaceon.mods.clockworkphase.lib.Textures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBlockInTheWayFX extends EntityClockworkPhaseFX
{
    public EntityBlockInTheWayFX(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        this.motionX = (-0.5F + world.rand.nextFloat()) * 0.2F;
        this.motionY = (-0.5F + world.rand.nextFloat()) * 0.2F;
        this.motionZ = (-0.5F + world.rand.nextFloat()) * 0.2F;
        this.particleMaxAge = 25;
        this.noClip = true;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return Textures.BLOCK_IN_THE_WAY;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }

    @Override
    public int getBrightnessForRender(float p_70070_1_)
    {
        return 255;
    }
}
