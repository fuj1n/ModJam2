package fuj1n.modjam2_src.client.gui.contentpane;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import fuj1n.modjam2_src.client.gui.GuiButtonDark;
import fuj1n.modjam2_src.client.gui.GuiPassField;

public class ContentPaneCoreInput extends ContentPane {

	public int mode = 1;
	
	public GuiPassField passcode = new GuiPassField(mc.fontRenderer, parent.width / 2 - 150, parent.height / 2 - 41, 100, 10);
	
	public ContentPaneCoreInput(GuiContainer paneParent) {
		super(paneParent);
		passcode.setFocused(true);
		passcode.setMaxStringLength(35);
	}

	@Override
	public void addButtons(List buttonList) {
		GuiButton currentButton = new GuiButtonDark(1, 165, 68, 40, 20, "Pass");
		addButton(buttonList, 1, parent.width / 2 - 78, parent.height / 2 - 50, "Pass", mode);
		addButton(buttonList, 2, parent.width / 2 - 38, parent.height / 2 - 50, "Card", mode);
		addButton(buttonList, 3, parent.width / 2 + 2, parent.height / 2 - 50, "Retina", mode);
		addButton(buttonList, 4, parent.width / 2 + 42, parent.height / 2 - 50, "Red", mode);
	}

	@Override
	public void drawContentForeground() {
		mc.fontRenderer.drawString("Mode:", 75, 31, 0xAAAAAA);
		
		drawContentRelativeToButtonStates();
	}
	
	public void drawContentRelativeToButtonStates(){
		switch(mode){
		case 1:
			mc.fontRenderer.drawString("Passcode:", 10, 80, 0xAAAAAA);
			passcode.drawTextBox();
			break;
		case 2:
			mc.fontRenderer.drawString("A master keycard will be", 25, 80, 0xAAAAAA);
			mc.fontRenderer.drawString("granted to you when this", 25, 80 + mc.fontRenderer.FONT_HEIGHT, 0xAAAAAA);
			mc.fontRenderer.drawString("GUI is closed.", 25, 80 + mc.fontRenderer.FONT_HEIGHT * 2, 0xAAAAAA);
			break;
		case 3:
			mc.fontRenderer.drawString("Only you can activate", 25, 80, 0xAAAAAA);
			mc.fontRenderer.drawString("this device.", 25, 80 + mc.fontRenderer.FONT_HEIGHT, 0xAAAAAA);
			break;
		case 4:
			mc.fontRenderer.drawString("Better red than dead", 25, 80, 0xAAAAAA);
			mc.fontRenderer.drawString("Redstone activated", 25, 80 + mc.fontRenderer.FONT_HEIGHT, 0xAAAAAA);
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
			passcode.setEnabled(true);
			passcode.setFocused(true);
			passcode.setCanLoseFocus(false);
			break;
		case 2:
			mode = 2;
			passcode.setEnabled(false);
			passcode.setFocused(false);
			break;
		case 3:
			mode = 3;
			passcode.setEnabled(false);
			passcode.setFocused(false);
			break;
		case 4:
			mode = 4;
			passcode.setEnabled(false);
			passcode.setFocused(false);
			break;
		}
	}
	
	@Override
	public void keyTyped(char par1, int par2){
		passcode.textboxKeyTyped(par1, par2);
	}
	
	@Override
	public void addDataToPacket(DataOutputStream outputStream) throws IOException{
		outputStream.writeInt(mode);
		switch(mode){
		case 1:
			outputStream.writeUTF(passcode.getText());
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	
}
