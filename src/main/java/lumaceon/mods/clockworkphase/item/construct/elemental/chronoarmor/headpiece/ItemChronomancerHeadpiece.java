package lumaceon.mods.clockworkphase.item.construct.elemental.chronoarmor.headpiece;

import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.construct.elemental.ItemElemental;
import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class ItemChronomancerHeadpiece extends ItemElemental
{
    public void elementize(Phases phase, EntityItem item)
    {
        super.elementize(phase, item);
        int id = phase.ordinal();
        if(!item.getEntityItem().getItem().equals(ModItems.chronomancerHeadpieceElements[id]))
        {
            ItemStack newItem = new ItemStack(ModItems.chronomancerHeadpieceElements[id]);
            newItem.setTagCompound(item.getEntityItem().stackTagCompound);
            newItem.setItemDamage(item.getEntityItem().getItemDamage());
            item.setEntityItemStack(newItem);
        }
    }

    public void unelementize(EntityItem item)
    {
        super.unelementize(item);
        if(!item.getEntityItem().getItem().equals(ModItems.chronomancerHeadpiece))
        {
            ItemStack newItem = new ItemStack(ModItems.chronomancerHeadpiece);
            newItem.setTagCompound(item.getEntityItem().stackTagCompound);
            newItem.setItemDamage(item.getEntityItem().getItemDamage());
            item.setEntityItemStack(newItem);
        }
    }
}
