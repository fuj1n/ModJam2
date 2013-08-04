package fuj1n.modjam2_src.client.gui.contentpane;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.common.ForgeDirection;

public class ContentPaneCoreRetaliate extends ContentPane{

	int mode = 1;
	int sideMode = 0;
	int timeMode = 1;
	ForgeDirection[] sides = ForgeDirection.VALID_DIRECTIONS;
	
	GuiTextField textField = new GuiTextField(mc.fontRenderer, parent.width / 2 - 186, parent.height / 2, 125, 10);
	
	public ContentPaneCoreRetaliate(GuiContainer paneParent) {
		super(paneParent);
		this.textField.setFocused(false);
		this.textField.setEnabled(false);
		this.textField.setMaxStringLength(100);
	}

	@Override
	public void addButtons(List buttonList){
		addButton(buttonList, 1, parent.width / 2 - 78, parent.height / 2 - 40, "None", mode);
		addButton(buttonList, 2, parent.width / 2 - 38, parent.height / 2 - 40, "Zap", mode);
		addButton(buttonList, 3, parent.width / 2 + 2, parent.height / 2 - 40, "Red", mode);
		addButton(buttonList, 4, parent.width / 2 + 42, parent.height / 2 - 40, "Notify", mode);
		if(mode == 3){
			buttonList.add(new GuiButton(5, parent.width / 2 - 45, parent.height / 2 - 7, 10, 10, "-"));
			buttonList.add(new GuiButton(6, parent.width / 2 + 19, parent.height / 2 - 7, 10, 10, "+"));
			buttonList.add(new GuiButton(7, parent.width / 2 - 45, parent.height / 2 + 4, 10, 10, "-"));
			buttonList.add(new GuiButton(8, parent.width / 2 + 19, parent.height / 2 + 4, 10, 10, "+"));
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
		case 4:
			mc.fontRenderer.drawString("Notifies you", 25, 90, 0xAAAAAA);
			mc.fontRenderer.drawString("Tags: #player = username", 25, 90 + mc.fontRenderer.FONT_HEIGHT, 0xAAAAAA);
			mc.fontRenderer.drawString("Message:", 25, 90 + mc.fontRenderer.FONT_HEIGHT * 2, 0xAAAAAA);
			this.textField.drawTextBox();
			break;
		}
	}
	
	@Override
	public void actionPerformed(GuiButton par1GuiButton){
		switch(par1GuiButton.id){
		case 1:
			mode = 1;
			this.textField.setFocused(false);
			this.textField.setEnabled(false);
			this.textField.setCanLoseFocus(false);
			break;
		case 2:
			mode = 2;
			this.textField.setFocused(false);
			this.textField.setEnabled(false);
			break;
		case 3:
			mode = 3;
			this.textField.setFocused(false);
			this.textField.setEnabled(false);
			break;
		case 4:
			mode = 4;
			this.textField.setFocused(true);
			this.textField.setEnabled(true);
			break;
		}
		if(mode == 3){
			switch(par1GuiButton.id){
			case 5:
				if(sideMode > 0)
					sideMode--;
				else
					sideMode = sides.length - 1;
				break;
			case 6:
				if(sideMode + 1 < sides.length)				
					sideMode++;
				else
					sideMode = 0;
				break;
			case 7:
				if(timeMode > 1)
					timeMode--;
				else
					timeMode = 100;
				break;
			case 8:
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
	public void keyTyped(char par1, int par2){
		textField.textboxKeyTyped(par1, par2);
	}
	
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
		case 4:
			outputStream.writeUTF(textField.getText());
			break;
		}
	}

}
