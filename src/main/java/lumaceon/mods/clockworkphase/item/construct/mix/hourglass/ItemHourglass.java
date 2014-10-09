package lumaceon.mods.clockworkphase.item.construct.mix.hourglass;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.construct.mix.ItemElementalClockworkConstruct;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class ItemHourglass extends ItemElementalClockworkConstruct
{
    public IIcon activeIcon;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister registry)
    {
        this.itemIcon = registry.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
        this.activeIcon = registry.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "Active");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack is)
    {
        if(NBTHelper.getBoolean(is, NBTTags.ACTIVE))
        {
            return activeIcon;
        }
        else
        {
            return itemIcon;
        }
    }

    public void elementize(Phases phase, EntityItem item)
    {
        super.elementize(phase, item);
        int id = phase.ordinal();
        if(!item.getEntityItem().getItem().equals(ModItems.hourglassElements[id]))
        {
            ItemStack newItem = new ItemStack(ModItems.hourglassElements[id]);
            newItem.setTagCompound(item.getEntityItem().stackTagCompound);
            newItem.setItemDamage(item.getEntityItem().getItemDamage());
            item.setEntityItemStack(newItem);
        }
    }

    public void unelementize(EntityItem item)
    {
        super.unelementize(item);
        if(!item.getEntityItem().getItem().equals(ModItems.hourglass))
        {
            ItemStack newItem = new ItemStack(ModItems.hourglass);
            newItem.setTagCompound(item.getEntityItem().stackTagCompound);
            newItem.setItemDamage(item.getEntityItem().getItemDamage());
            item.setEntityItemStack(newItem);
        }
    }
}