package fuj1n.modjam2_src.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import fuj1n.modjam2_src.item.SecureModItems;
import fuj1n.modjam2_src.tileentity.TileEntitySecurityCore;

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
			case 1:
				try {
					TileEntitySecurityCore te = (TileEntitySecurityCore)world.getBlockTileEntity(inputStream.readInt(), inputStream.readInt(), inputStream.readInt());
					te.setOutput();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	public void handleSecureCoreInitPacket(DataInputStream inputStream, EntityPlayer player, World world){		
		try {
			int x = inputStream.readInt();
			int y = inputStream.readInt();
			int z = inputStream.readInt();
			TileEntitySecurityCore te = (TileEntitySecurityCore)world.getBlockTileEntity(x, y, z);
			int mode = inputStream.readInt();
			switch(mode){
			case 1:
				String passcode = inputStream.readUTF();
				te.passcode = passcode;
				break;
			case 2:
				int cardID = new Random().nextInt();
				te.passcode = Integer.toString(cardID);
				
				ItemStack item = new ItemStack(SecureModItems.securityPass, 1);
				NBTTagCompound compound = new NBTTagCompound();
				compound.setInteger("cardID", cardID);
				compound.setString("owner", player.username);
				compound.setString("location", "x: " + x + ", y: " + y + ", z: " + z);
				item.setTagCompound(compound);
				
	            float f = 0.7F;
	            double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	            double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	            double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	            EntityItem entityitem = new EntityItem(world, player.posX + d0, player.posY + d1, player.posZ + d2, item);
	            entityitem.delayBeforeCanPickup = 0;
	            world.spawnEntityInWorld(entityitem);
				break;
			case 3:
				break;
			case 4:
				break;
			}
			
			int outputMode = inputStream.readInt();
			switch(outputMode){
			case 1:
				int sideMode = inputStream.readInt();
				int timeMode = inputStream.readInt();
				te.outSide = sideMode;
				te.outTime = timeMode;
				break;
			}
			int retMode = inputStream.readInt();
			switch(retMode){
			case 1:
				break;
			case 2:
				break;
			case 3:
				int sideMode = inputStream.readInt();
				int timeMode = inputStream.readInt();
				te.retSide = sideMode;
				te.retTime = timeMode;
			}
			te.inputMode = mode;
			te.outputMode = outputMode;
			te.retMode = retMode;
			te.playerName = player.username;
			
			PacketDispatcher.sendPacketToAllInDimension(te.getDescriptionPacket(), world.provider.dimensionId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
