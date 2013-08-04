package fuj1n.modjam2_src.block;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import fuj1n.modjam2_src.lib.ConfigManager;

public class SecureModBlocks {

	public static Block securityCore;
	public static Block secureBlock;
	public static Block secureDoor;
	
	public static void addAllBlocks(){
		securityCore = new BlockSecureCore(ConfigManager.instance.getValues().secureCoreId).setBlockUnbreakable().setCreativeTab(CreativeTabs.tabAllSearch).setUnlocalizedName("securityCore");
		secureBlock = new BlockSecure(ConfigManager.instance.getValues().secureBlockId).setBlockUnbreakable().setCreativeTab(CreativeTabs.tabAllSearch).setUnlocalizedName("secureBlock");
//		secureDoor = new BlockSecureDoor(ConfigManager.instance.getValues().secureDoorId).setBlockUnbreakable().setCreativeTab(CreativeTabs.tabAllSearch).setUnlocalizedName("secureDoor");
	}
	
	public static void registerAllBlocks(){
		GameRegistry.registerBlock(securityCore, "securityCore");
		GameRegistry.registerBlock(secureBlock, "secureBlock");
//		GameRegistry.registerBlock(secureDoor, ItemDoor.class, "secureDoor");
	}
	
}
