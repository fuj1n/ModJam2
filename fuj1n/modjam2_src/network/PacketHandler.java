package fuj1n.modjam2_src.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		EntityPlayer entityPlayer = null;
		World world = null;
		if(player instanceof EntityPlayer){
			entityPlayer = (EntityPlayer)player;
			world = entityPlayer.worldObj;
		}
		if(packet.channel == "fuj1nSecure"){
			DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
			int purposeId = -1;
			try{
				purposeId = inputStream.readInt();
			}catch(IOException e){
				e.printStackTrace();
			}
			switch(purposeId){
			case 0:
				handleSecureCoreInitPacket(inputStream, entityPlayer, world);
				break;
			}
		}
	}
	
	public void handleSecureCoreInitPacket(DataInputStream inputStream, EntityPlayer player, World world){
		try {
			int x = inputStream.readInt();
			int y = inputStream.readInt();
			int z = inputStream.readInt();
			int mode = inputStream.readInt();
			switch(mode){
			case 1:
				String passcode = inputStream.readUTF();
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
