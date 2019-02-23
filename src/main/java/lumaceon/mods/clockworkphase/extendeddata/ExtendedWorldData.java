package lumaceon.mods.clockworkphase.extendeddata;

import lumaceon.mods.clockworkphase.lib.Reference;
import lumaceon.mods.clockworkphase.phaseevent.PhaseEventAbstract;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

public class ExtendedWorldData extends WorldSavedData
{
    public static final String ID = Reference.MOD_ID + "_world_save_data";

    public PhaseEventAbstract phaseEvent;

    public ExtendedWorldData()
    {
        super(ExtendedWorldData.ID);
    }

    public ExtendedWorldData(String id)
    {
        super(id);
    }

    public void setWorldPhaseEvent(PhaseEventAbstract event, World world)
    {
        phaseEvent = event.copy();
        markDirty();
        if(phaseEvent.warmupTime > 40)
        {
            if(!world.isRemote && !phaseEvent.getWarningMessage().isEmpty())
            {
                for(int n = 0; n < world.playerEntities.size(); n++)
                {
                    ((EntityPlayer)world.playerEntities.get(n)).sendMessage(new TextComponentString(phaseEvent.getWarningMessage()));
                }
            }
        }
        else if(phaseEvent.warmupTime < 0)
        {
            if(!world.isRemote && !phaseEvent.getActivationMessage().isEmpty())
            {
                for(int n = 0; n < world.playerEntities.size(); n++)
                {
                    ((EntityPlayer)world.playerEntities.get(n)).sendMessage(new TextComponentString(phaseEvent.getActivationMessage()));
                }
            }
        }
    }

    public void removeWorldPhaseEvent(World world)
    {
        if(!world.isRemote && !phaseEvent.getDeactivationMessage().isEmpty())
        {
            for(int n = 0; n < world.playerEntities.size(); n++)
            {
                ((EntityPlayer)world.playerEntities.get(n)).sendMessage(new TextComponentString(phaseEvent.getDeactivationMessage()));
            }
        }
        phaseEvent = null;
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        if(phaseEvent != null)
        {
            nbt.setInteger("cp_phase", phaseEvent.getPhase().ordinal());
            nbt.setInteger("phase_id", phaseEvent.ID);

            NBTTagCompound compound = new NBTTagCompound();
            phaseEvent.writeToNBT(compound);
            nbt.setTag("phase_event_data", compound);
        }
        else
        {
            nbt.removeTag("cp_phase");
            nbt.removeTag("phase_id");
            nbt.removeTag("phase_event_data");
        }
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        if(phaseEvent == null)
        {
            phaseEvent = PhaseEventAbstract.getPhaseEventFromNBT(nbt);
            markDirty();
        }
    }

    public static ExtendedWorldData get(World world)
    {
        ExtendedWorldData dataHandler = (ExtendedWorldData)world.getPerWorldStorage().getOrLoadData(ExtendedWorldData.class, ID);
        if(dataHandler == null)
        {
            dataHandler = new ExtendedWorldData();
            world.getPerWorldStorage().setData(ID, dataHandler);
        }
        return dataHandler;
    }
}
