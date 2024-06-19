package tree.avl;

import java.util.Optional;

public class AVLTree<T> implements AVL<T> {

  /*

   */

  private BinaryTree<Integer> root;

  public AVLTree() {};

  public AVLTree(BinaryTree rootNode) {
    this.root = rootNode;
  }

  public BinaryTree<Integer> search(int targetData) {
    BinaryTree<Integer> currentNode = this.root;

    // currentNode가 빈 Node를 만나기 전까지 이동한다.
    while (currentNode != null) {
      // targetNode를 찾는다면 리턴
      if (currentNode.getData() == targetData) {
        return currentNode;
      } else if (currentNode.getData() > targetData) {
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
  public int getHeight(BinaryTree node) {
    if (node == null) {
      return 0;
    } else {
      return node.getHeight();
    }
  }

  public void updateHeight(BinaryTree node) {
    int leftChildHeight = this.getHeight(node.getLeftSubTree());
    int rightChildHeight = this.getHeight(node.getRightSubTree());
    node.setHeight(Math.max(leftChildHeight, rightChildHeight) + 1);
  }

  // 노드 좌우의 균형을 잡는다.
  // 값이 양수라면 왼쪽 노드의 높이가 더 높은것이고, 음수라면 오른쪽 노드의 높이가 더 높은것이다.
  // 2-1 = 1, 1-2 = -1
  public int getBalanceFactor(BinaryTree node) {
    return this.getHeight(node.getLeftSubTree()) - this.getHeight(node.getRightSubTree());
  }

  // LL회전 구현(1,3,5 값을 가진 오른쪽으로 치우쳐진 트리라고 가정)
  // 3이 Root 가 되어야 한다
  public BinaryTree rotateLeft(BinaryTree node) {
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
  // childNode가 rootNode가 되어야 한다
  public BinaryTree rotateRight(BinaryTree node) {
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

  // 어떤 회전을 시킬지 판단하고 회전시킨다.
  public BinaryTree<Integer> rotation(BinaryTree<Integer> targetNode, int data) {
    int balanceFactor = this.getBalanceFactor(targetNode);
    boolean isRootNode = false;

    if (targetNode == this.root) {
      isRootNode = true;
    }

    // 밸런스 팩터가 -1보다 작으면 오른쪽 자식노드가 높다는 뜻이다.
    // 그러나 RL 회전과 LL 회전 둘다 벨런스 팩터가 -1이므로 data의 위치를 기준으로 어떤 회전을 쓸지 구분이 필요하다.
    // targetNode의 왼쪽 자식노드보다 data가 크다면 LR 회전
    // targetNode의 오른쪽 자식노드보다 data가 작다면 RL 회전
    Integer rightSubTreeData;
    Integer leftSubTreeData;

    try{
      rightSubTreeData = (Integer) targetNode.getRightSubTree().getData();
      leftSubTreeData = (Integer) targetNode.getLeftSubTree().getData();
    }catch (NullPointerException e){
      rightSubTreeData = null;
      leftSubTreeData = null;
    }

    if (balanceFactor < -1 && rightSubTreeData != null && data > rightSubTreeData) {
      // LL 회전
      targetNode = this.rotateLeft(targetNode);
    } else if (balanceFactor > 1 && leftSubTreeData != null && data < leftSubTreeData) {
      // RR 회전
      targetNode = this.rotateRight(targetNode);
    } else if (balanceFactor > 1 && leftSubTreeData != null && data > leftSubTreeData) {
      // LR 회전
      targetNode.setLeftSubTree(this.rotateLeft(targetNode.getLeftSubTree()));
      targetNode = this.rotateRight(targetNode);
    } else if (balanceFactor < -1 && rightSubTreeData != null && data < rightSubTreeData) {
      // RL 회전
      targetNode.setRightSubTree(this.rotateRight(targetNode.getRightSubTree()));
      targetNode = this.rotateLeft(targetNode);
    }

    if (isRootNode) {
      this.root = targetNode;
    }

    return targetNode;
  }

  // remove 구현
  // 데이터를 삽입할때는 삽입하는 데이터가 균형을 무너트리지만 삭제할때는 어떤 데이터가 균형을 무너트리는지 알 수 없음으로 찾아줘야한다.
  public BinaryTree getUnBalanceNode(BinaryTree targetRootNode, BinaryTree unBalanceNode) {
    // 기저조건: 자식 노드가 아무것도 없을 때. 즉, 맨 마지막에 위치한 노드
    if (targetRootNode.getLeftSubTree() == null && targetRootNode.getRightSubTree() == null) {
      unBalanceNode = targetRootNode;
      return unBalanceNode;
    }

    int balanceFactor = this.getBalanceFactor(targetRootNode);
    if (balanceFactor > 0) {
      unBalanceNode = this.getUnBalanceNode(targetRootNode.getLeftSubTree(), unBalanceNode);
    } else if (balanceFactor < 0) {
      unBalanceNode = this.getUnBalanceNode(targetRootNode.getRightSubTree(), unBalanceNode);
    } else {
      unBalanceNode = targetRootNode.getRightSubTree();
    }

    return unBalanceNode;
  }

  // 상향식 접근으로는 자식 노드들의 높이 데이터가 업데이트되지 않는다. 가능하지만 비효율 적이다.
  // 또한 서브트리의 높이가 수정된다면 root부터 모든 부모트리의 높이가 수정되어야 한다.
  // 재귀로 접근한다면 자식 노드들을 검사하다가 균형이 무너지지않았다면 높이만 업데이트하고, 무너졌다면 회전시켜준다.
  // 가장 마지막 노드의 높이는 Math.max((0 + 0) + 1) 이고 그 다음 부모 노드는 Math.max((1 + 1) + 1) 이므로 2가 된다. 재귀를 통해
  // 높이를 업데이트 하는 방식이 가장 효율적이다.
  public BinaryTree insert(BinaryTree<Integer> targetRootNode, int data) {
    // 기저조건: 루트 또는 터미널 노드
    if (targetRootNode == null) {
      targetRootNode = new BinaryTree(data);
    }

    // AVL 트리에 처음 데이터를 삽입하는 경우
    if(this.root == null){
      this.root = targetRootNode;
    }else if(targetRootNode.getData() == data){
      // 중복데이터를 삽입하는 경우
      return targetRootNode;
    }else if(targetRootNode.getData() > data){
      // null 을 가리키는 값이 나올때까지 호출하여 터미널 노드에 값을 연결한다.
      targetRootNode.setLeftSubTree(this.insert(targetRootNode.getLeftSubTree(), data));
    }else{
      targetRootNode.setRightSubTree(this.insert(targetRootNode.getRightSubTree(), data));
    }

    // 재귀를 이용하여 계산하므로 가장 아래있는 노드부터 높이가 업데이트 된다.
    this.updateHeight(targetRootNode);
    // 높이가 맞지 않는다면 회전작업을 진행한다.
    targetRootNode = this.rotation(targetRootNode, data);

    return targetRootNode;
  }

  public BinaryTree remove(BinaryTree<Integer> targetRootNode, int data, BinaryTree<Integer> parentNode){
    // 삭제할 데이터가 왼쪽 자식노드에 존재하는 경우
    if(targetRootNode.getData() > data && targetRootNode.getLeftSubTree() != null){
      // 데이터 제거시 제거된 노드를 대체하는 자식노드가 리턴된다.
      targetRootNode.setLeftSubTree(this.remove(targetRootNode.getLeftSubTree(), data, targetRootNode));
     }else if(targetRootNode.getData() < data && targetRootNode.getRightSubTree() != null){
      // 삭제할 데이터가 오른쪽 자식노드에 존재하는 경우
      targetRootNode.setRightSubTree(this.remove(targetRootNode.getRightSubTree(), data, targetRootNode));
    }else if(targetRootNode.getData() == data){
      targetRootNode = this.removeHelper(targetRootNode, parentNode);

      if(parentNode == null && targetRootNode != null){
        this.updateHeight(targetRootNode);
        BinaryTree unBalanceNode = this.getUnBalanceNode(targetRootNode, new BinaryTree(null));
        targetRootNode = this.rotation(targetRootNode, (int) unBalanceNode.getData());
      }

      return targetRootNode;
    }

    this.updateHeight(targetRootNode);
    BinaryTree unBalanceNode = this.getUnBalanceNode(targetRootNode, new BinaryTree(null));
    targetRootNode = this.rotation(targetRootNode, (int) unBalanceNode.getData());
    return targetRootNode;
  }

  public BinaryTree removeHelper(BinaryTree<Integer> deletingNode, BinaryTree parentNode){
    BinaryTree<Integer> fakeParentRootNode = new BinaryTree(0);
    fakeParentRootNode.setRightSubTree(this.root);

    if(parentNode == null){
      parentNode = fakeParentRootNode;
    }

    BinaryTree deletingNodeChild = null;
    if(deletingNode.getLeftSubTree() == null && deletingNode.getRightSubTree() == null){
      if(parentNode.getLeftSubTree() == deletingNode){
        parentNode.removeLeftSubTree();
      } else {
        parentNode.removeRightSubTree();
      }
    } else if (deletingNode.getLeftSubTree() == null || deletingNode.getRightSubTree() == null){
      if(deletingNode.getLeftSubTree() != null){
        deletingNodeChild = deletingNode.getLeftSubTree();
      } else {
        deletingNodeChild = deletingNode.getRightSubTree();
      }

      // 부모노드의 왼쪽, 오른쪽 노드중 어느것을 제거하는지 파악 후 손자노드를 자식노드로 연결
      if(parentNode.getLeftSubTree() == deletingNode){
        parentNode.setLeftSubTree(deletingNodeChild);
      } else {
        parentNode.setRightSubTree(deletingNodeChild);
      }
    } else {
      BinaryTree<Integer> replacingNode = deletingNode.getLeftSubTree();
      BinaryTree<Integer> replacingNodeParent = deletingNode;

      while(replacingNode.getRightSubTree() != null){
        replacingNodeParent = replacingNode;
        replacingNode = replacingNode.getRightSubTree();
      }

      int deletingNodeData = deletingNode.getData();
      deletingNode.setData(replacingNode.getData());

      if(replacingNodeParent.getLeftSubTree() == replacingNode){
        replacingNodeParent.setLeftSubTree(replacingNode.getLeftSubTree());
      }else{
        replacingNodeParent.setRightSubTree(replacingNode.getLeftSubTree());
      }

      deletingNodeChild = deletingNode;
    }

    if(fakeParentRootNode.getRightSubTree() != this.root){
      this.root = fakeParentRootNode.getRightSubTree();
    }

    return deletingNodeChild;
  }


  public BinaryTree getRoot() {
    return this.root;
  }

}
