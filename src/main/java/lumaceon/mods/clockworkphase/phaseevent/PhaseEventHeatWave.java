package lumaceon.mods.clockworkphase.phaseevent;

import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class PhaseEventHeatWave extends PhaseEventAbstract
{
    public String EVENT_UNLOCALIZED_NAME = "clockworkphase:phase_event.healthyatmosphere";
    public String EVENT_WARNING_MESSAGE = "The air around you begins to heat up.";
    public String EVENT_ACTIVATION_MESSAGE = "A heat wave strikes.";
    public String EVENT_DEACTIVATION_MESSAGE = "The air around you returns to normal temperature.";

    @Override
    public void applyEntityEffects(EntityLivingBase entity)
    {
        if(warmupTime <= 0 && duration > 0)
        {
            if(entity.isBurning() && entity.worldObj.getWorldTime() % 4 == 0)
            {
                entity.attackEntityFrom(DamageSource.onFire, 0.5F);
            }
        }
    }

    @Override
    public int getWarmTime()
    {
        return 600;
    }

    @Override
    public String getUnlocalizedName()
    {
        return this.EVENT_UNLOCALIZED_NAME;
    }

    @Override
    public String getWarningMessage()
    {
        return this.EVENT_WARNING_MESSAGE;
    }

    @Override
    public String getActivationMessage()
    {
        return this.EVENT_ACTIVATION_MESSAGE;
    }

    @Override
    public String getDeactivationMessage()
    {
        return this.EVENT_DEACTIVATION_MESSAGE;
    }

    @Override
    public Phases getPhase() { return Phases.FIRE; }

}
