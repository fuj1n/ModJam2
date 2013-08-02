package fuj1n.modjam2_src.client.gui.contentpane;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.common.ForgeDirection;

public class ContentPaneCoreOutput extends ContentPane {

	int mode = 1;
	ForgeDirection[] sides = ForgeDirection.VALID_DIRECTIONS;
	int sideMode = 0;
	int timeMode = 1;
	
	public ContentPaneCoreOutput(GuiContainer paneParent) {
		super(paneParent);
	}

	@Override
	public void addButtons(List buttonList) {
		addButton(buttonList, 1, 192, 68, "Red", mode);
		if(mode == 1){
			buttonList.add(new GuiButton(2, 192, 100, 10, 10, "-"));
		}
	}

	@Override
	public void drawContentForeground() {
		switch(mode){
		case 1:
			mc.fontRenderer.drawString("Emit a redstone signal", 25, 80, 0xAAAAAA);
			drawCenteredString(mc.fontRenderer, "Side: " + sides[sideMode].toString(), 80, 90, 0xAAAAAA);
			drawCenteredString(mc.fontRenderer, "Time: " + timeMode + "s", 80, 100, 0xAAAAAA);
		}
	}

	@Override
	public void drawContentBackground() {

	}

}
