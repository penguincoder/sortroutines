//import trees.*;
//import queues.*;

public class SortRoutines
{


   // Finds the largest item in an array.
   // Precondition: theArray is an array of size items;
   // size >= 1.
   // Postcondition: Returns the index of the largest 
   // item in the array.
   private static int indexOfLargest(Comparable[] theArray, int size) 
   {
     int indexSoFar = 0; // index of largest item found so far
     // Invariant: theArray[indexSoFar]>=theArray[0..currIndex-1]
     for (int currIndex = 1; currIndex < size; ++currIndex) 
     {  
       if (theArray[currIndex].compareTo(theArray[indexSoFar])>0) 
       { 
         indexSoFar = currIndex;
       }  // end if
     } // end for
  
     return indexSoFar;  // index of largest item
   }  // end indexOfLargest

   // Sorts the items in an array into ascending order.
   // Precondition: theArray is an array of n items.
   // Postcondition: theArray is sorted into 
   // ascending order.
   // Calls: indexOfLargest.
   public static void selectionSort(Comparable[] theArray, int n) 
   {
     // last = index of the last item in the subarray of 
     //        items yet to be sorted
     // largest = index of the largest item found

     Comparable temp;

     for (int last = n-1; last >= 1; last--) //all but 
     {
       // Invariant: theArray[last+1..n-1] is sorted 
       // and > theArray[0..last]

       // select largest item in theArray[0..last]
       int largest = indexOfLargest(theArray, last+1);

       // swap largest item theArray[largest] with 
       // theArray[last]
       temp = theArray[largest];
       theArray[largest] = theArray[last];
       theArray[last] = temp;
     }  // end for

   }  // end selectionSort

   // Sorts the items in an array into ascending order.
   // Precondition: theArray is an array of n items.
   // Postcondition: theArray is sorted into ascending
   // order.
   public static void insertionSort(Comparable[] theArray, int n) 
   {
     // unsorted = first index of the unsorted region, 
     // loc = index of insertion in the sorted region, 
     // nextItem = next item in the unsorted region

     // initially, sorted region is theArray[0], 
     //          unsorted region is theArray[1..n-1];
     for (int unsorted = 1; unsorted < n; ++unsorted) 
     {
       // Invariant: theArray[0..unsorted-1] is sorted

       // find the right position (loc) in 
       // theArray[0..unsorted] for theArray[unsorted],  
       // which is the first item in the unsorted  
       // region; shift, if necessary, to make room
       Comparable nextItem = theArray[unsorted];
       int loc = unsorted;

       while ((loc > 0) && (theArray[loc-1].compareTo(nextItem) > 0)) 
       {
         // shift theArray[loc-1] to the right
         theArray[loc] = theArray[loc-1];
         loc--;
       }  // end while
       // insert nextItem into sorted region
       theArray[loc] = nextItem;
     }  // end for

   }  // end insertionSort

   // Sorts the items in an array into ascending order.
   // Precondition: theArray is an array of n items.
   // Postcondition: theArray is sorted into ascending 
   // order.
   public static void bubbleSort(Comparable[] theArray, int n) 
   {
     boolean sorted = false;  // false when swaps occur
     for (int pass = 1; (pass < n) && !sorted; ++pass) 
     {
       // Invariant: theArray[n+1-pass..n-1] is sorted
       //            and > theArray[0..n-pass]
       sorted = true;  // assume sorted
       for (int index = 0; index < n-pass; ++index) 
       {
         // Invariant: theArray[0..index-1] <= theArray[index]
         int nextIndex = index + 1;  
         if (theArray[index].compareTo(theArray[nextIndex]) > 0) 
         {
           // exchange items
           Comparable temp = theArray[index];
           theArray[index] = theArray[nextIndex];
           theArray[nextIndex] = temp;
           sorted = false;  // signal exchange
         }  // end if
       }  // end for

       // Assertion: theArray[0..n-pass-1] < theArray[n-pass]
     }  // end for

   }  // end bubbleSort

