import java.util.NoSuchElementException;

/**
 * An immutable sorted set that holds string keys. and none of the methods of
 * the set modifies it, except for add and remove modifies the set and they
 * return a new set instead.
 *
 * @author Mohammed Arab instructor Jason Heard course 2631
 *
 */
public class ImmutableBinarySearchTree implements ImmutableSortedSet {
    private final TreeNode root;

    /***
     * this is default constructor to initialize root to null.
     */
    ImmutableBinarySearchTree() {
        root = null;
    }

    /***
     * Parameterized constructor to make changes to the root since it is a final,
     * variable.
     *
     * @param node that needs to be changed.
     */
    public ImmutableBinarySearchTree(TreeNode node) {
        root = node;
    }

    @Override
    public ImmutableSortedSet add(String key) {
        TreeNode node = new TreeNode(key);
        ImmutableBinarySearchTree newTree = new ImmutableBinarySearchTree(node);
        return add(newTree, key);

    }

    private ImmutableBinarySearchTree add(ImmutableBinarySearchTree tree, String key) {
        TreeNode node;
        ImmutableBinarySearchTree newTree;

        // if the String key is in the tree then <code>this</code> should be returned.
        // else it checks if there is nothing in the tree then it will just return the
        // tree with, the added string key as the root of the tree, after that it checks
        // for every
        // key, that will be added if it is smaller than it's parent then it gets added
        // to the
        // left else to the right.
        if (contains(key)) {
            return this;
        } else if (root == null) {
            return tree;
        } else if (key == null || (root.getKey() != null && key.compareTo(root.getKey()) < 0)) {
            // makes a new tree with the left side being new root to compare it to the new
            // key, recursively
            // then adds new tree with all of the keys.
            newTree = new ImmutableBinarySearchTree(root.getLeft());
            newTree = newTree.add(tree, key);
            node = new TreeNode(root.getKey(), newTree.root, root.getRight());
            return new ImmutableBinarySearchTree(node);
        } else {
            // makes a new tree with the right side being new root to compare it to the new
            // key, recursively
            // then adds new tree with all of the keys.
            newTree = new ImmutableBinarySearchTree(root.getRight());
            newTree = newTree.add(tree, key);
            node = new TreeNode(root.getKey(), root.getLeft(), newTree.root);
            return new ImmutableBinarySearchTree(node);
        }

    }

    @Override
    public ImmutableSortedSet remove(String key) {
        return removeNode(key);
    }

    private ImmutableBinarySearchTree removeNode(String key) {
        ImmutableBinarySearchTree newTree;
        ImmutableBinarySearchTree tempTree;
        TreeNode node;
        TreeNode tempNode;

        // if the key that will get removed is not in the tree then nothing will be
        // changed.
        if (!contains(key)) {
            return this;
        } else if ((key == null && root.getKey() == null)) {
            // if root key is null and it is the key that will get removed then it just
            // returns right child of the tree,
            // as a new tree.
            newTree = new ImmutableBinarySearchTree(root.getRight());
            newTree = newTree.add(newTree, key);
            node = new TreeNode(root.getKey(), root.getLeft(), newTree.root);
            return new ImmutableBinarySearchTree(node);
        } else if (key.equals(root.getKey())) {
            if (root.getLeft() == null && root.getRight() == null) {
                return null;
            } else if (root.getLeft() == null) {
                return new ImmutableBinarySearchTree(root.getRight());
            } else if (root.getRight() == null) {
                return new ImmutableBinarySearchTree(root.getLeft());
            }
            if ((root.getRight() != null) && (root.getLeft() != null)) {
                // it checks if root key will get removed then will need to merge the two sides
                // into new parent,
                // new root will be the first child of the right child and then adds what was in
                // the left side
                // to the most smallest of the right child.
                tempNode = root.getRight().getLeft();
                if (tempNode != null) {
                    tempTree = new ImmutableBinarySearchTree(tempNode.getLeft());
                    newTree = new ImmutableBinarySearchTree(tempNode);
                    newTree = newTree.add(new ImmutableBinarySearchTree(root.getLeft()), root.getLeft().getKey());
                    node = new TreeNode(root.getRight().getKey(), newTree.root, root.getRight().getRight());
                    return new ImmutableBinarySearchTree(node);
                } else {
                    node = new TreeNode(root.getRight().getKey(), root.getLeft(), root.getRight().getRight());
                    return new ImmutableBinarySearchTree(node);
                }
            }

        } else if (key.compareTo(root.getKey()) < 0) {
            // else the key is on the left side of the root, then it keeps searching till
            // gets the node
            // that will be removed and merge it's right child to be the node of the one
            // that got removed.
            tempTree = new ImmutableBinarySearchTree(root.getLeft());
            newTree = (ImmutableBinarySearchTree) tempTree.remove(key);
            if (newTree == null) {
                node = new TreeNode(root.getKey(), null, root.getRight());
                return new ImmutableBinarySearchTree(node);
            } else {
                node = new TreeNode(root.getKey(), newTree.root, root.getRight());
                return new ImmutableBinarySearchTree(node);
            }
        } else {
            // removing key that is in the right side of the tree,
            // and merge it's right child to be the node of the one that got removed.
            tempTree = new ImmutableBinarySearchTree(root.getRight());
            newTree = (ImmutableBinarySearchTree) tempTree.remove(key);
            if (newTree == null) {
                node = new TreeNode(root.getKey(), root.getLeft(), null);
                return new ImmutableBinarySearchTree(node);
            } else {
                node = new TreeNode(root.getKey(), root.getLeft(), newTree.root);
                return new ImmutableBinarySearchTree(node);
            }
        }
        return null;
    }

