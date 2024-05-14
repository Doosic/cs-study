package list;

public interface List <T> {

  void printAll();
  void clear();
  void insertAt(int index, T data);
  void insertLast(T data);
  void deleteAt(int index);
  void deleteLast();
  Node getNodeAt(int index);
}
