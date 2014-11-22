package lumaceon.mods.clockworkphase.util;

import net.minecraft.item.ItemStack;

public class OverlayRenderData
{
    public ItemStack item;
    public int numberToRender;

    public OverlayRenderData(ItemStack item, int numberToRender)
    {
        this.item = item;
        this.numberToRender = numberToRender;
    }

    public void setItem(ItemStack is)
    {
        this.item = is;
    }

    public void setNumberToRender(int numberToRender)
    {
        this.numberToRender = numberToRender;
    }
}
