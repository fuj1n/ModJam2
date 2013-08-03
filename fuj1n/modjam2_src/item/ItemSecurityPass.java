package fuj1n.modjam2_src.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemSecurityPass extends Item{

	public ItemSecurityPass(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister){
		this.itemIcon = par1IconRegister.registerIcon("securemod:security_pass");
	}

}
