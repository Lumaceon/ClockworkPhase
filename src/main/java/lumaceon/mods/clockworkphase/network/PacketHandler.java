package lumaceon.mods.clockworkphase.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import lumaceon.mods.clockworkphase.lib.Reference;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

    public static void init()
    {
        INSTANCE.registerMessage(MessageGrowthAbsorption.class, MessageGrowthAbsorption.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageTimeSandCapacitorSync.class, MessageTimeSandCapacitorSync.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(MessageBlockInTheWay.class, MessageBlockInTheWay.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(MessageMultitoolGui.class, MessageMultitoolGui.class, 3, Side.SERVER);
        INSTANCE.registerMessage(MessageTemporalItemChange.class, MessageTemporalItemChange.class, 4, Side.SERVER);
        INSTANCE.registerMessage(MessageLifePantsLook.class, MessageLifePantsLook.class, 5, Side.SERVER);
        INSTANCE.registerMessage(MessagePocketWatchGui.class, MessagePocketWatchGui.class, 6, Side.SERVER);
        INSTANCE.registerMessage(MessageTimeSandTunnelParticle.class, MessageTimeSandTunnelParticle.class, 7, Side.CLIENT);
    }
}
