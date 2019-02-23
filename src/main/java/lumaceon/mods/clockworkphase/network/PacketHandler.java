package lumaceon.mods.clockworkphase.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import lumaceon.mods.clockworkphase.lib.Reference;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

    public static void init()
    {
        INSTANCE.registerMessage(MessageTimeSandCapacitorSync.class, MessageTimeSandCapacitorSync.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageMultitoolGui.class, MessageMultitoolGui.class, 1, Side.SERVER);
        INSTANCE.registerMessage(MessageTemporalItemChange.class, MessageTemporalItemChange.class, 2, Side.SERVER);
        INSTANCE.registerMessage(MessageLifePantsLook.class, MessageLifePantsLook.class, 3, Side.SERVER);
        INSTANCE.registerMessage(MessagePocketWatchGui.class, MessagePocketWatchGui.class, 4, Side.SERVER);
        INSTANCE.registerMessage(MessageParticleSpawn.class, MessageParticleSpawn.class, 5, Side.CLIENT);
        INSTANCE.registerMessage(MessageDoublePositionParticleSpawn.class, MessageDoublePositionParticleSpawn.class, 6, Side.CLIENT);
        INSTANCE.registerMessage(MessageOpenGui.class, MessageOpenGui.class, 7, Side.SERVER);
        INSTANCE.registerMessage(MessageMainspringAdditionButton.class, MessageMainspringAdditionButton.class, 8, Side.SERVER);
    }
}
