package fuj1n.modjam2_src.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import fuj1n.modjam2_src.inventory.ContainerDummy;

public class GuiSecureCore extends GuiContainer{

	private ResourceLocation background = new ResourceLocation("securemod:textures/gui/security_core.png");
	
	private EntityPlayer thePlayer;
	private int x, y, z;
	
	public String tabName = "Output";
	
	public GuiSecureCore(EntityPlayer par1EntityPlayer, int par2, int par3, int par4) {
		super(new ContainerDummy());
		this.thePlayer = par1EntityPlayer;
		this.x = par2;
		this.y = par3;
		this.z = par4;
		this.ySize = 210;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int f, int i){
		fontRenderer.drawString("Security Core", 8, 5, 0xAAAAAA);
		fontRenderer.drawString(tabName, ySize / 2 - fontRenderer.getStringWidth(tabName), 17, 0xAAAAAA);
	}
	
	@Override
	public void initGui() {
		super.initGui();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.func_110577_a(background);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
