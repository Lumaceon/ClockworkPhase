package lumaceon.mods.clockworkphase.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import net.minecraft.entity.player.EntityPlayer;

public class MessageOpenGui implements IMessageHandler<MessageOpenGui, IMessage>, IMessage
{
    int id, x, y, z;

    public MessageOpenGui()
    {

    }

    public MessageOpenGui(int id, int x, int y, int z)
    {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.id);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.id = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public IMessage onMessage(MessageOpenGui message, MessageContext ctx)
    {
        if(ctx.side.isServer() && ctx.getServerHandler().player != null)
        {
            EntityPlayer player = ctx.getServerHandler().player;
            if(player.getDistance((double)message.x, (double)message.y, (double)message.z) < 10 && message.id >= 4 && message.id <= 6)
            {
                player.openGui(ClockworkPhase.instance, message.id, player.world, message.x, message.y, message.z);
            }
        }
        return null;
    }
}
