// Import necessary libraries
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Circle contains data related to a Circle. Circle extends Shape.
 * 
 * Class: CSS162 Winter 2014
 * 
 * Instructor: Rob Nash
 * 
 * @author Spencer Niemi
 * @version 1.0
 */
public class Circle extends Shape implements Cloneable, Serializable,
		Comparable {

	/** The radius. */
	private int radius; // Variable for radius of the circle

	/**
	 * Instantiates a new circle.
	 * 
	 * @param a
	 *            the x
	 * @param b
	 *            the y
	 * @param r
	 *            the radius
	 * @param fill
	 *            the fill
	 */
	public Circle(int a, int b, int r, boolean fill) { // Constructor for Circle
		super(a, b, fill); // we explicitly call a superclass constructor that
							// takes 2
							// ints
		setRadius(r); // set radius to r
	}

	/**
	 * Returns the area of a shape
	 */
	public double getArea() { // Returns area of circle
		return Math.PI * (radius * radius);
	}

	/**
	 * Sets the radius.
	 * 
	 * @param n
	 *            the new radius
	 */
	public void setRadius(int n) { // Enforces radius >= 0
		if (n >= 0) {
			radius = n;
		} else {
			throw new RuntimeException("Radius Cannot be Less than 0: " + radius);
		}
	}

	/**
	 * Set the size of the circle relative to the distance that the user dragged
	 * the line across the screen.
	 */
	public void setSize(int size) {
		radius = size / 2;
	}

	/**
	 * Gets the diameter of the circle.
	 * 
	 * @return the diameter
	 */
	public double getDiameter() { // Returns diameter of circle
		return radius * 2;
	}

	/**
	 * Clone method, to implement cloneable
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Gets the radius of the circle.
	 * 
	 * @return the radius
	 */
	public double getRadius() {// Returns radius of the circle
		return radius;
	}

	// I experimented with using a different drawing method, such as filling
	// with a gradient,
	// but since the provided driver draws 20 shapes on the JFrame it looks
	// strange to
	// have filled in shapes since they all overlap. Therefore I am leaving them
	// with just outlines.
	/*
	 * (non-Javadoc)
	 * 
	 * @see Shape#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g) { // Code to draw the circle
		if (radius > 0) { // There is no shape if not greater than 0
			Graphics2D g2d = (Graphics2D) g; // Create a Graphics2D object for
												// use

			final int x = getX(); // Get x for location
			final int y = getY(); // get y for location

			g2d.setColor(getColor()); //
			Ellipse2D.Double toDraw = new Ellipse2D.Double(x, y, radius * 2,
					radius * 2);

			if (getIsFilled()) {
				g2d.fill(toDraw); // Uses
			} else {
				g2d.draw(toDraw);
			}
			// Ellipse2D
			// to
			// draw
			// a circle with the specified dimensions and location

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