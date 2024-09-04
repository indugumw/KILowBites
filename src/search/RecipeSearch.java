package search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import ingredient.Ingredient;
import recipe.Recipe;
import utility.SysUtil;

/**
 * Class that enables searching through recipes and displaying the filtered recipes.
 * 
 * @author Myan Indugula
 * 
 */
public class RecipeSearch
{
  private static String filepath = "res/recipe";

  private RecipeSearch()
  {

    // Static class, no construction
  }

  /**
   * Returns true if a recipe contains a given ingredient.
   * 
   * @param recipe
   *          The recipe being searched
   * @param userIngredient
   *          The ingredient the user wants to search for
   * 
   * @return True if the the recipe contains the ingredient.
   * 
   */
  public static boolean compareIngredients(final Recipe recipe, final String userIngredient)
  {
    ArrayList<Ingredient> ingredients = recipe.getIngredients();

    for (Ingredient i : ingredients)
    {
      String formattedUserIngredient = userIngredient.toLowerCase().trim();
      // System.out.println("[DEBUG] formattedUserIngredient: " + formattedUserIngredient);
      // System.out.println("[DEBUG] i.getName(): " + i.getName().toLowerCase());
      if (formattedUserIngredient.equals(i.getName().toLowerCase()))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Displays all recipes that contain a specified ingredient.
   * 
   * @param userIngredient
   *          The ingredient the user wants to search for
   * 
   * @return List of filtered recipes.
   * @throws IOException
   * 
   */
  public static ArrayList<Recipe> findMatchingRecipes(final String userIngredient)
      throws IOException
  {
    ArrayList<Recipe> matchingRecipes = new ArrayList<Recipe>();
    File folder = SysUtil.getDirectoryFromRoot(filepath);
    for (File file : folder.listFiles())
    {
      Recipe recipe = Recipe.open(file);
      if (compareIngredients(recipe, userIngredient))
      {
        matchingRecipes.add(recipe);
      }
    }

    return matchingRecipes;
  }

  /**
   * Displays all recipes that do not contain a specified ingredient.
   * 
   * @param userIngredient
   *          The ingredient the user wants to search for
   * 
   * @return List of filtered recipes.
   * @throws IOException
   * 
   */
  public static ArrayList<Recipe> findUnMatchingRecipes(final String userIngredient)
      throws IOException
  {
    ArrayList<Recipe> unMatchingRecipes = new ArrayList<Recipe>();
    File folder = SysUtil.getDirectoryFromRoot(filepath);
    for (File file : folder.listFiles())
    {
      Recipe recipe = Recipe.open(file);
      ArrayList<Ingredient> ingredients = recipe.getIngredients();
      for (Ingredient i : ingredients)
      {
        // System.out.println(i.getName());
      }
      if (!(compareIngredients(recipe, userIngredient)))
      {
        unMatchingRecipes.add(recipe);
      }
    }
    return unMatchingRecipes;
  }

}
