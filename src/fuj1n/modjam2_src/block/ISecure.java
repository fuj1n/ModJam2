package fuj1n.modjam2_src.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface ISecure {
	public boolean canBreak(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer);
}
