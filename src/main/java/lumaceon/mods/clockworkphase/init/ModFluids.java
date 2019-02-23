package lumaceon.mods.clockworkphase.init;

import lumaceon.mods.clockworkphase.fluid.FluidTimeSand;
import lumaceon.mods.clockworkphase.lib.Names;
import lumaceon.mods.clockworkphase.lib.Textures;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids
{
    public static Fluid timeSand;

    public static void init()
    {
        timeSand = new FluidTimeSand(Names.TIME_SAND, Textures.STILL_TIME_SAND, Textures.FLOWING_TIME_SAND);
        FluidRegistry.registerFluid(timeSand);
    }

    public static void registerBlocks()
    {
        timeSand.setBlock(ModBlocks.timeSand);
    }
}
