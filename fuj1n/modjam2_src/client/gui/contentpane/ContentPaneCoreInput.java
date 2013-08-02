package fuj1n.modjam2_src.client.gui.contentpane;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import fuj1n.modjam2_src.client.gui.GuiButtonDark;

public class ContentPaneCoreInput extends ContentPane {

	public int mode = 1;
	
	public ContentPaneCoreInput(GuiContainer paneParent) {
		super(paneParent);
	}

	@Override
	public void addButtons(List buttonList) {
		GuiButton currentButton = new GuiButtonDark(1, 165, 68, 40, 20, "Pass");
		addButton(buttonList, 1, 165, 68, "Pass", mode);
		addButton(buttonList, 2, 205, 68, "Card", mode);
		addButton(buttonList, 3, 245, 68, "Retina", mode);
	}

	@Override
	public void drawContentForeground() {
		mc.fontRenderer.drawString("Mode:", 10, 60, 0xAAAAAA);
	}

	@Override
	public void drawContentBackground() {

	}
	
	@Override
	public void actionPerformed(GuiButton par1GuiButton){
		switch(par1GuiButton.id){
		case 1:
			mode = 1;
			break;
		case 2:
			mode = 2;
			break;
		case 3:
			mode = 3;
			break;
		}
	}
	
	public GuiButton addButton(List buttonList, int id, int x, int y, String text, int currentlySelected){
		GuiButton currentButton = new GuiButtonDark(id, x, y, 40, 20, text);
		buttonList.add(currentButton);
		if(currentlySelected == id)
			currentButton.enabled = false;
		return currentButton;
	}
	
}
