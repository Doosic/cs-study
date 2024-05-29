package sort.selection;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    SelectionSort selectionSort = new SelectionSort();

    int[] arr = {3, 5, 6, 1, 2, 7};
    System.out.println("===== 정렬 전 =====");
    System.out.println(Arrays.toString(arr));

    arr = selectionSort.selectionSortFunction(arr);

    System.out.println("===== 정렬 후 =====");
    System.out.println(Arrays.toString(arr));
  }
}
