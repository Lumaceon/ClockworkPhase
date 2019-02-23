package lumaceon.mods.clockworkphase.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockHourglassLight extends BlockClockworkPhaseAbstract
{

    public static final AxisAlignedBB AABB = new AxisAlignedBB(0.34375, 0.34375, 0.34375, 0.65625, 0.65625, 0.65625);
    public static final PropertyInteger LIGHT = PropertyInteger.create("light", 0, 15);

    public BlockHourglassLight(Material material)
    {
        super(material);
        this.setCreativeTab(null);
        this.setLightOpacity(0);
        this.setHardness(0.0F);
        this.setDefaultState(getBlockState().getBaseState().withProperty(LIGHT, 0));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(LIGHT, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(LIGHT);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, LIGHT);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        if(world.getBlockState(pos).getBlock() instanceof BlockHourglassLight)
        {
            return world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
        }
        return 0;
    }

    @Override
    public int quantityDropped(Random p_149745_1_)
    {
        return 0;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }
}
