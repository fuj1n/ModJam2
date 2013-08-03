package fuj1n.modjam2_src.client.gui;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fuj1n.modjam2_src.inventory.ContainerDummy;

public class GuiSecureCorePasscode extends GuiContainer{

	private ResourceLocation background = new ResourceLocation("securemod:textures/gui/security_core_passcode.png");
	
	public GuiTextField passfield;
	
	String pass = "";
	
	public GuiSecureCorePasscode(EntityPlayer player, int x, int y, int z) {
		super(new ContainerDummy());
		ySize = 68;
	}

	@Override
	public void initGui(){
		super.initGui();
		passfield = new GuiTextField(fontRenderer, xSize / 2, ySize / 2, 50, 10);
		passfield.setMaxStringLength(15);
		passfield.setFocused(true);
		passfield.setCanLoseFocus(false);
		passfield.setText(pass);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int f, int i) {
		fontRenderer.drawString("Passcode", 8, 5, 0xAAAAAA);
		passfield.drawTextBox();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.func_110577_a(background);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void keyTyped(char par1, int par2) {
		super.keyTyped(par1, par2);
		
		passfield.textboxKeyTyped(par1, par2);
	}
	
	@Override
	public void updateScreen(){
		super.updateScreen();
		this.pass = passfield.getText();
	}

}
