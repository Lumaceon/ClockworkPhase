package lumaceon.mods.clockworkphase.item.construct.pocketwatch.module;

import lumaceon.mods.clockworkphase.extendeddata.ExtendedPlayerProperties;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.TimeSandHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemModulePartialGravity extends ItemModule
{
    @Override
    public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        if(NBTHelper.getBoolean(is, NBTTags.ACTIVE) && entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entity;
            int timeSandRequirement = MechanicTweaker.TIME_SAND_COST_PART_GRAVITY_MODULE;
            timeSandRequirement -= TimeSandHelper.removeTimeSandFromInventory(player.inventory, timeSandRequirement);
            if(timeSandRequirement > 0)
            {
                return;
            }

            player.fallDistance = 0;

            if(!player.isPotionActive(Potion.jump))
            {
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 100, 5));
            }

            ExtendedPlayerProperties properties = ExtendedPlayerProperties.get(player);
            if(player.motionY < 0)
            {
                player.motionY -= (player.motionY - properties.prevMotionY) / 1.2;
            }
            properties.prevMotionX = player.motionX;
            properties.prevMotionY = player.motionY;
            properties.prevMotionZ = player.motionZ;
        }
        else if(entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entity;
            ExtendedPlayerProperties properties = ExtendedPlayerProperties.get(player);
            properties.prevMotionX = properties.prevMotionY = properties.prevMotionZ = 0;
        }
    }


}