    @Override
    public String getAtIndex(int index) throws NoSuchElementException {
        return getAtIndex(this.root, index);
    }

    private String getAtIndex(TreeNode node, int index) throws NoSuchElementException {

        // if index is out of range then throws NoSuchElementException
        if (node == null || index == size() || index < 0) {
            throw new NoSuchElementException();
        } else if (size() == 1 || (node.getLeft() == null && index == 0)
                || (node.getRight() == null && index == size() - 1)) {
            // if root has no left and right then just return root key, or
            // if root doesn't have a left child and index is 0 it will be root key, or
            // if it doesn't have right child and index is size of tree -1 then it will be
            // root key.
            return node.getKey();
        } else if (node.getLeft() != null) {

            if (index == node.getLeft().getSize()) {
                // if left is not null and size of left = index then key will be the parent
                return node.getKey();
            } else if (index < node.getLeft().getSize()) {
                // if index is less than left size then we still need to move to the left,
                // and index will be the same since moving to the left doesn't change the order.
                return getAtIndex(node.getLeft(), index);
            } else {
                // else if left is not null and index is bigger than left side then we need to
                // move to the right,
                // and new index will be old - left size + (1 size of root)
                return getAtIndex(node.getRight(), index - (node.getLeft().getSize() + 1));
            }
        } else {
            // if left of the node is null then it should check right, and
            // index will get smaller by 1 since we moved to the right of the parent and
            // left of it is null.
            return getAtIndex(node.getRight(), index - 1);
        }

    }

    @Override
    public int getIndex(String key) throws NoSuchElementException {

        // if key is not in tree or tree is empty then throws NoSuchElementException
        if (!contains(key) || root == null) {
            throw new NoSuchElementException();
        } else {
            return getIndex(this.root, key);
        }

    }

    private static int getIndex(TreeNode node, String key) throws NoSuchElementException {

        // if node doesn't have left and right child then return index of 0 since index
        // is size - 1
        // or if node doesn't have left child only and key equals then it returns 0
        // since it is smallest string in that tree
        // or if key is null then index will always be 0 since it is always the smallest
        // key in tree
        if (node.getSize() == 1 || (node.getLeft() == null && key == node.getKey()) || key == null) {
            return 0;
        } else if ((node.getRight() == null && key == node.getKey()) || key.equals(node.getKey())) {
            // if node doesn't have right child and key equals, then returns
            // the size of left side since index biggest index,
            // is size - 1 which equals to left size of the parent.
            return node.getLeft().getSize();
        } else if (key.compareTo(node.getKey()) < 0) {
            // if key is not null and is less than parent key then recursively move to the
            // left.
            return getIndex(node.getLeft(), key);
        } else {
            // key is larger than parent then check two cases if left is null then it checks
            // recursively again,
            // and add one at the end to the returned index since it will at least be 1
            // cause of the parent
            // else if left is not null then it checks recursively till found and returns
            // left size + 1 for the parent.
            if (node.getLeft() == null) {
                return getIndex(node.getRight(), key) + 1;
            } else {
                return getIndex(node.getRight(), key) + node.getLeft().getSize() + 1;
            }
        }

    }

    @Override
    public boolean contains(String key) {
        return containsKey(this.root, key);
    }

    private static boolean containsKey(TreeNode node, String key) {

        // if node is null then key is not found
        // else it keeps checking left if key is null or smaller than parent and if it
        // equals it returns true,
        // else it will return false since it will be null. if key is larger than parent
        // then recursively checks
        // to the right of the parent.
        if (node == null) {
            return false;
        } else if (node.getKey() == key) {
            return true;
        } else if (node.getKey() != null && (key == null || key.compareTo(node.getKey()) < 0)) {
            return containsKey(node.getLeft(), key);
        } else {
            return containsKey(node.getRight(), key);
        }
    }

    @Override
    public int size() {

        if (root == null) {
            return 0;
        } else {
            return root.getSize();
        }

    }

    @Override
    public String[] keys() {
        String[] keys = new String[size()];
        return getKeys(keys, 0);
    }

    private String[] getKeys(String[] keys, int index) {

        // returns empty array if tree is empty, else it checks if index equals to size
        // then it returns the filled array
        // with at least one element, and it keeps loading elements from getAtIndex
        // until it loads everything.
        if (root == null) {
            return keys;
        } else if (size() == index) {
            return keys;
        } else {
            keys[index] = getAtIndex(index);
            return getKeys(keys, index + 1);
        }

    }

}