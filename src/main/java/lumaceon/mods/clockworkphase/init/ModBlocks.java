package lumaceon.mods.clockworkphase.init;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.block.*;
import lumaceon.mods.clockworkphase.block.blockitems.ItemBlockExtractor;
import lumaceon.mods.clockworkphase.block.blockitems.ItemBlockTimeWell;
import lumaceon.mods.clockworkphase.block.extractor.*;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityCelestialCompass;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeWell;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks
{
    public static Block brassBlock;
    public static Block windingBox;
    public static Block disassembler;
    public static Block timeSand;
    public static Block celestialCompass;
    public static Block celestialCompassSub;
    public static Block timeWell;
    public static Block oreTemporal;
    public static Block sandTemporal;
    public static Block woodTemporal;
    public static Block blockTemporal;

    public static Block extractorsElements[] = new Block[8];

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

        timeSand = new BlockTimeSand(ModFluids.timeSand, Material.water).setBlockName(Names.TIME_SAND);
        GameRegistry.registerBlock(timeSand, Names.TIME_SAND);

        celestialCompass = new BlockCelestialCompass(Material.iron).setBlockName(Names.CELESTIAL_COMPASS);
        GameRegistry.registerBlock(celestialCompass, Names.CELESTIAL_COMPASS);

        celestialCompassSub = new BlockCelestialCompassSub(Material.iron).setBlockName(Names.CELESTIAL_COMPASS_SUB);
        GameRegistry.registerBlock(celestialCompassSub, Names.CELESTIAL_COMPASS_SUB);

        timeWell = new BlockTimeWell(Material.iron).setBlockName(Names.TIME_WELL);
        GameRegistry.registerBlock(timeWell, ItemBlockTimeWell.class, Names.TIME_WELL);

        oreTemporal = new BlockTemporalOre().setBlockName(Names.TEMPORAL_ORE);
        GameRegistry.registerBlock(oreTemporal, Names.TEMPORAL_ORE);
        OreDictionary.registerOre("oreTemporal", oreTemporal);

        sandTemporal = new BlockTemporalSand().setBlockName(Names.TEMPORAL_SAND);
        GameRegistry.registerBlock(sandTemporal, Names.TEMPORAL_SAND);

        woodTemporal = new BlockTemporalWood().setBlockName(Names.TEMPORAL_WOOD);
        GameRegistry.registerBlock(woodTemporal, Names.TEMPORAL_WOOD);

        blockTemporal = new BlockTemporal(Material.iron).setBlockName(Names.TEMPORAL_BLOCK);
        GameRegistry.registerBlock(blockTemporal, Names.TEMPORAL_BLOCK);
        OreDictionary.registerOre("blockTemporal", blockTemporal);


        /** EXTRACTOR ITEM/BLOCK START **/
        extractorsElements[0] = new BlockExtractorLife(Material.iron).setBlockName(Names.EXTRACTOR + "Life");
        GameRegistry.registerBlock(extractorsElements[0], ItemBlockExtractor.class, Names.EXTRACTOR + "Life");

        extractorsElements[1] = new BlockExtractorLight(Material.iron).setBlockName(Names.EXTRACTOR + "Light");
        GameRegistry.registerBlock(extractorsElements[1], ItemBlockExtractor.class, Names.EXTRACTOR + "Light");

        extractorsElements[2] = new BlockExtractorWater(Material.iron).setBlockName(Names.EXTRACTOR + "Water");
        GameRegistry.registerBlock(extractorsElements[2], ItemBlockExtractor.class, Names.EXTRACTOR + "Water");

        extractorsElements[3] = new BlockExtractorEarth(Material.iron).setBlockName(Names.EXTRACTOR + "Earth");
        GameRegistry.registerBlock(extractorsElements[3], ItemBlockExtractor.class, Names.EXTRACTOR + "Earth");

        extractorsElements[4] = new BlockExtractorAir(Material.iron).setBlockName(Names.EXTRACTOR + "Air");
        GameRegistry.registerBlock(extractorsElements[4], ItemBlockExtractor.class, Names.EXTRACTOR + "Air");

        extractorsElements[5] = new BlockExtractorFire(Material.iron).setBlockName(Names.EXTRACTOR + "Fire");
        GameRegistry.registerBlock(extractorsElements[5], ItemBlockExtractor.class, Names.EXTRACTOR + "Fire");

        extractorsElements[6] = new BlockExtractorLunar(Material.iron).setBlockName(Names.EXTRACTOR + "Lunar");
        GameRegistry.registerBlock(extractorsElements[6], ItemBlockExtractor.class, Names.EXTRACTOR + "Lunar");

        extractorsElements[7] = new BlockExtractorDeath(Material.iron).setBlockName(Names.EXTRACTOR + "Death");
        GameRegistry.registerBlock(extractorsElements[7], ItemBlockExtractor.class, Names.EXTRACTOR + "Death");
        /** END EXTRACTOR **/


        hourglassLight = new BlockHourglassLight(Material.portal).setBlockName(Names.HOURGLASS_LIGHT);
        GameRegistry.registerBlock(hourglassLight, Names.HOURGLASS_LIGHT);
    }

    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCelestialCompass.class, Names.CELESTIAL_COMPASS);
        GameRegistry.registerTileEntity(TileEntityTimeWell.class, Names.TIME_WELL);
        GameRegistry.registerTileEntity(TileEntityExtractor.class, Names.EXTRACTOR);
    }
}