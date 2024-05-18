package stack;

public class Node<T>{

  /***
   * Linked list는 Node라는 구조체로 이루어져있는데, Node는 데이터 값과 next Node의 address값이
   * 들어있습니다. 메모리상에는 비연속적으로 저장되지만 각각의 Node들이 들고있는 next Node address 값 때문에
   * 논리적인 연속성을 가지고 있는 자료구조 입니다.
   */

  private T data;
  private Node next;

  public Node(){
    this.data = null;
    this.next = null;
  }

  public Node(T data){
    this.data = data;
    this.next = null;
  }

  public T getData() {
    return this.data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Node getNext() {
    return this.next;
  }

  public void setNext(Node next) {
    this.next = next;
  }
}
