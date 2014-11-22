package lumaceon.mods.clockworkphase.item.construct.hourglass;

import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.PhaseHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemHourglassFire extends ItemHourglass
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
            int tensionCost = (int)Math.round(MechanicTweaker.FIRE_HOURGLASS_TENSION_COST * Math.pow(efficiency, 2));
            if(PhaseHelper.getPhaseForWorld(world).equals(Phases.FIRE)) { tensionCost *= 0.1; }
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
                player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 10, speed / 150, false));
                if(player.isBurning())
                {
                    player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 10, speed / 150, false));
                    player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 10, 0, false));
                    player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 10, speed / 200, false));
                }
                this.removeTension(is, tensionCost);
            }
        }
    }

    @Override
    public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int meta, float f1, float f2, float f3)
    {
        boolean isActive = NBTHelper.getBoolean(is, NBTTags.ACTIVE);
        NBTHelper.setBoolean(is, NBTTags.ACTIVE, !isActive);
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
        boolean isActive = NBTHelper.getBoolean(is, NBTTags.ACTIVE);
        NBTHelper.setBoolean(is, NBTTags.ACTIVE, !isActive);
        return is;
    }
}
