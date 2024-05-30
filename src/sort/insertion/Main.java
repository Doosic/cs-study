package sort.insertion;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    InsertionSort insertionSort = new InsertionSort();

    int[] arr = {1,6,2,5,4,3,7};
    System.out.println("===== 정렬 전 =====");
    System.out.println(Arrays.toString(arr));

    insertionSort.InsertionSortFunction(arr);

    System.out.println("===== 정렬 후 =====");
    System.out.println(Arrays.toString(arr));
  }
}
