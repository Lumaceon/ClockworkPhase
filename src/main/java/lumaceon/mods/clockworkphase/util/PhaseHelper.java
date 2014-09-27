package lumaceon.mods.clockworkphase.util;

import lumaceon.mods.clockworkphase.lib.GlobalPhaseReference;
import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.world.World;

public class PhaseHelper
{
    public static Phases getPhaseForWorld(World world)
    {
        //phaseTime represents the time within the phase, translating the time of world to the time of phases.
        int phaseTime = (int)(world.getWorldTime() % (GlobalPhaseReference.phaseDuration * Phases.values().length));

        //Get the actual phase from phaseTime.
        int id = phaseTime / GlobalPhaseReference.phaseDuration;

        return Phases.values()[id];
    }
}
