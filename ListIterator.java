
import java.util.Iterator;

class ListIterator implements Iterator
{
   private int index;
   private int check;
   private ListInterface list;

   public ListIterator(ListInterface list)
   {
      index=1;
      check=list.size();
      this.list=list;
   }

   private void check()
   {
      if (check!=list.size())
      {
         //reset iteration to the start of the list
         index=1;
         check=list.size();
         throw new ListException("Change made to collection during iteration.");
         //check can miss alterations to the list if add/remove combinations are used
      }
   }

   public void remove()
   {   
      //to be implemented
   }

   public boolean hasNext()
   {
      check();
      return (index<=check);
   }

   public Object next()
   {
      check();

      if (hasNext())
      {
         index++;
         return list.get(index-1);
      }
      else
      {
         throw new ListException("End of list.");
      }
   }
}