package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.custom.CustomEvents;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.util.TextureHelper;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCelestialCompassSub extends BlockClockworkPhaseAbstract
{
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockCelestialCompassSub(Material material)
    {
        super(material);
        this.setCreativeTab(null);
        this.setBlockUnbreakable();
        this.setResistance(1000000F);
        this.setLightLevel(1.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons() {
        super.registerBlockIcons();
        ModelLoader.setCustomStateMapper(this, block -> CustomEvents.MODELS_COMPASS_SUB);
    }

    @Override
    public IBlockState getActualState(IBlockState state1, IBlockAccess blockAccess, BlockPos pos) {

        boolean flag = true;
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();

        //Coordinates to be passed into the TextureHelper.
        int xOffset = 0;
        int zOffset = 0;

        IBlockState state = blockAccess.getBlockState(new BlockPos(x, y, z));
        int meta = state.getBlock().getMetaFromState(state);
        EnumFacing direction = EnumFacing.byHorizontalIndex(meta);

        x += direction.getXOffset();
        z += direction.getZOffset();

        xOffset += -direction.getXOffset();
        zOffset += -direction.getZOffset();

        for (int n = 0; n < 10 && flag; n++) {
            if (blockAccess.getBlockState(new BlockPos(x, y, z)).getBlock().equals(ModBlocks.celestialCompass)) {
                flag = false;
            } else {
                state = blockAccess.getBlockState(new BlockPos(x, y, z));
                meta = state.getBlock().getMetaFromState(state);
                direction = EnumFacing.byHorizontalIndex(meta);

                x += direction.getXOffset();
                z += direction.getZOffset();

                xOffset += -direction.getXOffset();
                zOffset += -direction.getZOffset();
            }
        }

        int iconIndex = TextureHelper.getCCTextureIndexFromCoordinates(xOffset, zOffset);
        if (iconIndex < 0 || iconIndex > 96) {
            iconIndex = 0;
        }
        return CustomEvents.STATES_COMPASS_SUB[iconIndex];
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(FACING)).getHorizontalIndex();
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
}
