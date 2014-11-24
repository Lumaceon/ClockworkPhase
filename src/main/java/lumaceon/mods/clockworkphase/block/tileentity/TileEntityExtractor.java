package lumaceon.mods.clockworkphase.block.tileentity;

import cpw.mods.fml.common.network.NetworkRegistry;
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
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityExtractor extends TileEntityTimeSandCapacitor implements IInventory
{
    public Phases phase;
    public boolean extractorAreaReady = false;
    public boolean needsToUpdateArea = false;

    public int timeWellX, timeWellY, timeWellZ;

    protected ItemStack inventory;

    public TileEntityExtractor()
    {
        super();
    }

    public TileEntityExtractor(Phases phase)
    {
        super();
        this.phase = phase;
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

        if(inventory != null && inventory.getItem() instanceof ItemCatalyst)
        {
            if(inventory.getItem().equals(ModItems.catalystElements[phase.ordinal()]))
            {
                int newDamage, timeSandToAdd;
                switch(phase.ordinal())
                {
                    case 0: //Life
                        addTimeSand(MechanicTweaker.TIME_SAND_FROM_NATURAL_SPAWN);
                        newDamage = inventory.getItemDamage() + 1;
                        if(newDamage > inventory.getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            inventory.setItemDamage(newDamage);
                            markDirty();
                        }
                        break;
                    case 1: //Light
                        int lightLevels = 0;
                        lightLevels += worldObj.getBlockLightValue(xCoord + 1, yCoord, zCoord);
                        lightLevels += worldObj.getBlockLightValue(xCoord, yCoord, zCoord + 1);
                        lightLevels += worldObj.getBlockLightValue(xCoord, yCoord + 1, zCoord);
                        lightLevels += worldObj.getBlockLightValue(xCoord - 1, yCoord, zCoord);
                        lightLevels += worldObj.getBlockLightValue(xCoord, yCoord, zCoord - 1);
                        lightLevels += worldObj.getBlockLightValue(xCoord, yCoord - 1, zCoord);

                        timeSandToAdd = (int)((lightLevels / 75.0) * MechanicTweaker.TIME_SAND_FROM_LIGHT_SECOND);
                        if(timeSandToAdd <= 0) { return false; }
                        if(timeSandToAdd > MechanicTweaker.TIME_SAND_FROM_LIGHT_SECOND) { timeSandToAdd = MechanicTweaker.TIME_SAND_FROM_LIGHT_SECOND; }
                        addTimeSand(timeSandToAdd);
                        newDamage = inventory.getItemDamage() + 1;
                        if(newDamage > inventory.getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            inventory.setItemDamage(newDamage);
                            markDirty();
                        }
                        break;
                    case 2: //Water
                        int waterBlocks = 0;
                        for(int x = xCoord - 1; x <= xCoord + 1; x++)
                        {
                            for(int y = yCoord - 1; y <= yCoord + 1; y++)
                            {
                                for(int z = zCoord - 1; z <= zCoord + 1; z++)
                                {
                                    if(worldObj.getBlock(x, y, z).equals(Blocks.water)) {waterBlocks++;}
                                }
                            }
                        }
                        timeSandToAdd = (int)((waterBlocks / 26.0) * MechanicTweaker.TIME_SAND_FROM_WATER_SECOND);
                        if(timeSandToAdd <= 0) { return false; }
                        addTimeSand(timeSandToAdd);
                        newDamage = inventory.getItemDamage() + 1;
                        if(newDamage > inventory.getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            inventory.setItemDamage(newDamage);
                            markDirty();
                        }
                        break;
                    case 3: //Earth
                        addTimeSand(MechanicTweaker.TIME_SAND_FROM_TREE_EXTRACTION);
                        newDamage = inventory.getItemDamage() + 1;
                        if(newDamage > inventory.getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            inventory.setItemDamage(newDamage);
                            markDirty();
                        }
                        return true;
                    case 4: //Air
                        timeSandToAdd = (int)((this.yCoord / 255.0) * MechanicTweaker.TIME_SAND_FROM_AIR_SECOND);
                        if(timeSandToAdd <= 0) { return false; }
                        addTimeSand(timeSandToAdd);
                        newDamage = inventory.getItemDamage() + 1;
                        if(newDamage > inventory.getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            inventory.setItemDamage(newDamage);
                            markDirty();
                        }
                        break;
                    case 5: //Fire
                        addTimeSand(MechanicTweaker.TIME_SAND_FROM_ONE_FIRE_DAMAGE);
                        newDamage = inventory.getItemDamage() + 1;
                        if(newDamage > inventory.getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            inventory.setItemDamage(newDamage);
                            markDirty();
                        }
                        break;
                    case 6: //Lunar
                        if(worldObj.getWorldTime() % 24000 > 12000 && worldObj.getWorldTime() % 24000 < 24000)
                        {
                            addTimeSand(MechanicTweaker.TIME_SAND_FROM_MOON_SECOND);
                            newDamage = inventory.getItemDamage() + 1;
                            if(newDamage > inventory.getMaxDamage())
                            {
                                decrStackSize(0, 1);
                            }
                            else
                            {
                                inventory.setItemDamage(newDamage);
                                markDirty();
                            }
                        }
                        break;
                    case 7: //Death
                        addTimeSand(MechanicTweaker.TIME_SAND_FROM_DEATH);
                        newDamage = inventory.getItemDamage() + 1;
                        if(newDamage > inventory.getMaxDamage())
                        {
                            decrStackSize(0, 1);
                        }
                        else
                        {
                            inventory.setItemDamage(newDamage);
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
    public void updateEntity()
    {
        if(worldObj != null)
        {
            if(!extractorAreaReady && phase != null && !worldObj.isRemote)
            {
                if(!ExtractorAreas.doesAreaExist(worldObj, xCoord, yCoord, zCoord))
                {
                    if(phase.equals(Phases.FIRE) || phase.equals(Phases.EARTH) || phase.equals(Phases.LIFE) || phase.equals(Phases.DEATH))
                    {
                        int radius = 7;
                        ExtractorAreas.getAreasFromWorld(worldObj, phase).addArea(xCoord, yCoord, zCoord, xCoord - radius, yCoord - radius, zCoord - radius, xCoord + radius, yCoord + radius, zCoord + radius);
                    }
                }
                this.extractorAreaReady = true;
            }

            int timeSandToRemove = 1000;
            if(getTimeSand() > 10000)
            {
                timeSandToRemove = getTimeSand() / 10;
            }

            if(worldObj.getWorldTime() % 100 == 0 && getTimeSand() >= timeSandToRemove)
            {
                TileEntity te = worldObj.getTileEntity(timeWellX, timeWellY, timeWellZ);
                if(te != null && te instanceof TileEntityTimeWell)
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

                    if(!worldObj.isRemote)
                    {
                        PacketHandler.INSTANCE.sendToAllAround(new MessageDoublePositionParticleSpawn(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, timeWellX + 0.5, timeWellY + 0.5, timeWellZ + 0.5, 1), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 64));
                    }
                }
            }

            if(this.phase != null && worldObj.getWorldTime() % 20 == 0)
            {
                if(phase.equals(Phases.AIR) || phase.equals(Phases.WATER) || phase.equals(Phases.LUNAR) || phase.equals(Phases.LIGHT))
                {
                    applyEffect(phase);
                }
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("phase_element", this.phase.ordinal());
        NBTTagCompound compound = new NBTTagCompound();
        if(inventory != null) {inventory.writeToNBT(compound);}
        nbt.setTag(NBTTags.INVENTORY_ARRAY, compound);

        nbt.setInteger(NBTTags.CLOCKWORK_PHASE_X, timeWellX);
        nbt.setInteger(NBTTags.CLOCKWORK_PHASE_Y, timeWellY);
        nbt.setInteger(NBTTags.CLOCKWORK_PHASE_Z, timeWellZ);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.phase = Phases.values()[nbt.getInteger("phase_element")];
        inventory = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag(NBTTags.INVENTORY_ARRAY));

        timeWellX = nbt.getInteger(NBTTags.CLOCKWORK_PHASE_X);
        timeWellY = nbt.getInteger(NBTTags.CLOCKWORK_PHASE_Y);
        timeWellZ = nbt.getInteger(NBTTags.CLOCKWORK_PHASE_Z);
    }

    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return inventory;
    }

    @Override
    public ItemStack decrStackSize(int index, int lossCount)
    {
        ItemStack is = getStackInSlot(index);
        if (is != null)
        {
            if (lossCount >= is.stackSize)
            {
                setInventorySlotContents(index, null);
            }
            else
            {
                is = is.splitStack(lossCount);
                if (is.stackSize == 0)
                {
                    setInventorySlotContents(index, null);
                }
            }
        }
        markDirty();
        return is;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (inventory != null)
        {
            ItemStack itemStack = inventory;
            inventory = null;
            return itemStack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack is)
    {
        inventory = is;

        if (is != null && is.stackSize > this.getInventoryStackLimit())
        {
            is.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    @Override
    public String getInventoryName()
    {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory() { }

    @Override
    public void closeInventory() { }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack is)
    {
        return is.getItem() instanceof ItemCatalyst && is.getItem().equals(ModItems.catalystElements[phase.ordinal()]);
    }
}
