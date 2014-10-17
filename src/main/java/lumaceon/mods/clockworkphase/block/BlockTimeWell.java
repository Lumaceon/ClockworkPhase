package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeWell;
import lumaceon.mods.clockworkphase.item.construct.clockwork.IClockwork;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.Logger;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockTimeWell extends BlockClockworkPhase implements ITileEntityProvider
{
    public BlockTimeWell(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float f0, float f1, float f2)
    {
        if(!player.isSneaking())
        {
            if(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof IClockwork)
            {
                ItemStack is = player.getHeldItem();
                TileEntity te = world.getTileEntity(x, y, z);
                if(te instanceof TileEntityTimeWell)
                {
                    TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                    ((IClockwork) is.getItem()).addTimeSand(is, timeWell.removeTimeSand(timeWell.timeSandMultiplier));
                    return true;
                }
            }

            if(player.getHeldItem() == null)
            {
                TileEntity te = world.getTileEntity(x, y, z);
                if(te instanceof TileEntityTimeWell)
                {
                    TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                    int multiplier = timeWell.increaseTimeSandMultiplier();
                    if(world.isRemote)
                    {
                        player.addChatComponentMessage(new ChatComponentText("Now charging time sand in increments of " + "\u00A7e" + multiplier));
                    }
                    return true;
                }
            }
        }
        else
        {
            if(player.getHeldItem() == null)
            {
                TileEntity te = world.getTileEntity(x, y, z);
                if(te instanceof TileEntityTimeWell)
                {
                    TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                    int multiplier = timeWell.decreaseTimeSandMultiplier();
                    if(world.isRemote)
                    {
                        player.addChatComponentMessage(new ChatComponentText("Now charging time sand in increments of " + "\u00A7e" + multiplier));
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityTimeWell();
    }
}
