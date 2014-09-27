package lumaceon.mods.clockworkphase.fluid;

import net.minecraft.item.EnumRarity;
import net.minecraftforge.fluids.Fluid;

public class FluidTimeSand extends Fluid
{
    public FluidTimeSand(String fluidName)
    {
        super(fluidName);
        this.setLuminosity(10);
        this.setViscosity(250);
        this.setDensity(50);
        this.setRarity(EnumRarity.epic);
    }
}
