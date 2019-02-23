package lumaceon.mods.clockworkphase.phaseevent;

import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.util.Logger;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class PhaseEventHealthyAtmosphere extends PhaseEventAbstract
{
    public String EVENT_UNLOCALIZED_NAME = "clockworkphase.phase_event.healthyatmosphere";
    public String EVENT_WARNING_MESSAGE = "";
    public String EVENT_ACTIVATION_MESSAGE = "You feel more healthy than usual.";
    public String EVENT_DEACTIVATION_MESSAGE = "You suddenly feel weaker.";

    @Override
    public void applyEntityEffects(EntityLivingBase entity)
    {
        if(warmupTime <= 0 && duration > 0)
        {
            if(!entity.isPotionActive(MobEffects.REGENERATION))
            {
                entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 2));
            }
            if(!entity.isPotionActive(MobEffects.STRENGTH))
            {
                entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 200, 0));
            }

            if(entity.isPotionActive(MobEffects.POISON))
            {
                entity.removePotionEffect(MobEffects.POISON);
            }
            if(entity.isPotionActive(MobEffects.WITHER))
            {
                entity.removePotionEffect(MobEffects.WITHER);
            }
            if(entity.isPotionActive(MobEffects.MINING_FATIGUE))
            {
                entity.removePotionEffect(MobEffects.MINING_FATIGUE);
            }
            if(entity.isPotionActive(MobEffects.HUNGER))
            {
                entity.removePotionEffect(MobEffects.HUNGER);
            }
            if(entity.isPotionActive(MobEffects.WEAKNESS))
            {
                entity.removePotionEffect(MobEffects.WEAKNESS);
            }
        }
    }

    @Override
    public int getWarmTime()
    {
        return 0;
    }

    @Override
    public String getTranslationKey()
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
    public Phases getPhase() { return Phases.LIFE; }
}
