package fuj1n.modjam2_src.block;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import fuj1n.modjam2_src.lib.ConfigManager;

public class SecureModBlocks {

	public static Block securityCore;
	
	public static void addAllBlocks(){
		securityCore = new BlockSecureCore(ConfigManager.instance.getValues().secureCoreId).setHardness(0.3F).setResistance(5F).setCreativeTab(CreativeTabs.tabAllSearch).setUnlocalizedName("securityCore");
	}
	
	public static void registerAllBlocks(){
		GameRegistry.registerBlock(securityCore, "securityCore");
	}
	
}
