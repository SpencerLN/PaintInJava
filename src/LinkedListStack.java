// TODO: Auto-generated Javadoc
/**
 * The Class LinkedListStack is a generic Linked List that functions as a stack.
 * 
 * @author Spencer Niemi
 * @version 1.0 Class: CSS162 Winter 2014 Instructor: Rob Nash
 * @param <TBA>
 *            the generic type
 */
public class LinkedListStack<TBA> {

	/**
	 * The Node class stores a data object, and a link to the next Node.
	 */
	private class Node<TBA> {

		/** The data Object. */
		TBA data;

		/** The next Node in the list. */
		Node<TBA> next;

		/**
		 * Instantiates a new node.
		 * 
		 * @param newData
		 *            the new data
		 * @param newNode
		 *            the new node
		 */
		public Node(TBA newData, Node<TBA> newNode) {
			data = newData;
			next = newNode;
		}

		/**
		 * Gets the next node in the List.
		 * 
		 * @return the next node
		 */
		public Node<TBA> getNextNode() {
			return next;
		}
	}

	/** The head of the list. */
	private Node<TBA> head;

	/**
	 * Pushes a new Node into the list.
	 * 
	 * @param newData
	 *            the new data
	 */
	public void push(TBA newData) {
		head = new Node<TBA>(newData, head);
	}

	/**
	 * Pops the most recently added object out of the stack.
	 * 
	 * @return the tba
	 */
	public TBA pop() {
		TBA retVal = head.data;
		head = head.next;
		return retVal;
	}

	/**
	 * Checks if the stack is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Size of the stack.
	 * 
	 * @param start
	 *            the start
	 * @param count
	 *            the count
	 * @return the int
	 */
	private int size(Node<TBA> start, int count) {
		if (start != null) {
			count++;
			size(start.getNextNode(), count);
		}
		return count;
	}

	/**
	 * Facade method that redirects to the true size method.
	 * 
	 * @return the int
	 */
	public int size() {
		return size(head, 0);
	}
}
