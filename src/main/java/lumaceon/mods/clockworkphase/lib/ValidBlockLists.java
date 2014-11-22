package lumaceon.mods.clockworkphase.lib;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class ValidBlockLists
{
    public static class BlockOres
    {
        public static ArrayList<Block> validOres = new ArrayList<Block>(300);

        public static boolean isBlockValidOre(Block block)
        {
            for(int n = 0; n < validOres.size(); n++)
            {
                if(validOres.get(n).equals(block))
                {
                    return true;
                }
            }
            return false;
        }
    }

    public static class BlockLogs
    {
        public static ArrayList<Block> validLogs = new ArrayList<Block>(50);

        public static boolean isBlockValidWood(Block block)
        {
            for(int n = 0; n < validLogs.size(); n++)
            {
                if(validLogs.get(n).equals(block))
                {
                    return true;
                }
            }
            return false;
        }
    }

    public static class BlockShovelables
    {
        public static ArrayList<Block> validShovelables = new ArrayList<Block>(30);

        public static boolean isBlockValidShovelable(Block block)
        {
            for(int n = 0; n < validShovelables.size(); n++)
            {
                if(validShovelables.get(n).equals(block))
                {
                    return true;
                }
            }
            return false;
        }
    }

    public static void initValidBlocks()
    {
        String[] oreNames = OreDictionary.getOreNames();
        ArrayList<ItemStack> ores;
        ItemStack currentOre;

        for(int n = 0; n < oreNames.length; n++)
        {
            if(oreNames[n].contains("ore"))
            {
                ores = OreDictionary.getOres(oreNames[n]);
                for(int i = 0; i < ores.size(); i++)
                {
                    currentOre = ores.get(i);
                    if(Block.getBlockFromItem(currentOre.getItem()) != null)
                    {
                        BlockOres.validOres.add(Block.getBlockFromItem(currentOre.getItem()));
                    }
                }
            }

            if(oreNames[n].contains("gravel") || oreNames[n].contains("sand") || oreNames[n].contains("grass") || oreNames[n].contains("dirt"))
            {
                ores = OreDictionary.getOres(oreNames[n]);
                for(int i = 0; i < ores.size(); i++)
                {
                    currentOre = ores.get(i);
                    if(Block.getBlockFromItem(currentOre.getItem()) != null)
                    {
                        BlockShovelables.validShovelables.add(Block.getBlockFromItem(currentOre.getItem()));
                    }
                }
            }

            if(oreNames[n].contains("logWood"))
            {
                ores = OreDictionary.getOres(oreNames[n]);
                for(int i = 0; i < ores.size(); i++)
                {
                    currentOre = ores.get(i);
                    if(Block.getBlockFromItem(currentOre.getItem()) != null)
                    {
                        BlockLogs.validLogs.add(Block.getBlockFromItem(currentOre.getItem()));
                    }
                }
            }
        }
    }
}
