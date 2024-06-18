package stack;

public class Stack<T> {

  /*
   * push - 데이터 삽입
   * pop - 데이터 제거
   * peek - 데이터 참조
   * isEmpty - 데이터가 비어있는지 확인
   * */

  LinkedList<T> list;

  public Stack () {
    this.list = new LinkedList<>();
  }

  public void push(T data){
    // stack의 맨 위에 데이터를 추가
    this.list.insertAt(0, data);
  }

  public Node pop() {
    try {
      // stack의 맨 위에 데이터를 제거
      return this.list.deleteAt(0);
    } catch (Error e){
      return null;
    }
  }

  public Node peek(){
    // stack의 맨 위 데이터 읽기
    return this.list.getNodeAt(0);
  }

  public boolean isEmpty() {
    // 데이터가 비어있는지 조회
    return (this.list.getCount() == 0);
  }
}
