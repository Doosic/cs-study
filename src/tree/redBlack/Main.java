package tree.redBlack;

public class Main {

  public static void main(String[] args) {
    RedBlackTree redBlackTree = new RedBlackTree<>();
    redBlackTree.insert(17);
    redBlackTree.insert(9);
    redBlackTree.insert(19);
    redBlackTree.insert(75);
    redBlackTree.insert(85);

    System.out.println(redBlackTree.getRoot().getData());

    if(redBlackTree.getRoot() != null){
      redBlackTree.getRoot().inOrderTraversal(redBlackTree.getRoot());
    }
  }
}
