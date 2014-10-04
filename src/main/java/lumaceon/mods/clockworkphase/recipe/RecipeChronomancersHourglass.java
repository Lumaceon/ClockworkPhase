package lumaceon.mods.clockworkphase.recipe;

import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class RecipeChronomancersHourglass implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting ic, World world)
    {
        ItemStack item;
        for(int n = 0; n < ic.getSizeInventory(); n++)
        {
            item = ic.getStackInSlot(n);
            if(item != null)
            {
                //101
                //101
                //101
                if(n == 0 || n == 2 || n == 3 || n == 5 || n == 6 || n == 8)
                {
                    ArrayList<ItemStack> metals = OreDictionary.getOres("ingotBrass");
                    boolean equal = false;

                    for(int i = 0; i < metals.size(); i++)
                    {
                        if(metals.get(i).getItem().equals(item.getItem()))
                        {
                            equal = true;
                        }
                    }

                    if(!equal)
                    {
                        return false;
                    }
                }
                else if(n == 1 && !item.getItem().equals(ModItems.clockwork))
                {
                    return false;
                }
                else if(n == 4 && !item.getItem().equals(ModItems.mainspring))
                {
                    return false;
                }
                else if(n == 7 && !item.getItem().equals(ModItems.blandHourglass))
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting ic)
    {
        ItemStack output = new ItemStack(ModItems.hourglass);
        ItemStack mainspring = ic.getStackInSlot(4);
        ItemStack clockwork = ic.getStackInSlot(1);

        int maxTension = NBTHelper.getInt(mainspring, NBTTags.MAX_TENSION);
        int currentTension = NBTHelper.getInt(mainspring, NBTTags.TENSION_ENERGY);

        NBTHelper.setInteger(output, NBTTags.MAX_TENSION, maxTension);
        NBTHelper.setInteger(output, NBTTags.TENSION_ENERGY, currentTension);

        if(maxTension / 10 == 0) { output.setItemDamage(output.getMaxDamage()); }
        else { output.setItemDamage(10 - (currentTension / (maxTension / 10))); }

        NBTHelper.setInteger(output, NBTTags.QUALITY, NBTHelper.getInt(clockwork, NBTTags.QUALITY));
        NBTHelper.setInteger(output, NBTTags.SPEED, NBTHelper.getInt(clockwork, NBTTags.SPEED));
        NBTHelper.setInteger(output, NBTTags.MEMORY, NBTHelper.getInt(clockwork, NBTTags.MEMORY));

        NBTTagList nbtList = new NBTTagList();
        NBTTagCompound tag = new NBTTagCompound();
        clockwork.writeToNBT(tag);
        nbtList.appendTag(tag);
        NBTHelper.setTag(output, NBTTags.CLOCKWORK, nbtList);

        return output;
    }

    @Override
    public int getRecipeSize()
    {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return new ItemStack(ModItems.hourglass);
    }
}
