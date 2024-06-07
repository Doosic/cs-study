package tree.binarySearch;

public class BinarySearch {

  public BinarySearch() {};

  public int binarySearch(int[] arr, int target, int start, int end) {
    // 재귀를 반복하며 범위를 좁혀나갔지만 찾는 값이 없을 경우
    if(start > end){
      return -1;
    }

    // 정수값으로 떨어져야 한다. int 자료형에 담을시 반내림
    int mid = (start + end) / 2;

    if(arr[mid] == target){
      // 찾는 값과 일치하는 경우
      return mid;
    } else if (target > arr[mid]){
      // 찾는 값이 중간 값보다 더 큰 경우
      return this.binarySearch(arr, target, mid + 1, end);
    } else {
      // 찾는 값이 중간 값보다 더 작은 경우
      return this.binarySearch(arr, target, start, mid - 1);
    }
  }
}
