package lumaceon.mods.clockworkphase.item.elemental.hourglass;

import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.elemental.ItemElemental;
import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class ItemHourglass extends ItemElemental
{
    public void elementize(Phases phase, EntityItem item)
    {
        super.elementize(phase, item);
        int id = phase.ordinal();
        if(!item.getEntityItem().getItem().equals(ModItems.hourglassElements[id]))
        {
            item.setEntityItemStack(new ItemStack(ModItems.hourglassElements[id]));
        }
    }

    public void unelementize(EntityItem item)
    {
        super.unelementize(item);
        if(!item.getEntityItem().getItem().equals(ModItems.hourglass))
        {
            item.setEntityItemStack(new ItemStack(ModItems.hourglass));
        }
    }
}