package lumaceon.mods.clockworkphase.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockHourglassLight extends BlockClockworkPhase
{
    public BlockHourglassLight(Material material)
    {
        super(material);
        this.setCreativeTab(null);
        this.setLightOpacity(0);
        this.setBlockBounds(0.35F, 0.35F, 0.35F, 0.65F, 0.65F, 0.65F);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if(world.getBlock(x, y, z) instanceof BlockHourglassLight)
        {
            return world.getBlockMetadata(x, y, z);
        }
        return 0;
    }

    @Override
    public int quantityDropped(Random p_149745_1_)
    {
        return 0;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
}
