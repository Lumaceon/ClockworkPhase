package lumaceon.mods.clockworkphase.block.tileentity;

import cpw.mods.fml.common.network.NetworkRegistry;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.network.MessageTileEntityTimeWell;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;

public class TileEntityTimeWell extends TileEntityClockworkPhase
{
    /** Measured in mb (1/1000th of a bucket) **/
    private int timeSandStored = 0;
    /** Charges valid items by this amount, or less if it lacks time sand **/
    public int timeSandMultiplier = 1000;

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("time_sand_storage", this.timeSandStored);
        nbt.setInteger("ts_multiplier", this.timeSandMultiplier);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.timeSandStored = nbt.getInteger("time_sand_storage");
        this.timeSandMultiplier = nbt.getInteger("ts_multiplier");
    }

    /**
     * Method used to add time sand to this tile entity.
     * @param amount
     * @return The amount of overflow that couldn't be added to this tile entity.
     */
    public int addTimeSand(int amount)
    {
        if(this.worldObj.isRemote)
        {
            return 0;
        }

        if(timeSandStored + amount >= MechanicTweaker.MAX_TS_TIME_WELL)
        {
            int overflow = (timeSandStored + amount) - MechanicTweaker.MAX_TS_TIME_WELL;
            timeSandStored = MechanicTweaker.MAX_TS_TIME_WELL;
            markDirty();
            PacketHandler.INSTANCE.sendToAllAround(new MessageTileEntityTimeWell(this), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 256));
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
            PacketHandler.INSTANCE.sendToAllAround(new MessageTileEntityTimeWell(this), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 256));
            return 0;
        }
    }

    /**
     * Method used to remove time sand from this tile entity.
     * @param amount The amount requested.
     * @return The amount of time sand that could actually be removed.
     */
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
            PacketHandler.INSTANCE.sendToAllAround(new MessageTileEntityTimeWell(this), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 256));
            return amountRemoved;
        }
        else
        {
            timeSandStored -= amount;
            markDirty();
            PacketHandler.INSTANCE.sendToAllAround(new MessageTileEntityTimeWell(this), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 256));
            return amount;
        }
    }

    public void setTimeSandUnsynced(int amount)
    {
        if(amount >= MechanicTweaker.MAX_TS_TIME_WELL)
        {
            this.timeSandStored = MechanicTweaker.MAX_TS_TIME_WELL;
        }
        else
        {
            this.timeSandStored = amount;
        }
    }

    public int getTimeSand()
    {
        return this.timeSandStored;
    }

    /**
     * Tries to multiply the amount of time sand added to the tool on right-click by 10.
     * @return The new time sand multiplier
     */
    public int increaseTimeSandMultiplier()
    {
        if(timeSandMultiplier <= 0)
        {
            timeSandMultiplier = 1;
        }
        else if(timeSandMultiplier * 10 <= 1000000)
        {
            timeSandMultiplier *= 10;
        }
        return timeSandMultiplier;
    }

    /**
     * Tries to divide the amount of time sand added to the tool on right-click by 10.
     * @return The new time sand multiplier
     */
    public int decreaseTimeSandMultiplier()
    {
        if(timeSandMultiplier / 10 < 100)
        {
            timeSandMultiplier = 100;
        }
        else
        {
            timeSandMultiplier /= 10;
        }
        return timeSandMultiplier;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityTimeWell(this));
    }
}
