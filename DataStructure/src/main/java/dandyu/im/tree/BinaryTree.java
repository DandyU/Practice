package dandyu.im.tree;

public class BinaryTree {

    private BinaryNode rootNode;
    private int size;

    BinaryTree() {

    }

    public boolean add(final int value) {
        if (rootNode == null) {
            rootNode = BinaryNode.builder().number(value).build();
            size++;

            return true;
        }

        BinaryNode lastNode, cursor;
        lastNode = cursor = rootNode;
        while (cursor != null) {
            if (value == cursor.number)
                return false;

            lastNode = cursor;

            if (value < cursor.number) {
                cursor = cursor.leftNode;
                continue;
            } else {
                cursor = cursor.rightNode;
                continue;
            }
        }

        BinaryNode newNode = BinaryNode.builder().number(value).build();
        if (value < lastNode.number)
            lastNode.leftNode = newNode;
        else
            lastNode.rightNode = newNode;

        return true;
    }

}
