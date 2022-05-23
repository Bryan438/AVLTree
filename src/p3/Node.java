package p3;

public class Node {
    private Node parent;
    private Node leftChild;
    private Node rightChild;
    private int val;
    private int leftHeight;
    private int rightHeight;
    private int balance;
    public Node(int v)
    {
        this.val = v;
    }
    public void setParent(Node n)
    {
        this.parent = n;
    }
    public Node getParent()
    {
        return this.parent;
    }
    public void setLeftChild(Node n)
    {
        this.leftChild = n;
    }
    public void setRightChild(Node n)
    {
        this.rightChild = n;
    }
    public int getVal()
    {
        return val;
    }
    public int getLeftHeight()
    {
        return leftHeight;
    }
    public int getRightHeight()
    {
        return rightHeight;
    }
    public void setLeftHeight(int l)
    {
        leftHeight = l;
    }
    public void setRightHeight(int l)
    {
        rightHeight = l;
    }
    public int getBalance()
    {
        return balance;
    }
    public void setBalance(int b)
    {
        balance = b;
    }
    public void setVal(int v)
    {
        this.val = v;
    }
    public Node getLeftChild()
    {
        return this.leftChild;
    }
    public Node getRightChild()
    {
        return this.rightChild;
    }
}
