package lumaceon.mods.clockworkphase.client.gui;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.client.gui.container.*;
import lumaceon.mods.clockworkphase.inventory.InventoryClockworkAssembly;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeWell;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.client.gui.interfaces.*;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    public GuiHandler()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(ClockworkPhase.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(ID)
        {
            case 2:
                TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
                if(te instanceof TileEntityExtractor)
                {
                    return new ContainerExtractor((TileEntityExtractor)te, player.inventory, ((TileEntityExtractor) te).phase);
                }
                break;
            case 3:
                te = world.getTileEntity(new BlockPos(x, y, z));
                if(te instanceof TileEntityTimeWell)
                {
                    return new ContainerTimeWell((TileEntityTimeWell)te, player.inventory);
                }
                break;
            case 4:
                return new ContainerClockworkAssemblyTableCW(player.inventory, world);
            case 5:
                return new ContainerClockworkAssemblyTableMainspring(player.inventory, world);
            case 6:
                return new ContainerClockworkAssemblyTableAssemble(player.inventory, world);
            default:
                return null;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity te;
        switch(ID)
        {
            case 0:
                return new GuiMultitool(NBTHelper.getInventoryFromNBTTag(player.inventory.getCurrentItem(), NBTTags.TEMPORAL_ITEMS));
            case 1:
                return new GuiPocketWatch(NBTHelper.getInventoryFromNBTTag(player.inventory.getCurrentItem(), NBTTags.POCKET_WATCH_MODULES));
            case 2:
                te = world.getTileEntity(new BlockPos(x, y, z));
                if(te instanceof TileEntityExtractor)
                {
                    return new GuiExtractor((TileEntityExtractor)te, player.inventory, ((TileEntityExtractor) te).phase);
                }
                break;
            case 3:
                te = world.getTileEntity(new BlockPos(x, y, z));
                if(te instanceof TileEntityTimeWell)
                {
                    return new GuiTimeWell((TileEntityTimeWell)te, player.inventory);
                }
                break;
            case 4:
                return new GuiClockworkAssemblyTableCW(player.inventory, world, x, y, z);
            case 5:
                return new GuiClockworkAssemblyTableMainspring(player.inventory, world, x, y, z);
            case 6:
                return new GuiClockworkAssemblyTableAssemble(player.inventory, world, x, y, z);
            default:
                return null;
        }
        return null;
    }
}
