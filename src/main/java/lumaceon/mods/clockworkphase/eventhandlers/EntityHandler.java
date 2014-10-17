package lumaceon.mods.clockworkphase.eventhandlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.component.memory.MemoryItemRegistry;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EntityHandler
{
    @SubscribeEvent
    public void onEntityItemDrop(LivingDropsEvent event)
    {
        if(event.entityLiving.worldObj != null)
        {
            if(event.entityLiving.worldObj.rand.nextInt(2) == 0)
            {
                if(MemoryItemRegistry.memoryItemDrops.size() == 1)
                {
                    event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(MemoryItemRegistry.memoryItemDrops.get(0))));
                }
                else if(MemoryItemRegistry.memoryItemDrops.size() <= 0)
                {
                    return;
                }
                else
                {
                    event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(MemoryItemRegistry.memoryItemDrops.get(event.entity.worldObj.rand.nextInt(MemoryItemRegistry.memoryItemDrops.size())))));
                }
            }

            if(event.entityLiving.worldObj.rand.nextInt(2000) == 0)
            {
                event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(ModItems.gearChronosphere)));
            }
        }
    }

    @SubscribeEvent
    public void onItemDestroyed(PlayerDestroyItemEvent event)
    {
        ItemStack is = event.original;
        if(NBTHelper.hasTag(is, NBTTags.TEMPORAL_ITEMS))
        {
            ItemStack[] items = NBTHelper.getInventoryFromNBTTag(is, NBTTags.TEMPORAL_ITEMS);
            ItemStack[] newItems = new ItemStack[items.length - 1];
            ItemStack newItem = items[items.length - 1];
            int amountInNew = 0;

            for(int n = 0; n < newItems.length; n++)
            {
                if(items[n] != null)
                {
                    newItems[n] = items[amountInNew];
                    amountInNew++;
                }
            }
            NBTHelper.setNBTTagListFromInventory(newItem, NBTTags.TEMPORAL_ITEMS, newItems);
            if(event.entityPlayer.inventory.getCurrentItem() == null)
            {
                event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, newItem);
            }
            else if(event.entityPlayer.inventory.addItemStackToInventory(newItem))
            {
                return;
            }
            else
            {
                event.entityPlayer.worldObj.spawnEntityInWorld(new EntityItem(event.entityPlayer.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, newItem));
            }
        }
    }

}
