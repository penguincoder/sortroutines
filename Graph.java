import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Graph extends JFrame
{

   private DataVector[] points=new DataVector[10];  //max number of data series possible
   private int xaxis;
   private int yaxis;
   private final double XSIZE;  //size of the monitor in pixels
   private final double YSIZE;
   private final int CIRCLESIZE=5;  //size of the plotted points
   private int numArrays=0;

   public Graph(int xaxis, int yaxis, String title)
   {
      this.xaxis=xaxis;
      this.yaxis=yaxis;
      XSIZE = Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20;
      YSIZE = Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 40;
      setSize((int) XSIZE,(int) YSIZE);
      setTitle(title);
      addWindowListener(new GenericWindowListener());

      //center the window
      Dimension ScreenSize=getToolkit().getScreenSize();
      int screenWidth=ScreenSize.width - 20;
      int screenHeight=ScreenSize.height - 40;
      setLocation((screenWidth + 10)/2-(int) XSIZE/2,(screenHeight + 20)/2-(int) YSIZE/2);
      getContentPane().add(new GraphPanel());
      show();
   }

   public int getX()
   {
      return xaxis;
   }

   public int getY()
   {
      return yaxis;
   }

   public void setX(int newX)
   {
      xaxis = newX;
   }

   public void setY(int newY)
   {
      yaxis = newY;
   }

   public class GraphPanel extends JPanel
   {
      public void paint(Graphics g)
      {
         int xtemp=0;
         int ytemp=0;
         g.setColor(Color.black);
         g.fillRect(0,0,(int) XSIZE,(int) YSIZE);
         //convert from x,y values to proper screen coordinates
         for (int y=0;y<numArrays;y++)
         {
            g.setColor(points[y].getColor());
            for (int x=0;x<points[y].size();x++)
            {
               xtemp=(int) (points[y].getX(x)*XSIZE/xaxis);
               ytemp=(int) (YSIZE- points[y].getY(x)*YSIZE/yaxis);
               ytemp-=30;  //want to be able to see the lowest points
               g.fillOval(xtemp,ytemp,CIRCLESIZE,CIRCLESIZE);
            }

            g.drawString(points[y].getTitle(),xtemp-50,ytemp-20);
         }
      }      
   }

   public void addData(DataVector points)
   {
      this.points[numArrays]=points;
      numArrays++;
   }

}