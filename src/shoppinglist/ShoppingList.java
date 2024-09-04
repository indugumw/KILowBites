package shoppinglist;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import ingredient.Ingredient;
import meal.Meal;
import recipe.Recipe;
import utility.RecipeEditorUtil;

/**
 * Create a shopping list for either a recipe or meal.
 * 
 * @author Neil Sundrani
 *
 */
public class ShoppingList
{

  private static final String NA = "N/A";
  private static final String SPACE = " ";
  private static final String NL = "\n";
  private static final String COLON = ": ";
  private HashMap<Ingredient, Integer> shoppingList;
  private double numOfPeople;
  private String recipeName;

  /**
   * Constructs a new ShoppingList object for a recipe.
   * 
   * @param recipe
   *          the recipe for which the shopping list will be created
   * @param numOfPeople
   *          the number of people this recipe will serve
   */
  public ShoppingList(final Recipe recipe, final double numOfPeople)
  {
    this.shoppingList = new HashMap<Ingredient, Integer>();
    this.numOfPeople = numOfPeople;
    this.recipeName = recipe.getName();

    ArrayList<Ingredient> ingredients = recipe.getIngredients();
    ingredients = RecipeEditorUtil.sortIngredients(ingredients);
    for (Ingredient i : ingredients)
    {
      shoppingList.put(i, recipe.getServings());
    }

  }

  /**
   * Constructs a new ShoppingList object for a recipe.
   * 
   * @param recipeFileName
   *          the name of the file containing the recipe
   * @param numOfPeople
   *          the number of people this recipe will serve
   */
  public ShoppingList(final String recipeFileName, final double numOfPeople)
  {
    this.shoppingList = new HashMap<Ingredient, Integer>();
    this.numOfPeople = numOfPeople;
    Recipe recipe = null;

    try
    {
      recipe = Recipe.open(recipeFileName);
    }
    catch (IOException e)
    {
      // System.out.println("Cannot read file.");
    }

    ArrayList<Ingredient> ingredients = recipe.getIngredients();
    ingredients = RecipeEditorUtil.sortIngredients(ingredients);

    for (Ingredient i : ingredients)
    {
      shoppingList.put(i, recipe.getServings());
    }

  }

  /**
   * Constructs a new ShoppingList object for a custom meal.
   * 
   * @param meal
   *          the custom meal
   * @param numOfPeople
   *          the number of people this meal will serve
   */
  public ShoppingList(final Meal meal, final double numOfPeople)
  {

    this.shoppingList = new HashMap<Ingredient, Integer>();
    this.numOfPeople = numOfPeople;

    ArrayList<Recipe> recipes = meal.getRecipes();

    for (Recipe r : recipes)
    {
      ArrayList<Ingredient> ingredients = r.getIngredients();
      ingredients = RecipeEditorUtil.sortIngredients(ingredients);
      int servings = r.getServings();
      for (Ingredient i : ingredients)
      {
        shoppingList.put(i, servings);
      }
    }
  }

  /**
   * Displays the shopping list for this recipe.
   * 
   * @param scale
   *          the number of people this recipe will serve
   * @return an ArrayList containing the ingredients and their quantities needed for this recipe
   */
  public ArrayList<String> display(final double scale)
  {
    return display((int) scale);
  }

  /**
   * Displays the shopping list for this recipe.
   * 
   * @param scale
   *          the number of people this recipe will serve
   * @return an ArrayList containing the ingredients and their quantities needed for this recipe
   */
  public ArrayList<String> display(final int scale)
  {
    // System.out.println("Shopping List:\n\n");
    ArrayList<String> result = new ArrayList<String>();

    for (Entry<Ingredient, Integer> mapElem : shoppingList.entrySet())
    {
      this.numOfPeople = Math.floor(scale);
      String ingredientName = mapElem.getKey().getName();
      double amount = mapElem.getKey().getAmount();
      double servings = mapElem.getValue();

      // System.out.println(amount);
      // System.out.println(servings);

      // System.out.println("[DEBUG] UnitName:" + mapElem.getKey().getUnitName());

      if (!mapElem.getKey().getUnitName().equals(NA))
      {
        result.add(ingredientName + COLON + getTotalAmount(amount, servings) + SPACE
            + mapElem.getKey().getUnitName());
        /*
         * System.out.println(ingredientName + ": " + getTotalAmount(amount, servings) + " " +
         * mapElem.getKey().getUnitName());
         */
      }
      else
      {
        result.add(ingredientName + COLON + getTotalAmount(amount, servings));
        // System.out.println(ingredientName + ": " + getTotalAmount(amount, servings));
      }
    }

    return result;
  }

