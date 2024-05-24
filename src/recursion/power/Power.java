package recursion.power;

public class Power {

  public Power() {};

  /**
   * 재귀는 하위문제의 결과를 기반으로 현재 문제를 계산한다.
   * 하위문제: 2에5승 = 2에4승 X 2
   * */

  public int function(int x, int n){
    if(n == 0) return 1;
    return this.function(x, n - 1) * x; // 하위문제 2에 4승에 2를 곱하는 것
  }
}
