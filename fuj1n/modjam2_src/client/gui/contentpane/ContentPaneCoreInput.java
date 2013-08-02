package fuj1n.modjam2_src.client.gui.contentpane;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;

public class ContentPaneCoreInput extends ContentPane {

	public int mode = 0;
	
	public ContentPaneCoreInput(GuiContainer paneParent) {
		super(paneParent);
	}

	@Override
	public void addButtons(List buttonList) {
		
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
		
		}
	}
	
}
