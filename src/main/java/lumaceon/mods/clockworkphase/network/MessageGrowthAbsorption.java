package lumaceon.mods.clockworkphase.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.client.particle.sequence.ParticleSequenceGrowthAbsorption;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;

public class MessageGrowthAbsorption implements IMessageHandler<MessageGrowthAbsorption, IMessage>, IMessage
{
    public double[] from = new double[3];
    public double[] to = new double[3];

    public MessageGrowthAbsorption() {}

    public MessageGrowthAbsorption(double xFrom, double yFrom, double zFrom, double xTo, double yTo, double zTo)
    {
        this.from[0] = xFrom;
        this.from[1] = yFrom;
        this.from[2] = zFrom;

        this.to[0] = xTo;
        this.to[1] = yTo;
        this.to[2] = zTo;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeDouble(this.from[0]);
        buf.writeDouble(this.from[1]);
        buf.writeDouble(this.from[2]);

        buf.writeDouble(this.to[0]);
        buf.writeDouble(this.to[1]);
        buf.writeDouble(this.to[2]);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.from[0] = buf.readDouble();
        this.from[1] = buf.readDouble();
        this.from[2] = buf.readDouble();

        this.to[0] = buf.readDouble();
        this.to[1] = buf.readDouble();
        this.to[2] = buf.readDouble();
    }

    @Override
    public IMessage onMessage(MessageGrowthAbsorption message, MessageContext ctx)
    {
        ClientProxy.particleGenerator.spawnParticleSequence(new ParticleSequenceGrowthAbsorption(ClientProxy.particleGenerator, message.from[0], message.from[1], message.from[2], message.to[0], message.to[1], message.to[2]));
        return null;
    }
}
