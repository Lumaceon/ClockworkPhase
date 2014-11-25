package lumaceon.mods.clockworkphase.item.construct.clockworkarmor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.item.construct.abstracts.*;
import lumaceon.mods.clockworkphase.lib.*;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.TensionHelper;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemChronoArmor extends ItemArmor implements IClockwork, IDisassemble, ISpecialArmor
{
    public ItemChronoArmor(ArmorMaterial material, int renderIndex, int armorType)
    {
        super(material, renderIndex, armorType);
        this.setCreativeTab(ClockworkPhase.instance.creativeTabClockworkPhase);
        this.setMaxStackSize(1);
        this.setMaxDamage(500);
        this.setNoRepair();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
    {
        ItemStack hat = player.inventory.armorItemInSlot(0);
        ItemStack shirt = player.inventory.armorItemInSlot(1);
        ItemStack pants = player.inventory.armorItemInSlot(2);
        ItemStack shoes = player.inventory.armorItemInSlot(3);

        float singleArmorDefense = 0;
        float totalArmorDefense = 0;

        if(is != null && is.getItem() instanceof ItemChronoArmor)
        {
            ArmorProperties hatProp = ((ItemChronoArmor) is.getItem()).getProperties(player, is, new DamageSource("mob"), 0, 0);
            singleArmorDefense += hatProp.AbsorbRatio;
        }

        if(hat != null && hat.getItem() instanceof ItemChronoArmor)
        {
            ArmorProperties hatProp = ((ItemChronoArmor) hat.getItem()).getProperties(player, hat, new DamageSource("mob"), 0, 0);
            totalArmorDefense += hatProp.AbsorbRatio;
        }

        if(shirt != null && shirt.getItem() instanceof ItemChronoArmor)
        {
            ArmorProperties hatProp = ((ItemChronoArmor) shirt.getItem()).getProperties(player, shirt, new DamageSource("mob"), 0, 0);
            totalArmorDefense += hatProp.AbsorbRatio;
        }

        if(pants != null && pants.getItem() instanceof ItemChronoArmor)
        {
            ArmorProperties hatProp = ((ItemChronoArmor) pants.getItem()).getProperties(player, pants, new DamageSource("mob"), 0, 0);
            totalArmorDefense += hatProp.AbsorbRatio;
        }

        if(shoes != null && shoes.getItem() instanceof ItemChronoArmor)
        {
            ArmorProperties hatProp = ((ItemChronoArmor) shoes.getItem()).getProperties(player, shoes, new DamageSource("mob"), 0, 0);
            totalArmorDefense += hatProp.AbsorbRatio;
        }

        list.add("Tension: " + "\u00a7e" + NBTHelper.getInt(is, NBTTags.TENSION_ENERGY) + "/" + "\u00a7e" + NBTHelper.getInt(is, NBTTags.MAX_TENSION));

        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            list.add("");
            list.add("Clockwork Quality: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.QUALITY));
            list.add("Clockwork Speed: " + "\u00A7e" + NBTHelper.getInt(is, NBTTags.SPEED));
            list.add("");
            list.add("Defense (Item): " + "\u00A7e" + singleArmorDefense * 100 + "%");
            list.add("Defense (Total): " + "\u00A7e" + totalArmorDefense * 100 + "%");
        }
        else
        {
            list.add("-Hold shift for details-");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack is, int armorSlot)
    {
        ModelBiped armorModel = null;
        if(is != null && is.getItem() instanceof ItemChronoArmor)
        {
            if(is.getItem() instanceof ItemClockworkHeadpiece)
            {
                armorModel = ClientProxy.getChronoArmorModel(0);
            }
            if(is.getItem() instanceof ItemClockworkChestpiece)
            {
                armorModel = ClientProxy.getChronoArmorModel(1);
            }
            if(is.getItem() instanceof ItemClockworkLeggings)
            {
                armorModel = ClientProxy.getChronoArmorModel(2);
            }
            if(is.getItem() instanceof ItemClockworkBoots)
            {
                armorModel = ClientProxy.getChronoArmorModel(3);
            }

            if(armorModel != null)
            {
                armorModel.bipedHead.showModel = armorSlot == 0;
                armorModel.bipedHeadwear.showModel = armorSlot == 0;
                armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
                armorModel.bipedRightArm.showModel = armorSlot == 1;
                armorModel.bipedLeftArm.showModel = armorSlot == 1;
                armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
                armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
                armorModel.isSneak = entityLiving.isSneaking();
                armorModel.isRiding = entityLiving.isRiding();
                armorModel.isChild = entityLiving.isChild();
                armorModel.heldItemRight = entityLiving.getHeldItem() != null ? 1 : 0;
                if (entityLiving instanceof EntityPlayer)
                {
                    armorModel.aimedBow = ((EntityPlayer) entityLiving).getItemInUseDuration() > 2;
                }
            }
        }
        return armorModel;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        switch(slot)
        {
            case 0:
                return "clockworkphase:textures/armor/chronoArmor.png";
            case 1:
                return "clockworkphase:textures/armor/chronoArmor.png";
            case 2:
                return "clockworkphase:textures/armor/chronoArmor.png";
            default:
                return "clockworkphase:textures/armor/chronoArmor.png";
        }
    }

    @Override
    public int getItemEnchantability()
    {
        return 0;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, World world)
    {
        return 24000;
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

    @Override
    public void addTension(ItemStack is, int tension)
    {
        TensionHelper.addTension(is, tension);
    }

    @Override
    public void removeTension(ItemStack is, int tension)
    {
        TensionHelper.removeTension(is, tension);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack is)
    {

    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack is, DamageSource source, double damage, int slot)
    {
        double fullSetDefenseAdditions = 0.0;
        if(source.isUnblockable())
        {
            if(source.isDamageAbsolute())
            {
                new ArmorProperties(0, 0, 0);
            }
            else
            {
                fullSetDefenseAdditions -= 0.5;
            }
        }

        int tension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY); if(tension <= 0) { return new ArmorProperties(0, 0.0, 0); }
        int quality = NBTHelper.getInt(is, NBTTags.QUALITY); if(quality <= 0) { return new ArmorProperties(0, 0.0, 0); }
        int speed = NBTHelper.getInt(is, NBTTags.SPEED);

        if(speed >= 10)
        {
            double fullSetDefenseFinal = 0.92 / (300.0 / speed);
            if(fullSetDefenseFinal > 0.92) { fullSetDefenseFinal = 0.92; }
            if(speed > 300)
            {
                fullSetDefenseFinal += 0.08 / (400 / (speed - 300));
            }
            if(fullSetDefenseFinal > 1) { fullSetDefenseFinal = 1; }

            fullSetDefenseFinal += fullSetDefenseAdditions;
            return new ArmorProperties(0, fullSetDefenseFinal / 4.0, Integer.MAX_VALUE);
        }

        return new ArmorProperties(0, 0.0, 0);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        return this.getArmorMaterial().getDamageReductionAmount(Math.abs(slot - 3));
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack is, DamageSource source, int damage, int slot)
    {
        int tension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY);
        if(tension <= 0)
        {
            int maxTension = NBTHelper.getInt(is, NBTTags.MAX_TENSION);
            if(maxTension / is.getMaxDamage() == 0)
            {
                is.setItemDamage(is.getMaxDamage() - 1);
            }
            else
            {
                is.setItemDamage(is.getMaxDamage() - (tension / (maxTension / is.getMaxDamage())));
            }
            return;
        }
        int quality = NBTHelper.getInt(is, NBTTags.QUALITY);
        if(quality <= 0)
        {
            int maxTension = NBTHelper.getInt(is, NBTTags.MAX_TENSION);
            if(maxTension / is.getMaxDamage() == 0)
            {
                is.setItemDamage(is.getMaxDamage() - 1);
            }
            else
            {
                is.setItemDamage(is.getMaxDamage() - (tension / (maxTension / is.getMaxDamage())));
            }
            return;
        }
        int speed = NBTHelper.getInt(is, NBTTags.SPEED);

        float efficiency = (float)speed / (float)quality;
        int tensionCost = (int)Math.round(MechanicTweaker.CLOCKWORK_ARMOR_TENSION_COST * Math.pow(efficiency, 2));
        int newTension = tension - tensionCost;

        if(newTension <= 0)
        {
            this.removeTension(is, tension);
            return;
        }
        this.removeTension(is, tensionCost);
    }

    @Override
    public void disassemble(World world, double x, double y, double z, ItemStack is)
    {
        if(world.isRemote)
        {
            return;
        }

        int maxTension = NBTHelper.getInt(is, NBTTags.MAX_TENSION);

        if(maxTension != 0)
        {
            ItemStack mainspring = new ItemStack(ModItems.mainspring);
            NBTHelper.setInteger(mainspring, NBTTags.MAX_TENSION, maxTension);
            NBTHelper.setInteger(mainspring, NBTTags.TENSION_ENERGY, 0);

            world.spawnEntityInWorld(new EntityItem(world, x, y, z, mainspring));
        }

        if(NBTHelper.hasTag(is, NBTTags.CLOCKWORK))
        {
            NBTTagList tagList = NBTHelper.getTagList(is, NBTTags.CLOCKWORK);
            if(tagList.tagCount() > 0)
            {
                ItemStack clockwork = ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(0));
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, clockwork));
            }
        }

        NBTHelper.setInteger(is, NBTTags.TENSION_ENERGY, 0);
        NBTHelper.setInteger(is, NBTTags.MAX_TENSION, 0);
        NBTHelper.setInteger(is, NBTTags.QUALITY, 0);
        NBTHelper.setInteger(is, NBTTags.SPEED, 0);
        NBTHelper.setInteger(is, NBTTags.MEMORY, 0);
        NBTHelper.removeTag(is, NBTTags.CLOCKWORK);
        is.setItemDamage(is.getMaxDamage());
    }
}
