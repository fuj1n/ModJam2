package fuj1n.modjam2_src;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import fuj1n.modjam2_src.block.SecureModBlocks;
import fuj1n.modjam2_src.client.gui.GuiHandler;
import fuj1n.modjam2_src.lib.ConfigManager;

@Mod(modid = "SecureMod", name = "Secure Mod", version = "v0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SecureMod {

	@SidedProxy(clientSide="fuj1n.modjam2_src.client.ClientProxySecureMod", serverSide="fuj1n.modjam2_src.CommonProxySecureMod")
	public static CommonProxySecureMod proxy;
	
	@Instance("SecureMod")
	public static SecureMod instance;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event){
		proxy.PreInit();
		new ConfigManager(event.getSuggestedConfigurationFile()).readConfigValues();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent evnet){
		proxy.Init();
		
		SecureModBlocks.addAllBlocks();
		SecureModBlocks.registerAllBlocks();
		
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event){
		proxy.PostInit();
	}
	
}
