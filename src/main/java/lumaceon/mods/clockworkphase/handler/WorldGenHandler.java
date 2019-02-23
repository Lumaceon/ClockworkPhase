package lumaceon.mods.clockworkphase.handler;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
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
        ExtractorAreas.ExtractorArea area = ExtractorAreas.getAreasFromWorld(event.getWorld(), Phases.EARTH).getValidArea(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
        if(area != null)
        {
            TileEntity te = event.getWorld().getTileEntity(new BlockPos(area.extractorX, area.extractorY, area.extractorZ));
            if(te instanceof TileEntityExtractor)
            {
                if(((TileEntityExtractor) te).applyEffect(Phases.EARTH))
                {
                    PacketHandler.INSTANCE.sendToAllAround(new MessageDoublePositionParticleSpawn(event.getPos().getX() + 0.5, event.getPos().getY() + 0.5, event.getPos().getZ() + 0.5, area.extractorX + 0.5, area.extractorY + 0.5, area.extractorZ + 0.5, 0), new NetworkRegistry.TargetPoint(event.getWorld().provider.getDimension(), event.getPos().getX() + 0.5, event.getPos().getY() + 0.5, event.getPos().getZ() + 0.5, 64));
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }
}