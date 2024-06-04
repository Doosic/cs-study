package sort.quick;

public class QuickSort {

  /**
   * - 퀵 정렬
   * 퀵 정렬은 재귀를 사용하며 분할정복 알고리즘에 속한다.
   *
   * 퀵 정렬에서는 정렬하기 전에 배열에 있는 숫자 중 하나를 '피벗'으로 설정해준다.
   * 피벗을 선택하는 방법은 여러가지가 있지만 배열의 가장 왼쪽값을 선택.
   * [5,3,7,2,6,4,9,1,8] => 피벗 = 5
   * 피벗을 제외한 배열의 양쪽에가 값을 비교하기 위한 변수 선언. leftStartIndex, rightStartIndex
   *
   * leftStartIndex는 오른쪽으로 이동하다가 피벗보다 큰 값을 만나면 멈춘다.(7을 만나면 멈춘다.)
   * rightStartIndex는 왼쪽으로 이동하다가 피벗보다 작은 값을 만나면 멈춘다.(1을 만나면 멈춘다.)
   * 이후 leftStartIndex, rightStartIndex 서로가 가르키는 값을 swap한다. [5,3,7,2,6,4,9,1,8] => [5,3,1,2,6,4,9,7,8]
   * 위와 같은 일들을 반복한다.
   * leftStartIndex = 6, rightStartIndex = 4 [5,3,1,2,6,4,9,7,8] => [5,3,1,2,4,6,9,7,8]
   * 반복하다보면 left, right의 인덱스가 서로를 지나치는 경우가 발생한다. 이때 피벗과 rightStartIndex의 값을 교환한다.
   * 이때 5를 기준으로 왼쪽에 있는 값들은 5보다 작고, 오른족에 있는 값들은 5보다 큰 값들이 위치하게된다.
   * 이러한 방식을 왼쪽, 오른쪽에 계속 적용해준다.
   *
   * - 정리
   * 퀵 정렬은 한 번씩 정렬될 때마다 피벗이 정렬되고 정렬된 배열은 좌우로 나눠서
   * 같은 방식으로 호출해 정렬을 구현한다.
   *
   * - 시간복잡도
   * 퀵정렬은 피벗을 기준으로 데이터가 한 개가 될 때까지 반으로 나눈다. logn
   * 이렇게 나눠진 단계를 배열의 원소 수 n만큼 진행해야 하기 때문에 nLogn으로 볼 수 있다.
   *
   * - 장점
   * 성능만 본다면 퀵정렬보다 병합 정렬이 더 좋다고 볼 수 있다. 그러나 퀵정렬이 더 적은 비교와 더 적은 메모리 공간을
   * 차지하기 때문에 더 좋은 알고리즘으로 평가된다.
   *
   * - 단점
   * 최악의 경우에는 O(n2)의 성능을 보인다. 피벗이 배열을 반으로 가르지 않고 한쪽에 쏠리는 경우
   * 그러나 대부분에 경우 좋은 피벗을 선택하여 평균적인 성능을 보이므로 O(nlogn)이라 볼 수 있다.
   */

  public QuickSort() {};

  public void QuickSortFunction(int[] arr, int left, int right) {
    if(left <= right){
      int pivot = this.divide(arr, left, right);
      this.QuickSortFunction(arr, left, pivot -1);
      this.QuickSortFunction(arr, pivot + 1, right);
    }
  };

  public int divide(int[] arr, int left, int right){
    int pivot = arr[left];
    int leftStartIndex = left + 1;
    int rightStartIndex = right;

    // leftStartIndex가 rightStartIndex보다 크다면 지나친 상태이다.
    while(leftStartIndex <= rightStartIndex){
      while(leftStartIndex <= right && pivot >= arr[leftStartIndex]){
        leftStartIndex++;
      }

      while(rightStartIndex >= (left + 1) && pivot <= arr[rightStartIndex]){
        rightStartIndex--;
      }

      if(leftStartIndex <= rightStartIndex){
        this.swap(arr, leftStartIndex, rightStartIndex);
      }
    }

    this.swap(arr, left, rightStartIndex);
    return rightStartIndex;
  }

  private void swap (int[] arr, int index1, int index2){
    int temp = arr[index1];
    arr[index1] = arr[index2];
    arr[index2] = temp;
  }
}
