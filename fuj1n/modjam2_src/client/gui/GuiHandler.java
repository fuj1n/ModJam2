package fuj1n.modjam2_src.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case GuiIdReference.GUI_SECURECORE:
			return null;
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case GuiIdReference.GUI_SECURECORE:
			return new GuiSecureCore(player, x, y, z);
		}
		return null;
	}
	
	public static class GuiIdReference{
		public static final int GUI_SECURECORE  = 0;
	}

}
