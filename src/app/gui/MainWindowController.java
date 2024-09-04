package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.filechooser.FileNameExtensionFilter;

import exception.InstanceException;
import gui.editor.MealEditor;
import gui.editor.MealEditorController;
import gui.editor.RecipeEditor;
import gui.editor.RecipeEditorController;
import gui.search.MealSearchGUI;
import gui.search.RecipeSearchGUI;
import gui.tool.CalorieCalculatorWindow;
import gui.tool.UnitsConverterWindow;
import gui.viewer.ShoppingListViewer;
import meal.Meal;
import recipe.Recipe;
import utility.SysUtil;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * class for opening new windows.
 * 
 * @author Jackson Lofquist,
 * @version 4/25/2023
 *
 */
public class MainWindowController implements ActionListener
{
  public static final Locale LOCALE = Locale.getDefault();
  public static final ResourceBundle STRINGS = ResourceBundle.getBundle("app.gui.Strings",
      Locale.getDefault());
  private static final String SM = "SYSTEM_MESSAGE";
  private static final String EOF = "ERROR_OPENING_FILE";
  private static final String DF = "DATA_FILES";
  private static final String ERROR = "ERROR";
  private static final String IFT = "INVALID_FILE_TYPE";

  /**
   * Invoked when an action occurs.
   * 
   * @param evt
   *          the action performed
   */
  public void actionPerformed(final ActionEvent evt)
  {
    String order = evt.getActionCommand();
    if (order.equals(STRINGS.getString("EXIT")))
    {
      System.exit(0);
    }
    else if (order.equals(STRINGS.getString("UNITS_CONVERTER")))
    {
      try
      {
        UnitsConverterWindow.getUnitsConverterWindow();
      }
      catch (InstanceException ie)
      {
        JOptionPane.showMessageDialog(null, STRINGS.getString("ONE_CONVERTER"),
            STRINGS.getString(SM), JOptionPane.INFORMATION_MESSAGE);
      }
    }
    else if (order.equals(STRINGS.getString("RECIPE")))
    {
      new RecipeEditor(new RecipeEditorController());
    }
    else if (order.equals(STRINGS.getString("MEAL")))
    {
      new MealEditor(new MealEditorController());
    }
    else if (order.equals(STRINGS.getString("MEAL_SEARCH")))
    {
      new MealSearchGUI();
    }
    else if (order.equals(STRINGS.getString("RECIPE_SEARCH")))
    {
      new RecipeSearchGUI();
    }
    else if (order.equals(STRINGS.getString("CALORIE_CALCULATOR")))
    {
      try
      {
        CalorieCalculatorWindow.getCalorieCalculator();
      }
      catch (InstanceException ie)
      {
        JOptionPane.showMessageDialog(null, STRINGS.getString("ONE_CALCULATOR"),
            STRINGS.getString(SM), JOptionPane.INFORMATION_MESSAGE);
      }
    }
    else if (order.equals(STRINGS.getString("SHOPPING_LIST_RCP")))
    {
      FileNameExtensionFilter rcpFilter;
      JFileChooser fileChooser;
      Recipe recipe;

      rcpFilter = new FileNameExtensionFilter(STRINGS.getString(DF), "rcp");
      fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(SysUtil.getDirectoryFromRoot("res/recipe"));

      fileChooser.setFileFilter(rcpFilter);
      int result = fileChooser.showOpenDialog(null);
      if (result == JFileChooser.APPROVE_OPTION)
      {
        String path = fileChooser.getSelectedFile().getPath();
        String extension = rcpFilter.getExtensions()[0];
        if (path.endsWith(extension))
        {
          File recipeFile = fileChooser.getSelectedFile();
          try
          {
            recipe = Recipe.open(recipeFile);

            new ShoppingListViewer(recipe);
          }
          catch (IOException ioe)
          {
            JOptionPane.showMessageDialog(null, STRINGS.getString(EOF),
                STRINGS.getString(ERROR), JOptionPane.ERROR_MESSAGE);
          }
        }
        else
        {
          JOptionPane.showMessageDialog(null, STRINGS.getString(IFT),
              STRINGS.getString(ERROR), JOptionPane.ERROR_MESSAGE);
        }
      }
    }
    else if (order.equals(STRINGS.getString("SHOPPING_LIST_MEL")))
    {
      FileNameExtensionFilter melFilter;
      JFileChooser fileChooser;
      Meal meal;

      melFilter = new FileNameExtensionFilter(STRINGS.getString(DF), "mel");
      fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(SysUtil.getDirectoryFromRoot("res/meal"));

      fileChooser.setFileFilter(melFilter);
      int result = fileChooser.showOpenDialog(null);
      if (result == JFileChooser.APPROVE_OPTION)
      {
        String path = fileChooser.getSelectedFile().getPath();
        String extension = melFilter.getExtensions()[0];
        if (path.endsWith(extension))
        {
          String name = path.substring(0, path.length() - 4);
          try
          {
            meal = Meal.open(name);
            // ystem.out.println("[DEBUG] Meal Name: " + meal.getName());

            new ShoppingListViewer(meal, fileChooser.getSelectedFile());
          }
          catch (IOException ioe)
          {
            JOptionPane.showMessageDialog(null, STRINGS.getString(EOF),
                STRINGS.getString(ERROR), JOptionPane.ERROR_MESSAGE);
          }
        }
        else
        {
          JOptionPane.showMessageDialog(null, STRINGS.getString(IFT),
              STRINGS.getString(ERROR), JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }
}
