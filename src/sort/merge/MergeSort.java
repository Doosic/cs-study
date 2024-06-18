package sort.merge;

public class MergeSort {

  /*
   * - 병합 정렬
   * 해결하기 어려운 문제를 해결하기 쉬울 정도로 문제를 쪼개서 해결한다(분할 정복)
   * 재귀로 해결할 수 있다.
   *
   * arr = [3,5,2,4,1,7,8,6]
   * leftIndex = 0, rightIndex = 7 (인덱스값)
   * 배열의 원소가 1개일 때까지 분할한다. 기저조건식 if(leftIndex < rightIndex)
   *
   * - 시간복잡도
   * Merge함수 내에 흩어진 배열을 합치는 부분으로 시간복잡도를 판단한다.
   * 하나의 데이터와 하나의 데이터가 합쳐질때 비교연산을 2번 한다.
   * 두 개의 데이터와 두개의 데이터가 네 개로 합쳐질때는 비교가 4번 이루어진다.
   * 각 단계를 거칠때마다 영역의 수가 반으로 줄기 떄문에 LogN으로 볼 수 있다.
   *
   * 즉, 분할된 배열을 병합할 때는 n개의 데이터를 n번 비교하므로
   * O(nlogn)의 성능이 나온다.
   *
   * - 장점
   * 성능이 좋다.
   *
   * - 단점
   * 이해와 구현이 어렵다.
   */

  public MergeSort(){};

  public int[] MergeSortFunction(int[] arr, int leftIndex, int rightIndex){
    // 더 이상 나눌 수 없을 만큼 요소를 나눠준다.
    // 기저조건: leftIndex 0, rightIndex 0 인 상황. 더 이상 쪼갤 수 없을때 재귀를 벗어난다.
    /*
      ex) 배열에는 8개의 데이터가 들어있다. index는 0~7
      분할 정복을 위해 반으로 나눈다. left arr1 = 0~3 index, right arr1 = 4~7
      분할 정복을 위해 반으로 다시 나눈다. left arr1-1 = 0~1, left arr1-2 = 2~3, right arr1-1 = 4~5, right arr1-2 = 6~7
      분할 정복을 위해 반으로 다시 나눈다. left arr1-1-1 = 0..... 더 이상 쪼갤수 없는 상태. leftIndex와 rightIndex가 동일 기저조건에 일치함으로 재귀 탈출

      재귀를 탈출하며 Merge 시작
      반으로 나눈 것을 정렬하며 합친다. left arr1-1-1 + left arr 1-1-2 = 0,1
      반으로 나눈 것을 정렬하며 합친다.(쪼개진 기준으로는 right arr) left arr1-2-1 + left arr 1-2-2 = 2,3
      다시 합치면 0,1,2,3 .... 반복
     */
    if(leftIndex < rightIndex){
      // 가운데 index를 정해준다. left와 right를 나눌 기준.(이때 정수로 나눠준다)
      int midIndex = ((leftIndex + rightIndex) / 2);
      // left arr을 재귀적으로 분할 및 Merge한다
      this.MergeSortFunction(arr, leftIndex, midIndex);
      // right arr을 재귀적으로 분할 및 Merge한다
      this.MergeSortFunction(arr, midIndex + 1, rightIndex);
      // 재귀를 탈출한 arr을
      this.Merge(arr, leftIndex, midIndex, rightIndex);
    }

    return arr;
  }

  public int[] Merge(int[] arr, int leftIndex, int midIndex, int rightIndex){
    // leftIndex = 0
    int leftAreaIndex = leftIndex;
    // midIndex = 0
    int rightAreaIndex = midIndex + 1;
    // rightIndex = 1

    // leftAreaIndex = 0, rightAreaIndex = 1

    // tempArr.length = 8
    // 정렬한 데이터를 담아줄 변수
    int[] tempArr = new int[arr.length];

    // tempArrIndex = 0
    int tempArrindex = leftIndex;

    // 0 <= 0 && 1 <= 1
    while(leftAreaIndex <= midIndex && rightAreaIndex <= rightIndex){
      /*
         (arr[0] = 3) <= (arr[1] = 5)
         tempArr[0] = arr[0]의 값인 3이 더 작으므로 tempArr에 들어간다.
         더 작은 값부터 임시배열에 저장하기 위함이다.
         if(왼쪽영역의 값이 더 작을 경우), else(오른쪽 영역의 값이 더 작을 경우)
         더 작은 영역의 값을 temp에 넣은 이후(left라 가정) 다음 left와 기존 right의 값을 비교하기 위해
         leftAreaIndex의 값을 1씩 상승시킨다.
       */
      if(arr[leftAreaIndex] <= arr[rightAreaIndex]){
        tempArr[tempArrindex] = arr[leftAreaIndex++];
      }else{
        tempArr[tempArrindex] = arr[rightAreaIndex++];
      }
      // tempArrIndex도 다음 인덱스에 값을 저장하기 위해 값을 1씩 상승시킨다.
      tempArrindex++;
    }

    /*
      ex) 배열 left arr [2,3,4,5] , right arr[1,6,7,8] 이라 가정하고
      leftAreaIndex가 4인 경우 tempArr은 다음과 같다. tempArr [1,2,3,4,5,0,0,0]
      이때에는 right arr의 남은 6,7,8의 값을 모두 더해주면 된다.
     */
    if(leftAreaIndex > midIndex){
      for(int i = rightAreaIndex; i <= rightIndex; i++){
        tempArr[tempArrindex++] = arr[i];
      }
    }else{
      for(int i = leftAreaIndex; i <= midIndex; i++){
        tempArr[tempArrindex++] = arr[i];
      }
    }

    // 임시 배열에 있던 값을 기존 배열에 담아준다.
    for(int i = leftIndex; i <= rightIndex; i++){
      arr[i] = tempArr[i];
    }

    return arr;
  }

}
