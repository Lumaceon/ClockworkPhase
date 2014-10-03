package lumaceon.mods.clockworkphase.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemClockwork extends ItemClockworkPhase implements IDisassemble
{
    public ItemClockwork()
    {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(10);
        this.setNoRepair();
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        list.add("Power multiplier: 1");
    }

    @Override
    public void disassemble(World world, double x, double y, double z, ItemStack is)
    {
        
    }
}
