package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.custom.IHasModel;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.lib.Names;
import lumaceon.mods.clockworkphase.lib.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTimeSand extends BlockFluidClassic implements IHasModel
{
    public BlockTimeSand(Fluid fluid, Material material)
    {
        super(fluid, material);
        this.setLightLevel(15);
        this.setLightOpacity(0);
        setCreativeTab(ClockworkPhase.instance.creativeTabClockworkPhase);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons() {
        ModelResourceLocation milkLocation = new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, "fluid"), Names.TIME_SAND);
        Item milk = Item.getItemFromBlock(this);

        ModelBakery.registerItemVariants(milk);
        ModelLoader.setCustomMeshDefinition(milk, stack -> milkLocation);
        ModelLoader.setCustomStateMapper(this, new StateMapperBase() {
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return milkLocation;
            }
        });
    }
}
