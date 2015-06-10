import java.io.Serializable;

/**
 * The Class ArrayList is a generic List that stores a set of objects, stored in
 * data. It is able to resize if necessary.
 * 
 * @author Spencer Niemi
 * @version 1.0 Class: CSS162 Winter 2014 Instructor: Rob Nash
 * @param <TBA>
 *            the generic type
 */
public class ArrayList<TBA> implements Serializable {

	/** The array in which the data is stored. */
	private Object[] data = new Object[100];

	/** The current size of the list. */
	private int size = 0;

	/**
	 * Adds the given element to the list, expands the list if necessary
	 * 
	 * @param element
	 *            the element
	 */
	public void add(TBA element) {
		expand();
		data[size++] = element;
	}

	/**
	 * Removes the element at the given index.
	 * 
	 * @param index
	 *            the index
	 * @return the element to return
	 */
	public TBA remove(int index) {
		if (index >= size || index < 0) {
			throw new RuntimeException("Invalid index");
		}
		TBA element = (TBA) data[index];
		data[index] = null;
		--size;
		return element;
	}

	/**
	 * Gets the element at the given index.
	 * 
	 * @param index
	 *            the index
	 * @return the element at the given index
	 */
	public TBA get(int index) {
		if (index >= size) {
			throw new RuntimeException("Invalid index");
		}
		TBA element = (TBA) data[index];
		return element;
	}

	/**
	 * gets the size of the ArrayList
	 * 
	 * @return the int
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Empties the list.
	 */
	public void clear() {
		size = 0;
	}

	/**
	 * Expands the list if necessary.
	 */
	private void expand() {
		if (size < data.length) {
			return;
		}
		resize();
	}

	/**
	 * Sets the element at an index to a new value.
	 * 
	 * @param idx
	 *            the idx
	 * @param obj
	 *            the obj
	 */
	public void set(int idx, TBA obj) {
		data[idx] = obj;
	}

	/**
	 * Resize, doubles the size of the array.
	 */
	private void resize() {
		Object[] temp = new Object[data.length * 2];
		copy(data, temp);
		data = temp;
	}

	/**
	 * Copyies the data from one array to another, is used by the resize method.
	 * 
	 * @param src
	 *            the src
	 * @param dest
	 *            the dest
	 */
	private void copy(Object[] source, Object[] destination) {
		if (destination.length < source.length) {
			throw new RuntimeException(source + " cannot be copied into "
					+ destination);
		}
		for (int i = 0; i < source.length; i++) {
			destination[i] = source[i];
		}
	}

}