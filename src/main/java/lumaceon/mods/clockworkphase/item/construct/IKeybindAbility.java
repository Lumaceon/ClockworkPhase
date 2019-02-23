package lumaceon.mods.clockworkphase.item.construct;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IKeybindAbility
{
    @SideOnly(Side.CLIENT)
    public void useTemporalAbility();
}
