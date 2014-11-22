package lumaceon.mods.clockworkphase.block.tileentity;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.network.MessageTimeSandCapacitorSync;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;

public class TileEntityTimeSandCapacitor extends TileEntityClockworkPhase implements ITimeSandTile
{
    /** Measured in mb (1/1000th of a bucket) **/
    protected int timeSandStored = 0;

    public IMessage getCustomMessage()
    {
        return new MessageTimeSandCapacitorSync(this);
    }

    @Override
    public int getMaxTimeSandCapacity()
    {
        return MechanicTweaker.MAX_TS_DEFAULT;
    }

    /**
     * Method used to add time sand to this tile entity.
     * @param amount
     * @return The amount of overflow that couldn't be added to this tile entity.
     */
    @Override
    public int addTimeSand(int amount)
    {
        if(this.worldObj.isRemote)
        {
            return 0;
        }

        if(timeSandStored + amount >= getMaxTimeSandCapacity())
        {
            int overflow = (timeSandStored + amount) - getMaxTimeSandCapacity();
            timeSandStored = getMaxTimeSandCapacity();
            markDirty();
            PacketHandler.INSTANCE.sendToAllAround(getCustomMessage(), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 256));
            return overflow;
        }
        else if(amount <= 0)
        {
            return 0;
        }
        else
        {
            timeSandStored += amount;
            markDirty();
            PacketHandler.INSTANCE.sendToAllAround(getCustomMessage(), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 256));
            return 0;
        }
    }

    /**
     * Method used to remove time sand from this tile entity.
     * @param amount The amount requested.
     * @return The amount of time sand that could actually be removed.
     */
    @Override
    public int removeTimeSand(int amount)
    {
        if(this.worldObj.isRemote)
        {
            return 0;
        }

        if(amount <= 0)
        {
            return 0;
        }
        else if(timeSandStored - amount <= 0)
        {
            int amountRemoved = timeSandStored;
            timeSandStored = 0;
            markDirty();
            PacketHandler.INSTANCE.sendToAllAround(getCustomMessage(), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 256));
            return amountRemoved;
        }
        else
        {
            timeSandStored -= amount;
            markDirty();
            PacketHandler.INSTANCE.sendToAllAround(getCustomMessage(), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 256));
            return amount;
        }
    }

    @Override
    public void setTimeSandUnsynced(int amount)
    {
        if(amount >= getMaxTimeSandCapacity())
        {
            this.timeSandStored = getMaxTimeSandCapacity();
        }
        else
        {
            this.timeSandStored = amount;
        }
    }

    @Override
    public int getTimeSand()
    {
        return this.timeSandStored;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("time_sand_storage", this.timeSandStored);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.timeSandStored = nbt.getInteger("time_sand_storage");
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(getCustomMessage());
    }
}
