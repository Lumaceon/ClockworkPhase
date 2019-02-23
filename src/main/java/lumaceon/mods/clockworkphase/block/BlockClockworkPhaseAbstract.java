package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.custom.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockClockworkPhaseAbstract extends Block implements IHasModel
{
    public BlockClockworkPhaseAbstract(Material material)
    {
        super(material);
        this.setCreativeTab(ClockworkPhase.instance.creativeTabClockworkPhase);
        this.setHardness(3.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons()
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
//        this.blockIcon = registry.registerIcon(this.getTranslationKey().substring(this.getTranslationKey().indexOf(".") + 1));
    }
}
