package fuj1n.modjam2.test;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(name="ModJam #2 Test - fuj1n", modid="fuj1n.modjam.v2.test", version="UNDEFINED")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class ModJam2Test {

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event){
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event){
		
	}
	
	
}
