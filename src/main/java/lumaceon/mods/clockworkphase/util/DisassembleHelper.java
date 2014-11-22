package lumaceon.mods.clockworkphase.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class DisassembleHelper
{
    public static void disassembleMetalGear(World world, double x, double y, double z, ItemStack gear, String oreDictMetalName)
    {
        ArrayList<ItemStack> ores = OreDictionary.getOres(oreDictMetalName);
        if(!ores.isEmpty())
        {
            ItemStack output = ores.get(0).copy();
            if(output != null)
            {
                output.stackSize = 4;
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, output));
                gear.stackSize -= 1;
            }
        }
    }
}
