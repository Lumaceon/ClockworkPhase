package lumaceon.mods.clockworkphase.item;

import lumaceon.mods.clockworkphase.custom.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.item.component.generic.IBaseComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemClockworkPhaseGeneric extends Item implements IHasModel
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
    public void addInformation(ItemStack is, World worldIn, List<String> list, ITooltipFlag flagIn)
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
    @SideOnly(Side.CLIENT)
    public void registerIcons()
    {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
//        this.itemIcon = registry.registerIcon(this.getTranslationKey().substring(this.getTranslationKey().indexOf(".") + 1));
    }
}
