package lumaceon.mods.clockworkphase.item.component.memory;

import lumaceon.mods.clockworkphase.item.ItemClockworkPhase;
import lumaceon.mods.clockworkphase.item.component.IBaseComponent;
import net.minecraft.item.ItemStack;

public class ItemRustyGear extends ItemClockworkPhase implements IBaseComponent
{

    @Override
    public boolean isComponentSpeedy(ItemStack is)
    {
        return true;
    }

    @Override
    public boolean isComponentQuality(ItemStack is)
    {
        return true;
    }

    @Override
    public boolean isComponentMemory(ItemStack is)
    {
        return true;
    }

    @Override
    public int getGearQuality(ItemStack is)
    {
        return 5;
    }

    @Override
    public int getGearSpeed(ItemStack is)
    {
        return 10;
    }

    @Override
    public int getMemoryValue(ItemStack is)
    {
        return 70;
    }
}
