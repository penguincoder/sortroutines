import java.awt.event.*;


public class GenericWindowListener extends WindowAdapter
{
   private static int num = 0;

   public GenericWindowListener()
   {
      num++;
   }

   public void windowClosing (WindowEvent event)
   {
      if (num == 1)
         System.exit(0);
      else
      {
         event.getWindow().dispose();
         num--;
      }
   }


}

