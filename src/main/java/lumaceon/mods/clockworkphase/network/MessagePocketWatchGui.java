package lumaceon.mods.clockworkphase.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MessagePocketWatchGui implements IMessageHandler<MessagePocketWatchGui, IMessage>, IMessage
{
    public int buttonId;

    public MessagePocketWatchGui() {}

    public MessagePocketWatchGui(int buttonId)
    {
        this.buttonId = buttonId;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.buttonId);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.buttonId = buf.readInt();
    }

    @Override
    public IMessage onMessage(MessagePocketWatchGui message, MessageContext ctx)
    {
        if(ctx.side.isServer() && ctx.getServerHandler().playerEntity != null)
        {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            ItemStack is = player.inventory.getCurrentItem();
            if(is != null)
            {
                if(NBTHelper.hasTag(is, NBTTags.POCKET_WATCH_MODULES))
                {
                    ItemStack[] inventory = NBTHelper.getInventoryFromNBTTag(is, NBTTags.POCKET_WATCH_MODULES);
                    if(message.buttonId >= 0 && message.buttonId < inventory.length)
                    {
                        NBTHelper.setBoolean(inventory[message.buttonId], NBTTags.ACTIVE, !NBTHelper.getBoolean(inventory[message.buttonId], NBTTags.ACTIVE));
                        NBTHelper.setNBTTagListFromInventory(is, NBTTags.POCKET_WATCH_MODULES, inventory);
                    }
                }
            }
        }
        return null;
    }
}
