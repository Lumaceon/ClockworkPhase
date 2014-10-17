package lumaceon.mods.clockworkphase.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.client.particle.entityfx.EntityBlockInTheWayFX;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import net.minecraft.client.particle.EntityFX;

public class MessageBlockInTheWay implements IMessageHandler<MessageBlockInTheWay, IMessage>, IMessage
{
    double x, y, z;

    public MessageBlockInTheWay() {}

    public MessageBlockInTheWay(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
    }

    @Override
    public IMessage onMessage(MessageBlockInTheWay message, MessageContext ctx)
    {
        EntityFX particle = new EntityBlockInTheWayFX(ClockworkPhase.proxy.getStaticWorld(), message.x, message.y, message.z);
        ClientProxy.particleGenerator.spawnParticle(particle, 48.0F);
        return null;
    }
}
