package lumaceon.mods.clockworkphase.eventhandlers;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.client.gui.interfaces.GuiMultitool;
import lumaceon.mods.clockworkphase.client.settings.Keybindings;
import lumaceon.mods.clockworkphase.item.ItemTemporalMultitool;
import lumaceon.mods.clockworkphase.lib.GUIs;
import lumaceon.mods.clockworkphase.lib.KeyLib;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class KeyHandler
{
    public static KeyLib.Keys getKeyPressed()
    {
        if(Keybindings.multitool.isPressed())
        {
            return KeyLib.Keys.MULTITOOL;
        }
        else
        {
            return KeyLib.Keys.IRRELEVANT;
        }
    }

    @SubscribeEvent
    public void handleKeyPress(InputEvent.KeyInputEvent event)
    {
        if(getKeyPressed().equals(KeyLib.Keys.MULTITOOL))
        {
            if(!FMLClientHandler.instance().isGUIOpen(GuiMultitool.class))
            {
                EntityPlayer player = Minecraft.getMinecraft().thePlayer;
                if(player.inventory.getCurrentItem() != null && (player.inventory.getCurrentItem().getItem() instanceof ItemTemporalMultitool || NBTHelper.hasTag(player.inventory.getCurrentItem(), NBTTags.TEMPORAL_ITEMS)))
                {
                    player.openGui(ClockworkPhase.instance, GUIs.MULTITOOL_GUI.ordinal(), player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
                }
            }
        }
    }
}
