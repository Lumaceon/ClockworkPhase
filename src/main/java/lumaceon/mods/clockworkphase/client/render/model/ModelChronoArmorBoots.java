package lumaceon.mods.clockworkphase.client.render.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChronoArmorBoots extends ModelBiped
{
    private ModelRenderer rightBoot;
    private ModelRenderer leftBoot;

    public ModelChronoArmorBoots(float f)
    {
        super(f);
        textureHeight = 1024;
        textureWidth = 1024;
        rightBoot = new ModelRenderer(this, 0, 782);
        rightBoot.addBox(-27.5F, 70F, -27.5F, 55, 50, 55);
        rightBoot.setRotationPoint(bipedRightLeg.rotationPointX * 10, bipedRightLeg.rotationPointY * 10, bipedRightLeg.rotationPointZ * 10);

        leftBoot = new ModelRenderer(this, 0, 782);
        leftBoot.addBox(-27.5F, 70F, -27.5F, 55, 50, 55);
        leftBoot.setRotationPoint(bipedLeftLeg.rotationPointX * 10, bipedLeftLeg.rotationPointY * 10, bipedLeftLeg.rotationPointZ * 10);
    }

    @Override
	public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float size)
    {
        super.render(entity, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, size * 0.1F);
        rightBoot.setRotationPoint(bipedRightLeg.rotationPointX * 10, bipedRightLeg.rotationPointY * 10, bipedRightLeg.rotationPointZ * 10);
        rightBoot.isHidden = bipedRightLeg.isHidden;
        rightBoot.offsetX = bipedRightLeg.offsetX * 10;
        rightBoot.offsetY = bipedRightLeg.offsetY * 10;
        rightBoot.offsetZ = bipedRightLeg.offsetZ * 10;
        rightBoot.rotateAngleX = bipedRightLeg.rotateAngleX;
        rightBoot.rotateAngleY = bipedRightLeg.rotateAngleY;
        rightBoot.rotateAngleZ = bipedRightLeg.rotateAngleZ;
        rightBoot.render(size * 0.1F);

        leftBoot.setRotationPoint(bipedLeftLeg.rotationPointX * 10, bipedLeftLeg.rotationPointY * 10, bipedLeftLeg.rotationPointZ * 10);
        leftBoot.isHidden = bipedLeftLeg.isHidden;
        leftBoot.offsetX = bipedLeftLeg.offsetX * 10;
        leftBoot.offsetY = bipedLeftLeg.offsetY * 10;
        leftBoot.offsetZ = bipedLeftLeg.offsetZ * 10;
        leftBoot.rotateAngleX = bipedLeftLeg.rotateAngleX;
        leftBoot.rotateAngleY = bipedLeftLeg.rotateAngleY;
        leftBoot.rotateAngleZ = bipedLeftLeg.rotateAngleZ;
        leftBoot.render(size * 0.1F);
    }
}
