package hashSet;

import hashSet.HashTable;

public class HashSet<T> {

  /**
   * - 데이터의 중복을 허용하지 않는 자료구조
   * set은 hashTable을 이용하여 구현한다. hashSet이라고 불리기도 한다.
   * value값은 사용하지 않으며 key값 만을 사용해서 구현한다.
   *
   * add(data) - 데이터 삽입
   * isContain(data) - 데이터 체크
   * remove(data) - 데이터 제거
   * clear() - 셋 비우기
   * isEmpty() - 셋이 비었는지 체크
   * printAll() - 모든 데이터 출력
   * */

  private HashTable hashTable;

  public HashSet() {
    this.hashTable = new HashTable();
  }

  public void add(int data){
    if(this.hashTable.get(data) == null){
      this.hashTable.set(data, -1);
    }
  }

  public boolean isContain(int data){
    return this.hashTable.get(data) != null;
  }

  public void remove(int data){
    this.hashTable.remove(data);
  }

  public void clear(){
    for(int i = 0; i < this.hashTable.getArr().length; i++){
      this.hashTable.getArr()[i].clear();
    }
  }

  public boolean isEmpty() {
    boolean empty = true;
    for(int i = 0; i < this.hashTable.getArr().length; i++){
      if(this.hashTable.getArr()[i].getCount() > 0){
        empty = false;
        break;
      }
    }
    return empty;
  }

  public void printAll() {
    for(int i = 0; i < this.hashTable.getArr().length; i++){
      Node<HashData<T>> currentNode = this.hashTable.getArr()[i].getHead();
      while(currentNode != null){
        System.out.println(currentNode.getData().getKey());
        currentNode = currentNode.getNext();
      }
    }
  }

}
