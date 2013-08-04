package fuj1n.modjam2_src.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import fuj1n.modjam2_src.block.SecureModBlocks;
import fuj1n.modjam2_src.item.SecureModItems;

public class SecureModRecipes {

	public static void addAllRecipes(){
		GameRegistry.addRecipe(new RecipeDuplicateCard());
		addRegularRecipes();
	}
	
	private static void addRegularRecipes(){
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SecureModBlocks.secureBlock, 4, 0), new Object[]{
			"ibi", "bsb", "ibi", 'i', Item.ingotIron, 'b', "dyeBlack", 's', Block.stone
		}));
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(SecureModBlocks.secureBlock, 1, 1), new Object[]{
			new ItemStack(SecureModBlocks.secureBlock, 1, 0), Item.stick
		});
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SecureModBlocks.securityCore, 1), new Object[]{
			"grg", "lsl", "grg", 'g', Item.ingotGold, 'r', Item.redstone, 'l', "dyeLime", 's', SecureModBlocks.secureBlock
		}));
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SecureModItems.securityWrench, 1), new Object[]{
			"i i", "sss", " b ", 'i', Item.ingotIron, 's', SecureModBlocks.secureBlock, 'b', "dyeBlack"
		}));
	}
	
}
