package recursion.fibonacci;

import java.sql.SQLOutput;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) {
    Fibonacci fibonacci = new Fibonacci();

    long startTime = System.nanoTime();
    System.out.println(fibonacci.fibonacciFunction(5));
    long endTime = System.nanoTime();

    long executionTime = endTime - startTime;

    // 실행 시간 출력 (밀리초 단위로 변환하여 출력)
    System.out.println("Execution time: " + executionTime + " nanoseconds");
    System.out.println("Execution time: " + (executionTime / 1_000_000.0) + " milliseconds");

    System.out.println("===== 메모이제이션 =====");

    long startTimeDP = System.nanoTime();
    HashMap<Integer, Integer> memo = new HashMap<>();
    System.out.println(fibonacci.fibonacciTopDown(5, memo));
    long endTimeDP = System.nanoTime();

    long executionTimeDP = endTimeDP - startTimeDP;

    // 실행 시간 출력 (밀리초 단위로 변환하여 출력)
    System.out.println("Execution time: " + executionTimeDP + " nanoseconds");
    System.out.println("Execution time: " + (executionTimeDP / 1_000_000.0) + " milliseconds");

    System.out.println("===== 타뷸레이션 =====");

    long startTimeBU = System.nanoTime();
    System.out.println(fibonacci.fibonacciBottomUp(5));
    long endTimeBU = System.nanoTime();

    long executionTimeBU = endTimeBU - startTimeBU;

    // 실행 시간 출력 (밀리초 단위로 변환하여 출력)
    System.out.println("Execution time: " + executionTimeBU + " nanoseconds");
    System.out.println("Execution time: " + (executionTimeBU / 1_000_000.0) + " milliseconds");
  }
}
