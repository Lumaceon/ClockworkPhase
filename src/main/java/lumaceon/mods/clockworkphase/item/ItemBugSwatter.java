package lumaceon.mods.clockworkphase.item;

import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.lib.GlobalPhaseReference;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.util.Logger;
import lumaceon.mods.clockworkphase.util.PhaseHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBugSwatter extends ItemClockworkPhase
{
    public ItemBugSwatter()
    {
        super();
        this.setCreativeTab(ClockworkPhase.instance.creativeTabClockworkPhase);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
        Logger.info(Phases.values().length + "  " + (((int)(world.getWorldTime() % (GlobalPhaseReference.phaseDuration * Phases.values().length))) / GlobalPhaseReference.phaseDuration));
        return is;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int meta, float fX, float fY, float fZ)
    {
        Logger.info(PhaseHelper.getPhaseForWorld(world));
        return true;
    }

}
