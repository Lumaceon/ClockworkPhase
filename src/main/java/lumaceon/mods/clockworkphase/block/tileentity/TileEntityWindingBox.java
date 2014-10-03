package lumaceon.mods.clockworkphase.block.tileentity;

import lumaceon.mods.clockworkphase.item.ITension;
import net.minecraft.item.ItemStack;

public class TileEntityWindingBox extends TileEntityClockworkPhaseInventory
{
    public TileEntityWindingBox()
    {
        this.inventory = new ItemStack[1];
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack is)
    {
        if(is != null && is.getItem() instanceof ITension)
        {
            return true;
        }
        return false;
    }
}
