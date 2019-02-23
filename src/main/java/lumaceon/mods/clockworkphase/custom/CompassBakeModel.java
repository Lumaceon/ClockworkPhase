package lumaceon.mods.clockworkphase.custom;

import java.util.List;

import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.util.PhaseHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CompassBakeModel extends CustomBakeModel {

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        int index = PhaseHelper.getPhaseForWorld(ClockworkPhase.proxy.getStaticWorld()).ordinal();
        return CustomEvents.MODELS_COMPASS[index].getQuads(state, side, rand);
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return CustomEvents.MODELS_COMPASS[0].getParticleTexture();
    }

}
