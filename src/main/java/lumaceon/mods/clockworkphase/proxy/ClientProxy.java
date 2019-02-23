package lumaceon.mods.clockworkphase.proxy;

import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import lumaceon.mods.clockworkphase.block.tileentity.TileEntityCelestialCompass;
import lumaceon.mods.clockworkphase.client.ClientTickHandler;
import lumaceon.mods.clockworkphase.client.handler.RenderHandler;
import lumaceon.mods.clockworkphase.client.particle.ParticleGenerator;
import lumaceon.mods.clockworkphase.client.render.model.*;
import lumaceon.mods.clockworkphase.client.settings.Keybindings;
import lumaceon.mods.clockworkphase.client.handler.KeyHandler;
import lumaceon.mods.clockworkphase.item.construct.pocketwatch.module.ItemModule;
import lumaceon.mods.clockworkphase.lib.NBTTags;
import lumaceon.mods.clockworkphase.util.OverlayRenderData;
import lumaceon.mods.clockworkphase.util.InventorySearchHelper;
import lumaceon.mods.clockworkphase.util.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

import lumaceon.mods.clockworkphase.custom.RenderTileEntityCelestialCompass;

public class ClientProxy extends CommonProxy
{
    public static ParticleGenerator particleGenerator;

    public static ModelChronoArmorHeadpiece chronoArmorHeadpiece = new ModelChronoArmorHeadpiece(1.0F);
    public static ModelChronoArmorChestpiece chronoArmorChestpiece = new ModelChronoArmorChestpiece(1.0F);
    public static ModelChronoArmorLeggings chronoArmorLeggings = new ModelChronoArmorLeggings(0.5F);
    public static ModelChronoArmorBoots chronoArmorBoots = new ModelChronoArmorBoots(1.0F);

    public static ArrayList<OverlayRenderData> overlayRenderers = new ArrayList<OverlayRenderData>(20);

    public static ModelBiped getChronoArmorModel(int id)
    {
        switch(id)
        {
            case 0:
                return chronoArmorHeadpiece;
            case 1:
                return chronoArmorChestpiece;
            case 2:
                return chronoArmorLeggings;
            default:
                return chronoArmorBoots;
        }
    }

    @Override
    public void setRenderingForPlayer(EntityPlayer player)
    {
        overlayRenderers.clear();
        if(player != null && player.inventory != null)
        {
            ItemStack[] watches = InventorySearchHelper.getPocketWatches(player.inventory);
            for(int n = 0; watches != null && n < watches.length; n++) //Each pocket watch
            {
                if(!watches[n].isEmpty())
                {
                    ItemStack[] items = NBTHelper.getInventoryFromNBTTag(watches[n], NBTTags.POCKET_WATCH_MODULES);
                    for(int i = 0; items != null && i < items.length; i++) //Each module in pocket watch
                    {
                        if(!items[i].isEmpty() && NBTHelper.getBoolean(items[i], NBTTags.ACTIVE))
                        {
                            boolean dupe = false;
                            for(int q = 0; q < overlayRenderers.size() && !dupe; q++) //Check for duplicates
                            {
                                if(overlayRenderers.get(q).item.getItem().equals(items[i].getItem()))
                                {
                                    dupe = true;
                                }
                            }

                            if(!dupe)
                            {
                                overlayRenderers.add(new OverlayRenderData(items[i], NBTHelper.getInt(items[i], NBTTags.MODULE_POWER) / ((ItemModule)items[i].getItem()).getPowerDivisor()));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void setRenderNumberForItemStack(ItemStack item, int number)
    {
        for(int n = 0; n < overlayRenderers.size(); n++)
        {
            OverlayRenderData renderer = overlayRenderers.get(n);
            if(renderer != null && renderer.item.getItem().equals(item.getItem()))
            {
                renderer.setNumberToRender(number);
            }
        }
    }

    @Override
    public void registerKeybindings()
    {
        ClientRegistry.registerKeyBinding(Keybindings.multitool);
        ClientRegistry.registerKeyBinding(Keybindings.temporal);
    }

    public void initializeParticleGenerator()
    {
        particleGenerator = new ParticleGenerator(Minecraft.getMinecraft());
    }

    @Override
    public void initializeSideOnlyHandlers()
    {
//        MinecraftForge.EVENT_BUS.register(new TextureStitchHandler());
        MinecraftForge.EVENT_BUS.register(new RenderHandler());
        FMLCommonHandler.instance().bus().register(new ClientTickHandler());
        FMLCommonHandler.instance().bus().register(new KeyHandler());
    }

    @Override
    public void registerModels()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCelestialCompass.class, new RenderTileEntityCelestialCompass());
    }

    public World getStaticWorld()
    {
        return Minecraft.getMinecraft().world;
    }

    @Override
    public RayTraceResult getObjectLookedAt()
    {
        return Minecraft.getMinecraft().objectMouseOver;
    }
}
