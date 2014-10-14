package lumaceon.mods.clockworkphase.item.component.memory;

import lumaceon.mods.clockworkphase.item.ItemClockworkPhase;
import lumaceon.mods.clockworkphase.item.component.IBaseComponent;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.item.ItemStack;

public class ItemMemento extends ItemClockworkPhase implements IBaseComponent
{
    public ItemMemento()
    {
        this.setMaxStackSize(1);
    }

    @Override
    public boolean isComponentSpeedy(ItemStack is)
    {
        return false;
    }

    @Override
    public boolean isComponentQuality(ItemStack is)
    {
        return false;
    }

    @Override
    public boolean isComponentMemory(ItemStack is)
    {
        return true;
    }

    @Override
    public int getGearQuality(ItemStack is)
    {
        return 0;
    }

    @Override
    public int getGearSpeed(ItemStack is)
    {
        return 0;
    }

    @Override
    public int getMemoryValue(ItemStack is)
    {
        return NBTHelper.getInt(is, NBTTags.MEMORY);
    }
}
