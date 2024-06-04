package sort.merge;

public class MergeSortEx {

  public MergeSortEx(){};

  /*
    1.인자 값으로 정렬 할 배열, leftIndex, rightIndex를 받는다.
    2.재귀 호출시 탈출할 기저조건을 작성한다. 분할 정복이 불가능 할 때. 즉, 더 이상 나눌 수 없을 때
      left = 0, right = 1 이라면 값이 2개 있는 상황, left = 0, right = 0이라면 값이 1개 있으므로 최소 단위까지 분할된 상황
    3.배열을 계속해서 분할하기 위해 나누기 위한 중간 index값을 구한다.(자바는 int형에 담을시 정수형으로 담긴다)
    4.left 분할을 위한 재귀함수를 호출한다. 인자값으로는 arr, leftIndex, midIndex를 넘겨준다.
      right 분할을 위한 재귀함수를 호출한다. 인자값으로는 arr, midIndex +1, rightIndex를 넘겨준다.
      leftIndex = 0, midIndex = 3, rightIndex = 7
      [1,3,2,5,4,6,7,8] => left [1,3,2,5], right[4,6,7,8]
    5.Merge관련 함수를 작성한다.
      임시 배열에 두개의 배열을 합치고 기존 배열에 재할당하는 과정을 반복한다.
   */
  public int[] function(int[] arr, int leftIndex, int rightIndex){
    if(leftIndex < rightIndex){
      int midIndex = ((leftIndex + rightIndex) / 2);
      this.function(arr, leftIndex, midIndex);
      this.function(arr, midIndex + 1, rightIndex);
      this.merge(arr, leftIndex, midIndex, rightIndex);
    }

    return arr;
  };

  /*
    1.인자값으로 배열, leftIndex, midIndex, rightIndex를 받는다.
      midIndex를 기준으로 leftArea와 rightArea가 나뉜다.
    2.leftAreaIndex와 rightAreaIndex를 선언해준다.
    3.정렬된 값을 담아줄 임시배열과 임시index를 선언한다.
    4.while문을 선언하여 tempArr에 데이터를 담아준다.
      tempArr에 left에 값이 담길시 leftAreaIndex++, right에 담길시 rightAreaIndex++ 해준다.
      while문 조건식: leftAreaIndex는 midIndex와 같거나 작아야 한다.
             rightAreaIndex는 rightIndex와 같거나 작아야 한다.
    5.if문 조건.
      arr의 왼쪽 영역에 담긴 값이 오른쪽 영역의 값보다 작거나 같다면 왼쪽의 값을 담아준다.
      else는 그 반대로.
    6.if문 조건.
      leftAreaIndex가 midIndex보다 크다면 정렬된 값들을 모두 담은 것이기 때문에 rightIndex의 남은 값들을 전부
      tempArr에 담아준다.
    7.
   */
  public int[] merge(int[] arr, int leftIndex, int midIndex, int rightIndex){
    int leftAreaIndex = leftIndex;
    int rightAreaIndex = midIndex + 1;

    int[] tempArr = new int[arr.length];
    int tempArrIndex = leftIndex;

    while(leftAreaIndex <= midIndex && rightAreaIndex <= rightIndex){
      if(arr[leftAreaIndex] <= arr[rightAreaIndex]){
        tempArr[tempArrIndex] = arr[leftAreaIndex++];
      } else {
        tempArr[tempArrIndex] = arr[rightAreaIndex++];
      }
      tempArrIndex++;
    }

    if(leftAreaIndex > midIndex){
      for(int i = rightAreaIndex; i <= rightIndex; i++){
        tempArr[tempArrIndex++] = arr[i];
      }
    }else{
      for(int i = leftAreaIndex; i <= midIndex; i++){
        tempArr[tempArrIndex++] = arr[i];
      }
    }

    for(int i = leftIndex; i <= rightIndex; i++){
      arr[i] = tempArr[i];
    }

    return arr;
  }
}
