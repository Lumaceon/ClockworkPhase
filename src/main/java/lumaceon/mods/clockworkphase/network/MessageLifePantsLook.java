package lumaceon.mods.clockworkphase.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MessageLifePantsLook implements IMessageHandler<MessageLifePantsLook, IMessage>, IMessage
{
    int x, y, z;

    public MessageLifePantsLook() {}

    public MessageLifePantsLook(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public IMessage onMessage(MessageLifePantsLook message, MessageContext ctx)
    {
        if(ctx.side.isServer())
        {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            World world = player.worldObj;
            if(player.getDistance(message.x, message.y, message.z) <= 8)
            {
                /**if(player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() instanceof ItemChronomancerLeggingsLife)
                {
                    int timeSandRequirement = MechanicTweaker.TIME_SAND_COST_LIFE_LEGGINGS;
                    timeSandRequirement -= ((ITimeSand) player.inventory.armorItemInSlot(1).getItem()).removeTimeSandFromInventory(player.inventory, timeSandRequirement);
                    timeSandRequirement -= ((ITimeSand) player.inventory.armorItemInSlot(1).getItem()).removeTimeSand(player.inventory.armorItemInSlot(1), timeSandRequirement);

                    if(timeSandRequirement <= 0)
                    {
                        ItemDye.applyBonemeal(new ItemStack(Items.dye), world, message.x, message.y, message.z, player);
                    }
                }**/
            }
        }
        return null;
    }
}
