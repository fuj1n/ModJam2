package fuj1n.modjam2_src.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import fuj1n.modjam2_src.Helper;
import fuj1n.modjam2_src.client.gui.contentpane.ContentPane;
import fuj1n.modjam2_src.client.gui.contentpane.ContentPaneCoreInput;
import fuj1n.modjam2_src.client.gui.contentpane.ContentPaneCoreOutput;
import fuj1n.modjam2_src.client.gui.contentpane.ContentPaneCoreRetaliate;
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
		this.drawCenteredString(fontRenderer, tabName, xSize / 2 + 50, 5, 0xAAAAAA);
		if (this.currentPane >= 0 && this.currentPane < this.panes.size() && this.panes.get(currentPane) != null) {
			this.panes.get(currentPane).drawContentForeground();
		}
		
		//Note to self: Never render anything after passTooltip as it resets the native translation of the GUI
		for(int index = 0; index < buttons.size(); index++){
			if(buttons.get(index) instanceof GuiTab){
				GuiTab tab = (GuiTab)buttons.get(index);
				tab.passTooltip();
			}
		}
	}

	@Override
	public void initGui() {
		super.initGui();
		buttons.clear();
		buttons.add(new GuiButtonDark(0, this.width / 2 - 50, this.height / 2 + 80, 100, 20, "Done"));
		List l = new ArrayList<String>();
		l.add("Input");
		buttons.add(new GuiTab(0, this.width / 2 - 45, this.height / 2 - 85, "In", Helper.copyList(l), "Input", this));
		l.clear();
		l.add("Output");
		buttons.add(new GuiTab(1, this.width / 2 - 15, this.height / 2 - 85, "Out", Helper.copyList(l), "Output", this));
		l.clear();
		l.add("Retaliation");
		buttons.add(new GuiTab(2, this.width / 2 + 15, this.height / 2 - 85, "Ret", Helper.copyList(l), "Retaliation", this));
		/*
		buttons.add(new GuiTab(0, this.width / 2 - 114, this.height / 2 - 85, "In", Helper.copyList(l), "Input", this));
		l.clear();
		l.add("Output");
		buttons.add(new GuiTab(1, this.width / 2 + 87, this.height / 2 - 85, "Out", Helper.copyList(l), "Output", this));
		l.clear();
		l.add("Retaliation");
		buttons.add(new GuiTab(2, this.width / 2 + 87, this.height / 2 - 55, "Ret", Helper.copyList(l), "Retaliation", this));
		*/
		panes.add(new ContentPaneCoreInput(this));
		panes.add(new ContentPaneCoreOutput(this));
		panes.add(new ContentPaneCoreRetaliate(this));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton instanceof GuiTab) {
			GuiTab tab = (GuiTab) par1GuiButton;
			tabName = tab.tabTitle;

			switch (par1GuiButton.id) {
			default:
				this.currentPane = par1GuiButton.id;
				break;
			}
			
			return;
		} else {
			switch (par1GuiButton.id) {
			case 0:
				dispatchExitPacket();
				
				this.thePlayer.closeScreen();
				return;
			}
		}

		if (this.currentPane >= 0 && this.currentPane < this.panes.size() && this.panes.get(currentPane) != null) {
			this.panes.get(currentPane).actionPerformed(par1GuiButton);
		}
	}

	public void dispatchExitPacket(){
		int packetId = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(packetId);
			outputStream.writeInt(x);
			outputStream.writeInt(y);
			outputStream.writeInt(z);
			panes.get(0).addDataToPacket(outputStream);
			panes.get(1).addDataToPacket(outputStream);
			panes.get(2).addDataToPacket(outputStream);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "fuj1nSecure";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			PacketDispatcher.sendPacketToServer(packet);
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
		mc.getTextureManager().bindTexture(background);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		if (this.currentPane >= 0 && this.currentPane < this.panes.size() && this.panes.get(currentPane) != null) {
			this.panes.get(currentPane).drawContentBackground();
		}
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		if(this.currentPane >= 0 && this.currentPane < this.panes.size() && this.panes.get(currentPane) != null){
			this.panes.get(currentPane).keyTyped(par1, par2);
		}
		
		if (par2 == 1) {
			return;
		}

		if (par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.keyCode) {
			return;
		}
		super.keyTyped(par1, par2);
	}
}
