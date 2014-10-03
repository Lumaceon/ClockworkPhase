package lumaceon.mods.clockworkphase.block;

import lumaceon.mods.clockworkphase.item.IDisassemble;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockDisassembler extends BlockClockworkPhase
{
    public BlockDisassembler(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float f0, float f1, float f2)
    {
        if(!player.isSneaking())
        {
            if(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof IDisassemble)
            {
                ((IDisassemble) player.getHeldItem().getItem()).disassemble(world, x, y, z, player.getHeldItem());
                return true;
            }
        }
        return false;
    }
}
