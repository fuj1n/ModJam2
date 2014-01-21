package fuj1n.modjam2_src.event;

import fuj1n.modjam2_src.item.SecureModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PriorityManager {

	@ForgeSubscribe
	public void onBlockInteract(PlayerInteractEvent event){
		if(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK){
			EntityPlayer player = event.entityPlayer;
			
			if(player.getHeldItem() != null && player.getHeldItem().itemID == SecureModItems.securityWrench.itemID){
				event.setCanceled(SecureModItems.securityWrench.onItemUse(player.getHeldItem(), player, player.worldObj, event.x, event.y, event.z, event.face, 0F, 0F, 0F));
			}
		}
	}
	
}
