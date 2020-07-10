/* This is my artificial idea of creating an ArrayList. This has some
 * superficial ideas of what it means to have a capacity and a size.
 * The point of this ArrayList is to hide all the underlying
 * information from the user. 
 * 
 * Name: Tai Nguyen (cs12sp20amu)
 * PID: A15463428
 * email: tan005@ucsd.edu 
 */

// With generics
/** 
 * A basic idea of how a List works by having a capacity and size.
 * This also has methods for inserting, prepending, and appending items
 * to the list. This class can be extended into other classes in order
 * make a more specific type of list
 * 
 * @author Tai Nguyen
 */
public class MyArrayList<E> implements MyList<E> {
	
	//Instance Variables
	/** 
	 * The underlying data structure, that holds all the elements in an object array
	 */
	Object[] data;
	/**
	 * The number of existing elements in the object array. This can include null objects
	 */
	int size;
	// The capacity of the Object array if no capacity is specified
	private static final int DEFAULT_CAPACITY = 10;
	// The number I multiply the initial Capacity by if it is below the requiredCapacity
	private static final int two = 2; 
	// Default Size if no data is inputted into the constructor
	private static final int DEFAULT_SIZE = 0;
	
	//Constructors
	/**
	 * The default constructor for making my array list. It has establishes 10 as the 
	 * capacity since no capacity is specified
	 */
	public MyArrayList() {
		data = new Object[DEFAULT_CAPACITY];
		size = DEFAULT_SIZE;
	}
	/**
	 * The constructor used to make an Array list of a specified capacity.
	 * 
	 * This also throws an IllegalArgumentException if the argument is less than 0
	 * since an ArrayList can't have a capacity of less than 0.
	 * @param initialCapacity - the starting capacity that the ArrayList can hold
	 */
	public MyArrayList(int initialCapacity){
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Initial Capacity can't be less than 0");
		}
		data = new Object[initialCapacity];
		size = DEFAULT_SIZE;
	}
	/**
	 * The constructor used to make an array list of the same size as the 
	 * specified generic array. The capacity is also going to be the same 
	 * size as the length of the generic array.
	 * @param arr - generic array
	 */
	public MyArrayList (E[] arr) {
		
		// If arr is null, I will just treat the constructor as a 
		// a no argument constructor
		if (arr == null) {
			data = new Object[DEFAULT_CAPACITY];
			size = DEFAULT_SIZE;
		}
		else {
			data = new Object[arr.length];
			size = arr.length;
			for (int i = 0; i < arr.length; i++) {
				data[i] = arr[i];
			}
		}
	}
	
	//Methods
	
	@Override
	/**
     * Increase the capacity of underlying array if needed 
     * 
     * @param requiredCapacity - the capacity that the user needs
     * @return void
     */
    public void checkCapacity(int requiredCapacity) {
    	if (data.length < requiredCapacity) {
    		// Making another Object array and copying all my data onto it.
    		Object[] temp = new Object[two*data.length];
    		for (int i = 0; i < data.length; i++) {
    			temp[i] = data[i];
    		}
    		data = temp;
    		if (data.length < requiredCapacity) {
    			// Making another Object array and copying all my data onto it.
    			Object[] temp1 = new Object[requiredCapacity];
    			for (int i = 0; i < data.length; i++) {
    				temp1[i] = data[i];
    			}
    			data = temp1;
    		}
    	}
    }
	@Override
    /**
     * Get the amount of elements array can hold 
     * 
     * @return int - the amount of elements the array can hold
     */
    public int getCapacity() {
    	return data.length;
    }
	@Override
    /**
     * Add an element at the specified index 
     * 
     * @param index - the position where I want me item to be in
     * @param element - the data I want to insert
     * @return void
     */
    public void insert(int index, E element) {
    	if (index < 0 || index > size) {
    		throw new IndexOutOfBoundsException("Index is less than 0 or greater than the size");
    	}
    	if (size == data.length) {
    		this.checkCapacity(data.length + 1);
    	}
    	// Making another Object array and copying all my data onto it
    	Object[] temp = new Object[data.length];
    	//Copy before item
    	for (int i = 0; i < index; i++) {
    		temp[i] = data[i];
    	}
    	//Insert item
    	temp[index] = (Object) element;
    	//Copy after item
    	
    	for (int i = index; i < data.length-1; i++) {
    		temp[i+1] = data[i];
    	}
    	//Now setting my data array to equal the new Object array made
    	data = temp;
    	size++;
    }
	@Override
    /** 
     * Add an element to the end of the list
     * 
     * @param element - the data I want to insert
     * @return void
     */
    public void append(E element) {
    	/* I can just use my insert method and place the index 
    	 * at the end of the all my valid elements
    	 */
    	this.insert(this.size, element);
    }
	@Override
    /**
     * Add an element to the beginning of the list 
     * 
     * @param element - the data (or object) I want to insert
     * @return void
     */
    public void prepend(E element) {
    	this.insert(0, element);
    }
	@SuppressWarnings("unchecked")
	@Override
    /** 
     * Get the element at the given index 
     * 
     * @param index - the position I want to get the data from
     * @return E - the data from the position in the List
     */
    public E get(int index) {
    	if (index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException("Index is less than 0 or greater than or equal to the size");
    	}
    	return((E)data[index]);
    }
    @Override
    /** 
     * Replaces an element at the specified index with a new 
     * element and return the original elements
     * 
     * @param index - the position where I want to replace the element with 
     * the new element
     * @param element - the new element I want in the index
     * @return E - the element that was originally at the position, but is now replaced
     */
    public E set(int index, E element) {
    	if (index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException("Index is less than 0 or greater than or equal to the size");
    	}
    	// 
    	E dataToReturn = this.get(index);
    	data[index] = element;
    	return dataToReturn;
    	
    	
    }
    @SuppressWarnings("unchecked")
    @Override
    /** 
     * Remove the element at the specified index and return the removed element
     * 
     * @param index - the position of the data I want to remove from the array
     * @return E - the element that was removed
     */
    public E remove(int index) {
    	if (index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException("Index is less than 0 or greater than or equal to the size");
    	}
    	// Keeping a record of the element before I replace it
    	E dataToReturn = (E)(data[index]);
    	// Make a (temporary reference for a new) Object array to copy all my data
    	Object[] temp = new Object[data.length];
    	for (int i = 0; i < index; i++) {
    		temp[i] = data[i];
    	}
    	for (int i = index; i < data.length-1; i++) {
    		temp[i] = data[i+1];
    	}
    	size--;		
    	// Reassigning my data array to the new Object array
    	data = temp; 
    	return dataToReturn;
    }
    @Override
    /**
     * Get the number of elements in the list
     * 
     * @return int - the amount of elements in the list
     */
    public int size() {
    	
    	return(size);
    }
    @Override
	/**
     * Adjust the capacity to match the number of elements in the array
     * 
     * @return void
     */
    public void trimToSize() {
    	// Make a new object array to copy data onto
    	Object[] temp = new Object[size];
    	for (int i = 0; i < size; i++) {
    		temp[i] = data[i];
    	}
    	data = temp;
    }
	public static void main(String[] args) {
		MyArrayList<Integer> keith = new MyArrayList<Integer>();
		keith.append(3);
	}
}
