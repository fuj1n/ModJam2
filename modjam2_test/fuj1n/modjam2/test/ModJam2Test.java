package fuj1n.modjam2.test;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import fuj1n.modjam2.test.block.BlockDynamicColBoxTest;

@Mod(name="ModJam #2 Test - fuj1n", modid="fuj1n.modjam.v2.test", version="UNDEFINED")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class ModJam2Test {

	public static Block dynColTest;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event){
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		dynColTest = new BlockDynamicColBoxTest(2300).setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(dynColTest);
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event){
		
	}
	
	
}
