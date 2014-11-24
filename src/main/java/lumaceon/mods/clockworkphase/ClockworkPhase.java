package lumaceon.mods.clockworkphase;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.client.gui.GuiHandler;
import lumaceon.mods.clockworkphase.config.ConfigurationHandler;
import lumaceon.mods.clockworkphase.creativetab.CreativeTabClockworkPhase;
import lumaceon.mods.clockworkphase.handler.*;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModFluids;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.lib.ValidBlockLists;
import lumaceon.mods.clockworkphase.recipe.Recipes;
import lumaceon.mods.clockworkphase.lib.Reference;
import lumaceon.mods.clockworkphase.network.PacketHandler;
import lumaceon.mods.clockworkphase.proxy.IProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ClockworkPhase
{
    @Mod.Instance(Reference.MOD_ID)
    public static ClockworkPhase instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static IProxy proxy;

    public CreativeTabClockworkPhase creativeTabClockworkPhase = new CreativeTabClockworkPhase(CreativeTabs.getNextID(), Reference.MOD_NAME);

    @Mod.EventHandler
    public void preInitialize(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        ModFluids.init();

        ModBlocks.init();

        ModItems.init();

        proxy.registerKeybindings();
    }

    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event)
    {
        ModFluids.registerBlocks();

        ModBlocks.registerTileEntities();

        proxy.registerModels();

        Recipes.init();

        BucketHandler.INSTANCE.buckets.put(ModBlocks.timeSand, ModItems.timeSandBucket);

        FMLCommonHandler.instance().bus().register(new TickHandler());
        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
        MinecraftForge.TERRAIN_GEN_BUS.register(new WorldGenHandler());
        MinecraftForge.EVENT_BUS.register(new EntityHandler());
        MinecraftForge.EVENT_BUS.register(new WorldHandler());
        proxy.initializeSideOnlyHandlers();

        proxy.initializeParticleGenerator();

        new GuiHandler();

        PacketHandler.init();
    }

    @Mod.EventHandler
    public void postInitialize(FMLPostInitializationEvent event)
    {
        ValidBlockLists.initValidBlocks();
    }
}