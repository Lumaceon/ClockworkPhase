package lumaceon.mods.clockworkphase.custom;

import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityCelestialCompass;
import lumaceon.mods.clockworkphase.util.PhaseHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderTileEntityCelestialCompass extends TileEntitySpecialRenderer<TileEntityCelestialCompass>{

    @Override
    public void render(TileEntityCelestialCompass te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        int index = PhaseHelper.getPhaseForWorld(ClockworkPhase.proxy.getStaticWorld()).ordinal();
        if (te.currentRender != index) {
            te.currentRender = index;
            IBlockState state = te.getWorld().getBlockState(te.getPos());
            te.getWorld().notifyBlockUpdate(te.getPos(), state, state, 3);
        }
    }
}