  /**
   * Prints the shopping list to a file.
   * 
   * @param file
   *          the file to which the shopping list will be printed
   */
  public void printToFile(final File file)
  {
    try (PrintWriter writer = new PrintWriter(file))
    {
      writer.println("Shopping List:\n====================");

      // Sort ingredients by alphabetical order
      List<Entry<Ingredient, Integer>> sortedEntries = new ArrayList<>(shoppingList.entrySet());
      Collections.sort(sortedEntries, new Comparator<Entry<Ingredient, Integer>>()
      {
        @Override
        public int compare(final Entry<Ingredient, Integer> ingredient1,
            final Entry<Ingredient, Integer> ingredient2)
        {
          return ingredient1.getKey().getName().compareToIgnoreCase(ingredient2.getKey().getName());
        }
      });

      // Display shopping list
      for (Entry<Ingredient, Integer> mapElem : sortedEntries)
      {
        String ingredientName = mapElem.getKey().getName();
        double amount = mapElem.getKey().getAmount();
        double servings = mapElem.getValue();

        /*
         * writer.print(String.format("%s: %.1f %s(s)\n", ingredientName, getTotalAmount(amount,
         * servings), ingredientUnit));
         */

        if (!mapElem.getKey().getUnitName().equals(NA))
        {
          writer.print(ingredientName + COLON + getTotalAmount(amount, servings) + SPACE
              + mapElem.getKey().getUnitName());
          writer.print(NL);
          /*
           * System.out.println(ingredientName + ": " + getTotalAmount(amount, servings) + " " +
           * mapElem.getKey().getUnitName());
           */
        }
        else
        {
          writer.print(ingredientName + COLON + getTotalAmount(amount, servings));
          writer.print(NL);
          // System.out.println(ingredientName + ": " + getTotalAmount(amount, servings));
        }
      }
    }
    catch (IOException e)
    {
      System.err.println("Error when writing to file: " + e.getMessage());
    }
  }

  /**
   * Calculates the total amount of an ingredient needed for the recipe.
   * 
   * @param amount
   *          the amount of the ingredient needed per serving
   * @param servings
   *          the number of servings in the recipe
   * @return the total amount of the ingredient needed for the recipe
   */

  private double getTotalAmount(final double amount, final double servings)
  {
    return Double.parseDouble(String.format("%.3f", (amount / servings) * numOfPeople));
  }

  /**
   * Returns the shopping list for this recipe.
   * 
   * @return a HashMap containing the ingredients and their quantities needed for this recipe
   */
  public HashMap<Ingredient, Integer> getShoppingList()
  {
    return this.shoppingList;
  }

  /**
   * Returns the number of people this recipe serves as a String.
   * 
   * @return a String representation of the number of people this recipe serves
   */
  public String getNumberOfPeople()
  {
    return Double.toString(numOfPeople);
  }

  /**
   * Returns the name of this recipe.
   * 
   * @return the name of this recipe
   */
  public String getRecipeName()
  {
    return this.recipeName;
  }

  /**
   * Returns the number of people this recipe serves as a double rounded down to the nearest
   * integer.
   * 
   * @return a double representation of the number of people this recipe serves rounded down to the
   *         nearest integer
   */
  public double getNumberOfPeopleDouble()
  {
    return Double.valueOf(Math.floor(numOfPeople));
  }

}
