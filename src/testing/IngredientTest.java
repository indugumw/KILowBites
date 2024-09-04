package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import exception.UsageException;
import ingredient.Ingredient;
import recipe.Utensil;
import utility.SysUtil;

class IngredientTest
{

  @Test
  void constructor()
  {
	  Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
    
	  Assertions.assertEquals("Red Onion", i.getName());
	  Assertions.assertEquals("(Chopped)", i.getDetails());
	  Assertions.assertEquals(20.0, i.getAmount());
	  Assertions.assertEquals("gram", i.getUnitName());
	  Assertions.assertEquals(100, i.getCalories());
	  Assertions.assertEquals(0.77, i.getDensity());
	  Assertions.assertNull(i.getUtensil());
  }
  
  @Test
  public void itCreatesAnIngredientWithNullCaloriesAndDensity()
  {
	  Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", null, null, null);
    
	  Assertions.assertEquals("Red Onion", i.getName());
	  Assertions.assertEquals("(Chopped)", i.getDetails());
	  Assertions.assertEquals(20.0, i.getAmount());
	  Assertions.assertEquals("gram", i.getUnitName());
	  Assertions.assertEquals(0, i.getCalories());
	  Assertions.assertEquals(0, i.getDensity());
	  Assertions.assertNull(i.getUtensil());
  }
  
  @Test
  public void itCreatesAnIngredientWithNonNullUtensil()
  {
	  Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", null, null, "fork");
	  
	  Assertions.assertEquals("Red Onion", i.getName());
	  Assertions.assertEquals("(Chopped)", i.getDetails());
	  Assertions.assertEquals(20.0, i.getAmount());
	  Assertions.assertEquals("gram", i.getUnitName());
	  Assertions.assertEquals(0, i.getCalories());
	  Assertions.assertEquals(0, i.getDensity());
	  Assertions.assertEquals("fork", i.getUtensil());
  }
  
  @Test
  public void itCreates4ParamIngredient()
  {
	  Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "fork");
	  
