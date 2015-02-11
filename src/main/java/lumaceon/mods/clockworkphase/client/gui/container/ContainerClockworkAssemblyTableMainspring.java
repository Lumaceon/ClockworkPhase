package lumaceon.mods.clockworkphase.client.gui.container;

import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.api.MainspringMetal;
import lumaceon.mods.clockworkphase.client.gui.components.SlotMainspring;
import lumaceon.mods.clockworkphase.client.gui.components.SlotMainspringMetals;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.inventory.InventoryMainspringAssembly;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class ContainerClockworkAssemblyTableMainspring extends Container
{
    private InventoryMainspringAssembly matrix = new InventoryMainspringAssembly(this);
    private InventoryCraftResult result = new InventoryCraftResult();
    private World world;

    public ContainerClockworkAssemblyTableMainspring(InventoryPlayer ip, World world)
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

        this.addSlotToContainer(new SlotMainspringMetals(matrix, 0, 102, 57));
        this.addSlotToContainer(new SlotMainspringMetals(matrix, 1, 120, 57));
        this.addSlotToContainer(new SlotMainspringMetals(matrix, 2, 138, 57));
        this.addSlotToContainer(new SlotMainspringMetals(matrix, 3, 138, 75));
        this.addSlotToContainer(new SlotMainspringMetals(matrix, 4, 138, 93));
        this.addSlotToContainer(new SlotMainspringMetals(matrix, 5, 120, 93));
        this.addSlotToContainer(new SlotMainspringMetals(matrix, 6, 102, 93));
        this.addSlotToContainer(new SlotMainspringMetals(matrix, 7, 102, 75));

        this.addSlotToContainer(new SlotMainspring(result, 0, 120, 75));

        this.onCraftMatrixChanged(this.matrix);
    }

    public void addToMainspring()
    {
        ItemStack item;
        ItemStack mainspring = result.getStackInSlot(0);
        if(mainspring != null && mainspring.getItem().equals(ModItems.mainspring))
        {
            //Find the value of the metals
            int totalMetalValue = 0;
            for(int i = 0; i < 8; i++)
            {
                item = matrix.getStackInSlot(i);
                if(item != null)
                {
                    MainspringMetal metal;
                    ArrayList<ItemStack> ores;
                    boolean found = false;
                    for(int n = 0; n < ClockworkPhase.MAINSPRING_METAL_DICTIONARY.mainspringMetals.size() && !found; n++)
                    {
                        metal = ClockworkPhase.MAINSPRING_METAL_DICTIONARY.mainspringMetals.get(n);
                        ores = OreDictionary.getOres(metal.metalName);
                        for(ItemStack temp : ores)
                        {
                            if(OreDictionary.itemMatches(temp, item, false))
                            {
                                totalMetalValue += metal.metalValue;
                                found = true;
                            }
                        }
                    }
                    matrix.decrStackSize(i, 1);
                }
            }

            if(totalMetalValue > 0) //Do nothing if there's no metal value (all metal slots empty).
            {
                int previousMaxTension = NBTHelper.getInt(mainspring, NBTTags.MAX_TENSION);

                //Add the value to the mainspring
                if(!NBTHelper.hasTag(mainspring, NBTTags.MAX_TENSION))
                {
                    NBTHelper.setInteger(mainspring, NBTTags.MAX_TENSION, totalMetalValue);
                }
                else
                {
                    if(previousMaxTension + totalMetalValue >= MechanicTweaker.MAX_TENSION)
                    {
                        NBTHelper.setInteger(mainspring, NBTTags.MAX_TENSION, MechanicTweaker.MAX_TENSION);
                    }
                    else
                    {
                        NBTHelper.setInteger(mainspring, NBTTags.MAX_TENSION, previousMaxTension + totalMetalValue);
                    }
                }

                //Adjust metadata values, just for the sake of clarity.
                if(mainspring.getMaxDamage() == 0)
                {
                    mainspring.setItemDamage(0);
                }
                else if(MechanicTweaker.MAX_TENSION / mainspring.getMaxDamage() == 0)
                {
                    mainspring.setItemDamage(0);
                }
                else
                {
                    mainspring.setItemDamage(mainspring.getMaxDamage() - ((previousMaxTension + totalMetalValue) / (MechanicTweaker.MAX_TENSION / mainspring.getMaxDamage())));
                }
            }
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
        super.onContainerClosed(p_75134_1_);

        if (!this.world.isRemote)
        {
            for (int i = 0; i < 8; ++i)
            {
                ItemStack itemstack = this.matrix.getStackInSlotOnClosing(i);

                if (itemstack != null)
                {
                    p_75134_1_.dropPlayerItemWithRandomChoice(itemstack, false);
                }
            }

            ItemStack item = this.result.getStackInSlotOnClosing(0);

            if(item != null)
            {
                p_75134_1_.dropPlayerItemWithRandomChoice(item, false);
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
