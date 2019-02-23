package lumaceon.mods.clockworkphase.block.extractor;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.block.BlockClockworkPhaseAbstract;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeWell;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.GUIs;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockExtractor extends BlockClockworkPhaseAbstract
{
    public BlockExtractor(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack is = player.getHeldItem(hand);
        if(!is.isEmpty() && is.getItem().equals(ModItems.timeTuner))
        {
            int ttx = NBTHelper.getInt(is, NBTTags.CLOCKWORK_PHASE_X);
            int tty = NBTHelper.getInt(is, NBTTags.CLOCKWORK_PHASE_Y);
            int ttz = NBTHelper.getInt(is, NBTTags.CLOCKWORK_PHASE_Z);
            TileEntity timeWell = world.getTileEntity(new BlockPos(ttx, tty, ttz));
            if(timeWell instanceof TileEntityTimeWell)
            {
                if(timeWell.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()) <= 2048)
                {
                    TileEntity te = world.getTileEntity(pos);
                    if(te instanceof TileEntityExtractor)
                    {
                        TileEntityExtractor extractor = (TileEntityExtractor) te;
                        extractor.timeWellX = ttx;
                        extractor.timeWellY = tty;
                        extractor.timeWellZ = ttz;
                    }
                }
                else if(world.isRemote)
                {
                    player.sendMessage(new TextComponentString("The time link is too weak from this distance."));
                }
            }
            return true;
        }
        else if(!world.isRemote && !player.isSneaking())
        {
            FMLNetworkHandler.openGui(player, ClockworkPhase.instance, GUIs.EXTRACTOR_GUI.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return false;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TileEntityExtractor)
        {
            TileEntityExtractor timeWell = (TileEntityExtractor)te;
            for(int n = 0; n < timeWell.getSizeInventory(); n++)
            {
                ItemStack is = timeWell.removeStackFromSlot(n);
                if(!is.isEmpty())
                {
                    float f = 0.7F;
                    double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                    double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                    double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(world, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, is);
                    world.spawnEntity(entityitem);
                }
            }
        }
        super.breakBlock(world, pos, state);
    }
}
