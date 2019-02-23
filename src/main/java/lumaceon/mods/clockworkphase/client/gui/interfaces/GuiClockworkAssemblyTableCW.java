package lumaceon.mods.clockworkphase.client.gui.interfaces;

import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.client.gui.components.GuiButtonItem;
import lumaceon.mods.clockworkphase.client.gui.container.ContainerClockworkAssemblyTableCW;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.Textures;
import lumaceon.mods.clockworkphase.network.MessageOpenGui;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiClockworkAssemblyTableCW extends GuiContainer
{
    public ItemStack hourglass = new ItemStack(ModItems.hourglassElements[4]);
    public ItemStack mainspring = new ItemStack(ModItems.mainspring);
    public RenderItem itemRenders;
    public World world;
    public int x, y, z;

    public GuiClockworkAssemblyTableCW(InventoryPlayer ip, World world, int x, int y, int z)
    {
        super(new ContainerClockworkAssemblyTableCW(ip, world));
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xSize = 256;
        this.ySize = 256;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    public void initGui()
    {
        super.initGui();

        itemRenders = mc.getRenderItem();
        buttonList.clear();
        buttonList.add(new GuiButtonItem(hourglass, 0, guiLeft + 24 - 9, guiTop + 211 - 9, "", itemRenders, fontRenderer));
        buttonList.add(new GuiButtonItem(mainspring, 1, guiLeft + 231 - 9, guiTop + 211 - 9, "", itemRenders, fontRenderer));
    }

    @Override
    public void actionPerformed(GuiButton button)
    {
        switch(button.id)
        {
            case 0:
//                mc.player.closeScreen();
                PacketHandler.INSTANCE.sendToServer(new MessageOpenGui(6, x, y, z));
                break;
            case 1:
//                mc.player.closeScreen();
                PacketHandler.INSTANCE.sendToServer(new MessageOpenGui(5, x, y, z));
                break;
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(Textures.ASSEMBLY_TABLE_GUI_CW);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
