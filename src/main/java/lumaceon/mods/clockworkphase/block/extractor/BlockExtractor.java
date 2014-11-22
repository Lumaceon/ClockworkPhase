package lumaceon.mods.clockworkphase.block.extractor;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
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
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockExtractor extends BlockClockworkPhaseAbstract
{
    public BlockExtractor(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float f0, float f1, float f2)
    {
        ItemStack is = player.getHeldItem();
        if(is != null && is.getItem().equals(ModItems.timeTuner))
        {
            int ttx = NBTHelper.getInt(is, NBTTags.CLOCKWORK_PHASE_X);
            int tty = NBTHelper.getInt(is, NBTTags.CLOCKWORK_PHASE_Y);
            int ttz = NBTHelper.getInt(is, NBTTags.CLOCKWORK_PHASE_Z);
            TileEntity timeWell = world.getTileEntity(ttx, tty, ttz);
            if(timeWell != null && timeWell instanceof TileEntityTimeWell)
            {
                if(timeWell.getDistanceFrom(x, y, z) <= 2048)
                {
                    TileEntity te = world.getTileEntity(x, y, z);
                    if(te != null && te instanceof TileEntityExtractor)
                    {
                        TileEntityExtractor extractor = (TileEntityExtractor) te;
                        extractor.timeWellX = ttx;
                        extractor.timeWellY = tty;
                        extractor.timeWellZ = ttz;
                    }
                }
                else if(world.isRemote)
                {
                    player.addChatComponentMessage(new ChatComponentText("The time link is too weak from this distance."));
                }
            }
            return true;
        }
        else if(!world.isRemote && !player.isSneaking())
        {
            FMLNetworkHandler.openGui(player, ClockworkPhase.instance, GUIs.EXTRACTOR_GUI.ordinal(), world, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if(te != null && te instanceof TileEntityExtractor)
        {
            TileEntityExtractor timeWell = (TileEntityExtractor)te;
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
}
