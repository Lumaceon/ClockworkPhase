package lumaceon.mods.clockworkphase.block.tileentity;

import lumaceon.mods.clockworkphase.custom.IInventoryHelper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import lumaceon.mods.clockworkphase.block.extractor.ExtractorAreas;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.ItemCatalyst;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.network.MessageDoublePositionParticleSpawn;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityExtractor extends TileEntityTimeSandCapacitor implements IInventoryHelper, ITickable
{
    public Phases phase;
    public boolean extractorAreaReady = false;
    public boolean needsToUpdateArea = false;

    public int timeWellX, timeWellY, timeWellZ;

    protected NonNullList<ItemStack> invv;
//    protected ItemStack inventory;


    @Override
    public NonNullList<ItemStack> getInv() {
        return invv;
    }

    public TileEntityExtractor()
    {
        super();
        invv = NonNullList.withSize(1, ItemStack.EMPTY);
    }

    public TileEntityExtractor(Phases phase)
    {
        super();
        this.phase = phase;
        invv = NonNullList.withSize(1, ItemStack.EMPTY);
    }

    /**
     * Handles effects when a valid event occurs.
     * This is responsible for adding time sand and damaging the catalyst.
     * @param phase
     * @return If there is a catalyst that can apply the fork.
     */
    public boolean applyEffect(Phases phase)
    {
        if(!phase.equals(this.phase))
        {
            return false;
        }

        if(getInv().get(0).getItem() instanceof ItemCatalyst)
        {
            if(getInv().get(0).getItem().equals(ModItems.catalystElements[phase.ordinal()]))
            {
                int newDamage, timeSandToAdd;
                switch(phase.ordinal())
                {
                    case 0: //Life
                        addTimeSand(MechanicTweaker.TIME_SAND_FROM_NATURAL_SPAWN);
                        newDamage = getInv().get(0).getItemDamage() + 1;
                        if(newDamage > getInv().get(0).getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            getInv().get(0).setItemDamage(newDamage);
                            markDirty();
                        }
                        break;
                    case 1: //Light
                        int lightLevels = 0;
                        for (EnumFacing facing : EnumFacing.VALUES)
                            lightLevels += world.getLightFromNeighbors(getPos().offset(facing));

                        timeSandToAdd = (int)((lightLevels / 75.0) * MechanicTweaker.TIME_SAND_FROM_LIGHT_SECOND);
                        if(timeSandToAdd <= 0) { return false; }
                        if(timeSandToAdd > MechanicTweaker.TIME_SAND_FROM_LIGHT_SECOND) { timeSandToAdd = MechanicTweaker.TIME_SAND_FROM_LIGHT_SECOND; }
                        addTimeSand(timeSandToAdd);
                        newDamage = getInv().get(0).getItemDamage() + 1;
                        if(newDamage > getInv().get(0).getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            getInv().get(0).setItemDamage(newDamage);
                            markDirty();
                        }
                        break;
                    case 2: //Water
                        int waterBlocks = 0;
                        for(int x = getPos().getX() - 1; x <= getPos().getX() + 1; x++)
                        {
                            for(int y = getPos().getY() - 1; y <= getPos().getY() + 1; y++)
                            {
                                for(int z = getPos().getZ() - 1; z <= getPos().getZ() + 1; z++)
                                {
                                    if(world.getBlockState(new BlockPos(x, y, z)).getBlock().equals(Blocks.WATER)) {waterBlocks++;}
                                }
                            }
                        }
                        timeSandToAdd = (int)((waterBlocks / 26.0) * MechanicTweaker.TIME_SAND_FROM_WATER_SECOND);
                        if(timeSandToAdd <= 0) { return false; }
                        addTimeSand(timeSandToAdd);
                        newDamage = getInv().get(0).getItemDamage() + 1;
                        if(newDamage > getInv().get(0).getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            getInv().get(0).setItemDamage(newDamage);
                            markDirty();
                        }
                        break;
                    case 3: //Earth
                        addTimeSand(MechanicTweaker.TIME_SAND_FROM_TREE_EXTRACTION);
                        newDamage = getInv().get(0).getItemDamage() + 1;
                        if(newDamage > getInv().get(0).getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            getInv().get(0).setItemDamage(newDamage);
                            markDirty();
                        }
                        return true;
                    case 4: //Air
                        timeSandToAdd = (int)((this.getPos().getY() / 255.0) * MechanicTweaker.TIME_SAND_FROM_AIR_SECOND);
                        if(timeSandToAdd <= 0) { return false; }
                        addTimeSand(timeSandToAdd);
                        newDamage = getInv().get(0).getItemDamage() + 1;
                        if(newDamage > getInv().get(0).getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            getInv().get(0).setItemDamage(newDamage);
                            markDirty();
                        }
                        break;
                    case 5: //Fire
                        addTimeSand(MechanicTweaker.TIME_SAND_FROM_ONE_FIRE_DAMAGE);
                        newDamage = getInv().get(0).getItemDamage() + 1;
                        if(newDamage > getInv().get(0).getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            getInv().get(0).setItemDamage(newDamage);
                            markDirty();
                        }
                        break;
                    case 6: //Lunar
                        if(world.getWorldTime() % 24000 > 12000 && world.getWorldTime() % 24000 < 24000)
                        {
                            addTimeSand(MechanicTweaker.TIME_SAND_FROM_MOON_SECOND);
                            newDamage = getInv().get(0).getItemDamage() + 1;
                            if(newDamage > getInv().get(0).getMaxDamage())
                            {
                                decrStackSize(0, 1);
                            }
                            else
                            {
                                getInv().get(0).setItemDamage(newDamage);
                                markDirty();
                            }
                        }
                        break;
                    case 7: //Death
                        addTimeSand(MechanicTweaker.TIME_SAND_FROM_DEATH);
                        newDamage = getInv().get(0).getItemDamage() + 1;
                        if(newDamage > getInv().get(0).getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            getInv().get(0).setItemDamage(newDamage);
                            markDirty();
                        }
                        return true;
                    default:
                        return false;
                }
            }
        }
        return false;
    }

    @Override
    public int getMaxTimeSandCapacity()
    {
        return MechanicTweaker.MAX_TS_EXTRACTORS;
    }

    @Override
    public void update()
    {
        if(world != null)
        {
            if(!extractorAreaReady && phase != null && !world.isRemote)
            {
                if(!ExtractorAreas.doesAreaExist(world, getPos().getX(), getPos().getY(), getPos().getZ()))
                {
                    if(phase.equals(Phases.FIRE) || phase.equals(Phases.EARTH) || phase.equals(Phases.LIFE) || phase.equals(Phases.DEATH))
                    {
                        int radius = 7;
                        ExtractorAreas.getAreasFromWorld(world, phase).addArea(getPos().getX(), getPos().getY(), getPos().getZ(), getPos().getX() - radius, getPos().getY() - radius, getPos().getZ() - radius, getPos().getX() + radius, getPos().getY() + radius, getPos().getZ() + radius);
                    }
                }
                this.extractorAreaReady = true;
            }

            int timeSandToRemove = 1000;
            if(getTimeSand() > 10000)
            {
                timeSandToRemove = getTimeSand() / 10;
            }

            if(world.getWorldTime() % 100 == 0 && getTimeSand() >= timeSandToRemove)
            {
                TileEntity te = world.getTileEntity(new BlockPos(timeWellX, timeWellY, timeWellZ));
                if(te instanceof TileEntityTimeWell)
                {
                    TileEntityTimeWell timeWell = (TileEntityTimeWell)te;
                    if(timeWell.getTimeSand() + timeSandToRemove <= timeWell.getMaxTimeSandCapacity()) //All time sand can be added
                    {
                        timeWell.addTimeSand(removeTimeSand(timeSandToRemove));
                    }
                    else //Time sand addition goes over max capacity
                    {
                        timeWell.addTimeSand(removeTimeSand(timeWell.getMaxTimeSandCapacity() - timeWell.getTimeSand())); //Add only enough to max.
                    }

                    if(!world.isRemote)
                    {
                        PacketHandler.INSTANCE.sendToAllAround(new MessageDoublePositionParticleSpawn(getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() + 0.5, timeWellX + 0.5, timeWellY + 0.5, timeWellZ + 0.5, 1), new NetworkRegistry.TargetPoint(world.provider.getDimension(), getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() + 0.5, 64));
                    }
                }
            }

            if(this.phase != null && world.getWorldTime() % 20 == 0)
            {
                if(phase.equals(Phases.AIR) || phase.equals(Phases.WATER) || phase.equals(Phases.LUNAR) || phase.equals(Phases.LIGHT))
                {
                    applyEffect(phase);
                }
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("phase_element", this.phase.ordinal());
        NBTTagCompound compound = new NBTTagCompound();
        if(!getInv().get(0).isEmpty()) {getInv().get(0).writeToNBT(compound);}
        nbt.setTag(NBTTags.INVENTORY_ARRAY, compound);

        nbt.setInteger(NBTTags.CLOCKWORK_PHASE_X, timeWellX);
        nbt.setInteger(NBTTags.CLOCKWORK_PHASE_Y, timeWellY);
        nbt.setInteger(NBTTags.CLOCKWORK_PHASE_Z, timeWellZ);
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.phase = Phases.values()[nbt.getInteger("phase_element")];
        invv = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        invv.set(0, new ItemStack(nbt.getCompoundTag(NBTTags.INVENTORY_ARRAY)));

        timeWellX = nbt.getInteger(NBTTags.CLOCKWORK_PHASE_X);
        timeWellY = nbt.getInteger(NBTTags.CLOCKWORK_PHASE_Y);
        timeWellZ = nbt.getInteger(NBTTags.CLOCKWORK_PHASE_Z);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack is)
    {
        return is.getItem() instanceof ItemCatalyst && is.getItem().equals(ModItems.catalystElements[phase.ordinal()]);
    }
}
