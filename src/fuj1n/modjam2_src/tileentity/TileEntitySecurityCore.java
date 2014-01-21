package fuj1n.modjam2_src.tileentity;

import java.util.Iterator;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import fuj1n.modjam2_src.block.SecureModBlocks;
import fuj1n.modjam2_src.client.particle.EntityElectricityFX;
import fuj1n.modjam2_src.damage.DamageSourceElectricity;

public class TileEntitySecurityCore extends TileEntity {

	public String playerName = null;
	public String passcode = null;
	public int inputMode = 0;
	public int outputMode = 0;
	public int outSide = 0;
	public int outTime = 0;
	public int retMode = 0;
	public int retSide = 0;
	public int retTime = 0;
	public String retMessage = "";
	
	public int localTimeOut = 0;
	public int localTimeRet = 0;
	
	public EntityPlayer attackingPlayer = null;
	int timesAttackedPlayer = 0;
	int ticksAttackedPlayer = 0;
	
	public int[] redstoneSignalsOut = new int[6];
	public int[] redstoneSignalsRet = new int[6];
	
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		playerName = par1NBTTagCompound.getString("playerName");
		passcode = par1NBTTagCompound.getString("passcode");
		inputMode = par1NBTTagCompound.getInteger("inMode");
		outputMode = par1NBTTagCompound.getInteger("outMode");
		outSide = par1NBTTagCompound.getInteger("outSide");
		outTime = par1NBTTagCompound.getInteger("outTime");
		localTimeOut = par1NBTTagCompound.getInteger("localTime");
		retMode = par1NBTTagCompound.getInteger("retMode");
		retSide = par1NBTTagCompound.getInteger("retSide");
		retTime = par1NBTTagCompound.getInteger("retTime");
		localTimeRet = par1NBTTagCompound.getInteger("localTimeRet");
		for(int i = 0; i < redstoneSignalsOut.length; i++){
			redstoneSignalsOut[i] = par1NBTTagCompound.getInteger("redstoneOut" + i);
		}
		for(int i = 0; i < redstoneSignalsRet.length; i++){
			redstoneSignalsRet[i] = par1NBTTagCompound.getInteger("redstoneRet" + i);
		}
		retMessage = par1NBTTagCompound.getString("retMessage");
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
		par1NBTTagCompound.setInteger("localTime", localTimeOut);
		par1NBTTagCompound.setInteger("retMode", retMode);
		par1NBTTagCompound.setInteger("retSide", retSide);
		par1NBTTagCompound.setInteger("retTime", retTime);
		par1NBTTagCompound.setInteger("localTimeRet", localTimeRet);
		for(int i = 0; i < redstoneSignalsOut.length; i++){
			par1NBTTagCompound.setInteger("redstoneOut" + i, redstoneSignalsOut[i]);
		}
		for(int i = 0; i < redstoneSignalsRet.length; i++){
			par1NBTTagCompound.setInteger("redstoneRet" + i, redstoneSignalsRet[i]);
		}
		par1NBTTagCompound.setString("retMessage", retMessage);
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
		readFromNBT(pkt.data);
		updateSurroundingBlocks();
	}
	
	public void setOutput(){
		switch(outputMode){
		case 1:
			if(outSide < redstoneSignalsOut.length){
				redstoneSignalsOut[outSide] = 15;
				localTimeOut = outTime * (20 / 2);
				updateSurroundingBlocks();
			}
			break;
		}
	}
	
	public void setRetaliate(EntityPlayer par1EntityPlayer){
		switch(retMode){
		case 1:
			break;
		case 2:
			attackingPlayer = par1EntityPlayer;
			timesAttackedPlayer = 0;
			ticksAttackedPlayer = 5;
			break;
		case 3:
			if(retSide < redstoneSignalsRet.length){
				redstoneSignalsRet[retSide] = 15;
				localTimeRet = retTime * (20 / 2);
			}
			updateSurroundingBlocks();
			break;
		case 4:
			Iterator<EntityPlayer> i1 = worldObj.playerEntities.iterator();
			while(i1.hasNext()){
				EntityPlayer pl = i1.next();
				if(pl.username.equals(playerName)){
					if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
						pl.addChatMessage(retMessage != null && !retMessage.equals("") ? retMessage.replaceAll("#player", par1EntityPlayer.username) : par1EntityPlayer.username + " is messing with your security systems");
					}
				}
			}
			break;
		}
	}
	
	@Override
	public void updateEntity(){
		doParticles();
		//System.out.println(redstoneSignals[outSide]);
		if(localTimeOut > 0){
			localTimeOut--;
			if(localTimeOut == 0){
				switch(outputMode){
				case 1:
					if(outSide < redstoneSignalsOut.length){
						redstoneSignalsOut[outSide] = 0;
					}
				}
				updateSurroundingBlocks();
			}
		}
		
		if(localTimeRet > 0){
			localTimeRet--;
			if(localTimeRet == 0){
				switch(retMode){
				case 3:
					if(retSide < redstoneSignalsRet.length){
						redstoneSignalsRet[retSide] = 0;
					}
				}
				updateSurroundingBlocks();
			}
		}
		
		if(attackingPlayer != null && attackingPlayer.isDead){
			ticksAttackedPlayer = 0;
			timesAttackedPlayer = 0;
			attackingPlayer = null;
		}
		
		if(attackingPlayer != null){
			if(ticksAttackedPlayer >= 5){
				ticksAttackedPlayer = 0;
				if(timesAttackedPlayer < 10){
					timesAttackedPlayer++;
					attackingPlayer.attackEntityFrom(new DamageSourceElectricity(), 2F);
				}else{
					ticksAttackedPlayer = 0;
					timesAttackedPlayer = 0;
					attackingPlayer = null;
				}
			}else{
				ticksAttackedPlayer++;
			}
		}
	}
	
	public void doParticles(){
		net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getMinecraft();
		if(this.attackingPlayer != null){
			mc.effectRenderer.addEffect(new EntityElectricityFX(worldObj, this.attackingPlayer.posX + 0.5D, this.attackingPlayer.posY + 0.5D, this.attackingPlayer.posZ + 0.5D, xCoord - this.attackingPlayer.posX, yCoord - this.attackingPlayer.posY, zCoord - this.attackingPlayer.posZ));
			mc.effectRenderer.addEffect(new EntityElectricityFX(worldObj, this.attackingPlayer.posX + 0.5D, this.attackingPlayer.posY + 0.5D, this.attackingPlayer.posZ + 0.5D, xCoord - this.attackingPlayer.posX, yCoord - this.attackingPlayer.posY, zCoord - this.attackingPlayer.posZ));
			mc.effectRenderer.addEffect(new EntityElectricityFX(worldObj, this.attackingPlayer.posX + 0.5D, this.attackingPlayer.posY + 0.5D, this.attackingPlayer.posZ + 0.5D, xCoord - this.attackingPlayer.posX, yCoord - this.attackingPlayer.posY, zCoord - this.attackingPlayer.posZ));
			mc.effectRenderer.addEffect(new EntityElectricityFX(worldObj, this.attackingPlayer.posX + 0.5D, this.attackingPlayer.posY + 0.5D, this.attackingPlayer.posZ + 0.5D, xCoord - this.attackingPlayer.posX, yCoord - this.attackingPlayer.posY, zCoord - this.attackingPlayer.posZ));
			mc.effectRenderer.addEffect(new EntityElectricityFX(worldObj, this.attackingPlayer.posX + 0.5D, this.attackingPlayer.posY + 0.5D, this.attackingPlayer.posZ + 0.5D, xCoord - this.attackingPlayer.posX, yCoord - this.attackingPlayer.posY, zCoord - this.attackingPlayer.posZ));
			mc.effectRenderer.addEffect(new EntityElectricityFX(worldObj, this.attackingPlayer.posX + 0.5D, this.attackingPlayer.posY + 0.5D, this.attackingPlayer.posZ + 0.5D, xCoord - this.attackingPlayer.posX, yCoord - this.attackingPlayer.posY, zCoord - this.attackingPlayer.posZ));
			mc.effectRenderer.addEffect(new EntityElectricityFX(worldObj, this.attackingPlayer.posX + 0.5D, this.attackingPlayer.posY + 0.5D, this.attackingPlayer.posZ + 0.5D, xCoord - this.attackingPlayer.posX, yCoord - this.attackingPlayer.posY, zCoord - this.attackingPlayer.posZ));
			mc.effectRenderer.addEffect(new EntityElectricityFX(worldObj, this.attackingPlayer.posX + 0.5D, this.attackingPlayer.posY + 0.5D, this.attackingPlayer.posZ + 0.5D, xCoord - this.attackingPlayer.posX, yCoord - this.attackingPlayer.posY, zCoord - this.attackingPlayer.posZ));
		}
	}
	
	public void updateSurroundingBlocks(){
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, SecureModBlocks.securityCore.blockID);
		worldObj.notifyBlocksOfNeighborChange(xCoord - 1, yCoord, zCoord, SecureModBlocks.securityCore.blockID);
		worldObj.notifyBlocksOfNeighborChange(xCoord + 1, yCoord, zCoord, SecureModBlocks.securityCore.blockID);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, SecureModBlocks.securityCore.blockID);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord + 1, zCoord, SecureModBlocks.securityCore.blockID);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord - 1, SecureModBlocks.securityCore.blockID);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord + 1, SecureModBlocks.securityCore.blockID);
	}
	
}
