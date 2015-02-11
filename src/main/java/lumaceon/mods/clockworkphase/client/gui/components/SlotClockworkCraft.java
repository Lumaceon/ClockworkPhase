package lumaceon.mods.clockworkphase.client.gui.components;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotClockworkCraft extends Slot
{
    private IInventory craftingMatrix;

    public SlotClockworkCraft(IInventory inventory, IInventory craftingSlots, int id, int x, int y)
    {
        super(inventory, id, x, y);
        this.craftingMatrix = craftingSlots;
    }

    @Override
    public boolean isItemValid(ItemStack p_75214_1_)
    {
        return false;
    }

    public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_)
    {
        super.onSlotChange(p_75220_1_, p_75220_2_);

    }

    @Override
    public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
    {
        this.onCrafting(p_82870_2_);
        for(int i = 0; i < 9; i++)
        {
            ItemStack is = this.craftingMatrix.getStackInSlot(i);
            if(is != null)
            {
                this.craftingMatrix.decrStackSize(i, 1);
            }
        }
    }
}
