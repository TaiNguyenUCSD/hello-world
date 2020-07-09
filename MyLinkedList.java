/** Contains multiple classes that define
 * what a Node is and how it is ran. Also 
 * a collection of nodes come to together to make
 * a linked list objct. Then there is an Iterable
 * class used in order to transverse the Linked list
 * forwards and backwards. 
 * 
 * Name: Tai Nguyen
 * PID: A15463428
 * Email: tan005@ucsd.edu
 *  
 */

import java.util.*;

/** Behaves liked a Doubly Linked List with nodes
 * various methods to call and set the previous
 * and next nodes. The nodes' identities highly depend
 * on the nodes around them.
 *  
 */

public class MyLinkedList<E> extends AbstractList<E> {
	/**
	 * Number of nodes (not including the
	 * sentinel head and tail nodes)
	 */
	int nelems;		
	/**
	 * The null sentinel head node
	 */
	Node head;		
	/**
	 * The null sentinel tail node
	 **/
	Node tail;		
	/**
	 * An object that contains data and two pointers
	 * referencing to the node (positioned) 
	 * before and after it.
	 * @author drink
	 *
	 */
	protected class Node {
		//Instance Variables
		/**
		 * Information contained in a node
		 */
		E data;		
		/**
		 * Points to the next (forward) node
		 */
		Node next;	
		/**
		 * Points to the previous (backward) node 
		 */
		Node prev;		
		//Constructors
		/** 
		 * Constructor that sets the data
		 * @param e
		 */
		public Node(E e)
		{
			data = e;
		}
		//Methods
		/**
		 * Points the previous node to p
		 * @param p - the Node desired to point to
		 * as the previous Node
		 */
		public void setPrev(Node p)
		{
			prev = p;
		}
		/**
		 * Points the next node to n
		 * @param n - the Node desired to point to
		 * as the next node
		 */
		public void setNext(Node n)
		{
			next = n;
		}
		/**
		 * Changes the node's (calling object) data to e
		 * @param e - new data desired to place in the node
		 */
		public void setElement(E e)
		{
			this.data = e; 
		}
		/**
		 * Returns the Node that the current node (calling object's)
		 * next pointer points to
		 * @return Node - node that the current node's next pointer
		 * points to
		 */
		public Node getNext()
		{
			return (this.next);
		}
		/**
		 * Returns the Node that the current node (calling object's)
		 * previous pointer points to
		 * @return Node - node that the current node's previous pointer
		 * points to
		 */
		public Node getPrev()
		{
			return (this.prev);
		} 
		/**
		 * Returns the data contained in the node (calling object)
		 * @return E - data contained within the node
		 */
		public E getElement()
		{
			return (this.data);
		} 
		
		
	}

	/**
	 * An Iterator to navigate MyLinkedList and to go back
	 * and forth. Also has methods to remove nodes in a more
	 * efficient way remove and set nodes
	 */ 
	protected class MyListIterator implements ListIterator<E> {
		/**
		 * If it goes forward, it is true
		 */
		boolean forward;
		/**
		 * Describes if user can use the remove or set method
		 */
		boolean canRemoveOrSet;
		/**
		 * The pointers to the left and right of the iterator index
		 */
		Node left,right;
		/**
		 * The index describes the connection between two nodes
		 */
		int idx;
		/**
		 * The only 0-argument MyListIterator constructor
		 * to set the forward value to true, and the left 
		 * pointer to head and the right pointer to the first node
		 */
		public MyListIterator() {
			forward = true;
			left = head;
			idx = 0;
			if (nelems == 0) {
				right = tail;
			}
			right = head.next;
			canRemoveOrSet = false; 
		}
		public E getLeft() {
			return this.left.getElement();
		}
		public E getRight() {
			return this.right.getElement();
		}
		/**
		 * The add method describes the Node that goes in between
		 * the nodes pointing left and right
		 * @param e - the data specified in the added node
		 */
		@Override
		public void add(E e)
		{
			if (e == null) {
				throw new NullPointerException("data can't be null");
			}
			Node addedNode = new Node(e);
			addedNode.setNext(this.right);
			addedNode.setPrev(this.left);
			this.left.setNext(addedNode);
			this.right.setPrev(addedNode);
			this.left = addedNode;
			idx++;
			//Updates variables accordingly
			canRemoveOrSet = false;
			nelems++;
		}
		/**
		 * Returns true only if going forward continues to have
		 * both the left and right Node contain (non-null) data
		 * @return boolean - true if left.next and right.next contain 
		 * data
		 */
		@Override
		public boolean hasNext()
		{
			if (right.getElement() == null) {
				return false;
			}
			else {
				return true;
			}
		}
		/**
		 * Returns true if going back on the iterator has both
		 * the left and right nodes contain (non-null) data
		 * @return boolean - true if left.prev and right.prev contain data
		 */
		@Override
		public boolean hasPrevious()
		{
			if (left.getElement() == null) {
				return false;
			}
			else {
				return true;
			}
		}
		
