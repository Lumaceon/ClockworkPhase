package lumaceon.mods.clockworkphase.client.gui.components;

import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.api.MainspringMetal;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class SlotMainspringMetals extends Slot
{
    public SlotMainspringMetals(IInventory inventory, int slotId, int x, int y)
    {
        super(inventory, slotId, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack is)
    {
        MainspringMetal metal;
        ArrayList<ItemStack> ores;

        for(int i = 0; i < ClockworkPhase.MAINSPRING_METAL_DICTIONARY.mainspringMetals.size(); i++)
        {
            metal = ClockworkPhase.MAINSPRING_METAL_DICTIONARY.mainspringMetals.get(i);
            ores = OreDictionary.getOres(metal.metalName);
            for(ItemStack item : ores)
            {
                if(OreDictionary.itemMatches(item, is, false))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
