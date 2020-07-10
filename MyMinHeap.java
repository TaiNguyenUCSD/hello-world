/**
 * Name: Tai Nguyen
 * ID: cs12sp20amu
 * email: tan005@ucsd.edu 
 * 
 * This is MyMinHeap.java. And is used to define a MinHeap 
 * while incorporating Lists, ArrayLists, and Collections
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * This is the MinHeap class. Is used to define a Minimum Heap
 * where the Parent content is always less than the content
 * of the children content. Uses an arrayList as the underlying
 * data structure
 * @author drink
 *
 * @param <E>
 */

public class MyMinHeap<E extends Comparable<E>>
{
	//Final, unchanging constants
	private static final int one = 1;
	private static final int zero = 0; 
	private static final int two = 2;
	private static final int minusOne = -1;
	/**
	 * Underlying data structure. Is Linked List and can use the add, set, 
	 * and get methods
	 */
    protected List<E> list;
   
    /**
     * No argument constructor that initializes an ArrayList,
     * the underlying data structure.
     */
    public MyMinHeap() {
    	list = new ArrayList<E>();
    }
    /**
     * Constructor that accepts a collection to start off the Heap
     * @param collection
     */
    public MyMinHeap(Collection<? extends E> collection) {
    	if (collection == null) {
    		throw new NullPointerException("List entered cannot be null");
    	}
    	else if (collection.contains(null)) {
    		throw new NullPointerException("List cannot contain any null elements");		
    	}
    	else {
    		list =  new ArrayList<E>(collection);
    	}
    	for (int i = list.size() - one; i >= zero; i--) {
    		percolateDown(i);
    	}
    	
    }
    /**
     * Switches the elements specified by index from and to
     * @param from
     * @param to
     */
    protected void swap(int from, int to) {
    	if (from < zero || to < zero || from >= list.size() || to >=list.size()) {
    		throw new IndexOutOfBoundsException("Um, you messed up");
    	}
    	E FirstElement = list.get(from);
    	E SecondElement = list.get(to);
    	list.set(from, SecondElement);
    	list.set(to, FirstElement);
    }
    /**
     * Returns the index of the Parent node given an index of a child node
     * @param index
     * @return
     */
    protected int getParentIdx(int index) {
        if (index<=zero) {
        	throw new IndexOutOfBoundsException("Choose a child Node");
        }
        //Test if it's left or right child
        if (index%two == one) {//left child
        	return((index-one)/two);
        }
        else if (index%two == zero) {//right child
        	return((index-two)/two);
        }
    	return minusOne;
    		
    }
    /**
     * Returns the Index of the Left Child given the index of a parent node
     * @param index
     * @return
     */
    protected int getLeftChildIdx(int index) {
        return (two*index+one);
    }
    /**
     * Returns the Index of the Right child of the node of the index given
     * @param index
     * @return
     */
    protected int getRightChildIdx(int index) {
        return (two*index+two);
    }
    /**
     * Returns the Index of the child with a smaller element of the 
     * parent node of the index specified 
     * @param index
     * @return
     */
    protected int getMinChildIdx(int index) {
        int leftIndex = getLeftChildIdx(index);
        int rightIndex = getRightChildIdx(index);
        // Checks to see if left and right child don't exist
        if (rightIndex >= list.size()) {
        	if (leftIndex >= list.size()) {
        		
        		return minusOne;
        	}
        	else {
        		return leftIndex;
        	}
        }
        E left = list.get(leftIndex);
        E right = list.get(rightIndex);
        if (left.compareTo(right) < zero) {
        
        	return leftIndex; 
        }
        else if (left.compareTo(right) > zero) {
        	
        	return rightIndex;
        }
        else if (left.compareTo(right) == zero) {
        
        	return leftIndex;
        }
       
        return minusOne;
    }
    /**
     * Drags the Node (specified by the index) up until its
     * parent Node does not contain an element greater than it  
     * @param index
     */
    protected void percolateUp(int index) {
    	if (index < zero || index >= list.size()) {
    		throw new IndexOutOfBoundsException("Messed up");
    	}
    	else if (index == zero) {
    		return;
    	}
    	int childIndex = index;
    	while (list.get(this.getParentIdx(childIndex)).compareTo
    			(list.get(childIndex)) > zero) {
    		int previousParent = getParentIdx(childIndex);
    		this.swap(this.getParentIdx(childIndex),childIndex);
    		childIndex = previousParent;
    		// Checks to see if it is already placed at the child Node
    		if (childIndex <= zero) {
    			break;
    		}
    	}
    }
    /**
     * Drags the Node (specified by the index) down until 
     * its child Node does not contain an element lesser than it's 
     * content
     * @param index
     */
    protected void percolateDown(int index) {
    	if (index < zero || index >= list.size()) {
    		throw new IndexOutOfBoundsException("Messed up");
    	}
    	int parentIndex = index;
    	if (getMinChildIdx(parentIndex) == minusOne) {
    		return;
    	}
    	while(list.get(parentIndex).compareTo(list.get(
    			getMinChildIdx(parentIndex))) > zero) {
    		// Keep track of the littlest child of the current Node
    		int littleChildIndex = getMinChildIdx(parentIndex);
    		swap(parentIndex, getMinChildIdx(parentIndex));
    		parentIndex = littleChildIndex;
    		if (getMinChildIdx(parentIndex) == minusOne) {
    			break;
    		}
    	}
    }
    /**
     * Deletes a node specified by an index and then percolates up or down 
     * based on if the child or parent Node is out of order. Then returns 
     * the data in the deleted node
     * @param index
     * @return E 
     */
    protected E deleteIndex(int index) {
    	E orig = list.get(index);
    	E replacement = list.get(list.size()-one);
    	swap(index, list.size()-one);
    	list.remove(list.size()-one);
    	if (replacement.compareTo(orig) < zero) {
    		this.percolateUp(index);
    	}
    	else if (replacement.compareTo(orig) > zero) {
    		this.percolateDown(index);
    	}
    	return orig;
    }
    /**
     * Adds a node (with an element in the node) at the end 
     * and then uses percolates up method if the parent node contains 
     * element greater than it
     * @param E - element 
     */
    public void insert(E element) {
    	if (element.equals(null)) {
    		throw new NullPointerException("element specified cannot be null");
    	}
    	list.add(element);
    	this.percolateUp(list.size()-one);
    }
    /**
     * Returns the data of the root Node. This will also correspond to the Minimum
     * value of the heap. (Will return null if heap is empty)
     * @return E - data of Node
     */
    public E getMin() {
    	if (list.size() <= zero) {
    	    return null;	
    	}
    	return(list.get(zero));
    }
    /**
     * Removes the root Node and returns its data of the root Node. This
     * also will be the minimum value of the heap. (Will return null if 
     * heap is empty)
     * @return E - data in root node
     */
    public E remove() {
    	if (list.size() <= zero) {
    		return null;
    	}
        return(this.deleteIndex(zero));
    }
    /**
     * Returns the number of elements in the heap 
     * @return int 
     */
    public int size() {
        return(list.size());
    }
    /**
     * Empties out the heap, and makes it an empty array list
     */
    public void clear() {
    	list = new ArrayList<E>();
    }		
    
    public String toString() {
    	String bro = "[";
    	for (int i = 0; i < list.size(); i++) {
    		bro += list.get(i) + " ";
    	}
    	bro += "]";
    	return bro;
    }
}
