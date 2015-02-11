package lumaceon.mods.clockworkphase.client.gui.interfaces;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.client.gui.container.ContainerExtractor;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.lib.Textures;
import lumaceon.mods.clockworkphase.util.TimeSandParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiExtractor extends GuiContainer
{
    public TileEntityExtractor te;
    public Phases phase;

    public GuiExtractor(TileEntityExtractor te, InventoryPlayer ip, Phases phase)
    {
        super(new ContainerExtractor(te, ip, phase));
        this.xSize = 256;
        this.ySize = 256;
        this.te = te;
        this.phase = phase;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawCenteredString(this.fontRendererObj, TimeSandParser.getStringForRenderingFromTimeSand(te.getTimeSand()), 61, 14 - this.fontRendererObj.FONT_HEIGHT / 2, 16777215);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if(te.getStackInSlot(0) != null && te.getStackInSlot(0).getItem().equals(ModItems.catalystElements[phase.ordinal()]))
        {
            Minecraft.getMinecraft().renderEngine.bindTexture(Textures.EXTRACTOR_GUI_ACTIVE[phase.ordinal()]);
        }
        else
        {
            Minecraft.getMinecraft().renderEngine.bindTexture(Textures.EXTRACTOR_GUI[phase.ordinal()]);
        }
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
