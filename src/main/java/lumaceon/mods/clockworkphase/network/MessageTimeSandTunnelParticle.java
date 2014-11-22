package lumaceon.mods.clockworkphase.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.client.particle.entityfx.EntityTimeSandTunnelFX;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import net.minecraft.client.particle.EntityFX;

public class MessageTimeSandTunnelParticle implements IMessageHandler<MessageTimeSandTunnelParticle, IMessage>, IMessage
{
    double x, y, z, targetX, targetY, targetZ;

    public MessageTimeSandTunnelParticle() {}

    public MessageTimeSandTunnelParticle(double x, double y, double z, double targetX, double targetY, double targetZ)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeDouble(targetX);
        buf.writeDouble(targetY);
        buf.writeDouble(targetZ);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.targetX = buf.readDouble();
        this.targetY = buf.readDouble();
        this.targetZ = buf.readDouble();
    }

    @Override
    public IMessage onMessage(MessageTimeSandTunnelParticle message, MessageContext ctx)
    {
        EntityFX particle = new EntityTimeSandTunnelFX(ClockworkPhase.proxy.getStaticWorld(), message.x, message.y, message.z, message.targetX, message.targetY, message.targetZ);
        ClientProxy.particleGenerator.spawnParticle(particle, 64.0F);
        return null;
    }
}
