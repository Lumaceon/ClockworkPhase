package lumaceon.mods.clockworkphase.item.construct.hourglass;

import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.PhaseHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemHourglassWater extends ItemHourglass
{
    @Override
    public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        if(entity instanceof EntityPlayer && NBTHelper.getBoolean(is, NBTTags.ACTIVE))
        {
            EntityPlayer player = (EntityPlayer)entity;
            int tension = NBTHelper.getInt(is, NBTTags.TENSION_ENERGY);
            int quality = NBTHelper.getInt(is, NBTTags.QUALITY);
            if(quality <= 0) {
                NBTHelper.setBoolean(is, NBTTags.ACTIVE, false);
                return;
            }
            int speed = NBTHelper.getInt(is, NBTTags.SPEED);
            int memory = NBTHelper.getInt(is, NBTTags.MEMORY);

            float efficiency = (float)speed / (float)quality;
            int tensionCost = (int)Math.round(MechanicTweaker.WATER_HOURGLASS_TENSION_COST * Math.pow(efficiency, 2));
            if(PhaseHelper.getPhaseForWorld(world).equals(Phases.WATER)) { tensionCost *= 0.1; }
            int newTension = tension - tensionCost;

            if(newTension <= 0)
            {
                this.removeTension(is, tension);
                NBTHelper.setBoolean(is, NBTTags.ACTIVE, false);
                return;
            }

            if(efficiency > 10)
            {
                NBTHelper.setBoolean(is, NBTTags.ACTIVE, false);
                player.sendMessage(new TextComponentString("Your clockwork's quality can't handle it's speed."));
                return;
            }

            if(speed > 10)
            {
                player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 3, 0, false, true));
                if(player.isInWater())
                {
                    if(!player.isPotionActive(MobEffects.REGENERATION))
                    {
                        player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 10, speed / 200, false, true));
                    }
                    player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 3, speed / 35, false, true));
                    player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 3, 0, false, true));
                }
                this.removeTension(is, tensionCost);
            }
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack is = player.getHeldItem(hand);
        boolean isActive = NBTHelper.getBoolean(is, NBTTags.ACTIVE);
        NBTHelper.setBoolean(is, NBTTags.ACTIVE, !isActive);
        return EnumActionResult.SUCCESS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack is = player.getHeldItem(hand);
        boolean isActive = NBTHelper.getBoolean(is, NBTTags.ACTIVE);
        NBTHelper.setBoolean(is, NBTTags.ACTIVE, !isActive);
        return ActionResult.newResult(EnumActionResult.SUCCESS, is);
    }
}
