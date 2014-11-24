package lumaceon.mods.clockworkphase.item.construct.hourglass;

import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.PhaseHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemHourglassAir extends ItemHourglass
{
    @Override
    public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        if(entity instanceof EntityPlayer && NBTHelper.getBoolean(is, NBTTags.ACTIVE))
        {
            EntityPlayer player = (EntityPlayer)entity;
            int tension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY);
            int quality = NBTHelper.getInt(is, NBTTags.QUALITY); if(quality <= 0) {return;}
            int speed = NBTHelper.getInt(is, NBTTags.SPEED);
            int memory = NBTHelper.getInt(is, NBTTags.MEMORY);

            if(player.isInWater())
            {
                NBTHelper.setBoolean(is, NBTTags.ACTIVE, false);
                if(!player.capabilities.isCreativeMode)
                {
                    player.capabilities.allowFlying = false;
                    player.capabilities.isFlying = false;
                }
                return;
            }

            float efficiency = (float)speed / (float)quality;
            int tensionCost = (int)Math.round(MechanicTweaker.AIR_HOURGLASS_TENSION_COST * Math.pow(efficiency, 2));
            if(PhaseHelper.getPhaseForWorld(world).equals(Phases.AIR)) { tensionCost *= 0.1; }
            int newTension = tension - tensionCost;

            if(newTension <= 0)
            {
                NBTHelper.setBoolean(is, NBTTags.ACTIVE, false);
                if(!player.capabilities.isCreativeMode)
                {
                    player.capabilities.allowFlying = false;
                    player.capabilities.isFlying = false;
                }
                return;
            }

            if(efficiency > 10)
            {
                NBTHelper.setBoolean(is, NBTTags.ACTIVE, false);
                if(!player.capabilities.isCreativeMode)
                {
                    player.capabilities.allowFlying = false;
                    player.capabilities.isFlying = false;
                }
                player.addChatComponentMessage(new ChatComponentText("Your clockwork's quality can't handle it's speed."));
                return;
            }

            if(speed > 10)
            {
                player.motionX = player.getLookVec().xCoord * ((float)speed / 200.0F);
                player.motionY = player.getLookVec().yCoord * ((float)speed / 200.0F);
                player.motionZ = player.getLookVec().zCoord * ((float)speed / 200.0F);
                player.fallDistance = 0;
                this.removeTension(is, tensionCost);
            }
            else
            {
                NBTHelper.setBoolean(is, NBTTags.ACTIVE, false);
                if(!player.capabilities.isCreativeMode)
                {
                    player.capabilities.allowFlying = false;
                    player.capabilities.isFlying = false;
                }
            }
        }
    }

    @Override
    public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int meta, float f1, float f2, float f3)
    {
        boolean isActive = NBTHelper.getBoolean(is, NBTTags.ACTIVE);
        NBTHelper.setBoolean(is, NBTTags.ACTIVE, !isActive);
        if(isActive && !player.capabilities.isCreativeMode)
        {
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
        }
        else
        {
            player.capabilities.allowFlying = true;
        }
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
        boolean isActive = NBTHelper.getBoolean(is, NBTTags.ACTIVE);
        NBTHelper.setBoolean(is, NBTTags.ACTIVE, !isActive);
        if(isActive && !player.capabilities.isCreativeMode)
        {
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
        }
        else
        {
            player.capabilities.allowFlying = true;
        }
        return is;
    }
}
