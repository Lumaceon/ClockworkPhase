package lumaceon.mods.clockworkphase.util;

import lumaceon.mods.clockworkphase.lib.GlobalPhaseReference;
import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.world.World;

public class PhaseHelper
{
    public static Phases getPhaseForWorld(World world)
    {
        if(world == null)
        {
            Logger.error("PhaseHelper has been passed a null world. Defaulted to LIFE phase.");
            return Phases.LIFE;
        }
        //allPhaseTime is the amount of time it takes to go through all phases.
        int allPhaseTime = (int)(world.getWorldTime() % (GlobalPhaseReference.phaseDuration * Phases.values().length));

        //Get the actual phase from phaseTime.
        int id = allPhaseTime / GlobalPhaseReference.phaseDuration;

        return Phases.values()[id];
    }
}
