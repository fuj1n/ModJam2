package fuj1n.modjam2_src.client.gui.contentpane;

import java.util.List;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;

public abstract class ContentPane extends Gui{

	public GuiContainer parent;
	public ContentPane(GuiContainer paneParent){
		this.parent = paneParent;
	}
	
	public void addButtons(List buttonList){}
	
	public void drawContentForeground(){}
	public abstract void drawContentBackground();
	
}
