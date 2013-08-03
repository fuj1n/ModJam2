package fuj1n.modjam2_src.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import fuj1n.modjam2_src.block.ISecure;

public class ItemSecurityWrench extends Item{

	public ItemSecurityWrench(int par1) {
		super(par1);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister){
		this.itemIcon = par1IconRegister.registerIcon("securemod:security_wrench");
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
		if(Block.blocksList[par3World.getBlockId(par4, par5, par6)] != null){
			Block block = Block.blocksList[par3World.getBlockId(par4, par5, par6)];
			if(block instanceof ISecure){
				ISecure secure = (ISecure)block;
				if(secure.canBreak(par3World, par4, par5, par6, par2EntityPlayer)){
					block.dropBlockAsItem(par3World, par4, par5, par6, par3World.getBlockMetadata(par4, par5, par6), 0);
					block.onBlockDestroyedByPlayer(par3World, par4, par5, par6, par3World.getBlockMetadata(par4, par5, par6));
					par3World.setBlockToAir(par4, par5, par6);
					block.breakBlock(par3World, par4, par5, par6, block.blockID, par3World.getBlockMetadata(par4, par5, par6));
					return true;
				}
			}
		}
		return false;
	}

}
