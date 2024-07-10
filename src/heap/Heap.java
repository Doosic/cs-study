package heap;

public class Heap {

  private BinaryTree root;
  private BinaryTree lastInsertedNode;

  public Heap(){
    this.root = null;
    this.lastInsertedNode = null;
  }

  public void insert(int data){
    // 데이터를 처음 삽입하는 경우 마지막 노드에 인스턴스를 생성하고, 루트로 지정한다.
    if(this.lastInsertedNode == null){
      this.lastInsertedNode = new BinaryTree(data);
      this.root = this.lastInsertedNode;
      return;
    }

    // getInsertingParent 함수는 새로운 노드가 삽입될 위치의 부모노드를 구한다.
    // 마지막에 삽입된 노드의 위치를 기반으로 계산한다.
    BinaryTree insertingParent = this.getInsertingParent();
    BinaryTree<Integer> newNode = new BinaryTree(data);

    // 힙(완전 이진 트리)은 왼쪽에서 오른쪽으로 데이터를 먼저 채운다.
    if(insertingParent.getLeftSubTree() == null){
      insertingParent.setLeftSubTree(newNode);
    }else if(insertingParent.getRightSubTree() == null){
      insertingParent.setRightSubTree(newNode);
    }
    // 새로운 노드에게 부모노드를 연결함으로써 양방향 연결
    newNode.setParentTree(insertingParent);
    this.lastInsertedNode = newNode;

    // 새로 삽입된 데이터가 부모노드와 값을 비교하며 우선순위에 맞게 자신의 위치를 찾아간다.
    while(newNode.getParentTree()  != null){
      // 만약 부모노드의 값이 더 크다면 자리를 변경한다.(최소힙)
      if(this.isBigPriority(newNode.getData(), (int) newNode.getParentTree().getData()) == true){
        // 부모노드의 값을 임시 변수에 담아둔다.
        int tempData = (int) newNode.getParentTree().getData();
        // 부모노드의 데이터에 새로운 노드의 데이터를 담는다.
        newNode.getParentTree().setData(newNode.getData());
        // 새로운 노드의 데이터에 부모 노드의 값을 담아준다.
        newNode.setData(tempData);
        // 새로운 노드는 부모노드가 된다.
        newNode = newNode.getParentTree();
      }else{
        // 부모노드와 값을 비교하는 과정을 반복시 새로운 노드는 자기 자리를 찾게된다.
        // 부모노드가 더 작다면 반복문을 탈출한다(최소힙)
        break;
      }
    }
  }

  private boolean isBigPriority(int first, int second){
    return (first < second);
  }

  /*
    1.lastInsertedNode가 루트노드인 경우
      - 새로 삽입될 노드의 위치는 루트노드의 왼쪽 자식이다.
    2.lastInsertedNode가 부모노드의 왼쪽 자식노드인 경우
      - 부모노드의 오른쪽 자식노드 즉, 형제노드로 삽입된다.
    3.lastInsertedNode가 부모노드의 오른쪽 자식노드인 경우
      a.부모노드 중에서 오른쪽 형제 노드를 가지는 노드가 존재하는 경우
        - 오른쪽 형제 노드의 왼쪽 자식으로 타고 이동해 새로 삽입할 노드의 위치를 찾는다.
      b.부모노드 중에서 오른쪽 형제 노드를 가지는 노드가 존재하지 않는 경우
        - 모든 트리의 자리가 꽉 차서 더 이상 넣을 경우가 없을때는 다시 루트노드부터
          왼쪽 자식노드의 가장 하단으로 이동해 새로운 자식 노드를 삽입한다.
   */
  private BinaryTree getInsertingParent(){
    // 1.lastInsertedNode가 루트노드인 경우 루트노드를 반환한다.
    if(this.lastInsertedNode.getParentTree() == null){
      return this.lastInsertedNode;
      // 2.마지막으로 삽입된 노드가 부모노드의 왼쪽 자식노드인 경우
    }else{
      if(this.lastInsertedNode == this.lastInsertedNode.getParentTree().getLeftSubTree()){
        return this.lastInsertedNode.getParentTree();
      }else{
        BinaryTree current = this.lastInsertedNode;
        BinaryTree firstRightSibling = null;

        while(current.getParentTree().getParentTree() != null){
          current = current.getParentTree();

          firstRightSibling = this.getRightSibling(current);
          if(firstRightSibling != null){
            break;
          }
        }

        // 오른쪽 형제노드가 존재한다면
        if(firstRightSibling != null){
          while(firstRightSibling.getLeftSubTree() != null){
            firstRightSibling = firstRightSibling.getLeftSubTree();
          }
          return firstRightSibling;
          // 오른쪽 형제노드가 존재하지 않는 경우
        }else{
          current = this.root;
          while(current.getLeftSubTree() != null){
            current = current.getLeftSubTree();
          }
          return current;
        }
      }
    }
  }

