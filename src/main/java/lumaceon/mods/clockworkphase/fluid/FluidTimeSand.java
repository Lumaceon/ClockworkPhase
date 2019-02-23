package lumaceon.mods.clockworkphase.fluid;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidTimeSand extends Fluid
{
    public FluidTimeSand(String fluidName, String still, String flow)
    {
        super(fluidName, new ResourceLocation(still), new ResourceLocation(flow));
        this.setLuminosity(10);
        this.setViscosity(250);
        this.setDensity(50);
        this.setRarity(EnumRarity.EPIC);
    }
}
