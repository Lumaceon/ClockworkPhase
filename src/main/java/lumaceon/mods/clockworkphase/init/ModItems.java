package lumaceon.mods.clockworkphase.init;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.item.ItemBrassIngot;
import lumaceon.mods.clockworkphase.item.ItemBugSwatter;
import lumaceon.mods.clockworkphase.lib.Names;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems
{
    public static Item bugSwatter;
    public static Item brassIngot;

    public static void init()
    {
        bugSwatter = new ItemBugSwatter().setUnlocalizedName(Names.BUG_SWATTER);
        GameRegistry.registerItem(bugSwatter, Names.BUG_SWATTER);

        brassIngot = new ItemBrassIngot().setUnlocalizedName(Names.BRASS_INGOT);
        GameRegistry.registerItem(brassIngot, Names.BRASS_INGOT);
        OreDictionary.registerOre("ingotBrass", brassIngot);
    }
}