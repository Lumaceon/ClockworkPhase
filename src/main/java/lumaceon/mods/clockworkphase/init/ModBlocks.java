package lumaceon.mods.clockworkphase.init;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.block.*;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityCelestialCompass;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeWell;
import lumaceon.mods.clockworkphase.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks
{
    public static Block brassBlock;
    public static Block windingBox;
    public static Block disassembler;
    public static Block growthExtractor;
    public static Block timeSand;
    public static Block celestialCompass;
    public static Block celestialCompassSub;
    public static Block timeWell;

    public static Block hourglassLight;

    public static void init()
    {
        brassBlock = new BlockBrass(Material.iron).setBlockName(Names.BRASS_BLOCK);
        GameRegistry.registerBlock(brassBlock, Names.BRASS_BLOCK);
        OreDictionary.registerOre("blockBrass", brassBlock);

        windingBox = new BlockWindingBox(Material.iron).setBlockName(Names.WINDING_BOX);
        GameRegistry.registerBlock(windingBox, Names.WINDING_BOX);

        disassembler = new BlockDisassembler(Material.iron).setBlockName(Names.DISASSEMBLER);
        GameRegistry.registerBlock(disassembler, Names.DISASSEMBLER);

        growthExtractor = new BlockGrowthExtractor(Material.iron).setBlockName(Names.GROWTH_EXTRACTOR);
        GameRegistry.registerBlock(growthExtractor, Names.GROWTH_EXTRACTOR);

        timeSand = new BlockTimeSand(ModFluids.timeSand, Material.water).setBlockName(Names.TIME_SAND);
        GameRegistry.registerBlock(timeSand, Names.TIME_SAND);

        celestialCompass = new BlockCelestialCompass(Material.iron).setBlockName(Names.CELESTIAL_COMPASS);
        GameRegistry.registerBlock(celestialCompass, Names.CELESTIAL_COMPASS);

        celestialCompassSub = new BlockCelestialCompassSub(Material.iron).setBlockName(Names.CELESTIAL_COMPASS_SUB);
        GameRegistry.registerBlock(celestialCompassSub, Names.CELESTIAL_COMPASS_SUB);

        timeWell = new BlockTimeWell(Material.iron).setBlockName(Names.TIME_WELL);
        GameRegistry.registerBlock(timeWell, Names.TIME_WELL);


        hourglassLight = new BlockHourglassLight(Material.portal).setBlockName(Names.HOURGLASS_LIGHT);
        GameRegistry.registerBlock(hourglassLight, Names.HOURGLASS_LIGHT);
    }

    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCelestialCompass.class, Names.CELESTIAL_COMPASS);
        GameRegistry.registerTileEntity(TileEntityTimeWell.class, Names.TIME_WELL);
    }
}