package recipe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import gui.editor.RecipeEditorController;
import ingredient.Ingredient;
import static app.gui.MainWindowController.STRINGS;

/**
 * The Recipe class creates Recipe objects that contain Ingredients, Utensils, RecipeStep, and
 * Servings.
 * 
 * @author Logan, Xavier, Cooper, Myan
 *
 */

public class Recipe implements Serializable
{

  private static final String RCP = ".rcp";
  private static final long serialVersionUID = 1L;
  private String name;
  private ArrayList<Ingredient> ingredients;
  private ArrayList<Utensil> utensils;
  private ArrayList<RecipeStep> recipeSteps;
  private int servings;

  /**
   * Constructor for the Recipe class with only a name.
   * 
   * @param name
   *          Name of the recipe
   * 
   */

  public Recipe(final String name)
  {
    this(name, new ArrayList<Ingredient>(), new ArrayList<Utensil>(), new ArrayList<RecipeStep>(),
        0);
  }

  /**
   * Constructor for the Recipe class with a name, Ingredients, Utensils, RecipeSteps, and servings.
   * 
   * @param name
   *          Name of the recipe
   * @param ingredients
   *          Ingredients in the recipe
   * @param utensils
   *          Utensils used in the recipe
   * @param recipeSteps
   *          Recipe steps
   * @param servings
   *          Servings of the recipe
   * 
   */

  public Recipe(final String name, final ArrayList<Ingredient> ingredients,
      final ArrayList<Utensil> utensils, final ArrayList<RecipeStep> recipeSteps,
      final int servings)
  {
    this.name = name;
    this.ingredients = ingredients;
    this.utensils = utensils;
    this.recipeSteps = recipeSteps;
    this.servings = servings;
  }

  /**
   * Constructor for the Recipe class with Ingredients, Utensils, and RecipeSteps.
   * 
   * @param ingredients
   *          Ingredients in the recipe
   * @param utensils
   *          Utensils used in the recipe
   * @param recipeSteps
   *          Recipe steps
   * 
   */

  public Recipe(final ArrayList<Ingredient> ingredients, final ArrayList<Utensil> utensils,
      final ArrayList<RecipeStep> recipeSteps)
  {
    this.ingredients = ingredients;
    this.utensils = utensils;
    this.recipeSteps = recipeSteps;
    this.servings = 1;
  }

  /**
   * Function that adds an Ingredient to the Recipe object.
   * 
   * @param newIngredient
   *          The Ingredient to be added
   * 
   */

  public void addIngredient(final Ingredient newIngredient)
  {
    ingredients.add(newIngredient);
  }

  /**
   * Function that adds a Utensil to the Recipe object.
   * 
   * @param newUtensil
   *          The Utensil to be added
   * 
   */

  public void addUtensil(final Utensil newUtensil)
  {
    utensils.add(newUtensil);
  }

  /**
   * Function that adds a RecipeStep to the Recipe object.
   * 
   * @param newRecipeStep
   *          RecipeStep to be added
   * 
   */

  public void addRecipeStep(final RecipeStep newRecipeStep)
  {
    recipeSteps.add(newRecipeStep);
  }

  /**
   * Function that returns an ArrayList of Ingredients contained in the Recipe object.
   * 
   * @return The ArrayList of Ingredients
   * 
   */

  public ArrayList<Ingredient> getIngredients()
  {
    return this.ingredients;
  }

  /**
   * Function that returns an ArrayList of Utensils contained in the Recipe object.
   * 
   * @return The ArrayList of Utensils
   * 
   */

  public ArrayList<Utensil> getUtensils()
  {
    return this.utensils;
  }

  /**
   * Function that returns an ArrayList of RecipeSteps contained in the Recipe object.
   * 
   * @return The ArrayList of RecipeSteps
   * 
   */

  public ArrayList<RecipeStep> getSteps()
  {
    return this.recipeSteps;
  }

  /**
   * Function that returns the servings of the Recipe object.
   * 
   * @return The servings of the Recipe
   * 
   */

  public int getServings()
  {
    return this.servings;
  }

  /**
   * Function that writes the data contained in the Recipe object to a text file.
   * 
   * @param filename
   *          Name of the file to write to
   * 
   * @throws IOException
   * 
   */

  public void write(final String filename) throws IOException
  {
    FileOutputStream out = new FileOutputStream(filename + RCP);
    ObjectOutputStream serializer = new ObjectOutputStream(out);
    serializer.writeObject(this);
    serializer.flush();
    out.close();

  }

  /**
   * Static function that creates a Recipe object from data contained in a text file.
   * 
   * @param filename
   *          The name of the file to read
   * 
   * @return A Recipe object containing the data in the text file
   * 
   * @throws IOException
   * 
   */

