package sort.bubble;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    BubbleSort bubbleSort = new BubbleSort();

    int[] arr = {1, 3, 5, 2, 7, 4, 6};
    System.out.println("===== 정렬 전 =====");
    System.out.println(Arrays.toString(arr));

    arr = bubbleSort.bubbleSortFunction(arr);

    System.out.println("===== 정렬 후 =====");
    System.out.println(Arrays.toString(arr));
  }
}
