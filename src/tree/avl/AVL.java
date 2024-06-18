package tree.avl;

public interface AVL<T> {

  /*
   * - AVL 트리 추상자료형
   * getHeight(node) : 노드의 높이를 구한다
   * updateHeight(node) : 노드의 높이를 새롭게 구한다
   * getBalanceFactor(node) : 노드의 균형 체크
   * rotateLeft(node) : LL 회전
   * rotateRight(node) : RR 회전
   * rotation(targetNode, data) : LL, RR , LR, RL 회전을 시키는 함수
   * getUnBalanceNode(targetRootNode) : 균형이 무너진 트리에서 균형을 무너트린 원인이 된 노드를 찾는 함수
   * removeHelper(deletingNode, data, parentNode) : 이진 탐색 트리에서 remove 함수 와 같은 역할을 한다.
   */
}
