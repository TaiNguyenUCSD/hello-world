import java.util.Collection;

/**
 * This is MyPriority.java which contains MyPriorityQueue 
 * class
 */


/**
 * This is MyPriority Queue which is ordered like a line, but
 * allows priority for some objects. This priority is decided
 * by how low a number is 
 * @author drink
 *
 */
public class MyPriorityQueue<E extends Comparable<E>> {
	protected MyMinHeap<E> heap;
	/**
	 * No argument constructor that initializes a no argument heap data structure.
	 */
	public MyPriorityQueue() {
		heap = new MyMinHeap<E>();
	}
	/**
	 * Constructor that accepts a collection and initializes a heap with a collection 
	 * parameter.
	 */
	public MyPriorityQueue(Collection<? extends E> collection) {
		if (collection.equals(null) || collection.contains(null)) {
			throw new NullPointerException("Collection cannot be null");
		}
		else {
			heap = new MyMinHeap<E>(collection);
		}
	}
	/**
	 * Places an element in the heap based on how large its element is in 
	 * comparison to the heap.
	 * @param element
	 */
	public void push(E element) {
		if (element.equals(null)) {
			throw new NullPointerException("element cannot be null");
		}
		else {
			heap.insert(element);
		}
	}
	/**
	 * Looks at (returns) the element with the highest priority. Returns
	 * null if Priority queue is empty.
	 */
	public E peek() {
		return(heap.getMin());
	}
	/**
	 * Removes and returns element with highest priority. But if the Priority 
	 * Queue is empty, it returns null instead.
	 */
	public E pop() {
		return(heap.remove());
	}
	/**
	 * Returns amount of elements in the priority queue.
	 */
	public int getLength() {
		return(heap.size());
	}
	/**
	 * Clears entire priority queue and makes it an empty MyMinHeap
	 */
	public void clear() {
		heap.clear();
	}

}
