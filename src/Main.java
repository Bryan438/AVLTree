import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) {



        Tree tree = new Tree();
        int val = 0;

        Scanner myObj = new Scanner(System.in);

        while(true)
        {
            System.out.println("Construct tree, -1 to quit, or -2 to delete mode");
            val = myObj.nextInt();
            if(val == -1)
            {
                break;
            }
            else if(val == -2)
            {
                System.out.println("Delete tree, -1 to quit");
                int dValue = myObj.nextInt();
                tree.deleteNode(dValue);
            }
            else {
                Node n = new Node(val);
                if(tree.getNode() == null)
                {
                    tree.setNode(n);
                }
                else {
                    tree.insertNode(n);
                }
            }

            tree.printTree(tree.getNode());
        }

        /*while (true){
            System.out.println("Delete tree, -1 to quit");
            int value = myObj.nextInt();
            if(value == -1)
            {
                break;
            }
            Node n = new Node(value);
            val = n.getVal();
            n.dNode(val, tree.curNode, tree);
            tree.resetHei();
            tree.resetBal();
            n.checkBalance(tree.curNode, tree);
            printTree(tree.getNode());
        }*/


       /* Node first = new Node(3);
        Node second = new Node(2);
        Node third = new Node(1);
        Node fourth = new Node(4);


        tree.placeNode(first);
        tree.resetHei();
        tree.resetBal();
        first.checkBalance(tree.curNode, tree);

        tree.placeNode(second);
        tree.resetHei();
        tree.resetBal();
        second.checkBalance(tree.curNode, tree);

        tree.placeNode(third);
        tree.resetHei();
        tree.resetBal();
        third.checkBalance(tree.curNode, tree);

        tree.placeNode(fourth);
        tree.resetHei();
        tree.resetBal();
        fourth.checkBalance(tree.curNode, tree);*/

        /*tree.deleteNode(zero);
        tree.resetHei();
        tree.resetBal();
        zero.checkBalance(tree.curNode, tree);*/

        /*tree.deleteNode(second);
        tree.resetHei();
        tree.resetBal();
        second.checkBalance(tree.curNode, tree);*/

    }




}
class Tree
{
    private Node curNode;

    public void setNode(Node n) {
        curNode = n;
    }
    public Node getNode()
    {
        return curNode;
    }

    public void insertNode(Node n){
        boolean check = false;
        Node no = curNode;
        while(true) {
            if (n.getVal() <= no.getVal()) {
                if (no.getLeftNode() == null) {
                    no.setLeftNode(n);
                    n.setParent(no);
                    break;
                } else {
                    no = no.getLeftNode();
                }
            } else if (n.getVal() > no.getVal()) {
                if (no.getRightNode() == null) {
                    no.setRightNode(n);
                    n.setParent(no);
                    break;
                } else {
                    no = no.getRightNode();
                }
            } else {
                curNode = n;
                break;
            }
        }

        reBalance();
    }

    protected void remove(Node n) {

        if(n.getLeftNode() == null && n.getRightNode() == null)
        {
            if(n.getParent() == null)
            {
                curNode = null;
            }
            else if(n.getParent().getLeftNode() == n)
            {
                n.getParent().setLeftNode(null);
            }
            else{
                n.getParent().setRightNode(null);
            }
        }
        else if(((n.getLeftNode() != null || n.getRightNode() != null) == true) && (n.getRightNode() != null && n.getLeftNode() != null) == false)
        {
            Node x = n.getParent();
            if(n == curNode) {
                if(n.getLeftNode() != null) {
                    curNode = n.getLeftNode();
                }
                else{
                    curNode = n.getRightNode();
                }
            }
            else {
                if (n.getParent().getLeftNode() == n) {
                    if (n.getLeftNode() != null) {
                        x.setLeftNode(n.getLeftNode());
                        n.getLeftNode().setParent(n.getParent());
                    } else {
                        x.setLeftNode(n.getRightNode());
                        n.getRightNode().setParent(n.getParent());
                    }
                } else {
                    if (n.getLeftNode() != null) {
                        x.setRightNode(n.getLeftNode());
                        n.getLeftNode().setParent(n.getParent());
                    } else {
                        x.setRightNode(n.getRightNode());
                        n.getRightNode().setParent(n.getParent());
                    }
                }
            }
        }
        else{
            Node min = findMin(n.getRightNode());
            int val = min.getVal();
            n.setVal(val);
            if(min.getLeftNode() == null && min.getRightNode() == null) {
                if (min.getParent().getLeftNode() == min) {
                    min.getParent().setLeftNode(null);
                } else {
                    min.getParent().setRightNode(null);
                }
            }
            else{
                n.setRightNode(min.getRightNode());
                min.getRightNode().setParent(n);
            }
        }
        /*Node x = n.getParent();
        if (n.getParent().getLeftNode() == n){
            if(n.getLeftNode() != null && n.getRightNode() != null) {
                n.getRightNode().setParent(x);

                Node y = n.getRightNode();
                x.setLeftNode(y);
                n.getLeftNode().setParent(y);
                y.setLeftNode(n.getLeftNode());
            }
            else if(n.getLeftNode() != null)
            {
                n.getLeftNode().setParent(x);
                x.setLeftNode(n.getLeftNode());

            }
            else if(n.getRightNode() != null)
            {
                n.getRightNode().setParent(x);
                x.setLeftNode(n.getRightNode());
            }
            else{
                n.getParent().setLeftNode(null);
            }
        }
        else if(n.getParent().getRightNode() == n)
        {
            if(n.getLeftNode() != null && n.getRightNode() != null) {
                n.getLeftNode().setParent(x);
                Node y = n.getLeftNode();
                x.setRightNode(y);
                n.getRightNode().setParent(y);
                y.setRightNode(n.getRightNode());
            }
            else if(n.getLeftNode() != null)
            {
                n.getLeftNode().setParent(x);
                x.setRightNode(n.getLeftNode());

            }
            else if(n.getRightNode() != null)
            {
                n.getRightNode().setParent(x);
                x.setRightNode(n.getRightNode());
            }
            else {
                n.getParent().setRightNode(null);
            }
        }*/
    }

