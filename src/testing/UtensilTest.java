package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ingredient.Ingredient;
import recipe.Recipe;
import recipe.Utensil;

class UtensilTest {

	@Test
	void testConstructorAndGetters() {
		Utensil saucepan = new Utensil("Saucepan", "Medium");
		assertEquals(saucepan.getName(), "Saucepan");
		assertEquals(saucepan.getDetails(), "Medium");
		
		Utensil spoon = new Utensil("Spoon", "Wooden");
		assertEquals(spoon.getName(), "Spoon");
		assertEquals(spoon.getDetails(), "Wooden");
	}
	
	@Test
	void testNullName()
	{
		Utensil saucepan = new Utensil(null, "Medium");
		assertEquals(saucepan.getName(), null);
		
		Utensil spoon = new Utensil(null, "Wooden");
		assertEquals(spoon.getName(), null);
	}
	
	@Test
	void itSetsName() {
		Utensil saucepan = new Utensil("Saucepan", "Medium");
		
		saucepan.setName("Spoon");
		
		Assertions.assertEquals("Spoon", saucepan.getName());
	}
	
	@Test
	void testNullDetails() throws IOException
	{
		Utensil saucepan = new Utensil("Saucepan");
		assertEquals("", saucepan.getDetails());
		
	    Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
		ArrayList<Ingredient> ingredients = bananasFoster.getIngredients();
		Utensil spoon = new Utensil("Spoon", ingredients);
		assertEquals("", spoon.getDetails());
	}
	
	@Test
	void testAllNull()
	{
		Utensil saucepan = new Utensil(null);
		assertEquals("", saucepan.getDetails());
		assertEquals("", saucepan.getDetails());
		
		Utensil spoon = new Utensil(null, null, null);
		assertEquals(null, spoon.getName());
		assertEquals(null, spoon.getDetails());
	}
	
	@Test
	void itAddsOneIngredient() {
		// Arrange
		Utensil saucepan = new Utensil("Saucepan", "Medium");
		Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		
		// Act
		saucepan.addIngredient(i);
		
		// Assert
		Assertions.assertNotNull(saucepan.getIngredients());
		Assertions.assertEquals(1, saucepan.getIngredients().size());
	}
	
	@Test
	void itAddsMultipleIngredients() {
		// Arrange
		Utensil saucepan = new Utensil("Saucepan", "Medium");
		Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i2 = new Ingredient("Beef", "Ground", 20.0, "gram", 100, 0.77, null);
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(i2);
		ingredients.add(i);
		
		// Act
		saucepan.addIngredients(ingredients);
		
		// Assert
		Assertions.assertNotNull(saucepan.getIngredients());
		Assertions.assertEquals(2, saucepan.getIngredients().size());
	}
	
	@Test
	void itRemovesOneIngredient() {
		// Arrange
		Utensil saucepan = new Utensil("Saucepan", "Medium");
		Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		saucepan.addIngredient(i);
		
		// Act
		saucepan.removeIngredient(i.getName());
		
		// Assert
		Assertions.assertNull(saucepan.getIngredients());
	}
	
	@Test
	void itRemovesMultipleIngredients() {
		// Arrange
		Utensil saucepan = new Utensil("Saucepan", "Medium");
		Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i2 = new Ingredient("Beef", "Ground", 20.0, "gram", 100, 0.77, null);
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(i2);
		ingredients.add(i);
		saucepan.addIngredients(ingredients);
		
		// Act
		saucepan.removeIngredients(ingredients);
		
		// Assert
		Assertions.assertNull(saucepan.getIngredients());
	}
	
	@Test
	void itRemovesAllIngredients() {
		// Arrange
		Utensil saucepan = new Utensil("Saucepan", "Medium");
		Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		Ingredient i2 = new Ingredient("Beef", "Ground", 20.0, "gram", 100, 0.77, null);
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(i2);
		ingredients.add(i);
		saucepan.addIngredients(ingredients);
		
		// Act
		saucepan.removeAllIngredients();
		
		// Assert
		Assertions.assertNull(saucepan.getIngredients());
	}
	
	@Test
	void itReturnsTrueWhenHasIngredient() {
		// Arrange
		Utensil saucepan = new Utensil("Saucepan", "Medium");
		Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		saucepan.addIngredient(i);
		
		// Act
		boolean has = saucepan.hasIngredients();
		
		// Assert
		Assertions.assertTrue(has);
	}
	
	@Test
	void itReturnsFalseWhenDoesNotHaveIngredient() {
		// Arrange
		Utensil saucepan = new Utensil("Saucepan", "Medium");
		
		// Act
		boolean has = saucepan.hasIngredients();
		
		// Assert
		Assertions.assertFalse(has);
	}
	
	@Test
	void itReturnsToString() {
		// Arrange
		Utensil saucepan = new Utensil("Saucepan", "Medium");
		
		// Act
		String s= saucepan.toString();
		
		// Assert
		Assertions.assertEquals("Medium Saucepan", s);
	}
	
	@Test
	void itReturnsNullWhenDetailsIsEmpty() {
		// Arrange
		Utensil saucepan = new Utensil("Saucepan", "");
		
		// Act
		String s= saucepan.toString();
		
		// Assert
		Assertions.assertEquals("Saucepan", s);
	}

}
