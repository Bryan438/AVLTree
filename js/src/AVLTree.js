
class Node{
    parentNode = null;
    leftChild = null;
    rightChild = null;
    leftHeight;
    rightHeight;
    balance = 0;
    value;
    constructor(value){
        this.value = value;
    }
    setParent(node){
        this.parentNode = node;
    }
    setLeftChild(node){
        this.leftChild = node;
    }
    setRightChild(node){
        this.rightChild = node;
    }
    setLeftHeight(height){
        this.leftHeight = height;
    }
    setRightHeight(height){
        this.rightHeight = height;
    }
    setBalance(b){
        this.balance = b;
    }
    setVal(v){
        this.value = v;
    }
    getParent(){
        return this.parentNode;
    }
    getLeftNode(){
        return this.leftChild;
    }
    getRightNode(){
        return this.rightChild;
    }
    getLeftHeight(){
        return this.leftHeight;
    }
    getRightHeight(){
        return this.rightHeight;
    }
    getBalance(){
        return this.balance;
    }
    getValue(){
        return this.value;
    }
}

class Stage{
    node = null;
    leftChecked = false;
    rightChecked = false;
    constructor(n){
        this.node = n;
    }
    setLeftChecked(){
        this.leftChecked = true;
    }
    setRightChecked(){
        this.rightChecked = true;
    }
    getLeftChecked(){
        return this.leftChecked;
    }
    getRightChecked(){
        return this.rightChecked;
    }
    getNode(){
        return this.node;
    }

}

class Tree{
    curNode = null;
    constructor(){
    }
    setNode(n){
        this.curNode = n;
    }
    getNode(){
        return this.curNode;
    }
    insertNode(val){
        var n = new Node(val);
        var no = this.curNode;
        while(true){
            if(no === null){
                this.curNode = n;
                break;
            }
            else if(n.getValue() <= no.getValue()){
                if(no.getLeftNode() == null){
                    no.setLeftChild(n);
                    n.setParent(no);
                    break;
                }
                else{
                    no = no.getLeftNode();
                }
            }
            else{
                if(no.getRightNode() === null){
                    no.setRightChild(n);
                    n.setParent(no);
                    break;
                }
                else{
                    no = no.getRightNode();
                }
            }
            
        }
        this.setHeight();
        this.calBalance();
        this.checkBalance();

    }
    calBalance(){
        var first = new Stage(this.curNode);
        var stk = [];
        stk.push(first);
        while(stk.length !== 0){
            var scene = stk[stk.length - 1];
            var node = scene.getNode();
            if(node.getLeftNode() !== null && scene.getLeftChecked() === false){
                const s = new Stage(stk[stk.length - 1].getNode().getLeftNode());
                stk.push(s);
                continue;
            }
            stk[stk.length - 1].setLeftChecked();
            if(stk[stk.length - 1].getRightChecked() === false)
            {
                if(stk[stk.length - 1].getNode().getRightNode() === null)
                {
                    scene.setRightChecked();
                }
                else{
                    const s = new Stage(node.getRightNode());
                    stk.push(s);
                    continue;
                }
            }
            while(scene.getLeftChecked() === true && scene.getRightChecked() === true){
                node.setBalance(node.getLeftHeight() - node.getRightHeight());
                stk.pop();
                if(stk.length === 0){
                    break;
                }
                scene = stk[stk.length - 1];
                node = scene.getNode();
                if(stk[stk.length - 1].getLeftChecked() == false){
                    scene.setLeftChecked();
                }
                else if(stk[stk.length - 1].getRightChecked() == false){
                    scene.setRightChecked();
                }
            }
        }
    }
    setHeight(){
        var first = new Stage(this.curNode);
        var stk = [];
        stk.push(first);
        while(stk.length !== 0){
            var scene = stk[stk.length - 1];
            var node = scene.getNode();
            if(node.getLeftNode() !== null && scene.getLeftChecked() === false){
                const s = new Stage(stk[stk.length - 1].getNode().getLeftNode());
                stk.push(s);
                continue;
            }
            if(stk[stk.length - 1].getNode().getLeftNode() === null)
            {
                scene.setLeftChecked();
                node.setLeftHeight(-1);
            }
            if(stk[stk.length - 1].getRightChecked() === false)
            {
                if(stk[stk.length - 1].getNode().getRightNode() === null)
                {
                    scene.setRightChecked();
                    node.setRightHeight(-1);
                }
                else{
                    const s = new Stage(node.getRightNode());
                    stk.push(s);
                    continue;
                }
            }
            while(scene.getLeftChecked() === true && scene.getRightChecked() === true){
                if(node.getLeftNode() != null){
                    node.setLeftHeight(Math.max(node.getLeftNode().getLeftHeight(), node.getLeftNode().getRightHeight()) + 1);
                }
                if(stk[stk.length - 1].getNode().getRightNode() !== null)
                {
                    node.setRightHeight(Math.max(node.getRightNode().getLeftHeight(), node.getRightNode().getRightHeight()) + 1);
                }
                stk.pop();
                if(stk.length === 0){
                    break;
                }
                scene = stk[stk.length - 1];
                node = scene.getNode();
                if(stk[stk.length - 1].getLeftChecked() == false){
                    scene.setLeftChecked();
                }
                else if(stk[stk.length - 1].getRightChecked() == false){
                    scene.setRightChecked();
                }
            }
        }
    }

