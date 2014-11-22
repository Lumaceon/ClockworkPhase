package lumaceon.mods.clockworkphase.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.item.construct.ITemporalChange;
import lumaceon.mods.clockworkphase.item.construct.abstracts.ITimeSand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MessageTemporalItemChange implements IMessageHandler<MessageTemporalItemChange, IMessage>, IMessage
{

    @Override
    public void fromBytes(ByteBuf buf)
    {

    }

    @Override
    public void toBytes(ByteBuf buf)
    {

    }

    @Override
    public IMessage onMessage(MessageTemporalItemChange message, MessageContext ctx)
    {
        if(ctx.side.isServer() && ctx.getServerHandler().playerEntity != null)
        {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            ItemStack is = player.inventory.getCurrentItem();
            if(is != null)
            {
                if(is.getItem() instanceof ITemporalChange)
                {
                    //Check for ITimeSand items, not allowing change if they're out of time sand.\\
                    if(is.getItem() instanceof ITimeSand)
                    {
                        if(((ITimeSand)is.getItem()).getTimeSandFromInventory(player.inventory) <= 0 && ((ITimeSand)is.getItem()).getTimeSand(is) <= 0)
                        {
                            return null;
                        }
                    }
                    //Check for ITimeSand items, not allowing change if they're out of time sand.\\

                    ItemStack newItem = new ItemStack(((ITemporalChange) is.getItem()).getItemChangeTo());
                    newItem.setTagCompound(is.stackTagCompound);
                    newItem.setItemDamage(is.getItemDamage());
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, newItem);
                }
            }
        }
        return null;
    }
}
