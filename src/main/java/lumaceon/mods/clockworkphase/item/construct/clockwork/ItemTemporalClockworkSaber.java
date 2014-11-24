package lumaceon.mods.clockworkphase.item.construct.clockwork;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.construct.abstracts.IClockwork;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.network.MessageTemporalItemChange;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemTemporalClockworkSaber extends ItemClockworkSaber
{
    public ItemTemporalClockworkSaber()
    {
        super();
        this.setCreativeTab(null);
    }

    @Override
    public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        if(entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entity;
            int timeSand = getTimeSand(is);
            timeSand += getTimeSandFromInventory(player.inventory);
            if(timeSand < MechanicTweaker.TIME_SAND_PER_ENTITY_HIT && player.inventory.getStackInSlot(player.inventory.currentItem) != null)
            {
                if(player.inventory.getStackInSlot(player.inventory.currentItem).equals(is))
                {
                    ItemStack newItem = new ItemStack(this.getItemChangeTo());
                    newItem.setTagCompound(is.stackTagCompound);
                    newItem.setItemDamage(is.getItemDamage());
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, newItem);
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        list.add("Tension: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.TENSION_ENERGY) + "/" + "\u00a7e" + NBTHelper.getInt(is, NBTTags.MAX_TENSION));
        list.add("Time Sand: " + "\u00A7e" + getTimeSand(is));

        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            list.add("");
            list.add("Clockwork Quality: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.QUALITY) + " " + "\u00A7b" + "x 1.5");
            list.add("Clockwork Speed: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.SPEED) + " " + "\u00A7b" + "x 1.5");
            list.add("Memory: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.MEMORY));
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
                entity1.attackEntityFrom(DamageSource.causePlayerDamage(player), (speed * 1.5F) / 25.0F);
                this.removeTension(is, tensionCost);

                //Time sand handling
                int amountToRemove = MechanicTweaker.TIME_SAND_PER_ENTITY_HIT;
                amountToRemove -= this.removeTimeSandFromInventory(player.inventory, amountToRemove);
                amountToRemove -= this.removeTimeSand(is, amountToRemove);
                if(amountToRemove > 0) //Change item back to non-temporal if the player can't meet the TS requirements
                {
                    ItemStack newItem = new ItemStack(this.getItemChangeTo());
                    newItem.setTagCompound(is.stackTagCompound);
                    newItem.setItemDamage(is.getItemDamage());
                    if(player.inventory.getStackInSlot(player.inventory.currentItem).equals(is))
                    {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, newItem);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void disassemble(World world, double x, double y, double z, ItemStack is)
    {
        //NOOP
    }

    @Override
    public void useTemporalAbility()
    {
        PacketHandler.INSTANCE.sendToServer(new MessageTemporalItemChange());
    }

    @Override
    public Item getItemChangeTo()
    {
        return ModItems.clockworkSaber;
    }
}
