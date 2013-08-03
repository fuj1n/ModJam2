package fuj1n.modjam2_src.item;

import fuj1n.modjam2_src.lib.ConfigManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SecureModItems {

	public static Item securityPass;
	
	public static void addItems(){
		securityPass = new ItemSecurityPass(ConfigManager.instance.getValues().securityPassId).setCreativeTab(CreativeTabs.tabAllSearch).setUnlocalizedName("securityPass");
	}
	
}
