package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeWell;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.TimeSandHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockTimeWell extends BlockClockworkPhaseAbstract implements ITileEntityProvider
{
    public BlockTimeWell(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack is = player.getHeldItem(hand);

        if(!is.isEmpty() && is.getItem().equals(Items.BUCKET))
        {
            TileEntity te = world.getTileEntity(pos);
            if(te instanceof TileEntityTimeWell)
            {
                TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                if(timeWell.getTimeSand() >= 1000)
                {
                    timeWell.removeTimeSand(1000);
                    ItemStack itemStack = new ItemStack(ModItems.timeSandBucket);
                    itemStack.setTagCompound(is.getTagCompound());
                    itemStack.setItemDamage(is.getItemDamage());

                    is.shrink(1);
                    if (is.isEmpty()) {
                        player.setHeldItem(hand, itemStack);
                    } else if (!player.inventory.addItemStackToInventory(itemStack))
                    {
                        player.dropItem(itemStack, false);
                    }

//                    player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
                }
            }
            return true;
        }
        else if(!is.isEmpty() && is.getItem().equals(ModItems.timeSandBucket))
        {
            TileEntity te = world.getTileEntity(pos);
            if(te instanceof TileEntityTimeWell)
            {
                TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                if(timeWell.getTimeSand() + 1000 <= timeWell.getMaxTimeSandCapacity())
                {
                    timeWell.addTimeSand(1000);
                    ItemStack item = new ItemStack(Items.BUCKET);
                    item.setTagCompound(is.getTagCompound());
                    item.setItemDamage(is.getItemDamage());

                    is.shrink(1);
                    if (is.isEmpty()) {
                        player.setHeldItem(hand, item);
                    } else if (!player.inventory.addItemStackToInventory(item))
                    {
                        player.dropItem(item, false);
                    }

//                    player.inventory.setInventorySlotContents(player.inventory.currentItem, item);
                }
            }
            return true;
        }
        else if(!is.isEmpty() && is.getItem().equals(ModItems.timeTuner))
        {
            NBTHelper.setInteger(is, NBTTags.CLOCKWORK_PHASE_X, pos.getX());
            NBTHelper.setInteger(is, NBTTags.CLOCKWORK_PHASE_Y, pos.getY());
            NBTHelper.setInteger(is, NBTTags.CLOCKWORK_PHASE_Z, pos.getZ());

            if(world.isRemote)
            {
                player.sendMessage(new TextComponentString("Time well location saved."));
                player.sendMessage(new TextComponentString("X: " + pos.getX() + ", Y: " + pos.getY() + ", Z: " + pos.getZ()));
            }
            return true;
        }
        else if(!world.isRemote && !player.isSneaking())
        {
            TileEntity te = world.getTileEntity(pos);
            if(te instanceof TileEntityTimeWell)
            {
                player.openGui(ClockworkPhase.instance, 3, world, pos.getX(), pos.getY(), pos.getZ());
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        if(!world.isRemote && !player.capabilities.isCreativeMode)
        {
            TileEntity te = world.getTileEntity(pos);
            if(te instanceof TileEntityTimeWell)
            {
                TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                ItemStack result = new ItemStack(this);
                TimeSandHelper.addTimeSand(result, timeWell.getTimeSand(), timeWell.getMaxTimeSandCapacity());

                float f = 0.7F;
                double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                EntityItem entityitem = new EntityItem(world, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, result);

                entityitem.setPickupDelay(10);
                world.spawnEntity(entityitem);
            }
        }

        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        ArrayList<ItemStack> results = new ArrayList<ItemStack>(1);
        if(world instanceof World && !((World)world).isRemote)
        {
            TileEntity te = world.getTileEntity(pos);
            if(te instanceof TileEntityTimeWell)
            {
                TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                ItemStack result = new ItemStack(this);
                TimeSandHelper.addTimeSand(result, timeWell.getTimeSand(), timeWell.getMaxTimeSandCapacity());
                results.add(result);
            }
        }
        return results;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(world, pos, state, placer, stack);
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TileEntityTimeWell)
        {
            TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
            timeWell.setTimeSandUnsynced(TimeSandHelper.getTimeSand(stack));
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TileEntityTimeWell)
        {
            TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
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

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityTimeWell();
    }
}
