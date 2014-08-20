package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityWindingBox;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWindingBox extends BlockClockworkPhase implements ITileEntityProvider
{
    public BlockWindingBox(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float f0, float f1, float f2)
    {
        if(!player.isSneaking())
        {
            if(player.getHeldItem() != null && player.getHeldItem().getItem().equals(ModItems.handCrank))
            {
                ItemStack is = ((TileEntityWindingBox)world.getTileEntity(x, y, z)).getStackInSlot(0);

                if(is == null)
                {
                    return false;
                }

                NBTTagCompound nbt = is.getTagCompound();
                if(nbt == null)
                {
                    is.setTagCompound(new NBTTagCompound());
                    nbt = is.getTagCompound();
                }
                nbt.setInteger(NBTTags.TENSION_ENERGY, nbt.getInteger(NBTTags.TENSION_ENERGY) + 50);
                return true;
            }
        }
        return false;
    }



    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityWindingBox();
    }
}
