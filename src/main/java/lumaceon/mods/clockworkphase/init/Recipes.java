package lumaceon.mods.clockworkphase.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class Recipes
{
    public static void init()
    {
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.brassBlock), "bbb", "bbb", "bbb", 'b', new ItemStack(ModItems.brassIngot));
    }
}