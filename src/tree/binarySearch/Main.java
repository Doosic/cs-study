package tree.binarySearch;

public class Main {

  public static void main(String[] args) {
    int[] arr = {1,2,3,4,5,6,7,8,9,10};

    BinarySearch binarySearch = new BinarySearch();
    int index = binarySearch.binarySearch(arr, 3, 0, arr.length-1);
    System.out.println("index: " + index);
  }
}
