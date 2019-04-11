package lumaceon.mods.clockworkphase.item.construct.hourglass;

import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.PhaseHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemHourglassLight extends ItemHourglass
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
            int tensionCost = (int)Math.round(MechanicTweaker.LIGHT_HOURGLASS_TENSION_COST * Math.pow(efficiency, 2));
            if(PhaseHelper.getPhaseForWorld(world).equals(Phases.LIGHT)) { tensionCost *= 0.1; }
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

            if(speed < 50)
            {
                NBTHelper.setBoolean(is, NBTTags.ACTIVE, false);
                player.sendMessage(new TextComponentString("Your clockwork's speed is too slow to be of any use."));
                return;
            }

            if(!player.onGround)
            {
                return;
            }

            int x = (int)Math.floor(player.posX);
            int y = (int)Math.floor(player.posY + 1.5);
            int z = (int)Math.floor(player.posZ);
            int lightness = world.getLightFromNeighbors(new BlockPos(x, y, z));

            if(world.isAirBlock(new BlockPos(x, y, z)))
            {
                if(lightness <= 7)
                {
                    if(speed > 500) {speed = 500;}
                    world.setBlockState(new BlockPos(x, y, z), ModBlocks.hourglassLight.getStateFromMeta(10 + (speed / 100)), 2);
                    this.removeTension(is, tensionCost);
                }
            }
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        boolean isActive = NBTHelper.getBoolean(player.getHeldItem(hand), NBTTags.ACTIVE);
        NBTHelper.setBoolean(player.getHeldItem(hand), NBTTags.ACTIVE, !isActive);
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
