package lumaceon.mods.clockworkphase.client.particle.entityfx;

import lumaceon.mods.clockworkphase.lib.Textures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityElementalAttunementFX extends EntityClockworkPhaseFX
{
    public EntityElementalAttunementFX(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        this.motionX = (-0.5F + world.rand.nextFloat()) * 0.1;
        this.motionY = (1.0F + (world.rand.nextFloat() * 0.5F)) * 0.03;
        this.motionZ = (-0.5F + world.rand.nextFloat()) * 0.1;
        this.particleMaxAge = 45;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return Textures.TIME_SAND_EXTRACTION;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.motionY -= 0.0020;
    }

    @Override
    public int getBrightnessForRender(float p_70070_1_)
    {
        return 255;
    }
}
