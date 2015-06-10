import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The Class DrawPanel.
 * 
 * @author Spencer Niemi
 * @version 1.0 Class: CSS162 Winter 2014 Instructor: Rob Nash
 */
public class DrawPanel extends JPanel implements MouseListener {

	/** The my shapes. */
	private java.util.ArrayList<Shape> myShapes = new ArrayList<Shape>();

	/**
	 * The owner, the Application that instantiated the DrawPanel, used so we
	 * can call methods in the owner.
	 */
	private Application owner;

	/** The undo stack. */
	private Stack<Shape> undoStack = new Stack<Shape>();

	/** The original x. */
	private int origX;

	/** The original y. */
	private int origY;

	/** The Constant SIZE, a default value, to prevent user error.... */
	public static final int SIZE = 50; // default size is 50

	/**
	 * The isCleared boolean, true if the user has cleared the drawing window,
	 * false if user has not.
	 */
	private boolean isCleared;

	/** The isFilled boolean, true if the shape should be filled, false if not. */
	private boolean isFilled = false;

	/**
	 * Instantiates a new draw panel and adds a MouseListener so we can know
	 * where the user clicks.
	 * 
	 * @param caller
	 *            the caller
	 */
	public DrawPanel(Application caller) {
		owner = caller;
		this.addMouseListener(this);
	}

	/**
	 * Paint draws each shape onto the screen in the order it is listed in
	 * myShapes, they are sorted largest to smallest so that all shapes are
	 * displayed.
	 * 
	 * @param g
	 *            the Graphics object
	 */
	public void paint(Graphics g) {
		super.paint(g); // don't remove - required for GUI widgets to draw
						// correctly

		for (int i = 0; i < myShapes.size(); i++) {
			myShapes.get(i).draw(g);
		}
	}

	/**
	 * Adds the shape to the ArrayList list of shapes, the method also sorts the
	 * shapes from largest to smallest.
	 * 
	 * @param theShape
	 *            the the shape
	 */
	public void addShape(Shape theShape) {
		myShapes.add(theShape);
		sort();
	}

	/**
	 * When the mouse is pressed, record the x and y coordinates so we know
	 * where the user started dragging.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		origX = e.getX();
		origY = e.getY();
	}

	/**
	 * When the mouse is released calculate the distance that the user dragged
	 * the mouse and modify the shape appropriately so it resembles the
	 * approximate size the user indicated.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		int distance = (int) Math.sqrt((origX - e.getX()) * (origX - e.getX())
				+ (origY - e.getY()) * (origY - e.getY()));

		Shape toAdd = null;

		try {
			// Clone the current brush so we can modify it then add it to the
			// list
			toAdd = (Shape) owner.getBrush().clone();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
		if (origX - e.getX() <= 0) { // See which way the user dragged the mouse
			toAdd.setX(origX);
			toAdd.setY(origY);
		} else {
			toAdd.setX(e.getX());
			toAdd.setY(e.getY());
		} // Shape specific actions, used if the simple distance is not enough
		if (toAdd.getClass().getSimpleName().equals("Rectangle")) {
			int width = Math.abs(origX - e.getX());
			int length = Math.abs(origY - e.getY());
			if (width == 0 || length == 0) {
				return;
			}
			Rectangle theRec = (Rectangle) toAdd;
			theRec.setWidth(width);
			theRec.setLength(length);
			toAdd = theRec;
			addShape(toAdd);
			owner.repaint();
		} else if (toAdd.getClass().getSimpleName().equals("Line")) {
			if (distance <= 0) {
				return;
			}
			Line theLine = (Line) toAdd;
			if (e.getX() > origX) {
				theLine.setX2(e.getX());
				theLine.setY2(e.getY());
			} else {
				theLine.setX2(origX);
				theLine.setY2(origY);
				theLine.setX(e.getX());
				theLine.setY(e.getY());
			}
			toAdd = theLine;
			addShape(toAdd);
			owner.repaint();
		} else {
			// If the user didn't move the mouse then we can assume they didn't
			// really want to draw a shape and just accidently clicked
			if (distance <= 0) {
				return;
			}
			toAdd.setSize(distance);

			addShape(toAdd);
			owner.repaint();
		}
	}

	/**
	 * Saves the users picture to a .ser file. Takes advantage of the
	 * serializable interface.
	 * 
	 * @param toSave
	 *            the file where we should shave the object
	 */
	public void save(File toSave) {
		// Create output streams for the object
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(toSave, false);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(myShapes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Allows a user to load a previously saved image back into the drawing
	 * window.
	 * 
	 * @param toLoad
	 *            the file to load
	 */
	public void load(File toLoad) {
		owner.empty();
		ObjectInputStream ois = null;
		FileInputStream streamIn = null;

		try {
			streamIn = new FileInputStream(toLoad);
			ois = new ObjectInputStream(streamIn);
			Object input = ois.readObject();
			if (input instanceof ArrayList<?>) {
				ArrayList<Shape> readCase = (ArrayList<Shape>) input;
				for (int i = 0; i < readCase.size(); i++) {
					myShapes.add(readCase.get(i));
				}
			} else {
				throw new ApplicationException("The input file was not valid");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
					streamIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Empties the ArrayList of all shapes, also sets isCleared to true in case
	 * user wants to undo the clear. This is accomplished by pushing all shapes
	 * to the undoStack before clearing the list of shapes.
	 */
	public void empty() {
		for (int i = 0; i < myShapes.size(); i++) {
			undoStack.push(myShapes.get(i));
		}
		myShapes.clear();
		isCleared = true;
	}

	/**
	 * Undos the last shape that was added, if the user has cleared the panel,
	 * it will add back ALL shapes that have been drawn during that session.
	 */
	public void undo() {
		if (isCleared) {
			while (!undoStack.isEmpty()) {
				myShapes.add(undoStack.pop());
			}
			isCleared = false;
		} else {
			if (myShapes.size() <= 0) {
				throw new ApplicationException("Nothing to redo");
			} else {
				undoStack.push(myShapes.remove(myShapes.size() - 1));
			}
		}

	}

	/**
	 * Redo, after a shape is removed, allows the user to re-add it..
	 */
	public void redo() {
		if (undoStack.size() <= 0) {
			throw new ApplicationException("Nothing to redo");
		} else {
			myShapes.add(undoStack.pop());
		}
	}

	/**
	 * Sorts the shapes in myShapes by area, largest to smallest. Using an
	 * insertion sort.
	 */
	public void sort() {
		int j;
		for (int i = 1; i < myShapes.size(); i++) {
			Shape temp = myShapes.get(i);
			j = i;
			while (j > 0 && temp.compareTo(myShapes.get(j - 1)) >= 0) {
				myShapes.set(j, myShapes.get(j - 1));
				j--;
			}
			myShapes.set(j, temp);
		}
	}

	/**
	 * Gets the value of isFilled.
	 * 
	 * @return the isFilled value.
	 */
	public boolean getIsFilled() {
		return isFilled;
	}

	/**
	 * Sets isFilled to value of fill.
	 * 
	 * @param fill
	 *            the value to set to
	 */
	public void setIsFilled(boolean fill) {
		isFilled = fill;
	}

	/**
	 * Areas, used during debugging, utility method, has potential later use so
	 * leaving in the class.
	 * 
	 * @return the string
	 */
	public String areas() {
		String retVal = "";
		for (int i = 0; i < myShapes.size(); i++) {

			retVal += "[" + myShapes.get(i).getArea() + "]";
		}

		return retVal;
	}

	// Methods required by MouseListener

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}
}