package hashSet;

public class HashTable<T> {

  /*
   * set - 데이터 삽입
   * get - 데이터 읽기
   * remove - 데이터 제거
   * */

  private DoublyLinkedList[] arr;

  public HashTable() {
    this.arr = new DoublyLinkedList[10];
    for(int i = 0; i < 10; i++){
      this.arr[i] = new DoublyLinkedList<>();
    }
  }

  public int hashFunction(int number) {
    return number % 10;
  }

  public void set(int key, T value){
    this.arr[this.hashFunction(key)].insertAt(0, new HashData<>(key, value));
  }

  public T get(int key){
    /*
    * 1.key값을 가지고 배열의 인덱스를 조회하여 DoublyLinkedList를 찾는다.
    *   key값 % 10으로 찾는다.
    * 2.DoublyLinkedList에 저장된 현재 노드의 key값과 입력된 key값을 비교하여
    *   일치한다면 값을 리턴해준다.
    * 3.입력된 key값을 가진 currentNode가 존재하지 않는다면 null을 리턴한다.
    * */
    Node<HashData<T>> currentNode = this.arr[this.hashFunction(key)].getHead();
    while(currentNode != null){
      if(currentNode.getData().getKey() == key){
        return (T) currentNode.getData().getValue();
      }
      currentNode = currentNode.getNext();
    }
    return null;
  }

  public Node remove(int key){
    /*
     * 1.key값을 이용해 배열에 저장된 DoublyLinkedList를 찾는다.
     * 2.currentNode를 찾아준다.
     * 3.입력받은 key값과 같은 key값을 가진 데이터까리 나올때 까지 삭제한다.
     * */
    DoublyLinkedList list = this.arr[this.hashFunction(key)];
    Node<HashData<T>> currentNode = list.getHead();
    int deleteNodeIndex = 0;

    while(currentNode != null){
      if(currentNode.getData().getKey() == key){
        return list.deleteAt(deleteNodeIndex);
      }
      currentNode = currentNode.getNext();
      deleteNodeIndex ++;
    }
    return null;
  }

  public DoublyLinkedList[] getArr() {
    return arr;
  }

  public void setArr(DoublyLinkedList[] arr) {
    this.arr = arr;
  }
}
