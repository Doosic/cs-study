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
    this.count = 0;
  }

  // insertAt(index, data) - 인덱스 삽입
  @Override
  public void insertAt(int index, T data) {
    /*
      - 0보다 작은 음수이거나, 현재 count보다 큰곳에 넣으려 할 경우
      데이터가 1개 있는 경우 데이터는 0번 인덱스에 존재하고 count는 1이다.
      이때 2번 이상의 인덱스에 삽입하게 된다면 1번 인덱스를 건너 뛰게 된다.
      그러므로 index(2) > this.count(1) 라는 경우가 나오면 Error를 던져준다.
    */
    if (index > this.count || index < 0) {
      throw new Error("범위를 넘어갔습니다.");
    }

    // 데이터 값을 가진 Node를 생성자를 통해 생성한다.
    Node newNode = new Node(data);

    // 0번 index에 데이터를 삽입 할 경우
    if (index == 0) {
      /*
          1.새로 생성된 노드의 next 노드를 현재 헤드에 저장된 노드로 지정한다.
          2.새로 생성된 노드가 0번 인덱스에 위치함으로 헤드가 된다.
       */
      newNode.setNext(this.head);
      this.head = newNode;
    } else {

      /*
          1.데이터를 삽입 할 노드의 위치까지 이동하기 위해 헤드노드를 변수에 담아준다.
          2.반복문을 통해 데이터를 삽입하기 전 노드까지 이동한다.
            Q.index-1을 통해 삽입 전 노드까지 이동하는 이유(2번 index에 새로운 node를 삽입한다 가정한다.)
             - 1번 노드에 nextNode값을 새로운 노드에게 주기 위해.(2번에 새로운 노드가 배치됨으로 기존 2번 노드는 3번이 된다.)
             - 1번 노드에 nextNodeAddress 값으로 새로운 노드를 지정하기 위해
          3.count를 1증가시켜준다.
       */
      Node currentNode = this.head;

      // 삽입 할 위치의 전 노드까지 이동
      for (int i = 0; i < index - 1; i++) {
        currentNode = currentNode.getNext();
      }

      // 중간위치에 삽입되는 노드에는 현재 노드의 다음 노드를 연결해준다.
      newNode.setNext(currentNode.getNext());
      // 현재 노드는 새로 삽입된 노드를 가리켜준다
      currentNode.setNext(newNode);
    }
    this.count++;
  }

  // insertLast(data) - 마지막 삽입
  @Override
  public void insertLast(T data) {
    this.insertAt(this.count, data);
  }

  // deleteAt(index) - 인덱스 삭제
  @Override
  public void deleteAt(int index) {

    /*
        - index가 음수거나, 현재 count와 같거나 큰 곳에 데이터를 삭제하려 하는 경우
          node에 데이터가 1개 들어있을 경우 0번에 데이터가 존재한다. 이때 count는 데이터가 1개 들어있기에
          1이라는 값을 가진다. index 0번에 데이터가 들어있고 count는 1을 가리키기에 데이터를 제거할 수 없다.
     */
    if (index >= this.count || index < 0) {
      throw new Error("제거할 수 없습니다.");
    }

    Node currentNode = this.head;

    if (index == 0) {
      /*
          1.0번 인덱스의 데이터를 제거할 경우 next 노드가 head 노드가 된다.
            즉, 1번 인덱스의 노드가 0번노드로 순위가 올라가는 것이다.
          2.노드 하나가 제거되었음으로 count -1 해준다.
       */
      Node deleteNode = this.head;
      this.head = this.head.getNext();
      this.count--;
    } else {
      /*
          1.제거해야 할 노드의 전 노드까지 이동한다.(2번 인덱스의 노드를 제거한다 가정한다.)
          2.1번 노드로 이동한 후에 1번 노드의 next 노드를 3번 노드로 지정하여 2번 노드에 대한 참조를 해제한다.
          3.노드 하나가 제거되었음으로 count -1 해준다.
       */
      for (int i = 0; i < index - 1; i++) {
        currentNode = currentNode.getNext();
      }
      Node deleteNode = currentNode.getNext();
      currentNode.setNext(currentNode.getNext().getNext());
      this.count--;
    }
  }

  @Override
  public void deleteLast() {
    this.deleteAt(this.count - 1);
  }

  // getNodeAt(index) - 인덱스 읽기
  @Override
  public Node getNodeAt(int index) {
    /*
        - index가 음수거나, 현재 count와 같거나 큰 곳에 데이터를 조회하려는 경우
          데이터가 1개일시 인덱스 0번에 데이터가 들어있으며, count는 1번이기 때문에
          count보다 크거나 같은 곳의 데이터는 조회할 수 없다.
     */
    if (index >= this.count || index < 0) {
      throw new Error("범위를 넘어갔습니다.");
    }

    Node currentNode = this.head;
    for (int i = 0; i < index; i++) {
      currentNode = currentNode.getNext();
    }
    return currentNode;
  }

}
