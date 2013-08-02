package fuj1n.modjam2_src.client.gui.contentpane;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import fuj1n.modjam2_src.client.gui.GuiButtonDark;

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
	public void keyTyped(char par1, int par2){}
	public void addDataToPacket(DataOutputStream outputStream) throws IOException{}
	
	public GuiButton addButton(List buttonList, int id, int x, int y, String text, int currentlySelected){
		GuiButton currentButton = new GuiButtonDark(id, x, y, 40, 20, text);
		buttonList.add(currentButton);
		if(currentlySelected == id)
			currentButton.enabled = false;
		return currentButton;
	}
	
}
