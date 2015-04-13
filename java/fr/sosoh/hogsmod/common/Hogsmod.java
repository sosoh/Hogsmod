package fr.sosoh.hogsmod.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.sosoh.hogsmod.common.event.EventHandlerExtendedProps;
import fr.sosoh.hogsmod.common.items.ItemElderWand;
import fr.sosoh.hogsmod.common.packet.PacketMana;
import fr.sosoh.hogsmod.proxy.CommonProxy;

@Mod(modid = Hogsmod.MODID, version = Hogsmod.VERSION)
public class Hogsmod
{
    public static final String MODID = "hogsmod";
    public static final String VERSION = "0.1";
	public static SimpleNetworkWrapper network;
    
	public static Item itemElderWand;
	
	@Instance("hogsmod")
	public static Hogsmod instance;
	
	@SidedProxy(clientSide = "fr.sosoh.hogsmod.proxy.ClientProxy", serverSide = "fr.sosoh.hogsmod.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		itemElderWand = new ItemElderWand().setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("elderwand");
		GameRegistry.registerItem(itemElderWand, "item_elderwand");
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
		network.registerMessage(PacketMana.Handler.class, PacketMana.class, 2, Side.SERVER);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new EventHandlerExtendedProps());
		FMLCommonHandler.instance().bus().register(new EventHandlerExtendedProps());

		proxy.registerRender();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}