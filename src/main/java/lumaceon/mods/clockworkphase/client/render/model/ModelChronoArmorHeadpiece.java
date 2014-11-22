package lumaceon.mods.clockworkphase.client.render.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChronoArmorHeadpiece extends ModelBiped
{
    private ModelRenderer headpiece;

    public ModelChronoArmorHeadpiece(float f)
    {
        super(f);
        textureHeight = 1024;
        textureWidth = 1024;
        headpiece = new ModelRenderer(this, 0, 0);
        headpiece.addBox(-45F, -95F, -45F, 90, 90, 90);
        headpiece.setRotationPoint(0, 0, 0);
        bipedHead.addChild(headpiece);
    }

    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float size)
    {
        super.render(entity, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, size * 0.1F);
    }
}