   // Merges two sorted array segments theArray[first..mid] and 
   // theArray[mid+1..last] into one sorted array.
   // Precondition: first <= mid <= last. The subarrays 
   // theArray[first..mid] and theArray[mid+1..last] are 
   // each sorted in increasing order.
   // Postcondition: theArray[first..last] is sorted.
   // Implementation note: This method merges the two
   // subarrays into a temporary array and copies the result
   // into the original array anArray.
   private static void merge(Comparable[] theArray, int first, int mid, int last) 
   {
     int maxSize = theArray.length;
     // temporary array
     Comparable[] tempArray = new Comparable[maxSize]; 

     // initialize the local indexes to indicate the subarrays
     int first1 = first;    // beginning of first subarray
     int last1  = mid;      // end of first subarray
     int first2 = mid + 1;  // beginning of second subarray
     int last2  = last;     // end of second subarray
     // while both subarrays are not empty, copy the
     // smaller item into the temporary array
     int index = first1;    // next available location in 
                         // tempArray
     while ((first1 <= last1) && (first2 <= last2)) 
     {
       // Invariant: tempArray[first1..index-1] is in order
       if (theArray[first1].compareTo(theArray[first2])<=0)  //careful here for stable sorting
       {
         tempArray[index] = theArray[first1];
         first1++;
       }
       else 
       {
         tempArray[index] = theArray[first2];
         first2++;
       }  // end if

       index++;
     }  // end while

     // finish off the nonempty subarray

     // finish off the first subarray, if necessary
     while (first1 <= last1) 
     {
       // Invariant: tempArray[first1..index-1] is in order
       tempArray[index] = theArray[first1];
       first1++;
       index++;
     }  // end while

     // finish off the second subarray, if necessary
     while (first2 <= last2)
     {
       // Invariant: tempArray[first1..index-1] is in order
       tempArray[index] = theArray[first2];
       first2++;
       index++;
     }  // end while

     // copy the result back into the original array
     for (index = first; index <= last; ++index) 
     {
       theArray[index] = tempArray[index];
     }  // end for
   }  // end merge

   // Sorts the items in an array into ascending order. 
   // Precondition: theArray[first..last] is an array.
   // Postcondition: theArray[first..last] is sorted in 
   // ascending order.
   // Calls: merge.
   public static void mergeSort(Comparable[] theArray, int first, int last) 
   {
     if (first < last) 
     {
       // sort each half
       int mid = (first + last)/2;   // index of midpoint
       // sort left half theArray[first..mid]
       mergeSort(theArray, first, mid);
       // sort right half theArray[mid+1..last]   
       mergeSort(theArray, mid+1, last);  
 
       // merge the two halves
       merge(theArray, first, mid, last);
     }  // end if
   }  // end mergesort

   // Sorts the items in an array into ascending order.
   // Precondition: theArray[first..last] is an array.
   // Postcondition: theArray[first..last] is sorted.
   // Calls: partition.
   public static void quickSort(Comparable[] theArray, int first, int last) 
   {
     int pivotIndex;

     if (first < last)
     {
       // create the partition: S1, Pivot, S2
       pivotIndex = partition(theArray, first, last);

       // sort regions S1 and S2
       quickSort(theArray, first, pivotIndex-1);
       quickSort(theArray, pivotIndex+1, last);
     }  // end if
   }  // end quickSort

   // Partitions an array for quicksort.
   // Precondition: theArray[first..last] is an array; 
   // first <= last.
   // Postcondition: Returns the index of the pivot element of
   // theArray[first..last]. Upon completion of the method, 
   // this will be the index value lastS1 such that
   //    S1 = theArray[first..lastS1-1] <  pivot
   //         theArray[lastS1]          == pivot
   //    S2 = theArray[lastS1+1..last]  >= pivot
   // Calls: choosePivot.
   private static int partition(Comparable[] theArray, int first, int last) 
   {
     // tempItem is used to swap elements in the array
     Comparable tempItem; 
     // place pivot in theArray[first]             
     choosePivot(theArray, first, last);      
     Comparable pivot = theArray[first];   // reference pivot

     // initially, everything but pivot is in unknown
     int lastS1 = first;          // index of last item in S1

     // move one item at a time until unknown region is empty

     for (int firstUnknown = first + 1; firstUnknown <= last; ++firstUnknown) 
     {
       // Invariant: theArray[first+1..lastS1] < pivot
       //            theArray[lastS1+1..firstUnknown-1] >= pivot

       // move item from unknown to proper region
       if (theArray[firstUnknown].compareTo(pivot) < 0) 
       {
         // item from unknown belongs in S1
         ++lastS1;
         tempItem = theArray[firstUnknown];
         theArray[firstUnknown] = theArray[lastS1];
         theArray[lastS1] = tempItem;
       }  // end if
     // else item from unknown belongs in S2
     }  // end for

     // place pivot in proper position and mark its location
     tempItem = theArray[first];
     theArray[first] = theArray[lastS1];
     theArray[lastS1] = tempItem;
     return lastS1;
   }  // end partition

   // Chooses a pivot for quicksort's partition algorithm and 
   // swaps it with the first item in an array.
   // Precondition: theArray[first..last] is an array; 
   // first <= last.
   // Postcondition: theArray[first] is the pivot.
   private static void choosePivot(Comparable[] theArray, int first, int last) 
   {

      // Implementation left as an exercise.

   }  // end choosePivot

