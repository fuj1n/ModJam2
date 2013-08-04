package fuj1n.modjam2_src.client.gui.contentpane;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.common.ForgeDirection;

public class ContentPaneCoreRetaliate extends ContentPane{

	int mode = 1;
	int sideMode = 0;
	int timeMode = 1;
	ForgeDirection[] sides = ForgeDirection.VALID_DIRECTIONS;
	
	public ContentPaneCoreRetaliate(GuiContainer paneParent) {
		super(paneParent);
	}

	@Override
	public void addButtons(List buttonList){
		addButton(buttonList, 1, parent.width / 2 - 78, parent.height / 2 - 40, "None", mode);
		addButton(buttonList, 2, parent.width / 2 - 17, parent.height / 2 - 40, "Zap", mode);
		addButton(buttonList, 3, parent.width / 2 + 42, parent.height / 2 - 40, "Red", mode);
		if(mode == 3){
			buttonList.add(new GuiButton(4, parent.width / 2 - 45, parent.height / 2 - 7, 10, 10, "-"));
			buttonList.add(new GuiButton(5, parent.width / 2 + 19, parent.height / 2 - 7, 10, 10, "+"));
			buttonList.add(new GuiButton(6, parent.width / 2 - 45, parent.height / 2 + 4, 10, 10, "-"));
			buttonList.add(new GuiButton(7, parent.width / 2 + 19, parent.height / 2 + 4, 10, 10, "+"));
		}
	}
	
	@Override
	public void drawContentForeground(){
		mc.fontRenderer.drawString("Mode:", 75, 55, 0xAAAAAA);
		
		drawContentRelativeToButtonStates();
	}
	
	public void drawContentRelativeToButtonStates(){
		switch(mode){
		case 1:
			mc.fontRenderer.drawString("No retaliation", 25, 90, 0xAAAAAA);
			break;
		case 2:
			mc.fontRenderer.drawString("Zap the player with", 25, 90, 0xAAAAAA);
			mc.fontRenderer.drawString("electricity", 25, 90 + mc.fontRenderer.FONT_HEIGHT, 0xAAAAAA);
			break;
		case 3:
			mc.fontRenderer.drawString("Emit a redstone signal", 25, 90, 0xAAAAAA);
			drawCenteredString(mc.fontRenderer, "Side: " + sides[sideMode].getOpposite().toString(), 80, 100, 0xAAAAAA);
			drawCenteredString(mc.fontRenderer, "Time: " + timeMode + "s", 80, 110, 0xAAAAAA);
			break;
		}
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
		if(mode == 3){
			switch(par1GuiButton.id){
			case 4:
				if(sideMode > 0)
					sideMode--;
				else
					sideMode = sides.length - 1;
				break;
			case 5:
				if(sideMode + 1 < sides.length)				
					sideMode++;
				else
					sideMode = 0;
				break;
			case 6:
				if(timeMode > 1)
					timeMode--;
				else
					timeMode = 100;
				break;
			case 7:
				if(timeMode < 100)
					timeMode++;
				else
					timeMode = 0;
				break;
			}
		}
	}
	
	@Override
	public void drawContentBackground() {}
	
	@Override
	public void addDataToPacket(DataOutputStream outputStream) throws IOException{
		outputStream.writeInt(mode);
		switch(mode){
		case 1:
			break;
		case 2:
			break;
		case 3:
			outputStream.writeInt(sideMode);
			outputStream.writeInt(timeMode);
			break;
		}
	}

}
