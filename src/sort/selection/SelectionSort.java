package sort.selection;

public class SelectionSort {

  /**
   * - 선택 정렬
   * 배열의 "정렬되지 않은 영역"의 첫 번째 원소를 시작으로 마지막 원소까지
   * 비교 후 가장 작은 값을 첫 번째 원소로 가져온다.
   * [4,2,1,3] -> [1,2,4,3] -> [1,2,3,4] 마지막 남은 원소는 정렬에서 제외한다. 나머지 값들이 정렬되었기에
   *
   * - 시간복잡도
   * 이중 반복문이기 때문에 O(n2)으로 볼 수 있다.
   *
   * - 장점
   * 구현이 간단하다
   *
   * - 단점
   * 성능이 좋지 않다
   * */

  public SelectionSort() {};

  public int[] selectionSortFunction(int[] arr) {
    // 마지막 원소는 자동 정렬되기 때문에 arr.length -1번 반복한다.
    for(int i = 0; i < arr.length-1; i++){
      // 최소값을 가지고있을 index 선언, i를 넣은 이유는 반복될 때 마다 정렬된 영역을 제외하기 위해서
      int minValueIndex = i;

      // j = i + 1인 이유는 i의 값을 minValueIndex에 이미 저장했기 때문이다.
      for(int j = i + 1; j < arr.length; j++){
        // 가장 작은 값을 발견시 minValueIndex에 해당 인덱스를 저장한다.
        if(arr[j] < arr[minValueIndex]){
          // 반복문을 순회하며 정렬되지 않은 영역중에서 가장 작은값이 위치한 인덱스를 저장한다.
          minValueIndex = j;
        }
      }

      // 정렬되지 않은 영역의 첫번째 원소와 가장 작은 값의 교체
      int temp = arr[i];
      // 가장 작은 값을 정렬되지 않은 영역의 첫번째 원소와 교체한다.
      arr[i] = arr[minValueIndex];
      // 가장 작은값이 위치하던 곳에 정렬되지 않은 영역의 첫번째 원소값을 넣어준다.
      arr[minValueIndex] = temp;
    }

    return arr;
  }
}
