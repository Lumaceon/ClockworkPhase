package lumaceon.mods.clockworkphase.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MessageMultitoolGui implements IMessageHandler<MessageMultitoolGui, IMessage>, IMessage
{
    public int buttonId;

    public MessageMultitoolGui() {}

    public MessageMultitoolGui(int buttonId)
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
    public IMessage onMessage(MessageMultitoolGui message, MessageContext ctx)
    {
        if(ctx.side.isServer() && ctx.getServerHandler().player != null)
        {
            EntityPlayer player = ctx.getServerHandler().player;
            ItemStack is = player.inventory.getCurrentItem().copy();
            if(!is.isEmpty())
            {
                if(NBTHelper.hasTag(is, NBTTags.TEMPORAL_ITEMS))
                {
                    ItemStack[] inventory = NBTHelper.getInventoryFromNBTTag(is, NBTTags.TEMPORAL_ITEMS);
                    if(message.buttonId >= 0 && message.buttonId < inventory.length)
                    {
                        ItemStack newItem = inventory[message.buttonId];
                        NBTHelper.removeTag(is, NBTTags.TEMPORAL_ITEMS);
                        inventory[message.buttonId] = is;
                        NBTHelper.setNBTTagListFromInventory(newItem, NBTTags.TEMPORAL_ITEMS, inventory);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, newItem);
                    }
                }
            }
        }
        return null;
    }
}
