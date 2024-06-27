package tree.redBlack;

public interface RedBlack {

  /*
    - 추상 자료형

    * 공통
    rotateLeft(node) - 왼쪽 회전
    rotateRight(node) - 오른쪽 회전
    replaceParentsChild(parent, oldChild, newChild) - parent가 자식으로 가리키고 있던 oldChild를 newChild로 바꾼다.
                                                      회전 이후 자식노드를 바꿔줄때 사용

    * 삽입
    insert(data) - 데이터를 Red-Black 트리에 삽입
    rebalanceAfterInsertion(node) - 데이터 삽입 후 네 가지 경우를 나눠서 Recoloring과 회전
    getUncle(parent) - 삼촌노드를 구하는 함수

    * 제거
    remove(data) - 데이터 제거, 제거는 코드가 길어지므로 상황마다 나눠서 처리
    removeWithZeroOrOneChild(node) - remove함수 내에서 호출. 제거할 노드가 1개 이하(0~1)의 자식 노드를 가지고 있을 때 호출하는 함수
    getBiggestNode(node) - AVL트리에서 데이터를 삭제할 때 대체할 노드를 왼쪽 노드의 가장 오른쪽 노드, 즉 가장 큰 값의 노드를 가져와서 교체했었다.
                       위의 기능을 해준다.
    rebalanceAfterDeletion(node) - 삭제하고 난 뒤 Red-Balck 트리의 규칙에 맞게 균형을 맞춰주는 함수
    getSibling(node) - 균형을 맞추는 동안 형제노드의 색을 비교하는 경우가 있기에 형제노드를 구하는 함수
    handleRedSibling(node, sibling) - 형제노드가 빨간색일 때 균형을 맞춰준다
    isBlack(node) - 형제노드가 검정색일 때 균형을 맞춰준다
    handleBlackSiblingWithAtLeastOneRedChild(node, sibling) - 검은색 형제노드가 하나 이상의 빨간색 자식노드를 가지고 있는 경우 균형을 맞춰주는 함수
   */
}
