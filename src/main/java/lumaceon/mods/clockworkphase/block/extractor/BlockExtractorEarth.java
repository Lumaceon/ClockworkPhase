package lumaceon.mods.clockworkphase.block.extractor;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockExtractorEarth extends BlockExtractor implements ITileEntityProvider
{
    public BlockExtractorEarth(Material material)
    {
        super(material);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int data)
    {
        return new TileEntityExtractor(Phases.EARTH);
    }
}
