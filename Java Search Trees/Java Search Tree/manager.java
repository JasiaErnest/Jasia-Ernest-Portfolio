import java.util.Stack;

public class manager<E> {

    searchTreeNode<String> root; 
    
    public manager() { 
        this.root = null; 
    }

    //A method to add a new account (node)
    public void add(searchTreeNode<String> account) { 
        //Checks if the binary search tree is empty
        if (root == null) { 
            root = account; //Adds the account to the binary search tree
            return; 
        } 
        searchTreeNode<String> current = root; //Declaring current as the first node that was added
        searchTreeNode<String> parent; 
        while (true) { 
            parent = current; //For adding 
            if (account.userIdx.compareTo(current.userIdx) < 0) { 
                current = current.left; //Traversing to the left of the current node
                if (current == null) { //Checks if it is occupied. If occupied, the while loop will continue. If not occupied:
                    parent.left = account; //Adds the account to the binary search tree
                    return; //Return statement to stop the while loop & get out of the method
                } 
            } else { 
                current = current.right; //Traversing to the right of the current node
                if (current == null) { //Checks if it is occupied. If occupied, the while loop will continue. If not occupied:
                    parent.right = account; //Adds the account to the binary search tree
                return; //Return statement to stop the while loop & get out of the method
            } 
        } 
    }
    }
    //Method to search for the account (node) by using the index
    public searchTreeNode<String> search(String incomingUserIdx) { 
        searchTreeNode<String> current = root; 
        while (current != null) { 
            if (current.userIdx.equals(incomingUserIdx)) { 
                return current; 
            } else if (incomingUserIdx.compareTo(current.userIdx) < 0) { 
                /*If smaller than zero (negative), then incomingUserIdx is smaller 
                than current.userIdx so it moves to the left as the left should contain smaller user index.*/
                current = current.left; 
            } else { 
                //The right should contain larger user index
                current = current.right; 
            } 
        } 
        return null; 
    }

    //Method to print all the data in an account (node) 
    public void forPrint(searchTreeNode<String> root) {
        System.out.println("User index: " + root.userIdx);
    }

    //Method to print all the data in an account in a in-order format
    //Uses stack so the main logic is the same as the findUserIdx method
    public void inorder() { 
        if (root == null) {
            System.out.println("This directory is empty");
            return; 
        }
        Stack<searchTreeNode<String>> stack = new Stack<>(); 
        searchTreeNode<String> current = root; 
        while (current != null || !stack.isEmpty()) { 
            while (current != null) { 
                stack.push(current); 
                current = current.left; 
            } 
            current = stack.pop(); 
            forPrint(current); 
            current = current.right; 
        }
    }

    //Method to print all the data in an account in a pre-order format
    public void preorder() { 
        if (root == null) {
            System.out.println("This directory is empty");
            return; 
        } 
        Stack<searchTreeNode<String>> stack = new Stack<>(); 
        stack.push(root); 

        while (!stack.isEmpty()) { 
            searchTreeNode<String> node = stack.pop(); 
            forPrint(node);
            if (node.right != null) { 
                stack.push(node.right); 
            } 
            if (node.left != null) { 
                stack.push(node.left); 
        } }
    } 
    
    //Method to print all the data in an account in a post-order format
    public void postorder() { 
        if (root == null) {
            System.out.println("This directory is empty");
            return; 
        }
        //stack1 is used essentially as a placeholder for stack2
        Stack<searchTreeNode<String>> stack1 = new Stack<>(); 
        Stack<searchTreeNode<String>> stack2 = new Stack<>(); 
        stack1.push(root);

        while (!stack1.isEmpty()) { 
            searchTreeNode<String> node = stack1.pop(); 
            stack2.push(node); 
            if (node.left != null) { 
                stack1.push(node.left); 
            } 
            if (node.right != null) { 
                stack1.push(node.right); 
            } 
        } 

        //stack2 is then looped through and printed
        while (!stack2.isEmpty()) { 
            searchTreeNode<String> node = stack2.pop(); 
            forPrint(node);
    }
    }

    //Method to delete a specific account (node) according to the user's wishes
    public void delete(String userIdxToDelete) { 
        searchTreeNode<String> current = root;
        //Used so that the next accounts (nodes) after the deleted one can find its' rightful position 
        searchTreeNode<String> parent = null;  
        while (current != null && !current.userIdx.equals(userIdxToDelete)) { 
            parent = current; //Updating the parent
            if (userIdxToDelete.compareTo(current.userIdx) < 0) { 
                /*If smaller than zero (negative), then incomingUserIdx is smaller 
                than current.userIdx so it moves to the left as the left should contain smaller user index.*/
                current = current.left; 
            } else { 
                //The right should contain larger user index
                current = current.right; 
            } 
        } 
        
        if (current == null) return; 
        // Case 1: Node to be deleted has no children (is a leaf node) 
        if (current.left == null && current.right == null) { 
            if (current != root) { 
                if (parent.left == current) { 
                    parent.left = null; 
                } else { 
                    parent.right = null; 
                } 
            } else { 
                root = null; 
            } 
        } // Case 2: Node to be deleted has one child
        else if (current.left == null || current.right == null) { 
            searchTreeNode<String> child = (current.left != null) ? current.left : current.right; 
            if (current != root) { 
                if (current == parent.left) { 
                    parent.left = child; 
                } else { 
                    parent.right = child; 
                } 
            } else { 
                root = child; 
            } 
        } else { // Case 3: Node to be deleted has two children 
            searchTreeNode<String> successorParent = current; 
            searchTreeNode<String> successor = current.right; 
            while (successor.left != null) { 
                successorParent = successor; 
                successor = successor.left; 
            }
            current.userIdx = successor.userIdx; 
            if (successorParent != current) { 
                successorParent.left = successor.right; 
            } else { 
                current.right = successor.right; 
            } 
        }
    }

    //This method modifies a specific node in the binary search tree
    public void modify(String userIdx, String newUserIdx) { 
        //Using the search method to search for the account (node) that the user wants to modify
        searchTreeNode<String> accountToModify = search(userIdx); 
        //Overrides everything
        if (accountToModify != null) { 
            accountToModify.userIdx = newUserIdx;
        } 
    }
}
