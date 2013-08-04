package fuj1n.modjam2_src.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSecureBlock extends ItemBlock{

	public ItemBlockSecureBlock(int par1) {
		super(par1);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int par1){
		return par1;
	}
	
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return "tile.secureBlock." + par1ItemStack.getItemDamage();
    }

}
