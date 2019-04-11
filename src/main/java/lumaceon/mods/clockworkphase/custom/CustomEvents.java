package lumaceon.mods.clockworkphase.custom;

import java.util.HashMap;
import java.util.Map;

import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.lib.BlockPatterns;
import lumaceon.mods.clockworkphase.lib.Reference;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class CustomEvents {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre e) {
        for (int i = 0; i < 96; i++) {
            e.getMap().registerSprite(new ResourceLocation(Reference.MOD_ID, "blocks/celestial_compass_sub/" + i));
        }
        for (int i = 0; i < 8; i++) {
            e.getMap().registerSprite(new ResourceLocation(Reference.MOD_ID, "blocks/celestial_compass/" + i));
        }
    }

    @SideOnly(Side.CLIENT)
    public static Map<IBlockState, ModelResourceLocation> MODELS_COMPASS_SUB = new HashMap<>(96);
    public static IBlockState[] STATES_COMPASS_SUB = new IBlockState[96];

    @SideOnly(Side.CLIENT)
    public static IBakedModel[] MODELS_COMPASS = new IBakedModel[8];

    static {
        for (int i = 0; i < 96; i++) {
            PropertyBool bool = PropertyBool.create("state_sub_" + i);
            BlockStateContainer stateContainer = new BlockStateContainer(ModBlocks.celestialCompassSub, bool, BlockHorizontal.FACING);
            STATES_COMPASS_SUB[i] = stateContainer.getValidStates().get(BlockPatterns.CELESTIAL_COMPASS[i].meta * 2 + 1);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent e) {
        for (int i = 0; i < 96; i++) {
            ModelResourceLocation mrl = new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, "compass_sub"), "state_sub_" + i);
            IBakedModel model = ModelBaker.bakeModel("celestial_compass_sub/" + i, "celestial_compass_sub");
            e.getModelRegistry().putObject(mrl, model);
            MODELS_COMPASS_SUB.put(STATES_COMPASS_SUB[i], mrl);
        }
        for (int i = 0; i < 8; i++) {
            MODELS_COMPASS[i] = ModelBaker.bakeModel("celestial_compass/" + i, "celestial_compass");
        }
        IBakedModel compass_model = new CompassBakeModel();
        for (ModelResourceLocation mrl : e.getModelRegistry().getKeys()) {
            if (mrl.getNamespace().equals(Reference.MOD_ID) && !mrl.getVariant().equals("inventory")) {
                if (mrl.getPath().equals("celestial_compass")) {
                    e.getModelRegistry().putObject(mrl, compass_model);
                }
            }
        }
    }
}
