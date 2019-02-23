package lumaceon.mods.clockworkphase.item.construct.hourglass;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.construct.abstracts.ITimeSandSupplier;
import lumaceon.mods.clockworkphase.item.construct.abstracts.ItemTimeSandElementalClockworkAbstract;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.TimeSandHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemHourglass extends ItemTimeSandElementalClockworkAbstract implements ITimeSandSupplier
{

    public ItemHourglass() {
        super();
        this.addPropertyOverride(new ResourceLocation("active"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return NBTHelper.getBoolean(stack, NBTTags.ACTIVE) ? 1.0F : 0.0F;
            }
        });
    }

    @Override
	public void elementize(Phases phase, EntityItem item)
    {
        super.elementize(phase, item);
        int id = phase.ordinal();
        if(!item.getItem().getItem().equals(ModItems.hourglassElements[id]))
        {
            ItemStack newItem = new ItemStack(ModItems.hourglassElements[id]);
            newItem.setTagCompound(item.getItem().getTagCompound());
            newItem.setItemDamage(item.getItem().getItemDamage());
            item.setItem(newItem);
        }
    }

    @Override
	public void unelementize(EntityItem item)
    {
        super.unelementize(item);
        if(!item.getItem().getItem().equals(ModItems.hourglass))
        {
            ItemStack newItem = new ItemStack(ModItems.hourglass);
            newItem.setTagCompound(item.getItem().getTagCompound());
            newItem.setItemDamage(item.getItem().getItemDamage());
            item.setItem(newItem);
        }
    }

    @Override
    public int getTimeSandAvailable(ItemStack is)
    {
        return TimeSandHelper.getTimeSand(is);
    }
}