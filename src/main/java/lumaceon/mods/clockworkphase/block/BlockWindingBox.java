package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.item.construct.abstracts.IClockwork;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWindingBox extends BlockClockworkPhaseAbstract
{
    public BlockWindingBox(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!player.isSneaking())
        {
            if(!player.getHeldItem(hand).isEmpty() && player.getHeldItem(hand).getItem() instanceof IClockwork)
            {
                ItemStack is = player.getHeldItem(hand);
                if(NBTHelper.hasTag(is, NBTTags.MAX_TENSION))
                {
                    ((IClockwork) is.getItem()).addTension(is, NBTHelper.getInt(is, NBTTags.MAX_TENSION) / 25);
                    return true;
                }
            }
        }
        return false;
    }
}
