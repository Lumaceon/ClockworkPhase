package lumaceon.mods.clockworkphase.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.client.handler.KeyHandler;
import lumaceon.mods.clockworkphase.item.construct.IKeybindAbility;
import lumaceon.mods.clockworkphase.item.construct.abstracts.IDisassemble;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemTemporalMultitool extends ItemClockworkPhaseGeneric implements IDisassemble, IKeybindAbility
{
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, World worldIn, List<String> list, ITooltipFlag flagIn)
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            if(NBTHelper.hasTag(is, NBTTags.TEMPORAL_ITEMS))
            {
                ItemStack[] items = NBTHelper.getInventoryFromNBTTag(is, NBTTags.TEMPORAL_ITEMS);
                for(int n = 0; n < items.length; n++)
                {
                    list.add("Item " + (n + 1) + ": " + "\u00A7e" + items[n].getDisplayName());
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
    public void disassemble(World world, double x, double y, double z, ItemStack is)
    {
        if(world.isRemote)
        {
            return;
        }

        if(NBTHelper.hasTag(is, NBTTags.TEMPORAL_ITEMS))
        {
            ItemStack[] items = NBTHelper.getInventoryFromNBTTag(is, NBTTags.TEMPORAL_ITEMS);
            for(int n = 0; n < items.length; n++)
            {
                world.spawnEntity(new EntityItem(world, x, y, z, items[n]));
            }
            NBTHelper.removeTag(is, NBTTags.TEMPORAL_ITEMS);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void useTemporalAbility()
    {
        KeyHandler.handleMultitoolAbility();
    }
}