package lumaceon.mods.clockworkphase.init;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.item.ItemBrassIngot;
import lumaceon.mods.clockworkphase.item.ItemBugSwatter;
import lumaceon.mods.clockworkphase.item.ItemHandCrank;
import lumaceon.mods.clockworkphase.item.ItemMainspring;
import lumaceon.mods.clockworkphase.item.windable.IWindable;
import lumaceon.mods.clockworkphase.item.windable.ItemTimeEnergyDevice;
import lumaceon.mods.clockworkphase.lib.Names;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems
{
    public static Item bugSwatter;
    public static Item brassIngot;
    public static Item mainspring;
    public static Item handCrank;
    public static Item timeEnergyDevice;

    public static void init()
    {
        bugSwatter = new ItemBugSwatter().setUnlocalizedName(Names.BUG_SWATTER);
        GameRegistry.registerItem(bugSwatter, Names.BUG_SWATTER);

        brassIngot = new ItemBrassIngot().setUnlocalizedName(Names.BRASS_INGOT);
        GameRegistry.registerItem(brassIngot, Names.BRASS_INGOT);
        OreDictionary.registerOre("ingotBrass", brassIngot);

        mainspring = new ItemMainspring().setUnlocalizedName(Names.MAINSPRING);
        GameRegistry.registerItem(mainspring, Names.MAINSPRING);

        handCrank = new ItemHandCrank().setUnlocalizedName(Names.HAND_CRANK);
        GameRegistry.registerItem(handCrank, Names.HAND_CRANK);

        timeEnergyDevice = new ItemTimeEnergyDevice().setUnlocalizedName(Names.TIME_ENERGY_DEVICE);
        GameRegistry.registerItem(timeEnergyDevice, Names.TIME_ENERGY_DEVICE);
    }
}