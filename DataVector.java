import java.util.*;
import java.awt.Color;

public class DataVector
{

   private Vector myVector;
   private int size;
   private Color myColor;
   private String title;

   public DataVector(String title,Color myColor)
   {
      this.title=title;
      myVector=new Vector();
      size=0;
      this.myColor=myColor;
   }
   
   public void addPoint(double x, double y)
   {
      myVector.add(new Data(x,y));
      size++;
   }

   public double getX(int index)
   {
      return ((Data) myVector.elementAt(index)).getX();
   }

   public double getY(int index)
   {
      return ((Data) myVector.elementAt(index)).getY();
   }

   public int size()
   {
      return size;
   }

   public String getTitle()
   {
      return title;
   }

   public Color getColor()
   {
      Color temp=new Color(myColor.getRed(),myColor.getGreen(),myColor.getBlue());
      return temp;
   }

   public void removePoint(double x, double y)
   {
      Data temp=new Data(x,y);

      for (int z=0;z<size();z++)
      {
         if ( temp.equals(myVector.elementAt(z)) )
            myVector.removeElementAt(z);
      }
   }

}