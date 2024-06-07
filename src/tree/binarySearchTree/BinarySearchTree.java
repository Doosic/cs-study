package tree.binarySearchTree;

import tree.BinaryTree;

public class BinarySearchTree<T> {

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

    }

    return deletingNode;
  }

  public BinaryTree getRoot() {
    return this.root;
  }

}
