package tree;

public class Main {

  public static void main(String[] args) {
    BinaryTree tree1 = new BinaryTree(1);
    BinaryTree tree2 = new BinaryTree(2);
    BinaryTree tree3 = new BinaryTree(3);
    BinaryTree tree4 = new BinaryTree(4);
    BinaryTree tree5 = new BinaryTree(5);
    BinaryTree tree6 = new BinaryTree(6);
    BinaryTree tree7 = new BinaryTree(7);

    tree1.setLeftSubTree(tree2);
    tree1.setRightSubTree(tree3);
    tree2.setLeftSubTree(tree4);
    tree2.setRightSubTree(tree5);
    tree3.setLeftSubTree(tree6);
    tree3.setRightSubTree(tree7);

    System.out.println("루트의 오른쪽 자식 노드: " + tree1.getRightSubTree().getData());
    System.out.println("루트의 오른쪽 자식 노드의 왼쪽 자식노드: " + tree1.getRightSubTree().getLeftSubTree().getData());

    System.out.println("===== 전위순회 =====");
    tree1.preOrderTrabersal(tree1);

    System.out.println("===== 중위순회 =====");
    tree1.inOrderTraversal(tree1);

    System.out.println("===== 후위순회 =====");
    tree1.postOrderTraversal(tree1);
  }
}
