package lumaceon.mods.clockworkphase.item.construct.abstracts;

import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.item.ItemClockworkPhaseGeneric;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.lib.Ranges;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemElementalAbstract extends ItemClockworkPhaseGeneric implements IElemental
{
    @Override
    public int getEntityLifespan(ItemStack itemStack, World world)
    {
        return 24000;
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
        NBTHelper.setInteger(item, NBTTags.ELEMENTIZE_TIMER, 0);
        return true;
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        int x = (int)Math.floor(entityItem.posX);
        int y = (int)Math.floor(entityItem.posY - 1);
        int z = (int)Math.floor(entityItem.posZ);
        BlockPos pos = new BlockPos(x, y, z);

        Block targetBlock = entityItem.world.getBlockState(pos).getBlock();
        boolean flag = true;

        if(!targetBlock.equals(ModBlocks.celestialCompass) && !targetBlock.equals(ModBlocks.celestialCompassSub))
        {
            return false;
        }

        IBlockState state = entityItem.world.getBlockState(new BlockPos(x, y, z));
        int meta = state.getBlock().getMetaFromState(state);
        EnumFacing direction = EnumFacing.byHorizontalIndex(meta);

        for(int n = 0; n < 10 && flag; n++)
        {
            //Celestial Compass was found, exit out of the loop
            if(entityItem.world.getBlockState(pos).getBlock().equals(ModBlocks.celestialCompass))
            {
                flag = false;
            }
            //Continue searching by way of metadata
            else
            {
                x += direction.getDirectionVec().getX();
                z += direction.getDirectionVec().getZ();

                state = entityItem.world.getBlockState(new BlockPos(x, y, z));
                meta = state.getBlock().getMetaFromState(state);
                direction = EnumFacing.byHorizontalIndex(meta);
            }
        }
        handleElementization(entityItem, x, y, z);
        return false;
    }

    private void handleElementization(EntityItem item, int x, int y, int z)
    {
        double xDifference = item.posX - x;
        double zDifference = item.posZ - z;

        //Null
        if(Ranges.CELESTIAL_NULL[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_NULL[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.world.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                unelementize(item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_LIFE[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_LIFE[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.world.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.LIFE, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_LIGHT[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_LIGHT[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.world.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.LIGHT, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_WATER[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_WATER[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.world.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.WATER, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_EARTH[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_EARTH[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.world.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.EARTH, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_AIR[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_AIR[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.world.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.AIR, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_FIRE[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_FIRE[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.world.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.FIRE, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_DARKNESS[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_DARKNESS[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.world.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.LUNAR, item);
                return;
            }
        }
        else if(Ranges.CELESTIAL_DEATH[0].isValueInclusivelyWithinRange(xDifference) && Ranges.CELESTIAL_DEATH[1].isValueInclusivelyWithinRange(zDifference))
        {
            if(item.world.isRemote)
            {
                ClientProxy.particleGenerator.SPAWNER.spawnElementizeParticle(item.posX, item.posY, item.posZ);
            }

            if(NBTHelper.getInt(item.getItem(), NBTTags.ELEMENTIZE_TIMER) > 200)
            {
                elementize(Phases.DEATH, item);
                return;
            }
        }
        else
        {
            return;
        }

        NBTHelper.setInteger(item.getItem(), NBTTags.ELEMENTIZE_TIMER, NBTHelper.getInt(item.getItem(), NBTTags.ELEMENTIZE_TIMER) + 1);
    }

    /**
     * Method for changing an item's type via celestial compass.
     * Overrides should always call the super class.
     * @param phase The phase that the item was dropped on.
     * @param item The item to be elementized.
     */
    public void elementize(Phases phase, EntityItem item)
    {
        NBTHelper.setInteger(item.getItem(), NBTTags.ELEMENTIZE_TIMER, 0);
    }

    /**
     * Genericizes the item dropped in the center of the celestial compass.
     * Overrides should always call the super class.
     * @param item The item to be genericized.
     */
    public void unelementize(EntityItem item)
    {
        NBTHelper.setInteger(item.getItem(), NBTTags.ELEMENTIZE_TIMER, 0);
    }
}
