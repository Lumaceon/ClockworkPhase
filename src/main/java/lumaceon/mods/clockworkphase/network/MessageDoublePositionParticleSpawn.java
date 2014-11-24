package lumaceon.mods.clockworkphase.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;

public class MessageDoublePositionParticleSpawn implements IMessageHandler<MessageDoublePositionParticleSpawn, IMessage>, IMessage
{
    double x, y, z, auxX, auxY, auxZ;
    int id;

    public MessageDoublePositionParticleSpawn() {}

    public MessageDoublePositionParticleSpawn(double x, double y, double z, double auxX, double auxY, double auxZ, int id)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.auxX = auxX;
        this.auxY = auxY;
        this.auxZ = auxZ;
        this.id = id;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeDouble(auxX);
        buf.writeDouble(auxY);
        buf.writeDouble(auxZ);
        buf.writeInt(id);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.auxX = buf.readDouble();
        this.auxY = buf.readDouble();
        this.auxZ = buf.readDouble();
        this.id = buf.readInt();
    }

    @Override
    public IMessage onMessage(MessageDoublePositionParticleSpawn message, MessageContext ctx)
    {
        switch(message.id)
        {
            case 0: //Growth Absorption.
                ClientProxy.particleGenerator.SPAWNER.spawnGrowthAbsorptionParticle(message.x, message.y, message.z, message.auxX, message.auxY, message.auxZ);
                break;
            case 1: //Time sand tunnel.
                ClientProxy.particleGenerator.SPAWNER.spawnTimeSandTunnelParticle(message.x, message.y, message.z, message.auxX, message.auxY, message.auxZ);
                break;
        }
        return null;
    }
}
