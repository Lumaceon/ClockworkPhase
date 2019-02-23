package lumaceon.mods.clockworkphase.client.handler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import lumaceon.mods.clockworkphase.util.OverlayRenderData;
import lumaceon.mods.clockworkphase.lib.Textures;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class RenderHandler
{
    public static RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

    public RenderHandler()
    {
//        renderItem.renderWithColor = false;
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event)
    {
        if(event.getType().equals(RenderGameOverlayEvent.ElementType.FOOD)) //Render after food bar (no particular reason).
        {
            for(int n = 0; n < ClientProxy.overlayRenderers.size(); n++)
            {
                OverlayRenderData renderer = ClientProxy.overlayRenderers.get(n);
                if(!renderer.item.isEmpty())
                {
                    renderItem.renderItemIntoGUI(renderer.item, 2 + (n % 10) * 18, 2 + (n / 10) * 18);
                    renderItem.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRenderer, renderer.item, 2 + (n % 10) * 18, 2 + (n / 10) * 18, null);
                    GL11.glDisable(GL11.GL_LIGHTING);

                    if(renderer.numberToRender > 0)
                    {
                        Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(renderer.numberToRender), (2 + (n % 10) * 18) + 9 - Minecraft.getMinecraft().fontRenderer.getStringWidth(String.valueOf(renderer.numberToRender)) / 2, (2 + (n / 10) * 18) + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT / 2, 16777215);
                        GL11.glDisable(GL11.GL_LIGHTING);
                    }
                }
            }
            Minecraft.getMinecraft().renderEngine.bindTexture(Textures.VANILLA_ICONS);
        }
    }
}
