import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;

/**
 * The Class Application is where the main method resides, and ties all other
 * relevant classes together. It has a ButtonPanel for the control buttons, a
 * DrawPanel for the shapes to be drawn in, along with a "brush", or the current
 * shape being drawn, along with a color selection which defaults to green if a
 * user does not choose one. Application extends JFrame to implement the GUI.
 * 
 * @author Spencer Niemi
 * @version 1.0 Class: CSS162 Winter 2014 Instructor: Rob Nash
 */
public class Application extends JFrame {

	/** The current brush that the user has selected, default is square. */
	private Shape currentBrush;

	/** The buttons as laid out and created in ButtonPanel. */
	private ButtonPanel buttons;

	/** The drawing panel where users will be able to draw shapes. */
	private DrawPanel draw;

	/** The current color chosen by the user, the default is green. */
	Color color;

	/**
	 * Instantiates a new application with the default settings, along with
	 * instantiating all of the needed objects and panels for paint to run.
	 */
	public Application() {
		// Call the super constructor to generate a JFrame
		super("Paint");
		// Setup the window
		Container contentPane = super.getContentPane();
		BorderLayout layout = new BorderLayout();
		contentPane.setLayout(layout);
		draw = new DrawPanel(this); // Create the drawing panel
		buttons = new ButtonPanel(this); // Create the button panel
		buttons.setPreferredSize(new Dimension(200, 600)); // set size of the
															// buttons
		draw.setPreferredSize(new Dimension(400, 600)); // Set size of the
														// drawing panel
		draw.setBackground(Color.WHITE); // Change background to white to
											// differentiate from buttons
		contentPane.add(buttons, BorderLayout.WEST); // Buttons should be on the
														// left
		contentPane.add(draw, BorderLayout.CENTER);
		color = Color.GREEN; // Default color

		setSize(600, 600); // Set size of window to 600x600 by default
		pack(); // Make everything fit right in the frame (buttons)
		setLocationRelativeTo(null);
		setVisible(true); // After everything is setup make the window visible
							// to the user
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set the default brush to the square if the user doesn't choose one.
		currentBrush = new Square(0, 0, 200, false);

	}

	/**
	 * The main method, only contains one line, to start running the program.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Application app = new Application();
	}

	/**
	 * Sets the brush to whatever shape the user chooses, this is selected in
	 * the ButtonPanel.
	 * 
	 * @param newShape
	 *            the new brush
	 */
	public void setBrush(Shape newShape) {
		currentBrush = newShape;
		setColor(color);
	}

	/**
	 * Gets the current brush, used for drawing the shape.
	 * 
	 * @return the brush
	 */
	public Shape getBrush() {
		return currentBrush;
	}

	/**
	 * Adds the shape to the ArrayList of shapes by cloning the current shape
	 * and calling addShape in the DrawPanel. The Application is repainted after
	 * adding the shape so that the user can see the changes they made.
	 */
	public void addShape() {
		try {
			draw.addShape((Shape) currentBrush.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		draw.sort();
		repaint();
	}

	/**
	 * Sets the color to whatever color the user chose when they hit the
	 * choose color button in the ButtonPanel.
	 * 
	 * @param toSet
	 *            the new color to set
	 */
	public void setColor(Color toSet) {
		color = toSet;
		currentBrush.setColor(color);
	}

	/**
	 * Returns the current color the user has chosen.
	 * 
	 * @return the color the user chose
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Calls the save function in DrawPanel, passing the filename the user chose
	 * along with it so the program knows where to save the file.
	 * 
	 * @param toSave
	 *            the file to save
	 */
	public void save(File toSave) {
		draw.save(toSave);
	}

	/**
	 * Calls the load function in DrawPanel, passing the filename the user chose
	 * along with it so the program knows where to load the file from, and which
	 * file.
	 * 
	 * @param toLoad
	 *            the file to load
	 */
	public void load(File toLoad) {
		draw.load(toLoad);
		repaint();
	}

	/**
	 * Calls the empty function in DrawPanel and then repaints the window so
	 * user can see the changes..
	 */
	public void empty() {
		draw.empty();
		repaint();
	}

	/**
	 * Call the undo function in DrawPanel so that the last shape the user added
	 * to the screen is removed. Repaint the window so user can see the changes.
	 */
	public void undo() {
		draw.undo();
		repaint();
	}

	/**
	 * Call the redo function in DrawPanel so that the last shape the user
	 * removed from the screen is added back. Repaint the window so user can see
	 * the changes.
	 */
	public void redo() {
		draw.redo();
		repaint();
	}

	/**
	 * Sets the isFilled boolean to whatever value is provided.
	 * 
	 * @param fill
	 *            the new value for isFilled
	 */
	public void setIsFilled(boolean fill) {
		draw.setIsFilled(fill);
		currentBrush.setIsFilled(fill);
	}

	/**
	 * Gets the isFilled.
	 * 
	 * @return the isFilled value
	 */
	public boolean getIsFilled() {
		return draw.getIsFilled();
	}

}
