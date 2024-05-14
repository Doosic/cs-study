package list;

public class Node <T>{

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

  public Node(T data, Node next) {
    this.data = data;
    this.next = next;
  };

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
