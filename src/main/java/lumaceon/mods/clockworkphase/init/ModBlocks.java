package lumaceon.mods.clockworkphase.init;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.block.*;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityCelestialCompass;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityWindingBox;
import lumaceon.mods.clockworkphase.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks
{
    public static Block brassBlock;
    public static Block windingBox;
    public static Block gearPanel;
    public static Block growthExtractor;
    public static Block timeSand;
    public static Block celestialCompass;
    public static Block celestialCompassSub;

    public static void init()
    {
        brassBlock = new BlockBrass(Material.iron).setBlockName(Names.BRASS_BLOCK);
        GameRegistry.registerBlock(brassBlock, Names.BRASS_BLOCK);
        OreDictionary.registerOre("blockBrass", brassBlock);

        windingBox = new BlockWindingBox(Material.iron).setBlockName(Names.WINDING_BOX);
        GameRegistry.registerBlock(windingBox, Names.WINDING_BOX);

        gearPanel = new BlockGear(Material.iron).setBlockName(Names.GEAR_PANEL);
        GameRegistry.registerBlock(gearPanel, Names.GEAR_PANEL);

        growthExtractor = new BlockGrowthExtractor(Material.iron).setBlockName(Names.GROWTH_EXTRACTOR);
        GameRegistry.registerBlock(growthExtractor, Names.GROWTH_EXTRACTOR);

        timeSand = new BlockTimeSand(ModFluids.timeSand, Material.water).setBlockName(Names.TIME_SAND);
        GameRegistry.registerBlock(timeSand, Names.TIME_SAND);

        celestialCompass = new BlockCelestialCompass(Material.iron).setBlockName(Names.CELESTIAL_COMPASS);
        GameRegistry.registerBlock(celestialCompass, Names.CELESTIAL_COMPASS);

        celestialCompassSub = new BlockCelestialCompassSub(Material.iron).setBlockName(Names.CELESTIAL_COMPASS_SUB);
        GameRegistry.registerBlock(celestialCompassSub, Names.CELESTIAL_COMPASS_SUB);
    }

    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityWindingBox.class, Names.WINDING_BOX);
        GameRegistry.registerTileEntity(TileEntityCelestialCompass.class, Names.CELESTIAL_COMPASS);
    }
}