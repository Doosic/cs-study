package list;

public class ListMain {
  public static void main(String[] args) {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);

    node1.setNext(node2);
    node2.setNext(node3);

    System.out.println(node1.getData());
    System.out.println(node1.getNext().getData());
    System.out.println(node1.getNext().getNext().getData());

    System.out.println("===== insertAt() 다섯 번 호출 =====");
    LinkedList list = new LinkedList();
    list.insertAt(0, 0);
    list.insertAt(1, 1);
    list.insertAt(2, 2);
    list.insertAt(3, 3);
    list.insertAt(4, 4);
    list.printAll();

    System.out.println("===== clear() 호출 =====");
    list.clear();
    list.printAll();

    System.out.println("===== insertLast() 세 번 호출 =====");
    list.insertLast(0);
    list.insertLast(1);
    list.insertLast(2);
    list.printAll();

    System.out.println("===== deleteAt() 세 번 호출 =====");
    list.printAll();
    list.deleteAt(0);
    list.printAll();
    list.deleteAt(1);
    list.printAll();

    System.out.println("===== deleteLast() 호출 ===== ");
    list.deleteLast();
    list.printAll();

    System.out.println("===== getNodeAt() 호출 ===== ");
    list.insertLast(7);
    System.out.println(list.getNodeAt(0).getData());
  }
}
