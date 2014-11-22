package lumaceon.mods.clockworkphase.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityCelestialCompass;
import lumaceon.mods.clockworkphase.util.PhaseHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCelestialCompass extends BlockClockworkPhaseAbstract implements ITileEntityProvider
{
    public IIcon[] icons = new IIcon[8];

    public BlockCelestialCompass(Material material)
    {
        super(material);
        this.setResistance(1000000.0F);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntityCelestialCompass.destroyCompass(world, x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityCelestialCompass();
    }

    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int meta)
    {
        if(meta != 0 && meta != 1)
        {
            return this.blockIcon;
        }

        return this.icons[PhaseHelper.getPhaseForWorld(ClockworkPhase.proxy.getStaticWorld()).ordinal()];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister registry)
    {
        this.blockIcon = registry.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));

        for(int n = 0; n < 8; n++)
        {
            this.icons[n] = registry.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "/" + n);
        }
    }
}