    leftRotation(n){
        var parent = n.getParent();
        var rightNode = n.getRightNode();
        var leftNode = rightNode.getLeftNode();
        if(parent === null){
            rightNode.setLeftChild(n);
            rightNode.setParent(null);
            n.setParent(rightNode);
            if(leftNode != null){
                n.setRightChild(leftNode);
                leftNode.setParent(n);
            }
            else{
                n.setRightChild(null);
            }
            this.setNode(rightNode);
        }
        else{
            rightNode.setLeftChild(n);
            rightNode.setParent(parent);
            n.setParent(rightNode);
            if(rightNode.getParent().getValue() > rightNode.getValue())
            {
                rightNode.getParent().setLeftChild(rightNode);
            }
            else{
                rightNode.getParent().setRightChild(rightNode);
            }
            if(leftNode != null){
                n.setRightChild(leftNode);
                leftNode.setParent(n);
            }
            else{
                n.setRightChild(null);
            }
        }

    }
    rightRotation(n)
    {
        var parent = n.getParent();
        var leftChild = n.getLeftNode();
        var rightChild = leftChild.getRightNode();
        if(parent === null){
            leftChild.setRightChild(n);
            leftChild.setParent(null);
            n.setParent(leftChild);
            if(rightChild !== null){
                n.setLeftChild(rightChild);
                rightChild.setParent(n);
            }
            else{
                n.setLeftChild(null);
            }
            this.setNode(leftChild);
        }
        else{
            leftChild.setRightChild(n);
            leftChild.setParent(parent);
            n.setParent(leftChild);
            if(leftChild.getParent().getValue() > leftChild.getValue())
            {
                leftChild.getParent().setRightChild(leftChild);
            }
            else{
                leftChild.getParent().setLeftChild(leftChild);
            }
            if(rightChild !== null){
                n.setLeftChild(rightChild);
                rightChild.setParent(n);
            }
            else{
                n.setLeftChild(null);
            }
        }
    }
    leftRightRotation(n)
    {
        var leftNode = n.getLeftNode();
        var leftRightNode = leftNode.getRightNode();
        var leftRightLeftNode = leftRightNode.getLeftNode();
        n.setLeftChild(leftRightNode);
        leftRightNode.setLeftChild(leftNode);
        leftRightNode.setParent(n);
        leftNode.setParent(leftRightNode);
        if(leftRightLeftNode === null){
            leftNode.setRightChild(null);
        }
        else{
            leftNode.setRightChild(leftRightLeftNode);
            leftRightLeftNode.setParent(leftNode);
        }
        this.rightRotation(n);
    }
    rightLeftRotation(n)
    {
        var rightNode = n.getRightNode();
        var rightLeftNode = rightNode.getLeftNode();
        var rightLeftRightNode = rightLeftNode.getRightNode();
        n.setRightChild(rightLeftNode);
        rightLeftNode.setRightChild(rightNode);
        rightLeftNode.setParent(n);
        rightNode.setParent(rightLeftNode);
        if(rightLeftRightNode === null){
            rightNode.setLeftChild(null);
        }
        else{
            rightNode.setLeftChild(rightLeftRightNode);
            rightLeftRightNode.setParent(rightNode);
        }
        this.leftRotation(n);
    }
    checkBalance()
    {
        var first = new Stage(this.curNode);
        var stk = [];
        stk.push(first);
        while(stk.length !== 0){
            var scene = stk[stk.length - 1];
            var node = scene.getNode();
            if(node.getLeftNode() !== null && scene.getLeftChecked() === false){
                const s = new Stage(stk[stk.length - 1].getNode().getLeftNode());
                stk.push(s);
                continue;
            }
            scene.setLeftChecked();     
            if(stk[stk.length - 1].getRightChecked() === false)
            {
                if(stk[stk.length - 1].getNode().getRightNode() === null)
                {
                    scene.setRightChecked();
                }
                else{
                    const s = new Stage(node.getRightNode());
                    stk.push(s);
                    continue;
                }
            }
            while(scene.getLeftChecked() === true && scene.getRightChecked() === true){
                if(node.getBalance() > 1 || node.getBalance() < -1)
                {
                    var checked = node.getBalance();
                    if(checked < -1 && node.getLeftNode() === null){
                        this.leftRotation(node);
                    }
                    else if(checked > 1 && node.getLeftNode().getBalance() === -1)
                    {
                        this.leftRightRotation(node);
                    }
                    else if(checked > 1 && node.getRightNode() === null){
                        this.rightRotation(node);
                    }
                    else if(checked < -1 && node.getRightNode().getBalance() === 1)
                    {
                        this.rightLeftRotation(node);
                    }
                    else if(checked < -1){
                        this.leftRotation(node);
                    }
                    else{
                        this.rightRotation(node);
                    }
                }
                this.setHeight();
                this.calBalance();
                stk.pop();
                if(stk.length === 0){
                    break;
                }
                scene = stk[stk.length - 1];
                node = scene.getNode();
                if(stk[stk.length - 1].getLeftChecked() == false){
                    scene.setLeftChecked();
                }
                else if(stk[stk.length - 1].getRightChecked() == false){
                    scene.setRightChecked();
                }
            }
        }
    }
    remove(n){
        if(n.getLeftNode() === null && n.getRightNode() === null)
        {
            if(n.getParent() === null){
                this.curNode = null;
            }
            else if(n.getParent().getLeftNode() === n){
                n.getParent().setLeftChild(null);
            }
            else{
                n.getParent().setRightChild(null);
            }
        }
        else if(((n.getLeftNode() != null || n.getRightNode() != null) === true) && (n.getRightNode() != null && n.getLeftNode() != null) === false){
            var parent = n.getParent();
            var leftChild = n.getLeftNode();
            var rightChild = n.getRightNode();
            if(parent === null){
                if(leftChild != null){
                    this.curNode = leftChild;
                    leftChild.setParent(null);
                }
                else{
                    this.curNode = rightChild;
                    rightChild.setParent(null);
                }
            }
            else{
                if(parent.getLeftNode() === n){
                    if(leftChild != null){
                        parent.setLeftChild(leftChild);
                        leftChild.setParent(parent);
                    }
                    else{
                        parent.setLeftChild(rightChild);
                        rightChild.setParent(parent);
                    }
                }
                else{
                    if(leftChild != null){
                        parent.setRightChild(leftChild);
                        leftChild.setParent(parent);
                    }
                    else{
                        parent.setRightChild(rightChild);
                        rightChild.setParent(parent);
                    }
                }
            }      
        }
        else{
            var leftNode = n.getLeftNode();
            if(leftNode.getRightNode() === null){
                var temp = n.getValue();
                n.setVal(leftNode.getValue());
                leftNode.setVal(temp);
                this.remove(leftNode);
            }
            else{
                var leftLargestNode = leftNode;
                while(leftLargestNode.getRightNode() != null){
                    leftLargestNode = leftLargestNode.getRightNode();
                    if(leftLargestNode === null){
                        break;
                    }
                }
                temp = n.getValue();
                n.setVal(leftLargestNode.getValue());
                leftLargestNode.setVal(temp);
                this.remove(leftLargestNode);
            }
        }
    }
    deleteNode(val){
        var first = new Stage(this.curNode);
        var stk = [];
        stk.push(first);
        while(stk.length !== 0){
            var scene = stk[stk.length - 1];
            var node = scene.getNode();
            if(node.getLeftNode() !== null && scene.getLeftChecked() === false){
                const s = new Stage(stk[stk.length - 1].getNode().getLeftNode());
                stk.push(s);
                continue;
            }
            stk[stk.length - 1].setLeftChecked();
            if(stk[stk.length - 1].getRightChecked() === false)
            {
                if(stk[stk.length - 1].getNode().getRightNode() === null)
                {
                    scene.setRightChecked();
                }
                else{
                    const s = new Stage(node.getRightNode());
                    stk.push(s);
                    continue;
                }
            }
            while(scene.getLeftChecked() === true && scene.getRightChecked() === true){
                if(stk[stk.length - 1].getNode().getValue() === val){
                    this.remove(stk[stk.length-1].getNode());
                    if(this.curNode === null){
                        stk.pop();
                        break;
                    }
                    this.setHeight();
                    this.calBalance();
                    this.checkBalance();  
                }
                stk.pop();
                if(stk.length === 0){
                    break;
                }
                scene = stk[stk.length - 1];
                node = scene.getNode();
                if(stk[stk.length - 1].getLeftChecked() == false){
                    scene.setLeftChecked();
                }
                else if(stk[stk.length - 1].getRightChecked() == false){
                    scene.setRightChecked();
                }
            }
        }
        if(this.curNode != null){
            this.setHeight();
            this.calBalance();
            this.checkBalance();
        }
    }
    printTree(){
        var first = new Stage(this.curNode);
        var stk = [];
        if(this.curNode != null){
            stk.push(first);
        }
        
        while(stk.length !== 0){
            var scene = stk[stk.length - 1];
            var node = scene.getNode();
            if(node.getLeftNode() !== null && scene.getLeftChecked() === false){
                const s = new Stage(stk[stk.length - 1].getNode().getLeftNode());
                stk.push(s);
                continue;
            }
            scene.setLeftChecked();
            if(stk[stk.length - 1].getRightChecked() === false)
            {
                if(stk[stk.length - 1].getNode().getRightNode() === null)
                {
                    scene.setRightChecked();
                }
                else{
                    const s = new Stage(node.getRightNode());
                    stk.push(s);
                    continue;
                }
            }
            while(scene.getLeftChecked() === true && scene.getRightChecked() === true){
                console.log(node.getValue() + " ");
                stk.pop();
                if(stk.length === 0){
                    break;
                }
                scene = stk[stk.length - 1];
                node = scene.getNode();
                if(stk[stk.length - 1].getLeftChecked() == false){
                    scene.setLeftChecked();
                }
                else if(stk[stk.length - 1].getRightChecked() == false){
                    scene.setRightChecked();
                }
            }
        }
    }
}
const tree = new Tree()
tree.insertNode(20);

tree.deleteNode(20);


tree.printTree();

