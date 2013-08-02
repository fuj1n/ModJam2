package fuj1n.modjam2_src.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSecureCore extends Block{

	public BlockSecureCore(int par1) {
		super(par1, Material.tnt);
		this.setStepSound(this.soundMetalFootstep);
	}

}
