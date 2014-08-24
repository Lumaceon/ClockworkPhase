package lumaceon.mods.clockworkphase.eventhandlers;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lumaceon.mods.clockworkphase.util.Logger;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;

public class GrowthHandler
{
    @SubscribeEvent
    public void onSaplingGrow(SaplingGrowTreeEvent event)
    {
        event.setResult(Event.Result.DENY);
        Logger.info("Sapling Slapling");
    }
}
