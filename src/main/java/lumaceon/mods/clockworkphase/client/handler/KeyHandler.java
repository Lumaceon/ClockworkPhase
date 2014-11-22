package lumaceon.mods.clockworkphase.client.handler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.client.gui.interfaces.GuiMultitool;
import lumaceon.mods.clockworkphase.client.gui.interfaces.GuiPocketWatch;
import lumaceon.mods.clockworkphase.client.settings.Keybindings;
import lumaceon.mods.clockworkphase.item.ItemTemporalMultitool;
import lumaceon.mods.clockworkphase.item.construct.IKeybindAbility;
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
        else if(Keybindings.temporal.isPressed())
        {
            return KeyLib.Keys.TEMPORAL_ABILITY;
        }
        else
        {
            return KeyLib.Keys.IRRELEVANT;
        }
    }

    @SubscribeEvent
    public void handleKeyPress(InputEvent.KeyInputEvent event)
    {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        KeyLib.Keys keyPressed = getKeyPressed();
        if(keyPressed.equals(KeyLib.Keys.MULTITOOL))
        {
            if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ItemTemporalMultitool)
            {
                ((IKeybindAbility)player.inventory.getCurrentItem().getItem()).useTemporalAbility();
            }

            if(player.inventory.getCurrentItem() != null && NBTHelper.hasTag(player.inventory.getCurrentItem(), NBTTags.TEMPORAL_ITEMS))
            {
                handleMultitoolAbility();
            }
        }
        else if(keyPressed.equals(KeyLib.Keys.TEMPORAL_ABILITY))
        {
            if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof IKeybindAbility)
            {
                ((IKeybindAbility)player.inventory.getCurrentItem().getItem()).useTemporalAbility();
            }
        }
    }

    public static void handleMultitoolAbility()
    {
        if(!FMLClientHandler.instance().isGUIOpen(GuiMultitool.class))
        {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            player.openGui(ClockworkPhase.instance, GUIs.MULTITOOL_GUI.ordinal(), player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
        }
    }

    public static void handlePocketWatchAbility()
    {
        if(!FMLClientHandler.instance().isGUIOpen(GuiPocketWatch.class))
        {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            player.openGui(ClockworkPhase.instance, GUIs.POCKET_WATCH_GUI.ordinal(), player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
        }
    }
}
