package tree.avl;


public class BinaryTree<T> implements Tree<T> {

  private T data;
  private BinaryTree leftSubTree;
  private BinaryTree rightSubTree;
  private int height = 1;

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

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void preOrderTraversal(BinaryTree tree){
    if(tree == null) return;
    System.out.println(tree.getData());
    this.preOrderTraversal(tree.getLeftSubTree());
    this.preOrderTraversal(tree.getRightSubTree());
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

  public BinaryTree removeLeftSubTree() {
    BinaryTree deletingNode = this.getLeftSubTree();
    this.setLeftSubTree(null);
    return deletingNode;
  }

  public BinaryTree removeRightSubTree() {
    BinaryTree deletingNode = this.getRightSubTree();
    this.setRightSubTree(null);
    return deletingNode;
  }

}
