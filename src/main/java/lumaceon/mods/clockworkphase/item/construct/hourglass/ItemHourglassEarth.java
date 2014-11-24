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

public class ItemHourglassEarth extends ItemHourglass
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
            int tensionCost = (int)Math.round(MechanicTweaker.EARTH_HOURGLASS_TENSION_COST * Math.pow(efficiency, 2));
            if(PhaseHelper.getPhaseForWorld(world).equals(Phases.EARTH)) { tensionCost *= 0.1; }
            int newTension = tension - tensionCost;

            if(newTension <= 0)
            {
                this.removeTension(is, tension);
                NBTHelper.setBoolean(is, NBTTags.ACTIVE, false);
                return;
            }

            if(efficiency > 10)
            {
                NBTHelper.setBoolean(is, NBTTags.ACTIVE, false);
                player.addChatComponentMessage(new ChatComponentText("Your clockwork's quality can't handle it's speed."));
                return;
            }

            if(speed > 10)
            {
                player.fallDistance = 0;
                int targetY = NBTHelper.getInt(is, NBTTags.Y_LEVEL);
                double playersY = player.posY;
                if(world.isRemote) { playersY -= 1.62000000476837; }

                if(targetY - playersY > 0 && targetY - playersY < 0.2)
                {
                    player.posY = targetY;
                }

                if(targetY >= playersY)
                {
                    player.onGround = true;
                    player.isAirBorne = false;
                    player.setJumping(false);
                    if(player.motionY < 0)
                    {
                        player.motionY = 0;
                    }
                    this.removeTension(is, tensionCost);
                }
            }
        }
    }

    @Override
    public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int meta, float f1, float f2, float f3)
    {
        boolean isActive = NBTHelper.getBoolean(is, NBTTags.ACTIVE);
        NBTHelper.setBoolean(is, NBTTags.ACTIVE, !isActive);
        if(!isActive) //Being turned on
        {
            NBTHelper.setInteger(is, NBTTags.Y_LEVEL, (int)Math.floor(player.posY + 0.5));
            if(!world.isRemote)
            {
                player.capabilities.allowFlying = true;
            }
        }
        else if(!player.capabilities.isCreativeMode)
        {
            player.capabilities.isFlying = false;
            player.capabilities.allowFlying = false;
        }
        return true;
    }

    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
        boolean isActive = NBTHelper.getBoolean(is, NBTTags.ACTIVE);
        NBTHelper.setBoolean(is, NBTTags.ACTIVE, !isActive);
        if(!isActive) //Being turned on
        {
            NBTHelper.setInteger(is, NBTTags.Y_LEVEL, (int)Math.floor(player.posY + 0.5));
            if(!world.isRemote)
            {
                player.capabilities.allowFlying = true;
            }
        }
        else if(!player.capabilities.isCreativeMode)
        {
            player.capabilities.isFlying = false;
            player.capabilities.allowFlying = false;
        }
        return is;
    }
}
