
public class BinaryTree {
    public TreeNode root;

    public static class TreeNode {

        public TreeNode left;
        public TreeNode right;
        public Object data;

        public TreeNode(Object data) {
            this.data = data;
            left = right = null;
        }
    }


    public static void main(String[] args) {

        /**
         *   Our Example Binary Search Tree
         *       10
         *     5    20
         *   4  8  15 25
         */

        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(10);
        tree.root.left = new TreeNode(5);
        tree.root.right = new TreeNode(20);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(8);
        tree.root.right.left = new TreeNode(15);
        tree.root.right.right = new TreeNode(25);

        System.out.println("Search Value 2 is in tree? " + searchIteratively(tree.root, 2));
        System.out.println("Search Value 10 in tree? " + searchIteratively(tree.root, 10));
        System.out.println("Inserting value 3: " + insertionIterative(tree.root,3));
        System.out.println("Search Value 3 tree? " + searchIteratively(tree.root, 3));
        System.out.println("Search Value 4 tree? " + searchIteratively(tree.root, 4));
        deleteNodeIteratively(tree.root,4);
        System.out.println("Search Value 4 tree? " + searchIteratively(tree.root, 4));
    }
    
    
    public static boolean searchIteratively(TreeNode root, int value) {

        while (root != null) {
            if ((int) root.data == value)
                return true;

            if (value < (int) root.data)
                root = root.left;

            else
                root = root.right;
        }

        return false;
    }

    public static TreeNode insertionIterative(TreeNode root, int value) {

        TreeNode current, parent;

        TreeNode tempNode = new TreeNode(value);

        if (root == null) {
            root = tempNode;
            return root;
        } else {
            current = root;
        }

        while (true) {
            parent = current;

            if (value < (int) current.data) {
                current = current.left;
                if (current == null) {
                    parent.left = tempNode;
                    return root;
                }

            } else if (value > (int) current.data) {
                current = current.right;

                if (current == null) {
                    parent.right = tempNode;
                    return root;
                }
            }

        }
    }

    public static TreeNode deleteNodeIteratively(TreeNode root, int value) {
        TreeNode parent = null, current = root;
        boolean hasLeft = false;

        if (root == null)
            return root;

        while (current != null) {
            if ((int) current.data == value) {
                break;
            }

            parent = current;
            if (value < (int) current.data) {
                hasLeft = true;
                current = current.left;
            } else {
                hasLeft = false;
                current = current.right;
            }
        }


        if (parent == null) {
            return deleteNodeIteratively(current);
        }

        if (hasLeft) {
            parent.left = deleteNodeIteratively(current);
        } else {
            parent.right = deleteNodeIteratively(current);
        }

        return root;
    }

    private static TreeNode deleteNodeIteratively(TreeNode node) {

        if (node != null) {
            if (node.left == null && node.right == null) {
                return null;
            }

            if (node.left != null && node.right != null) {
                TreeNode inOrderSuccessor = deleteInOrderSuccessorDuplicate(node);
                node.data = inOrderSuccessor.data;
            } else if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return node;
    }

    private static TreeNode deleteInOrderSuccessorDuplicate(TreeNode node) {
        TreeNode parent = node;
        node = node.right;
        boolean rightChild = node.left == null;

        while (node.left != null) {
            parent = node;
            node = node.left;
        }

        if (rightChild) {
            parent.right = node.right;
        } else {
            parent.left = node.right;
        }

        node.right = null;
        return node;
    }
 


}
