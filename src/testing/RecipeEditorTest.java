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
import recipe.Utensil;
import utility.RecipeEditorUtil;

class RecipeEditorTest {

	@Test
	void itPresentsIngredientsInAlphabeticalOrder() throws IOException {
		// Arrange
	    Recipe bananasFoster = Recipe.open("res/recipe/OvenFriedChicken");
	    ArrayList<Ingredient> ingredients = bananasFoster.getIngredients();
	    
	    ArrayList<Ingredient> correctIngredients = new ArrayList<>();
	    correctIngredients.add(new Ingredient("chicken", null, 0, null));
	    correctIngredients.add(new Ingredient("egg", null, 0, null));
	    correctIngredients.add(new Ingredient("milk", null, 0, null));
	    correctIngredients.add(new Ingredient("paprika", null, 0, null));
	    correctIngredients.add(new Ingredient("pepper", null, 0, null));
	    correctIngredients.add(new Ingredient("saltine crackers", null, 0, null));
	    correctIngredients.add(new Ingredient("thyme", null, 0, null));

		// Act
	    ArrayList<Ingredient> sortedIngredients = RecipeEditorUtil.sortIngredients(ingredients);
		
		// Assert
	    Assertions.assertTrue(compareListIngredients(sortedIngredients, correctIngredients));
	}
	
	@Test
	void itPresentsUtensilsInAlphabeticalOrder() throws IOException {
		// Arrange
	    Recipe bananasFoster = Recipe.open("res/recipe/OvenFriedChicken");
	    ArrayList<Utensil> utensils = bananasFoster.getUtensils();
	    
	    ArrayList<Utensil> correctUtensils = new ArrayList<>();
	    correctUtensils.add(new Utensil("baking pan"));
	    correctUtensils.add(new Utensil("bowl"));
	    correctUtensils.add(new Utensil("dish"));

		// Act
	    ArrayList<Utensil> sortedIngredients = RecipeEditorUtil.sortUtensils(utensils);
		
		// Assert
	    Assertions.assertTrue(compareListUtensils(sortedIngredients, correctUtensils));
	}
	
	/**
     * Compares two Lists of Ingredients to determine if they are equal. Assumes both lists are non-null.
     * 
     * @author Myan Indugula
     * 
     * @param list1 First list being compared
     * @param list2 Second list being compared
     * 
     * @return True if the lists are equal.
     *
     */
	public boolean compareListIngredients(List<Ingredient> list1, List<Ingredient> list2) 
	{
		if (list1.size() != list2.size())
		{
			return false;
		}
		
		for (int i = 0; i < list1.size(); i++)
		{
			if (list1.get(i).getName().equals(list2.get(i).getName())
					== false)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
     * Compares two Lists of Utensils to determine if they are equal. Assumes both lists are non-null.
     * 
     * @author Myan Indugula
     * 
     * @param list1 First list being compared
     * @param list2 Second list being compared
     * 
     * @return True if the lists are equal.
     *
     */
	public boolean compareListUtensils(List<Utensil> list1, List<Utensil> list2) 
	{
		if (list1.size() != list2.size())
		{
			return false;
		}
		
		for (int i = 0; i < list1.size(); i++)
		{
			if (list1.get(i).getName().equals(list2.get(i).getName())
					== false)
			{
				return false;
			}
		}
		return true;
	}

}
