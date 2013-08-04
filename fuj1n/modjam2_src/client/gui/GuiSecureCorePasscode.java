package fuj1n.modjam2_src.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import fuj1n.modjam2_src.inventory.ContainerDummy;
import fuj1n.modjam2_src.tileentity.TileEntitySecurityCore;

public class GuiSecureCorePasscode extends GuiContainer{

	private ResourceLocation background = new ResourceLocation("securemod:textures/gui/security_core_passcode.png");
	
	public GuiPassField passfield;
	
	String pass = "";
	
	EntityPlayer thePlayer;
	int x, y, z;
	
	public GuiSecureCorePasscode(EntityPlayer player, int x, int y, int z) {
		super(new ContainerDummy());
		this.thePlayer = player;
		this.x = x;
		this.y = y;
		this.z = z;
		ySize = 68;
	}

	@Override
	public void initGui(){
		super.initGui();
		passfield = new GuiPassField(fontRenderer, width / 2 - 170, height / 2 - 100, 100, 10);
		passfield.setMaxStringLength(35);
		passfield.setFocused(true);
		passfield.setCanLoseFocus(false);
		passfield.setText(pass);
		buttonList.add(new GuiButton(0, width / 2 - 45, height / 2 + 5, 100, 20, "Done"));
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
	
	@Override
	public void actionPerformed(GuiButton par1GuiButton){
		switch(par1GuiButton.id){
		case 0:
			TileEntitySecurityCore te = (TileEntitySecurityCore)thePlayer.worldObj.getBlockTileEntity(x, y, z);
			if(this.passfield.getText().equals(te.passcode)){
				te.setOutput();
				dispatchOpenPacket();
			}else{
				te.setRetaliate(thePlayer);
				dispatchRetaliatePacket();
			}
			thePlayer.closeScreen();
			break;
		}
	}
	
	public void dispatchOpenPacket(){
		int packetId = 1;
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(packetId);
			outputStream.writeInt(x);
			outputStream.writeInt(y);
			outputStream.writeInt(z);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "fuj1nSecure";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			PacketDispatcher.sendPacketToServer(packet);
		}
	}
	
	public void dispatchRetaliatePacket(){
		int packetId = 2;
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(packetId);
			outputStream.writeInt(x);
			outputStream.writeInt(y);
			outputStream.writeInt(z);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "fuj1nSecure";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			PacketDispatcher.sendPacketToServer(packet);
		}
	}

}
