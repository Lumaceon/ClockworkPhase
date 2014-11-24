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

public class ItemHourglassLunar extends ItemHourglass
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

            float efficiency = (float)speed / (float)quality;
            int tensionCost = (int)Math.round(MechanicTweaker.LUNAR_HOURGLASS_TENSION_COST * Math.pow(efficiency, 2));
            if(PhaseHelper.getPhaseForWorld(world).equals(Phases.LUNAR)) { tensionCost *= 0.1; }
            if(world.getWorldTime() % 24000 >= 14000 && world.getWorldTime() % 24000 <= 22000) { tensionCost *= 0.5; }
            int newTension = tension - tensionCost;

            if(newTension <= 0)
            {
                this.removeTension(is, tension);
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

            if(speed < 50)
            {
                NBTHelper.setBoolean(is, NBTTags.ACTIVE, false);
                if(!player.capabilities.isCreativeMode)
                {
                    player.capabilities.allowFlying = false;
                    player.capabilities.isFlying = false;
                }
                player.addChatComponentMessage(new ChatComponentText("Your clockwork's speed is too slow to be of any use."));
                return;
            }

            if(speed > 500) { speed = 500; }
            double x = NBTHelper.getDouble(is, NBTTags.X_MOTION) * (0.99 + (speed / 500) / 100);
            double y = NBTHelper.getDouble(is, NBTTags.Y_MOTION) * (0.99 + (speed / 500) / 100);
            double z = NBTHelper.getDouble(is, NBTTags.Z_MOTION) * (0.99 + (speed / 500) / 100);

            if(player.motionX != x)
            {
                NBTHelper.setDouble(is, NBTTags.X_MOTION, x);
            }
            if(player.motionY != y)
            {
                NBTHelper.setDouble(is, NBTTags.Y_MOTION, y);
            }
            if(player.motionZ != z)
            {
                NBTHelper.setDouble(is, NBTTags.Z_MOTION, z);
            }

            player.motionX = x;
            player.motionY = y;
            player.motionZ = z;
            player.fallDistance = 0;
            this.removeTension(is, tensionCost);
        }
    }

    @Override
    public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int meta, float f1, float f2, float f3)
    {
        boolean isActive = NBTHelper.getBoolean(is, NBTTags.ACTIVE);
        NBTHelper.setBoolean(is, NBTTags.ACTIVE, !isActive);
        NBTHelper.setDouble(is, NBTTags.X_MOTION, player.motionX);
        NBTHelper.setDouble(is, NBTTags.Y_MOTION, player.motionY);
        NBTHelper.setDouble(is, NBTTags.Z_MOTION, player.motionZ);
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

    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
        boolean isActive = NBTHelper.getBoolean(is, NBTTags.ACTIVE);
        NBTHelper.setBoolean(is, NBTTags.ACTIVE, !isActive);
        NBTHelper.setDouble(is, NBTTags.X_MOTION, player.motionX);
        NBTHelper.setDouble(is, NBTTags.Y_MOTION, player.motionY);
        NBTHelper.setDouble(is, NBTTags.Z_MOTION, player.motionZ);
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
