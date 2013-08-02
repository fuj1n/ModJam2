package fuj1n.modjam2_src.client.gui.contentpane;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;

public abstract class ContentPane extends Gui{

	public GuiContainer parent;
	public Minecraft mc;
	
	public ContentPane(GuiContainer paneParent){
		this.parent = paneParent;
		mc = Minecraft.getMinecraft();
	}
	
	public void addButtons(List buttonList){}
	
	public void drawContentForeground(){}
	public abstract void drawContentBackground();
	public void actionPerformed(GuiButton par1GuiButton){}
	public void updatePane(){}
	
}
