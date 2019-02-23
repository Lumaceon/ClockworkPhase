package lumaceon.mods.clockworkphase.client.render.model;

import lumaceon.mods.clockworkphase.client.render.RenderDrone;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ModelDrone extends ModelBiped
{
    private ModelRenderer sentry1, sentry2;

    public ModelDrone(float f)
    {
        super(f);
        textureWidth = 1024;
        textureHeight = 1024;

        sentry1 = new ModelRenderer(this, 0, 0);
        sentry1.addBox(-200F, -80F, -20F, 40, 40, 40);
        sentry1.setRotationPoint(0, 0, 0);

        sentry2 = new ModelRenderer(this, 0, 0);
        sentry2.addBox(200F, -80F, -20F, 40, 40, 40);
        sentry2.setRotationPoint(bipedBody.rotationPointX * 10, bipedBody.rotationPointY * 10, bipedBody.rotationPointZ * 10);
    }

    /**public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float size)
    {
        super.render(entity, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, size * 0.1F);
        sentry1.isHidden = false;

        if(entity instanceof EntityPlayer)
        {
            ExtendedPlayerProperties properties = ExtendedPlayerProperties.get((EntityPlayer)entity);
            RenderDrone render;
            if(properties.droneRenderers.isEmpty())
            {
                render = new RenderDrone(entity.worldObj.rand);
                properties.droneRenderers.add(render);
            }
            render = properties.droneRenderers.get(0);
            int time = render.timeSinceLastUpdate; if(time > 5) {time = 5;}
            sentry1.offsetX = render.prevOffsetX + ((render.offsetX - render.prevOffsetX) * (time / 5F));
            sentry1.offsetY = render.prevOffsetY + ((render.offsetY - render.prevOffsetY) * (time / 5F));
            sentry1.offsetZ = render.prevOffsetZ + ((render.offsetZ - render.prevOffsetZ) * (time / 5F));
        }
        else
        {
            sentry1.offsetX = bipedBody.offsetX * 10;
            sentry1.offsetY = bipedBody.offsetY * 10;
            sentry1.offsetZ = bipedBody.offsetZ * 10;
        }

        sentry1.render(size * 0.1F);
    }*/
}
