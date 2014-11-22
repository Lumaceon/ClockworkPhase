package lumaceon.mods.clockworkphase.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import lumaceon.mods.clockworkphase.block.extractor.ExtractorAreas;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import net.minecraft.tileentity.TileEntity;

public class TickHandler
{
    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event)
    {
        if(event.world.getWorldTime() % 600 == 0) //Once every 30 seconds.
        {
            TileEntity te;
            for(int n = 0; n < ExtractorAreas.EXTRACTORS.length; n++) //For every element.
            {
                ExtractorAreas area = (ExtractorAreas)ExtractorAreas.EXTRACTORS[n].get(event.world.provider.dimensionId); //Gets the areas for the world.
                if(area != null)
                {
                    for(int g = 0; g < area.areas.size(); g++) //For each area.
                    {
                        if(area.areas.get(g) != null)
                        {
                            te = event.world.getTileEntity(area.areas.get(g).extractorX, area.areas.get(g).extractorY, area.areas.get(g).extractorZ);
                            if(te != null) //There is no tile entity anymore.
                            {
                                if(!(te instanceof TileEntityExtractor)) //There is a tile entity, but it's incorrect.
                                {
                                    area.removeArea(area.areas.get(g).id);
                                }
                                else if(((TileEntityExtractor) te).phase.ordinal() != n) //Tile entity exists, but has wrong element.
                                {
                                    area.removeArea(area.areas.get(g).id);
                                }
                                else if(((TileEntityExtractor) te).needsToUpdateArea) //Tile entity is correct, but needs to be updated.
                                {
                                    area.removeArea(area.areas.get(g).id);
                                    ((TileEntityExtractor) te).needsToUpdateArea = false;
                                    ((TileEntityExtractor) te).extractorAreaReady = false;
                                }
                            }
                            else
                            {
                                area.removeArea(area.areas.get(g).id);
                            }
                        }
                    }
                }

            }
        }
    }
}
