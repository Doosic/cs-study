package list;

public class LinkedList<T> implements List<T> {

  private Node head;
  private int count = 0;

  public LinkedList() {
    this.head = null;
    this.count = 0;
  }

  public LinkedList(Node head) {
    this.head = head;
  }

  @Override
  public void printAll() {
    Node currentNode = this.head;

    while (currentNode != null){
      System.out.println(currentNode.getData());
      currentNode = currentNode.getNext();
    }
  }

  @Override
  public void insertAt(int index, T data) {
    if(index > this.count || index < 0){
        throw new Error("범위를 넘어갔습니다.");
    }

    // 1.list의 가장 앞부분에 삽입하는 경우. 새로 삽입하는 노드가 head가 되어야 한다.
    // 2.가장 앞부분을 제외한 나머지 부분에 삽입하는 경우. head 노드에서 next로 목표 인덱스까지 이동.
    Node newNode = new Node(data);

    if(index == 0){
      newNode.setNext(this.head);
      this.head = newNode;
    } else {
      Node currentNode = this.head;

      // 삽입 할 위치의 전 노드까지 이동
      for(int i = 0; i < index - 1; i++){
        currentNode = currentNode.getNext();
      }

      // 중간위치에 삽입되는 노드에는 현재 노드의 다음 노드를 연결해준다.
      newNode.setNext(currentNode.getNext());
      // 현재 노드는 새로 삽입된 노드를 가리켜준다
      currentNode.setNext(newNode);
    }
    this.count++;
  }

}
