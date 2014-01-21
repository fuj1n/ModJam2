package fuj1n.modjam2_src.recipe;

import fuj1n.modjam2_src.item.SecureModItems;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeDuplicateCard implements IRecipe{

	@Override
	public boolean matches(InventoryCrafting inventorycrafting, World world) {
		if(inventorycrafting.getStackInSlot(0) != null && inventorycrafting.getStackInSlot(0).itemID == SecureModItems.securityPass.itemID){
			for(int i = 1; i < inventorycrafting.getSizeInventory(); i++){
				if(inventorycrafting.getStackInSlot(i) != null){
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventorycrafting) {
		return inventorycrafting.getStackInSlot(0).copy();
	}

	@Override
	public int getRecipeSize() {
		return 1;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(SecureModItems.securityPass, 1);
	}

}
