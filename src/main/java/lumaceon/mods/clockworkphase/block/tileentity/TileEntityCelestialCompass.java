package lumaceon.mods.clockworkphase.block.tileentity;

import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.lib.BlockPatterns;
import lumaceon.mods.clockworkphase.lib.GlobalPhaseReference;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityCelestialCompass extends TileEntityClockworkPhase
{
    public int blocksToPlace = 96;

    @Override
    public void updateEntity()
    {
        if(this.worldObj.getWorldTime() % GlobalPhaseReference.phaseDuration == 0)
        {
            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }

        if(!this.worldObj.isRemote)
        {
            if(blocksToPlace == 0)
            {
                for(int n = 0; n < BlockPatterns.CELESTIAL_COMPASS.length; n++)
                {
                    int x, y, z;
                    x = this.xCoord + BlockPatterns.CELESTIAL_COMPASS[n].x;
                    y = this.yCoord + BlockPatterns.CELESTIAL_COMPASS[n].y;
                    z = this.zCoord + BlockPatterns.CELESTIAL_COMPASS[n].z;
                    this.worldObj.markBlockForUpdate(x, y, z);
                    blocksToPlace--;
                }
            }
            else if(blocksToPlace > 0)
            {
                int x, y, z, meta;
                x = this.xCoord + BlockPatterns.CELESTIAL_COMPASS[blocksToPlace - 1].x;
                y = this.yCoord + BlockPatterns.CELESTIAL_COMPASS[blocksToPlace - 1].y;
                z = this.zCoord + BlockPatterns.CELESTIAL_COMPASS[blocksToPlace - 1].z;
                meta = BlockPatterns.CELESTIAL_COMPASS[blocksToPlace - 1].meta;

                this.getWorldObj().setBlock(x, y, z, ModBlocks.celestialCompassSub, meta, 2);
                blocksToPlace--;
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("internal_block_count", this.blocksToPlace);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.blocksToPlace = nbt.getInteger("internal_block_count");
    }
}