package lumaceon.mods.clockworkphase.phaseevent;

import lumaceon.mods.clockworkphase.lib.GlobalPhaseReference;
import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PhaseEventSolarExcitement extends PhaseEventAbstract
{
    public String EVENT_UNLOCALIZED_NAME = "clockworkphase.phase_event.solarexcite";
    public String EVENT_WARNING_MESSAGE = "";
    public String EVENT_ACTIVATION_MESSAGE = "Everything grows much more bright.";
    public String EVENT_DEACTIVATION_MESSAGE = "The world dims around you.";

    @Override
    public void applyEntityEffects(EntityLivingBase entity)
    {
        if(entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entity;
            player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 220, 0, true, true));
        }
    }

    @Override
    public int getDuration() { return GlobalPhaseReference.phaseDuration; }

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
    public Phases getPhase() { return Phases.LIGHT; }
}
