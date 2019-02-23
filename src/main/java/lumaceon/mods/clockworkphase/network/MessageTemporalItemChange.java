package lumaceon.mods.clockworkphase.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lumaceon.mods.clockworkphase.item.construct.ITemporalChange;
import lumaceon.mods.clockworkphase.item.construct.abstracts.ITimeSand;
import lumaceon.mods.clockworkphase.util.Logger;
import lumaceon.mods.clockworkphase.util.TimeSandHelper;
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
        if(ctx.side.isServer() && ctx.getServerHandler().player != null)
        {
            EntityPlayer player = ctx.getServerHandler().player;
            ItemStack is = player.inventory.getCurrentItem();
            if(!is.isEmpty())
            {
                if(is.getItem() instanceof ITemporalChange)
                {
                    boolean timeSand = false;
                    if(TimeSandHelper.getTimeSandFromInventory(player.inventory) > 0)
                    {
                        timeSand = true;
                    }
                    if(is.getItem() instanceof ITimeSand)
                    {
                        if(((ITimeSand) is.getItem()).getTimeSand(is) > 0)
                        {
                            timeSand = true;
                        }
                    }

                    if(timeSand)
                    {
                        ItemStack newItem = new ItemStack(((ITemporalChange) is.getItem()).getItemChangeTo());
                        newItem.setTagCompound(is.getTagCompound());
                        newItem.setItemDamage(is.getItemDamage());
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, newItem);
                    }
                }
            }
        }
        return null;
    }
}
