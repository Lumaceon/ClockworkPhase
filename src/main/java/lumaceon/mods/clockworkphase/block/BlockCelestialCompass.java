package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityCelestialCompass;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCelestialCompass extends BlockClockworkPhaseAbstract implements ITileEntityProvider
{

    public BlockCelestialCompass(Material material)
    {
        super(material);
        this.setResistance(1000000.0F);
    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public void registerBlockIcons() {
//        super.registerBlockIcons();
//        ModelLoader.setCustomStateMapper(this, block -> CustomEvents.MODELS_COMPASS);
//    }
//
//    @Override
//    public int getMetaFromState(IBlockState state) {
//        return 0;
//    }
//
//    @Override
//    public IBlockState getActualState(IBlockState state1, IBlockAccess blockAccess, BlockPos pos) {
//        return FMLCommonHandler.instance().getEffectiveSide().isClient() ? getActualState() : CustomEvents.STATES_COMPASS[0];
//    }
//
//    @SideOnly(Side.CLIENT)
//    private IBlockState getActualState() {
//        int index = PhaseHelper.getPhaseForWorld(ClockworkPhase.proxy.getStaticWorld()).ordinal();
//        System.out.println("+" + index);
//        return CustomEvents.STATES_COMPASS[index];
//    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntityCelestialCompass.destroyCompass(world, pos.getX(), pos.getY(), pos.getZ());
        super.breakBlock(world, pos, state);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityCelestialCompass();
    }
}
