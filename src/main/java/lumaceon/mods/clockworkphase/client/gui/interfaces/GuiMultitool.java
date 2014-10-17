package lumaceon.mods.clockworkphase.client.gui.interfaces;

import lumaceon.mods.clockworkphase.client.gui.components.GuiButtonItem;
import lumaceon.mods.clockworkphase.client.gui.container.ContainerMultitool;
import lumaceon.mods.clockworkphase.network.MessageMultitoolGui;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class GuiMultitool extends GuiContainer
{
    public ItemStack[] items;
    public RenderItem[] itemRenders;

    public GuiMultitool(ItemStack[] itemStacks)
    {
        super(new ContainerMultitool());
        itemRenders = new RenderItem[20];
        for(int n = 0; n < 20; n++)
        {
            itemRenders[n] = new RenderItem();
        }

        if(itemStacks == null)
        {
            itemStacks = new ItemStack[0];
        }
        items = itemStacks;
        this.xSize = 300;
        this.ySize = 60;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        buttonList.clear();
        int index = 0;
        for(int x = 0; x < 10; x++)
        {
            for(int y = 0; y < 2; y++)
            {
                if(items.length > index && items[index] != null)
                {
                    buttonList.add(new GuiButtonItem(items[index], index, guiLeft + (x % 10) * 30, guiTop + y * 30, "", itemRenders[index], fontRendererObj));
                }
                else
                {
                    buttonList.add(new GuiButtonItem(null, index, guiLeft + (x % 10) * 30, guiTop + y * 30, "", itemRenders[index], fontRendererObj));
                }
                index++;
            }
        }
    }

    @Override
    public void actionPerformed(GuiButton button)
    {
        PacketHandler.INSTANCE.sendToServer(new MessageMultitoolGui(button.id));
        mc.thePlayer.closeScreen();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
