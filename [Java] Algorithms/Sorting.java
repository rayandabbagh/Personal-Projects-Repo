import java.util.Comparator;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Rayan Dabbagh
 * @version 2.0
 * @userid rdabbagh3
 * @GTID 903591640
 * <p>
 * Collaborators: NA
 * <p>
 * Resources: NA
 */
public class Sorting {

    /**
     * Implement insertion sort.
     * <p>
     * It should be:
     * in-place
     * stable
     * adaptive
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The Array or the Comparator passed in are null!");
        }

        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j], arr[j - 1]) < 0) {
                swapHelperMethod(arr, j - 1, j);
                j--;

            }
        }
    }

    /**
     * Implement cocktail sort.
     * <p>
     * It should be:
     * in-place
     * stable
     * adaptive
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n)
     * <p>
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("The comparator you entered is null! Cannot use a null comparator.");
        }
        if (arr == null) {
            throw new IllegalArgumentException("The array you entered is null! Cannot sort a null array.");
        }

        boolean swaps = true;
        int start = 0;
        int end = arr.length - 1;
        int tempVar;
        while (swaps) {
            swaps = false;
            tempVar = end;
            for (int j = start; j < tempVar; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swapHelperMethod(arr, j, j + 1);
                    swaps = true;
                    end = j;
                }
            }
            tempVar = start;
            if (swaps) {
                swaps = false;
                for (int z = end; z > tempVar; z--) {
                    if (comparator.compare(arr[z - 1], arr[z]) > 0) {
                        swapHelperMethod(arr, z - 1, z);
                        swaps = true;
                        start = z;
                    }
                }
            }
        }
    }

    /**
     * Implement merge sort.
     * <p>
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     * <p>
     * Have a worst case running time of:
     * O(n log n)
     * <p>
     * And a best case running time of:
     * O(n log n)
     * <p>
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     * <p>
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     * <p>
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("The comparator you entered is null! Cannot use a null comparator.");
        }
        if (arr == null) {
            throw new IllegalArgumentException("The array you entered is null! Cannot sort a null array.");
        }

        if (arr.length <= 1) {
            return;
        }
        int lengthArray = arr.length;
        int middleIndex = lengthArray / 2;
        T[] leftArray = (T[]) new Object[middleIndex];
        for (int i = 0; i < middleIndex; i++) {
            leftArray[i] = arr[i];
        }
        T[] rightArray = (T[]) new Object[lengthArray - middleIndex];
        for (int j = 0; j < (lengthArray - middleIndex); j++) {
            rightArray[j] = arr[j + middleIndex];
        }

        mergeSort(leftArray, comparator);
        mergeSort(rightArray, comparator);

        int a = 0;
        int b = 0;

        while (a < leftArray.length && b < rightArray.length) {
            if (comparator.compare(leftArray[a], rightArray[b]) <= 0) {
                arr[a + b] = leftArray[a];
                a++;
            } else {
                arr[a + b] = rightArray[b];
                b++;
            }
        }
        while (a < leftArray.length) {
            arr[a + b] = leftArray[a];
            a++;
        }
        while (b < rightArray.length) {
            arr[a + b] = rightArray[b];
            b++;
        }

    }

    /**
     * Implement quick sort.
     * <p>
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     * <p>
     * int pivotIndex = rand.nextInt(b - a) + a;
     * <p>
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     * <p>
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     * <p>
     * It should be:
     * in-place
     * unstable
     * not adaptive
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n log n)
     * <p>
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (rand == null) {
            throw new IllegalArgumentException("The comparator you entered is null! Cannot use a null random gen.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("The comparator you entered is null! Cannot use a null comparator.");
        }
        if (arr == null) {
            throw new IllegalArgumentException("The array you entered is null! Cannot sort a null array.");
        }
        quickSortHelpermethod(arr, 0, arr.length - 1, comparator, rand);
    }


    /**
     * Implement LSD (least significant digit) radix sort.
     * <p>
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     * <p>
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     * <p>
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     * <p>
     * Have a worst case running time of:
     * O(kn)
     * <p>
     * And a best case running time of:
     * O(kn)
     * <p>
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need. The number of iterations
     * can be determined using the number with the largest magnitude.
     * <p>
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     * <p>
     * Refer to the PDF for more information on LSD Radix Sort.
     * <p>
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     * <p>
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("The array passed in is null");
        }
        int longestNum = 0;
        int count;
        int divisorNum;
        for (int j = 0; j < arr.length; j++) {
            count = 0;
            int num = arr[j];
            if (arr[j] == Integer.MIN_VALUE) {
                num = Integer.MAX_VALUE;
            }
            while (num != 0) {
                num = num / 10;
                count++;
            }
            if (count > longestNum) {
                longestNum = count;
            }
        }
        LinkedList<Integer>[] bucketsArray = (LinkedList<Integer>[]) new LinkedList[19];
        divisorNum = 1;
        int digitNum;
        for (int z = 0; z < longestNum; z++) {
            for (int j = 0; j < arr.length; j++) {
                digitNum = (arr[j] / divisorNum) % 10;
                digitNum = digitNum + 9;
                if (bucketsArray[digitNum] == null) {
                    bucketsArray[digitNum] = new LinkedList<Integer>();
                }
                bucketsArray[digitNum].addLast(arr[j]);
            }
            divisorNum = divisorNum * 10;
            int index = 0;
            for (LinkedList<Integer> linked : bucketsArray) {
                if (linked == null) {
                    linked = new LinkedList<Integer>();
                }
                for (Integer element : linked) {
                    arr[index++] = element;
                }
                linked.clear();
            }
        }
    }

    /**
     * Implement heap sort.
     * <p>
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     * <p>
     * Have a worst case running time of:
     * O(n log n)
     * <p>
     * And a best case running time of:
     * O(n log n)
     * <p>
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     * <p>
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     * <p>
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data passed in is null!");
        }
        PriorityQueue<Integer> min = new PriorityQueue<>(data);
        int[] newArray = new int[data.size()];
        for (int z = 0; z < newArray.length; z++) {
            newArray[z] = min.remove();
        }
        return newArray;
    }

    /**
     * Helper method for swapping
     *
     * @param array  the array in which I am swapping elements
     * @param index1 the first index of the element being swapped
     * @param index2 the second index of the element being swapped
     * @param <T>    type of the data in the array
     */
    public static <T> void swapHelperMethod(T[] array, int index1, int index2) {
        if (index1 == index2) {
            return;
        } else {
            T tempVar = array[index1];
            array[index1] = array[index2];
            array[index2] = tempVar;
        }
    }

    /**
     * Private helper method
     *
     * @param array      the array to be sorted
     * @param startIndex start index of the new sub-array
     * @param endIndex   end index of the new sub-array
     * @param comparator the comparator given
     * @param random     random number
     * @param <T>        data type of elements of array
     */

    public static <T> void quickSortHelpermethod(T[] array, int startIndex, int endIndex, Comparator<T> comparator,
                                                 Random random) {
        if ((endIndex - startIndex) < 1) {
            return;
        }
        int pivot = random.nextInt(endIndex - startIndex + 1) + startIndex;
        T pivotVal = array[pivot];
        swapHelperMethod(array, startIndex, pivot);
        int a = startIndex + 1;
        int b = endIndex;
        while (a <= b) {
            while (a <= b && comparator.compare(array[a], pivotVal) <= 0) {
                a++;
            }
            while (a <= b && comparator.compare(array[b], pivotVal) >= 0) {
                b--;
            }
            if (a <= b) {
                swapHelperMethod(array, a, b);
                a++;
                b--;
            }
        }
        swapHelperMethod(array, startIndex, b);
        quickSortHelpermethod(array, startIndex, b - 1, comparator, random);
        quickSortHelpermethod(array, b + 1, endIndex, comparator, random);

    }
}