    public void deleteNode(int val) {
        /*if(n == null)
        {
            return null;
        }
        dNode(val, n.getLeftNode(), t);
        dNode(val, n.getRightNode(), t);
        if(n.getVal() == val)
        {
            t.deleteNode(n);
            t.resetHei();
            t.resetBal();
            n.checkBalance(t.curNode, t);
        }
        return null;*/
        Stage first = new Stage(curNode);
        Stack<Stage> stk = new Stack<Stage>();
        stk.push(first);

        while(stk.empty() == false)
        {
            Stage scene = stk.peek();
            Node node = scene.getNode();

            if(node.getLeftNode() != null && scene.getLeftChecked() == false) {
                Stage s = new Stage (stk.peek().getNode().getLeftNode());
                stk.push(s);
                continue;
            }
            stk.peek().setLeftChecked();
            if(scene.getRightChecked() == false)
            {
                if(stk.peek().getNode().getRightNode() == null)
                {
                    stk.peek().setrightChecked();
                }
                else {
                    Stage s = new Stage(node.getRightNode());
                    stk.push(s);
                    continue;
                }
            }
            while(stk.peek().getLeftChecked() == true && stk.peek().getRightChecked() == true)
            {
                if(stk.peek().getNode().getVal() == val) {
                    remove(stk.peek().getNode());
                    if (curNode == null)
                    {
                        stk.pop();
                        break;
                    }
                    resetHei();
                    resetBal();
                    checkBalance();
                }
                stk.pop();
                if(stk.empty() == true)
                {
                    break;
                }
                else if(stk.peek().getLeftChecked() == false)
                {
                    stk.peek().setLeftChecked();
                }
                else if(stk.peek().getRightChecked() == false)
                {
                    stk.peek().setrightChecked();
                }

            }
        }

        reBalance();
    }

    void reBalance()
    {
        resetHei();
        resetBal();
        checkBalance();
    }

    public Node findMin(Node n)
    {
        Node x = n;
        while(x.getLeftNode() != null) {
            x = x.getLeftNode();
        }
        return x;
    }
    public void resetHei()
    {
        setHeight();
    }
    public void resetBal() {
        getBalance();
    }

    public int setHeight()
    {
        /*if(n == null)
        {
            return -1;
        }
        n.leftHeight = setHeight(n.getLeftNode());
        n.rightHeight = setHeight(n.getRightNode());
        n.height =  Math.max(n.leftHeight, n.rightHeight) + 1;
        return n.height;*/
        Stage first = new Stage(curNode);
        Stack<Stage> stk = new Stack<Stage>();
        stk.push(first);
        while(stk.empty() == false)
        {
            Stage scene = stk.peek();
            Node node = scene.getNode();

            if(node.getLeftNode() != null && scene.getLeftChecked() == false) {
                Stage s = new Stage (stk.peek().getNode().getLeftNode());
                stk.push(s);
                continue;
            }
            if(stk.peek().getNode().getLeftNode() == null)
            {
                scene.setLeftChecked();
                node.setLeftHeight(-1);
            }
            if(stk.peek().getRightChecked() == false)
            {
                if(stk.peek().getNode().getRightNode() == null)
                {
                    scene.setrightChecked();
                    node.setRightHeight(-1);

                }
                else {
                    Stage s = new Stage(node.getRightNode());
                    stk.push(s);
                    continue;
                }
            }
            while(scene.getLeftChecked() == true && scene.getRightChecked() == true) {
                if (node.getLeftNode() != null) {
                    node.setLeftHeight(Math.max(node.getLeftNode().getLeftHeight(), node.getLeftNode().getRightHeight()) + 1);
                }
                if (stk.peek().getNode().getRightNode() != null)
                {
                    node.setRightHeight(Math.max(node.getRightNode().getLeftHeight(), node.getRightNode().getRightHeight()) + 1);
                }


                stk.pop();


                if(stk.empty() == true)
                {
                    break;
                }

                scene = stk.peek();
                node = scene.getNode();

                if(stk.peek().getLeftChecked() == false)
                {
                    scene.setLeftChecked();
                }
                else if(stk.peek().getRightChecked() == false)
                {
                   scene.setrightChecked();
                }

            }
        }
        return 0;
    }

