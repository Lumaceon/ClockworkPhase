package lumaceon.mods.clockworkphase.item.construct.clockwork;

import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemClockworkSaber extends ItemClockworkConstruct
{
    public ItemClockworkSaber()
    {
        super();
    }

    @Override
    public boolean hitEntity(ItemStack is, EntityLivingBase entity1, EntityLivingBase entity2)
    {
        if(is.getItem() instanceof IClockwork)
        {
            if(entity2 instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer)entity2;
                int tension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY);
                int quality = NBTHelper.getInt(is, NBTTags.QUALITY);
                if (quality <= 0) {return false;}
                int speed = NBTHelper.getInt(is, NBTTags.SPEED);
                int memory = NBTHelper.getInt(is, NBTTags.MEMORY);
                float efficiency = (float) speed / (float) quality;
                int tensionCost = (int)Math.round(MechanicTweaker.TENSION_PER_HIT * Math.pow(efficiency, 2));
                int newTension = tension - tensionCost;

                if (newTension <= 0)
                {
                    this.removeTension(is, tension);
                    return true;
                }
                entity1.attackEntityFrom(DamageSource.causePlayerDamage(player), speed / 20);
                this.removeTension(is, tensionCost);
            }
        }
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack is, World world, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        return true;
    }
}
