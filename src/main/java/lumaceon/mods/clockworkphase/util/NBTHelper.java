package lumaceon.mods.clockworkphase.util;

import lumaceon.mods.clockworkphase.lib.NBTTags;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTHelper
{
    public static boolean hasTag(ItemStack is, String keyName)
    {
        return !is.isEmpty() && is.getTagCompound() != null && is.getTagCompound().hasKey(keyName);
    }

    public static void removeTag(ItemStack is, String keyName)
    {
        if (is.getTagCompound() != null)
        {
            is.getTagCompound().removeTag(keyName);
        }
    }

    private static void initNBTTagCompound(ItemStack is)
    {
        if (is.getTagCompound() == null)
        {
            is.setTagCompound(new NBTTagCompound());
        }
    }

    public static void setNBTTagListFromInventory(ItemStack is, String keyName, ItemStack[] inventory)
    {
        initNBTTagCompound(is);

        NBTTagList nbtList = new NBTTagList();
        for (int index = 0; index < inventory.length; index++)
        {
            if (!inventory[index].isEmpty())
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("slot_index", (byte)index);
                inventory[index].writeToNBT(tag);
                nbtList.appendTag(tag);
            }
        }
        setTagList(is, keyName, nbtList);
    }

    public static ItemStack[] getInventoryFromNBTTag(ItemStack is, String keyName)
    {
        initNBTTagCompound(is);

        if(!is.getTagCompound().hasKey(keyName))
        {
            return null;
        }

        NBTTagList nbt = getTagList(is, keyName);
        ItemStack[] inventory;
        inventory = new ItemStack[nbt.tagCount()];

        for (int i = 0; i < nbt.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = nbt.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("slot_index");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = new ItemStack(tagCompound);
            }
        }
        return inventory;
    }

    public static void setTag(ItemStack is, String keyName, NBTBase nbt)
    {
        initNBTTagCompound(is);

        is.getTagCompound().setTag(keyName, nbt);
    }

    public static void setTagList(ItemStack is, String keyName, NBTTagList nbt)
    {
        initNBTTagCompound(is);

        is.getTagCompound().setTag(keyName, nbt);
    }

    public static NBTBase getTag(ItemStack is, String keyName)
    {
        initNBTTagCompound(is);

        if(!is.getTagCompound().hasKey(keyName))
        {
            setTagList(is, keyName, new NBTTagList());
        }

        return is.getTagCompound().getTag(keyName);
    }

    public static NBTTagList getTagList(ItemStack is, String keyName)
    {
        initNBTTagCompound(is);

        if(!is.getTagCompound().hasKey(keyName))
        {
            setTagList(is, keyName, new NBTTagList());
        }

        NBTBase returnValue = is.getTagCompound().getTag(keyName);

        if(!(returnValue instanceof NBTTagList))
        {
            Logger.error("Method getTagList in NBTHelper attempted to load an invalid tag.");
            return new NBTTagList();
        }
        return (NBTTagList)returnValue;
    }

    public static void setLong(ItemStack is, String keyName, long keyValue)
    {
        initNBTTagCompound(is);

        is.getTagCompound().setLong(keyName, keyValue);
    }

    public static String getString(ItemStack is, String keyName)
    {
        initNBTTagCompound(is);

        if (!is.getTagCompound().hasKey(keyName))
        {
            setString(is, keyName, "");
        }

        return is.getTagCompound().getString(keyName);
    }

    public static void setString(ItemStack is, String keyName, String keyValue)
    {
        initNBTTagCompound(is);

        is.getTagCompound().setString(keyName, keyValue);
    }

    public static boolean getBoolean(ItemStack is, String keyName)
    {
        initNBTTagCompound(is);

        if (!is.getTagCompound().hasKey(keyName))
        {
            setBoolean(is, keyName, false);
        }

        return is.getTagCompound().getBoolean(keyName);
    }

    public static void setBoolean(ItemStack is, String keyName, boolean keyValue)
    {
        initNBTTagCompound(is);

        is.getTagCompound().setBoolean(keyName, keyValue);
    }

    public static byte getByte(ItemStack is, String keyName)
    {
        initNBTTagCompound(is);

        if (!is.getTagCompound().hasKey(keyName))
        {
            setByte(is, keyName, (byte) 0);
        }

        return is.getTagCompound().getByte(keyName);
    }

    public static void setByte(ItemStack is, String keyName, byte keyValue)
    {
        initNBTTagCompound(is);

        is.getTagCompound().setByte(keyName, keyValue);
    }

    // short
    public static short getShort(ItemStack is, String keyName)
    {
        initNBTTagCompound(is);

        if (!is.getTagCompound().hasKey(keyName))
        {
            setShort(is, keyName, (short) 0);
        }

        return is.getTagCompound().getShort(keyName);
    }

    public static void setShort(ItemStack is, String keyName, short keyValue)
    {
        initNBTTagCompound(is);

        is.getTagCompound().setShort(keyName, keyValue);
    }

    public static int getInt(ItemStack is, String keyName)
    {
        initNBTTagCompound(is);

        if (!is.getTagCompound().hasKey(keyName))
        {
            setInteger(is, keyName, 0);
        }

        return is.getTagCompound().getInteger(keyName);
    }

    public static void setInteger(ItemStack is, String keyName, int keyValue)
    {
        initNBTTagCompound(is);

        is.getTagCompound().setInteger(keyName, keyValue);
    }

    public static long getLong(ItemStack is, String keyName)
    {
        initNBTTagCompound(is);

        if (!is.getTagCompound().hasKey(keyName))
        {
            setLong(is, keyName, 0);
        }

        return is.getTagCompound().getLong(keyName);
    }

    public static float getFloat(ItemStack is, String keyName)
    {
        initNBTTagCompound(is);

        if (!is.getTagCompound().hasKey(keyName))
        {
            setFloat(is, keyName, 0);
        }

        return is.getTagCompound().getFloat(keyName);
    }

    public static void setFloat(ItemStack is, String keyName, float keyValue)
    {
        initNBTTagCompound(is);

        is.getTagCompound().setFloat(keyName, keyValue);
    }

    public static double getDouble(ItemStack is, String keyName)
    {
        initNBTTagCompound(is);

        if (!is.getTagCompound().hasKey(keyName))
        {
            setDouble(is, keyName, 0);
        }

        return is.getTagCompound().getDouble(keyName);
    }

    public static void setDouble(ItemStack is, String keyName, double keyValue)
    {
        initNBTTagCompound(is);

        is.getTagCompound().setDouble(keyName, keyValue);
    }
}