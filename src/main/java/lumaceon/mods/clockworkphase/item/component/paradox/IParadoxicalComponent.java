package lumaceon.mods.clockworkphase.item.component.paradox;

import net.minecraft.item.ItemStack;

public interface IParadoxicalComponent
{
    public int getParadoxSignificance(ItemStack is);

    public int getParadoxPower(ItemStack is);
}
