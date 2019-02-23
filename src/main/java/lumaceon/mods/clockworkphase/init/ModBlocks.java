package lumaceon.mods.clockworkphase.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase.custom.IHasModel;
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
    public static Block clockworkAssemblyTable;
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
        brassBlock = toModel(new BlockBrass(Material.IRON)).register(Names.BRASS_BLOCK);
        OreDictionary.registerOre("blockBrass", brassBlock);

        clockworkAssemblyTable = toModel(new BlockClockworkAssemblyTable(Material.WOOD)).register(Names.ASSEMBLY_TABLE);

        windingBox = toModel(new BlockWindingBox(Material.IRON)).register(Names.WINDING_BOX);

        disassembler = toModel(new BlockDisassembler(Material.IRON)).register(Names.DISASSEMBLER);

        timeSand = toModel(new BlockTimeSand(ModFluids.timeSand, Material.WATER)).register(Names.TIME_SAND);

        celestialCompass = toModel(new BlockCelestialCompass(Material.IRON)).register(Names.CELESTIAL_COMPASS);

        celestialCompassSub = toModel(new BlockCelestialCompassSub(Material.IRON)).register(Names.CELESTIAL_COMPASS_SUB);

        timeWell = toModel(new BlockTimeWell(Material.IRON)).register(Names.TIME_WELL, ItemBlockTimeWell.class);

        oreTemporal = toModel(new BlockTemporalOre()).register(Names.TEMPORAL_ORE);
        OreDictionary.registerOre("oreTemporal", oreTemporal);

        sandTemporal = toModel(new BlockTemporalSand()).register(Names.TEMPORAL_SAND);

        woodTemporal = toModel(new BlockTemporalWood()).register(Names.TEMPORAL_WOOD);

        blockTemporal = toModel(new BlockTemporal(Material.IRON)).register(Names.TEMPORAL_BLOCK);
        OreDictionary.registerOre("blockTemporal", blockTemporal);


        /** EXTRACTOR ITEM/BLOCK START **/
        extractorsElements[0] = toModel(new BlockExtractorLife(Material.IRON)).register(Names.EXTRACTOR + "_life", ItemBlockExtractor.class);

        extractorsElements[1] = toModel(new BlockExtractorLight(Material.IRON)).register(Names.EXTRACTOR + "_light", ItemBlockExtractor.class);

        extractorsElements[2] = toModel(new BlockExtractorWater(Material.IRON)).register(Names.EXTRACTOR + "_water", ItemBlockExtractor.class);

        extractorsElements[3] = toModel(new BlockExtractorEarth(Material.IRON)).register(Names.EXTRACTOR + "_earth", ItemBlockExtractor.class);

        extractorsElements[4] = toModel(new BlockExtractorAir(Material.IRON)).register(Names.EXTRACTOR + "_air", ItemBlockExtractor.class);

        extractorsElements[5] = toModel(new BlockExtractorFire(Material.IRON)).register(Names.EXTRACTOR + "_fire", ItemBlockExtractor.class);

        extractorsElements[6] = toModel(new BlockExtractorLunar(Material.IRON)).register(Names.EXTRACTOR + "_lunar", ItemBlockExtractor.class);

        extractorsElements[7] = toModel(new BlockExtractorDeath(Material.IRON)).register(Names.EXTRACTOR + "_death", ItemBlockExtractor.class);
        /** END EXTRACTOR **/


        hourglassLight = toModel(new BlockHourglassLight(Material.PORTAL)).register(Names.HOURGLASS_LIGHT);
    }

    private static IHasModel toModel(Block block) {
        return (IHasModel) block;
    }

    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCelestialCompass.class, Names.CELESTIAL_COMPASS);
        GameRegistry.registerTileEntity(TileEntityTimeWell.class, Names.TIME_WELL);
        GameRegistry.registerTileEntity(TileEntityExtractor.class, Names.EXTRACTOR);
    }
}