package lumaceon.mods.clockworkphase.client.gui.interfaces;

import lumaceon.mods.clockworkphase.client.gui.components.GuiButtonItem;
import lumaceon.mods.clockworkphase.client.gui.container.ContainerClockworkAssemblyTableAssemble;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.Textures;
import lumaceon.mods.clockworkphase.network.MessageOpenGui;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiClockworkAssemblyTableAssemble extends GuiContainer
{
    public ItemStack mainspring = new ItemStack(ModItems.mainspring);
    public ItemStack clockwork = new ItemStack(ModItems.clockwork);
    public RenderItem itemRenders;
    public World world;
    public int x, y, z;

    public GuiClockworkAssemblyTableAssemble(InventoryPlayer ip, World world, int x, int y, int z)
    {
        super(new ContainerClockworkAssemblyTableAssemble(ip, world));
        itemRenders = new RenderItem();
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xSize = 256;
        this.ySize = 256;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        buttonList.clear();
        buttonList.add(new GuiButtonItem(mainspring, 0, guiLeft + 24 - 9, guiTop + 211 - 9, "", itemRenders, fontRendererObj));
        buttonList.add(new GuiButtonItem(clockwork, 1, guiLeft + 231 - 9, guiTop + 211 - 9, "", itemRenders, fontRendererObj));
    }

    @Override
    public void actionPerformed(GuiButton button)
    {
        switch(button.id)
        {
            case 0:
                mc.thePlayer.closeScreen();
                PacketHandler.INSTANCE.sendToServer(new MessageOpenGui(5, x, y, z));
                break;
            case 1:
                mc.thePlayer.closeScreen();
                PacketHandler.INSTANCE.sendToServer(new MessageOpenGui(4, x, y, z));
                break;
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(Textures.ASSEMBLY_TABLE_GUI_ASSEMBLE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
