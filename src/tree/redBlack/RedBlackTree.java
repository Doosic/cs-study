package tree.redBlack;

import tree.redBlack.BinaryTree;

public class RedBlackTree<T> implements RedBlack{

  private BinaryTree<Integer> root;

  public RedBlackTree(){};

  public RedBlackTree(BinaryTree rootNode) {
    this.root = rootNode;
  }

  // 해당 주석은 아래 값을 기준으로한다. 목적 LL회전을 통해 7(rightChild)을 5(node)의 위치와 변경해 node로 만든다.
  // 3(parent), 5(node), 7(rightChild), 9(손자) 오른쪽으로 뻗은 노드
  public void rotateLeft(BinaryTree node){
    BinaryTree parent = node.getParentTree();
    BinaryTree rightChild = node.getRightSubTree();

    // 7(rightChild)의 왼쪽 자식노드를 5(node)의 오른쪽 자식 노드로 변경한다.
    // 값이 존재하지 않는다면 NILL 노드. null값으로 두면 된다.
    node.setRightSubTree(rightChild.getLeftSubTree());

    // 만일 NIL 노드가 아니라면 7의 왼쪽 자식노드는 5를 새로운 부모 노드로 지정한다.
    if(rightChild.getLeftSubTree() != null){
      rightChild.getLeftSubTree().setParentTree(node);
    }

    // 7의 새로운 자식노드는 5가된다.
    rightChild.setLeftSubTree(node);
    // 5의 새로운 부모노드는 7이된다.
    node.setParentTree(rightChild);

    // 해당 함수를 통해 3의 오른쪽 자식을 7로 변경한다. 7의 부모를 3으로 변경한다.
    this.replaceParentsChild(parent, node, rightChild);
  }

  public void rotateRight(BinaryTree node){
    BinaryTree parent = node.getParentTree();
    BinaryTree leftChild = node.getLeftSubTree();

    node.setLeftSubTree(leftChild.getRightSubTree());

    if(leftChild.getRightSubTree() != null){
      leftChild.getRightSubTree().setParentTree(node);
    }

    leftChild.setRightSubTree(node);
    node.setParentTree(leftChild);

    this.replaceParentsChild(parent, node, leftChild);
  }

  public void replaceParentsChild(BinaryTree parent, BinaryTree oldChild, BinaryTree newChild) {
    // parent가 null인 경우는 해당 노드가 root인 경우 뿐이다.
    // 그렇기에 parent가 존재하지 않는다면 root로 변경한다.
    if(parent == null){
      this.root = newChild;
      // parent에 leftNode가 이전 노드라면 left를 새로운 노드로 변경한다.
    }else if(parent.getLeftSubTree() == oldChild){
      parent.setLeftSubTree(newChild);
      // prent에 rightNode가 이전 노드라면 right를 새로운 노드로 변경한다.
    }else if(parent.getRightSubTree() == oldChild){
      parent.setRightSubTree(newChild);
    }

    // newChild인 7의 새로운 부모로 parent 즉, 3을 지정한다.
    if(newChild != null){
      newChild.setParentTree(parent);
    }
  }

  public void insert(int data){
    BinaryTree<Integer> current = this.root;
    BinaryTree<Integer> parent = null;

    // current 변수가 null이 될때까지 삽입 할 위치를 찾아간다.
    // parent에는 삽입할 변수의 부모노드가 담긴다.
    while(current != null){
      parent = current;
      if(data < current.getData()){
        current = current.getLeftSubTree();
      }else if(data > current.getData()){
        current = current.getRightSubTree();
      }else{
        return;
      }
    }

    // 입력받은 data 값을 통해 삽입할 노드를 생성한다.
    BinaryTree newNode = new BinaryTree(data);

    // parent가 존재하지 않을 경우 root가 된다.
    if(parent == null){
      this.root = newNode;
      // parent의 값보다 작을경우 왼쪽 노드가 된다.
    }else if(data < parent.getData()){
      parent.setLeftSubTree(newNode);
      // parent의 값보다 클경우 오른쪽 노드가 된다.
    }else {
      parent.setRightSubTree(newNode);
    }

    // 삽입된 노드의 부모노드는 parent가 된다.
    newNode.setParentTree(parent);
    // red-black 트리의 균형을 맞춰준다.
    this.rebalanceAfterInsertion(newNode);
  }

