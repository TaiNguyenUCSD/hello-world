/**
 * MyStack.java contains the MyStack class and 
 * a bunch of other various methods within in order to 
 * manipulate this abstract data type.
 */


/*
 * Name: Tai Nguyen
 * CS ID: cs12sp20amu
 * email: tan005@ucsd.edu
 */
/**
 * My Stack implements the Stack Interface which requires
 * various methods in order to treat the ADT like
 * a stack of books or a pile
 * @author drink
 *
 * @param <E>
 */
public class MyStack<E> implements StackInterface<E> {
	//Instance Variables
	//Deque that describes the underlying data structure
	MyDeque<E> theStack;
	
	//Constructors
	public MyStack (int initialCapacity) {
		theStack = new MyDeque<E>(initialCapacity);
	}
	
	//Methods
	/**
	 * Returns true if the stack is empty
	 * @return boolean 
	 */
	public boolean empty() {
		if (theStack.size() <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Places element at the top of the books(stack)
	 * @param e - element desired to push
	 */
	public void push(E e) {
		theStack.addFirst(e);
	}
	/**
	 * Removes and returns element at the top of the stack
	 */
	public E pop() {
		return(theStack.removeFirst());
	}
	/**
	 * Returns element given at the top of the stack
	 * @return E 
	 */
	public E peek() {
		return(theStack.peekFirst());
	}
	

	public static void main(String[] args) {
		MyStack<Integer> stack = new MyStack<Integer>(4);
		stack.push(4);
		stack.push(3);
		System.out.print(stack.peek() + " ");
		stack.push(2);
		stack.pop();
		System.out.print(stack.peek() + " ");
		stack.push(1);
		stack.push(8);
		System.out.print(stack.pop());
		
	}

}
