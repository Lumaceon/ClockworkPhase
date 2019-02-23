package lumaceon.mods.clockworkphase.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBakerModel extends CustomBakeModel {

    private TextureAtlasSprite particle;
    private final Map<EnumFacing, List<BakedQuad>> quads = new HashMap<>();

    public ModelBakerModel(TextureAtlasSprite particle, Map<EnumFacing, BakedQuad> quads) {
        this.particle = particle;
        for (Map.Entry<EnumFacing, BakedQuad> quad : quads.entrySet()) {
            ArrayList<BakedQuad> list = new ArrayList<>();
            list.add(quad.getValue());
            this.quads.put(quad.getKey(), list);
        }
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return side == null ? Collections.emptyList() : quads.get(side);
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return particle;
    }
}
