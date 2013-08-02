package fuj1n.modjam2_src.block;

import fuj1n.modjam2_src.SecureMod;
import fuj1n.modjam2_src.client.gui.GuiHandler.GuiIdReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockSecureCore extends Block {

	private Icon[] icons = new Icon[3];

	public BlockSecureCore(int par1) {
		super(par1, Material.tnt);
		this.setStepSound(this.soundMetalFootstep);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		par5EntityPlayer.openGui(SecureMod.instance, GuiIdReference.GUI_SECURECORE, par1World, par2, par3, par4);
		return true;
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
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return par1 == 1 ? this.icons[1] : (par1 == 0 ? this.icons[1] : (par1 != par2 ? this.icons[0] : this.icons[2]));
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons[0] = par1IconRegister.registerIcon("securemod:secure_block_base");
		icons[1] = par1IconRegister.registerIcon("securemod:secure_block_axisY");
		icons[2] = par1IconRegister.registerIcon("securemod:secure_core_front");
	}

}
