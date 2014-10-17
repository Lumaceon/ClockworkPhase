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
        INSTANCE.registerMessage(MessageTileEntityTimeWell.class, MessageTileEntityTimeWell.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(MessageBlockInTheWay.class, MessageBlockInTheWay.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(MessageMultitoolGui.class, MessageMultitoolGui.class, 3, Side.SERVER);
    }
}
