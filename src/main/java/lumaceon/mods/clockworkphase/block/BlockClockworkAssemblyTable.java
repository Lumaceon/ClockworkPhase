package lumaceon.mods.clockworkphase.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockClockworkAssemblyTable extends BlockClockworkPhaseAbstract
{
    public IIcon topIcon;

    public BlockClockworkAssemblyTable(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float f0, float f1, float f2)
    {
        if(!world.isRemote && !player.isSneaking())
        {
            player.openGui(ClockworkPhase.instance, 4, world, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int meta)
    {
        if(meta != 0 && meta != 1)
        {
            return this.blockIcon;
        }
        else
        {
            return this.topIcon;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister registry)
    {
        this.blockIcon = registry.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
        this.topIcon = registry.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "Top");
    }
}
