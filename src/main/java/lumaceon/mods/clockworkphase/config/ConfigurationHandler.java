package lumaceon.mods.clockworkphase.config;

import lumaceon.mods.clockworkphase.lib.GlobalPhaseReference;
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
