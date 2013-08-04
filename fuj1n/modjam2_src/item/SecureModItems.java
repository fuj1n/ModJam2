package fuj1n.modjam2_src.item;

import fuj1n.modjam2_src.lib.ConfigManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SecureModItems {

	public static Item securityPass;
	public static Item securityWrench;
	public static Item secureDoor;
	
	public static void addItems(){
		securityPass = new ItemSecurityPass(ConfigManager.instance.getValues().securityPassId).setCreativeTab(CreativeTabs.tabAllSearch).setUnlocalizedName("securityPass");
		securityWrench = new ItemSecurityWrench(ConfigManager.instance.getValues().securityWrenchId).setCreativeTab(CreativeTabs.tabAllSearch).setUnlocalizedName("securityWrench");
		secureDoor = new ItemSecureDoor(ConfigManager.instance.getValues().secureDoorItemId).setCreativeTab(CreativeTabs.tabAllSearch).setUnlocalizedName("secureDoor");
	}
	
}
