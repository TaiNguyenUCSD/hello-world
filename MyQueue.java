/*
 * Name: Tai Nguyen
 * CS ID: cs12sp20amu
 * email: tan005@ucsd.edu
 */


public class MyQueue<E> implements QueueInterface<E>  {
	
	//Instance variables
	//Underlying structure
	MyDeque<E> theQueue;
	
	//Constructors
	public MyQueue (int initialCapacity) {
		theQueue = new MyDeque<E>(initialCapacity);
	}
	
	//Methods
	/**
	 * Returns true if MyQueue is empty
	 */
	public boolean empty() {
		if (theQueue.size() <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Places element in the back of the line(queue)
	 * @param e - element desired to insert
	 */
	public void enqueue(E e) {
		theQueue.addLast(e);
	}
	/**
	 * Removes and returns the element in the front of the line(queue)
	 * @return - E
	 */
	public E dequeue() {
		return(theQueue.removeFirst());
	}
	/**
	 * Returns (but does not remove) the element in the front of the line
	 * (queue)
	 * @return - E 
	 */
	public E peek() {
		return(theQueue.peekFirst());
	}
	
	
	public static void main(String[] args) {
		MyQueue<Integer> queue = new MyQueue<Integer>(5);
		queue.enqueue(8);
		queue.enqueue(5);
		queue.enqueue(3);
		queue.enqueue(1);
		queue.enqueue(2);
		for(int i = 0; i <= 2; i++){
		    System.out.print(queue.dequeue() + " ");
		}
		System.out.print(queue.dequeue() + " ");
		System.out.print(queue.peek() + " ");
		queue.enqueue(2);
		queue.dequeue();
		queue.enqueue(1);
		System.out.print(queue.dequeue());

	}

}
