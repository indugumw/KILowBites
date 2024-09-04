package testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ingredient.Ingredient;
import meal.Meal;
import recipe.Recipe;
import search.MealSearch;
import search.RecipeSearch;

public class MealSearchTest 
{
	@Test
	void itReturnsTrueWhenIngredientIsInMeal() throws IOException {
		// Arrange
	    Meal chickenBeans= Meal.open("res/meal/Chicken&Beans");
	    String userIngredient = "pinto beans";
		
		// Act
	    boolean foundInRecipe = MealSearch.compareIngredients(chickenBeans, userIngredient);
		
		// Assert
	    Assertions.assertTrue(foundInRecipe);
	}
	
	@Test
	void itReturnsFalseWhenIngredientIsNotInMeal() throws IOException {
		// Arrange
		Meal chickenBeans = Meal.open("res/meal/Chicken&Beans");	    
		String userIngredient = "apples";
		
		// Act
	    boolean foundInRecipe = MealSearch.compareIngredients(chickenBeans, userIngredient);
		
		// Assert
	    Assertions.assertFalse(foundInRecipe);
	}
	
	@Test
	public void itFindsOneMatchingMeal() throws IOException {
		// Arrange
		String userIngredient = "pinto beans";
		
		ArrayList<Meal> correctMeals = new ArrayList<Meal>();
		Meal chickenBeans = Meal.open("res/meal/Chicken&Beans");
		correctMeals.add(chickenBeans);
		
		// Act
		ArrayList<Meal> matchingMeals = MealSearch.findMatchingMeals(userIngredient);
		
		// Assert
		Assertions.assertTrue(compareList(correctMeals, matchingMeals));
	}
	
	@Test
	public void itReturnsFalseWhenThereAreNoMatchingMeals() throws IOException {
		// Arrange
		String userIngredient = "broccoli";
		
		ArrayList<Meal> correctMeals = new ArrayList<Meal>();
		
		// Act
		ArrayList<Meal> matchingMeals = MealSearch.findMatchingMeals(userIngredient);
		
		// Assert
		Assertions.assertTrue(compareList(correctMeals, matchingMeals));
	}
	
	@Test
	public void itReturnsTrueWhenThereAreNoMatchingMeals() throws IOException {
		// Arrange
		String userIngredient = "broccoli";
		
		ArrayList<Meal> correctMeals = new ArrayList<Meal>();
		Meal chickenBeans = Meal.open("res/meal/Chicken&Beans");
		correctMeals.add(chickenBeans);
		
		// Act
		ArrayList<Meal> unMatchingMeals = MealSearch.findUnMatchingMeals(userIngredient);
		
		// Assert
		Assertions.assertEquals(1, unMatchingMeals.size());
		Assertions.assertTrue(compareList(correctMeals, unMatchingMeals));
	}
	
	/**
     * Compares two Lists to determine if they are equal. Assumes both lists are non-null.
     * 
     * @author Myan Indugula
     * 
     * @param list1 First list being compared
     * @param list2 Second list being compared
     * 
     * @return True if the lists are equal.
     *
     */
	public boolean compareList(List<Meal> list1, List<Meal> list2) 
	{
		if (list1.size() != list2.size())
		{
			return false;
		}
		
		ArrayList<Ingredient> list1Ingredients = new ArrayList<>();
		ArrayList<Ingredient> list2Ingredients = new ArrayList<>();
		
		for (Meal r1: list1) 
		{
			ArrayList<Ingredient> ingredients1 = r1.getIngredients();
			for (Ingredient i: ingredients1)
			{
				list1Ingredients.add(i);
			}
		}
		
		for (Meal r2: list2) 
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
