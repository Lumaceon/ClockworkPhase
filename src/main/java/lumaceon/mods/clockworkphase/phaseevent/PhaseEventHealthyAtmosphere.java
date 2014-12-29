package lumaceon.mods.clockworkphase.phaseevent;

import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.util.Logger;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class PhaseEventHealthyAtmosphere extends PhaseEventAbstract
{
    public String EVENT_UNLOCALIZED_NAME = "clockworkphase:phase_event.healthyatmosphere";
    public String EVENT_WARNING_MESSAGE = "";
    public String EVENT_ACTIVATION_MESSAGE = "You feel more healthy than usual.";
    public String EVENT_DEACTIVATION_MESSAGE = "You suddenly feel weaker.";

    @Override
    public void applyEntityEffects(EntityLivingBase entity)
    {
        if(warmupTime <= 0 && duration > 0)
        {
            if(!entity.isPotionActive(Potion.regeneration))
            {
                entity.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 200, 2));
            }
            if(!entity.isPotionActive(Potion.damageBoost))
            {
                entity.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 200, 0));
            }

            if(entity.isPotionActive(Potion.poison))
            {
                entity.removePotionEffect(Potion.poison.getId());
            }
            if(entity.isPotionActive(Potion.wither))
            {
                entity.removePotionEffect(Potion.wither.getId());
            }
            if(entity.isPotionActive(Potion.digSlowdown))
            {
                entity.removePotionEffect(Potion.digSlowdown.getId());
            }
            if(entity.isPotionActive(Potion.hunger))
            {
                entity.removePotionEffect(Potion.hunger.getId());
            }
            if(entity.isPotionActive(Potion.weakness))
            {
                entity.removePotionEffect(Potion.weakness.getId());
            }
        }
    }

    @Override
    public int getWarmTime()
    {
        return 0;
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
    public Phases getPhase() { return Phases.LIFE; }
}
