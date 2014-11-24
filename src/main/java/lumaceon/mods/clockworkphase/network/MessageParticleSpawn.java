package lumaceon.mods.clockworkphase.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;

public class MessageParticleSpawn implements IMessageHandler<MessageParticleSpawn, IMessage>, IMessage
{
    double x, y, z;
    int id;

    public MessageParticleSpawn() {}

    public MessageParticleSpawn(double x, double y, double z, int id)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeInt(id);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.id = buf.readInt();
    }

    @Override
    public IMessage onMessage(MessageParticleSpawn message, MessageContext ctx)
    {
        switch(message.id)
        {
            case 0: //Block in the way.
                ClientProxy.particleGenerator.SPAWNER.spawnBlockInTheWayParticle(message.x, message.y, message.z);
                break;
            case 1: //Time sand tool extraction.
                ClientProxy.particleGenerator.SPAWNER.spawnTimeSandExtractionParticles(message.x, message.y, message.z, 12);
                break;
        }
        return null;
    }
}

