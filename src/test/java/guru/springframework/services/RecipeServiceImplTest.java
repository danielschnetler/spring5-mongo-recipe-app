package guru.springframework.services;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

	@Test
	public void getRecipeByIdTest() {
		Recipe recipe = new Recipe();
		recipe.setId("1");

		when(recipeRepository.findById(anyString())).thenReturn(Optional.of(recipe));

		Recipe returnedRecipe = recipeService.findById("1");

		assertNotNull(returnedRecipe);
		verify(recipeRepository, times(1)).findById(anyString());
		verify(recipeRepository, never()).findAll();

	}
	
	@Test(expected = NotFoundException.class)
	public void getRecipeByIdTestNotFount() {
		
		Optional<Recipe> recipeOptional = Optional.empty();
		
		when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
		Recipe recipeReturned =  recipeService.findById("1");

		//Then
		assertNull(recipeReturned);
	}

	@Test
	public void testGetRecipes() {

		Recipe recipe = new Recipe();
		HashSet<Recipe> recipesData = new HashSet<Recipe>();
		recipesData.add(recipe);

		when(recipeRepository.findAll()).thenReturn(recipesData);

		Set<Recipe> recipes = recipeService.getRecipes();

		assertEquals(1, recipes.size());

		verify(recipeRepository, times(1)).findAll();
	}
	
	@Test
	public void testDeleteRecipe() {
		//Given
		String recipeId = "2";
		
		//when
		recipeService.deleteById(recipeId);
		
		verify(recipeRepository, times(1)).deleteById(anyString());
	}

}
