package lumaceon.mods.clockworkphase.custom;

import lumaceon.mods.clockworkphase.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IHasModel {

    @SideOnly(Side.CLIENT)
    default void registerBlockIcons() {

    }

    @SideOnly(Side.CLIENT)
    default void registerIcons() {

    }

    default <T> T register(String name) {
        if (this instanceof Block)
            register(name, ItemBlock.class);
        else {
            ForgeRegistries.ITEMS.register(((Item) this).setTranslationKey(Reference.MOD_ID + "." + name).setRegistryName(Reference.MOD_ID, name));
            if (FMLCommonHandler.instance().getEffectiveSide().isClient())
                registerIcons();
        }
        return (T) this;
    }

    default <T> T register(String name, Class<? extends ItemBlock> itemclass) {
        Block block = (Block) this;
        ForgeRegistries.BLOCKS.register(block.setTranslationKey(Reference.MOD_ID + "." + name).setRegistryName(Reference.MOD_ID, name));
        try {
            ForgeRegistries.ITEMS.register(itemclass.getConstructor(Block.class).newInstance(block).setRegistryName(block.getRegistryName()));
        } catch (Exception e) {
            throw new LoaderException("Failed to register block!", e);
        }
        if (FMLCommonHandler.instance().getEffectiveSide().isClient())
            registerBlockIcons();
        return (T) this;
    }
}
