import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 * The Class Square is a Shape and provides all required methods of Shape. It
 * implements Cloneable, Serializable, and Comparable to fulfill the
 * requirements of Shape
 * 
 * @author Spencer Niemi
 * @version 1.0 Class: CSS162 Winter 2014 Instructor: Rob Nash
 */
class Square extends Shape implements Cloneable, Serializable, Comparable {

	/** The side length. */
	private int side;

	/**
	 * Instantiates a new square
	 * 
	 * @param a
	 *            the x value
	 * @param b
	 *            the y value
	 * @param s
	 *            the side length
	 * @param fill
	 *            whether or not shape should be filled
	 */
	public Square(int a, int b, int s, boolean fill) {
		super(a, b, fill); // we explicitly call a superclass constructor that
							// takes 2
							// ints
		side = s;
	}

	/**
	 * Returns the area of the shape, calculated by side*side, since it is a
	 * square.
	 * 
	 * @return double the area of the shape
	 */
	@Override
	public double getArea() {
		return side * side;
	}

	/**
	 * Clones the object, used when adding a new shape to the DrawPanel
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Changes the size of the object, since this is a square, we simply change
	 * the side length.
	 */
	public void setSize(int size) {
		side = size;
	}

	/**
	 * Gets the side length.
	 * 
	 * @return the side length
	 */
	public double getSide() {
		return side;
	}

	/**
	 * Gets the perimeter.
	 * 
	 * @return the perimeter
	 */
	public double getPerimeter() {
		return 4 * side;
	}

	/**
	 * Draws the shape onto the panel using Graphics2D. Reads the value for
	 * isFilled in superclass to determine which drawing method to use.
	 */
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		final int x = getX();
		final int y = getY();

		g2d.setColor(getColor());
		if (getIsFilled()) {
			g2d.fillRect(x, y, side, side);
		} else {
			g2d.drawRect(x, y, side, side);
		}
	}

	/**
	 * compares two shapes using the area as the relative metric for
	 * comparisons.
	 * 
	 * @param o
	 *            the o
	 * @return int negative if smaller, positive if larger, 0 if the same.
	 */
	@Override
	public int compareTo(Object o) {
		if (o == null || !(o instanceof Shape)) { // Make sure the object is a
													// valid shape
			// Throw exception if object not valid
			throw new RuntimeException("Invalid Shape");
		} else {
			// Cast the Object to a shape
			Shape that = (Shape) o;
			return (int) (this.getArea() - that.getArea());
		}
	}
}