package lumaceon.mods.clockworkphase.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.client.gui.container.ContainerClockworkAssemblyTableMainspring;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class MessageMainspringAdditionButton implements IMessageHandler<MessageMainspringAdditionButton, IMessage>, IMessage
{
    public MessageMainspringAdditionButton() {}

    @Override
    public void toBytes(ByteBuf buf) {}

    @Override
    public void fromBytes(ByteBuf buf) {}

    @Override
    public IMessage onMessage(MessageMainspringAdditionButton message, MessageContext ctx)
    {
        if(ctx.side.isServer() && ctx.getServerHandler().player != null)
        {
            EntityPlayer player = ctx.getServerHandler().player;
            Container container = player.openContainer;
            if(container instanceof ContainerClockworkAssemblyTableMainspring)
            {
                ContainerClockworkAssemblyTableMainspring mainspringContainer = (ContainerClockworkAssemblyTableMainspring)container;
                mainspringContainer.addToMainspring();
            }
        }
        return null;
    }
}
