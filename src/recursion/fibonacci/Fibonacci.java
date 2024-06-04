package recursion.fibonacci;

import java.util.HashMap;

public class Fibonacci {

  /**
   * - 피보나치 수열
   * 보나치 수열은 1과 1로 시작한다. 다음 두 수를 가지고 무한대의 수를 만든다. 1과 1을 더해 2를 만든다.
   * 다음 1과 2를 더해 3을 만든다... 이와 같은 과정을 무한 반복한다.
   *
   * - 메모이제이션
   * 동일한 계산을 반복하지 않도록 결과를 저장하여 재사용하는 기법이다.
   *
   * - 메모이제이션 단점
   * 메모이제이션을 사용하면 피보나치 수열과 같은 문제의 경우 아주 빠른 속도를 얻을 수 있다.
   * 다만 속도를 위해 메모리(공간)를 사용하게 된다. 데이터를 캐싱해놓는 것과 같은 일이기 때문에 많은 수의 데이터를
   * 저장하게 된다.
   * */

  public Fibonacci() {};

  public int fibonacciFunction(int n) {
    if(n == 0 || n == 1){
      return n;
    }
    return this.fibonacciFunction(n - 2) + this.fibonacciFunction(n - 1);
  };

  /*
    - 메모이제이션
    주로 재귀적 접근을 사용하여, 하위 문제의 결과를 캐시(HashMap)해두고 필요할 때마다 이를 재사용한다.

    구현: 함수가 호출될 때마다 먼저 캐시(HashMap)을 확인하여 결과가 있는지 확인하고 없다면 계산하여 캐시에 저장한다.
    특징:
      - 탐색순서: 주로 재귀적으로 접근한다. 필요한 경우에만 하위 문제가 계산된다.
      - 공간 복잡도: 스택 메모리를 사용하여 재귀 호출을 관리하기 때문에, 큰 문제에서는 스택 오버플로우 위험이 있다.
   */
  public int fibonacciTopDown(int n, HashMap<Integer, Integer> memo){
    if(n == 0 || n == 1){
      return n;
    }

    if(memo.get(n) == null){
      memo.put(n, fibonacciTopDown(n - 2, memo) + fibonacciTopDown(n - 1, memo));
    }

    return memo.get(n);
  }

  /*
    - 타뷸레이션
    반복적 접근을 사용하여, 모든 하위 문제를 먼저 계산하고, 그 결과를 테이블(배열)에 저장한다.

    구현: 작은 문제부터 순차적을 테이블을 채워나가면서, 이전에 계산된 결과를 이용해 상위 문제를 해결한다.
    특징:
      - 탐색순서: 반복적 접근 방식으로, 문제를 해결하기 위해 필요한 모든 하위 문제가 미리 계산되어진다.
      - 공간 복잡도: 스택메모리를 사용하지 않으므로, 스택 오버플로우의 위험이 없다. 그러나 테이블을 저장하기 위한
                    메모리가 필요하다.
   */
  public int fibonacciBottomUp(int n){
    if(n <= 1) return n;

    int[] table = new int[n + 1];
    table[0] = 0;
    table[1] = 1;

    for(int i = 2; i <= n; i++){
      table[i] = table[i - 2] + table[i - 1];
    }

    return table[n];
  }


}
