package lumaceon.mods.clockworkphase.client.gui.container;

import lumaceon.mods.clockworkphase.inventory.InventoryClockworkAssembly;
import lumaceon.mods.clockworkphase.client.gui.components.SlotClockworkComponent;
import lumaceon.mods.clockworkphase.client.gui.components.SlotClockworkCraft;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.component.generic.IBaseComponent;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ContainerClockworkAssemblyTableCW extends Container
{
    private InventoryClockworkAssembly matrix = new InventoryClockworkAssembly(this);
    private InventoryCraftResult result = new InventoryCraftResult();
    private World world;

    public ContainerClockworkAssemblyTableCW(InventoryPlayer ip, World world)
    {
        this.world = world;

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

        this.addSlotToContainer(new SlotClockworkComponent(matrix, 0, 21, 13));
        this.addSlotToContainer(new SlotClockworkComponent(matrix, 1, 120, 16));
        this.addSlotToContainer(new SlotClockworkComponent(matrix, 2, 210, 25));
        this.addSlotToContainer(new SlotClockworkComponent(matrix, 3, 48, 50));
        this.addSlotToContainer(new SlotClockworkComponent(matrix, 4, 165, 57));
        this.addSlotToContainer(new SlotClockworkComponent(matrix, 5, 30, 94));
        this.addSlotToContainer(new SlotClockworkComponent(matrix, 6, 87, 112));
        this.addSlotToContainer(new SlotClockworkComponent(matrix, 7, 183, 112));
        this.addSlotToContainer(new SlotClockworkComponent(matrix, 8, 138, 133));

        this.addSlotToContainer(new SlotClockworkCraft(result, matrix, 0, 120, 75));

        this.onCraftMatrixChanged(this.matrix);
    }

    @Override
    public void onCraftMatrixChanged(IInventory p_75130_1_)
    {
        this.result.setInventorySlotContents(0, getClockworkResult());
    }

    public ItemStack getClockworkResult()
    {
        ArrayList<ItemStack> components = new ArrayList<ItemStack>(9);
        boolean found = false;

        for(int i = 0; i < 9; i++)
        {
            ItemStack is = matrix.getStackInSlot(i);
            if(is != null && is.getItem() instanceof IBaseComponent)
            {
                found = true;
                components.add(is);
            }
        }
        if(!found) { return null; }

        IBaseComponent componentData;
        ItemStack tempItem;
        ItemStack output = new ItemStack(ModItems.clockwork);

        int resultingQuality = 0;
        int resultingSpeed = 0;
        int resultingMemory = 0;

        NBTTagList nbtList = new NBTTagList();
        for(int n = 0; n < components.size(); n++)
        {
            tempItem = components.get(n);

            //NBT data save of the ItemStack
            NBTTagCompound tag = new NBTTagCompound();
            tempItem.writeToNBT(tag);
            nbtList.appendTag(tag);

            componentData = (IBaseComponent)tempItem.getItem();

            if(componentData.isComponentQuality(tempItem))
            {
                resultingQuality += componentData.getGearQuality(tempItem);
            }

            if(componentData.isComponentSpeedy(tempItem))
            {
                resultingSpeed += componentData.getGearSpeed(tempItem);
            }

            if(componentData.isComponentMemory(tempItem))
            {
                resultingMemory += componentData.getMemoryValue(tempItem);
            }
        }
        NBTHelper.setTagList(output, NBTTags.INVENTORY_ARRAY, nbtList);

        NBTHelper.setInteger(output, NBTTags.QUALITY, resultingQuality);
        NBTHelper.setInteger(output, NBTTags.SPEED, resultingSpeed);
        NBTHelper.setInteger(output, NBTTags.MEMORY, resultingMemory);

        return output;
    }

    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
        super.onContainerClosed(p_75134_1_);

        if (!this.world.isRemote)
        {
            for (int i = 0; i < 9; ++i)
            {
                ItemStack itemstack = this.matrix.getStackInSlotOnClosing(i);

                if (itemstack != null)
                {
                    p_75134_1_.dropPlayerItemWithRandomChoice(itemstack, false);
                }
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return matrix.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        return null;
    }
}
