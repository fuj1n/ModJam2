package fuj1n.modjam2_src.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fuj1n.modjam2_src.SecureMod;
import fuj1n.modjam2_src.client.gui.GuiHandler.GuiIdReference;
import fuj1n.modjam2_src.item.SecureModItems;
import fuj1n.modjam2_src.tileentity.TileEntitySecurityCore;

public class BlockSecureCore extends BlockContainer {

	private Icon[] icons = new Icon[3];

	public BlockSecureCore(int par1) {
		super(par1, Material.tnt);
		this.setStepSound(this.soundMetalFootstep);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		TileEntitySecurityCore te = (TileEntitySecurityCore)par1World.getBlockTileEntity(par2, par3, par4);
		
		switch(te.inputMode){
		case 1:
			
			break;
		case 2:
			if(par5EntityPlayer.getHeldItem() != null && par5EntityPlayer.getHeldItem().itemID == SecureModItems.securityPass.itemID && par5EntityPlayer.getHeldItem().getTagCompound() != null){
				if(Integer.toString(par5EntityPlayer.getHeldItem().getTagCompound().getInteger("cardID")).equals(te.passcode)){					
					te.setOutput();
				}
				return true;
			}
			break;
		case 3:
			if(par5EntityPlayer.username.equals(te.playerName)){
				te.setOutput();
				return true;
			}
			break;
		case 4:
			break;
		}
		
		return false;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
		}

		if (l == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
		}

		if (l == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
		}

		if (l == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
		}
		
		if(par5EntityLivingBase instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)par5EntityLivingBase;
			player.openGui(SecureMod.instance, GuiIdReference.GUI_SECURECORE, par1World, par2, par3, par4);
		}
	}

	@Override
	public Icon getBlockTexture(IBlockAccess par1World, int par2, int par3, int par4, int par5) {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		return par5 == 1 ? this.icons[1] : (par5 == 0 ? this.icons[1] : (par5 != meta ? this.icons[0] : this.icons[2]));
	}
	
	@Override
	public Icon getIcon(int par1, int par2){
		if(par1 == 0 || par1 == 1){
			return icons[1];
		}else if(par1 == 4){
			return icons[2];
		}
			
		return icons[0];
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons[0] = par1IconRegister.registerIcon("securemod:secure_block_base");
		icons[1] = par1IconRegister.registerIcon("securemod:secure_block_axisY");
		icons[2] = par1IconRegister.registerIcon("securemod:secure_core_front");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySecurityCore();
	}

}
