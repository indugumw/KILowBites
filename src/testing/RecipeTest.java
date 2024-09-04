package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import recipe.Recipe;
import recipe.RecipeStep;
import recipe.Utensil;
import search.RecipeSearch;
import utility.SysUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ingredient.DefaultIngredients;
import ingredient.Ingredient;
import meal.Meal;

class RecipeTest {
	
	@Test
	void itCreatesRecipeOneParam() {
		// Arrange
		Recipe r = new Recipe("Monkey Bread");
		
		// Act
		
		// Assert
		Assertions.assertEquals("Monkey Bread", r.getName());
		Assertions.assertNotNull(r.getIngredients());
		Assertions.assertNotNull(r.getUtensils());
		Assertions.assertNotNull(r.getSteps());
	}
	
	@Test
	void itCreatesRecipeNoName() {
		// Arrange
		ArrayList<Ingredient> ingredientList = new ArrayList<>();
		ArrayList<Utensil> utensilList = new ArrayList<>();
		ArrayList<RecipeStep> stepList = new ArrayList<>();
		
		// Act
		Recipe r = new Recipe(ingredientList, utensilList, stepList);
		
		// Assert
		Assertions.assertNotNull(r.getIngredients());
		Assertions.assertNotNull(r.getUtensils());
		Assertions.assertNotNull(r.getSteps());
	}
	
