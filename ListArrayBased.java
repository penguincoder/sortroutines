
// ********************************************************
// Array-based implementation of the ADT list.
// *********************************************************

import java.util.Iterator;

public class ListArrayBased implements ListInterface
{

  private int maxList;
  private Object[] items;  // an array of list items
  private int size;  // number of items in list

  public ListArrayBased() 
  {
    maxList=2;
    size = 0;
    items = new Object[maxList];
  }  // end default constructor
   
  public boolean isEmpty() 
  {
    return (size == 0);
  } // end isEmpty
   
  public int size() 
  {
    return size;
  }  // end size
   
  public void removeAll() 
  {
    // Creates a new array; marks old array for 
    // garbage collection.
    maxList=2;
    items = new Object[maxList];
    size = 0;
  } // end removeAll

  public void add(int index, Object item) throws  ListException 
  {
    if (size >= maxList) 
    {
      //throw new ListException("List full.");
      arrayResize();
    }  // end if

    if (index >= 1 && index <= size+1) 
    {
      // make room for new element by shifting all items at 
      // positions >= index toward the end of the 
      // list (no shift if index == size+1)
      for (int pos = size; pos >= index; pos--) 
      {
          items[pos] = items[pos-1];
      } // end for
      // insert new item
      items[index-1] = item;
      size++;
    } 
    else 
    {  // index out of range
      throw new ListException("List index out of range.");
    }  // end if

  } //end add
   
  public Object get(int index) throws ListException 
  {

    if (index >= 1 && index <= size) 
    {
      return items[index-1];
    }
    else  
    {  // index out of range
      throw new ListException("List index out of range.");
    }  // end if

  } // end get
   
  public void remove(int index) throws ListException 
  {
    if (index >= 1 && index <= size) 
    {
      // delete item by shifting all items at 
      // positions > index toward the beginning of the list
      // (no shift if index == size)
      for (int pos = index; pos < size(); pos++) 
      {
        items[pos-1] = items[pos];
      }  // end for
      items[size()-1]=null; //eliminate dangling references
      size--;
    }
    else 
    {  // index out of range
        throw new ListException("List index out of range.");
    }  // end if
  } //end remove

  private void arrayResize()
  {
     maxList*=2;
     Object[] temp=new Object[maxList];

     for (int x=0;x<size;x++)
     {
        temp[x]=items[x];
     }

     items=temp;
  }

  public Iterator iterator()
  {
     return new ListIterator(this);
  }

  public Object[] toArray()
  {
     Object[] tempArray=new Object[size()];
     for (int x=0;x<size();x++)
     {
        tempArray[x]=get(x+1);
     }
     return tempArray;
  }

}  // end ListArrayBased