   public static void shellSort(Comparable[] theArray, int n) 
   {
      int loc;
      Comparable nextItem;
      for (int h = n/2; h > 0; h = h/2) 
      {
        for (int unsorted = h; unsorted < n; ++unsorted) 
        {
          nextItem = theArray[unsorted];
          loc = unsorted;
          while ((loc >= h) && (theArray[loc-h].compareTo(nextItem) > 0) ) 
          {
            theArray[loc] = theArray[loc-h];
            loc = loc - h;
          }  // end while
          theArray[loc] = nextItem;
       }  // end for unsorted
     }  // end for h

   }  // end shellsort
   

	/**
	 * Sorts an array of Radixable elements radixably. <BR>
	 * Preconditions: An array of Radixable objects, an integer of the number of keys to sort by, and an integer of the number of elements to sort. <BR>
	 * Postconditions: Returns a new array of the sorted elements. <BR>
	 * @author <A HREF="mailto:arc4472@tntech.edu">Andrew Coleman</A><BR>
	 * @hidden All your base are belong to us.
	 */
	public static void radixSort(Radixable[] array, int index, int n)	{
		/* the maximum size of the bin array */
		/* 26 letters, 10 digits, 1 extraneous character */
		final int MAX_SIZE = 26 + 10 + 1;
		/* our array for the sorting bins */
		QueueInterface[] bins = new QueueInterface[MAX_SIZE];
		/* the array to return */
		//Radixable[] result = new Radixable[n];
		/* an array to keep the size of each bin at the end of each iteration */
		int[] binsize = new int[MAX_SIZE];
		for (int i = 0; i < MAX_SIZE; i++)	{binsize[i] = 0;bins[i] = new QueueReferenceBased();}
		/* Strings are indexed with an offset of +1 */
		//index--;
		/* these next two hunks of loopage are basically the same, but this way is a big time saver
			instead of dumping the original array into a queue/array, i directly sort it into the bins
			here and the elements are not taken out of the bins until i return the result array.
			I have tried using one big loop and flags, but i get null for every item when i get done sorting.
			Besides, it saves on having to do all that extra logic checks a few hundred thousand times */
		for (int iter = 0; iter < n; iter++)	{
			/* this is the only line that differs between these two loops, here it comes from the array */
			Radixable data = array[iter];
			/* get an ascii value of the character */
			int	bin = (int) data.getRadixChar(index);
			/* capital letters converted to lowercase */
			if (bin >= 65 && bin <= 90)
				bin += 32;
			/* lowercase letters */
			if (bin >= 97 && bin <= 122)
				bin -= 86;
			/* numbers */
			else if (bin >=48 && bin <= 57)
				bin -= 47;
			/* non alphanumeric characters */
			else
				bin = 0;
			bins[bin].enqueue(data);
		}

		/* already sorted the first character */
		index--;
		/* loop through remaining search keys */
		for (int loop = index; loop >= 0; loop--)	{
			/* save the size of each bin */
			for (int i = 0; i < MAX_SIZE; i++)	{binsize[i] = bins[i].size();}
			/* loop through each bin */
			for (int iter = 0; iter < MAX_SIZE; iter++)	{
				/* loop through the number of items saved in binsize
					now you don't have to dump the elements into another queue/array */
				for (int eachbinitem = 0; eachbinitem < binsize[iter]; eachbinitem++)	{
					/* get data from the bins rather than the array */
					Radixable data = (Radixable) bins[iter].dequeue();
					int	bin = (int) data.getRadixChar(loop);
					if (bin >= 65 && bin <= 90)
						bin += 32;
					if (bin >= 97 && bin <= 122)
						bin -= 86;
					else if (bin >=48 && bin <= 57)
						bin -= 47;
					else
						bin = 0;
					bins[bin].enqueue(data);
				}
			}
		}
		
		/* keeps up with the total number of elements placed in the array (always < n) */
		//int count = 0;
		/* loop through each bin */
		//for (int i = 0; i < MAX_SIZE; i++)
			/* keep dumping the items inside until it's all gone */
		//	while (!bins[i].isEmpty())	{
		//		result[count] = (Radixable) bins[i].dequeue();
		//		count++;
		//	}
		/* bye bye */
		//return result;
		/* this is sorting 200,000 strings (made by a random word generator in a Tolkien wordset) in
			about 1282 milliseconds by my primitive timing methods and by comparison i made a radixsort
			earlier that dumps everything into arrays was coming in at > 1600 milliseconds.
		*/
   }
}