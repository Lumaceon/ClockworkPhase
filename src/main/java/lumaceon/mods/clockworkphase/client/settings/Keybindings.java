package lumaceon.mods.clockworkphase.client.settings;

import lumaceon.mods.clockworkphase.lib.KeyLib;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keybindings
{
    public static KeyBinding multitool = new KeyBinding(KeyLib.MULTITOOL, Keyboard.KEY_V, KeyLib.CATEGORY);
    public static KeyBinding temporal = new KeyBinding(KeyLib.TEMPORAL_ABILITY, Keyboard.KEY_R, KeyLib.CATEGORY);
}
