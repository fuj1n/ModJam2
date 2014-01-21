package fuj1n.modjam2_src.item;

import fuj1n.modjam2_src.SecureMod;
import fuj1n.modjam2_src.lib.ConfigManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SecureModItems {

	public static Item securityPass;
	public static Item securityWrench;
	public static Item secureDoor;
	
	public static void addItems(){
		securityPass = new ItemSecurityPass(ConfigManager.instance.getValues().securityPassId).setCreativeTab(SecureMod.secureModCreativeTab).setUnlocalizedName("securityPass");
		securityWrench = new ItemSecurityWrench(ConfigManager.instance.getValues().securityWrenchId).setCreativeTab(SecureMod.secureModCreativeTab).setUnlocalizedName("securityWrench");
		secureDoor = new ItemSecureDoor(ConfigManager.instance.getValues().secureDoorItemId).setCreativeTab(SecureMod.secureModCreativeTab).setUnlocalizedName("secureDoor");
	}
	
}
