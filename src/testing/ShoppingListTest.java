package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ingredient.Ingredient;
import meal.Meal;
import recipe.Recipe;
import shoppinglist.ShoppingList;
import utility.SysUtil;

class ShoppingListTest {

	@Test
	void itDisplaysShoppingListForRecipe() throws IOException {
		// Arrange
	    Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
	    int servings = 3;
	    ShoppingList shoppingList = new ShoppingList(bananasFoster, 3);
	    
	    // Act
	    
	    // Assert
	    shoppingList.display(1);
	}
	
	@Test
	void itDisplaysShoppingListForRecipeWithFileName() throws IOException {
		// Arrange
	    int servings = 3;
	    ShoppingList shoppingList = new ShoppingList("res/recipe/CheekyBeans", 3);
	    
	    // Act
	    
	    // Assert
	    shoppingList.display(1);
	}
	
	@Test
	void itThrowsExceptionWhenRecipeForNonexistentFileName() throws IOException {
	    {
	        try
	        {
	    		// Arrange
	    	    int servings = 3;
	    	    ShoppingList shoppingList = new ShoppingList("res/recipe/CheekyBeans", 3);
	    	    
	    	    Recipe r = Recipe.open("res/recipe/MonkeyBread");
	     
	          // Shouldn’t get here
	          fail("Constructor should have thrown an IOException");
	        }
	        catch (IOException ioe)
	        {
	          // The exception was thrown as expected
	        }   
	      }
	}
	
	@Test
	void itDisplaysShoppingListForMeal() throws IOException {
		// Arrange
		ArrayList <Recipe> recipes = new ArrayList<>();
		Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
			    
		Recipe chicken = new Recipe("chicken");
		Ingredient butter = new Ingredient("butter", "", 0.0, "", 0, 0.0, "");
		chicken.addIngredient(butter);
		recipes.add(chicken);
		recipes.add(bananasFoster);
			    
		Meal thanksgiving = new Meal("thanksgiving", recipes, 3);
	    int servings = 3;
	    ShoppingList shoppingList = new ShoppingList(thanksgiving, 3);
	    
	    // Act
	    
	    // Assert
	    shoppingList.display(1);
	}
	
	@Test
	void itChecksSort() throws IOException {
		// Arrange
	    Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
	    int servings = 3;
	    ShoppingList shoppingList = new ShoppingList(bananasFoster, 3);
	    
	    ArrayList<Ingredient> correctIngredients = new ArrayList<>();
	    correctIngredients.add(new Ingredient("bananas", null, 0, null));
	    correctIngredients.add(new Ingredient("brown sugar", null, 0, null));
	    correctIngredients.add(new Ingredient("butter", null, 0, null));
	    correctIngredients.add(new Ingredient("cinnamon", null, 0, null));
	    correctIngredients.add(new Ingredient("creme de cacao", null, 0, null));
	    correctIngredients.add(new Ingredient("ice cream", null, 0, null));
	    correctIngredients.add(new Ingredient("rum", null, 0, null));
	    
	    // Act
	    
	    // Assert
	    shoppingList.display(1);
	}

	@Test
	void itPrintsToFile() throws IOException {
		// Arrange
	    Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
	    int servings = 3;
	    ShoppingList shoppingList = new ShoppingList(bananasFoster, 3);
	    
	    File file;
      
      try {
        file = SysUtil.getDirectoryFromExports("Shopping Lists/CheekyBeansTestShoppingList");
      }
      catch (NullPointerException npe) {
        file = SysUtil.getDirectoryFromRoot("Exports/Shopping Lists/CheekyBeansTestShoppingList");
      }
	    
	    // Act
	    shoppingList.printToFile(file);
	    
	    // Assert
	    shoppingList.display(1);
	    file.delete();
	}
	
	@Test
	void itPerformsVariousGetters() throws IOException {
		// Arrange
	    Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
	    int servings = 3;
	    ShoppingList shoppingList = new ShoppingList(bananasFoster, 3);
	    
	    // Act
	    String num = shoppingList.getNumberOfPeople();
	    String name = shoppingList.getRecipeName();
	    double doubleNum = shoppingList.getNumberOfPeopleDouble();
	    
	    // Assert
	    Assertions.assertEquals("3.0", num);
	    Assertions.assertEquals("Cheeky Beans", name);
	    Assertions.assertEquals(3.0, doubleNum);
	    Assertions.assertNotNull(shoppingList.getShoppingList());
	}
	
	@Test
	void itDisplaysFromDouble() throws IOException {
		// Arrange
	    Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
	    double servings = 3.0;
	    ShoppingList shoppingList = new ShoppingList(bananasFoster, 3);
	    
	    // Act
	    ArrayList<String> display = shoppingList.display(servings);
	    
	    // Assert
	    Assertions.assertNotNull(shoppingList.getShoppingList());
  
	}
	
	@Test
	void itDisplaysIngredientWithoutUnits() throws IOException {
		// Arrange
	    Recipe bananasFoster = Recipe.open("res/recipe/OvenFriedChicken");
	    double servings = 3.0;
	    ShoppingList shoppingList = new ShoppingList(bananasFoster, 3);
	    
	    // Act
	    ArrayList<String> display = shoppingList.display(servings);
	    
	    // Assert
	    Assertions.assertNotNull(shoppingList.getShoppingList());
  
	}

}
