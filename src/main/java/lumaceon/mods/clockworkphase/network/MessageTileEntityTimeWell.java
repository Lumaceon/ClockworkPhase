package lumaceon.mods.clockworkphase.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeWell;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityTimeWell implements IMessageHandler<MessageTileEntityTimeWell, IMessage>, IMessage
{
    int x, y, z;
    int timeSand;
    int timeSandMultiplier;

    public MessageTileEntityTimeWell() {};

    public MessageTileEntityTimeWell(TileEntityTimeWell timeWell)
    {
        x = timeWell.xCoord;
        y = timeWell.yCoord;
        z = timeWell.zCoord;
        timeSand = timeWell.getTimeSand();
        timeSandMultiplier = timeWell.timeSandMultiplier;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(timeSand);
        buf.writeInt(timeSandMultiplier);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        timeSand = buf.readInt();
        timeSandMultiplier = buf.readInt();
    }

    @Override
    public IMessage onMessage(MessageTileEntityTimeWell message, MessageContext ctx)
    {
        TileEntity te = ClockworkPhase.proxy.getStaticWorld().getTileEntity(message.x, message.y, message.z);

        if(te != null && te instanceof TileEntityTimeWell)
        {
            TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
            timeWell.setTimeSandUnsynced(message.timeSand);
            timeWell.timeSandMultiplier = message.timeSandMultiplier;
        }
        return null;
    }
}