	@Test
	void itRemovesIngredient() {
		// Arrange
		ArrayList<Ingredient> ingredientList = new ArrayList<>();
		ArrayList<Ingredient> ingredientList2 = new ArrayList<>();
		Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i2 = new Ingredient("Beef", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i3 = new Ingredient("Chicken", "Chopped", 20.0, "gram", 100, 0.77, null);
		ingredientList.add(i);
		ingredientList.add(i2);
		ingredientList.add(i3);
		
		ingredientList2.add(i);
		ingredientList2.add(i3);
		
		ArrayList<Utensil> utensilList = new ArrayList<>();
		ArrayList<RecipeStep> stepList = new ArrayList<>();

		Recipe r = new Recipe(ingredientList, utensilList, stepList);
		Recipe correct = new Recipe(ingredientList, utensilList, stepList);
		
		// Act
		r.removeIngredient("Beef");
		
		// Assert
		Assertions.assertEquals(2, r.getIngredients().size());
		Assertions.assertTrue(RecipeTest.compareIngredients(r, correct));
	}
	
	@Test
	void itRemovesUtensil() {
		// Arrange
		ArrayList<Utensil> utensilList = new ArrayList<>();
		Utensil u = new Utensil("fork", "metal");
		Utensil u2 = new Utensil("spoon", "plastic");
		Utensil u3 = new Utensil("chopsticks", "wooden");
		utensilList.add(u);
		utensilList.add(u2);
		utensilList.add(u3);
		
		ArrayList<Ingredient> ingredientList = new ArrayList<>();
		ArrayList<RecipeStep> stepList = new ArrayList<>();

		Recipe r = new Recipe(ingredientList, utensilList, stepList);
		
		// Act
		r.removeUtensil("fork");
		
		// Assert
		Assertions.assertEquals(2, r.getUtensils().size());
	}
	
	@Test
	void itRemovesStep() {
		// Arrange
		ArrayList<RecipeStep> stepList = new ArrayList<>();
		ArrayList<Ingredient> saucepanIngredients = new ArrayList<>();
		Utensil saucepan = new Utensil("saucepan", "large", saucepanIngredients);
		RecipeStep u = new RecipeStep("heat", "rum", saucepan, "in", "until it almost simmers");
		RecipeStep u2 = new RecipeStep("spoon", "plastic", saucepan, "in", "until melted");
		RecipeStep u3 = new RecipeStep("chopsticks", "wooden", saucepan, "in", "until golden brown");
		stepList.add(u);
		stepList.add(u2);
		stepList.add(u3);
		
		ArrayList<Ingredient> ingredientList = new ArrayList<>();
		ArrayList<Utensil> utensilList = new ArrayList<>();
		utensilList.add(saucepan);

		Recipe r = new Recipe(ingredientList, utensilList, stepList);
		
		// Act
		String step = u.toString();
		r.removeRecipeStep(step);
		
		// Assert
		Assertions.assertEquals(2, r.getSteps().size());
	}
	
	@Test
	void testAdders()
	{
		ArrayList<Ingredient> ingredientList = new ArrayList<>();
		ArrayList<Utensil> utensilList = new ArrayList<>();
		ArrayList<RecipeStep> stepList = new ArrayList<>();
		
		Recipe test = new Recipe("Toast", ingredientList, utensilList, stepList, 1);
		
		assertEquals(test.getIngredients(), ingredientList);
		assertEquals(test.getUtensils(), utensilList);
		assertEquals(test.getSteps(), stepList);
		assertEquals(test.getServings(), 1);
		
		HashMap<String, Ingredient> ingredientMap = DefaultIngredients.getIngredientsMap();
		
		test.addIngredient(ingredientMap.get("Butter"));
		assertEquals(test.getIngredients().get(0), ingredientMap.get("Butter"));
		
		Utensil butterknife = new Utensil("Butterknife", "Metal");
		test.addUtensil(butterknife);
		assertEquals(test.getUtensils().get(0), butterknife);
		
		RecipeStep step = new RecipeStep("Spread Butter", "Bread", butterknife, null, null);
		test.addRecipeStep(step);
		assertEquals(test.getSteps().get(0), step);
	}
	
	@Test
	void itReturnsTrueWhenRecipeHasIngredient() {
		ArrayList<Ingredient> ingredientList = new ArrayList<>();
		Ingredient i = new Ingredient("red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i2 = new Ingredient("beef", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i3 = new Ingredient("chicken", "Chopped", 20.0, "gram", 100, 0.77, null);
		ingredientList.add(i);
		ingredientList.add(i2);
		ingredientList.add(i3);
		
		ArrayList<Utensil> utensilList = new ArrayList<>();
		ArrayList<RecipeStep> stepList = new ArrayList<>();

		Recipe r = new Recipe(ingredientList, utensilList, stepList);
		
		// Act
		
		// Assert
		Assertions.assertTrue(r.hasIngredient("Beef"));
	}
	
	@Test
	void itReturnsFalseWhenRecipeDoesNotHaveIngredient() {
		ArrayList<Ingredient> ingredientList = new ArrayList<>();
		Ingredient i = new Ingredient("red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i2 = new Ingredient("beef", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i3 = new Ingredient("chicken", "Chopped", 20.0, "gram", 100, 0.77, null);
		ingredientList.add(i);
		ingredientList.add(i2);
		ingredientList.add(i3);
		
		ArrayList<Utensil> utensilList = new ArrayList<>();
		ArrayList<RecipeStep> stepList = new ArrayList<>();

		Recipe r = new Recipe(ingredientList, utensilList, stepList);
		
		// Act
		
		// Assert
		Assertions.assertFalse(r.hasIngredient("Goat"));
	}
	
	@Test
	void itReturnsIngredientFromString() {
		ArrayList<Ingredient> ingredientList = new ArrayList<>();
		Ingredient i = new Ingredient("red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i2 = new Ingredient("beef", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i3 = new Ingredient("chicken", "Chopped", 20.0, "gram", 100, 0.77, null);
		ingredientList.add(i);
		ingredientList.add(i2);
		ingredientList.add(i3);
		
		ArrayList<Utensil> utensilList = new ArrayList<>();
		ArrayList<RecipeStep> stepList = new ArrayList<>();

		Recipe r = new Recipe(ingredientList, utensilList, stepList);
		
		// Act
		Ingredient found = Recipe.ingredientFromString("beef", r);
		
		// Assert
		Assertions.assertEquals(i2, found);
	}
	
	@Test
	void itReturnsFalseWhenNoIngredientFromString() {
		ArrayList<Ingredient> ingredientList = new ArrayList<>();
		Ingredient i = new Ingredient("red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i2 = new Ingredient("beef", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i3 = new Ingredient("chicken", "Chopped", 20.0, "gram", 100, 0.77, null);
		ingredientList.add(i);
		ingredientList.add(i2);
		ingredientList.add(i3);
		
		ArrayList<Utensil> utensilList = new ArrayList<>();
		ArrayList<RecipeStep> stepList = new ArrayList<>();

		Recipe r = new Recipe(ingredientList, utensilList, stepList);
		
		// Act
		Ingredient found = Recipe.ingredientFromString("pork", r);
		
		// Assert
		Assertions.assertNull(found);
	}
	
	@Test
	void itReturnsUtensilFromString() {
		ArrayList<Utensil> utensilList = new ArrayList<>();;
		Utensil u = new Utensil("fork", "metal");
		Utensil u2 = new Utensil("spoon", "plastic");
		Utensil u3 = new Utensil("chopsticks", "wooden");
		utensilList.add(u);
		utensilList.add(u2);
		utensilList.add(u3);
		
		ArrayList<Ingredient> ingredientList = new ArrayList<>();
		ArrayList<RecipeStep> stepList = new ArrayList<>();

		Recipe r = new Recipe(ingredientList, utensilList, stepList);
		
		// Act
		Utensil found = r.utensilFromString("fork");
		
		// Assert
		Assertions.assertEquals(u, found);
	}
	
	@Test
	void itReturnsFalseWhenNoUtensilFromString() {
		ArrayList<Utensil> utensilList = new ArrayList<>();;
		Utensil u = new Utensil("fork", "metal");
		Utensil u2 = new Utensil("spoon", "plastic");
		Utensil u3 = new Utensil("chopsticks", "wooden");
		utensilList.add(u);
		utensilList.add(u2);
		utensilList.add(u3);
		
		ArrayList<Ingredient> ingredientList = new ArrayList<>();
		ArrayList<RecipeStep> stepList = new ArrayList<>();

		Recipe r = new Recipe(ingredientList, utensilList, stepList);
		
		// Act
		Utensil found = r.utensilFromString("spork");
		
		// Assert
		Assertions.assertNull(found);
	}
	
	@Test
	void itReturnsTrueWhenIngredientIsInRecipe() throws IOException 
	{
			// Arrange
		    Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
		    String userIngredient = "pinto beans";
			
			// Act
		    boolean foundInRecipe = RecipeSearch.compareIngredients(bananasFoster, userIngredient);
			
			// Assert
		    Assertions.assertTrue(foundInRecipe);
		}
	
	@Test
	void itChecksName() throws IOException 
	{
			// Arrange
		    Recipe recipe = Recipe.open("res/recipe/CheekyBeans");
			
			// Act
			
			// Assert
		    Assertions.assertNotNull(recipe.getName());
	}
	
	@Test
    void itWritesRecipe() throws IOException {
		// Arrange
        Recipe actualRecipe = Recipe.open("res/recipe/CheekyBeans");

        // Act
        actualRecipe.write("res/recipe/CheekyBeans2");

        // Assert
        assertEquals("Cheeky Beans", actualRecipe.getName());
        File file = SysUtil.getDirectoryFromRoot("res/recipe/CheekyBeans2.rcp");
        file.delete();
    }
	
	@Test
    void open_shouldReturnRecipeObject_whenGivenValidFilename() throws IOException {
		// Arrange
        String name = "Cheeky Beans";

        // Act
        Recipe actualRecipe = Recipe.open("res/recipe/CheekyBeans");

        // Assert
        assertEquals(name, actualRecipe.getName());
    }
	
	@Test
    void ItOpensRecipeWithFile() throws IOException {
		// Arrange
        String name = "Cheeky Beans";
        File file = new File("res/recipe/CheekyBeans.rcp");

        // Act
        Recipe actualRecipe = Recipe.open(file);

        // Assert
        assertEquals(name, actualRecipe.getName());
    }
	
	@Test
    void open_shouldReturnNewRecipeObject_whenGivenInvalidFilename() throws IOException {
        
        try
        {
        	Recipe actualRecipe = Recipe.open("res/recipe/MonkeyBread");
     
          // Shouldn’t get here
          fail("Constructor should have thrown an IllegalArgumentException");
        }
        catch (IOException ioe)
        {
          // The exception was thrown as expected
        }   
    }
	
	@Test
    void ItSetsNameAndServings() throws IOException {
		// Arrange
        Recipe recipe = Recipe.open("res/recipe/CheekyBeans");

        // Act
        recipe.setName("Funky Beans");
        recipe.setServings(100);

        // Assert
        assertEquals("Funky Beans", recipe.getName());
        assertEquals(100, recipe.getServings());
    }
	
	@Test
    void itOpensRecipesFromFolder() throws IOException {
		// Arrange
	    File folder = SysUtil.getDirectoryFromRoot("res/recipe");
        for (File file: folder.listFiles()) {
        	// System.out.println(file.getName());
        }
    }
	
	/**
     * Compares two Recipes to determine if they have the same ingredients. Assumes both are 
     * non-null.
     * 
     * @author Myan Indugula
     * 
     * @param r1 First recipe being compared
     * @param r2 Second recipe being compared
     * 
     * @return True if the recipes have the same ingredients.
     *
     */
	public static boolean compareIngredients(Recipe r1, Recipe r2) 
	{
		if (r1.getIngredients().size() != r2.getIngredients().size())
		{
			return false;
		}
		
		for (int i = 0; i < r1.getIngredients().size(); i++)
		{
			if (!(r1.getIngredients().get(i).getName().toLowerCase().trim().equals
					(r2.getIngredients().get(i).getName().toLowerCase().trim())))
				return false;
		}
		return true;
	}

}
