package fuj1n.modjam2_src.tileentity;

import fuj1n.modjam2_src.block.SecureModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySecurityCore extends TileEntity {

	public String playerName = null;
	public String passcode = null;
	public int inputMode = 0;
	public int outputMode = 0;
	public int outSide = 0;
	public int outTime = 0;
	
	public int localTime = 0;
	
	public int[] redstoneSignals = new int[6];
	
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		playerName = par1NBTTagCompound.getString("playerName");
		passcode = par1NBTTagCompound.getString("passcode");
		inputMode = par1NBTTagCompound.getInteger("inMode");
		outputMode = par1NBTTagCompound.getInteger("outMode");
		outSide = par1NBTTagCompound.getInteger("outSide");
		outTime = par1NBTTagCompound.getInteger("outTime");
		localTime = par1NBTTagCompound.getInteger("localTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		if(playerName != null){
			par1NBTTagCompound.setString("playerName", playerName);
		}
		if(passcode != null){
			par1NBTTagCompound.setString("passcode", passcode);
		}
		par1NBTTagCompound.setInteger("inMode", inputMode);
		par1NBTTagCompound.setInteger("outMode", outputMode);
		par1NBTTagCompound.setInteger("outSide", outSide);
		par1NBTTagCompound.setInteger("outTime", outTime);
		par1NBTTagCompound.setInteger("localTime", localTime);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		Packet132TileEntityData packet = new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, nbt);
		return packet;
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		readFromNBT(pkt.customParam1);
	}
	
	public void setOutput(){
		switch(outputMode){
		case 1:
			if(outSide < redstoneSignals.length){
				redstoneSignals[outSide] = 15;
				localTime = outTime * (20 / 2);
			}
			break;
		}
		updateSurroundingBlocks();
	}
	
	@Override
	public void updateEntity(){
		//System.out.println(redstoneSignals[outSide]);
		if(localTime > 0){
			localTime--;
			if(localTime == 0){
				switch(outputMode){
				case 1:
					if(outSide < redstoneSignals.length){
						redstoneSignals[outSide] = 0;
					}
				}
				updateSurroundingBlocks();
			}
		}
	}
	
	public void updateSurroundingBlocks(){
//		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
//		worldObj.markBlockForUpdate(xCoord + 1, yCoord, zCoord);
//		worldObj.markBlockForUpdate(xCoord - 1, yCoord, zCoord);
//		worldObj.markBlockForUpdate(xCoord, yCoord + 1, zCoord);
//		worldObj.markBlockForUpdate(xCoord, yCoord - 1, zCoord);
//		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord + 1);
//		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord - 1);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, SecureModBlocks.securityCore.blockID);
	}
	
}
