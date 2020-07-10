/**
 * A DequeInterface is a sequential structure with restricted access.
 *
 * Access is available only at the ends of the structure:
 * addFirst(E), E removeFirst(), and E peekFirst()
 * operate on the first element of the list;
 * addLast(E), E removeLast(), and E peekLast()
 * operate on the last element of the list.
 *
 * (A sequential structure which, like DequeInterface, permits access
 * and modification only at the ends is sometimes called a "deque",
 * pronounced "deck", which is short for "double-ended queue.")
 *
 * An implementation of DequeInterface must allow duplicate elements,
 * but must not permit null elements, since some of the methods
 * use null as a signaling return value.
 *
 * In addition to the methods required in the definition of this interface,
 * a class implementing this interface should provide a public constructor
 * with a single argument of type int, which specifies the initial
 * capacity of the DequeInterface.  The constructor should throw an
 * IllegalArgumentException if the specified capacity is negative.
 */
public interface DequeInterface<E>  {

    /**
     * Returns the number of elements in this DequeInterface.
     * PRECONDITION: none
     * POSTCONDITION: the DequeInterface is unchanged.
     * @return the number of elements in this DequeInterface
     */
    public int size();

    /**
     * Doubles the capacity of this DequeInterface.
     * PRECONDITION: none
     * POSTCONDITION: the DequeInterface's capacity is now doubled and
     * maintains the same elements. No elements have changed. If the capacity
     * is 0, set capacity to some default capacity.
     */
    public void expandCapacity();

    /**
     * Adds the specified element to the front of this DequeInterface.
     * PRECONDITION: none
     * POSTCONDITION: if the MyDeque is at capacity, expandCapacity is called 
     * to double the size of this container. The element is now the front
     * element in this DequeInterface, none of the other elements have been
     * changed, and the size is increased by 1.
     * @param element the element to add to the front of the array
     * @throws NullPointerException if the specified element is null.
     */
    public void addFirst(E element);

    /**
     * Adds the specified element to the back of this DequeInterface.
     * PRECONDITION: none
     * POSTCONDITION: if the MyDeque is at capacity, expandCapacity is called
     * to double the size of this container. The element is now the back element
     * in this DequeInterface, none of the other elements have been changed, and
     * the size is increased by 1.
     * @param element the element to add to the back of the array
     * @throws NullPointerException if the specified element is null.
     */
    public void addLast(E element);


    /**
     * Removes the element at the front of this DequeInterface.
     * Returns the element removed, or null if there was no such element.
     * PRECONDITION: the DequeInterface's size is greater than zero.
     * POSTCONDITION: the front element in this DequeInterface has been removed,
     * none of the other elements have been changed, and
     * the size is decreased by 1.
     * @return  the element removed, or null if the size was zero.
     */
    public E removeFirst();

    /**
     * Removes the element at the back of this DequeInterface.
     * Returns the element removed, or null if there was no such element.
     * PRECONDITION: the DequeInterface's size is greater than zero.
     * POSTCONDITION: the back element in this DequeInterface has been removed,
     * none of the other elements have been changed, and
     * the size is decreased by 1.
     * @return  the element removed, or null if the size was zero.
     */
    public E removeLast();

    /**
     * Returns the element at the front of this DequeInterface,
     * or null if there was no such element.
     * PRECONDITION: the DequeInterface's size is greater than zero.
     * POSTCONDITION: The DequeInterface is unchanged.
     * @return  the element at the front, or null if the size was zero.
     */
    public E peekFirst();

    /**
     * Returns the element at the back of this DequeInterface,
     * or null if there was no such element.
     * PRECONDITION: the DequeInterface's size is greater than zero.
     * POSTCONDITION: The DequeInterface is unchanged.
     * @return  the element at the back, or null if the size was zero.
     */
    public E peekLast();


}
