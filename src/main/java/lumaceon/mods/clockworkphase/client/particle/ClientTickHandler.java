package lumaceon.mods.clockworkphase.client.particle;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;

public class ClientTickHandler
{
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        ClientProxy.particleGenerator.updateParticleSequences();
    }
}
