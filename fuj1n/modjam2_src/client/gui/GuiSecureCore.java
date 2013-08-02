package fuj1n.modjam2_src.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fuj1n.modjam2_src.Helper;
import fuj1n.modjam2_src.client.gui.contentpane.ContentPane;
import fuj1n.modjam2_src.client.gui.contentpane.ContentPaneCoreInput;
import fuj1n.modjam2_src.client.gui.contentpane.ContentPaneCoreOutput;
import fuj1n.modjam2_src.inventory.ContainerDummy;

public class GuiSecureCore extends GuiContainer {

	private ResourceLocation background = new ResourceLocation("securemod:textures/gui/security_core.png");

	private EntityPlayer thePlayer;
	private int x, y, z;

	private List buttons = new ArrayList();

	private List<ContentPane> panes = new ArrayList<ContentPane>();
	private int currentPane = 0;

	public String tabName = "Input";
	public int currentTab = 0;

	public GuiSecureCore(EntityPlayer par1EntityPlayer, int par2, int par3, int par4) {
		super(new ContainerDummy());
		this.thePlayer = par1EntityPlayer;
		this.x = par2;
		this.y = par3;
		this.z = par4;
		this.ySize = 210;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int f, int i) {
		fontRenderer.drawString("Security Core", 8, 5, 0xAAAAAA);
//		fontRenderer.drawString(tabName, ySize / 2 - fontRenderer.getStringWidth(tabName), 17, 0xAAAAAA);
		this.drawCenteredString(fontRenderer, tabName, xSize / 2, 17, 0xAAAAAA);
		if (this.currentPane >= 0 && this.currentPane < this.panes.size() && this.panes.get(currentPane) != null) {
			this.panes.get(currentPane).drawContentForeground();
		}
	}

	@Override
	public void initGui() {
		//BUTTON ID 0 RESERVED FOR THE SAVE BUTTON
		super.initGui();
		List l = new ArrayList<String>();
		l.add("Input");
		buttons.add(new GuiTab(0, this.width / 2 - 70, this.height / 2 - 85, "In", Helper.copyList(l), "Input", this));
		l.clear();
		l.add("Output");
		buttons.add(new GuiTab(1, this.width / 2 + 40, this.height / 2 - 85, "Out", Helper.copyList(l), "Output", this));
		
		panes.add(new ContentPaneCoreInput(this));
		panes.add(new ContentPaneCoreOutput(this));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton instanceof GuiTab) {
			GuiTab tab = (GuiTab) par1GuiButton;
			tabName = tab.tabTitle;
			
			switch (par1GuiButton.id){
			default:
				this.currentPane = par1GuiButton.id;
				break;
			}
		} else {
			switch (par1GuiButton.id) {

			}
		}
		
		if (this.currentPane >= 0 && this.currentPane < this.panes.size() && this.panes.get(currentPane) != null){
			this.panes.get(currentPane).actionPerformed(par1GuiButton);
		}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		this.buttonList.clear();
		this.buttonList.addAll(buttons);
		if (this.currentPane >= 0 && this.currentPane < this.panes.size() && this.panes.get(currentPane) != null) {
			this.panes.get(currentPane).addButtons(buttonList);
			this.panes.get(currentPane).updatePane();
		}

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.func_110577_a(background);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		if (this.currentPane >= 0 && this.currentPane < this.panes.size() && this.panes.get(currentPane) != null) {
			this.panes.get(currentPane).drawContentBackground();
		}
	}
}
