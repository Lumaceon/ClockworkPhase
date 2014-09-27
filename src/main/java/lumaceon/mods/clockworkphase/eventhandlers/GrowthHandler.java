package lumaceon.mods.clockworkphase.eventhandlers;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.network.MessageGrowthAbsorption;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;

public class GrowthHandler
{
    @SubscribeEvent
    public void onSaplingGrow(SaplingGrowTreeEvent event)
    {
        int radiusMinusOne = 5;
        int[] extractorLocation = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

        for(int x = event.x - radiusMinusOne; !isExtractorFound(extractorLocation[0]) && x <= event.x + radiusMinusOne; x++)
        {
            for(int z = event.z - radiusMinusOne; !isExtractorFound(extractorLocation[0]) && z <= event.z + radiusMinusOne; z++)
            {
                for(int y = event.y - 2; !isExtractorFound(extractorLocation[0]) && y <= event.y; y++)
                {
                    if(event.world.getBlock(x, y, z).equals(ModBlocks.growthExtractor))
                    {
                        extractorLocation[0] = x;
                        extractorLocation[1] = y;
                        extractorLocation[2] = z;
                    }
                }
            }
        }

        if(isExtractorFound(extractorLocation[0]))
        {
            PacketHandler.INSTANCE.sendToAllAround(new MessageGrowthAbsorption(event.x + 0.5, event.y + 0.5, event.z + 0.5, extractorLocation[0], extractorLocation[1], extractorLocation[2]), new NetworkRegistry.TargetPoint(event.world.provider.dimensionId, event.x + 0.5, event.y + 0.5, event.z + 0.5, 80));
            event.setResult(Event.Result.DENY);
        }
    }

    private boolean isExtractorFound(int location) {return !(location == Integer.MIN_VALUE);}
}