  public static Recipe open(final String filename) throws IOException
  {
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename + RCP));
    Recipe recipe;
    // System.out.println("[DEBUG] Reached open");
    try
    {
      recipe = (Recipe) in.readObject();
      // System.out.println("[DEBUG] Reached inside of open's try");
    }
    catch (ClassNotFoundException cnfe)
    {
      recipe = new Recipe(STRINGS.getString("ERROR_OPENING_FILE"));
    }
    in.close();

    return recipe;

  }

  /**
   * Static function that creates a Recipe object from data contained in a text file.
   * 
   * @param file
   *          The file to be read
   * 
   * @return A Recipe object containing the data in the text file
   * 
   * @throws IOException
   * 
   */

  public static Recipe open(final File file) throws IOException
  {
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
    Recipe recipe;
    // System.out.println("[DEBUG] Reached open");
    try
    {
      recipe = (Recipe) in.readObject();
      // System.out.println("[DEBUG] Reached inside of open's try");
    }
    catch (ClassNotFoundException cnfe)
    {
      recipe = new Recipe("name");
    }
    in.close();

    return recipe;

  }

  /**
   * Function that returns the name of the Recipe object.
   * 
   * @return The name of the Recipe
   * 
   */

  public String getName()
  {
    return name;
  }

  /**
   * Static function that returns an Ingredient if the Recipe object contains an Ingredient that has
   * a name matching with the given String. Returns the Ingredient if there is a match, or returns
   * null if there is none.
   * 
   * @param ingredientName
   *          Name of the Ingredient
   * @param r
   *          The recipe to search through
   * 
   * @return An Ingredient, or null
   * 
   */

  public static Ingredient ingredientFromString(final String ingredientName, final Recipe r)
  {
    for (Ingredient i : r.getIngredients())
    {
      if (i.getName().toLowerCase().equals(ingredientName.toLowerCase()))
      {
        return i;
      }
    }
    return null;
  }

  /**
   * Function that returns a Utensil if the Recipe object contains a Utensil with a name matching
   * the given String. Returns a Utensil if there is a match, or returns null if there is none.
   * 
   * @param utensilName
   *          Name of the Utensil to search for
   * 
   * @return A Utensil, or null
   * 
   */

  public Utensil utensilFromString(final String utensilName)
  {
    for (Utensil u : this.getUtensils())
    {
      if (u.getName().toLowerCase().equals(utensilName.toLowerCase()))
      {
        return u;
      }
    }
    return null;
  }

  /**
   * Function that removes any Ingredients contained in the Recipe object if they have a name
   * matching the ingredientName String.
   * 
   * @param ingredientName
   *          The String to compare to
   * 
   */

  public void removeIngredient(final String ingredientName)
  {
    ArrayList<Ingredient> ingredientsIterate = new ArrayList<>(this.ingredients); // stops
    // ConcurrentModificationException
    for (Ingredient i : ingredientsIterate)
    {
      if (i.getName().toLowerCase().equals(ingredientName.toLowerCase()))
      {
        this.ingredients.remove(i);
      }
    }
  }

  /**
   * Function that removes any Utensils contained in the Recipe object if they have a name matching
   * the utensilName String.
   * 
   * @param utensilName
   *          The String to compare to
   * 
   */

  public void removeUtensil(final String utensilName)
  {
    ArrayList<Utensil> utensilsIterate = new ArrayList<>(this.utensils); // stops
    // ConcurrentModificationException
    for (Utensil u : utensilsIterate)
    {
      if (u.getName().toLowerCase().equals(utensilName.toLowerCase()))
      {
        this.utensils.remove(u);
      }
    }
  }

  /**
   * Function that removes any RecipeSteps contained in the Recipe object if they have a name
   * matching the step String.
   * 
   * @param step
   *          The String to compare to
   * 
   */

  public void removeRecipeStep(final String step)
  {
    ArrayList<RecipeStep> recipeStepsIterate = new ArrayList<>(this.recipeSteps);
    ArrayList<Utensil> utensilIterate = new ArrayList<>(this.utensils);
    for (RecipeStep s : recipeStepsIterate)
    {
      if (s.toString().equals(step))
      {
        this.recipeSteps.remove(s);
        for (Utensil utensil : utensilIterate) // should remove the ingredient added to the utensil
        // by a step.
        {
          if (s.getDestinationUtensil().equals(utensil) && s.getActionType().equals("In"))
          {
            if (RecipeEditorController.isUpper(s.getSubject()))
            {
              ArrayList<Ingredient> ingredientsList = new ArrayList<>(
                  utensilFromString(s.getSubject().toLowerCase()).getIngredients());
              s.getDestinationUtensil().removeIngredients(ingredientsList);
            }
            else
            {
              s.getDestinationUtensil().removeIngredient(s.getSubject());
            }
          }
        }
      }
    }
  }

  /**
   * Function that returns true if the Recipe object contains an Ingredient with a name matching the
   * userIngredient String, or false if not.
   * 
   * @param userIngredient
   *          The String to compare to
   * 
   * @return A boolean
   * 
   */

  public boolean hasIngredient(final String userIngredient)
  {
    for (Ingredient i : this.ingredients)
    {
      String newUserIngredient = userIngredient.toLowerCase().trim();

      if (newUserIngredient.toLowerCase().equals(i.getName().toLowerCase()))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Function that changes the name value of the Recipe object to the newName String.
   * 
   * @param newName
   *          The String to replace the name with
   * 
   */

  public void setName(final String newName)
  {
    this.name = newName;
  }

  /**
   * Function that changes the servings value of the Recipe object to the newServings int.
   * 
   * @param newServings
   *          The int to replace servings with
   * 
   */

  public void setServings(final int newServings)
  {
    this.servings = newServings;
  }
}
