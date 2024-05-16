package queue;

public class DoublyLinkedList<T> implements List<T>{

  private Node head;
  private Node tail;
  private int count = 0;

  public DoublyLinkedList() {
    this.head = null;
    this.tail = null;
    this.count = 0;
  }

  public DoublyLinkedList(Node head, Node tail) {
    this.head = head;
    this.tail = tail;
  }

  @Override
  public void printAll() {
    Node currentNode = this.head;
    String text = "[";

    while (currentNode != null) {
      text += currentNode.getData();
      currentNode = currentNode.getNext();

      if (currentNode != null) {
        text += ", ";
      }
    }
    text += "]";
    System.out.println(text);
  }

  @Override
  public void clear() {
    this.head = null;
    this.tail = null;
    this.count = 0;
  }

  @Override
  public void insertAt(int index, T data) {
    if (index > this.count || index < 0) {
      throw new Error("범위를 넘어갔습니다.");
    }

    Node newNode = new Node(data);

    if (index == 0) { // head에 삽입하는 경우
      newNode.setNext(this.head);
      if (this.head != null) {
        this.head.setPrev(newNode);
      }
      this.head = newNode;
    } else if (index == this.count) { // tail에 삽입하는 경우
      newNode.setNext(null);
      newNode.setPrev(this.tail);
      this.tail.setNext(newNode);
    } else { // 그 외 위치에 삽입하는 경우
      Node currentNode = this.head;

      for (int i = 0; i < index - 1; i++) {
        currentNode = currentNode.getNext();
      }

      newNode.setNext(currentNode.getNext());
      newNode.setPrev(currentNode);
      currentNode.setNext(newNode);
      newNode.getNext().setPrev(newNode);
    }

    // 새로 삽입한 노드가 마지막 노드일 경우 tail은 새로 삽입한 노드가 된다.
    if (newNode.getNext() == null) {
      this.tail = newNode;
    }

    this.count++;
  }

  @Override
  public void insertLast(T data) {
    this.insertAt(this.count, data);
  }

  @Override
  public Node deleteAt(int index) {

    if (index >= this.count || index < 0) {
      throw new Error("제거할 수 없습니다.");
    }

    Node currentNode = this.head;

    if (index == 0) { // 첫 번째 노드를 제거하는 경우
      Node deleteNode = this.head;
      if (this.head.getNext() == null) { // 데이터가 1개 일때, head의 다음 node가 null일 경우 모두 비워주면 된다.
        this.head = null;
        this.tail = null;
      } else { // 데이터가 2개 이상일 때
        this.head = this.head.getNext();
        this.head.setPrev(null);
      }
      this.count--;
      return deleteNode;
    } else if (index == this.count - 1) { // 마지막 노드를 제거하는 경우
      Node deleteNode = this.tail;
      this.tail.getPrev().setNext(null);
      this.tail = this.tail.getPrev();
      this.count--;
      return deleteNode;
    } else { // 그 외 노드를 제거하는 경우
      for (int i = 0; i < index - 1; i++) {
        currentNode = currentNode.getNext();
      }
      Node deleteNode = currentNode.getNext();
      currentNode.setNext(currentNode.getNext().getNext());
      currentNode.getNext().setPrev(currentNode);
      this.count--;
      return deleteNode;
    }
  }

  @Override
  public Node deleteLast() {
    try{
      return this.deleteAt(this.count - 1);
    } catch (Error e){
      return null;
    }
  }

  @Override
  public Node getNodeAt(int index) {
    if (index >= this.count || index < 0) {
      throw new Error("범위를 넘어갔습니다.");
    }

    Node currentNode = this.head;
    for (int i = 0; i < index; i++) {
      currentNode = currentNode.getNext();
    }
    return currentNode;
  }


  public Node getHead() {
    return head;
  }

  public Node getTail() {
    return tail;
  }

  public int getCount() {
    return count;
  }

}
