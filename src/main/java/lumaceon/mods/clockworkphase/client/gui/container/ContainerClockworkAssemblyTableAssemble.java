package lumaceon.mods.clockworkphase.client.gui.container;

import lumaceon.mods.clockworkphase.client.gui.components.SlotClockworkCraft;
import lumaceon.mods.clockworkphase.inventory.InventoryClockworkAssembly;
import lumaceon.mods.clockworkphase.item.ItemTemporalMultitool;
import lumaceon.mods.clockworkphase.item.component.ItemClockwork;
import lumaceon.mods.clockworkphase.item.component.ItemMainspring;
import lumaceon.mods.clockworkphase.item.construct.abstracts.IClockwork;
import lumaceon.mods.clockworkphase.item.construct.pocketwatch.ItemPocketWatch;
import lumaceon.mods.clockworkphase.item.construct.pocketwatch.module.ItemModule;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ContainerClockworkAssemblyTableAssemble extends Container
{
    private InventoryClockworkAssembly matrix = new InventoryClockworkAssembly(this);
    private InventoryCraftResult result = new InventoryCraftResult();
    private World world;

    public ContainerClockworkAssemblyTableAssemble(InventoryPlayer ip, World world)
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

        this.addSlotToContainer(new Slot(matrix, 0, 84, 75));
        this.addSlotToContainer(new Slot(matrix, 1, 156, 75));

        this.addSlotToContainer(new SlotClockworkCraft(result, matrix, 0, 120, 75));

        this.onCraftMatrixChanged(this.matrix);
    }

    @Override
    public void onCraftMatrixChanged(IInventory p_75130_1_)
    {
        ItemStack result;

        result = getClockworkRecipe();
        if(result != null)
        {
            this.result.setInventorySlotContents(0, result);
            return;
        }

        result = getMultitoolRecipe();
        if(result != null)
        {
            this.result.setInventorySlotContents(0, result);
            return;
        }

        result = getPocketWatchRecipe();
        this.result.setInventorySlotContents(0, result);
    }

    public ItemStack getClockworkRecipe()
    {
        ItemStack item;
        boolean mainspring = false;
        boolean clockwork = false;
        boolean construct = false;
        boolean doubles = false;

        boolean alreadyContainsMainspring = false;
        boolean alreadyContainsClockwork = false;

        for(int i = 0; i < 2; i++)
        {
            item = matrix.getStackInSlot(i);
            if(item == null) { return null; }


            if(item.getItem() instanceof IClockwork)
            {
                if(construct)
                {
                    doubles = true;
                }
                construct = true;

                if(NBTHelper.getInt(item, NBTTags.MAX_TENSION) != 0)
                {
                    alreadyContainsMainspring = true;
                }

                if(NBTHelper.hasTag(item, NBTTags.CLOCKWORK))
                {
                    alreadyContainsClockwork = true;
                }
            }
            else if(item.getItem() instanceof ItemMainspring)
            {
                if(mainspring)
                {
                    doubles = true;
                }
                mainspring = true;
            }
            else if(item.getItem() instanceof ItemClockwork)
            {
                if(clockwork)
                {
                    doubles = true;
                }
                clockwork = true;
            }
            else
            {
                return null;
            }
        }

        if(alreadyContainsMainspring && mainspring)
        {
            return null;
        }
        if(alreadyContainsClockwork && clockwork)
        {
            return null;
        }
        if(doubles)
        {
            return null;
        }
        if((mainspring || clockwork) && construct)
        {
            return constructResult();
        }
        else
        {
            return null;
        }
    }

    private ItemStack constructResult()
    {
        ItemStack tempItem;
        ItemStack mainspring = null;
        ItemStack clockwork = null;
        ItemStack construct = null;

        for(int n = 0; n < matrix.getSizeInventory(); n++)
        {
            tempItem = matrix.getStackInSlot(n);
            if (tempItem != null)
            {
                if(tempItem.getItem() instanceof ItemMainspring)
                {
                    mainspring = tempItem.copy();
                }

                if(tempItem.getItem() instanceof ItemClockwork)
                {
                    clockwork = tempItem.copy();
                }

                if(tempItem.getItem() instanceof IClockwork)
                {
                    construct = tempItem.copy();
                }
            }
        }

        ItemStack output = construct;
        if(mainspring != null)
        {
            int currentTension = NBTHelper.getInt(mainspring, NBTTags.TENSION_ENERGY);
            int maxTension = NBTHelper.getInt(mainspring, NBTTags.MAX_TENSION);

            NBTHelper.setInteger(output, NBTTags.TENSION_ENERGY, currentTension);
            NBTHelper.setInteger(output, NBTTags.MAX_TENSION, maxTension);

            if(output != null)
            {
                if(maxTension / output.getMaxDamage() == 0) //Check for division by 0
                {
                    output.setItemDamage(output.getMaxDamage());
                }
                else
                {
                    output.setItemDamage(output.getMaxDamage() - (currentTension / (maxTension / output.getMaxDamage())));
                }
            }
        }

        if(clockwork != null)
        {
            NBTHelper.setInteger(output, NBTTags.QUALITY, NBTHelper.getInt(clockwork, NBTTags.QUALITY));
            NBTHelper.setInteger(output, NBTTags.SPEED, NBTHelper.getInt(clockwork, NBTTags.SPEED));
            NBTHelper.setInteger(output, NBTTags.MEMORY, NBTHelper.getInt(clockwork, NBTTags.MEMORY));

            NBTTagList nbtList = new NBTTagList();
            NBTTagCompound tag = new NBTTagCompound();
            clockwork.writeToNBT(tag);
            nbtList.appendTag(tag);
            NBTHelper.setTagList(output, NBTTags.CLOCKWORK, nbtList);
        }
        return output;
    }

    public ItemStack getMultitoolRecipe()
    {
        ItemStack item;
        boolean fluxItem = false;
        boolean multitool = false;
        boolean doubles = false;

        for(int n = 0; n < matrix.getSizeInventory(); n++)
        {
            item = matrix.getStackInSlot(n);
            if(item != null)
            {
                if(item.getItem() instanceof ItemTemporalMultitool)
                {
                    if(multitool)
                    {
                        doubles = true;
                    }
                    multitool = true;

                    ItemStack[] items = NBTHelper.getInventoryFromNBTTag(item, NBTTags.TEMPORAL_ITEMS);
                    if(items != null && items.length >= 20)
                    {
                        return null;
                    }
                }
                else if(item.getItem().getItemStackLimit(item) == 1 && !(item.getItem() instanceof ItemBlock) && !(item.getItem() instanceof ItemTemporalMultitool) && !(item.getItem() instanceof ItemPotion) && !(item.getItem() instanceof ItemArmor) && !(item.getItem() instanceof ItemBucket))
                {
                    if(fluxItem)
                    {
                        doubles = true;
                    }
                    fluxItem = true;

                    if(NBTHelper.hasTag(item, NBTTags.TEMPORAL_ITEMS))
                    {
                        return null;
                    }
                }
                else
                {
                    return null;
                }
            }
        }

        if(fluxItem && multitool && !doubles)
        {
            return multitoolResult();
        }
        return null;
    }

    private ItemStack multitoolResult()
    {
        ItemStack tempItem;
        ItemStack fluxableItem = null;
        ItemStack multitool = null;

        for(int n = 0; n < matrix.getSizeInventory(); n++)
        {
            tempItem = matrix.getStackInSlot(n);
            if(tempItem != null)
            {
                if(tempItem.getItem() instanceof ItemTemporalMultitool)
                {
                    multitool = tempItem.copy();
                }

                if(tempItem.getItem().getItemStackLimit(tempItem) == 1 && !(tempItem.getItem() instanceof ItemBlock) && !(tempItem.getItem() instanceof ItemTemporalMultitool))
                {
                    fluxableItem = tempItem.copy();
                }
            }
        }

        if(fluxableItem != null && multitool != null)
        {
            ItemStack[] inventory = NBTHelper.getInventoryFromNBTTag(multitool, NBTTags.TEMPORAL_ITEMS);
            ItemStack[] resultInventory;
            if(inventory != null)
            {
                resultInventory = new ItemStack[inventory.length + 1];
                for(int n = 0; n < inventory.length; n++)
                {
                    resultInventory[n] = inventory[n];
                }
            }
            else
            {
                resultInventory = new ItemStack[1];
            }

            resultInventory[resultInventory.length - 1] = fluxableItem;
            NBTHelper.setNBTTagListFromInventory(multitool, NBTTags.TEMPORAL_ITEMS, resultInventory);
            return multitool;
        }
        return null;
    }

    public ItemStack getPocketWatchRecipe()
    {
        ItemStack item;
        ItemStack pocketWatch = null;
        ItemStack module = null;
        boolean moduleExists = false;
        boolean pocketWatchExists = false;

        for(int n = 0; n < matrix.getSizeInventory(); n++)
        {
            item = matrix.getStackInSlot(n);
            if(item != null)
            {
                if(item.getItem() instanceof ItemPocketWatch)
                {
                    pocketWatch = item.copy();
                    if(pocketWatchExists)
                    {
                        return null;
                    }
                    pocketWatchExists = true;

                    ItemStack[] items = NBTHelper.getInventoryFromNBTTag(item, NBTTags.POCKET_WATCH_MODULES);
                    if(items != null && items.length >= 20)
                    {
                        return null;
                    }
                }
                else if(item.getItem() instanceof ItemModule)
                {
                    module = item.copy();
                    if(moduleExists)
                    {
                        return null;
                    }
                    moduleExists = true;

                    if(NBTHelper.hasTag(item, NBTTags.POCKET_WATCH_MODULES))
                    {
                        return null;
                    }
                }
                else
                {
                    return null;
                }
            }
        }

        if(pocketWatch != null && module != null && NBTHelper.hasTag(pocketWatch, NBTTags.POCKET_WATCH_MODULES))
        {
            ItemStack[] items = NBTHelper.getInventoryFromNBTTag(pocketWatch, NBTTags.POCKET_WATCH_MODULES);
            for(int n = 0; n < items.length; n++)
            {
                if(items[n] != null)
                {
                    if(items[n].getItem().equals(module.getItem()))
                    {
                        return null;
                    }
                }
            }
        }
        if(moduleExists && pocketWatchExists)
        {
            return pocketWatchResult();
        }
        return null;
    }

    private ItemStack pocketWatchResult()
    {
        ItemStack tempItem;
        ItemStack module = null;
        ItemStack pocketWatch = null;

        for(int n = 0; n < matrix.getSizeInventory(); n++)
        {
            tempItem = matrix.getStackInSlot(n);
            if (tempItem != null)
            {
                if(tempItem.getItem() instanceof ItemPocketWatch)
                {
                    pocketWatch = tempItem.copy();
                }

                if(tempItem.getItem() instanceof ItemModule)
                {
                    module = tempItem.copy();
                }
            }
        }

        if(module != null && pocketWatch != null)
        {
            ItemStack[] inventory = NBTHelper.getInventoryFromNBTTag(pocketWatch, NBTTags.POCKET_WATCH_MODULES);
            ItemStack[] resultInventory;
            if(inventory != null)
            {
                resultInventory = new ItemStack[inventory.length + 1];
                for(int n = 0; n < inventory.length; n++)
                {
                    resultInventory[n] = inventory[n];
                }
            }
            else
            {
                resultInventory = new ItemStack[1];
            }

            resultInventory[resultInventory.length - 1] = module;
            NBTHelper.setNBTTagListFromInventory(pocketWatch, NBTTags.POCKET_WATCH_MODULES, resultInventory);
            return pocketWatch;
        }
        return null;
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
    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return matrix.isUseableByPlayer(p_75145_1_);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        return null;
    }
}
