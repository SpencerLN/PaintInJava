// TODO: Auto-generated Javadoc
/**
 * The Class QuickSort extends Sort and overrides the sort and swap methods of
 * the Sort class. QuickSort implements a quick sort for an array of integers
 * while tracking comparisons using the incrementComparisons() method.
 * 
 * @author Spencer Niemi
 * @version 1.0 
 * Class: CSS162 
 * Winter 2014 
 * Instructor: Rob Nash
 */
public class QuickSort {

	/**
	 * selectPivot moves indexes until correct conditions are reached, then
	 * returns the pivot value for use in the sort method.
	 * 
	 * @param numbers
	 *            the array of numbers being sorted
	 * @param left
	 *            the left index
	 * @param right
	 *            the right index
	 * @return the value of the pivot
	 */
	private static int selectPivot(int[] numbers, int left, int right) {
		int pivot = numbers[left]; // Select the left index as the initial pivot

		while (true) { // Intentional infinite loop that will be broken out of
						// when correct pivot point is obtained

			// Increment the left index while the integer at the left index is
			// less than the integer at pivot. Until there is a number to the
			// left of pivot point where the value is less than the element at
			// pivot
			while (numbers[left] < pivot) {
				left++;
			}

			// Increment the right index while the integer at the right index is
			// greater than the element at pivot. Until there is a number to the
			// right of the pivot point where the value is greater than the
			// element at pivot
			while (numbers[right] > pivot) {
				right--;
			}

			// Swap the elements so they are in the correct order
			if (left < right) {
				swap(numbers, right, left);
			} else {
				// Return the right value as the pivot
				return right;
			}
		}
	}

	/**
	 * Overrides sort in Sort, implements a quick sort of an array of integers.
	 * This method is a recursive method and as such calls itself until a base
	 * condition is reached, in this case until right >= left.
	 * 
	 * @param numbers
	 *            The array of integers which need to be sorted
	 * @param left
	 *            The left index
	 * @param right
	 *            The right index
	 */
	public static void sort(int[] numbers, int left, int right) {
		// If left < right then the array is not in order, so we need to call
		// selectPivot
		if (left < right) {
			int pivot = selectPivot(numbers, left, right);

			// If the pivot is greater than left, call sort on the left half of
			// the array
			if (pivot > left) {
				sort(numbers, left, pivot - 1);
			}

			// If the pivot is greater than left, and less than right, call sort
			// on the right half of the array
			if (pivot + 1 < right) {
				sort(numbers, pivot + 1, right);
			}
		}
	}

	/**
	 * Swap for two elements of an array of integers. It would make more sense
	 * to have this method in the Sort class, since it is needed in any Sort
	 * 
	 * @param array
	 *            the integers to sort
	 * @param index1
	 *            the index of the first integer to swap
	 * @param index2
	 *            the index of the second integer to swap
	 */
	public static void swap(int[] array, int index1, int index2) {
		int temp = array[index1]; // Temporary variable to hold element during
									// swap
		array[index1] = array[index2];
		array[index2] = temp;
	}
}