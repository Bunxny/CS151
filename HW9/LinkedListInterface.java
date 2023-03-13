/**
 * An interface for Linked Lists
 * @Author gtowell
 * Created: Nov 2020
 * Modified: April 2021
 * @param <M>
 */
public interface LinkedListInterface<M>
{
    /**
     * Return the number of items in the linked list
     * @return the number of items in the list
     */
    int size();
    /**
     * true if the list is empty
     * @return true iff the list if empty
     */
    boolean isEmpty();

    /**
     * The first item in the list
     * @return the first item in the list
     */
    M first();

    /**
     * the last item inthe list
     * @return the last item
     */
    M last();

    /**
     * Add a new data item at the end of the list
     * @param c the data item to be added
     */
    void addLast(M c);

    /**
     * Add a new data item at the beginning of the list
     * @param c the item to be added
     */
    void addFirst(M c);

    /**
     * Remove the data item from the beginning of the list
     * @return the data item removed (or null)
     */
    M removeFirst();

    /**
     * Remove data item from end of list 
     * @return the data item removed (or null)
     */
    M removeLast();

    /**
     * Remove a data item from the list
     * @param r the item to be removed
     * @return true if the item was int he list, and therefore was removed.
     */
    boolean remove(M r);
}