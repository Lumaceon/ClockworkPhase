package lumaceon.mods.clockworkphase.item.component.generic;

import lumaceon.mods.clockworkphase.item.ItemClockworkPhaseGeneric;

public abstract class ItemBaseComponentGeneric extends ItemClockworkPhaseGeneric implements IBaseComponent
{
    public ItemBaseComponentGeneric()
    {
        super();
        this.setMaxStackSize(64);
    }
}
