package lumaceon.mods.clockworkphase.client.gui.interfaces;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeWell;
import lumaceon.mods.clockworkphase.client.gui.container.ContainerTimeWell;
import lumaceon.mods.clockworkphase.lib.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiTimeWell extends GuiContainer
{
    private TileEntityTimeWell timeWell;

    public GuiTimeWell(TileEntityTimeWell te, InventoryPlayer ip)
    {
        super(new ContainerTimeWell(te, ip));
        this.xSize = 256;
        this.ySize = 256;
        this.timeWell = te;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawCenteredString(this.fontRendererObj, String.valueOf(timeWell.getTimeSand()), 61, 14 - this.fontRendererObj.FONT_HEIGHT / 2, 16777215);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(Textures.TIME_WELL_GUI);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