	  Assertions.assertEquals("Red Onion", i.getName());
	  Assertions.assertEquals("Chopped", i.getDetails());
	  Assertions.assertEquals(20.0, i.getAmount());
	  Assertions.assertEquals("fork", i.getUtensil());
  }
  
  @Test
  public void itFormatsDetailsWhenStringIsEmpty()
  {
	  // Arrange
	  Ingredient i = new Ingredient("Red Onion", null, 20.0, "gram", null, null, "fork");
	  
	  // Act
	  String details = i.formatDetails(i.getDetails());
	  
	  // Assert
	  Assertions.assertEquals("", details);
  }
  
  @Test
  public void itFormatsDetailsWhenSurroundingParams()
  {
	  // Arrange
	  Ingredient i = new Ingredient("Red Onion", "(Chopped)", 20.0, "gram", null, null, "fork");
	  
	  // Act
	  String details = i.formatDetails(i.getDetails());
	  
	  // Assert
	  Assertions.assertEquals("(Chopped)", details);
  }
  
  @Test
  public void itSetsAmountCaloriesAndDensity()
  {
	  // Arrange
	  Ingredient i = new Ingredient("Red Onion", "(Chopped)", 20.0, "gram", null, null, "fork");
	  
	  // Act
	  i.setAmount(15);
	  i.setCalories(140);
	  i.setDensity(45);
	  
	  // Assert
	  Assertions.assertEquals(15, i.getAmount());
	  Assertions.assertEquals(140, i.getCalories());
	  Assertions.assertEquals(45, i.getDensity());
  }
  
  @Test
  public void itHasNtrInfoWhenCaloriesAndDensityAreZero()
  {
	  // Arrange
	  Ingredient i = new Ingredient("Red Onion", "(Chopped)", 20.0, "gram", 0, 0.0, "fork");
	  
	  // Act
	  
	  // Assert
	  Assertions.assertFalse(i.hasNtrInfo());
  }
  
  @Test
  public void itHasNtrInfoWhenCaloriesAndDensityAreNotZero()
  {
	  // Arrange
	  Ingredient i = new Ingredient("Red Onion", "(Chopped)", 20.0, "gram", 1, 1.0, "fork");
	  
	  // Act
	  
	  // Assert
	  Assertions.assertTrue(i.hasNtrInfo());
  }
  
  @Test
  public void itHasNtrInfoWhenOnlyCaloriesIsZero()
  {
	  // Arrange
	  Ingredient i = new Ingredient("Red Onion", "(Chopped)", 20.0, "gram", 0, 1.0, "fork");
	  
	  // Act
	  
	  // Assert
	  Assertions.assertFalse(i.hasNtrInfo());
  }
  
  @Test
  public void itHasNtrInfoWhenOnlyDensityIsZero()
  {
	  // Arrange
	  Ingredient i = new Ingredient("Red Onion", "(Chopped)", 20.0, "gram", 1, 0.0, "fork");
	  
	  // Act
	  
	  // Assert
	  Assertions.assertFalse(i.hasNtrInfo());
  }
  
  //@Test
  void toStringTest() {
    Ingredient redOnion = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
    // System.out.println("[DEBUG] " + redOnion.toString(true, true));
    assertTrue(redOnion.toString(true, true).equals("Red Onion (Chopped) 20.00"));
    // System.out.println("[DEBUG] " + redOnion.toString(false, false));
    assertTrue(redOnion.toString(false, false).equals("Red Onion"));
  }
  
  @Test
  void itReturnsToStringIfAmountAndDetailsAreIncluded()
  {
	  // Arrange
	  Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
	  
	  // Act
	  String s = i.toString(true, true);
	  
	  // Assert
	  Assertions.assertEquals("20.00 gram of (Chopped) Red Onion", s);
  }
  
  @Test
  void itReturnsToStringIfOnlyAmountIsIncluded()
  {
	  // Arrange
	  Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
	  
	  // Act
	  String s = i.toString(true, false);
	  
	  // Assert
	  Assertions.assertEquals("20.00 gram of Red Onion", s);
  }
  
  @Test
  void itReturnsToStringIfOnlyDetailsIsIncluded()
  {
	  // Arrange
	  Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
	  
	  // Act
	  String s = i.toString(false, true);
	  
	  // Assert
	  Assertions.assertEquals(" gram of (Chopped) Red Onion", s);
  }
  
  @Test
  void openNTR() throws IOException {
    Ingredient.initAllIngredients();
    // System.out.println("[DEBUG] Loaded Ingredients: ");
    ArrayList<Ingredient> fromDefaultIngredients = Ingredient.loadDefaultIngredients();
    // System.out.println("[DEBUG] FromDefault[0]: " + fromDefaultIngredients.get(0).getCalories());
    // System.out.println("[DEBUG] LoadedFromFile[0]: " + Ingredient.allIngredients().get(0).getCalories());
  }
  
  @Test
  void itChecksIfAnIngredientAlreadyExists() throws IOException 
  {
	  // Arrange
	  Ingredient.initAllIngredients();
    
	  // Act 
    
	  // Assert
	  Assertions.assertFalse(Ingredient.alreadyExists(new Ingredient("Banana Bread", "loaf", 1, null)));
  }
  
  @Test
  void itIsADefaultIngredient() throws IOException 
  {
	  // Arrange
	  Ingredient i = new Ingredient("Alcohol", "Beer", 275, null);
    
	  // Act 
    
	  // Assert
	  Assertions.assertTrue(Ingredient.isDefaultIngredient(i));
  }
  
  //@Test
  void itAddsNewIngredientToAllIngredients() throws IOException
  {
	  // Arrange
	  Ingredient i = new Ingredient("Kale", "Chopped", 20.0, "gram", 100, 0.77, null);

	  Ingredient.initAllIngredients();
	  ArrayList<Ingredient> list = Ingredient.allIngredients();
	 
	  // Act
	  Ingredient.addToAllIngredients(i);
	  
	  // Assert
	  Assertions.assertEquals(80, list.size());
	  Ingredient.removeFromAllIngredients(i);
  }
  
  
  @Test
  void itThrowsExceptionWhenNewIngredientIsRemovedFromAllIngredients()
  {
	  try
	    {

		  // Arrange
		  Ingredient i = new Ingredient("Bone Marrow", "Chopped", 20.0, "gram", 100, 0.77, null);
		  ArrayList<Ingredient> list = Ingredient.allIngredients();
		 
		  // Act
		  Ingredient.removeFromAllIngredients(i);
	 
	      // Shouldn’t get here
	      fail("Constructor should have thrown an IllegalArgumentException");
	    }
	    catch (UsageException iae)
	    {
	      // The exception was thrown as expected
	    } 
  }
  
  @Test
  void itThrowsExceptionWhenDefaultIngredientIsRemovedFromAllIngredients()
  {
	  try
	    {
		  // Arrange
		  Ingredient i = new Ingredient("Alcohol", "Handle", 20.0, "ounce", 100, 0.77, null);
		  ArrayList<Ingredient> list = Ingredient.allIngredients();
		 
		  // Act
		  Ingredient.removeFromAllIngredients(i);
	 
	      // Shouldn’t get here
	      fail("Constructor should have thrown an IllegalArgumentException");
	    }
	    catch (UsageException iae)
	    {
	      // The exception was thrown as expected
	    } 
  }
  
  @Test
  void itGetsNewIngredientFromString()
  {
	  // Arrange
	  String s = "Rum";
	 
	  // Act
	  Ingredient i = Ingredient.fromString(s);
	  
	  // Assert
	  Assertions.assertEquals("Rum", i.getName());
  }
  
  @Test
  void itSavesIngredient() throws IOException, ClassNotFoundException
  {
	  // Arrange
	  File file = SysUtil.getDirectoryFromRoot("res/ntr/ingredients.ntr");
	  ArrayList<Ingredient> allIngredients;
	  allIngredients = Ingredient.loadDefaultIngredients();
	  
	  // Act
	  Ingredient.save();
	      
	   //Assert
	  Assertions.assertNotNull(allIngredients); 
  }
  
  @Test
  void itGetsNullFromString() throws IOException, ClassNotFoundException
  {
	  // Arrange
	  
	  // Act
	  Ingredient i = Ingredient.fromString("kiwi");
	      
	   //Assert
	  Assertions.assertNull(i); 
  }
  
  @Test
  void itIsNotDefaultIngredient() throws IOException, ClassNotFoundException
  {
	  // Arrange
	  Ingredient i = new Ingredient("kiwi", null, 0, null);
	  
	  // Act
	  boolean is = Ingredient.isDefaultIngredient(i);
	      
	   //Assert
	  Assertions.assertFalse(is); 
  }
  
  @Test
  void itAddsToAllIngredients() throws IOException, ClassNotFoundException
  {
	  // Arrange
	  Ingredient i = new Ingredient("kiwi", null, 0, null);
	  
	  // Act
	  Ingredient.addToAllIngredients(i);
	      
	   //Assert
	  Assertions.assertNotNull(Ingredient.allIngredients()); 
	  Ingredient.removeFromAllIngredients(i);
  }

}