  // 오른쪽 형제 노드를 구하는 함수
  private BinaryTree getRightSibling(BinaryTree node){
    // 현재 노드가 부모노드의 오른쪽 자식 노드가 아니라면 왼쪽 자식 노드라는 것
    if(node.getParentTree().getRightSubTree() != null){
      return node.getParentTree().getRightSubTree();
    }
    return null;
  }

  private BinaryTree getLeftSibling(BinaryTree node){
    // 현재 노드가 부모노드의 왼쪽 자식 노드가 아니라면 오른쪽 자식 노드라는 것
    if(node.getParentTree().getLeftSubTree() != null){
      return node.getParentTree().getLeftSubTree();
    }
    return null;
  }

  // 우선순위가 가장 높은 것부터 제거된다.
  public BinaryTree remove(){
    BinaryTree deletedNode = null;

    // 루트노드가 제거될때
    if(this.lastInsertedNode == this.root){
      deletedNode = this.root;
      this.root = null;
      this.lastInsertedNode = null;
      return deletedNode;
    }

    // getNewLastInsertedNode 마지막에 삽입된 노드의 바로 이전 노드를 구한다.
    BinaryTree prevLastInsertedNode = this.getNewLastInsertedNode();
    // 마지막에 삽입된 노드의 값을 루트노드와 바꿔준다.
    // tempData에 루트노드의 값을 담고, root에는 마지막 데이터의 값을 담아주고 last에는 루트의 값을 담아준다.
    int tempData = (int) this.root.getData();
    this.root.setData(this.lastInsertedNode.getData());
    this.lastInsertedNode.setData(tempData);

    // 마지막으로 삽입된 노드의 부모에게 연결되어있는 자식의 참조를 해제한다.
    if(this.lastInsertedNode.getParentTree().getLeftSubTree() == this.lastInsertedNode){
      this.lastInsertedNode.getParentTree().setLeftSubTree(null);
    }else{
      this.lastInsertedNode.getParentTree().setRightSubTree(null);
    }
    // 자식은 부모의 참조를 해제한다.
    this.lastInsertedNode.setParentTree(null);
    deletedNode = this.lastInsertedNode;
    this.lastInsertedNode = prevLastInsertedNode;

    BinaryTree current = this.root;
    do{
      // getHigherPriorityChild 두 노드를 비교하여 더 우선순위가 높은(값이작은)것을 반환해준다.(최소힙)
      BinaryTree higherChild = this.getHigherPriorityChild(current.getLeftSubTree(), current.getRightSubTree());
      if(higherChild == null){
        break;
      }else if(this.isBigPriority((Integer) current.getData(), (Integer) higherChild.getData()) == false){
        // 우선순위가 더 높은 자식이 존재 할 경우 current 노드와 위치를 변경한다.
        tempData = (int) current.getData();
        current.setData(higherChild);
        higherChild.setData(tempData);
        current = higherChild;
      }else{
        break;
      }
    }while(current.getLeftSubTree() != null || current.getRightSubTree() != null);

    return deletedNode;
  }

  private BinaryTree getHigherPriorityChild(BinaryTree left, BinaryTree right){
    if(left == null){
      return right;
    }else if(right == null){
      return left;
    }else if(this.isBigPriority((Integer) left.getData(), (Integer) right.getData())){
      return left;
    }else{
      return right;
    }
  }

  private BinaryTree getNewLastInsertedNode(){
    BinaryTree prevLastInsertedNode = null;

    if(this.lastInsertedNode == this.lastInsertedNode.getParentTree().getLeftSubTree()){
      BinaryTree current = this.lastInsertedNode;
      BinaryTree firstLeftSibling = null;
      while(current.getParentTree().getParentTree() != null){
        current = current.getParentTree();
        firstLeftSibling = this.getLeftSibling(current);
        if(firstLeftSibling != null){
          break;
        }
      }

      // 부모노드중에 왼쪽 형제노드가 존재하는 경우 오른쪽으로 내려간다.
      if(firstLeftSibling != null){
        while (firstLeftSibling.getRightSubTree() != null){
          firstLeftSibling = firstLeftSibling.getRightSubTree();
        }
        prevLastInsertedNode = firstLeftSibling;
      }else{
        // 부모노드중에 왼쪽 형제노드가 존재하지 않는 경우
        current = this.root;
        while(current.getRightSubTree() != null){
          current = current.getRightSubTree();
        }
        prevLastInsertedNode = current;
      }

      // 마지막에 삽입된 노드가 부모노드의 오른쪽 자식노드인 경우
    }else{
      prevLastInsertedNode = this.lastInsertedNode.getParentTree().getLeftSubTree();
    }

    return prevLastInsertedNode;
  }

  public BinaryTree getRoot() {
    return root;
  }
}
