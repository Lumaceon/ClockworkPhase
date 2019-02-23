package lumaceon.mods.clockworkphase.network;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeSandCapacitor;
import net.minecraft.tileentity.TileEntity;

public class MessageTimeSandCapacitorSync implements IMessageHandler<MessageTimeSandCapacitorSync, IMessage>, IMessage
{
    int x, y, z;
    int timeSand;

    public MessageTimeSandCapacitorSync() {}

    public MessageTimeSandCapacitorSync(TileEntityTimeSandCapacitor capacitor)
    {
        x = capacitor.getPos().getX();
        y = capacitor.getPos().getY();
        z = capacitor.getPos().getZ();
        timeSand = capacitor.getTimeSand();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(timeSand);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        timeSand = buf.readInt();
    }

    @Override
    public IMessage onMessage(MessageTimeSandCapacitorSync message, MessageContext ctx)
    {
        TileEntity te = ClockworkPhase.proxy.getStaticWorld().getTileEntity(new BlockPos(message.x, message.y, message.z));

        if(te instanceof TileEntityTimeSandCapacitor)
        {
            TileEntityTimeSandCapacitor timeWell = (TileEntityTimeSandCapacitor)te;
            timeWell.setTimeSandUnsynced(message.timeSand);
        }
        return null;
    }
}
