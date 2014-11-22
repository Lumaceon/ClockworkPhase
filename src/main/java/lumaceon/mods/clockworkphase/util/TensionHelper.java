package lumaceon.mods.clockworkphase.util;

import lumaceon.mods.clockworkphase.lib.NBTTags;
import net.minecraft.item.ItemStack;

public class TensionHelper
{
    public static void addTension(ItemStack is, int tension)
    {
        if(NBTHelper.hasTag(is, NBTTags.MAX_TENSION))
        {
            int maxTension = NBTHelper.getInt(is, NBTTags.MAX_TENSION);
            int currentTension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY);

            if(currentTension + tension >= maxTension)
            {
                NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, maxTension);
            }
            else
            {
                NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, currentTension + tension);
            }

            if(maxTension / is.getMaxDamage() == 0)
            {
                is.setItemDamage(is.getMaxDamage());
            }
            else
            {
                is.setItemDamage(is.getMaxDamage() - (currentTension / (maxTension / is.getMaxDamage())));
            }
        }
    }

    public static void removeTension(ItemStack is, int tension)
    {
        int maxTension = NBTHelper.getInt(is, NBTTags.MAX_TENSION);
        int currentTension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY);

        if(currentTension - tension <= 0)
        {
            NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, 0);
        }
        else
        {
            NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, currentTension - tension);
        }

        if(maxTension / is.getMaxDamage() == 0)
        {
            is.setItemDamage(is.getMaxDamage());
        }
        else
        {
            is.setItemDamage(is.getMaxDamage() - (currentTension / (maxTension / is.getMaxDamage())));
        }
    }
}
