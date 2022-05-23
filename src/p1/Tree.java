package p1;
import p2.Stage;
import p3.Node;

import java.util.Stack;

public class Tree {
    private Node curNode;

    public void setNode(Node n) {
        curNode = n;
    }
    public Node getNode()
    {
        return curNode;
    }

    public void insertNode(Node n){
        Node no = curNode;
        while(true) {
            if (n.getVal() <= no.getVal()) {
                if (no.getLeftChild() == null) {
                    no.setLeftChild(n);
                    n.setParent(no);
                    break;
                } else {
                    no = no.getLeftChild();
                }
            } else if (n.getVal() > no.getVal()) {
                if (no.getRightChild() == null) {
                    no.setRightChild(n);
                    n.setParent(no);
                    break;
                } else {
                    no = no.getRightChild();
                }
            } else {
                curNode = n;
                break;
            }
        }

        reBalance();
    }

    protected void remove(Node n) {

        if(n.getLeftChild() == null && n.getRightChild() == null)
        {
            if(n.getParent() == null)
            {
                curNode = null;
            }
            else if(n.getParent().getLeftChild() == n)
            {
                n.getParent().setLeftChild(null);
            }
            else{
                n.getParent().setRightChild(null);
            }
        }
        else if(((n.getLeftChild() != null || n.getRightChild() != null) == true) && (n.getRightChild() != null && n.getLeftChild() != null) == false)
        {
            Node x = n.getParent();
            if(n == curNode) {
                if(n.getLeftChild() != null) {
                    curNode = n.getLeftChild();
                }
                else{
                    curNode = n.getRightChild();
                }
            }
            else {
                if (n.getParent().getLeftChild() == n) {
                    if (n.getLeftChild() != null) {
                        x.setLeftChild(n.getLeftChild());
                        n.getLeftChild().setParent(n.getParent());
                    } else {
                        x.setLeftChild(n.getRightChild());
                        n.getRightChild().setParent(n.getParent());
                    }
                } else {
                    if (n.getLeftChild() != null) {
                        x.setRightChild(n.getLeftChild());
                        n.getLeftChild().setParent(n.getParent());
                    } else {
                        x.setRightChild(n.getRightChild());
                        n.getRightChild().setParent(n.getParent());
                    }
                }
            }
        }
        else{
            Node min = findMin(n.getRightChild());
            int val = min.getVal();
            n.setVal(val);
            if(min.getLeftChild() == null && min.getRightChild() == null) {
                if (min.getParent().getLeftChild() == min) {
                    min.getParent().setLeftChild(null);
                } else {
                    min.getParent().setRightChild(null);
                }
            }
            else{
                n.setRightChild(min.getRightChild());
                min.getRightChild().setParent(n);
            }
        }
        /*Node x = n.getParent();
        if (n.getParent().getLeftChild() == n){
            if(n.getLeftChild() != null && n.getRightChild() != null) {
                n.getRightChild().setParent(x);

                Node y = n.getRightChild();
                x.setLeftChild(y);
                n.getLeftChild().setParent(y);
                y.setLeftChild(n.getLeftChild());
            }
            else if(n.getLeftChild() != null)
            {
                n.getLeftChild().setParent(x);
                x.setLeftChild(n.getLeftChild());

            }
            else if(n.getRightChild() != null)
            {
                n.getRightChild().setParent(x);
                x.setLeftChild(n.getRightChild());
            }
            else{
                n.getParent().setLeftChild(null);
            }
        }
        else if(n.getParent().getRightChild() == n)
        {
            if(n.getLeftChild() != null && n.getRightChild() != null) {
                n.getLeftChild().setParent(x);
                Node y = n.getLeftChild();
                x.setRightChild(y);
                n.getRightChild().setParent(y);
                y.setRightChild(n.getRightChild());
            }
            else if(n.getLeftChild() != null)
            {
                n.getLeftChild().setParent(x);
                x.setRightChild(n.getLeftChild());

            }
            else if(n.getRightChild() != null)
            {
                n.getRightChild().setParent(x);
                x.setRightChild(n.getRightChild());
            }
            else {
                n.getParent().setRightChild(null);
            }
        }*/
    }

