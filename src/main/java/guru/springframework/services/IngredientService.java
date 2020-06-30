package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

public interface IngredientService {
	IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
	
	IngredientCommand saveIngredientCommand(IngredientCommand command);
	
	void deleteByRecipeIdAndIngredientId(String recipeId, String ingredientId);
}
