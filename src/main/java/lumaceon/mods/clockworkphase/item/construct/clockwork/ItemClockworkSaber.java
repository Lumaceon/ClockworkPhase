package lumaceon.mods.clockworkphase.item.construct.clockwork;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.construct.IKeybindAbility;
import lumaceon.mods.clockworkphase.item.construct.ITemporalChange;
import lumaceon.mods.clockworkphase.item.construct.abstracts.IClockwork;
import lumaceon.mods.clockworkphase.item.construct.abstracts.ItemTimeSandClockworkAbstract;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.network.MessageTemporalItemChange;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemClockworkSaber extends ItemTimeSandClockworkAbstract implements IKeybindAbility, ITemporalChange
{
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        list.add("Tension: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.TENSION_ENERGY) + "/" + "\u00a7e" + NBTHelper.getInt(is, NBTTags.MAX_TENSION));
        list.add("Time Sand: " + "\u00A7e" + getTimeSand(is));

        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            int quality = NBTHelper.getInt(is, NBTTags.QUALITY);
            int speed = NBTHelper.getInt(is, NBTTags.SPEED);
            int memory = NBTHelper.getInt(is, NBTTags.MEMORY);
            int memoryWebPower = (int)(memory * Math.pow(player.experienceLevel + 1.0F, 2.0F));
            int chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR;
            if(memoryWebPower > 0)
            {
                chance = MechanicTweaker.TIME_SAND_CHANCE_FACTOR / memoryWebPower;
                if(chance < 1) { chance = 1; }
            }

            list.add("");
            list.add("Clockwork Quality: " + "\u00A7e" + quality);
            list.add("Clockwork Speed: " + "\u00A7e" + speed);
            list.add("Memory: " + "\u00A7e" + memory);
            if(memory > 0)
            {
                if(chance < 1000000)
                {
                    list.add("Chance of Extracting Time Sand: 1 in " + chance);
                }
                else
                {
                    list.add("Chance of Extracting Time Sand: 1 in an eternity.");
                }
            }

            list.add("");
        }
        else
        {
            list.add("-Hold shift for details-");
        }
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
                float efficiency = (float) speed / (float) quality;
                int tensionCost = (int)Math.round(MechanicTweaker.TENSION_PER_HIT * Math.pow(efficiency, 2));
                int newTension = tension - tensionCost;

                if (newTension <= 0)
                {
                    this.removeTension(is, tension);
                    return true;
                }
                entity1.attackEntityFrom(DamageSource.causePlayerDamage(player), speed / 25);
                this.removeTension(is, tensionCost);
            }
        }
        return true;
    }


    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack is, World world, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        return true;
    }

    @Override
    public int getMaxTimeSand()
    {
        return MechanicTweaker.MAX_TIME_SAND_TOOLS;
    }

    @Override
    public void useTemporalAbility()
    {
        PacketHandler.INSTANCE.sendToServer(new MessageTemporalItemChange());
    }

    @Override
    public Item getItemChangeTo()
    {
        return ModItems.temporalClockworkSaber;
    }
}
