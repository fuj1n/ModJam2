package fuj1n.modjam2_src;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import fuj1n.modjam2_src.block.SecureModBlocks;
import fuj1n.modjam2_src.client.CreativeTabSecurityMod;
import fuj1n.modjam2_src.client.gui.GuiHandler;
import fuj1n.modjam2_src.item.SecureModItems;
import fuj1n.modjam2_src.lib.ConfigManager;
import fuj1n.modjam2_src.network.PacketHandler;
import fuj1n.modjam2_src.recipe.SecureModRecipes;
import fuj1n.modjam2_src.tileentity.TileEntitySecureBlock;
import fuj1n.modjam2_src.tileentity.TileEntitySecurityCore;

@Mod(modid = "SecureMod", name = "Secure Mod", version = "v0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"fuj1nSecure"}, packetHandler=PacketHandler.class)
public class SecureMod {

	@SidedProxy(clientSide="fuj1n.modjam2_src.client.ClientProxySecureMod", serverSide="fuj1n.modjam2_src.CommonProxySecureMod")
	public static CommonProxySecureMod proxy;
	
	@Instance("SecureMod")
	public static SecureMod instance;
	
	public static CreativeTabs secureModCreativeTab;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event){
		proxy.PreInit();
		new ConfigManager(event.getSuggestedConfigurationFile()).readConfigValues();
		
		if(event.getSide() == Side.CLIENT){
			registerCreativeTab();
		}
	}
	
	public void registerCreativeTab() {
		secureModCreativeTab = new CreativeTabSecurityMod("fuj1n.secureMod");
		LanguageRegistry.instance().addStringLocalization("itemGroup." + secureModCreativeTab.getTabLabel(), "Secure Mod");
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		proxy.Init();
		
		SecureModBlocks.addAllBlocks();
		SecureModBlocks.registerAllBlocks();
		
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		
//		Creates buggy behavior with block breaking
//		MinecraftForge.EVENT_BUS.register(new PriorityManager());
		
		SecureModItems.addItems();
		
		SecureModRecipes.addAllRecipes();
		
		registerAllTileEntities();
	}
	
	public void registerAllTileEntities(){
		GameRegistry.registerTileEntity(TileEntitySecurityCore.class, "fuj1n.securemod.tileenitysecuritycore");
		GameRegistry.registerTileEntity(TileEntitySecureBlock.class, "fuj1n.securemod.tileenitysecureblock");
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event){
		proxy.PostInit();
	}
	
}
