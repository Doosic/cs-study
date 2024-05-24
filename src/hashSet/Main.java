package hashSet;

public class Main {

  public static void main(String[] args) {
    HashSet hashSet = new HashSet();
    System.out.println(hashSet.isEmpty());

    System.out.println("===== 데이터 삽입 =====");
    hashSet.add(1);
    hashSet.add(1);
    hashSet.add(123);
    hashSet.add(512);
    hashSet.printAll();
    System.out.println("isEmpty: " + hashSet.isEmpty());

    System.out.println("===== 데이터 체크1 =====");
    System.out.println("isContain: " + hashSet.isContain(1));

    System.out.println("===== 데이터 제거1 =====");
    hashSet.remove(1);
    hashSet.printAll();
    System.out.println("isEmpty: " + hashSet.isEmpty());

    System.out.println("===== 데이터 체크2 =====");
    System.out.println("isContain: " + hashSet.isContain(1));

    System.out.println("===== clear 체크2 =====");
    hashSet.clear();
    hashSet.printAll();
    System.out.println("isEmpty: " + hashSet.isEmpty());

  }
}
