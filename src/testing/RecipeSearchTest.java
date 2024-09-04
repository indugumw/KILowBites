package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ingredient.Ingredient;
import recipe.Recipe;
import search.RecipeSearch;

class RecipeSearchTest {
	
	@Test
	void itReturnsTrueWhenIngredientIsInRecipe() throws IOException {
		// Arrange
	    Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
	    String userIngredient = "pinto beans";
		//testing
		// Act
	    boolean foundInRecipe = RecipeSearch.compareIngredients(bananasFoster, userIngredient);
		
		// Assert
	    Assertions.assertTrue(foundInRecipe);
	}
	
	@Test
	void itReturnsFalseWhenIngredientIsNotInRecipe() throws IOException {
		// Arrange
		Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");	    
		String userIngredient = "apples";
		
		// Act
	    boolean foundInRecipe = RecipeSearch.compareIngredients(bananasFoster, userIngredient);
		
		// Assert
	    Assertions.assertFalse(foundInRecipe);
	}
	
	@Test
	public void itFindsOneMatchingRecipe() throws IOException {
		// Arrange
		String userIngredient = "pinto beans";
		
		ArrayList<Recipe> correctRecipes = new ArrayList<Recipe>();
		Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
		correctRecipes.add(bananasFoster);
		
		// Act
		ArrayList<Recipe> matchingRecipes = RecipeSearch.findMatchingRecipes(userIngredient);
		
		// Assert
		Assertions.assertTrue(compareList(correctRecipes, matchingRecipes));
	}
	
	//@Test
	public void itFindsMultipleMatchingRecipes() throws IOException {
		// Arrange
		String userIngredient = "butter";
		
		ArrayList<Recipe> correctRecipes = new ArrayList<Recipe>();
		Recipe bananasFoster = Recipe.open("res/recipe/BananasFoster");
		Recipe bananasFoster2 = Recipe.open("res/recipe/MacaroniAndCheese");
		correctRecipes.add(bananasFoster);
		correctRecipes.add(bananasFoster2);
		
		// Act
		ArrayList<Recipe> matchingRecipes = RecipeSearch.findMatchingRecipes(userIngredient);
		
		// Assert
		Assertions.assertTrue(compareList(correctRecipes, matchingRecipes));
	}
	
	@Test
	public void itReturnsFalseWhenThereAreNoMatchingRecipes() throws IOException {
		// Arrange
		String userIngredient = "broccoli";
		
		ArrayList<Recipe> correctRecipes = new ArrayList<Recipe>();
		
		// Act
		ArrayList<Recipe> matchingRecipes = RecipeSearch.findMatchingRecipes(userIngredient);
		
		// Assert
		Assertions.assertTrue(compareList(correctRecipes, matchingRecipes));
	}
	
	@Test
	public void itReturnsTrueWhenThereAreNoMatchingRecipes() throws IOException {
		// Arrange
		String userIngredient = "truffles";
		
		ArrayList<Recipe> correctRecipes = new ArrayList<Recipe>();
		Recipe cheekyBeans = Recipe.open("res/recipe/CheekyBeans");
		correctRecipes.add(cheekyBeans);
		Recipe bananasFoster = Recipe.open("res/recipe/BananaFoster");
		correctRecipes.add(bananasFoster);
		Recipe chicken = Recipe.open("res/recipe/OvenFriedChicken");
		correctRecipes.add(chicken);
		Recipe cheese = Recipe.open("res/recipe/Macaroni&Cheese");
		correctRecipes.add(cheese);
		
		// Act
		ArrayList<Recipe> unMatchingRecipes = RecipeSearch.findUnMatchingRecipes(userIngredient);
		
		// Assert
		Assertions.assertEquals(4, unMatchingRecipes.size());
	}
	
	
	/**
     * Compares the Ingredients of 2 Lists of Recipes to determine if they are equal.
     * Assumes both lists are non-null.
     * 
     * @author Myan Indugula
     * 
     * @param list1 First list being compared
     * @param list2 Second list being compared
     * 
     * @return True if the lists are equal.
     *
     */
	public boolean compareList(List<Recipe> list1, List<Recipe> list2) 
	{
		if (list1.size() != list2.size())
		{
			return false;
		}
		
		ArrayList<Ingredient> list1Ingredients = new ArrayList<>();
		ArrayList<Ingredient> list2Ingredients = new ArrayList<>();
		
		for (Recipe r1: list1) 
		{
			ArrayList<Ingredient> ingredients1 = r1.getIngredients();
			for (Ingredient i: ingredients1)
			{
				list1Ingredients.add(i);
			}
		}
		
		for (Recipe r2: list2) 
		{
			ArrayList<Ingredient> ingredients2 = r2.getIngredients();
			for (Ingredient i: ingredients2)
			{
				list2Ingredients.add(i);
			}
		}
		
		for (int i = 0; i < list1.size(); i++)
		{
			if (list1Ingredients.get(i).getName().equals(list2Ingredients.get(i).getName())
					== false)
			{
				return false;
			}
		}
		return true;
	}
}
