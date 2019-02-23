package lumaceon.mods.clockworkphase.client.particle.entityfx;

import lumaceon.mods.clockworkphase.lib.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityClockworkPhaseFX extends Particle
{
    public EntityClockworkPhaseFX(World world, double x, double y, double z)
    {
        super(world, x, y, z);

        this.particleAlpha = 1.0F;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
    }

    public EntityClockworkPhaseFX(World world, double x, double y, double z, double xMotion, double yMotion, double zMotion)
    {
        super(world, x, y, z, xMotion, yMotion, zMotion);

        this.particleAlpha = 1.0F;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        Tessellator.getInstance().draw();
        Minecraft.getMinecraft().renderEngine.bindTexture(getResourceLocation());

        float f10 = 0.1F * this.particleScale;

        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)par2 - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)par2 - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)par2 - interpPosZ);
        float f14 = 1.0F;
        int i = this.getBrightnessForRender(par2);
        int j = i >> 16 & 65535;
        int k = i & 65535;

        buffer.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        buffer.pos((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10)).tex(1, 1).color(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10)).tex(1, 0).color(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10)).tex(0, 0).color(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10)).tex(0, 1).color(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha).lightmap(j, k).endVertex();
        Tessellator.getInstance().draw();

        Minecraft.getMinecraft().renderEngine.bindTexture(Textures.VANILLA_PARTICLES);
        buffer.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
    }

    public ResourceLocation getResourceLocation()
    {
        return Textures.VANILLA_PARTICLES;
    }

    public BlockPos getPoss() {
        return new BlockPos(posX, posY, posZ);
    }

    @Override
    public int getBrightnessForRender(float p_70070_1_)
    {
        return 255;
    }
}
