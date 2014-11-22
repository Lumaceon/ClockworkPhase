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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockTimeWell extends BlockClockworkPhaseAbstract implements ITileEntityProvider
{
    public BlockTimeWell(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float f0, float f1, float f2)
    {
        ItemStack is = player.getHeldItem();

        if(is != null && is.getItem().equals(Items.bucket))
        {
            TileEntity te = world.getTileEntity(x, y, z);
            if(te != null && te instanceof TileEntityTimeWell)
            {
                TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                if(timeWell.getTimeSand() >= 1000)
                {
                    timeWell.removeTimeSand(1000);
                    ItemStack itemStack = new ItemStack(ModItems.timeSandBucket);
                    itemStack.setTagCompound(is.stackTagCompound);
                    itemStack.setItemDamage(is.getItemDamage());
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
                }
            }
            return true;
        }
        else if(is != null && is.getItem().equals(ModItems.timeSandBucket))
        {
            TileEntity te = world.getTileEntity(x, y, z);
            if(te != null && te instanceof TileEntityTimeWell)
            {
                TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                if(timeWell.getTimeSand() + 1000 <= timeWell.getMaxTimeSandCapacity())
                {
                    timeWell.addTimeSand(1000);
                    ItemStack item = new ItemStack(Items.bucket);
                    item.setTagCompound(is.stackTagCompound);
                    item.setItemDamage(is.getItemDamage());
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, item);
                }
            }
            return true;
        }
        else if(is != null && is.getItem().equals(ModItems.timeTuner))
        {
            NBTHelper.setInteger(is, NBTTags.CLOCKWORK_PHASE_X, x);
            NBTHelper.setInteger(is, NBTTags.CLOCKWORK_PHASE_Y, y);
            NBTHelper.setInteger(is, NBTTags.CLOCKWORK_PHASE_Z, z);

            if(world.isRemote)
            {
                player.addChatComponentMessage(new ChatComponentText("Time well location saved."));
                player.addChatComponentMessage(new ChatComponentText("X: " + x + ", Y: " + y + ", Z: " + z));
            }
            return true;
        }
        else if(!world.isRemote && !player.isSneaking())
        {
            TileEntity te = world.getTileEntity(x, y, z);
            if(te != null && te instanceof TileEntityTimeWell)
            {
                player.openGui(ClockworkPhase.instance, 3, world, x, y, z);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
    {
        if(!world.isRemote && !player.capabilities.isCreativeMode)
        {
            TileEntity te = world.getTileEntity(x, y, z);
            if(te != null && te instanceof TileEntityTimeWell)
            {
                TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                ItemStack result = new ItemStack(this);
                TimeSandHelper.addTimeSand(result, timeWell.getTimeSand(), timeWell.getMaxTimeSandCapacity());

                float f = 0.7F;
                double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, result);

                entityitem.delayBeforeCanPickup = 10;
                world.spawnEntityInWorld(entityitem);
            }
        }

        return super.removedByPlayer(world, player, x, y, z, willHarvest);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> results = new ArrayList<ItemStack>(1);
        if(!world.isRemote)
        {
            TileEntity te = world.getTileEntity(x, y, z);
            if(te != null && te instanceof TileEntityTimeWell)
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
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, player, stack);
        TileEntity te = world.getTileEntity(x, y, z);
        if(te != null && te instanceof TileEntityTimeWell)
        {
            TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
            timeWell.setTimeSandUnsynced(TimeSandHelper.getTimeSand(stack));
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if(te != null && te instanceof TileEntityTimeWell)
        {
            TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
            for(int n = 0; n < timeWell.getSizeInventory(); n++)
            {
                ItemStack is = timeWell.getStackInSlotOnClosing(n);
                if(is != null)
                {
                    float f = 0.7F;
                    double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                    double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                    double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, is);
                    world.spawnEntityInWorld(entityitem);
                }
            }
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityTimeWell();
    }
}
