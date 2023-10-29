/**
 * this is Tree Node class which creates new node, and sets it's left and right
 * child to null with size of 1.
 *
 * @author Mohammed Arab instructor Jason Heard course 2631
 */
public class TreeNode {
    private final String key;
    private final TreeNode left;
    private final TreeNode right;
    private final int size;

    /***
     * this constructor is to make the root of the tree.
     *
     * @param newKey is the string for the root node.
     */
    public TreeNode(String newKey) {
        key = newKey;
        left = null;
        right = null;
        size = 1;
    }

    /***
     * this is to make the new tree without changing it's old nodes, also every time
     * nodes gets added or removed size gets changed.
     *
     * @param newKey   is the string for the added node.
     * @param newLeft  new left child of the new node.
     * @param newRight new right child of the new node.
     */
    public TreeNode(String newKey, TreeNode newLeft, TreeNode newRight) {
        key = newKey;
        left = newLeft;
        right = newRight;
        if (left == null && right == null) {
            size = 1;
        } else if (left == null && right != null) {
            size = 1 + right.size;
        } else if (left != null && right == null) {
            size = 1 + left.size;
        } else {
            size = 1 + right.size + left.size;
        }
    }

    /**
     * returns the key of the node.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * returns left child of the node.
     *
     * @return the left
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * returns right child of the node.
     *
     * @return the right
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * returns the size of the tree.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

}
