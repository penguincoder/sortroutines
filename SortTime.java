import java.util.Random;
import java.awt.*;

public class SortTime
{
   private static Graph myGraph;
   private static Object[] original;
   private static Object[] originalSorted;
   private Object[] sorted;
   private static int MAX;

   public SortTime (int xscale,int yscale, String graphTitle)
   {
      //create the graph object with the specified x-axis, y-axis, and title
      myGraph=new Graph(xscale, yscale, graphTitle);
      MAX=2*xscale;
      Random rand=new Random();
 
      //create an array of CDs that have random titles for sorting
      original=new CD[MAX];
      originalSorted=new CD[MAX];

      for (int y=0;y<original.length;y++)
      {
         String title="";
         for (int x=0;x<5;x++)
         {
            char myChar;
            int temp=Math.abs(rand.nextInt())%36;
            if (temp<10)
            {
               myChar=(char) (temp+48);
            }
            else
            {
               myChar=(char) (temp+87);
            }
            title=title+myChar;
         }
         original[y]=new CD(title,"Rush",12.99,11);
      }

      for (int y=0;y<original.length;y++)
      {
         originalSorted[y]=original[y];
      }

      //need a sorted version to test quicksort in the worst case
      SortRoutines.quickSort((Comparable[])originalSorted,0,original.length-1);
   }

   public void doTask(Object[] myArray,int n,int choice)
   {
      switch (choice)
      {
         //here is where we call the various sorting techniques
         //explain why the parameter lists appear as they do
         case 1:
            SortRoutines.selectionSort((Comparable[])myArray,n);
            break;
         case 2:
            SortRoutines.insertionSort((Comparable[])myArray,n);
            break;
         case 3:
            SortRoutines.bubbleSort((Comparable[])myArray,n);
            break;
         case 4:
            SortRoutines.mergeSort((Comparable[])myArray,0,n-1);
            break;
         case 5:
            SortRoutines.quickSort((Comparable[])myArray,0,n-1);
            break;
		case 6:
			SortRoutines.radixSort((Radixable[])myArray, 4, n);
			break;
      }
   }

   public void go (int start,int stop, int step, String title, Color myColor, int choice)
   {
      sorted=new CD[MAX];

      //creates a new set of data with the specified color
      DataVector myData=new DataVector(title,myColor);
      myGraph.addData(myData);

      //prevents array overflow (can't go higher than MAX)
      //otherwise the user could ask for a simulation range larger than the array can support
      if (stop>myGraph.getX()*2)  
         stop=myGraph.getX()*2;

      //start over with an unsorted array, but the number of items to sort will increase
      for (int i=start; i<stop; i+=step)  
      {
         //start again with an unsorted array
         for (int x=0;x<original.length;x++)
         {
            //switch the comments below to test quicksort worst case
            sorted[x]=original[x];
            //sorted[x]=originalSorted[x];
         }
         //try to garbage collect the old array that is no longer being used
         System.gc();

         //here is the actual timing calculation
         long startTime=System.currentTimeMillis();
         doTask(sorted,i,choice);
         long stopTime=System.currentTimeMillis();
         int time=(int) (stopTime-startTime);

         //add a point to the data set
         myData.addPoint(i, time);

         //if we exceed the boundaries of the graph, reset Graph scale
         if (i > myGraph.getX())  
            myGraph.setX(i + i/10);
         if (time > myGraph.getY())
            myGraph.setY(time + time/20);

         //show the updated graph
         myGraph.repaint();
      }

   }

   public static void main(String[] args)
   {
      //set up the graph with the x-range, y-range, and title
      SortTime timer=new SortTime(10000,2000,"Sorting Times");

      //run simulations for various sorting techinques 
      //starting number to sort, ending number to sort, step size for simulation, title of set of points, color of set of points, sorting algorithm to use (see above)
      timer.go(1,1900,100,"Selection",Color.blue,1);
      timer.go(1,1900,100,"Insertion",Color.red,2);
      timer.go(1,1900,100,"Bubble",Color.yellow,3);

      timer.go(1,5000,100,"Merge",Color.green,4);
      timer.go(1,10000,100,"Quick",Color.white,5);
	  timer.go(1,10000,100,"Radix",Color.gray,6);
   }
  
}

