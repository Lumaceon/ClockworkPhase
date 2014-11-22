package lumaceon.mods.clockworkphase.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.item.component.generic.IBaseComponent;
import lumaceon.mods.clockworkphase.lib.Textures;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemClockworkPhaseGeneric extends Item
{
    public ItemClockworkPhaseGeneric()
    {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(50);
        this.setNoRepair();
        this.setCreativeTab(ClockworkPhase.instance.creativeTabClockworkPhase);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        if(is.getItem() instanceof IBaseComponent)
        {
            IBaseComponent component = (IBaseComponent)is.getItem();
            if(component.isComponentQuality(is))
            {
                list.add("Quality: " + "\u00a7e" + component.getGearQuality(is));
            }
            if(component.isComponentSpeedy(is))
            {
                list.add("Speed: " + "\u00a7e" + component.getGearSpeed(is));
            }
            if(component.isComponentMemory(is))
            {
                list.add("Memory: " + "\u00a7e" + component.getMemoryValue(is));
            }
        }
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf('.') + 1));
    }

    @Override
    public String getUnlocalizedName(ItemStack is)
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf('.') + 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister registry)
    {
        this.itemIcon = registry.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }
}
