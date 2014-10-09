package lumaceon.mods.clockworkphase.item.construct.clockwork;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IDisassemble
{
    public void disassemble(World world, double x, double y, double z, ItemStack is);
}
