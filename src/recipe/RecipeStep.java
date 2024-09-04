package recipe;

import java.io.Serializable;

import gui.editor.RecipeEditorController;

import static app.gui.MainWindowController.STRINGS;

/**
 * Class that represents the steps of a recipe.
 * 
 * @author Myan Indugula
 * 
 */
public class RecipeStep implements Serializable
{

  private static final long serialVersionUID = 1L;
  private static final String NA = "N/A";
  private static final String CONTENTS = "THE_CONTENTS_OF";
  private static final String SPACE = " ";
  private static final String THE = " the ";
  private String action;
  private String subject;
  private String actionType;
  private Utensil destinationUtensil = new Utensil(STRINGS.getString(NA));
  private String details;

  /**
   * Constructs a new RecipeStep object.
   * 
   * @param action
   *          the action to be performed in this step
   * @param subject
   *          the subject on which the action will be performed
   * @param destinationUtensil
   *          the utensil to which the subject will be moved after the action is performed
   * @param actionType
   *          the type of action to be performed
   * @param details
   *          additional details about this step
   */
  public RecipeStep(final String action, final String subject, final Utensil destinationUtensil,
      final String actionType, final String details)
  {
    this.action = action;
    this.subject = subject;
    this.actionType = actionType;
    this.destinationUtensil = destinationUtensil;
    this.details = details;
  }

  /**
   * Constructs a new RecipeStep object.
   * 
   * @param action
   *          the action to be performed in this step
   * @param subject
   *          the subject on which the action will be performed
   * @param destinationUtensil
   *          the name of the utensil to which the subject will be moved after the action is
   *          performed
   * @param details
   *          additional details about this step
   */
  public RecipeStep(final String action, final String subject, final String destinationUtensil,
      final String details)
  {
    this.action = action;
    this.subject = subject;
    this.destinationUtensil.setName(destinationUtensil);
    this.details = details;
  }

  /**
   * Returns the action attribute.
   * 
   * @return the action
   */
  public String getAction()
  {
    return action;
  }

  /**
   * Sets the action attribute to the given value.
   * 
   * @param action
   *          The given action
   * 
   */
  public void setAction(final String action)
  {
    this.action = action;
  }

  /**
   * Returns the subject attribute.
   * 
   * @return the subject
   * 
   */
  public String getSubject()
  {
    return subject;
  }

  /**
   * Sets the subject attribute to the given value.
   *
   * @param subject
   *          The given subject
   * 
   */
  public void setSubject(final String subject)
  {
    this.subject = subject;
  }

  /**
   * Returns the destinationUtensil attribute.
   * 
   * @return the utensil destination
   * 
   */
  public Utensil getDestinationUtensil()
  {
    return destinationUtensil;
  }

  /**
   * Sets the destinationUtensil attribute to the given value.
   * 
   * @param destinationUtensil
   *          The given destination utensil
   * 
   */
  public void setDestinationUtensil(final Utensil destinationUtensil)
  {
    this.destinationUtensil = destinationUtensil;
  }

  // /**
  // * Returns the time attribute
  // *
  // */
  // public String getTime() {
  // return time;
  // }
  // //TO-DO - RECIPE STEP TIMES
  // /**
  // * Sets the time attribute to the given value
  // *
  // * @param time The given time
  // *
  // */
  // public void setTime(String time) {
  // this.time = time;
  // }

  /**
   * Returns a String representation of a Recipe step.
   * 
   * @return the formated string
   * 
   */
  public String toString()
  {
    if (RecipeEditorController.isUpper(subject))
    {
      if (destinationUtensil.getName().equals(STRINGS.getString(NA)))
        return String.format("%s %s %s", this.action, STRINGS.getString(CONTENTS).toLowerCase(),
            this.subject.toLowerCase());
      else
        return this.action + STRINGS.getString(CONTENTS).toLowerCase() + this.subject.toLowerCase()
            + SPACE + this.actionType.toLowerCase() + THE
            + this.destinationUtensil.toString().toLowerCase() + SPACE + this.details.toLowerCase();
    }
    else
    {
      return this.action + " " + STRINGS.getString("THE").toLowerCase() + this.subject.toLowerCase()
          + SPACE + this.actionType.toLowerCase() + THE
          + this.destinationUtensil.toString().toLowerCase() + SPACE + this.details.toLowerCase();
    }
  }

  /**
   * Returns the type of action to be performed in this step.
   * 
   * @return the type of action to be performed in this step
   */
  public String getActionType()
  {
    return actionType;
  }
}
