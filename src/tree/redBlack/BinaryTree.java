package tree.redBlack;


public class BinaryTree<T> implements Tree<T>{

  private final boolean RED = false;
  private final boolean BLACK = true;

  private T data;
  private BinaryTree leftSubTree;
  private BinaryTree rightSubTree;
  private BinaryTree parentTree;
  private boolean color = RED;

  // 부모노드, 자식노드 서로가 참조가능한 양방향 노드
  public BinaryTree(T data){
    this.data = data;
    this.leftSubTree = null;
    this.rightSubTree = null;
    this.parentTree = null;

    this.color = RED;
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

  public boolean isRED() {
    return RED;
  }

  public boolean isBLACK() {
    return BLACK;
  }

  public BinaryTree getParentTree() {
    return parentTree;
  }

  public void setParentTree(BinaryTree parentTree) {
    this.parentTree = parentTree;
  }

  public boolean isColor() {
    return color;
  }

  public void setColor(boolean color) {
    this.color = color;
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

  public BinaryTree remeveRightSubTree() {
    BinaryTree deletingNode = this.getRightSubTree();
    this.setRightSubTree(null);
    return deletingNode;
  }

}
