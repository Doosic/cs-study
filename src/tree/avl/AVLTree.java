package tree.avl;

public class AVLTree<T> implements AVL<T>{

  /*

   */

  private BinaryTree<Integer> root;

  public AVLTree(){};

  public AVLTree(BinaryTree rootNode) {
    this.root = rootNode;
  }

  public BinaryTree<Integer> search(int targetData){
    BinaryTree<Integer> currentNode = this.root;

    // currentNode가 빈 Node를 만나기 전까지 이동한다.
    while(currentNode != null){
      // targetNode를 찾는다면 리턴
      if(currentNode.getData() == targetData){
        return currentNode;
      } else if (currentNode.getData() > targetData){
        // targetData가 더 작다면 leftSubTree로 이동
        currentNode = currentNode.getLeftSubTree();
      } else {
        // targetData가 더 크다면 rightSubTree로 이동
        currentNode = currentNode.getRightSubTree();
      }
    }

    currentNode = new BinaryTree<>(null);
    return currentNode;
  }

  // 노드의 현재 높이를 구한다.
  public int getHeight(BinaryTree node){
    if(node == null){
      return 0;
    }else{
      return node.getHeight();
    }
  }

  public void updateHeight(BinaryTree node){
    int leftChildHeight = this.getHeight(node.getLeftSubTree());
    int rightChildHeight = this.getHeight(node.getRightSubTree());
    node.setHeight(Math.max(leftChildHeight, rightChildHeight) + 1);
  }

  // 노드 좌우의 균형을 잡는다.
  public int getBalanceFactor(BinaryTree node) {
    return this.getHeight(node.getLeftSubTree()) - this.getHeight(node.getRightSubTree());
  }

  // LL회전 구현(1,3,5 값을 가진 오른쪽으로 치우쳐진 트리라고 가정)
  // 3이 Root 가 되어야 한다
  public BinaryTree rotateLeft(BinaryTree node){
    // root(1)의 rightNode(3)을 childNode에 담아둔다.
    BinaryTree childNode = node.getRightSubTree();

    // rightNode(3)이 root가 되어야한다. 이때 rightNode에 leftNode(2)가 존재한다면
    // 이 값을 버릴수는 없다. leftNode(2)를 root(1)의 rightNode(2)로 변경을 해준다.
    // rightNode는 항상 더 큰 값을 가지기 때문에 root(1)의 rightNode(2)로 변경이 가능한 것이다.
    node.setRightSubTree(childNode.getLeftSubTree());

    // childNode(3)의 leftNode로 기존 root(1)가 할당된다.
    childNode.setLeftSubTree(node);

    // root의 높이를 재조정 한다.
    this.updateHeight(node);
    // childNode의 높이를 재조정한다.
    this.updateHeight(childNode);

    return childNode;
  }

  // RR회전 구현(5,3,1 값을 가진 왼쪽으로 치우쳐진 트리라고 가정)
  // 3이 Root가 되어야 한다
  public BinaryTree lotateRight(BinaryTree node){
    // childNode = 3
    BinaryTree childNode = node.getLeftSubTree();

    // childNode의 오른쪽 자식노드인 4를 5의 왼쪽 자식노드로 변경해준다.
    node.setLeftSubTree(childNode.getRightSubTree());

    // 3이 루트노드가 되고 5는 3의 오른쪽 자식노드가 된다.
    childNode.setRightSubTree(node);

    // root의 높이를 재조정 한다.
    this.updateHeight(node);
    // childNode의 높이를 재조정한다.
    this.updateHeight(childNode);

    return childNode;
  }



  public BinaryTree getRoot() {
    return this.root;
  }

}
