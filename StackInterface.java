/**
 * An interface that specifies the familiar stack abstraction.
 *
 * In addition to the methods required in the definition of this interface,
 * a class implementing this interface should provide a public constructor
 * with a single argument of type int, which specifies the initial
 * capacity of the StackInterface.  The constructor should throw an
 * IllegalArgumentException if the specified capacity is negative.
 */
public interface StackInterface<E> {

    /**
     * Checks whether or not the stack is empty.
     * PRECONDITION: none
     * POSTCONDITION: the StackInterface is unchanged.
     * @return True if there are no elements in the StackInterface, false 
     * otherwise.
     */
    public boolean empty();

    /**
     * Adds the specified element to the top of this StackInterface.
     * PRECONDITION: none
     * POSTCONDITION: if the MyStack is at capacity, the capacity of this
     * container is doubled. The element is now the top element in this
     * StackInterface, none of the other elements have been changed, and
     * the size is increased by 1.
     * @param element the element to add to the stack
     * @throws NullPointerException if the specified element is null.
     */
    public void push(E element);

    /**
     * Removes the element at the top of this StackInterface.
     * Returns the element removed, or null if there was no such element.
     * PRECONDITION: the StackInterface's size is greater than zero.
     * POSTCONDITION: the top element in this StackInterface has been removed,
     * none of the other elements have been changed, and
     * the size is decreased by 1.
     * @return  the element removed, or null if the size was zero.
     */
    public E pop();

    /**
     * Returns the element at the top of this StackInterface,
     * or null if there was no such element.
     * PRECONDITION: the StackInterface's size is greater than zero.
     * POSTCONDITION: The StackInterface is unchanged.
     * @return  the element at the top, or null if the size was zero.
     */
    public E peek();

}
