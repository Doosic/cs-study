package heap;

public class Main {

  public static void main(String[] args) {
    Heap heap = new Heap();
    heap.insert(4);
    heap.insert(2);
    heap.insert(5);
    heap.insert(7);
    heap.insert(1);

    heap.getRoot().inOrderTraversal(heap.getRoot());
    System.out.println(heap.getRoot().getData());

    System.out.println("===== remove =====");
    System.out.println(heap.remove().getData());
  }
}
