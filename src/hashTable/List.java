package hashTable;

public interface List<T> {

  /**
   * - 연결리스트의 추상자료형
   * printAll() - 모든 데이터 출력
   * clear()- 모든 데이터 제거
   * insertAt(index, data) - 인덱스 삽입
   * insertLast(data) - 마지막 삽입
   * deleteAt(index) - 인덱스 삭제
   * deleteLast() - 마지막 삭제
   * getNodeAt(index) - 인덱스 읽기
   * */

  void printAll();
  void clear();
  void insertAt(int index, T data);
  void insertLast(T data);
  Node deleteAt(int index);
  Node deleteLast();
  Node getNodeAt(int index);
}
