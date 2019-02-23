package lumaceon.mods.clockworkphase.item;

import lumaceon.mods.clockworkphase.custom.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;

public class ItemTimeSandBucket extends ItemBucket implements IHasModel
{
    public ItemTimeSandBucket(Block fluidBlock)
    {
        super(fluidBlock);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons()
    {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
//        this.itemIcon = registry.registerIcon(this.getTranslationKey().substring(this.getTranslationKey().indexOf(".") + 1));
    }
}
