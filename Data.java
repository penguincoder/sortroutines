public class Data
{

   private double x;
   private double y;

   public Data(double x,double y)
   {
      this.x=x;
      this.y=y;
   }
   
   public double getX()
   {
      return x;
   }

   public double getY()
   {
      return y;
   }

   public boolean equals(Data otherData)
   {
      if (x==otherData.getX() && y==otherData.getY())
         return true;
      else 
         return false;
   }

}