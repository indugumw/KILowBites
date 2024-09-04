package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import gui.editor.RecipeEditorController;
import recipe.Recipe;
import recipe.RecipeStep;
import recipe.Utensil;

class RecipeStepTest {
	
	@Test
	void itCreatesRecipeStepWithActionType() throws IOException {
		// Arrange
		Utensil skillet = new Utensil("skillet", "large");
		RecipeStep firstStep = new RecipeStep("put", "butter", skillet, "in", "for 2 minutes");
		
		// Act
		
		// Assert
		Assertions.assertEquals("put", firstStep.getAction());
		Assertions.assertEquals("butter", firstStep.getSubject());
		Assertions.assertEquals("skillet", firstStep.getDestinationUtensil().getName());
		Assertions.assertEquals("in", firstStep.getActionType());
	}
	
	@Test
	void itCreatesRecipeStepWithoutActionType() throws IOException {
		// Arrange
		RecipeStep firstStep = new RecipeStep("put", "butter", "skillet", "for 2 minutes");
		
		// Act
		
		// Assert
		Assertions.assertEquals("put", firstStep.getAction());
		Assertions.assertEquals("butter", firstStep.getSubject());
	}

	@Test
	void itSetsAction() throws IOException {
		// Arrange
		Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
		ArrayList<RecipeStep> recipeSteps = bananasFoster.getSteps();
		RecipeStep firstStep = recipeSteps.get(0);
		
		// Act
		firstStep.setAction("melt");
		
		// Assert
		Assertions.assertEquals("melt", firstStep.getAction());
	}
	
	@Test
	void itGetsFirstAction() throws IOException {
		// Arrange
		Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
		ArrayList<RecipeStep> recipeSteps = bananasFoster.getSteps();
		RecipeStep firstStep = recipeSteps.get(0);
		
		// Act
		
		// Assert
		Assertions.assertEquals("Put", firstStep.getAction());
	}
	
	@Test
	void itGetsSecondAction() throws IOException {
		// Arrange
		Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
		ArrayList<RecipeStep> recipeSteps = bananasFoster.getSteps();
		RecipeStep firstStep = recipeSteps.get(1);
		
		// Act
		
		// Assert
		Assertions.assertEquals("Boil", firstStep.getAction());
	}
	
	@Test
	void itGetsLastAction() throws IOException {
		// Arrange
		Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
		ArrayList<RecipeStep> recipeSteps = bananasFoster.getSteps();
		RecipeStep firstStep = recipeSteps.get(recipeSteps.size() - 1);
		
		// Act
		
		// Assert
		Assertions.assertEquals("Boil", firstStep.getAction());
	}
	
	@Test
	void itSetsSubject() throws IOException {
		// Arrange
		Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
		ArrayList<RecipeStep> recipeSteps = bananasFoster.getSteps();
		RecipeStep firstStep = recipeSteps.get(0);
		
		// Act
		firstStep.setSubject("bananas");
		
		// Assert
		Assertions.assertEquals("bananas", firstStep.getSubject());
	}
	
	@Test
	void itGetsFirstSubject() throws IOException {
		// Arrange
		Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
		ArrayList<RecipeStep> recipeSteps = bananasFoster.getSteps();
		RecipeStep firstStep = recipeSteps.get(0);
		
		// Act
		
		// Assert
		Assertions.assertEquals("Pinto Beans", firstStep.getSubject());
	}
	
	@Test
	void itSetsDestinationUtensil() throws IOException {
		// Arrange
		Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
		ArrayList<RecipeStep> recipeSteps = bananasFoster.getSteps();
		RecipeStep firstStep = recipeSteps.get(0);
		Utensil fork = new Utensil("fork", "");
		
		// Act
		firstStep.setDestinationUtensil(fork);
		
		// Assert
		Assertions.assertEquals(fork, firstStep.getDestinationUtensil());
	}
	
	@Test
	void itGetsFirstDestinationUtensil() throws IOException {
		// Arrange
		Recipe bananasFoster = Recipe.open("res/recipe/CheekyBeans");
		ArrayList<RecipeStep> recipeSteps = bananasFoster.getSteps();
		RecipeStep firstStep = recipeSteps.get(0);
		Utensil skillet = new Utensil("Pot", "Large");
		
		// Act
		
		// Assert
		Assertions.assertEquals(skillet.getName(), firstStep.getDestinationUtensil().getName());
		Assertions.assertEquals(skillet.getDetails(), firstStep.getDestinationUtensil().getDetails());
	}
	
	@Test
	void itGetsToStringUpperCaseNoDestinationUtensil() throws IOException {
		// Arrange
		Utensil skillet = new Utensil("N/A", "large");
		RecipeStep firstStep = new RecipeStep("put", "SKILLET", skillet, "In", "for 2 minutes");
		
		// Act
		String s = firstStep.toString();
		
		// Assert
		Assertions.assertTrue(RecipeEditorController.isUpper(firstStep.getSubject()));
		Assertions.assertTrue(firstStep.getDestinationUtensil().getName().equals("N/A"));
		Assertions.assertEquals("put the contents of the  skillet", s);
	}
	
	@Test
	void itGetsToStringUpperCaseWithDestinationUtensil() throws IOException {
		// Arrange
		Utensil skillet = new Utensil("Skillet", "large");
		RecipeStep firstStep = new RecipeStep("put", "SKILLET", skillet, "In", 
				"for 2 minutes");
		
		// Act
		String s = firstStep.toString();
		
		// Assert
		Assertions.assertTrue(RecipeEditorController.isUpper(firstStep.getSubject()));
		Assertions.assertFalse(firstStep.getDestinationUtensil().getName().equals("N/A"));
		Assertions.assertEquals("putthe contents of the skillet in the large skillet for 2 minutes", s);
	}
	
	@Test
	void itGetsToStringLowerCase() throws IOException {
		// Arrange
		Utensil skillet = new Utensil("Skillet", "Large");
		RecipeStep firstStep = new RecipeStep("Put", "butter", skillet, "In", "For 2 minutes");
		
		// Act
		String s = firstStep.toString();
		
		// Assert
		Assertions.assertFalse(RecipeEditorController.isUpper(firstStep.getSubject()));
		Assertions.assertEquals("Put the butter in the large skillet for 2 minutes", s);
	}

}
