package lumaceon.mods.clockworkphase.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import lumaceon.mods.clockworkphase.client.lib.GlobalReferences;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import lumaceon.mods.clockworkphase.util.Logger;

public class ClientTickHandler
{
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        ClientProxy.particleGenerator.updateParticleSequences();
        GlobalReferences.globalTimer++;
    }
}
