package tree.avl;

public class Main {

  public static void main(String[] args) {
    AVLTree avlTree = new AVLTree();
    System.out.println("===== insert =====");
    avlTree.insert(avlTree.getRoot(),1);
    avlTree.insert(avlTree.getRoot(),3);
    avlTree.insert(avlTree.getRoot(),2);
    avlTree.insert(avlTree.getRoot(),6);
    avlTree.insert(avlTree.getRoot(),4);
    avlTree.insert(avlTree.getRoot(),7);
    avlTree.insert(avlTree.getRoot(),5);
//    System.out.println(avlTree.getRoot().getData());

    avlTree.getRoot().inOrderTraversal(avlTree.getRoot());

    System.out.println("===== remove =====");
//    avlTree.remove(avlTree.getRoot(), 2, new BinaryTree<>(null));
//    avlTree.remove(avlTree.getRoot(), 3, new BinaryTree<>(null));
    avlTree.remove(avlTree.getRoot(), 1, new BinaryTree<>(null));

//    System.out.println(avlTree.getRoot().getData());
    avlTree.getRoot().inOrderTraversal(avlTree.getRoot());

    System.out.println("===== search =====");
    System.out.println(avlTree.search(7).getData());
  }
}
