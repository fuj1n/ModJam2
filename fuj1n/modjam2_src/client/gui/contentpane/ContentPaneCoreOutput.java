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
		addButton(buttonList, 1, parent.width / 2 - 23, parent.height / 2 - 50, "Red", mode);
		if(mode == 1){
			buttonList.add(new GuiButton(2, parent.width / 2 - 45, parent.height / 2 - 17, 10, 10, "-"));
			buttonList.add(new GuiButton(3, parent.width / 2 + 19, parent.height / 2 - 17, 10, 10, "+"));
			buttonList.add(new GuiButton(4, parent.width / 2 - 45, parent.height / 2 - 6, 10, 10, "-"));
			buttonList.add(new GuiButton(5, parent.width / 2 + 19, parent.height / 2 - 6, 10, 10, "+"));
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
	
	@Override
	public void actionPerformed(GuiButton par1GuiButton){
		switch(par1GuiButton.id){
		case 1:
			mode = 1;
			break;
		case 2:
			if(sideMode > 0)
				sideMode--;
			else
				sideMode = sides.length - 1;
			break;
		case 3:
			if(sideMode + 1 < sides.length)				
				sideMode++;
			else
				sideMode = 0;
			break;
		case 4:
			if(timeMode > 1)
				timeMode--;
			else
				timeMode = 100;
			break;
		case 5:
			if(timeMode < 100)
				timeMode++;
			else
				timeMode = 0;
			break;
		}
	}

}
