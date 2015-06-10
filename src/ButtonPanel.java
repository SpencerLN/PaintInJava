import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The Class ButtonPanel.
 * 
 * @author Spencer Niemi
 * @version 1.0 Class: CSS162 Winter 2014 Instructor: Rob Nash
 */
public class ButtonPanel extends JPanel implements ActionListener {

	/**
	 * Owner is the Application that called this ButtonPanel, this allows us to
	 * run any methods from Application directly.
	 */
	Application owner;

	/** The rectangle button. */
	JButton rectangleButton = new JButton("Rectangle");

	/** The square button. */
	JButton squareButton = new JButton("Square");

	/** The circle button. */
	JButton circleButton = new JButton("Circle");

	/** The choose color button. */
	JButton chooseColor = new JButton("Choose Color");

	/** The save button. */
	JButton save = new JButton("Save");

	/** The load button. */
	JButton load = new JButton("Load");

	/** The clear button. */
	JButton clear = new JButton("Clear");

	/** The undo button. */
	JButton undo = new JButton("Undo");

	/** The redo button. */
	JButton redo = new JButton("Redo");

	/** The line button. */
	JButton line = new JButton("Line");

	/**
	 * The tofill button group, makes sure that only one radio button is
	 * selected at any one time.
	 */
	ButtonGroup tofill = new ButtonGroup();

	/** The fill button. */
	JRadioButton fill = new JRadioButton("Fill");

	/** The nofill button. */
	JRadioButton nofill = new JRadioButton("No Fill");

	/**
	 * Instantiates a new button panel.
	 * 
	 * @param caller
	 *            the Object that called the ButtonPanel, this allows us to call
	 *            methods in the caller directly.
	 */
	public ButtonPanel(Application caller) {
		owner = caller;

		setLayout(new GridLayout(6, 2)); // Use a grid layout for the buttons
		// Add each button to the panel
		add(squareButton);
		add(circleButton);
		add(rectangleButton);
		add(line);
		add(save);
		add(load);
		add(chooseColor);
		add(clear);
		add(undo);
		add(redo);
		tofill.add(fill);
		tofill.add(nofill);
		add(fill);
		add(nofill);

		// Add a listener for each button so that we know when it is pressed.
		squareButton.addActionListener(this);
		circleButton.addActionListener(this);
		rectangleButton.addActionListener(this);
		chooseColor.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		clear.addActionListener(this);
		undo.addActionListener(this);
		redo.addActionListener(this);
		line.addActionListener(this);
		fill.addActionListener(this);
		nofill.addActionListener(this);
	}

	/**
	 * Method is called whenever a button is pressed, uses if statements to find
	 * which button was pressed and call the appropriate actions on it.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == squareButton) { // If the square button set brush
												// to square
			owner.setBrush(new Square(0, 0, 50, owner.getIsFilled())); // Default
																		// location
																		// and
																		// size

		} else if (e.getSource() == circleButton) { // If the circle button set
													// brush to circle
			owner.setBrush(new Circle(0, 0, 50, owner.getIsFilled())); // Default
																		// location
																		// and
																		// size
		} else if (e.getSource() == rectangleButton) { // If the rectangle
														// button
														// set brush to
														// rectangle
			owner.setBrush(new Rectangle(0, 0, 50, 80, owner.getIsFilled())); // Default
																				// location
																				// and
			// size if not
			// specified already
		} else if (e.getSource() == undo) { // Listen for the undo button, if it
											// is pressed call the undo method
											// in Application to remove the last
											// change.
			owner.undo();
		} else if (e.getSource() == redo) { // Listen for the redo button, if it
											// is pressed call the redo method
											// in Application to redo the last
											// undo.
			owner.redo();
		} else if (e.getSource() == line) { // If the user chooses the line, set
											// the brush to the line with the
											// default location and size.
			owner.setBrush(new Line(0, 0, 20, 20, owner.getIsFilled()));
		} else if (e.getSource() == chooseColor) { // Allow user to choose the
													// color
			Color selectedColor = JColorChooser.showDialog(this,
					"Pick a Color", Color.GREEN); // Open the color chooser so
													// the user can choose a
													// color, default to green.

			if (selectedColor != null) { // Set color of shape
				owner.setColor(selectedColor);
			} else { // Throw exception if something is wrong with color they
						// chose
				throw new ApplicationException("You didn't choose a valid color");
			}
		} else if (e.getSource() == save) {
			save();

		} else if (e.getSource() == load) { // If user wants to load a saved
											// file, call the load() method.
			load();
		} else if (e.getSource() == clear) { // If user wants to empty the
												// frame, call empty() in
												// Application.
			owner.empty();
		} else if (e.getSource() == nofill) {
			owner.setIsFilled(false);
		} else if (e.getSource() == fill) {
			owner.setIsFilled(true);
		}

	}

	/**
	 * When the user wants to save bring up the save file dialog, so they can
	 * choose a location and file name. Defaults to picture.ser, to at least
	 * give the user an idea of how they should be saving the file. We then call
	 * the save function in Application and pass the chosen filename and
	 * location.
	 */
	public void save() {
		JFileChooser chooser = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"SER Files", "ser"); // Default the filename to picture.ser, so
										// the user knows what to save it as.
		chooser.setFileFilter(filter);
		chooser.setSelectedFile(new File("picture.ser"));
		int returnVal = chooser.showSaveDialog(getParent());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			owner.save(chooser.getSelectedFile()); // Call the save method in
													// Application and provide
													// the users chosen name and
													// location

		}
	}

	/**
	 * When the user wants to load bring up the open file dialog, so they can
	 * choose a location and file name. Defaults to picture.ser, to at least
	 * give the user an idea of how they should be loading the file. We then
	 * call the load function in Application and pass the chosen filename and
	 * location.
	 */
	public void load() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"SER Files", "ser");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(getParent());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			owner.load(chooser.getSelectedFile()); // Caller the load method in
			// Application and provide
			// the users chosen name and
			// location
		}
	}
}
