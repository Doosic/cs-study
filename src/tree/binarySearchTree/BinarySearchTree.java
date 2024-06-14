package tree.binarySearchTree;

import tree.BinaryTree;

public class BinarySearchTree<T> {

  /*
  * - 이진 트리
  * 이진트리는 컴파일러가 사용하는 구문 트리를 만들거나
  * JPEG, MPEG 파일을 만드는 허프만 트리를 만들때 유용하게 쓰인다.
  *
  * - 이진 탐색 트리
  *
  * 이진 탐색 트리는 이진 트리에 규칙을 더 추가해서 만든 자료구조이며,
  *
  * 규칙1.중복된 노드가 없어야 한다.
  * 규칙2.특정 노드의 왼쪽 자식노드는 해당 노드보다 작은 값으로만 이루어져야 한다.
  * 규칙3.특정 노드의 오른쪽 자식노드는 해당 노드보다 큰 값으로만 이루어져야 한다.
  * 규칙4.모든 자식 트리에도 위에 규칙이 적용되어야 한다.
  *
  * - 시간 복잡도
  * 트리가 잘 만들어 지는것에 따라 이진 탐색 트리의 삽입, 제거, 검색의 성능은 O(n)이 될 수도, O(logN)이 될수도 있다.
  * (잘못만들어지는 트리는 한방향으로만 데이터가 치우치는 경우를 말한다. 연결리스트와 다를바 없는 상황이다.)
  *
  *
  * */

  private BinaryTree<Integer> root;

  public BinarySearchTree(){};

  public BinarySearchTree(BinaryTree rootNode) {
    this.root = rootNode;
  }

