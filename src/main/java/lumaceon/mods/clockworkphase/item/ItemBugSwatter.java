package lumaceon.mods.clockworkphase.item;

import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.block.extractor.ExtractorAreas;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeSandCapacitor;
import lumaceon.mods.clockworkphase.lib.GlobalPhaseReference;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.lib.Phases;
import lumaceon.mods.clockworkphase.util.Logger;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.Collection;

public class ItemBugSwatter extends ItemClockworkPhaseGeneric
{
    public ItemBugSwatter()
    {
        super();
        this.setCreativeTab(ClockworkPhase.instance.creativeTabClockworkPhase);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        Logger.info(Phases.values().length + "  " + (((int)(world.getWorldTime() % (GlobalPhaseReference.phaseDuration * Phases.values().length))) / GlobalPhaseReference.phaseDuration));
        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(player.isSneaking())
        {
            TileEntity te = world.getTileEntity(pos);
            if(te instanceof TileEntityTimeSandCapacitor)
            {
                if(!world.isRemote)
                {
                    player.sendMessage(new TextComponentString("Internal Time Sand (Server): \u00A76" + ((TileEntityTimeSandCapacitor) te).getTimeSand()));
                }
                else
                {
                    player.sendMessage(new TextComponentString("Internal Time Sand (Client): \u00A76" + ((TileEntityTimeSandCapacitor) te).getTimeSand()));
                }
            }
            return EnumActionResult.SUCCESS;
        }

        for(int n = 0; n < ExtractorAreas.EXTRACTORS.length; n++)
        {
            Collection areas = ExtractorAreas.EXTRACTORS[n].values();
            for(int i = 0; i < areas.toArray().length; i++)
            {
                if(areas.toArray()[i] != null)
                {
                    ExtractorAreas area = ((ExtractorAreas)areas.toArray()[i]);
                    for(int g = 0; g < area.areas.size(); g++)
                    {
                        if(area.areas.get(g) != null)
                        {
                            Logger.info("Extractor: " + area.areas.get(g).extractorX + ", " + area.areas.get(g).extractorY + ", " + area.areas.get(g).extractorZ);
                            Logger.info("Low: " + area.areas.get(g).lowX + ", " + area.areas.get(g).lowY + ", " + area.areas.get(g).lowZ);
                            Logger.info("High: " + area.areas.get(g).highX + ", " + area.areas.get(g).highY + ", " + area.areas.get(g).highZ);
                        }
                    }
                }
            }
        }
        return EnumActionResult.SUCCESS;
    }

}
