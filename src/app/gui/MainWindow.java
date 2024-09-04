package app.gui;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JWindow;

import static app.gui.MainWindowController.STRINGS;

/**
 * Main window class.
 * 
 * @author Jackson Lofquist
 * @version 2/29/23
 */
public class MainWindow extends JWindow
{
  private static final long serialVersionUID = 1L;

  /**
   * constructs the main window.
   */
  public MainWindow()
  {
    JMenu file, edit, search, view, tools, configure, help;
    JMenuItem fItem, eItem, sItem, vItem, tItem, cItem, hItem;
    JLabel displayField;

    JFrame frame = new JFrame(STRINGS.getString("KILOW_MAIN_WINDOW"));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = (JPanel) frame.getContentPane();
    panel.setLayout(new BorderLayout());
    ImageIcon logoImageIcon = new ImageIcon(
        getClass().getClassLoader().getResource("gui/image/logo.png"));
    Image logoImage = logoImageIcon.getImage();
    logoImage = logoImage.getScaledInstance(225, 78, Image.SCALE_SMOOTH);
    logoImageIcon = new ImageIcon(logoImage);
    displayField = new JLabel(logoImageIcon);
    frame.add(displayField);
    MainWindowController controller = new MainWindowController();
    JMenuBar mBar = new JMenuBar();

    frame.setJMenuBar(mBar);
    file = new JMenu(STRINGS.getString("FILE"));
    mBar.add(file);
    fItem = new JMenuItem(STRINGS.getString("EXIT"));
    file.add(fItem);
    fItem.addActionListener(controller);
    edit = new JMenu(STRINGS.getString("EDIT"));
    mBar.add(edit);
    eItem = new JMenuItem(STRINGS.getString("RECIPE"));
    eItem.addActionListener(controller);
    edit.add(eItem);
    eItem = new JMenuItem(STRINGS.getString("MEAL"));
    edit.add(eItem);
    eItem.addActionListener(controller);
    search = new JMenu(STRINGS.getString("SEARCH"));
    mBar.add(search);
    sItem = new JMenuItem(STRINGS.getString("RECIPE_SEARCH"));
    sItem.addActionListener(controller);
    search.add(sItem);
    sItem = new JMenuItem(STRINGS.getString("MEAL_SEARCH"));
    search.add(sItem);
    sItem.addActionListener(controller);
    view = new JMenu(STRINGS.getString("VIEW"));
    mBar.add(view);
    vItem = new JMenuItem(STRINGS.getString("SHOPPING_LIST_RCP"));
    view.add(vItem);
    vItem.addActionListener(controller);
    vItem = new JMenuItem(STRINGS.getString("SHOPPING_LIST_MEL"));
    view.add(vItem);
    vItem.addActionListener(controller);
    tools = new JMenu(STRINGS.getString("TOOLS"));
    mBar.add(tools);
    tItem = new JMenuItem(STRINGS.getString("UNITS_CONVERTER"));
    tools.add(tItem);
    tItem.addActionListener(controller);
    tItem = new JMenuItem(STRINGS.getString("CALORIE_CALCULATOR"));
    tItem.addActionListener(controller);
    tools.add(tItem);
    configure = new JMenu(STRINGS.getString("CONFIGURE"));
    mBar.add(configure);
    cItem = new JMenuItem(STRINGS.getString("PREFERENCES"));
    configure.add(cItem);
    cItem = new JMenuItem(STRINGS.getString("SHORTCUTS"));
    configure.add(cItem);
    cItem = new JMenuItem(STRINGS.getString("NUTRITION"));
    configure.add(cItem);
    help = new JMenu(STRINGS.getString("HELP"));
    mBar.add(help);
    hItem = new JMenuItem(STRINGS.getString("ABOUT"));
    help.add(hItem);
    hItem = new JMenuItem(STRINGS.getString("USER_GUIDE"));
    help.add(hItem);
    frame.setSize(500, 300);
    configure.setVisible(false);
    help.setVisible(false);
    frame.setVisible(true);
  }
}
