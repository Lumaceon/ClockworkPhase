package lumaceon.mods.clockworkphase.client.render.model;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import java.util.ArrayList;

public class ModelExtractor extends ModelBase
{
    private ArrayList<ModelRenderer> renderers;

    public ModelExtractor()
    {
        renderers = new ArrayList<ModelRenderer>();
        textureHeight = 256;
        textureWidth = 256;

        ModelRenderer main = new ModelRenderer(this);
        renderers.add(main);

        ModelRenderer mainBox = new ModelRenderer(this, 0, 0);
        mainBox.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6);
        mainBox.setRotationPoint(0, 16, 0);
        main.addChild(mainBox);

        ModelRenderer top = new ModelRenderer(this, 0, 0);
        top.addBox(-4.5F, -5.0F, -4.5F, 9, 1, 9);
        top.setRotationPoint(0, 16, 0);
        main.addChild(top);

        ModelRenderer bottom = new ModelRenderer(this, 0, 0);
        bottom.addBox(-4.5F, 4.0F, -4.5F, 9, 1, 9);
        bottom.setRotationPoint(0, 16, 0);
        main.addChild(bottom);

        ModelRenderer pillars = new ModelRenderer(this, 0, 0);
        pillars.addBox(-4.0F, -4.0F, -4.0F, 1, 8, 1);
        pillars.addBox(3.0F, -4.0F, -4.0F, 1, 8, 1);
        pillars.addBox(-4.0F, -4.0F, 3.0F, 1, 8, 1);
        pillars.addBox(3.0F, -4.0F, 3.0F, 1, 8, 1);
        pillars.setRotationPoint(0, 16, 0);
        main.addChild(pillars);
    }

    public void render(TileEntityExtractor te, float size)
    {
        for(int n = 0; n < renderers.size(); n++)
        {
            renderers.get(n).render(size);
        }
    }
}
