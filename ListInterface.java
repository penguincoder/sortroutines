
import java.util.Iterator;

/**
 * <b><code>ListInterface</code></b> provides the user interface for the general list.
 *
 * @author
 *   <a href="mailto:mboshart@tntech.edu">Mark Boshart</a>
 * @version 1.0
 */
public interface ListInterface 
{
    /**
     *   Determines whether a list is empty. <br>
     *   Precondition: None. <br>
     *   Postcondition: Returns true if the list is empty, otherwise returns false. <br>
     *   Throws: None.
     */
  public boolean isEmpty();

    /**
     *   Determines the length of a list. <br>
     *   Precondition: None. <br>
     *   Postcondition: Returns the number of items that are currently in the list. <br>
     *   Throws: None.
     */
  public int size();

    /**
     *   Adds an item to the list at position index. <br>
     *   Precondition: index indicates the position at which the item should be inserted in the list. <br>
     *   Postcondition: If insertion is successful, item is at position index in the list, and other items are renumbered accordingly. <br>
     *   Throws: ListException if index < 1 or index > size()+1. <br>
     *   Throws: ListException if item cannot be placed on the list.
     *
     * @param index <code>int</code> position to insert the object.
     * @param item <code>Object</code> object to be inserted.
     */
  public void add(int index, Object item) throws ListException;

    /**
     *   Retrieves a list item by position. <br>
     *   Precondition: index is the number of the item to be retrieved. <br>
     *   Postcondition: If 1 <= index <= size(), the item at position index in the list is returned. <br>
     *   Throws: ListException if index < 1 or index > size(). <br>
     *
     * @param index <code>int</code> position to retrieve the object.
     */
  public Object get(int index) throws ListException;

    /**
     *   Deletes an item from the list at a given position. <br>
     *   Precondition: index indicates where the deletion should occur. <br>
     *   Postcondition: If 1 <= index <= size(), the item at position index in the list is deleted, and other items are renumbered accordingly. <br>
     *   Throws: ListException if index < 1 or index > size(). <br>
     *
     * @param index <code>int</code> position to remove the object.
     */
  public void remove(int index) throws ListException;

    /**
     *   Deletes all the items from the list. <br>
     *   Precondition: None. <br>
     *   Postcondition: The list is empty. <br>
     *   Throws: None.
     */
  public void removeAll();

    /**
     *   Returns an Iterator for the list. <br>
     *   Precondition: None. <br>
     *   Postcondition: An Iterator is returned. <br>
     *   Throws: None.
     */
  public Iterator iterator();

}  // end ListInterface
