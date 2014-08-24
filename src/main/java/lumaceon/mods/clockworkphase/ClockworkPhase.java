package lumaceon.mods.clockworkphase;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumaceon.mods.clockworkphase.config.ConfigurationHandler;
import lumaceon.mods.clockworkphase.creativetab.CreativeTabClockworkPhase;
import lumaceon.mods.clockworkphase.eventhandlers.GrowthHandler;
import lumaceon.mods.clockworkphase.init.ModBlocks;
import lumaceon.mods.clockworkphase.init.ModItems;
import lumaceon.mods.clockworkphase.init.Recipes;
import lumaceon.mods.clockworkphase.lib.Reference;
import lumaceon.mods.clockworkphase.proxy.IProxy;
import lumaceon.mods.clockworkphase.util.Logger;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ClockworkPhase
{
    @Mod.Instance(Reference.MOD_ID)
    public static ClockworkPhase instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static IProxy proxy;

    @SideOnly(Side.CLIENT)
    public CreativeTabClockworkPhase creativeTabClockworkPhase = new CreativeTabClockworkPhase(CreativeTabs.getNextID(), Reference.MOD_NAME);

    @Mod.EventHandler
    public void preInitialize(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        ModBlocks.init();

        ModItems.init();
    }

    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event)
    {
        ModBlocks.registerTileEntities();

        Recipes.init();

        MinecraftForge.TERRAIN_GEN_BUS.register(new GrowthHandler());

        proxy.initializeParticleGenerator();
    }

    @Mod.EventHandler
    public void postInitialize(FMLPostInitializationEvent event)
    {
        for(int i = 0; i < OreDictionary.getOreNames().length; i++)
        {
            Logger.info(OreDictionary.getOreNames()[i]);
        }
    }
}