package main;

class Node {
    int value;
    Node left, right;

    Node(int value) {
        this.value = value;
        left = right = null;
    }
}

class BST {
    Node root;

    // Insert a value into BST
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    // Search for a value
    public boolean search(int value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node root, int value) {
        if (root == null) return false;
        if (root.value == value) return true;
        return value < root.value
            ? searchRec(root.left, value)
            : searchRec(root.right, value);
    }

    // Inorder traversal
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.value + " ");
            inorderRec(root.right);
        }
    }

    // Preorder traversal
    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Postorder traversal
    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.value + " ");
        }
    }
}

public class Demo {
    public static void main(String[] args) {
        BST bst = new BST();
        int[] numbers = {50, 30, 70, 20, 40, 60, 80};

        for (int num : numbers) {
            bst.insert(num);
        }

        System.out.println("Inorder traversal:");
        bst.inorder();

        System.out.println("Preorder traversal:");
        bst.preorder();

        System.out.println("Postorder traversal:");
        bst.postorder();

        int searchVal = 60;
        System.out.println("Searching for " + searchVal + ": " + bst.search(searchVal));

        searchVal = 25;
        System.out.println("Searching for " + searchVal + ": " + bst.search(searchVal));
    }
}
