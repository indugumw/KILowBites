package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import recipe.Recipe;
import recipe.RecipeStep;
import recipe.Utensil;
import utility.SysUtil;
import meal.Meal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ingredient.DefaultIngredients;
import ingredient.Ingredient;

class MealTest {
	
	@Test
	void itReturnsTrueWhenIngredientIsInMeal() throws IOException {
		// Arrange
		ArrayList <Recipe> recipes = new ArrayList<>();
	    Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
	    
	    Recipe chicken = new Recipe("chicken");
	    Ingredient butter = new Ingredient("butter", "", 0.0, "", 0, 0.0, "");
	    chicken.addIngredient(butter);
	    recipes.add(chicken);
	    recipes.add(bananasFoster);
	    
		Meal thanksgiving = new Meal("thanksgiving", recipes, 12);
				
		// Act
				
		// Assert
		Assertions.assertTrue(thanksgiving.hasIngredient("butter"));
	}
	
	@Test
	void itReturnsFalseWhenIngredientIsNotInMeal() throws IOException {
		// Arrange
		ArrayList <Recipe> recipes = new ArrayList<>();
	    Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
	    
	    Recipe chicken = new Recipe("chicken");
	    Ingredient butter = new Ingredient("butter", "", 0.0, "", 0, 0.0, "");
	    chicken.addIngredient(butter);
	    recipes.add(chicken);
	    recipes.add(bananasFoster);
	    
		Meal thanksgiving = new Meal("thanksgiving", recipes, 12);
				
		// Act
				
		// Assert
		Assertions.assertFalse(thanksgiving.hasIngredient("pork"));
	}
	
	@Test
	void itRemovesRecipe() throws IOException {
		// Arrange
		ArrayList <Recipe> recipes = new ArrayList<>();
	    Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
	    
	    Recipe chicken = new Recipe("chicken");
	    Ingredient butter = new Ingredient("butter", "", 0.0, "", 0, 0.0, "");
	    chicken.addIngredient(butter);
	    recipes.add(chicken);
	    recipes.add(bananasFoster);
	    
		Meal thanksgiving = new Meal("thanksgiving", recipes, 12);
				
		// Act
		thanksgiving.removeRecipe(0);
				
		// Assert
		Assertions.assertEquals(1, thanksgiving.getRecipes().size());
		Assertions.assertEquals("Cheeky Beans", thanksgiving.getRecipes().get(0).getName());
	}
	
	@Test
    void open_shouldReturnMealObject_whenGivenValidFilename() throws IOException {
		// Arrange
        String name = "Chicken&Beans";

        // Act
        Meal actualMeal = Meal.open("res/meal/Chicken&Beans");

        // Assert
        assertEquals(name, actualMeal.getName());
    }
	
	@Test
    void open_shouldReturnNewMealObject_whenGivenInvalidFilename() throws IOException {
        
        try
        {
          Meal actualMeal = Meal.open("res/meal/MonkeyBread");
     
          // Shouldn’t get here
          fail("Constructor should have thrown an IllegalArgumentException");
        }
        catch (IOException ioe)
        {
          // The exception was thrown as expected
        }   
    }
	
	@Test
    void itWritesMeal() throws IOException {
		// Arrange
        String name = "Chicken&Beans";
        Meal actualMeal = Meal.open("res/meal/Chicken&Beans");

        // Act
        actualMeal.write("res/meal/Chicken&Beans");

        // Assert
        assertEquals(name, actualMeal.getName());
        File file = SysUtil.getDirectoryFromRoot("res/meal/LotsOfCheekyBeans.rcp");
        file.delete();
    }
	
	@Test
    void itGetsServings() throws IOException {
		// Arrange
        Meal actualMeal = Meal.open("res/meal/Chicken&Beans");

        // Act
        int servings = actualMeal.getServings();

        // Assert
        assertEquals(0, servings);
    }
	
	@Test
    void itAddsARecipe() throws IOException {
		// Arrange
        Meal actualMeal = Meal.open("res/meal/Chicken&Beans");
	    Recipe recipe = Recipe.open("res/recipe/CheekyBeans");

        // Act
        actualMeal.addRecipe(recipe);

        // Assert
        Assertions.assertNotNull(actualMeal.getRecipes());
        Assertions.assertEquals(3, actualMeal.getRecipes().size());
    }
	
	@Test
	void itCreatesMealWithNullRecipes() throws IOException {
		// Arrange
		Meal thanksgiving = new Meal("thanksgiving", null, 12);
				
		// Act
				
		// Assert
		Assertions.assertNotNull(thanksgiving.getRecipes());
	}
	
	@Test
    void itOpensAMealFromFile() throws IOException {
		// Arrange
        String name = "Chicken&Beans";
        File file = new File("res/meal/Chicken&Beans.mel");

        // Act
        Meal actualMeal = Meal.open(file);

        // Assert
        assertEquals(name, actualMeal.getName());
    }
	
	@Test
    void itOpensMealsFromFolder() throws IOException {
		// Arrange
	    File folder = SysUtil.getDirectoryFromRoot("res/meal");
        for (File file: folder.listFiles()) {
        	// System.out.println(file.getName());
        }
    }
	
	@Test
    void itOpensAMealFromNonExistentFile() throws ClassNotFoundException, IOException {
        try
        {
    		// Arrange
            File file = new File("res/meal/CheekyBeans.mel");

            // Act
            Meal actualMeal = Meal.open(file);
     
          // Shouldn’t get here
          fail("Constructor should have thrown an IllegalArgumentException");
        }
        catch (IOException e)
        {
          // The exception was thrown as expected
        }   
    }
	
	@Test
    void itSetsName() throws IOException {
		// Arrange
        File file = new File("res/meal/Chicken&Beans.mel");
        Meal actualMeal = Meal.open(file);

        // Act
        actualMeal.setName("CheekyBeans");

        // Assert
        assertEquals("CheekyBeans", actualMeal.getName());
    }

}
