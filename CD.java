//********************************************************************
//  CD.java       Author: Lewis and Loftus
//
//  Represents a compact disc.
//********************************************************************

import java.text.DecimalFormat;

public class CD implements Comparable, Radixable
{
   private String title, artist;
   private double value;
   private int tracks;

   //-----------------------------------------------------------------
   //  Creates a new CD with the specified information.
   //-----------------------------------------------------------------
   public CD (String theTitle, String theArtist, double theValue, int theTracks)
   {
      title = theTitle;
      artist = theArtist;
      value = theValue;
      tracks = theTracks;
   }

   //-----------------------------------------------------------------
   //  Returns a description of this CD.
   //-----------------------------------------------------------------
   public String toString()
   {
      DecimalFormat fmt=new DecimalFormat("0.00");
      String description = "$" + fmt.format(value) + "  " + tracks + "  " + title + "  " + artist;
      return description;
   }

   public String getTitle()
   {
      return title;
   }

   public double getPrice()
   {
      return value;
   }

   public String getArtist()
   {
      return artist;
   }

   public int getTracks()
   {
      return tracks;
   }

   public void setTracks(int tempTracks)
   {
      tracks=tempTracks;
   }

   public void setPrice(double tempPrice)
   {
      value=tempPrice;
   }

   public int compareTo (Object other)
   {
      //define the way that CD's are compared-- in this case, alphabetically by title
      //comparing the objects is reduced to comparing the strings that make up the title
      int result;
      result = (this.getTitle().toLowerCase().compareTo( ((CD) other).getTitle().toLowerCase() ));
      return result;
   }

	public char getRadixChar(int index)	{
		try	{
			return title.charAt(index);
		}
		catch (StringIndexOutOfBoundsException e)	{
			return ' ';
		}
	}
}
