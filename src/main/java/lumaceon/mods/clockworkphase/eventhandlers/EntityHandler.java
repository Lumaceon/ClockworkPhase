package lumaceon.mods.clockworkphase.eventhandlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.component.ItemMemoryCore;
import lumaceon.mods.clockworkphase.item.construct.mix.hourglass.ItemHourglassDeath;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.Logger;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EntityHandler
{
    @SubscribeEvent
    public void onEntityAttacked(LivingAttackEvent event)
    {
        if(event.source.getSourceOfDamage() instanceof EntityPlayer)
        {
            if(event.entityLiving.getHealth() - event.ammount <= 0)
            {
                EntityPlayer player = (EntityPlayer)event.source.getEntity();
                boolean found = false;
                int index = 0;
                ItemStack item;

                for(int n = 0; n < player.inventory.getSizeInventory() && !found; n++)
                {
                    item = player.inventory.getStackInSlot(n);
                    if(item != null && item.getItem() instanceof ItemMemoryCore)
                    {
                        index = n;
                        found = true;
                    }
                }

                if(found)
                {
                    ItemStack is = player.inventory.getStackInSlot(index);
                    int resultingMemory = NBTHelper.getInt(is, NBTTags.MEMORY) + MechanicTweaker.MEMORY_PER_KILL;

                    if(resultingMemory > MechanicTweaker.MAX_MEMORY_MEMCORE)
                    {
                        resultingMemory = MechanicTweaker.MAX_MEMORY_MEMCORE;
                    }

                    NBTHelper.setInteger(is, NBTTags.MEMORY, resultingMemory);
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityItemDrop(LivingDropsEvent event)
    {
        if(event.entityLiving.worldObj != null)
        {
            if(event.entityLiving.worldObj.rand.nextInt(200) == 0)
            {
                event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(ModItems.memoryCore)));
            }
        }
    }
}