  /*
    - 4가지 상황을 고려해서 로직작성
      1.새로운 노드가 루트노드인 경우
      2.부모노드와 삼촌노드가 빨간색인 경우
      3.부모노드는 빨간색이고 삼촌노드는 검은색, 새로운 노드는 안쪽 손자인 경우
      4.부모노드는 빨간색이고 삼촌노드는 검은색이고 새로운 노드는 바깥쪽 손자인 경우
   */
  public void rebalanceAfterInsertion(BinaryTree node) {
    BinaryTree parent = node.getParentTree();

    /*
      1 - 새로운 노드가 루트노드인 경우
      새로운 노드가 루트노드인 경우(블랙으로 변경한다)
      할아버지 노드인 경우도 블랙으로 변경
     */
    if(parent == null){
      node.setColor(node.isBLACK());
      return;
    }

    // 2~4번째 경우 부모노드가 모두 빨간색이다. 이 밑으로 부모노드가 전부 빨간색인게 증명된다.
    if(parent.isColor() == parent.isBLACK()){
      return;
    }

    /*
      2 - 부모노드와 삼촌노드가 빨간색인 경우
      부모노드와 삼촌노드가 빨간색인지 검사한다.
      부모노드가 검정색인 경우는 위에서 처리되었으므로 빨간색인것이 증명되었다.
     */
    BinaryTree uncle = this.getUncle(parent);
    BinaryTree grandParent = parent.getParentTree();

    // 삼촌노드가 존재하고, 빨간색이라면 부모노드와 삼촌노드를 검정색으로 변경한다.
    // 할아버지 노드는 빨간색으로 변경한다. 할아버지 노드는 검은색을 유지해야 함으로 rebalanceAfterInsertion 할아버지를 대상으로 함수 재호출.
    if(uncle != null && uncle.isColor() == uncle.isRED()){
      parent.setColor(parent.isBLACK());
      uncle.setColor(uncle.isBLACK());
      grandParent.setColor(grandParent.isRED());
      this.rebalanceAfterInsertion(grandParent);

    /*
      3 - 부모노드는 빨간색이고 삼촌노드는 검은색, 새로운 노드는 안쪽 손자인 경우
      부모노드와 삼촌노드가 빨간색인지 검사한다.
      부모노드가 검정색인 경우는 위에서 처리되었으므로 빨간색인것이 증명되었다.
     */
    }else if(this.isBlack(uncle) == true){
      // 왼쪽 노드의 안쪽 자식인지, 오른쪽 노드의 안쪽 자식인지 확인한다.

      // 오른쪽 안쪽 손자인 경우 할아버지 노드의 오른쪽 자식 노드가 부모노드이고, 부모노드의 왼쪽 자식노드가 새로운 노드인 경우
      if(grandParent.getRightSubTree() == parent && parent.getLeftSubTree() == node){
        // 부모노드를 오른쪽으로 회전시킨다.
        this.rotateRight(parent);
        // 할아버지 노드를 왼쪽으로 회전시킨다.
        this.rotateLeft(grandParent);
        // node를 검정색으로 변경하고, node의 자식노드가 된 할아버지와 부모를 빨간색으로 유지해야 한다.
        node.setColor(node.isBLACK());
        // 부모는 이미 빨간색이므로 할아버지만 빨간색으로 변경한다.
        grandParent.setColor(grandParent.isRED());

        // 왼쪽 안쪽 손자인 경우 할아버지 노드의 왼쪽 자식 노드가 부모노드이고, 부모노드의 오른쪽 자식 노드가 새로운 노드인 경우
      }else if(grandParent.getLeftSubTree() == parent && parent.getRightSubTree() == node){
        // 부모노드를 삽입된 노드의 반대방향인 왼쪽으로 회전시킨다.
        this.rotateLeft(parent);
        // 할아버지 노드를 오른쪽으로 회전시킨다.
        this.rotateRight(grandParent);
        // node를 검정색으로 변경하고, node의 자식노드가 된 할아버지와 부모를 빨간색으로 리컬러링한다.
        node.setColor(node.isBLACK());
        grandParent.setColor(grandParent.isRED());

        // 오른쪽 바깥 손자인 경우 할아버지 노드의 오른쪽 자식 노드가 부모노드이고, 부모노드의 오른쪽 자식 노드가 새로운 노드인 경우
      }else if(grandParent.getParentTree() == parent && parent.getRightSubTree() == node){
        // 할아버지 노드를 새로운 노드가 삽입된 반대방향으로 회전시킨다.
        this.rotateLeft(grandParent);
        // parent 노드를 검은색으로 바꾸고, 할아버지 노드의 색을 빨간색으로 변경한다.
        parent.setColor(parent.isBLACK());
        grandParent.setColor(grandParent.isRED());

        // 왼쪽 바깥 손자인 경우 할아버지 노드의 왼쪽 자식 노드가 부모노드이고, 부모노드의 왼쪽 자식 노드가 새로운 노드인 경우
      }else if(grandParent.getLeftSubTree() == parent && parent.getLeftSubTree() == node){
        // 할아버지 노드를 새로운 노드가 삽입된 반대방향을 회전시킨다.
        this.rotateRight(grandParent);
        // parent 노드를 검정색으로 바꾸고 할아버지 노드의 색을 빨간색으로 변경한다.
        parent.setColor(parent.isBLACK());
        grandParent.setColor(grandParent.isRED());
      }

    }



  }

