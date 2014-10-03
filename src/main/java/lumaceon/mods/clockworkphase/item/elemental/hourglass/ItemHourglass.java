package lumaceon.mods.clockworkphase.item.elemental.hourglass;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.IDisassemble;
import lumaceon.mods.clockworkphase.item.ITension;
import lumaceon.mods.clockworkphase.item.elemental.ItemElemental;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemHourglass extends ItemElemental implements ITension, IDisassemble
{
    public ItemHourglass()
    {
        super();
        this.setMaxDamage(10);
        this.setNoRepair();
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        list.add("Tension: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.TENSION_ENERGY) + "/" + "\u00a7e" + NBTHelper.getInt(is, NBTTags.MAX_TENSION));
    }

    public void elementize(Phases phase, EntityItem item)
    {
        super.elementize(phase, item);
        int id = phase.ordinal();
        if(!item.getEntityItem().getItem().equals(ModItems.hourglassElements[id]))
        {
            ItemStack newItem = new ItemStack(ModItems.hourglassElements[id]);

            if(NBTHelper.hasTag(item.getEntityItem(), NBTTags.MAX_TENSION))
            {
                NBTHelper.setInteger(newItem, NBTTags.MAX_TENSION, NBTHelper.getInt(item.getEntityItem(), NBTTags.MAX_TENSION));
            }
            if(NBTHelper.hasTag(item.getEntityItem(), NBTTags.TENSION_ENERGY))
            {
                NBTHelper.setInteger(newItem, NBTTags.TENSION_ENERGY, NBTHelper.getInt(item.getEntityItem(), NBTTags.TENSION_ENERGY));
            }

            newItem.setItemDamage(item.getEntityItem().getItemDamage());
            item.setEntityItemStack(newItem);
        }
    }

    public void unelementize(EntityItem item)
    {
        super.unelementize(item);
        if(!item.getEntityItem().getItem().equals(ModItems.hourglass))
        {
            ItemStack newItem = new ItemStack(ModItems.hourglass);

            if(NBTHelper.hasTag(item.getEntityItem(), NBTTags.MAX_TENSION))
            {
                NBTHelper.setInteger(newItem, NBTTags.MAX_TENSION, NBTHelper.getInt(item.getEntityItem(), NBTTags.MAX_TENSION));
            }
            if(NBTHelper.hasTag(item.getEntityItem(), NBTTags.TENSION_ENERGY))
            {
                NBTHelper.setInteger(newItem, NBTTags.TENSION_ENERGY, NBTHelper.getInt(item.getEntityItem(), NBTTags.TENSION_ENERGY));
            }

            newItem.setItemDamage(item.getEntityItem().getItemDamage());
            item.setEntityItemStack(newItem);
        }
    }

    @Override
    public void addTension(ItemStack is, int tension)
    {
        if(NBTHelper.hasTag(is, NBTTags.MAX_TENSION))
        {
            int maxTension = NBTHelper.getInt(is, NBTTags.MAX_TENSION);
            int currentTension;
            if(!NBTHelper.hasTag(is, NBTTags.TENSION_ENERGY)) { currentTension = 0; }
            else { currentTension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY); }

            if(currentTension + tension >= maxTension)
            {
                NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, maxTension);
            }
            else
            {
                NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, currentTension + tension);
            }

            if(maxTension / 10 == 0) { is.setItemDamage(is.getMaxDamage()); }
            else { is.setItemDamage(10 - (currentTension / (maxTension / 10))); }
        }
    }

    @Override
    public void removeTension(ItemStack is, int tension)
    {
        int maxTension = 0;
        int currentTension;
        if(NBTHelper.hasTag(is, NBTTags.MAX_TENSION)) { maxTension = NBTHelper.getInt(is, NBTTags.MAX_TENSION); }
        if(!NBTHelper.hasTag(is, NBTTags.TENSION_ENERGY)) { currentTension = 0; }
        else { currentTension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY); }

        if(currentTension - tension <= 0)
        {
           NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, 0);
        }
        else
        {
            NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, currentTension - tension);
        }

        if(maxTension / 10 == 0) { is.setItemDamage(is.getMaxDamage()); }
        else { is.setItemDamage(10 - (currentTension / (maxTension / 10))); }
    }

    @Override
    public void disassemble(World world, double x, double y, double z, ItemStack is)
    {
        if(world.isRemote)
        {
            return;
        }

        int maxTension = NBTHelper.getInt(is, NBTTags.MAX_TENSION);

        if(maxTension != 0)
        {
            ItemStack mainspring = new ItemStack(ModItems.mainspring);
            NBTHelper.setInteger(mainspring, NBTTags.MAX_TENSION, maxTension);
            NBTHelper.setInteger(mainspring, NBTTags.TENSION_ENERGY, 0);

            world.spawnEntityInWorld(new EntityItem(world, x, y, z, mainspring));
        }

        ItemStack clockwork = new ItemStack(ModItems.clockwork);

        NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, 0);
        NBTHelper.setInteger(is, NBTTags.MAX_TENSION, 0);
        is.setItemDamage(is.getMaxDamage());

        world.spawnEntityInWorld(new EntityItem(world, x, y, z, clockwork));
    }
}