package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.custom.IHasModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.init.ModItems;
import net.minecraft.block.BlockOre;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockTemporalWood extends BlockOre implements IHasModel
{
    public BlockTemporalWood()
    {
        super();
        this.setCreativeTab(null);
        this.setHardness(3.0F);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ModItems.nuggetTemporal;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random random)
    {
        return random.nextInt(1) + 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons()
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
//        this.blockIcon = registry.registerIcon(this.getTranslationKey().substring(this.getTranslationKey().indexOf(".") + 1));
    }
}
