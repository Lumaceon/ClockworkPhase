package lumaceon.mods.clockworkphase.client.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerMultitool extends Container
{
    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return false;
    }
    /**public ContainerHourglass(InventoryPlayer ip, TileEntityIntervalink te)
    {
        this.te = te;

        for(int x = 0; x < 9; x++)
        {
            this.addSlotToContainer(new Slot(ip, x, 8 + x * 18 , 198));
        }

        for(int x = 0; x < 9; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                this.addSlotToContainer(new Slot(ip, 9 + y + (x * 3), 8 + x * 18, 140 + y * 18));
            }
        }

        this.addSlotToContainer(new SlotIntervalink(te, 0, 80, 54));
    }**/
}
