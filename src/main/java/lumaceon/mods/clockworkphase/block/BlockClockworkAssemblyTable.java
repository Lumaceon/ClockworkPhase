package lumaceon.mods.clockworkphase.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockClockworkAssemblyTable extends BlockClockworkPhaseAbstract
{
//    public IIcon topIcon;

    public BlockClockworkAssemblyTable(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote && !player.isSneaking())
        {
            player.openGui(ClockworkPhase.instance, 4, world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return false;
    }

//    @Override
//    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int meta)
//    {
//        if(meta != 0 && meta != 1)
//        {
//            return this.blockIcon;
//        }
//        else
//        {
//            return this.topIcon;
//        }
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void registerBlockIcons(IIconRegister registry)
//    {
//        this.blockIcon = registry.registerIcon(this.getTranslationKey().substring(this.getTranslationKey().indexOf(".") + 1));
//        this.topIcon = registry.registerIcon(this.getTranslationKey().substring(this.getTranslationKey().indexOf(".") + 1) + "Top");
//    }
}
