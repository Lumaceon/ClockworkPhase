package lumaceon.mods.clockworkphase.client.render;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.client.render.model.ModelExtractor;
import lumaceon.mods.clockworkphase.lib.Textures;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RenderExtractor extends TileEntitySpecialRenderer
{
    public ModelExtractor model;

    public RenderExtractor()
    {
        model = new ModelExtractor();
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_147500_8_)
    {
        GL11.glPushMatrix();
        this.bindTexture(Textures.EXTRACTOR);
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
        model.render((TileEntityExtractor)te, 0.0625F);
        GL11.glPopMatrix();
    }
}
