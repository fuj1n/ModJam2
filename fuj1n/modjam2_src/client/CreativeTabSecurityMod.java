package fuj1n.modjam2_src.client;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fuj1n.modjam2_src.block.SecureModBlocks;

public class CreativeTabSecurityMod extends CreativeTabs {

	public CreativeTabSecurityMod(String label) {
		super(label);
	}

	public CreativeTabSecurityMod(int par1, String par2Str) {
		super(par1, par2Str);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * the itemID for the item to be displayed on the tab
	 */
	public int getTabIconItemIndex() {
		return SecureModBlocks.securityCore.blockID;
	}

}
