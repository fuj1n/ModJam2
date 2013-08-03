package fuj1n.modjam2_src.item;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSecurityPass extends Item {

	public ItemSecurityPass(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
		return false;
	}

	public Item getContainerItem() {
		return this;
	}

	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("securemod:security_pass");
	}
	
	@Override
    public ItemStack getContainerItemStack(ItemStack itemStack){
        return itemStack;
    }

	@Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("cardID")){
			if(GuiScreen.isShiftKeyDown()){
				par3List.add(Integer.toString(par1ItemStack.getTagCompound().getInteger("cardID")));
			}else{
				par3List.add("Hold shift for more information");
			}
			
			if(GuiScreen.isCtrlKeyDown()){
				par3List.add("Dispose in fire");
			}else{
				par3List.add("Hold control for disposal instructions")
			}
		}
	}
	
}
