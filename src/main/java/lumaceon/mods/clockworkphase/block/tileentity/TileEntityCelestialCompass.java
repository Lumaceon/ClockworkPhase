package lumaceon.mods.clockworkphase.block.tileentity;

import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.lib.BlockPatterns;
import lumaceon.mods.clockworkphase.lib.GlobalPhaseReference;
import lumaceon.mods.clockworkphase.network.MessageParticleSpawn;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TileEntityCelestialCompass extends TileEntityClockworkPhase implements ITickable
{
    public int currentRender;

    public static final int BLOCKS_IN_STRUCTURE = 96;
    public int blocksToPlace = 96;

    @Override
    public void update()
    {
        if(this.world.getWorldTime() % GlobalPhaseReference.phaseDuration == 0)
        {
            IBlockState state = world.getBlockState(new BlockPos(getPos()));
            world.notifyBlockUpdate(new BlockPos(getPos()), state, state, 3);
        }

        if(!this.world.isRemote)
        {
            if(blocksToPlace == 0)
            {
                for(int n = 0; n < BlockPatterns.CELESTIAL_COMPASS.length; n++)
                {
                    int x, y, z;
                    x = this.getPos().getX() + BlockPatterns.CELESTIAL_COMPASS[n].x;
                    y = this.getPos().getY() + BlockPatterns.CELESTIAL_COMPASS[n].y;
                    z = this.getPos().getZ() + BlockPatterns.CELESTIAL_COMPASS[n].z;
                    IBlockState state = world.getBlockState(new BlockPos(x, y, z));
                    world.notifyBlockUpdate(new BlockPos(x, y, z), state, state, 3);
                    blocksToPlace--;
                }
            }
            else if(blocksToPlace > 0)
            {
                int x, y, z, meta;
                x = this.getPos().getX() + BlockPatterns.CELESTIAL_COMPASS[blocksToPlace - 1].x;
                y = this.getPos().getY() + BlockPatterns.CELESTIAL_COMPASS[blocksToPlace - 1].y;
                z = this.getPos().getZ() + BlockPatterns.CELESTIAL_COMPASS[blocksToPlace - 1].z;
                meta = BlockPatterns.CELESTIAL_COMPASS[blocksToPlace - 1].meta;

                BlockPos pos = new BlockPos(x, y, z);
                if(world.isAirBlock(pos) || world.getBlockState(pos).getBlock().equals(ModBlocks.celestialCompassSub))
                {
                    this.getWorld().setBlockState(new BlockPos(x, y, z), ModBlocks.celestialCompassSub.getStateFromMeta(meta), 2);
                    blocksToPlace--;
                }
                else
                {
                    PacketHandler.INSTANCE.sendToAllAround(new MessageParticleSpawn(x + 0.5, y + 0.5, z + 0.5, 0), new NetworkRegistry.TargetPoint(world.provider.getDimension(), x + 0.5, y + 0.5, z + 0.5, 48));
                }
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("internal_block_count", this.blocksToPlace);
        return nbt;
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

            if(world.getBlockState(new BlockPos(currentX, currentY, currentZ)).getBlock().equals(ModBlocks.celestialCompassSub))
            {
                world.setBlockToAir(new BlockPos(currentX, currentY, currentZ));
            }
        }
    }
}