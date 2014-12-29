package lumaceon.mods.clockworkphase.init;

import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.phaseevent.PhaseEventAbstract;
import lumaceon.mods.clockworkphase.phaseevent.PhaseEventHealthyAtmosphere;
import lumaceon.mods.clockworkphase.phaseevent.PhaseEventHeatWave;
import lumaceon.mods.clockworkphase.phaseevent.PhaseEventSolarExcitement;
import lumaceon.mods.clockworkphase.registry.PhaseEventRegistry;

public class PhaseEvents
{
    public static PhaseEventAbstract healthyAtmosphere;
    public static PhaseEventAbstract solarExcitement;

    public static PhaseEventAbstract heatWave;

    public static void init()
    {
        healthyAtmosphere = new PhaseEventHealthyAtmosphere();
        PhaseEventRegistry.registerPhaseEvent(healthyAtmosphere, Phases.LIFE);

        solarExcitement = new PhaseEventSolarExcitement();
        PhaseEventRegistry.registerPhaseEvent(solarExcitement, Phases.LIGHT);


        heatWave = new PhaseEventHeatWave();
        PhaseEventRegistry.registerPhaseEvent(heatWave, Phases.FIRE);
    }
}
