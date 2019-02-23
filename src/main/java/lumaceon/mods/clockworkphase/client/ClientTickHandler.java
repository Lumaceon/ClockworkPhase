package lumaceon.mods.clockworkphase.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.client.lib.GlobalReferences;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import net.minecraft.client.Minecraft;

public class ClientTickHandler
{
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if(GlobalReferences.globalTimer % 20 == 0)
        {
            ClockworkPhase.proxy.setRenderingForPlayer(Minecraft.getMinecraft().player);
        }
        ClientProxy.particleGenerator.updateParticleSequences();
        GlobalReferences.globalTimer++;
    }
}
