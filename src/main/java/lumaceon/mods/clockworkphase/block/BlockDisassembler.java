package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.item.construct.abstracts.IDisassemble;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDisassembler extends BlockClockworkPhaseAbstract
{
    public BlockDisassembler(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!player.isSneaking())
        {
            if(!player.getHeldItem(hand).isEmpty() && player.getHeldItem(hand).getItem() instanceof IDisassemble)
            {
                ((IDisassemble) player.getHeldItem(hand).getItem()).disassemble(world, pos.getX(), pos.getY(), pos.getZ(), player.getHeldItem(hand));
                return true;
            }
        }
        return false;
    }
}
