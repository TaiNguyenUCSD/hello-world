/*
 * Name: Tai Nguyen
 * ID: cs12sp20amu
 * Email: tan005@ucsd.edu
 */


public class MyDeque<E> implements DequeInterface<E>{
	//Underlying data structure, circular array
	Object[] data; 
	// # of elements in the Deque
	int size;
	//refers to the index of the last element in the Deque
	int rear;
	//refers to the index of the first element in the Deque
	int front; 
	
	//Unchanging variables
	private static final int zero = 0;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int CONSTANT_TWO = 2; 
	private static final int one = 1; 
	
	//Constructors 
	public MyDeque(int initialCapacity) {
		if (initialCapacity < zero) {
			throw new IllegalArgumentException("Intial Capacity can't be less"
					+ "than 0");
		}
		data = new Object[initialCapacity];
		size = zero;
		rear = zero;
		front = zero;
	}
	//Methods
	/** 
	 * Returns the amount of elements in the Deque
	 * @return int - elements in the Deque
	 */
	public int size() {
		return size;
	}
	/**
	 * Expands capacity to twice the size, and if the size was 0,
	 * changes the size to 10 (default capacity)
	 */
	public void expandCapacity() {
		int newCapacity;
		Object[] newData; 
		if (data.length == zero) {
			newCapacity = DEFAULT_CAPACITY;
			newData = new Object[newCapacity];
		}
		// If data is more than zero capacity, must copy elements to 
		// new data structure
		else {
			newCapacity = CONSTANT_TWO*data.length;
			newData = new Object[newCapacity];
			if (rear < front) {
				//Copies from front to end
				for (int i = front; i < data.length; i++) {
					if (data[i] == null) {
						continue;
					}
					newData[i-front] = data[i];
				}
				// After copying the front to end, I will continue from the middle to the end 
				int middleIndex = (data.length) - front;
				//Copies from beginning to rear
				for (int i = 0; i <= rear; i++,middleIndex++) {
					if (data[i] == null) {
						//If the data is null, it won't start incrementing the middle
						middleIndex--;
						continue;
					}
					newData[middleIndex] = data[i];
				}
			}
			if (rear >= front) {
				for (int i = front; i <= rear; i++) {
					newData[i-front] = data[i];
				}
			}
		}		
		//Lose original data pointer and point to the new data Object created
		front = zero;
		rear = size - one;
		data = newData;
	}
	/**
	 * Adds specified element before the first index. However, if index
	 * is 0, then the element is added at the ened of the list
	 * @param element - data user wants to add
	 */
	public void addFirst(E element) {
		if (element == null) {
			throw new NullPointerException ("element cannot be null");
		}
		if (size == data.length) { 
			this.expandCapacity();
		}
		// If this is the first element, just adds one at the 0 index
		if (size < one) {
			data[zero] = element;
		}
		else {
			// Changes front index
			front = front - one;
			// If the Front index is less than 0, places the Front index at the back
			if(front < zero) {
				front = data.length-one;
			}
			data[front] = (Object)element;
		}			
		size++;		
	}
	/**
	 * Adds the specified element past the last index of the Deque. 
	 * However, if the last index is at the end of the Deque, 
	 * it will loop back to the beginning of the Deque
	 * @param element
	 */
	public void addLast(E element) {
		if (element == null) {
			throw new NullPointerException("Element cannot be null");
		}
		if (size == data.length) {
			this.expandCapacity();
		}
		// If this is the first element, just adds one at the 0 index
		if (size < one) {
			data[zero] = element;
		}
		else {
			//Changes rear index
			rear = rear + one;
			// If rear would be greater than the capacity, it would wrap around to the beginning
			if (rear >= data.length) {
				rear = zero;
			}
			data[rear] = (Object)element;
		}
		size++;
	}
	/**
	 * Takes away the first element in the Deque
	 * @return E - the removed first element
	 */
	@SuppressWarnings("unchecked")
	public E removeFirst() {
		E ElementToReturn = (E)data[front];
		data[front] = null;
		if (ElementToReturn == null) {
			return null;
		}
		front = front + one;
		front = front%data.length;
		size--;
		return ElementToReturn;
	}
	/**
	 * Takes away the last element in the Deque
	 * @return E - the removed last element
	 */
	@SuppressWarnings("unchecked")
	public E removeLast() {
		E ElementToReturn = (E)data[rear];
		data[rear] = null;
		if (ElementToReturn == null) {
			return null;
		}
		rear = rear - one;
		if (rear < zero) {
			rear = data.length - one;
		}
		size--;
		return ElementToReturn;
	}
	/**
	 * Returns the element at the front index of the Deque
	 * @return E - element at the front index
	 */	
	public E peekFirst() {
		return((E)data[front]);
	}
	/**
	 * Returns the element at the rear index of the Deque
	 * @return E - the element at the rear index
	 */
	public E peekLast() {
		return((E)data[rear]);
	}
	
	

	public static void main(String[] args) {
		
		MyDeque<Integer> deque = new MyDeque<Integer>(6);
		deque.addFirst(1);
		deque.addFirst(2);
		deque.addLast(3);
		deque.addFirst(4);
		deque.removeLast();
		deque.addLast(8);
		deque.removeFirst();
		for(int i = 0; i <= 1; i ++) {
		    deque.addLast(deque.removeFirst());
		}
		System.out.println(deque.peekFirst());
		System.out.println(deque.peekLast());
	}

}
