// Import necessary libraries
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Rectangle contains data related to a Rectangle. Rectangle extends
 * Shape.
 * 
 * Class: CSS162 Winter 2014
 * 
 * Instructor: Rob Nash
 * 
 * @author Spencer Niemi
 * @version 1.0
 */
class Rectangle extends Shape implements Cloneable, Serializable, Comparable {
	// Instance variables for width and length
	/** The width. */
	private double width;

	/** The length. */
	private double length;

	/**
	 * Instantiates a new rectangle.
	 *
	 * @param a            the a
	 * @param b            the b
	 * @param l            the l
	 * @param w            the w
	 * @param fill the fill
	 */
	public Rectangle(int a, int b, int l, int w, boolean fill) { // Constructor for Rectangle
													// class
		super(a, b, fill); // we explicitly call a superclass constructor that takes 2
						// ints
		// Set width and length to the specified input
		setWidth(w);
		setLength(l);
	}

	/**
	 * Sets the width.
	 * 
	 * @param n
	 *            the new width
	 */
	public void setWidth(int n) { // Enforces width >= 0
		if (n > 0) {
			width = n;
		} else {
			System.err.println("Cannot be less than 0");
			System.exit(-1);
		}
	}

	/**
	 * Sets the length.
	 * 
	 * @param n
	 *            the new length
	 */
	public void setLength(int n) { // Enforces length >= 0
		if (n > 0) {
			length = n;
		} else {
			System.err.println("Cannot be less than 0");
			System.exit(-1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Shape#getArea()
	 */
	public double getArea() { // Returns area of the rectangle
		return width * length;
	}

	/**
	 * Gets the length.
	 * 
	 * @return the length
	 */
	public double getLength() { // Returns length of the rectangle
		return length;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public double getWidth() { // Returns width of the rectangle
		return width;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Shape#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	// I experimented with using a different drawing method, such as filling
	// with a gradient,
	// but since the provided driver draws 20 shapes on the JFrame it looks
	// strange to
	// have filled in shapes since they all over lap. Therefore I am leaving
	// them with just outlines.
	/*
	 * (non-Javadoc)
	 * 
	 * @see Shape#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g) { // Draw the rectangle with specified
									// dimensions
		if (width > 0 && length > 0) { // There is no shape if not greater than
										// 0
			Graphics2D g2d = (Graphics2D) g; // Create new Graphics2D object

			final int x = getX(); // Get x for location
			final int y = getY(); // Get y for location

			g2d.setColor(getColor());
			if (getIsFilled()) {
				g2d.fillRect(x, y, (int) width, (int) length);
			} else {
				g2d.drawRect(x, y, (int) width, (int) length);
			}// Uses
				// drawRect
				// to draw
			// a rectangle with the specified dimensions
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Shape#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		if (o == null || !(o instanceof Shape)) {
			throw new RuntimeException("Invalid Shape");
		} else {
			Shape that = (Shape) o;
			return (int) (this.getArea() - that.getArea());
		}
	}

}