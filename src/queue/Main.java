package queue;

public class Main {

  public static void main(String[] args) {
    Queue queue = new Queue();

    System.out.println("===== enqueue() 세 번 호출 =====");
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);

    queue.printAll();

    System.out.println("===== dnqueue() 네 번 호출 =====");
    System.out.println(queue.dequeue().getData());
    System.out.println(queue.dequeue().getData());
    System.out.println(queue.dequeue().getData());
    System.out.println(queue.dequeue());

    System.out.println("isEmpty: " + queue.isEmpty());
  }
}
