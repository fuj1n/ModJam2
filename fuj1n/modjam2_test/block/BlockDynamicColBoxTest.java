package fuj1n.modjam2_test.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDynamicColBoxTest extends Block{

	public BlockDynamicColBoxTest(int par1) {
		super(par1, Material.craftedSnow);
	}
	
	@Override
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
		if(par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)){
			this.setBlockBounds(0F, 0F, 0F, 1F, 3F, 1F);
		}else{
			this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		}
		
		super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
	}
	
	@Override
    public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}
}
