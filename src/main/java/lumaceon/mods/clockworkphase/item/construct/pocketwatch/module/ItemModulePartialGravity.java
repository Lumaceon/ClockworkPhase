package lumaceon.mods.clockworkphase.item.construct.pocketwatch.module;

import lumaceon.mods.clockworkphase.extendeddata.PlayerCapability;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.TimeSandHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
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

            if(!player.isPotionActive(MobEffects.JUMP_BOOST))
            {
                player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 100, 5));
            }

            PlayerCapability.IPlayerHandler cap = player.getCapability(PlayerCapability.CAPABILITY_PLAYER, null);
            if(player.motionY < 0)
            {
                player.motionY -= (player.motionY - cap.getPrevMotion().getY()) / 1.2;
            }
            cap.setPrevMotion(new BlockPos(player.motionX, player.motionY, player.motionZ));
        }
        else if(entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entity;
            PlayerCapability.IPlayerHandler cap = player.getCapability(PlayerCapability.CAPABILITY_PLAYER, null);
            cap.setPrevMotion(new BlockPos(0, 0, 0));
        }
    }


}
