package lumaceon.mods.clockworkphase.client.particle.entityfx;

import lumaceon.mods.clockworkphase.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityGrowthAbsorptionFX extends EntityFX
{
    public double[] targetLocation = new double[3];

    public static final ResourceLocation location = new ResourceLocation(Reference.MOD_ID, "textures/particles/growth_absorption.png");
    public static final ResourceLocation vLocation = new ResourceLocation("textures/particle/particles.png");

    public EntityGrowthAbsorptionFX(World world, double x, double y, double z, double xTarget, double yTarget, double zTarget, float scale, int timer)
    {
        super(world, x, y, z);

        targetLocation[0] = xTarget;
        targetLocation[1] = yTarget;
        targetLocation[2] = zTarget;

        this.particleMaxAge = timer + 30;
        this.particleScale = scale;
    }

    @Override
    public void renderParticle(Tessellator t, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        t.draw();
        Minecraft.getMinecraft().renderEngine.bindTexture(location);

        float f10 = 0.1F * this.particleScale;

        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)par2 - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)par2 - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)par2 - interpPosZ);
        float f14 = 1.0F;

        t.startDrawingQuads();
        t.setColorRGBA_F(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha);
        t.setBrightness(240);
        t.addVertexWithUV((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10), 1, 1);
        t.addVertexWithUV((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10), 1, 0);
        t.addVertexWithUV((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10), 0, 0);
        t.addVertexWithUV((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10), 0, 1);
        t.draw();

        Minecraft.getMinecraft().renderEngine.bindTexture(vLocation);
        t.startDrawingQuads();
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
    }
}