  public BinaryTree getUncle(BinaryTree parent){
    BinaryTree grandParent = parent.getParentTree();
    if(grandParent.getLeftSubTree() == parent){
      return grandParent.getRightSubTree();
    }else if(grandParent.getRightSubTree() == parent){
      return grandParent.getLeftSubTree();
    }

    return null;
  }

  public boolean isBlack(BinaryTree node){
    return node == null || node.isColor() == node.isBLACK();
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


  public BinaryTree remove(int data){
    BinaryTree<Integer> currentNode = this.root;

    while(currentNode != null && currentNode.getData() != data){
      if(data < currentNode.getData()){
        currentNode = currentNode.getLeftSubTree();
      }else if(data > currentNode.getData()){
        currentNode = currentNode.getRightSubTree();
      }
    }

    // 제거할 데이터를 찾지못했다면 함수 종료
    if(currentNode == null){
      return null;
    }

    // 삭제 후 대체할 노드를 담을 변수 선언
    BinaryTree replaceNode = null;
    boolean deletedNodeColor = new BinaryTree<>().isRED();
    BinaryTree nodeColor = new BinaryTree<>();

    // 자식 노드가 1개 이하 일때의 상황
    if(currentNode.getLeftSubTree() == null || currentNode.getRightSubTree() == null){
      replaceNode = this.removeWithZeroOneChild(currentNode);
      deletedNodeColor = currentNode.isColor();
    }else{
      // 자식 노드가 2개인 경우
      // 왼쪽 자식중 가장 큰 값을 가져와 대체한다.
      BinaryTree<Integer> succesor = this.getBiggest(currentNode.getLeftSubTree());
      currentNode.setData(succesor.getData());
      replaceNode = this.removeWithZeroOneChild(succesor);
      deletedNodeColor = currentNode.isColor();
    }

    if(deletedNodeColor == nodeColor.isBLACK()){
      this.rebalanceAfterDeletion(replaceNode);

      if(replaceNode instanceof NilNode){
        this.replaceParentsChild(replaceNode.getParentTree(), replaceNode, null);
      }
    }

   return replaceNode;
  }

  private void rebalanceAfterDeletion(BinaryTree node){
    if(node == this.root){
      node.setColor(node.isBLACK());
      return;
    }

    BinaryTree sibling = this.getSibling(node);

    if(sibling.isColor() == node.isRED()){
      this.handleRedSibling(node, sibling);
      sibling = this.getSibling(node);
    }

    if(this.isBlack(sibling)){
      if(this.isBlack(sibling.getLeftSubTree()) && this.isBlack(sibling.getRightSubTree())){
        if(node.getParentTree().isColor() == node.isRED()){
          sibling.setColor(sibling.isRED());
          node.getParentTree().setColor(node.getParentTree().isBLACK());
        }else{
          sibling.setColor(sibling.isRED());
          this.rebalanceAfterDeletion(node.getParentTree());
        }
      }else{
        this.handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
      }
    }

  }

  private void handleBlackSiblingWithAtLeastOneRedChild(BinaryTree node, BinaryTree sibling){
    boolean nodeIsLeftChild = (node.getParentTree().getLeftSubTree() == node);

    if(nodeIsLeftChild == true && this.isBlack(sibling.getRightSubTree())){
      sibling.getLeftSubTree().setColor(sibling.isBLACK());
      sibling.setColor(sibling.isRED());
      this.rotateRight(sibling);
      sibling = node.getParentTree().getRightSubTree();
    }else if(nodeIsLeftChild == false && this.isBlack(sibling.getLeftSubTree())){
      sibling.getRightSubTree().setColor(sibling.isBLACK());
      sibling.setColor(sibling.isBLACK());
      this.rotateLeft(sibling);
      sibling = node.getParentTree().getLeftSubTree();
    }

    sibling.setColor(node.getParentTree().isColor());
    node.setColor(node.isBLACK());

    if(nodeIsLeftChild){
      sibling.getRightSubTree().setColor(sibling.isBLACK());
      this.rotateLeft(node.getParentTree());
    }else{
      sibling.getLeftSubTree().setColor(sibling.isBLACK());
      this.rotateRight(node.getParentTree());
    }
  }

  private void handleRedSibling(BinaryTree node, BinaryTree sibling){
    sibling.setColor(sibling.isRED());
    node.getParentTree().setColor(node.isRED());

    // 부모노드를 대체된 노드 방향으로 회전시킨다. 왼쪽 노드일 경우 왼쪽으로
    if(node.getParentTree().getLeftSubTree() == node){
      this.rotateLeft(node.getParentTree());
    }else{
      this.rotateRight(node.getParentTree());
    }
  }

  // 형제 노드 구하기(getSibling)
  private BinaryTree getSibling(BinaryTree node){
    BinaryTree parent = node.getParentTree();

    // 현재노드가 부모노드의 왼쪽 자식노드라면 형제는 반대인 오른쪽
    if(node == parent.getLeftSubTree()){
      return parent.getRightSubTree();
    }else if(node == parent.getRightSubTree()){
      return parent.getLeftSubTree();
    }
    return null;
  }

  private BinaryTree getBiggest(BinaryTree node) {
    while(node.getRightSubTree() != null){
      node = node.getRightSubTree();
    }
    return node;
  }

  private BinaryTree removeWithZeroOneChild(BinaryTree node) {
    // 자식 노드가 왼쪽에 있다면 할아버지 노드와 왼쪽 자식 노드를 연결해준다.
    if(node.getLeftSubTree() != null){
      this.replaceParentsChild(node.getParentTree(), node, node.getLeftSubTree());
      return node.getLeftSubTree();

      // 자식 노드가 오른쪽에 있다면 할아버지 노드와 오른쪽 자식 노드를 연결해준다.
    }else if(node.getRightSubTree() != null){
      this.replaceParentsChild(node.getParentTree(), node, node.getRightSubTree());
      return node.getRightSubTree();

      // 자식노드가 모두 null인 경우 NILL 노드를 임시로 사용한다.
    }else{
      BinaryTree newChild = (node.isColor() == node.isBLACK()) ? new NilNode() : null;
      this.replaceParentsChild(node.getParentTree(), node, newChild);
      return newChild;
    }

  }


  public BinaryTree getRoot() {
    return this.root;
  }

}
