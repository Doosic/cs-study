package queue;

import queue.List;

public class Queue<T> {

  private DoublyLinkedList<T> list;

  public Queue(){
    this.list = new DoublyLinkedList<>();
  }

  public void printAll() {
    this.list.printAll();
  }

  public void clear() {
    this.list.clear();
  }

  public void enqueue(T data){
    this.list.insertAt(0, data);
  }

  public Node dequeue() {
    try{
      return this.list.deleteLast();
    } catch (Error e){
      return null;
    }
  }

  public Node front() {
    return this.list.getTail();
  }

  public boolean isEmpty() {
    return this.list.getCount() == 0;
  }

}