		/**
		 * Moves both the left and right node forward, and sets forward
		 * to true. Also returns the right Node data before the shift
		 * @return E -  the right Node elements before moving forward
		 */
		@Override
		public E next()
		{
			if (right.getElement() == null) {
				throw new NoSuchElementException("The data is null");
			}
			E dataToReturn = right.getElement();
			left = left.next;
			right = right.next;
			idx++;
			forward = true;
			canRemoveOrSet = true;
			return dataToReturn;
		}
		/**
		 * Returns the index of the right node (in the MyListIterator
		 * context)
		 * @return int - the index of the right node
		 */
		@Override
		public int nextIndex()
		{
			if (idx == nelems) {
				return nelems;
			}
			if (right.getElement() == null) {
				return nelems;
			}
			return idx;		
		}
		/**
		 * Moves the left and right nodes to the left. Also returns
		 * data from the left node before moving
		 * @return E - the data returned from the left node
		 * before moving
		 */
		@Override
		public E previous()
		{
			E dataToReturn = left.getElement();
			if (dataToReturn == null) {
				throw new NoSuchElementException("previous "
						+ "elements can't have null data");
			}
			left = left.getPrev();
			right = right.getPrev();
			idx--;
			forward = false;
			canRemoveOrSet = true;
			return dataToReturn;
		}
		/**
		 * Returns the left Node index (as defined by MyListIterator) 
		 * @return int - the left Node index 
		 */
		@Override
		public int previousIndex()
		{
			if (idx == 0) {
				return -1;
			}
			return (idx-1);
		}
		/**
		 * Removes the node most recently ref
		 */
		@Override
		public void remove()
		{
			if (this.canRemoveOrSet == false) {
				throw new IllegalStateException("Must call next"
						+ "or previous in order to remove");
			}
			if (forward == true) {
				this.left = this.left.prev;
				MyLinkedList.this.remove(idx-1);
			}
			if (forward == false) {
				this.right = this.right.next;
				MyLinkedList.this.remove(idx);
			}
			idx--;
			this.canRemoveOrSet = false;
		}
		/**
		 * Changes the value of the Node returned by the last 
		 * next or previous method
		 * @param e - the data replacing the data in the Node
		 */
		@Override
		public void set(E e) 
		{
			if (e == null) {
				throw new NullPointerException("Data must not"
						+ "be null");
			}
			if (this.canRemoveOrSet == false) {
				throw new IllegalStateException("next or previous"
						+ "must be called first");
			}
			if (forward == true) {
				left.setElement(e);
			}
			if (forward == false) {
				right.setElement(e);
			}
		}
		/**
		 * Prints out the contents of the left and right node while
		 * also displaying all the instance variable information
		 */
		@Override
		public String toString() {
			String data = "";
			data = data + "left:" + left.getElement() + "   right:" +
					right.getElement();
			data = data + "\nidx: " + idx + "|";
			if (forward == true) {
				data = data + "  moving forward";
			}
			else if (forward == false) {
				data = data + "  moving backwards";
			}
			data = data + " &  ";
			if (canRemoveOrSet == true) {
				data = data + "can remove";
			}
			else if (canRemoveOrSet == false) {
				data = data + "can't remove";
			}
			return data;

		}
	}

