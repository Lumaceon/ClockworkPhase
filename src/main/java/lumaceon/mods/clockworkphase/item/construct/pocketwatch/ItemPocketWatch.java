package lumaceon.mods.clockworkphase.item.construct.pocketwatch;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.client.handler.KeyHandler;
import lumaceon.mods.clockworkphase.item.ItemClockworkPhaseGeneric;
import lumaceon.mods.clockworkphase.item.construct.IKeybindAbility;
import lumaceon.mods.clockworkphase.item.construct.pocketwatch.module.ItemModule;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPocketWatch extends ItemClockworkPhaseGeneric implements IKeybindAbility
{
    @Override
    public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        if(NBTHelper.hasTag(is, NBTTags.POCKET_WATCH_MODULES))
        {
            ItemStack[] items = NBTHelper.getInventoryFromNBTTag(is, NBTTags.POCKET_WATCH_MODULES);
            for(int n = 0; n < items.length; n++)
            {
                if(items[n] != null)
                {
                    items[n].getItem().onUpdate(items[n], world, entity, p_77663_4_, p_77663_5_);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void useTemporalAbility()
    {
        KeyHandler.handlePocketWatchAbility();
    }

    public static boolean doesActiveItemModuleExist(ItemStack[] pocketWatches, ItemModule module)
    {
        ItemStack is = getItemModuleFromMultiple(pocketWatches, module);
        return is != null && NBTHelper.getBoolean(is, NBTTags.ACTIVE);
    }

    public static boolean doesItemModuleExist(ItemStack[] pocketWatches, ItemModule module)
    {
        return getItemModuleFromMultiple(pocketWatches, module) != null;
    }

    public static ItemStack getItemModuleFromMultiple(ItemStack[] pocketWatches, ItemModule module)
    {
        ItemStack result = null;
        for(int n = 0; n < pocketWatches.length && result == null; n++)
        {
            result = getItemModuleIfExists(pocketWatches[n], module);
        }
        return result;
    }

    /**
     * Checks the itemstack given (which should always be a pocket watch) for the give module.
     *
     * @param pocketWatch The pocket watch to check for the module.
     * @param module The module to check for.
     * @return The itemstack of the first valid module to occur.
     */
    public static ItemStack getItemModuleIfExists(ItemStack pocketWatch, ItemModule module)
    {
        ItemStack result = null;
        if(NBTHelper.hasTag(pocketWatch, NBTTags.POCKET_WATCH_MODULES))
        {
            ItemStack[] items = NBTHelper.getInventoryFromNBTTag(pocketWatch, NBTTags.POCKET_WATCH_MODULES);
            for(int n = 0; n < items.length && result == null; n++)
            {
                if(items[n].getItem().equals(module))
                {
                    result = items[n];
                }
            }
        }
        return result;
    }
}
