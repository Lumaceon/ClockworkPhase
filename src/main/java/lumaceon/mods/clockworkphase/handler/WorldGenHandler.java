package lumaceon.mods.clockworkphase.handler;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import lumaceon.mods.clockworkphase.block.extractor.ExtractorAreas;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.network.MessageDoublePositionParticleSpawn;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;

public class WorldGenHandler
{
    @SubscribeEvent
    public void onSaplingGrow(SaplingGrowTreeEvent event)
    {
        ExtractorAreas.ExtractorArea area = ExtractorAreas.getAreasFromWorld(event.world, Phases.EARTH).getValidArea(event.world, event.x, event.y, event.z);
        if(area != null)
        {
            TileEntity te = event.world.getTileEntity(area.extractorX, area.extractorY, area.extractorZ);
            if(te != null && te instanceof TileEntityExtractor)
            {
                if(((TileEntityExtractor) te).applyEffect(Phases.EARTH))
                {
                    PacketHandler.INSTANCE.sendToAllAround(new MessageDoublePositionParticleSpawn(event.x + 0.5, event.y + 0.5, event.z + 0.5, area.extractorX + 0.5, area.extractorY + 0.5, area.extractorZ + 0.5, 0), new NetworkRegistry.TargetPoint(event.world.provider.dimensionId, event.x + 0.5, event.y + 0.5, event.z + 0.5, 64));
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }
}