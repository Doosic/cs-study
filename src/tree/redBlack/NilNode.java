package tree.redBlack;

public class NilNode extends BinaryTree{
  public NilNode() {
    super(0);
    this.setColor(this.isBLACK());
  }
}
