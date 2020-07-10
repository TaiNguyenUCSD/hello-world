/**
 * An interface that specifies the familiar queue abstraction.
 *
 * In addition to the methods required in the definition of this interface,
 * a class implementing this interface should provide a public constructor
 * with a single argument of type int, which specifies the initial
 * capacity of the QueueInterface.  The constructor should throw an
 * IllegalArgumentException if the specified capacity is negative.
 */
public interface QueueInterface<E> {

    /**
     * Checks whether or not the queue is empty.
     * PRECONDITION: none
     * POSTCONDITION: the StackInterface is unchanged.
     * @return True if there are no elements in the QueueInterface, false 
     * otherwise.
     */
    public boolean empty();

    /**
     * Adds the specified element to the tail of this QueueInterface.
     * PRECONDITION: none
     * POSTCONDITION: if the MyQueue is at capacity, the capacity of this
     * container is doubled. The element is now the tail element in this
     * QueueInterface, none of the other elements have been changed, and
     * the size is increased by 1. 
     * @param element the element to add to the queue
     * @throws NullPointerException if the specified element is null.
     */
    public void enqueue(E element);

    /**
     * Removes the element at the head of this QueueInterface.
     * Returns the element removed, or null if there was no such element.
     * PRECONDITION: the QueueInterface's size is greater than zero.
     * POSTCONDITION: the head element in this QueueInterface has been removed,
     * none of the other elements have been changed, and
     * the size is decreased by 1.
     * @return  the element removed, or null if the size was zero.
     */
    public E dequeue();

    /**
     * Returns the element at the head of this QueueInterface,
     * or null if there was no such element.
     * PRECONDITION: the QueueInterface's size is greater than zero.
     * POSTCONDITION: The QueueInterface is unchanged.
     * @return  the element at the head, or null if the size was zero.
     */
    public E peek();

}
