package sort.quick;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    QuickSort quickSort = new QuickSort();
    int[] arr = {5,3,7,2,6,4,9,1,8};

    System.out.println("===== 정렬 전 =====");
    System.out.println(Arrays.toString(arr));

    quickSort.QuickSortFunction(arr, 0, arr.length -1);

    System.out.println("===== 정렬 후 =====");
    System.out.println(Arrays.toString(arr));

  }
}
