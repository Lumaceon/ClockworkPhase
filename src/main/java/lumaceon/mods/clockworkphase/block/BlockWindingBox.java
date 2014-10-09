package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.item.construct.clockwork.IClockwork;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockWindingBox extends BlockClockworkPhase
{
    public BlockWindingBox(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float f0, float f1, float f2)
    {
        if(!player.isSneaking())
        {
            if(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof IClockwork)
            {
                ItemStack is = player.getHeldItem();
                if(NBTHelper.hasTag(is, NBTTags.MAX_TENSION))
                {
                    ((IClockwork) is.getItem()).addTension(is, NBTHelper.getInt(is, NBTTags.MAX_TENSION) / 25);
                    return true;
                }
            }
        }
        return false;
    }
}
