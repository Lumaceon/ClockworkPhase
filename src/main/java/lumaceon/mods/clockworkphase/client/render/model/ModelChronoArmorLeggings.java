package lumaceon.mods.clockworkphase.client.render.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChronoArmorLeggings extends ModelBiped
{
    private ModelRenderer back;
    private ModelRenderer waistBox;
    private ModelRenderer leftLegGuard;
    private ModelRenderer rightLegGuard;

    public ModelChronoArmorLeggings(float f)
    {
        super(f);
        textureHeight = 1024;
        textureWidth = 1024;
        back = new ModelRenderer(this, 360, 321);
        back.addBox(-120.0F, -80.0F, 60.0F, 240, 320, 1);
        back.setRotationPoint(0, 0, 0);
        bipedBody.addChild(back);

        waistBox = new ModelRenderer(this, 184, 646);
        waistBox.addBox(-45.0F, 80F, -24.0F, 90, 35, 48);
        waistBox.setRotationPoint(0, 0, 0);
        bipedBody.addChild(waistBox);

        leftLegGuard = new ModelRenderer(this, 0, 637);
        leftLegGuard.addBox(-22.5F, -10F, -23.0F, 47, 100, 45);
        leftLegGuard.setRotationPoint(bipedLeftLeg.rotationPointX * 10, bipedLeftLeg.rotationPointY * 10, bipedLeftLeg.rotationPointZ * 10);

        rightLegGuard = new ModelRenderer(this, 0, 637);
        rightLegGuard.addBox(-24.5F, -10F, -23.0F, 47, 100, 45);
        rightLegGuard.setRotationPoint(bipedRightLeg.rotationPointX * 10, bipedRightLeg.rotationPointY * 10, bipedRightLeg.rotationPointZ * 10);
    }

    @Override
	public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float size)
    {
        super.render(entity, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, size * 0.1F);
        rightLegGuard.setRotationPoint(bipedRightLeg.rotationPointX * 10, bipedRightLeg.rotationPointY * 10, bipedRightLeg.rotationPointZ * 10);
        rightLegGuard.isHidden = bipedRightLeg.isHidden;
        rightLegGuard.offsetX = bipedRightLeg.offsetX * 10;
        rightLegGuard.offsetY = bipedRightLeg.offsetY * 10;
        rightLegGuard.offsetZ = bipedRightLeg.offsetZ * 10;
        rightLegGuard.rotateAngleX = bipedRightLeg.rotateAngleX;
        rightLegGuard.rotateAngleY = bipedRightLeg.rotateAngleY;
        rightLegGuard.rotateAngleZ = bipedRightLeg.rotateAngleZ;
        rightLegGuard.render(size * 0.1F);

        leftLegGuard.setRotationPoint(bipedLeftLeg.rotationPointX * 10, bipedLeftLeg.rotationPointY * 10, bipedLeftLeg.rotationPointZ * 10);
        leftLegGuard.isHidden = bipedLeftLeg.isHidden;
        leftLegGuard.offsetX = bipedLeftLeg.offsetX * 10;
        leftLegGuard.offsetY = bipedLeftLeg.offsetY * 10;
        leftLegGuard.offsetZ = bipedLeftLeg.offsetZ * 10;
        leftLegGuard.rotateAngleX = bipedLeftLeg.rotateAngleX;
        leftLegGuard.rotateAngleY = bipedLeftLeg.rotateAngleY;
        leftLegGuard.rotateAngleZ = bipedLeftLeg.rotateAngleZ;
        leftLegGuard.render(size * 0.1F);
    }
}
