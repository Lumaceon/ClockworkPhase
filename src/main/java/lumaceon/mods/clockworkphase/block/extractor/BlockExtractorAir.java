package lumaceon.mods.clockworkphase.block.extractor;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockExtractorAir extends BlockExtractor implements ITileEntityProvider
{
    public BlockExtractorAir(Material material)
    {
        super(material);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_)
    {
        return new TileEntityExtractor(Phases.AIR);
    }
}
