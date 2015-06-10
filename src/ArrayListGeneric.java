// TODO: Auto-generated Javadoc
/**
 * The Class ArrayListGeneric.
 *
 * @author Spencer Niemi
 * @version 1.0
 * Class: CSS162 Winter 2014
 * Instructor: Rob Nash
 * @param <TBA> the generic type
 */
public class ArrayListGeneric<TBA> {
	
	/** The data. */
	private String[] data;
	
	/** The num_elements. */
	private int num_elements;

	
	/**
	 * Instantiates a new array list generic.
	 */
	public ArrayListGeneric() {
		data = new String[100];
		num_elements = 0;
	}
	
	/**
	 * Insert.
	 *
	 * @param idx the idx
	 * @param next the next
	 */
	public void insert(int idx, String next) {
		arrayShiftRight(idx);
		data[idx] = next;
		num_elements++;
	}
	
	/**
	 * Insert.
	 *
	 * @param next the next
	 */
	public void insert(String next) {
		data[num_elements] = next;
		num_elements++;
	}

	/**
	 * Removes the.
	 *
	 * @param idx the idx
	 * @return the string
	 */
	public String remove(int idx) {
		String retVal = data[idx];
		arrayShiftLeft(idx);
		num_elements--;
		return retVal;
	}

	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		if (num_elements == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Empty.
	 */
	public void empty() {
		num_elements = 0;
	}
	
	/**
	 * Array shift left.
	 *
	 * @param start the start
	 */
	public void arrayShiftLeft(int start) {
		for (int i = start; i < num_elements; i++) {
			data[i] = data[i + 1];
		}
	}
	
	/**
	 * Array shift right.
	 *
	 * @param start the start
	 */
	public void arrayShiftRight(int start) {
		for (int i = start; i < num_elements - 1; i++) {
			data[i] = data[i - 1];
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String retVal = "";
		for (int i = 0; i < num_elements; i++) {
			retVal = data[i] + ", ";
		}
		return retVal;
	}
	
	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size() {
		return num_elements - 1;
	}
}
