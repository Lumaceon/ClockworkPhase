package lumaceon.mods.clockworkphase.client.gui.container;

import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.client.gui.components.SlotItemSpecific;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.Phases;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerExtractor extends Container
{
    public TileEntityExtractor te;
    public Phases phase;

    public ContainerExtractor(TileEntityExtractor te, InventoryPlayer ip, Phases phase)
    {
        this.te = te;
        this.phase = phase;

        for(int x = 0; x < 9; x++)
        {
            this.addSlotToContainer(new Slot(ip, x, 48 + x * 18 , 232));
        }

        for(int x = 0; x < 9; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                this.addSlotToContainer(new Slot(ip, 9 + y + (x * 3), 48 + x * 18, 174 + y * 18));
            }
        }

        this.addSlotToContainer(new SlotItemSpecific(te, 0, 120, 74, ModItems.catalystElements[phase.ordinal()]));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return te.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        return null;
    }
}
