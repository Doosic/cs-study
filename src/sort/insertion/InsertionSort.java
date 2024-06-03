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

    // 반복문으로 배열을 순회한다
    // i = 1 첫 번째 원소는 이미 정렬되어 있다고 가정
    for(int i = 1; i < arr.length; i++){
      // i값은 정렬되지 않은 영역의 첫 번째 데이터
      int insertionData = arr[i];
      int j;

      // 정렬된 영역의 맨 뒤부터 첫 번째 원소까지 역순으로 순회하며 정렬될 원소의 위치를 찾는다
      // 정렬된 영역의 마지막 원소는 정렬되지 않은 영역의 첫 번째 원소의 한 칸 앞이다
      // i - 1을 통해 정렬된 영역의 맨 뒤의 값으로 설정한다. i는 정렬되지 않은 영역의 첫 번째 데이터이기 때문이다.
      for(j = i - 1; j >= 0; j--){
        // 정렬된 영역의 마지막 원소부터 역순으로 순회하며 삽입할 원소와 비교한다.
        // 현재 순회하는 인덱스의 삽입할 원소보다 크다면 오른쪽 인덱스로 덮어씌워준다.
        if(arr[j] > insertionData){
          // 정렬된 영역의 데이터값이 더 크다면 정렬되지 않은 영역의 첫번째 데이터가 씌워진다.
          arr[j + 1] = arr[j];
        }else{
          break;
        }
      }
      // 데이터값이 더 작다면 insertionData 등록
      // 반복문을 나온 후 j + 1 임을 기억할 것
      arr[j + 1] = insertionData;
    }

    return arr;
  }
}
