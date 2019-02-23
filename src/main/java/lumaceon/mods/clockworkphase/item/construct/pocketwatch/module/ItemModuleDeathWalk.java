package lumaceon.mods.clockworkphase.item.construct.pocketwatch.module;

import lumaceon.mods.clockworkphase.extendeddata.PlayerCapability;
import lumaceon.mods.clockworkphase.lib.MechanicTweaker;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.proxy.ClientProxy;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import lumaceon.mods.clockworkphase.util.TimeSandHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scala.collection.generic.BitOperations;

public class ItemModuleDeathWalk extends ItemModule
{
    @Override
    public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        if(entity != null && entity instanceof EntityPlayer && NBTHelper.getBoolean(is, NBTTags.ACTIVE) && entity.onGround)
        {
            EntityPlayer player = (EntityPlayer)entity;
            PlayerCapability.IPlayerHandler cap = player.getCapability(PlayerCapability.CAPABILITY_PLAYER, null);
            int deathWalkPower = NBTHelper.getInt(is, NBTTags.MODULE_POWER);

            if(deathWalkPower < MechanicTweaker.DEATH_ATTACK_MAX)
            {
                double addition = (Math.abs(cap.getPrevPos().getX() - player.posX) + Math.abs(cap.getPrevPos().getY() - player.posY) + Math.abs(cap.getPrevPos().getZ() - player.posZ)) * 5;
                cap.setPrevPos(new BlockPos(player.posX, player.posY, player.posZ));
                if(addition > 20) { addition = 20; }

                deathWalkPower += addition;
                if(deathWalkPower > MechanicTweaker.DEATH_ATTACK_MAX) { deathWalkPower = MechanicTweaker.DEATH_ATTACK_MAX; }

                if(addition > 0)
                {
                    int timeSandRequirement = MechanicTweaker.TIME_SAND_COST_DEATH_WALK_MODULE * (int)addition;
                    timeSandRequirement -= TimeSandHelper.removeTimeSandFromInventory(player.inventory, timeSandRequirement);
                    if(timeSandRequirement > 0)
                    {
                        return;
                    }
                }

                NBTHelper.setInteger(is, NBTTags.MODULE_POWER, deathWalkPower);
            }
            if(world.isRemote)
            {
                ClientProxy.setRenderNumberForItemStack(is, deathWalkPower / MechanicTweaker.DEATH_ATTACK_PER_HEALTH);
            }
        }
    }

    @Override
    public int getPowerDivisor()
    {
        return MechanicTweaker.DEATH_ATTACK_PER_HEALTH;
    }
}
