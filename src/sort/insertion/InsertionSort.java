package sort.insertion;

public class InsertionSort {

  /**
   * - 삽입 정렬
   * 삽입정렬은 먼저 정렬된 영역과 정렬되지 않은 영억으로 구분한다.
   * 이후 정렬되지 않은 영역의 첫번째 데이터를 꺼내 정렬된 영역의 데이터와 하나씩 비교하며
   * 적절한 위치에 데이터를 삽입한다.
   *
   * - 시간복잡도
   * O(n2)
   *
   * - 장점
   * 이해가 쉽고 구현이 간단하다
   *
   * - 단점
   * 성능이 좋지 않다
   * */

  public InsertionSort() {};

  public int[] InsertionSortFunction(int[] arr) {
    for(int i = 0; i < arr.length; i++){
      int insertionData = arr[i];
      int j;
      for(j = i - 1; j >= 0; j--){
        if(arr[j] > insertionData){
          arr[j + 1] = arr[j];
        }else{
          break;
        }
      }
      arr[j + 1] = insertionData;
    }

    return arr;
  }
}
