package deque;

public class Deque<T> {

  /*
   * 덱은 데이터의 삽입과 제거를 head와 tail에서 자유롭게 한다.
   *
   * printAll - 모든 데이터  출력
   * addFirst - head에 데이터 삽입
   * removeFirst - head에 데이터 제거
   * addLast - tail에 데이터 삽입
   * removeLast - tail에서 데이터 제거
   * isEmpty - 리스트 비었는지 체크
   */

  private DoublyLinkedList list;

  public Deque() {
    this.list = new DoublyLinkedList();
  }

  public void printAll() {
    this.list.printAll();
  }

  public void addFirst(T data){
    this.list.insertAt(0, data);
  }

  public Node removeFirst() {
    return this.list.deleteAt(0);
  }

  public void addLast(T data){
    this.list.insertAt(this.list.getCount(), data);
  }

  public Node removeLast() {
    return this.list.deleteLast();
  }

  public boolean isEmpty() {
    return (this.list.getCount() == 0);
  }

}