  public void insert(int data){
    // 이진 탐색트리에 노드가 하나도 없을 경우
    if(this.root == null){
      this.root = new BinaryTree(data);
      return;
    }

    /*
      다른 노드가 존재하는 경우
      어느 위치에 데이터를 삽입해야 할지 알아야 한다.
     */
    BinaryTree<Integer> currentNode = this.root;
    BinaryTree<Integer> parentNode = null;

    // while문을 통한 데이터 삽입위치 찾기
    while(currentNode != null){
      // parentNode에 자식 노드로 등록하기 위해 parentNode 저장
      parentNode = currentNode;

      // 입력받은 값이 currentNode보다 작은경우 leftSubTree에 삽입
      if(currentNode.getData() > data){
        currentNode = currentNode.getLeftSubTree();
      } else if (currentNode.getData() < data){
        // 입력받은 값이 currentNode보다 큰경우 rightSubTree에 삽입
        currentNode = currentNode.getRightSubTree();
      } else {
        // 같은 값일경우 중복은 없기에 return;
        return;
      }
    }

    BinaryTree<Integer> newNode = new BinaryTree(data);

    // 부모노드보다 작은경우 leftSubTree에 삽입
    if(parentNode.getData() > data){
      parentNode.setLeftSubTree(newNode);
    }else{
      // 부모노드보다 큰경우 rightSubTree에 삽입
      parentNode.setRightSubTree(newNode);
    }
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

  /*
    * 이진 탐색 트리의 제거
    - 첫 번째 상황: 터미널 노드 제거
    - 두 번째 상황: 자식노드가 한 개인 노드 제거
    - 세 번째 상황: 자식노드가 두 개인 노드 제거
   */
  public BinaryTree remove(int targetData){
    // fakeParentRootNode: 루트노드를 제거하기 위한 임시변수(RootNode의 부모로 사용된다)
    BinaryTree<Integer> fakeParentRootNode = new BinaryTree(0);
    // parentNode: 부모노드(root노드의 부모인 fakeParentRootNode를 사용)
    BinaryTree<Integer> parentNode = fakeParentRootNode;
    // currentNode: 현재 노드
    BinaryTree<Integer> currentNode = this.root;
    // deletingNode: 제거할 노드를 담아서 반환할 노드
    BinaryTree<Integer> deletingNode = null;

    // 루트노드의 임시부모노드에 right변수로 할당해준다. 임시노드는 가장 작은값으로 설정
    fakeParentRootNode.setRightSubTree(this.root);

    while(currentNode != null && currentNode.getData() != targetData){
      parentNode = currentNode;

      // 찾는 값이 현재노드보다 작다면 왼쪽으로 내려간다.
      if(currentNode.getData() > targetData){
        currentNode = currentNode.getLeftSubTree();
      } else {
        currentNode = currentNode.getRightSubTree();
      }
    }

    /*
      while문을 나왔을때는 2가지 경우만 남는다.
      - 제거할 노드를 찾은 경우
      - 제거할 노드를 찾지 못한 경우(null을 가리킨다)
     */

    // 제거할 노드를 찾지 못한 경우
    if(currentNode == null){
      return null;
    }

    // 제거할 노드를 찾은 경우
    deletingNode = currentNode;

    // 첫 번째 상황: 터미널 노드 제거
    // 터미널 노드인지 확인이 필요하다. 왼쪽, 오른쪽 자식이 있는지 확인한다. 이 경우 부모노드와의 연결만을 끊어주면 된다.
    if(deletingNode.getLeftSubTree() == null && deletingNode.getRightSubTree() == null){
      if(parentNode.getLeftSubTree() == deletingNode){
        parentNode.removeLeftSubTree();
      } else {
        parentNode.remeveRightSubTree();
      }
    } else if (deletingNode.getLeftSubTree() == null || deletingNode.getRightSubTree() == null){
      // - 두 번째 상황: 자식노드가 한 개인 노드 제거
      // 제거할 노드의 자식 노드를 제거할 노드의 부모 노드와 연결해준다.
      BinaryTree deletingNodeChild;

      // 삭제할 노드의 자식노드를 찾아서 deletingNodeChild에 담아준다. 제거할 노드의 부모노드에 새로운 자식 노드가 되기 위함이다.
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
      // - 세 번째 상황: 자식노드가 두 개인 노드 제거
      // 왼쪽, 오른쪽 자식 노드중 가장 큰 값을 가진 노드를 배치한다. 왼쪽이라 가정한다.
      // 선택된 큰 값의 자식노드로 제거될 노드의 자식노드 연결, 부모노드 연결, 오른쪽 노드 연결을 해줘야 한다.
      // 그러나 구현의 편의성을 위해 제거할 노드에 값으로 변경될 노드의 값을 대입해준다. 그렇다면 제거하고 새롭게 연결할 필요가 없다.

      // 대체할 노드의 왼쪽 자식노드와 부모노드를 변수에 저장해둔다.
      BinaryTree<Integer> replacingNode = deletingNode.getLeftSubTree();
      BinaryTree<Integer> replacingNodeParent = deletingNode;

      // 대체할 노드는 삭제할 왼쪽 노드의 가장 큰 값을 택해야 함으로 오른쪽으로 이동하며 찾는다
      while(replacingNode.getRightSubTree() != null){
        replacingNodeParent = replacingNode;
        replacingNode = replacingNode.getRightSubTree();
      }

      // 제거할 데이터를 return값으로 전해주기 위해 deletingNodeData에 담아준다.
      int deletingNodeData = deletingNode.getData();
      // 기존 제거할 Node의 데이터는 대체될 Node의 Data를 담아준다.
      deletingNode.setData(replacingNode.getData());

      /*
        대체할 노드의 부모노드(replacingNodeParent)에 오른쪽 Node로 오른쪽 자식노드(replacingNode)의 왼쪽 노드를 담아준다.
        오른쪽 자식 노드는 가장 큰값이라는 가정하에 deletingNode가 있던 자리로 이동했기 때문이다.

        왼쪽 자식 노드는 있을수도 있고 없을수도 있다. 있다면
       */
      if(replacingNodeParent.getLeftSubTree() == replacingNode){
        replacingNodeParent.setLeftSubTree(replacingNode.getLeftSubTree());
      }else{
        replacingNodeParent.setRightSubTree(replacingNode.getLeftSubTree());
      }

      deletingNode = replacingNode;
      deletingNode.setData(deletingNodeData);
    }

    // root 노드가 변경된 경우라면 fakeParentRootNode에 오른쪽 자식 노드로 설정해준다.
    // 0 -> 18 -> 15가 있을경우 15가 18의 자리를 대체했을때 15는 fakePrentRootNode의 rightSubTree에 담겨있다.
    // 이때 root를 15로 변경해준다. fakeParentRootNode의 RightSubTree는 Root 노드이기 때문이다.
    // 말로하기 작성하기 어려움... 직접 그려봐야 이해할 수 있다.
    if(fakeParentRootNode.getRightSubTree() != this.root){
      this.root = fakeParentRootNode.getRightSubTree();
    }

    return deletingNode;
  }

  public BinaryTree getRoot() {
    return this.root;
  }

}
