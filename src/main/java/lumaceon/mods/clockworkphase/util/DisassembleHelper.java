package lumaceon.mods.clockworkphase.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class DisassembleHelper
{
    public static void disassembleMetalGear(World world, double x, double y, double z, ItemStack gear, String oreDictMetalName)
    {
        NonNullList<ItemStack> ores = OreDictionary.getOres(oreDictMetalName);
        if(!ores.isEmpty())
        {
            ItemStack output = ores.get(0).copy();
            if(!output.isEmpty())
            {
                output.setCount(4);
                world.spawnEntity(new EntityItem(world, x, y, z, output));
                gear.shrink(1);
            }
        }
    }
}
