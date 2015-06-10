import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * The Class Shape.
 * 
 * The Shape superclass contains all "common" features and data of shapes. It
 * stores the x and y integer locations for each shape along with the color.
 * Several of the methods, including getArea() and setSize() are overridden in
 * nearly all subclasses.
 * 
 * @author Spencer Niemi
 * @version 1.0 Class: CSS162 Winter 2014 Instructor: Rob Nash
 */
class Shape extends Object implements Cloneable, Serializable, Comparable {

	/** The x coordinate. */
	private int x = 0;

	/** The y coordinate. */
	private int y = 0;

	/** The color. */
	private Color color = Color.GREEN; // Defaults to green

	/** Should shape be filled. */
	private boolean isFilled = false;

	/**
	 * Instantiates a new shape.
	 * 
	 * @param a
	 *            the x
	 * @param b
	 *            the y
	 * @param fill
	 *            the fill
	 */
	public Shape(int a, int b, boolean fill) {
		x = a;
		y = b;
		isFilled = fill;
	}

	/**
	 * Default no argument constructor that instantiates a new shape, at the
	 * default location of (0,0).
	 */
	public Shape() {
		x = 0;
		y = 0;
	}

	/**
	 * Gets the area of any given shape. Should be overridden in any subclass
	 * that extends Shape to provide a real area value.
	 * 
	 * @return the area
	 */
	public double getArea() {
		return -1;
	}

	/**
	 * Draws the appropriate shape, again, this method should be overridden in
	 * an class that extends Shape to provide a legitimate way to draw each
	 * shape.
	 * 
	 * @param g
	 *            the g
	 */
	public void draw(Graphics g) {
	}

	/**
	 * Gets the x coordinate.
	 * 
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y coordinate.
	 * 
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the x to a new value.
	 * 
	 * @param newX
	 *            the new x
	 */
	public void setX(int newX) {
		x = newX;
	}

	/**
	 * Sets the y to a new value.
	 * 
	 * @param newY
	 *            the new y
	 */
	public void setY(int newY) {
		y = newY;
	}

	/**
	 * Sets the size, this method needs to be overridden in any subclasses.
	 * 
	 * @param size
	 *            the new size
	 */
	public void setSize(int size) {
	}

	/**
	 * Sets the color.
	 * 
	 * @param theColor
	 *            the new color
	 */
	public void setColor(Color theColor) {
		color = theColor;
	}

	/**
	 * Gets the current color.
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Clones the shape object
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Needs to be implemented in any subclasses, compares shapes.
	 */
	public int compareTo(Object o) { // Should NEVER be used
		return 0;
	}

	/**
	 * Checks if the shape should be filled.
	 * 
	 * @return true if should be filled, false if not
	 */
	public boolean getIsFilled() {
		return isFilled;
	}

	/**
	 * Sets the isFilled value to value of fill
	 * 
	 * @param fill
	 *            the new value
	 */
	public void setIsFilled(boolean fill) {
		isFilled = fill;
	}
}