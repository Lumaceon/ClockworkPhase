package lumaceon.mods.clockworkphase.client.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModFluids;
import net.minecraftforge.client.event.TextureStitchEvent;

public class TextureStitchHandler
{
    @SubscribeEvent
    public void postStitch(TextureStitchEvent.Post event)
    {
        ModFluids.timeSand.setIcons(ModBlocks.timeSand.getBlockTextureFromSide(0), ModBlocks.timeSand.getBlockTextureFromSide(1));
    }
}
