package matrix;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 03.02.14
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
public class Sort {
//    private int [] a = {3, 1, 5, 4, 2, 6, 1, 0, 12};
    private int N = 10;
    Random random = new Random();

    public void selectSort(int [] a){
        for(int i = 0; i < a.length; i++){
            int min = i;
            for(int j = i + 1; j < a.length; j++){
                if(a[j] < a[min]){
                    int temp = a[j];
                    a[j] = a[min];
                    a[min] = temp;
                }
            }
        }

    }
    public void bubleSort(int [] a){
        for(int i = a.length - 1; i > 0; i--){
            for(int j = 0; j < i; j++){

                System.out.print(a[j] + " ");
                if(a[j] > a[i]){
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }

            }
            System.out.println();
        }
    }

    public void qSort(int [] a, int low, int hi){
        int i = low;
        int j = hi;
        int mid = (low + hi) / 2;
        while(i <= j){
            while(a[i] < a[mid]){
                i++;
            }
            while(a[j] > a[mid]){
                j--;
            }
            if(i <= j){
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }

        if(low < j){
            qSort(a, low, j);
        }
        if(hi > i){
            qSort(a, i, hi);
        }
    }

    public void myMerge_2(int [] a, int [] tmpArr, int leftPos, int rightPos, int rightEnd){
        int left = leftPos;
        int right = rightPos + 1;
        int k = leftPos;

        for(int i = leftPos; i <= rightEnd; i++){
            tmpArr[i] = a[i];
        }

        while(left <= rightPos && right <= rightEnd){
            if(tmpArr[left] < tmpArr[right]){
                a[k++] = tmpArr[left++];
            }else{
                a[k++] = tmpArr[right++];
            }
        }
        while(left <= rightPos){
            a[k++] = tmpArr[left++];
        }
    }

    public void merge(int [] a, int [] tmpArray, int leftPos, int rightPos, int rightEnd){

        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd){
            if(a[leftPos] <= a[rightPos]){
                tmpArray[tmpPos++] = a[leftPos++];
            }else{
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }
        while(leftPos <= leftEnd){
            tmpArray[tmpPos++] = a[leftPos++];
        }
        while(rightPos <= rightEnd){
            tmpArray[tmpPos++] = a[rightPos++];
        }

        for(int i = 0; i < numElements; i++, rightEnd--){
            a[rightEnd] = tmpArray[rightEnd];
        }
    }

    public void print(int [] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public void selectSort_2(int [] a){
        int min;

        for(int i = 0; i < a.length; i++){
            min = i;
            for(int j = i + 1; j < a.length; j++){
                if(a[j] < a[min]){
                    int temp = a[j];
                    a[j] = a[min];
                    a[min] = temp;
                }
            }
        }
    }




    public void inserSort(int [] a){

        for(int i = 0; i < a.length; i++){
            for(int j = i; j > 0 && (a[j] < a[j - 1]); j--){
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
    }

    public void shellSOrt(int [] a){
        int  h = 0;
        while (h < a.length / 3){
            h = h * 3 + 1;
            System.out.printf("%d\n", h);
        }

        while(h > 0){
            for(int i = h; i < a.length; i++){
                for(int j = i; j >=  h && (a[j] < a[j - h]); j-=h){
                    int temp = a[j];
                    a[j] = a[j - h];
                    a[j - h] = temp;
                }
            }
            h /= 3;

        }
    }




    public int binarySearch(int [] a, int key, int min, int max){

        int mid = (min + max) / 2;

        if(a[mid] == key){
            return mid;
        }else if(a[mid] > key){
            return binarySearch(a, key, min, mid);
        }else{
            return binarySearch(a, key, mid, max);
        }

    }


    public int heapSize;
    public void swap(int [] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;

    }
    public int Left(int i){
        return (i << 1);
    }
    public int Right(int i){
        return (i << 1) + 1;
    }

    public void maxHeap(int [] A, int i){

        int left = Left(i);
        int right = Right(i);

//        int left = 2*i;
//        int right = 2*i + 1;
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
    public void buildHeap(int [] A){
        heapSize = A.length - 1;
        for(int i = heapSize/2; i >= 0; i--){
            maxHeap(A, i);
        }
    }

    public static void testingMemoryWIthQSort(int [] a, int left, int right){
        int mid = (left + right) / 2;
        int i = left;
        int j = right;
        while(i <= j){
            while(a[i] < a[mid]){
                i++;
            }
            while(a[j] > a[mid]){
                j--;
            }
            if(i <= j){
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }

        if(i < right){
            testingMemoryWIthQSort(a, left, i);
        }
        if(left < j){
            testingMemoryWIthQSort(a,j, right);
        }
    }
    public void heapSort(int [] A){
        buildHeap(A);
        for(int i = heapSize; i > 0; i--){
            swap(A, 0, i);
            heapSize = heapSize - 1;
            maxHeap(A, 0);

        }
    }



    public static void main(String [] args){

        Sort s = new Sort();
//        int [] b = {2, 9, 8, 6, 5, 7};
        int [] b = {3, 7, 5, 1, 6, 4, 2};

        s.print(b);
//        s.buildHeap(b);
        testingMemoryWIthQSort(b, 0, b.length-1);
//        s.heapSort(b);
        s.print(b);
//        s.heapSort(b);
//        s.print(b);

//        s.selectSort(b);
//        s.bubleSort(b);
//        s.qSort(b, 0, b.length - 1);
//        s.print(b);
//        s.mergeSort(b);
//        s.inserSort(b);
//        s.shellSOrt(b);

//        int i = s.binarySearch(b, 3, 0, b.length - 1);
//        System.out.print(i);




    }
}
