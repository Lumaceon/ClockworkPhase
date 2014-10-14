package lumaceon.mods.clockworkphase.eventhandlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.component.memory.ItemMemento;
import lumaceon.mods.clockworkphase.item.component.memory.MemoryItemRegistry;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EntityHandler
{
    @SubscribeEvent
    public void onEntityItemDrop(LivingDropsEvent event)
    {
        if(event.entityLiving.worldObj != null)
        {
            if(event.entityLiving.worldObj.rand.nextInt(200) == 0)
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
                    event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(MemoryItemRegistry.memoryItemDrops.get(event.entity.worldObj.rand.nextInt(MemoryItemRegistry.memoryItemDrops.size() - 1)))));
                }
            }
        }
    }
}
