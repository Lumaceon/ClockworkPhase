package lumaceon.mods.clockworkphase.item.construct;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IKeybindAbility
{
    @SideOnly(Side.CLIENT)
    public void useTemporalAbility();
}