    public void getBalance()
    {
        /*if (n != null) {
            n.balance = n.leftHeight - n.rightHeight;
            getBalance(n.getLeftNode());
            getBalance(n.getRightNode());
        }*/
        Stage first = new Stage(curNode);
        Stack<Stage> stk = new Stack<Stage>();
        stk.push(first);
        while(stk.empty() == false)
        {
            Stage scene = stk.peek();
            Node node = scene.getNode();
            if(node.getLeftNode() != null && scene.getLeftChecked() == false) {
                Stage s = new Stage (node.getLeftNode());
                stk.push(s);
                continue;
            }
            stk.peek().setLeftChecked();
            if(stk.peek().getRightChecked() == false)
            {
                if(stk.peek().getNode().getRightNode() == null)
                {
                    stk.peek().setrightChecked();
                }
                else {
                    Stage s = new Stage(stk.peek().getNode().getRightNode());
                    stk.push(s);
                    continue;
                }
            }
            while(stk.peek().getLeftChecked() == true && stk.peek().getRightChecked() == true)
            {
                node.setBalance(node.getLeftHeight() - node.getRightHeight());
                stk.pop();
                if(stk.empty() == true)
                {
                    break;
                }
                scene = stk.peek();
                node = scene.getNode();

                if(stk.peek().getLeftChecked() == false)
                {
                    stk.peek().setLeftChecked();
                }
                else if(stk.peek().getRightChecked() == false)
                {
                    stk.peek().setrightChecked();
                }

            }
        }
    }

    public void leftRotation(Node n)
    {
        Node parent = n.getParent();
        Node rightNode = n.getRightNode();
        Node leftNode = rightNode.getLeftNode();

        if(parent == null) {

            rightNode.setLeftNode(n);
            rightNode.setParent(null);

            n.setParent(rightNode);
            if(leftNode != null) {
                n.setRightNode(leftNode);
            }
            else {
                n.setRightNode(null);
            }


            setNode(rightNode);
        }
        else {
            rightNode.setLeftNode(n);
            rightNode.setParent(parent);
            n.setParent(rightNode);
            if(rightNode.getParent().getVal() > rightNode.getVal())
            {
                rightNode.getParent().setLeftNode(rightNode);
            }
            else{
                rightNode.getParent().setRightNode(rightNode);
            }
            if(leftNode != null)
            {
                n.setRightNode(leftNode);
            }
            else{
                n.setRightNode(null);
            }

        }
    }
    public void rightRotation(Node n)
    {
        Node parent = n.getParent();
        Node leftNode = n.getLeftNode();
        Node leftLeftNode = leftNode.getLeftNode();

        if(parent == null) {
            leftNode.setRightNode(n);
            n.setParent(leftNode);
            n.setLeftNode(null);
            leftNode.setParent(null);
            leftNode.setLeftNode(leftLeftNode);
            leftLeftNode.setParent(leftNode);
            setNode(leftNode);

        }
        else {
            leftNode.setRightNode(n);
            leftNode.setParent(parent);
            n.setParent(leftNode);
            if(leftNode.getParent().getVal() < leftNode.getVal())
            {
                leftNode.getParent().setRightNode(leftNode);
            }
            else{
                leftNode.getParent().setLeftNode(leftNode);
            }
            n.setLeftNode(null);

        }
    }

    public void leftrightRotation(Node n)
    {
        Node leftNode = n.getLeftNode();
        Node leftrightNode = leftNode.getRightNode();
        n.setLeftNode(leftrightNode);
        leftrightNode.setLeftNode(leftNode);
        leftrightNode.setParent(n);
        leftNode.setParent(leftrightNode);
        leftNode.setRightNode(null);
        rightRotation(n);
    }
    public void rightleftRotation(Node n)
    {
        Node rightNode = n.getRightNode();
        Node rightleftNode = rightNode.getLeftNode();
        n.setRightNode(rightleftNode);
        rightleftNode.setRightNode(rightNode);
        rightleftNode.setParent(n);
        rightNode.setParent(rightleftNode);
        rightNode.setLeftNode(null);
        leftRotation(n);
    }

