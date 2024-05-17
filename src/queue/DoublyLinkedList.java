package queue;

public class DoublyLinkedList<T> implements List<T>{

  /*
      - 이중 연결리스트
      큐를 구현하기 위해 head의 정보와 tail의 정보를 저장한다.
      데이터가 처음 진입하는 곳이 head, 데이터가 추출되는 곳이 tail 이다.
   */

  private Node head;
  private Node tail;
  private int count = 0;

  public DoublyLinkedList() {
    this.head = null;
    this.tail = null;
    this.count = 0;
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
    /*
        데이터를 넣을 수 있는 최대 위치는 0번 index ~ count 까지다.
        처음, 중간, 맨 마지막 위치에 삽입
     */
    if (index > this.count || index < 0) {
      throw new Error("범위를 넘어갔습니다.");
    }

    Node newNode = new Node(data);

    if (index == 0) { // head에 삽입하는 경우
      /*
          1.new 노드에 next 노드로 현재 노드를 지정한다.
          2.만약 현재 head가 null이 아니라면 prev 노드로 new 노드를 지정한다.(2번 인덱스의 노드가 1번 노드를 prev로 가지고 있는 상황)
          3.서로를 참조하는 작업이 끝난후에 new 노드를 head로 지정해준다.
       */
      newNode.setNext(this.head);
      if (this.head != null) {
        this.head.setPrev(newNode);
      }
      this.head = newNode;
    } else if (index == this.count) { // tail에 삽입하는 경우
      /*
          1.tail에 node를 삽입하는 경우 next 노드는 존재하지 않기에 null로 지정한다.
          2.현재 tail을 new 노드에 prev로 지정한다.
          3.tail의 다음 node를 new 노드로 지정한다.
          4.tail을 newNode로 변경하는데 이건 조건문 바깥으로 나가서 마지막에 작업해준다.
       */
      newNode.setNext(null);
      newNode.setPrev(this.tail);
      this.tail.setNext(newNode);
    } else { // 그 외 위치에 삽입하는 경우
      /*
          1.새로운 노드를 삽입하기 전 위치까지 이동한다.
          2.new 노드에 next 노드로 현재 노드의 next 노드로 지정한다.
          3.prev로는 현재 노드를 지정한다.
          4.현재 노드에 next 노드로는 new 노드를 지정하낟.
          5.new 노드에 다음 노드의 prev는 new 노드가 된다.
       */
      Node currentNode = this.head;

      for (int i = 0; i < index - 1; i++) {
        currentNode = currentNode.getNext();
      }

      newNode.setNext(currentNode.getNext());
      newNode.setPrev(currentNode);
      currentNode.setNext(newNode);
      newNode.getNext().setPrev(newNode);
    }

    /*
        new 노드에 next가 null인 경우는 new 노드가 tail인 경우 외에는 없다.
        그러므로 tail에 new 노드를 할당한다.
     */
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
    /*
        - index가 음수거나, 현재 count와 같거나 큰 곳에 데이터를 삭제하려 하는 경우
          node에 데이터가 1개 들어있을 경우 0번에 데이터가 존재한다. 이때 count는 데이터가 1개 들어있기에
          1이라는 값을 가진다. index 0번에 데이터가 들어있고 count는 1을 가리키기에 데이터를 제거할 수 없다.
     */
    if (index >= this.count || index < 0) {
      throw new Error("제거할 수 없습니다.");
    }

    Node currentNode = this.head;

    if (index == 0) { // 첫 번째 노드를 제거하는 경우
      /*
          첫 번째 노드를 제거할 경우 next 노드가 존재하는 경우와 존재하지 않는 경우
          2개의 경우를 구분하여 제거해야 한다.
       */
      Node deleteNode = this.head;
      if (this.head.getNext() == null) {
        // 데이터가 1개 일때, head의 다음 node가 null일 경우 모두 비워준다.
        this.head = null;
        this.tail = null;
      } else {
        // 데이터가 2개 이상일 때는 next 노드를 현재 노드로 변경한다.
        // head인 0번 노드에 next인 1번 노드를 삽입하고, head(0번)이 된 1번 노드에 prev를 제거한다.
        this.head = this.head.getNext();
        this.head.setPrev(null);
      }
      this.count--;
      return deleteNode;
    } else if (index == this.count - 1) { // 마지막 노드를 제거하는 경우
      /*
          1.마지막 이전 노드에 next 노드(즉, tail)의 참조를 해제한다.(3번이 마지막이라면 2번이 3번에 대한 참조를 해제)
          2.tail에 이전 노드를 할당한다.(2번 노드를 tail에 할당한다.)
       */
      Node deleteNode = this.tail;
      this.tail.getPrev().setNext(null);
      this.tail = this.tail.getPrev();
      this.count--;
      return deleteNode;
    } else { // 그 외 노드를 제거하는 경우
      /*
         1.삭제하기 전 노드까지 이동한다.
         2.현재 노드의 next노드를 next,next 노드로 변경한다.(3번을 제거할 경우, 2번의 next로 4번을 할당.)
         3.4번의 prev 노드로 현재 노드를 할당
         4.count 를 1 빼준다.
       */
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
