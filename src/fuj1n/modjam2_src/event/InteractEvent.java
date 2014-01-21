package fuj1n.modjam2_src.event;

import net.minecraft.block.Block;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import fuj1n.modjam2_src.block.ISecure;
import fuj1n.modjam2_src.tileentity.TileEntitySecurityCore;

public class InteractEvent {

	@ForgeSubscribe
	public void onClick(PlayerInteractEvent event){
		if(event.action == Action.LEFT_CLICK_BLOCK && event.entityPlayer != null){
			Block block = Block.blocksList[event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z)];
			if(block instanceof ISecure){
				if(!((ISecure)block).canBreak(event.entityPlayer.worldObj, event.x, event.y, event.z, event.entityPlayer)){
					event.setCanceled(true);
					if(event.entityPlayer.worldObj.getBlockTileEntity(event.x, event.y, event.z) instanceof TileEntitySecurityCore){
						((TileEntitySecurityCore)event.entityPlayer.worldObj.getBlockTileEntity(event.x, event.y, event.z)).setRetaliate(event.entityPlayer);
					}
				}
			}
		}
	}
	
}
