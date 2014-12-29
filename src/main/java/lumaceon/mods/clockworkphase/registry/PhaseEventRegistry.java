package lumaceon.mods.clockworkphase.registry;

import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.phaseevent.PhaseEventAbstract;
import lumaceon.mods.clockworkphase.util.Logger;

import java.util.ArrayList;
import java.util.Random;

public class PhaseEventRegistry
{
    private static ArrayList[] PHASE_EVENTS =
            {
                new ArrayList<PhaseEventAbstract>(5), //Life
                new ArrayList<PhaseEventAbstract>(5), //Light
                new ArrayList<PhaseEventAbstract>(5), //Water
                new ArrayList<PhaseEventAbstract>(5), //Earth
                new ArrayList<PhaseEventAbstract>(5), //Air
                new ArrayList<PhaseEventAbstract>(5), //Fire
                new ArrayList<PhaseEventAbstract>(5), //Lunar
                new ArrayList<PhaseEventAbstract>(5) //Death
            };

    public static void registerPhaseEvent(PhaseEventAbstract event, Phases phase)
    {
        event.setupID(PHASE_EVENTS[phase.ordinal()].size());
        PHASE_EVENTS[phase.ordinal()].add(event);
    }

    public static PhaseEventAbstract getRandomEventForPhase(Phases phase, Random random)
    {
        ArrayList<PhaseEventAbstract> events = PHASE_EVENTS[phase.ordinal()];
        if(!events.isEmpty())
        {
            return events.get(random.nextInt(events.size()));
        }
        return null;
    }

    public static PhaseEventAbstract[] getAllPossiblePhaseEvents(Phases phase)
    {
        return (PhaseEventAbstract[])PHASE_EVENTS[phase.ordinal()].toArray();
    }

    public static PhaseEventAbstract getSpecificPhaseEvent(Phases phase, int id)
    {
        ArrayList<PhaseEventAbstract> events = PHASE_EVENTS[phase.ordinal()];
        if(id > events.size() || id < 0)
        {
            Logger.warn("[Tried to load a nonexistent phase event.]");
            Logger.warn("ID: " + id);
            Logger.warn("Phase: " + phase.toString());
            Logger.warn("Amount of available events for " + phase.toString() + " phase: " + events.size());
        }
        else
        {
            return events.get(id);
        }
        return null;
    }
}
