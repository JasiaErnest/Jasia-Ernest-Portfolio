public class searchTreeNode<E> {
    
    //Creating the data placeholders
    protected E userIdx;
    
    //Making left and right nodes for the tree
    searchTreeNode<E> left, right;

    //A method to set the data in the node to the user input data
    public searchTreeNode(E incomingUserIdx) { 
        this.userIdx = incomingUserIdx;
        this.left = this.right = null;
    }
}
