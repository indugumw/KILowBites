package search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import ingredient.Ingredient;
import meal.Meal;
import utility.SysUtil;

/**
 * Class that has useful methods required for the MealSearchGUI class.
 * 
 * @author Logan, Myan
 *
 */

public class MealSearch
{

  private static String filepath = "res/meal";

  private MealSearch()
  {
    // Static class, no construction
  }

  /**
   * Returns true if a meal contains a given ingredient.
   * 
   * @param m
   *          The meal being searched
   * @param userIngredient
   *          The ingredient the user wants to search for
   * 
   * @return True if the the meal contains the ingredient.
   * 
   */
  public static boolean compareIngredients(final Meal m, final String userIngredient)
  {
    ArrayList<Ingredient> ingredients = m.getIngredients();

    for (Ingredient i : ingredients)
    {
      String newUserIngredient = userIngredient.toLowerCase().trim();

      if (newUserIngredient.equals(i.getName().toLowerCase()))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Displays all meals that contain a specified ingredient.
   * 
   * @param userIngredient
   *          The ingredient the user wants to search for
   * 
   * @return List of filtered meals.
   * @throws IOException
   * 
   */
  public static ArrayList<Meal> findMatchingMeals(final String userIngredient) throws IOException
  {
    ArrayList<Meal> matchingMeals = new ArrayList<Meal>();

    File folder = SysUtil.getDirectoryFromRoot(filepath);

    ArrayList<Meal> mealList = new ArrayList<Meal>();
    for (File file : folder.listFiles())
    {
      Meal meal = Meal.open(file);
      if(compareIngredients(meal, userIngredient)) 
      {
        matchingMeals.add(meal);
      }
    }

    for (Meal m : mealList)
    {
      if (m.hasIngredient(userIngredient))
        matchingMeals.add(m);
    }

    return matchingMeals;
  }

  /**
   * Class that returns a list of meals that don't contain the userIngredient.
   * 
   * @param userIngredient
   *          The ingredient to search for
   * 
   * @return The list of meals
   * @throws IOException
   */

  public static ArrayList<Meal> findUnMatchingMeals(final String userIngredient) throws IOException
  {
    ArrayList<Meal> matchingMeals = new ArrayList<Meal>();

    File folder = SysUtil.getDirectoryFromRoot(filepath);

    ArrayList<Meal> mealList = new ArrayList<Meal>();
    for (File file : folder.listFiles())
    {
      Meal meal = Meal.open(file);
      mealList.add(meal);
    }
    for (Meal m : mealList)
    {
      if (!m.hasIngredient(userIngredient))
        matchingMeals.add(m);
    }
    return matchingMeals;
  }
}
