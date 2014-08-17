package lumaceon.mods.clockworkphase.creativetab;

import lumaceon.mods.clockworkphase.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabClockworkPhase extends CreativeTabs
{
    public CreativeTabClockworkPhase(int par1, String par2Str)
    {
        super(par1, par2Str);
    }

    @Override
    public Item getTabIconItem()
    {
        return ModItems.brassIngot;
    }
}