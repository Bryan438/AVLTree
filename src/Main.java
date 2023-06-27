import java.util.Scanner;
import java.lang.*;
import p1.Tree;
import p3.Node;

public class Main {

    public static void main(String[] args) {


        Tree tree = new Tree();
        int val;

        Scanner myObj = new Scanner(System.in);

        while (true) {
            System.out.println("Construct tree, -1 to quit, or -2 to delete mode");
            val = myObj.nextInt();
            if (val == -1) {
                break;
            } else if (val == -2) {
                System.out.println("Delete tree, -1 to quit");
                int dValue = myObj.nextInt();
                tree.deleteNode(dValue);
            } else {
                Node n = new Node(val);
                if (tree.getNode() == null) {
                    tree.setNode(n);
                } else {
                    tree.insertNode(val);
                }
            }

            tree.printTree();
        }

    }

}
