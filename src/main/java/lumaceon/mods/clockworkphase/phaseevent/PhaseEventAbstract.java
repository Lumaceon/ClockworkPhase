package lumaceon.mods.clockworkphase.phaseevent;

import lumaceon.mods.clockworkphase.extendeddata.ExtendedWorldData;
import lumaceon.mods.clockworkphase.lib.GlobalPhaseReference;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.registry.PhaseEventRegistry;
import lumaceon.mods.clockworkphase.util.PhaseHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public abstract class PhaseEventAbstract
{
    public int ID;
    public Phases phase;

    public String EVENT_UNLOCALIZED_NAME = "clockworkphase:phase_event.default";
    public String EVENT_WARNING_MESSAGE = "A developer suddenly forgets to extend default values and screws everything up: +50 derpy developer points.";
    public String EVENT_ACTIVATION_MESSAGE = "A developer suddenly forgets to extend default values and screws everything up: +50 derpy developer points.";
    public String EVENT_DEACTIVATION_MESSAGE = "A developer suddenly forgets to extend default values and screws everything up: +50 derpy developer points.";

    /**
     * The time between the warning message and the actual effect. This can be used to warn the player
     * about the oncoming effects. Time is in ticks (20 = 1 seconds).
     */
    public int warmupTime = getWarmTime();
    public int duration = getDuration();

    public PhaseEventAbstract() {}

    public void setupID(int id)
    {
        this.ID = id;
    }

    public void updatePhaseEvent(World world)
    {
        if(!world.isRemote)
        {
            if(!MechanicTweaker.PHASE_EVENTS)
            {
                ExtendedWorldData.get(world).removeWorldPhaseEvent(world);
                return;
            }

            if(warmupTime > 0)
            {
                warmupTime--;
            }
            else if(warmupTime == 0)
            {
                if(!getActivationMessage().isEmpty())
                {
                    for(int n = 0; n < world.playerEntities.size(); n++)
                    {
                        ((EntityPlayer)world.playerEntities.get(n)).addChatComponentMessage(new ChatComponentText(this.getActivationMessage()));
                    }
                }
                warmupTime--;
            }
            else
            {
                duration--;
            }

            if(duration < 0 || PhaseHelper.getPhaseForWorld(world) != getPhase())
            {
                ExtendedWorldData.get(world).removeWorldPhaseEvent(world);
            }
        }
    }

    public void applyEntityEffects(EntityLivingBase player)
    {
        //NOOP
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("warmup_time", warmupTime);
        nbt.setInteger("duration_time", duration);
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        warmupTime = nbt.getInteger("warmup_time");
        duration = nbt.getInteger("duration_time");
    }

    public PhaseEventAbstract copy()
    {
        PhaseEventAbstract copy = null;
        try {
            copy = this.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return copy;
    }

    public String getUnlocalizedName()
    {
        return this.EVENT_UNLOCALIZED_NAME;
    }

    public String getWarningMessage()
    {
        return this.EVENT_WARNING_MESSAGE;
    }

    public String getActivationMessage()
    {
        return this.EVENT_ACTIVATION_MESSAGE;
    }

    public String getDeactivationMessage()
    {
        return this.EVENT_DEACTIVATION_MESSAGE;
    }

    public Phases getPhase() { return this.phase; }

    public int getWarmTime() { return 600; }

    public int getDuration() { return GlobalPhaseReference.phaseDuration; }

    public static PhaseEventAbstract getPhaseEventFromNBT(NBTTagCompound nbt)
    {
        PhaseEventAbstract event = null;
        if(nbt.hasKey("cp_phase") && nbt.hasKey("phase_id"))
        {
            Phases phase = Phases.values()[nbt.getInteger("cp_phase")];
            int id = nbt.getInteger("phase_id");
            event = PhaseEventRegistry.getSpecificPhaseEvent(phase, id).copy();

            if(event != null)
            {
                NBTTagCompound compound = nbt.getCompoundTag("phase_event_data");
                event.readFromNBT(compound);
            }
        }
        return event;
    }
}
