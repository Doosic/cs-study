package sort.merge;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    MergeSort mergeSort = new MergeSort();
    int[] arr = {3,5,2,4,1,7,8,6};

    System.out.println("===== 정렬 전 =====");
    System.out.println(Arrays.toString(arr));

    mergeSort.MergeSortFunction(arr, 0, arr.length -1);

    System.out.println("===== 정렬 후 =====");
    System.out.println(Arrays.toString(arr));

  }
}
