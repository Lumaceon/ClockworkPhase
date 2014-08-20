package lumaceon.mods.clockworkphase.init;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.block.BlockBrass;
import lumaceon.mods.clockworkphase.block.BlockWindingBox;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityWindingBox;
import lumaceon.mods.clockworkphase.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks
{
    public static Block brassBlock;
    public static Block windingBox;

    public static void init()
    {
        brassBlock = new BlockBrass(Material.iron).setBlockName(Names.BRASS_BLOCK);
        GameRegistry.registerBlock(brassBlock, Names.BRASS_BLOCK);
        OreDictionary.registerOre("blockBrass", brassBlock);

        windingBox = new BlockWindingBox(Material.iron).setBlockName(Names.WINDING_BOX);
        GameRegistry.registerBlock(windingBox, Names.WINDING_BOX);
    }

    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityWindingBox.class, Names.WINDING_BOX);
    }
}