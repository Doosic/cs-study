package tree;

public class BinaryTree<T> implements Tree{

  private T data;
  private BinaryTree leftSubTree;
  private BinaryTree rightSubTree;

  public BinaryTree(T data){
    this.data = data;
    this.leftSubTree = null;
    this.rightSubTree = null;
  }

  public BinaryTree(T data, BinaryTree leftTree, BinaryTree rightTree){
    this.data = data;
    this.leftSubTree = leftTree;
    this.rightSubTree = rightTree;
  }

  public T getData() {
    return this.data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public BinaryTree getLeftSubTree() {
    return this.leftSubTree;
  }

  public BinaryTree getRightSubTree() {
    return this.rightSubTree;
  }

  public void setLeftSubTree(BinaryTree binaryTree) {
    this.leftSubTree = binaryTree;
  }

  public void setRightSubTree(BinaryTree binaryTree) {
    this.rightSubTree = binaryTree;
  }

  public void preOrderTrabersal(BinaryTree tree){
    if(tree == null) return;
    System.out.println(tree.getData());
    this.preOrderTrabersal(tree.getLeftSubTree());
    this.preOrderTrabersal(tree.getRightSubTree());
  }

  public void inOrderTraversal(BinaryTree tree){
    if(tree == null) return;

    this.inOrderTraversal(tree.getLeftSubTree());
    System.out.println(tree.getData());
    this.inOrderTraversal(tree.getRightSubTree());
  }

  public void postOrderTraversal(BinaryTree tree){
    if(tree == null) return;

    this.postOrderTraversal(tree.getLeftSubTree());
    this.postOrderTraversal(tree.getRightSubTree());
    System.out.println(tree.getData());
  }

}
