package recursion.hanoi;

public class Main {

  public static void main(String[] args) {
    Hanoi hanoi = new Hanoi();

    // 기둥 A에 꽂혀있는 원반 3개를 기둥 C로 이동해라.
    hanoi.hanoiFunction(3, "A", "C", "B");

  }
}