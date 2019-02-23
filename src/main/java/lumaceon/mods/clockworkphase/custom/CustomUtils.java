package lumaceon.mods.clockworkphase.custom;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class CustomUtils {

    public static boolean isToolEffective(ItemStack stack, IBlockState state) {
        for (String type : stack.getItem().getToolClasses(stack))
        {
            if (state.getBlock().isToolEffective(type, state))
                return true;
        }
        return false;
    }
}
