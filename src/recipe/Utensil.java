package recipe;

import java.io.Serializable;
import java.util.ArrayList;

import ingredient.Ingredient;

/**
 * Utility Class for a utensil object.
 * 
 * @author Logan
 *
 */

public class Utensil implements Serializable
{
  /**
   * 
   * 
   */
  private static final long serialVersionUID = 1L;
  private String name;
  private String details;
  private ArrayList<Ingredient> ingredients;

  /**
   * Constructs a new Utensil object with an empty list of ingredients.
   * 
   * @param name
   *          the name of this utensil
   * @param details
   *          additional details about this utensil
   */
  public Utensil(final String name, final String details)
  {
    this.name = name;
    this.details = details;
    this.ingredients = new ArrayList<Ingredient>();
  }

  /**
   * Constructs a new Utensil object with a list of ingredients.
   * 
   * @param name
   *          the name of this utensil
   * @param details
   *          additional details about this utensil
   * @param ingredients
   *          the list of ingredients in this utensil
   */
  public Utensil(final String name, final String details, final ArrayList<Ingredient> ingredients)
  {
    this.name = name;
    this.details = details;
    this.ingredients = ingredients;
  }

  /**
   * Constructs a new Utensil object with an empty list of ingredients and no details.
   * 
   * @param name
   *          the name of this utensil
   */
  public Utensil(final String name)
  {
    this.name = name;
    this.details = "";
    this.ingredients = new ArrayList<Ingredient>();
  }

  /**
   * Constructs a new Utensil object with a list of ingredients and no details.
   * 
   * @param name
   *          the name of this utensil
   * @param ingredients
   *          the list of ingredients in this utensil
   */
  public Utensil(final String name, final ArrayList<Ingredient> ingredients)
  {
    this.name = name;
    this.details = "";
    this.ingredients = ingredients;

  }

  /**
   * Adds an ingredient to this utensil.
   * 
   * @param ingredient
   *          the ingredient to be added
   */
  public void addIngredient(final Ingredient ingredient)
  {
    ingredients.add(ingredient);
  }

  /**
   * Adds a list of ingredients to this utensil.
   * 
   * @param ingredients
   *          the list of ingredients to be added
   */
  public void addIngredients(final ArrayList<Ingredient> ingredients)
  {
    for (Ingredient i : ingredients)
    {
      addIngredient(i);
    }
  }

  /**
   * Removes an ingredient from this utensil by its name.
   * 
   * @param ingredientName
   *          the name of the ingredient to be removed
   */
  public void removeIngredient(final String ingredientName)
  {
    ArrayList<Ingredient> ingredientIterate = new ArrayList<>(ingredients);
    for (Ingredient ingredient : ingredientIterate)
    {

      if (ingredient.getName().toLowerCase().equals(ingredientName.toLowerCase()))
      {
        if (ingredients.size() == 1)
          ingredients = new ArrayList<>();
        else
          ingredients.remove(ingredients.indexOf(ingredient));
      }
    }
  }

  /**
   * Removes a list of ingredients from this utensil.
   * 
   * @param ingredients
   *          the list of ingredients to be removed
   */
  public void removeIngredients(final ArrayList<Ingredient> ingredients)
  {
    for (Ingredient ingredient : ingredients)
    {
      removeIngredient(ingredient.getName());
    }
  }

  /**
   * Removes all ingredients from this utensil.
   */
  public void removeAllIngredients()
  {
    ingredients = new ArrayList<>();
  }

  /**
   * Returns the name of this utensil.
   * 
   * @return the name of this utensil
   */
  public String getName()
  {
    return this.name;
  }

  /**
   * Sets the name of this utensil.
   * 
   * @param name
   *          the new name for this utensil
   */
  public void setName(final String name)
  {
    if (this != null)
      this.name = name;
  }

  /**
   * Returns the details about this utensil.
   * 
   * @return the details about this utensil
   */
  public String getDetails()
  {
    return this.details;
  }

  /**
   * Returns the list of ingredients in this utensil.
   * 
   * @return the list of ingredients in this utensil or null if there are no ingredients
   */
  public ArrayList<Ingredient> getIngredients()
  {
    if (hasIngredients())
      return ingredients;
    else
      return null;
  }

  /**
   * Checks if this utensil has any ingredients.
   * 
   * @return true if this utensil has ingredients, false otherwise
   */
  public boolean hasIngredients()
  {
    if (ingredients.size() == 0)
    {
      return false;
    } else {
      return true;
    }

  }

  /**
   * Returns a string representation of this utensil.
   * 
   * @return a string representation of this utensil
   */
  public String toString()
  {
    if (!details.isEmpty())
    {
      return this.details + " " + this.name;
    }
    else
    {
      return this.name;
    }
  }

}
