package lumaceon.mods.clockworkphase.item;

import net.minecraft.item.ItemStack;

public interface ITension
{
    public void addTension(ItemStack is, int tension);

    public void removeTension(ItemStack is, int tension);
}
