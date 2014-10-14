package lumaceon.mods.clockworkphase.block.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityMemoryWell extends TileEntityClockworkPhase
{
    public int timeSandStored = 0;

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("memory_count", this.timeSandStored);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.timeSandStored = nbt.getInteger("time_sand_storage");
    }
}
