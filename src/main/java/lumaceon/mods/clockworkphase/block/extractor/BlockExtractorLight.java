package lumaceon.mods.clockworkphase.block.extractor;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockExtractorLight extends BlockExtractor implements ITileEntityProvider
{
    public BlockExtractorLight(Material material)
    {
        super(material);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityExtractor(Phases.LIGHT);
    }
}
