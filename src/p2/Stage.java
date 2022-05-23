package p2;
import p3.Node;

public class Stage {
    private Node node;
    private Boolean leftChecked = false;
    private Boolean rightChecked = false;
    public Stage(Node n)
    {
        this.node = n;
    }
    public void setLeftChecked()
    {
        leftChecked = true;
    }
    public void setrightChecked()
    {
        rightChecked = true;
    }
    public boolean getLeftChecked()
    {
        return leftChecked;
    }
    public boolean getRightChecked()
    {
        return rightChecked;
    }
    public Node getNode()
    {
        return node;
    }
}
