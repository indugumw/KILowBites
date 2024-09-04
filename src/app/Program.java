package app;

import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.gui.MainWindow;
import ingredient.Ingredient;

/**
 * The main class to run the KiLowBites program.
 * 
 * @author S23Team2C
 * @version 4/25/2023
 *
 */
public class Program
{
  /**
   * Main method to start product.
   * 
   * @param args
   *          list of arguments
   */
  public static void main(final String[] args)
  {
    setLookAndFeel();
    try
    {
      Ingredient.initAllIngredients();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        new MainWindow();
      }
    });
  }

  /**
   * sets the look and feel of the window.
   */
  private static void setLookAndFeel()
  {
    boolean done = false;
    try
    {
      UIManager.LookAndFeelInfo[] lfs = UIManager.getInstalledLookAndFeels();
      for (int i = 0; i < lfs.length && !done; i++)
      {
        if ("Nimbus".equals(lfs[i].getName()))
        {
          UIManager.setLookAndFeel(lfs[i].getClassName());
          done = true;
        }
      }

      if (!done)
      {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
      }
    }
    catch (ClassNotFoundException cnfe)
    {
    }
    catch (IllegalAccessException iae)
    {
    }
    catch (InstantiationException ie)
    {
    }
    catch (UnsupportedLookAndFeelException ulale)
    {
    }
  }
}
