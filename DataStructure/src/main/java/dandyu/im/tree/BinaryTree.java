package dandyu.im.tree;

public class BinaryTree {

    class BinaryNode {
        public int number;
        public BinaryNode leftNode;
        public BinaryNode rightNode;
    }

    private BinaryNode rootNode;
    private int size;
    private int level;

    public boolean add(int value) {
        if (rootNode == null) {
            System.out.println("Root node number " + value);
            rootNode = new BinaryNode();
            rootNode.number = value;
            size = 1;
            level = 1;
            return true;
        }

        BinaryNode lastNode, cursor;
        lastNode = cursor = rootNode;
        int thisLevel = 1;
        while (cursor != null) {
            if (value == cursor.number) {
                System.out.println(value + " already exist.");
                return false;
            }

            lastNode = cursor;

            if (value < cursor.number) {
                cursor = cursor.leftNode;
                thisLevel++;
                if (level < thisLevel)
                    level = thisLevel;
                continue;
            } else {
                cursor = cursor.rightNode;
                thisLevel++;
                if (level < thisLevel)
                    level = thisLevel;
                continue;
            }
        }

        BinaryNode newNode = new BinaryNode();
        newNode.number = value;
        if (value < lastNode.number) {
            System.out.println("Add "+ newNode.number + " to leftNode of " + lastNode.number);
            lastNode.leftNode = newNode;
        } else {
            System.out.println("Add "+ newNode.number + " to rightNode of " + lastNode.number);
            lastNode.rightNode = newNode;
        }
        size++;

        return true;
    }

    public int size() {
        return size;
    }

    public int level() {
        return level;
    }

    public BinaryNode search(int value) {
        if (rootNode == null) {
            return null;
        }

        // preorder, inorder, postorder
        //BinaryNode targetNode = preorder(rootNode, value);
        //return targetNode;
        return null;
    }

    public void showPreorder() {
        if (rootNode == null) {
            System.out.println("Root node is null.");
            return;
        }

        preorder(rootNode);
        System.out.println("");
    }

    private void preorder(BinaryNode node) {
        System.out.print(" > " + node.number);

        if (node.leftNode != null)
            preorder(node.leftNode);

        if (node.rightNode != null)
            preorder(node.rightNode);
    }

    public void showInorder() {
        if (rootNode == null) {
            System.out.println("Root node is null.");
            return;
        }

        inorder(rootNode);
        System.out.println("");
    }

    private void inorder(BinaryNode node) {
        if (node.leftNode != null)
            inorder(node.leftNode);

        System.out.print(" > " + node.number);

        if (node.rightNode != null)
            inorder(node.rightNode);
    }

    public void showPostorder() {
        if (rootNode == null) {
            System.out.println("Root node is null.");
            return;
        }

        postorder(rootNode);
        System.out.println("");
    }

    private void postorder(BinaryNode node) {
        if (node.leftNode != null)
            postorder(node.leftNode);

        if (node.rightNode != null)
            postorder(node.rightNode);

        System.out.print(" > " + node.number);
    }

    /*private BinaryNode preorder(BinaryNode node, int value) {
        System.out.println(node.number);
        if (node.number == value) {
            System.out.println(value + " is found.");
            return node;
        } else {
            if (node.leftNode != null) {
                BinaryNode nodeA = preorder(node.leftNode, value);
                if (nodeA != null)
                    return nodeA;
            }

            if (node.rightNode != null) {
                BinaryNode nodeB = preorder(node.rightNode, value);
                if (nodeB != null)
                    return nodeB;
            }

            return null;
        }
    }*/

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        int searchValue = 18;
        tree.add(9);
        tree.add(4);
        tree.add(19);
        tree.add(3);
        tree.add(8);
        tree.add(11);
        tree.add(20);
        tree.add(2);
        tree.add(12);
        tree.add(18);

        System.out.println("====================================");
        System.out.println("Tree Size: " + tree.size());
        System.out.println("Tree Level: " + tree.level());
        System.out.println("");
        System.out.println("Show Preorder");
        tree.showPreorder();
        System.out.println("Show Inorder");
        tree.showInorder();
        System.out.println("Show Postorder");
        tree.showPostorder();
        System.out.println("====================================");
        System.out.println("Search value: " + searchValue);
        BinaryNode searchedNode = tree.search(searchValue);
        if (searchedNode == null) {
            System.out.println(searchValue + " Not Found");
            return;
        }

        System.out.println(searchValue + " Found");
    }

    private static int getRandomNumber() {
        return (int) ((Math.random() * 100) + 1);
    }

}









