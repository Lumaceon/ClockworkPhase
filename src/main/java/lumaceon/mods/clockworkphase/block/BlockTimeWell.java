package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityMemoryWell;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTimeWell extends BlockClockworkPhase implements ITileEntityProvider
{
    public BlockTimeWell(Material material)
    {
        super(material);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityMemoryWell();
    }
}