    public Node checkBalance()
    {
        /*if(n == null)
        {
            return null;
        }
        checkBalance(n.getLeftNode(), t);
        checkBalance(n.getRightNode(), t);

        if(n.balance > 1 || n.balance < -1)
        {
            int checked = n.balance;
            if (checked < -1 && (n.getRightNode().getRightNode() != null)) {
                t.leftRotation(n, t);
            } else if (checked < -1 && (n.getRightNode().getLeftNode() != null)) {
                t.rightleftRotation(n, t);
            } else if (checked > 1 && (n.getLeftNode().getLeftNode() != null)) {
                t.rightRotation(n, t);
            } else {
                t.leftrightRotation(n, t);
            }
        }
        return null;*/

        Stage first = new Stage(curNode);
        Stack<Stage> stk = new Stack<Stage>();
        stk.push(first);
        while(stk.empty() == false)
        {
            Stage scene = stk.peek();
            Node node = scene.getNode();

            if(node.getLeftNode() != null && scene.getLeftChecked() == false) {
                Stage s = new Stage (node.getLeftNode());
                stk.push(s);
                continue;

            }
            scene.setLeftChecked();
            if(scene.getRightChecked() == false)
            {
                if(node.getRightNode() == null)
                {
                    scene.setrightChecked();
                }
                else {
                    Stage s = new Stage(node.getRightNode());
                    stk.push(s);
                    continue;
                }
            }
            while(scene.getLeftChecked() == true && scene.getRightChecked() == true)
            {
                if(node.getBalance() > 1 || node.getBalance() < -1)
                {
                    int checked = node.getBalance();
                    if (checked < -1 && (node.getRightNode().getRightNode() != null)) {
                        leftRotation(node);
                    } else if (checked < -1 && (node.getRightNode().getLeftNode() != null)) {
                        rightleftRotation(node);
                    } else if (checked > 1 && (node.getLeftNode().getLeftNode() != null)) {
                        rightRotation(node);
                    } else {
                        leftrightRotation(node);
                    }
                }
                resetHei();
                resetBal();
                stk.pop();
                if(stk.empty() == true)
                {
                    break;
                }

                scene  = stk.peek();
                node = scene.getNode();

                if(scene.getLeftChecked() == false)
                {
                    scene.setLeftChecked();
                }
                else if(scene.getRightChecked() == false)
                {
                    scene.setrightChecked();
                }

            }
        }
        return null;
    }

    public static void printTree(Node n)
    {

        /*if (n != null) {
            System.out.print(n.getVal() + " ");
            printTree(n.getLeftNode());
            printTree(n.getRightNode());
        }*/
        Stage first = new Stage(n);
        Stack<Stage> stk = new Stack<Stage>();
        stk.push(first);
        while(stk.empty() == false)
        {
            Stage scene = stk.peek();
            Node node = scene.getNode();
            if(node.getLeftNode() != null && scene.getLeftChecked() == false) {
                Stage s = new Stage (node.getLeftNode());
                stk.push(s);
                continue;
                /*scene = stk.peek();
                node = scene.getNode();*/
            }
            stk.peek().setLeftChecked();
            if(stk.peek().getRightChecked() == false)
            {
                if(stk.peek().getNode().getRightNode() == null)
                {
                    stk.peek().setrightChecked();
                }
                else {
                    Stage s = new Stage(stk.peek().getNode().getRightNode());
                    stk.push(s);
                    continue;
                }
            }
            while(stk.peek().getLeftChecked() == true && stk.peek().getRightChecked() == true)
            {
                System.out.print(node.getVal() + " ");

                stk.pop();
                if(stk.empty() == true)
                {
                    break;
                }
                scene = stk.peek();
                node = scene.getNode();

                if(stk.peek().getLeftChecked() == false)
                {
                    stk.peek().setLeftChecked();
                }
                else if(stk.peek().getRightChecked() == false)
                {
                    stk.peek().setrightChecked();
                }

            }
        }
    }

}

class Stage
{
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

class Node
{
    private Node parent;
    private Node leftNode;
    private Node rightNode;
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
    public void setLeftNode(Node n)
    {
        this.leftNode = n;
    }
    public void setRightNode(Node n)
    {
        this.rightNode = n;
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
    public Node getLeftNode()
    {
        return this.leftNode;
    }
    public Node getRightNode()
    {
        return this.rightNode;
    }




}