	//  Implementation of the MyLinkedList Class
	/**
	 * 0-argument constructor used to create a 
	 * MyLinkedList object. It first starts out as
	 * a circularly linked list. This is the only way
	 * to create a MyLinkedList object
	 */
	public MyLinkedList()
	{
		/**
		 * Number of elements in the Linked list, excluding
		 * the sentinel head and tail nodes
		 */
		nelems = 0;
		/**
		 * Sentinel (empty) head
		 */
		head = new Node(null);
		/**
		 * Sentinel (empty) tail
		 */
		tail = new Node(null);
		// Setting connections between head and tail
		tail.prev = head;
		head.next = tail;
	}
	/**
	 * Method used to retrieve the amount of nodes 
	 * in the MyLinkedList
	 * @return int - number of nodes
	 */
	@Override
	public int size()
	{
		Node curr = head.next;
		int count = 0;
		
		while (curr.getElement() != null) {
		curr = curr.getNext();
		count++;
		}
			
		if (count != nelems) {
			throw new NullPointerException("Something"
					+ " went wrong mate");
		}
    	return nelems;
	}
	/**
	 * Returns data at an index of the Linked List
	 * @param index - position of Linked list user wants information
	 * @return E - information contained in specified node
	 */
	@Override
	public E get(int index)
	{
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index is less than 0");
		}
		if (index >= nelems) {
			throw new IndexOutOfBoundsException("Index is greater than "
					+ "or equal to the number of elements");
		}
		return(this.getNth(index).getElement());
	}
	/**
	 * Adds a new node at the specified index with 
	 * the data in the parameter. If the data is null
	 * or if the index is less than 0 or more than the size,
	 * an Exception is thrown. 
	 * @param data - information wanted in the new node
	 */
	@Override
	public void add(int index, E data) 
	{
		if (data == null) {
			throw new NullPointerException("Must not have null data");
		}
		if (index < 0 || index > nelems) {
			throw new IndexOutOfBoundsException("Index must be between 0"
					+ "and the number of elements");
		}
		if (nelems == 0) {
			this.add(data);
		}
		else {
			nelems++;
			Node addedNode = new Node(data);
			Node afterNode = this.getNth(index);
			addedNode.prev = afterNode.prev;
			addedNode.next = afterNode;
			afterNode.prev.next = addedNode;
			afterNode.prev = addedNode;	
			
		}
	}
	/**
	 * Adds a new node to the end of the Linked List
	 * with data specified in the parameter. If the data 
	 * specified is null, a NullPointerException is thrown
	 * @param data - information wanted in the new node
	 * @return boolean - mostly returns true unless exceptions
	 * are thrown
	 */
	public boolean add(E data)
	{
		if (data == null) {
			throw new NullPointerException("Must not have null data");
		}
		
		Node endNode = new Node(data);
		endNode.prev = tail.prev;
		endNode.next = tail;
		tail.prev.next = endNode;
		tail.prev = endNode;
		nelems++;
		return true; 
	}
	/**
	 * Changes the value of the node in MyLinkedList 
	 * specified by the index to the new data
	 * @param index - position of Node wanted to change
	 * @param data - data wanted to set in the node
	 * @return E - data returned from the original node
	 */
	public E set(int index, E data) 
	{
		if (data == null) {
			throw new NullPointerException("data must not"
					+ "be null");
		}
		if (index < 0) {
			throw new IndexOutOfBoundsException("index is less"
					+ "than 0");
		}
		if (index >= nelems) {
			throw new IndexOutOfBoundsException("index is greater"
					+ "than or equal to the number of elements");
		}
		Node curr = this.getNth(index);
		E dataToReturn = curr.getElement();
		curr.setElement(data);
		return dataToReturn;
	}
	/**
	 * Removes the node at the specified index
	 * @param index - the position where I want the 
	 * node removed
	 * @return E - the information from the node removed
	 */
	public E remove(int index)
	{
		if (index <0) {
			throw new IndexOutOfBoundsException("index is less than 0");
		}
		if (index >= nelems) {
			throw new IndexOutOfBoundsException("index is greater or "
					+ "equal to the amount of nodes");
		}
		Node returnedNode = this.getNth(index);
		Node curr = returnedNode;
		curr.getPrev().setNext(curr.getNext());
		curr.getNext().setPrev(curr.getPrev());
		nelems--;
		return(returnedNode.getElement());
		
	}
	/**
	 * Clears all of the nodes in MyLinkedList 
	 * besides the sentinel head and tail nodes
	 */
	public void clear()
	{
		this.head.setNext(this.tail);
		this.tail.setPrev(this.head);
		nelems = 0;
	}
	/**
	 * Returns true if the list is empty
	 */
	public boolean isEmpty()
	{
		if (head.next.equals(tail) || tail.prev.equals(head)) {
			return(true);
		}
		return(false);
	}
	/**
	 * Returns the node (not the actual data) 
	 * at a specific index counting from the
	 * head
	 * @param index - the position of the Node wanted
	 * @return Node - the Node at specified position
	 */
	protected Node getNth(int index)
	{
		if (index < 0) {
			throw new IndexOutOfBoundsException(
					"referenced a position less than 0");
		}
		if (index >= nelems) {
			throw new IndexOutOfBoundsException(
					"referenced a position more than or equal to "
					+ "the number of elements");
		}
		Node curr = head.next;
		/* If the index is 0, it returns the next node 
		 * after head. If the index is higher, it will
		 * cycle through the for loop until it gets to the 
		 * index
		 */
		for (int i = 0; i < index; i++) {
			curr = curr.next;
			if (curr == null) {
				throw new NullPointerException(
						"something went wrong");
			}
		}
		return (curr);
	}
	/**
	 * Prints out the contents of the LinkedList in 
	 * brackets ([]) where the objects are separated by commas
	 * @return String - Contents of the linked list
	 */
	@Override
	public String toString() {
		String contents = "[";
		// Stopping it at -1 for aesthetic purposes
		for (int i = 0; i < nelems-1; i++) {
			contents = contents + this.get(i);
			contents = contents + ", ";
		}
		// Adding the last element in the list
		if (nelems > 0) {
			contents = contents + this.get(nelems - 1);
		}
		contents = contents + "]";
		return(contents);
		
	}
	/**
	 * Evaluates if two MyLinkedLIst objects are equal 
	 * to each other by checking if they're the same type and
	 * by directly comparing the contents of their nodes 
	 * @param o - the object compared
	 * @return boolean - true if LinkedLists are equal
	 */
	@Override
	public boolean equals(Object o) {
		/* If this returns true, that's good and I can
		 * keep comparing the two Linked Lists
		 */
		if (o instanceof MyLinkedList) {
		}
		else {
			return false;
		}
		@SuppressWarnings("rawtypes")
		MyLinkedList second = (MyLinkedList)o;
		Node curr1 = this.head.getNext();
		@SuppressWarnings("unchecked")
		Node curr2 = second.head.getNext();
		while (curr1 != null && curr2 != null) {
			if (curr1.data.equals(curr2.data)) {
			}
			else {
				return false;
			}
			curr1 = curr1.getNext();
			curr2 = curr2.getNext();
		}
		return true;
		
	}
	/**
	 * Have Iterator method be overridden with a MyListIterator()
	 * @return Iterator<E> overridden Iterator
	 */
	@Override
	public Iterator<E> iterator()
	{
		MyListIterator empty = new MyListIterator();
		return empty;
	}
	/**
	 * Have ListIterator method be overridden with a MyListIterator()
	 * @return ListIterator<E> overridden by MyListIterator
	 */
	@Override
	public ListIterator<E> listIterator() {
	    MyListIterator empty = new MyListIterator();
		return empty;
	}
	public static void main(String[] args) {	
	}

}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4