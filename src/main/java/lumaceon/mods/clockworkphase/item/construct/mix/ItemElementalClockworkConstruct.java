package lumaceon.mods.clockworkphase.item.construct.mix;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.construct.clockwork.IClockwork;
import lumaceon.mods.clockworkphase.item.construct.clockwork.IDisassemble;
import lumaceon.mods.clockworkphase.item.construct.elemental.IElemental;
import lumaceon.mods.clockworkphase.item.construct.elemental.ItemElemental;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemElementalClockworkConstruct extends ItemElemental implements IElemental, IClockwork, IDisassemble
{
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        list.add("Tension: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.TENSION_ENERGY) + "/" + "\u00a7e" + NBTHelper.getInt(is, NBTTags.MAX_TENSION));
        int timeSand = NBTHelper.getInt(is, NBTTags.INTERNAL_TIME_SAND);
        if(timeSand > 0)
        {
            list.add("Internal Time Sand: " + "\u00A7e" + timeSand);
        }
        list.add("");

        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            list.add("Clockwork Quality: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.QUALITY));
            list.add("Clockwork Speed: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.SPEED));
            list.add("Memory: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.MEMORY));
            list.add("");
        }
        else
        {
            list.add("-Hold shift for details-");
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
    public void addTimeSand(ItemStack is, int timeSand)
    {
        int currentMemoryPower = NBTHelper.getInt(is, NBTTags.INTERNAL_TIME_SAND);

        if(currentMemoryPower + timeSand >= MechanicTweaker.MAX_TIME_SAND_TOOLS)
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, MechanicTweaker.MAX_TIME_SAND_TOOLS);
        }
        else
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, currentMemoryPower + timeSand);
        }
    }

    @Override
    public void removeTimeSand(ItemStack is, int timeSand)
    {
        int currentMemoryPower = NBTHelper.getInt(is, NBTTags.INTERNAL_TIME_SAND);

        if(currentMemoryPower - timeSand <= 0)
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, 0);
        }
        else if(currentMemoryPower - timeSand >= MechanicTweaker.MAX_TIME_SAND_TOOLS)
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, MechanicTweaker.MAX_TIME_SAND_TOOLS);
        }
        else
        {
            NBTHelper.setInteger(is, NBTTags.INTERNAL_TIME_SAND, currentMemoryPower - timeSand);
        }
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

        if(NBTHelper.hasTag(is, NBTTags.CLOCKWORK))
        {
            NBTTagList tagList = NBTHelper.getTagList(is, NBTTags.CLOCKWORK);
            if(tagList.tagCount() > 0)
            {
                ItemStack clockwork = ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(0));
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, clockwork));
            }
        }

        NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, 0);
        NBTHelper.setInteger(is, NBTTags.MAX_TENSION, 0);
        NBTHelper.setInteger(is, NBTTags.QUALITY, 0);
        NBTHelper.setInteger(is, NBTTags.SPEED, 0);
        NBTHelper.setInteger(is, NBTTags.MEMORY, 0);
        NBTHelper.removeTag(is, NBTTags.CLOCKWORK);
        is.setItemDamage(is.getMaxDamage());
    }
}
