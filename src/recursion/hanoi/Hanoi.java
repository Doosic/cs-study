package recursion.hanoi;

public class Hanoi {

  // hanoi(3, "A", "C", "B");
  // 원반3개, 시작(from) A, 임시(temp) B, 목표(to) C

  public Hanoi(){};

  /*
   * 재귀함수에 들어갈때는 Stack에 쌓인다고 생각하면 편하다. 실제로도 그렇다.
   * 가장 마지막으로 들어간 함수(가장 작은 문제)가 첫번째로 해결된다.
   * 그러므로 가장 작은 단위부터 문제 해결이된다.
   * */

  public void hanoiFunction(int count, String from, String to, String temp){
    // count: 원반의 수
    // from: 원반들이 위치한 곳의 번호
    // to: 원반들을 옮길 목적지 번호
    // other: 나머지 한 곳(목적지가 아닌) 곳 번호(임시저장소)

    // 모두 옮겼으면 종료
    if(count == 0) return;

    // 가장 아래 원반을 제외한 원반들을 재귀적으로 목적지가 아닌 곳으로 옮김
    this.hanoiFunction(count - 1, from, temp, to);

    // 가장 아래 원반을 목적지로 옮김
    System.out.println("원반 " + count + "를 " + from + "에서 " + to + "로 이동");

    // 목적지가 아닌 곳으로 옮겼던 원반들을 재귀적으로 목적지로 옮김
    this.hanoiFunction(count - 1, temp, to, from);
  }
}
