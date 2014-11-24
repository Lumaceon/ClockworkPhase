package lumaceon.mods.clockworkphase.block.tileentity;

import cpw.mods.fml.common.network.NetworkRegistry;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.lib.BlockPatterns;
import lumaceon.mods.clockworkphase.lib.GlobalPhaseReference;
import lumaceon.mods.clockworkphase.network.MessageParticleSpawn;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TileEntityCelestialCompass extends TileEntityClockworkPhase
{
    public static final int BLOCKS_IN_STRUCTURE = 96;
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

                if(worldObj.isAirBlock(x, y, z) || worldObj.getBlock(x, y, z).equals(ModBlocks.celestialCompassSub))
                {
                    this.getWorldObj().setBlock(x, y, z, ModBlocks.celestialCompassSub, meta, 2);
                    blocksToPlace--;
                }
                else
                {
                    PacketHandler.INSTANCE.sendToAllAround(new MessageParticleSpawn(x + 0.5, y + 0.5, z + 0.5, 0), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, x + 0.5, y + 0.5, z + 0.5, 48));
                }
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

    public static void destroyCompass(World world, int x, int y, int z)
    {
        for(int n = 0; n < BLOCKS_IN_STRUCTURE; n++)
        {
            int currentX, currentY, currentZ;
            currentX = x + BlockPatterns.CELESTIAL_COMPASS[n].x;
            currentY = y + BlockPatterns.CELESTIAL_COMPASS[n].y;
            currentZ = z + BlockPatterns.CELESTIAL_COMPASS[n].z;

            if(world.getBlock(currentX, currentY, currentZ).equals(ModBlocks.celestialCompassSub))
            {
                world.setBlock(currentX, currentY, currentZ, Blocks.air);
            }
        }
    }
}