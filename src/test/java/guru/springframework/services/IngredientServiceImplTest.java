package guru.springframework.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

public class IngredientServiceImplTest {

	IngredientServiceImpl ingredientService;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	@Mock
	RecipeRepository recipeRepository;

	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Mock
	IngredientRepository ingredientRepository;

	public IngredientServiceImplTest() {
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(
				new UnitOfMeasureToUnitOfMeasureCommand());
		this.ingredientCommandToIngredient = new IngredientCommandToIngredient(
				new UnitOfMeasureCommandToUnitOfMeasure());

	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		ingredientService = new IngredientServiceImpl(recipeRepository, unitOfMeasureRepository, ingredientRepository,
				ingredientToIngredientCommand, ingredientCommandToIngredient);
	}

	@Test
	public void testFindByRecipeIdAndIngredientId() {
		// Given
		Recipe recipe = new Recipe();
		recipe.setId("1");

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId("1");

		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId("2");

		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId("3");

		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient3);

		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

		// When
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId("1", "3");

		assertNotNull(ingredientCommand);
		assertEquals(Long.valueOf(3L), ingredientCommand.getId());
		assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
		verify(recipeRepository, times(1)).findById(anyString());
	}

	@Test
	public void testSaveExistingIngredient() {
		// Given
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId("3");
		ingredientCommand.setRecipeId("2");

		Optional<Recipe> recipeOptional = Optional.of(new Recipe());

		Recipe savedRecipe = new Recipe();
		savedRecipe.setId("2");
		savedRecipe.addIngredient(new Ingredient());
		savedRecipe.getIngredients().iterator().next().setId("3");

		when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
		when(recipeRepository.save(any())).thenReturn(savedRecipe);

		// When
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

		// Then
		assertNotNull(savedCommand);
		assertEquals(ingredientCommand.getId(), savedCommand.getId());

		verify(recipeRepository, times(1)).save(any(Recipe.class));
		verify(recipeRepository, times(1)).findById(anyString());

	}

	@Test
	public void deleteIngredient() {
		// Given
		String recipeId = "1";
		String ingredientId = "3";
		
		Recipe recipe = new Recipe();
		recipe.setId(recipeId);
		
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ingredientId);
		
		recipe.addIngredient(ingredient);
		
		when(recipeRepository.findById(anyString())).thenReturn(Optional.of(recipe));

		// When
		ingredientService.deleteByRecipeIdAndIngredientId(recipeId, ingredientId);

		verify(recipeRepository, times(1)).findById(anyString());
		verify(recipeRepository, times(1)).save(any(Recipe.class));
	}

}
