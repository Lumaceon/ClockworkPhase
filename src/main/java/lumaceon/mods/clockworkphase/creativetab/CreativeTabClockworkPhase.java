package lumaceon.mods.clockworkphase.creativetab;

import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabClockworkPhase extends CreativeTabs
{
    public CreativeTabClockworkPhase(int par1, String par2Str)
    {
        super(par1, par2Str);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ModItems.hourglassElements[Phases.AIR.ordinal()]);
    }
}