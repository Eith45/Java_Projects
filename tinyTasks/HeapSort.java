import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 27.02.14
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */

public class HeapSort {
    public static final int max_values = 1000000;
    /* heapSort(inputArray)
        Sort the array inputArray using using an array-based heap sort algorithm.
        You may add additional functions if needed, but the entire program must be
        contained in this file.

        No dynamic memory allocation is permitted or necessary.
        Do not use a linked data structure of any sort.
        Zero marks will be given to submissions which do not use an array-based
        heap, or which use a non-heap sorting algorithm.

        Do not change the function signature (name/parameters/return type).
    */

    public static int heapSize;

    public static int Left(int i){
        return (i << 1);
    }
    public static int Right(int i){
        return (i << 1) + 1;
    }

    public static void swap(int [] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void maxHeap(int [] A, int i){
        int left = Left(i);
        int right = Right(i);
        int largest;

        if(left <= heapSize && A[left] > A[i]){
            largest = left;
        }else{
            largest = i;
        }
        if(right <= heapSize && A[right] > A[largest]){
            largest = right;
        }
        if(largest != i){
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;
            maxHeap(A, largest);
        }
    }

    public static void heapSort(int[] inputArray){
		/* Create an array A which will contain the heap */
		/* A has size n+1 to allow 1-based indexing */
        int n = inputArray.length;
        int[] A = new int[n + 1];
		/* Copy the array inputArray into A, with inputArray[i] being stored in A[i+1] */
        for(int i = 1; i <= inputArray.length; i++){
            A[i] = inputArray[i - 1];
        }
		/* Transform A into a max-heap using a 'bottom-up' algorithm. */
		/* One such algorithm is given in Section 13 of the lab notes. The recursive version
		   is algorithm 17 and the iterative version is algorithm 18 */
        heapSize = A.length - 1;
        for(int i = heapSize/2; i >= 1; i--){
            maxHeap(A, i);
        }
		/* Perform a sequence of n 'remove-maximum' operations, storing the removed element at
		   each step in successively smaller indices of A (e.g. the first removed element
		   is stored in A[n], the second in A[n-1], etc.). Remember to use a 'bubble-down'
		   operation to restore the heap property after each removal. Pseudocode
		   for bubble down is given as part of algorithms 17 and 18 in Section 13 of the lab notes. */
        for(int i = heapSize; i >=1; i--){
            swap(A, 1, i);
            heapSize = heapSize - 1;
            maxHeap(A, 1);

        }
		/* Copy the sorted values in A back into inputArray, with inputArray[i] getting
		   the value from A[i+1] */
        for(int i = 1; i < A.length; i++){
            inputArray[i - 1] = A[i];
        }
    }

    public static void main(String[] args){
		/* Code to test your implementation */
		/* You may modify this, but the contents of this method will be discarded
	        before marking (so do not put any of the heap sort implementation here) */
        Scanner s;
        if (args.length > 0){
            try{
                s = new Scanner(new File(args[0]));
            } catch(java.io.FileNotFoundException e){
                System.out.printf("Unable to open %s\n",args[0]);
                return;
            }
            System.out.printf("Reading input values from %s.\n",args[0]);
        }else{
            s = new Scanner(System.in);
            System.out.printf("Enter up to %d positive integers to be sorted. Enter a negative value to end the list.\n",max_values);
        }
        int[] array = new int[max_values];
        int n = 0;
        int v;
        while(s.hasNextInt() && (v = s.nextInt()) >= 0)
            array[n++] = v;
        array = Arrays.copyOfRange(array, 0, n);

        System.out.printf("Read %d values, sorting...\n",array.length);
        heapSort(array);

        //Don't print out the values if there are more than 100 of them
        if (array.length <= 100){
            System.out.println("Sorted values:");
            for (int i = 0; i < array.length; i++)
                System.out.printf("%d ",array[i]);
            System.out.println();
        }

        //Check whether the sort was successful
        boolean isSorted = true;
        for (int i = 0; i < n-1; i++)
            if (array[i] > array[i+1])
                isSorted = false;
        System.out.printf("Array %s sorted.\n",isSorted? "is":"is not");
    }
}
