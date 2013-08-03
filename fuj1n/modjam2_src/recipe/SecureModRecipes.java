package fuj1n.modjam2_src.recipe;

import cpw.mods.fml.common.registry.GameRegistry;

public class SecureModRecipes {

	public static void addAllRecipes(){
		GameRegistry.addRecipe(new RecipeDuplicateCard());
	}
	
}
