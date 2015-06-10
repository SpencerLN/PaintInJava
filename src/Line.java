import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 * The Class Line.
 * 
 * @author Spencer Niemi
 * @version 1.0 Class: CSS162 Winter 2014 Instructor: Rob Nash
 */
class Line extends Shape implements Cloneable, Serializable, Comparable {

	/** The length. */
	private int length;

	/** The x2. */
	private int x2;

	/** The y2. */
	private int y2;

	/**
	 * Instantiates a new line.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param x2
	 *            the x2 coordinate
	 * @param y2
	 *            the y2 coordinate
	 * @param fill
	 *            the boolean indicating whether or not the shape should be filled
	 */
	public Line(int x, int y, int x2, int y2, boolean fill) {
		super(x, y, fill); // we explicitly call a superclass constructor that
							// takes 2
							// ints
		this.setX2(x2);
		this.setY2(y2);

		length = (int) Math.sqrt(Math.abs((x - x2)) * Math.abs((x - x2))
				+ Math.abs((y - y2)) * Math.abs((y - y2))); // Uses distance
															// between two
															// points to
															// determine the
															// length
	}

	/**
	 * Returns the area of the shape, since it is a line, it is always just the
	 * length times 1
	 */
	public double getArea() {
		return length;
	}

	/**
	 * Clones the object, used when drawing onto the panel
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * sets the size of the object based on drag and draw
	 */
	public void setSize(int size) {
		length = size;
	}

	/**
	 * Gets the side length.
	 * 
	 * @return the side length
	 */
	public double getSide() {
		return length;
	}

	/**
	 * Gets the perimeter.
	 * 
	 * @return the perimeter
	 */
	public double getPerimeter() {
		return 4 * length;
	}

	/**
	 * Gets the x2.
	 * 
	 * @return the x2
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * Sets the x2.
	 * 
	 * @param x2
	 *            the new x2
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}

	/**
	 * Gets the y2.
	 * 
	 * @return the y2
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * Sets the y2.
	 * 
	 * @param y2
	 *            the new y2
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}

	/**
	 * Draws the shape onto the panel using Graphics2D
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		final int x = getX();
		final int y = getY();

		g2d.setColor(getColor());

		g2d.drawLine(x, y, getX2(), getY2());
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