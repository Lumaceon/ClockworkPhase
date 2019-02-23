package lumaceon.mods.clockworkphase.custom;

import com.google.common.collect.ImmutableMap;

import lumaceon.mods.clockworkphase.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.block.model.ModelRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.PerspectiveMapWrapper;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Vector3f;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class ModelBaker {

    private static final float[] UVS = new float[]{0.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 16.0F, 16.0F};
    private static final FaceBakery FACE_BAKERY = new FaceBakery();
    private static final Vector3f POS_FROM = new Vector3f(0.0F, 0.0F, 0.0F);
    private static final Vector3f POS_TO = new Vector3f(16.0F, 16.0F, 16.0F);
    private static final ModelRotation modelRotation = ModelRotation.X0_Y0;

    public static IBakedModel bakeModel(String locationTop, String locationSide) {
        TextureMap map = Minecraft.getMinecraft().getTextureMapBlocks();
        TextureAtlasSprite top = map.getAtlasSprite(new ResourceLocation(Reference.MOD_ID, "blocks/" + locationTop).toString());
        TextureAtlasSprite side = map.getAtlasSprite(new ResourceLocation(Reference.MOD_ID, "blocks/" + locationSide).toString());

        Map<EnumFacing, BakedQuad> faceQuads = new HashMap<>();
        for (EnumFacing facing : EnumFacing.VALUES) {
            BlockFaceUV blockFaceUV = new BlockFaceUV(UVS, 0);
            BlockPartFace blockPartFace = new BlockPartFace(facing, 0, "", blockFaceUV);
            TextureAtlasSprite sprite = facing.getAxis() == EnumFacing.Axis.Y ? top : side;
            faceQuads.put(facing, FACE_BAKERY.makeBakedQuad(POS_FROM, POS_TO, blockPartFace, sprite, facing, modelRotation, null, true, true));
        }
        return new ModelBakerModel(side, faceQuads);
    }

    private static final String MODEL_BLOCK = "{'parent':'block/block'}".replaceAll("'", "\"");

    private static ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> defaultTransforms;

    public static ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> getDefaultTransforms() {
        if (defaultTransforms == null) {
            defaultTransforms = PerspectiveMapWrapper.getTransforms(ModelBlock.deserialize(MODEL_BLOCK).getAllTransforms());
        }
        return defaultTransforms;
    }
}
