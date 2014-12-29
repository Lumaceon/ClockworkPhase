package lumaceon.mods.clockworkphase.config;

import lumaceon.mods.clockworkphase.lib.GlobalPhaseReference;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{
    public static void init(File configurationFile)
    {
        Configuration config = new Configuration(configurationFile);

        try
        {
            config.load();

            //Add each configuration value here
            MechanicTweaker.PHASE_EVENTS = config.get(Configuration.CATEGORY_GENERAL, "EnablePhaseEvents", false, "Enables or disables world-wide events that occur occasionally based on phases.").getBoolean(false);
            GlobalPhaseReference.phaseDuration = config.get(Configuration.CATEGORY_GENERAL, "PhaseDuration", 24000, "The duration of each phase in ticks. 1 Minecraft day = 24,000.").getInt(24000);
        }
        catch(Exception e)
        {

        }
        finally
        {
            config.save();
        }
    }
}
