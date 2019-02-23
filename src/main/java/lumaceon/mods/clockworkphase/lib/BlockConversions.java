package lumaceon.mods.clockworkphase.lib;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class BlockConversions
{
    public static ArrayList<String> validPickaxeBlocks = new ArrayList<String>(50);

    public static void initValidBlocks()
    {
        //Vanilla
        addToPickaxe("oreGold");
        addToPickaxe("oreIron");
        addToPickaxe("oreLapis");
        addToPickaxe("oreDiamond");
        addToPickaxe("oreRedstone");
        addToPickaxe("oreEmerald");
        addToPickaxe("oreQuartz");
        addToPickaxe("oreCoal");

        //Thaumcraft
        addToPickaxe("oreCinnabar");
        addToPickaxe("oreAmber");
        addToPickaxe("oreInfusedAir");
        addToPickaxe("oreInfusedFire");
        addToPickaxe("oreInfusedWater");
        addToPickaxe("oreInfusedEarth");
        addToPickaxe("oreInfusedOrder");
        addToPickaxe("oreInfusedEntropy");

        //IC2 and Thermal Foundation
        addToPickaxe("oreCopper");
        addToPickaxe("oreLead");
        addToPickaxe("oreSilver");
        addToPickaxe("oreTin");
        addToPickaxe("oreFerrous");
        addToPickaxe("oreUranium");

        //Railcraft
        addToPickaxe("oreSulfer");
        addToPickaxe("oreSaltpeter");
        addToPickaxe("oreFireStone");

        //Tinker's Construct (most of these seem irrelevant...)
        addToPickaxe("oreCobalt");
        addToPickaxe("oreArdite");
        addToPickaxe("oreAluminum");
        addToPickaxe("oreAluminium");
        addToPickaxe("oreNickel");
        addToPickaxe("oreAluminumBrass");
        addToPickaxe("oreBronze");
        addToPickaxe("oreElectrum");
        addToPickaxe("orePlatinum");
        addToPickaxe("oreNaturalAluminum");
        addToPickaxe("oreSteel");
        addToPickaxe("oreGlue");
        addToPickaxe("oreAlumite");
        addToPickaxe("oreManyullun");
        addToPickaxe("oreInvar");
        addToPickaxe("orePigIron");
        addToPickaxe("oreObsidian");
    }

    /**
     * Adds the specified oreDictionary string to the valid block conversions for the clockwork pickaxe, checking
     * to see if the ore is valid and exists.
     * @param ore A string representing the ore.
     * @return If the ore was added or not.
     */
    public static boolean addToPickaxe(String ore)
    {
        NonNullList<ItemStack> ores = OreDictionary.getOres(ore);
        if(ore.isEmpty()) { return false; }
        validPickaxeBlocks.add(ore);
        return true;
    }

    public static boolean addToShovel(String ore)
    {
        NonNullList<ItemStack> ores = OreDictionary.getOres(ore);
        if(ore.isEmpty()) { return false; }
        validPickaxeBlocks.add(ore);
        return true;
    }
}