    public void deleteNode(int val) {
        /*if(n == null)
        {
            return null;
        }
        dNode(val, n.getLeftChild(), t);
        dNode(val, n.getRightChild(), t);
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

            if(node.getLeftChild() != null && scene.getLeftChecked() == false) {
                Stage s = new Stage(stk.peek().getNode().getLeftChild());
                stk.push(s);
                continue;
            }
            stk.peek().setLeftChecked();
            if(scene.getRightChecked() == false)
            {
                if(stk.peek().getNode().getRightChild() == null)
                {
                    stk.peek().setrightChecked();
                }
                else {
                    Stage s = new Stage(node.getRightChild());
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
                    reBalance();
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
        while(x.getLeftChild() != null) {
            x = x.getLeftChild();
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
        n.leftHeight = setHeight(n.getLeftChild());
        n.rightHeight = setHeight(n.getRightChild());
        n.height =  Math.max(n.leftHeight, n.rightHeight) + 1;
        return n.height;*/
        Stage first = new Stage(curNode);
        Stack<Stage> stk = new Stack<Stage>();
        stk.push(first);
        while(stk.empty() == false)
        {
            Stage scene = stk.peek();
            Node node = scene.getNode();

            if(node.getLeftChild() != null && scene.getLeftChecked() == false) {
                Stage s = new Stage(stk.peek().getNode().getLeftChild());
                stk.push(s);
                continue;
            }
            if(stk.peek().getNode().getLeftChild() == null)
            {
                scene.setLeftChecked();
                node.setLeftHeight(-1);
            }
            if(stk.peek().getRightChecked() == false)
            {
                if(stk.peek().getNode().getRightChild() == null)
                {
                    scene.setrightChecked();
                    node.setRightHeight(-1);

                }
                else {
                    Stage s = new Stage(node.getRightChild());
                    stk.push(s);
                    continue;
                }
            }
            while(scene.getLeftChecked() == true && scene.getRightChecked() == true) {
                if (node.getLeftChild() != null) {
                    node.setLeftHeight(Math.max(node.getLeftChild().getLeftHeight(), node.getLeftChild().getRightHeight()) + 1);
                }
                if (stk.peek().getNode().getRightChild() != null)
                {
                    node.setRightHeight(Math.max(node.getRightChild().getLeftHeight(), node.getRightChild().getRightHeight()) + 1);
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
            getBalance(n.getLeftChild());
            getBalance(n.getRightChild());
        }*/
        Stage first = new Stage(curNode);
        Stack<Stage> stk = new Stack<Stage>();
        stk.push(first);
        while(stk.empty() == false)
        {
            Stage scene = stk.peek();
            Node node = scene.getNode();
            if(node.getLeftChild() != null && scene.getLeftChecked() == false) {
                Stage s = new Stage(node.getLeftChild());
                stk.push(s);
                continue;
            }
            stk.peek().setLeftChecked();
            if(stk.peek().getRightChecked() == false)
            {
                if(stk.peek().getNode().getRightChild() == null)
                {
                    stk.peek().setrightChecked();
                }
                else {
                    Stage s = new Stage(stk.peek().getNode().getRightChild());
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
        Node rightNode = n.getRightChild();
        Node leftNode = rightNode.getLeftChild();

        if(parent == null) {

            rightNode.setLeftChild(n);
            rightNode.setParent(null);

            n.setParent(rightNode);
            if(leftNode != null) {
                n.setRightChild(leftNode);
            }
            else {
                n.setRightChild(null);
            }


            setNode(rightNode);
        }
        else {
            rightNode.setLeftChild(n);
            rightNode.setParent(parent);
            n.setParent(rightNode);
            if(rightNode.getParent().getVal() > rightNode.getVal())
            {
                rightNode.getParent().setLeftChild(rightNode);
            }
            else{
                rightNode.getParent().setRightChild(rightNode);
            }
            if(leftNode != null)
            {
                n.setRightChild(leftNode);
            }
            else{
                n.setRightChild(null);
            }

        }
    }
    public void rightRotation(Node n)
    {
        /*Node parent = n.getParent();
        Node leftChild = n.getLeftChild();
        Node leftLeftNode = leftChild.getLeftChild();

        if(parent == null) {
            leftChild.setRightChild(n);
            n.setParent(leftChild);
            n.setLeftChild(null);
            leftChild.setParent(null);
            leftChild.setLeftChild(leftLeftNode);
            leftLeftNode.setParent(leftChild);
            setNode(leftChild);

        }
        else {
            leftChild.setRightChild(n);
            leftChild.setParent(parent);
            n.setParent(leftChild);
            if(leftChild.getParent().getVal() < leftChild.getVal())
            {
                leftChild.getParent().setRightChild(leftChild);
            }
            else{
                leftChild.getParent().setLeftChild(leftChild);
            }
            n.setLeftChild(null);

        }*/
        Node parent = n.getParent();
        Node leftChild = n.getLeftChild();
        Node rightChild = leftChild.getRightChild();

        if(parent == null) {

            leftChild.setRightChild(n);
            leftChild.setParent(null);

            n.setParent(leftChild);
            if(rightChild != null) {
                n.setLeftChild(rightChild);
            }
            else {
                n.setLeftChild(null);
            }


            setNode(leftChild);
        }
        else {
            leftChild.setRightChild(n);
            leftChild.setParent(parent);
            n.setParent(leftChild);
            if(leftChild.getParent().getVal() > leftChild.getVal())
            {
                leftChild.getParent().setRightChild(leftChild);
            }
            else{
                leftChild.getParent().setLeftChild(leftChild);
            }
            if(rightChild != null)
            {
                n.setLeftChild(rightChild);
            }
            else{
                n.setLeftChild(null);
            }

        }
    }

    public void leftRightRotation(Node n)
    {
        Node leftNode = n.getLeftChild();
        Node leftrightNode = leftNode.getRightChild();
        n.setLeftChild(leftrightNode);
        leftrightNode.setLeftChild(leftNode);
        leftrightNode.setParent(n);
        leftNode.setParent(leftrightNode);
        leftNode.setRightChild(null);
        rightRotation(n);
    }
    public void rightLeftRotation(Node n)
    {
        Node rightNode = n.getRightChild();
        Node rightleftNode = rightNode.getLeftChild();
        n.setRightChild(rightleftNode);
        rightleftNode.setRightChild(rightNode);
        rightleftNode.setParent(n);
        rightNode.setParent(rightleftNode);
        rightNode.setLeftChild(null);
        leftRotation(n);
    }

    public Node checkBalance()
    {
        /*if(n == null)
        {
            return null;
        }
        checkBalance(n.getLeftChild(), t);
        checkBalance(n.getRightChild(), t);

        if(n.balance > 1 || n.balance < -1)
        {
            int checked = n.balance;
            if (checked < -1 && (n.getRightChild().getRightChild() != null)) {
                t.leftRotation(n, t);
            } else if (checked < -1 && (n.getRightChild().getLeftChild() != null)) {
                t.rightLeftRotation(n, t);
            } else if (checked > 1 && (n.getLeftChild().getLeftChild() != null)) {
                t.rightRotation(n, t);
            } else {
                t.leftRightRotation(n, t);
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

            if(node.getLeftChild() != null && scene.getLeftChecked() == false) {
                Stage s = new Stage(node.getLeftChild());
                stk.push(s);
                continue;

            }
            scene.setLeftChecked();
            if(scene.getRightChecked() == false)
            {
                if(node.getRightChild() == null)
                {
                    scene.setrightChecked();
                }
                else {
                    Stage s = new Stage(node.getRightChild());
                    stk.push(s);
                    continue;
                }
            }
            while(scene.getLeftChecked() == true && scene.getRightChecked() == true)
            {
                if(node.getBalance() > 1 || node.getBalance() < -1)
                {
                    int checked = node.getBalance();
                    if (checked < -1 && (node.getRightChild().getRightChild() != null)) {
                        leftRotation(node);
                    } else if (checked < -1 && (node.getRightChild().getLeftChild() != null)) {
                        rightLeftRotation(node);
                    } else if (checked > 1 && (node.getLeftChild().getLeftChild() != null)) {
                        rightRotation(node);
                    } else {
                        leftRightRotation(node);
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

    public void printTree()
    {

        /*if (n != null) {
            System.out.print(n.getVal() + " ");
            printTree(n.getLeftChild());
            printTree(n.getRightChild());
        }*/
        Stage first = new Stage(curNode);
        Stack<Stage> stk = new Stack<Stage>();
        stk.push(first);
        while(stk.empty() == false)
        {
            Stage scene = stk.peek();
            Node node = scene.getNode();
            if(node.getLeftChild() != null && scene.getLeftChecked() == false) {
                Stage s = new Stage(node.getLeftChild());
                stk.push(s);
                continue;
                /*scene = stk.peek();
                node = scene.getNode();*/
            }
            stk.peek().setLeftChecked();
            if(stk.peek().getRightChecked() == false)
            {
                if(stk.peek().getNode().getRightChild() == null)
                {
                    stk.peek().setrightChecked();
                }
                else {
                    Stage s = new Stage(stk.peek().getNode().getRightChild());
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
