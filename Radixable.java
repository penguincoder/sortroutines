/**
 *  An interface for making objects easy to sort Radixably. <BR>
 *  @author <A HREF="mailto:arc4472@tntech.edu">Andrew Coleman</A>
 */
public interface Radixable	{
	/**
	 *  The only method needed to sort objects Radixably. <BR>
	 *  Preconditions: An index in the name that is being used to sort.
	 *  Postconditions: Returns a char at the index, or a space if the index is invalid.
	 */
	public char getRadixChar(int index);
}