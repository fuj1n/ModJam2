package fuj1n.modjam2_src.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
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
				handleSecureCoreInitPacket(inputStream, player);
				break;
			}
		}
	}
	
	public void handleSecureCoreInitPacket(DataInputStream inputStream, Player player){
		
	}

}
