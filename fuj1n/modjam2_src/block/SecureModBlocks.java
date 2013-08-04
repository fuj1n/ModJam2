package fuj1n.modjam2_src.block;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import fuj1n.modjam2_src.SecureMod;
import fuj1n.modjam2_src.item.ItemBlockSecureBlock;
import fuj1n.modjam2_src.lib.ConfigManager;

public class SecureModBlocks {

	public static Block securityCore;
	public static Block secureBlock;
	public static Block secureDoor;
	
	public static void addAllBlocks(){
		securityCore = new BlockSecureCore(ConfigManager.instance.getValues().secureCoreId).setBlockUnbreakable().setCreativeTab(SecureMod.secureModCreativeTab).setUnlocalizedName("securityCore");
		secureBlock = new BlockSecure(ConfigManager.instance.getValues().secureBlockId).setBlockUnbreakable().setCreativeTab(SecureMod.secureModCreativeTab).setUnlocalizedName("secureBlock");
		secureDoor = new BlockSecureDoor(ConfigManager.instance.getValues().secureDoorBlockId).setBlockUnbreakable().setCreativeTab(null).setUnlocalizedName("secureDoor");
	}
	
	public static void registerAllBlocks(){
		GameRegistry.registerBlock(securityCore, "securityCore");
		GameRegistry.registerBlock(secureBlock, ItemBlockSecureBlock.class, "secureBlock");
		GameRegistry.registerBlock(secureDoor, "secureDoor");
	}
	
}
