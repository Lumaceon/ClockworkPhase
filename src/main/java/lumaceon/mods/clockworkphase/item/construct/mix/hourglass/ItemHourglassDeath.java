package lumaceon.mods.clockworkphase.item.construct.mix.hourglass;

import lumaceon.mods.clockworkphase.item.construct.clockwork.IClockwork;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.util.Logger;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.PhaseHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHourglassDeath extends ItemHourglass
{
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

    public void applyHourglassEffects(ItemStack is, EntityLivingBase entityAttacked, EntityPlayer player)
    {
        int tension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY);
        int quality = NBTHelper.getInt(is, NBTTags.QUALITY); if(quality <= 0) {return;}
        int speed = NBTHelper.getInt(is, NBTTags.SPEED);
        int memory = NBTHelper.getInt(is, NBTTags.MEMORY);

        float efficiency = (float)speed / (float)quality;
        int tensionCost = (int)Math.round(MechanicTweaker.DEATH_HOURGLASS_TENSION_COST * Math.pow(efficiency, 2));
        if(PhaseHelper.getPhaseForWorld(player.worldObj).equals(Phases.DEATH)) { tensionCost *= 0.1; }
        int newTension = tension - tensionCost;

        if(newTension <= 0)
        {
            this.removeTension(is, tension);
            NBTHelper.setBoolean(is, NBTTags.ACTIVE, false);
            return;
        }

        if(speed > 10)
        {
            entityAttacked.addPotionEffect(new PotionEffect(Potion.wither.getId(), 50, speed / 150, false));
            ((IClockwork)is.getItem()).removeTension(is, tensionCost);
        }
    }
}
