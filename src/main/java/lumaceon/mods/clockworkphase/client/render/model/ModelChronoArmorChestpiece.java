package lumaceon.mods.clockworkphase.client.render.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChronoArmorChestpiece extends ModelBiped
{
    private ModelRenderer back;
    private ModelRenderer chest;
    private ModelRenderer rightArmGuard;
    private ModelRenderer leftArmGuard;

    public ModelChronoArmorChestpiece(float f)
    {
        super(f);
        textureHeight = 1024;
        textureWidth = 1024;
        back = new ModelRenderer(this, 360, 0);
        back.addBox(-120.0F, -80.0F, 60.0F, 240, 320, 1);
        back.setRotationPoint(0, 0, 0);
        bipedBody.addChild(back);

        chest = new ModelRenderer(this, 0, 180);
        chest.addBox(-50.0F, -10F, -27.5F, 100, 130, 55);
        chest.setRotationPoint(0, 0, 0);
        bipedBody.addChild(chest);

        rightArmGuard = new ModelRenderer(this, 0, 365);
        rightArmGuard.addBox(-42F, -30F, -28F, 56, 80, 56);
        rightArmGuard.setRotationPoint(bipedRightArm.rotationPointX * 10, bipedRightArm.rotationPointY * 10, bipedRightArm.rotationPointZ * 10);

        leftArmGuard = new ModelRenderer(this, 0, 501);
        leftArmGuard.addBox(-22F, -30F, -28F, 56, 80, 56);
        leftArmGuard.setRotationPoint(bipedLeftArm.rotationPointX * 10, bipedLeftArm.rotationPointY * 10, bipedLeftArm.rotationPointZ * 10);
    }

    @Override
    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float size)
    {
        super.render(entity, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, size * 0.1F);
        rightArmGuard.setRotationPoint(bipedRightArm.rotationPointX * 10, bipedRightArm.rotationPointY * 10, bipedRightArm.rotationPointZ * 10);
        rightArmGuard.isHidden = bipedRightArm.isHidden;
        rightArmGuard.offsetX = bipedRightArm.offsetX * 10;
        rightArmGuard.offsetY = bipedRightArm.offsetY * 10;
        rightArmGuard.offsetZ = bipedRightArm.offsetZ * 10;
        rightArmGuard.rotateAngleX = bipedRightArm.rotateAngleX;
        rightArmGuard.rotateAngleY = bipedRightArm.rotateAngleY;
        rightArmGuard.rotateAngleZ = bipedRightArm.rotateAngleZ;
        rightArmGuard.render(size * 0.1F);

        leftArmGuard.setRotationPoint(bipedLeftArm.rotationPointX * 10, bipedLeftArm.rotationPointY * 10, bipedLeftArm.rotationPointZ * 10);
        leftArmGuard.isHidden = bipedLeftArm.isHidden;
        leftArmGuard.offsetX = bipedLeftArm.offsetX * 10;
        leftArmGuard.offsetY = bipedLeftArm.offsetY * 10;
        leftArmGuard.offsetZ = bipedLeftArm.offsetZ * 10;
        leftArmGuard.rotateAngleX = bipedLeftArm.rotateAngleX;
        leftArmGuard.rotateAngleY = bipedLeftArm.rotateAngleY;
        leftArmGuard.rotateAngleZ = bipedLeftArm.rotateAngleZ;
        leftArmGuard.render(size * 0.1F);
    }
}
