package lumaceon.mods.clockworkphase.item.component;

import lumaceon.mods.clockworkphase.item.ItemClockworkPhase;
import net.minecraft.item.ItemStack;

public class ItemOilBucket extends ItemClockworkPhase implements IBaseComponent
{
    @Override
    public boolean isComponentSpeedy(ItemStack is)
    {
        return true;
    }

    @Override
    public boolean isComponentQuality(ItemStack is)
    {
        return false;
    }

    @Override
    public boolean isComponentMemory(ItemStack is)
    {
        return false;
    }

    @Override
    public int getGearQuality(ItemStack is)
    {
        return 0;
    }

    @Override
    public int getGearSpeed(ItemStack is)
    {
        return 85;
    }

    @Override
    public int getMemoryValue(ItemStack is)
    {
        return 0;
    }
}
