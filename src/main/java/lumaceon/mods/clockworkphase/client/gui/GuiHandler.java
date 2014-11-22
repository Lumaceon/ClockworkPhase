package lumaceon.mods.clockworkphase.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import lumaceon.mods.clockworkphase.ClockworkPhase;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityTimeWell;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityExtractor;
import lumaceon.mods.clockworkphase.client.gui.container.ContainerExtractor;
import lumaceon.mods.clockworkphase.client.gui.container.ContainerTimeWell;
import lumaceon.mods.clockworkphase.client.gui.interfaces.GuiExtractor;
import lumaceon.mods.clockworkphase.client.gui.interfaces.GuiMultitool;
import lumaceon.mods.clockworkphase.client.gui.interfaces.GuiPocketWatch;
import lumaceon.mods.clockworkphase.client.gui.interfaces.GuiTimeWell;
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
                TileEntity te = world.getTileEntity(x, y, z);
                if(te != null && te instanceof TileEntityExtractor)
                {
                    return new ContainerExtractor((TileEntityExtractor)te, player.inventory, ((TileEntityExtractor) te).phase);
                }
                break;
            case 3:
                te = world.getTileEntity(x, y, z);
                if(te != null && te instanceof TileEntityTimeWell)
                {
                    return new ContainerTimeWell((TileEntityTimeWell)te, player.inventory);
                }
                break;
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
                te = world.getTileEntity(x, y, z);
                if(te != null && te instanceof TileEntityExtractor)
                {
                    return new GuiExtractor((TileEntityExtractor)te, player.inventory, ((TileEntityExtractor) te).phase);
                }
                break;
            case 3:
                te = world.getTileEntity(x, y, z);
                if(te != null && te instanceof TileEntityTimeWell)
                {
                    return new GuiTimeWell((TileEntityTimeWell)te, player.inventory);
                }
                break;
            default:
                return null;
        }
        return null;
    }
}
