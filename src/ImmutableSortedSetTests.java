import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests for an immutable sorted set implementation.
 *
 * @author Jason Heard
 * @version 1.0
 */
public class ImmutableSortedSetTests {

    /**
     * Constructs a new tree to test. This should be updated to construct your tree
     * class.
     *
     * @return The tree implementation to test.
     */
    public static ImmutableSortedSet makeTree() {
        return new ImmutableBinarySearchTree();
    }

    /**
     * Tests that the add operation works to the left.
     */
    @Test
    public void addWorksOnLeft() {
        ImmutableSortedSet tree1 = makeTree().add("George").add("Fred").add("Bob").add("Alice").add(null);

        assertTrue(tree1.contains(null));
        assertTrue(tree1.contains("Alice"));
        assertTrue(tree1.contains("Bob"));
        assertTrue(tree1.contains("Fred"));
        assertTrue(tree1.contains("George"));
    }

    /**
     * Tests that the add operation works to the right.
     */
    @Test
    public void addWorksOnRight() {
        ImmutableSortedSet tree1 = makeTree().add(null).add("Alice").add("Bob").add("Fred").add("George");

        assertTrue(tree1.contains(null));
        assertTrue(tree1.contains("Alice"));
        assertTrue(tree1.contains("Bob"));
        assertTrue(tree1.contains("Fred"));
        assertTrue(tree1.contains("George"));
    }

    /**
     * Tests that the add operation with no changes returns <code>this</code>.
     */
    @Test
    public void addNoChangeReturnsThis() {
        ImmutableSortedSet tree1 = makeTree().add("Fred").add("Alice").add("Bob").add("George");

        ImmutableSortedSet tree2 = tree1.add("Alice").add("Bob").add("Fred").add("George");

        assertSame(tree1, tree2);
    }

    /**
     * Tests that the get at index operation fails appropriately with an empty tree.
     */
    @Test(expected = NoSuchElementException.class)
    public void getAtIndexWithEmptyTree() {
        ImmutableSortedSet emptyTree = makeTree();

        emptyTree.getAtIndex(1);
    }

    /**
     * Tests that the get at index operation fails appropriately with an invalid
     * index.
     */
    @Test(expected = NoSuchElementException.class)
    public void getAtIndexWithInvalidIndex() {
        ImmutableSortedSet tree = makeTree().add("Alice").add("Bob").add("Fred").add("George").add(null);

        tree.getAtIndex(10);
    }

    /**
     * Tests that the get at index operation works with a non-empty tree and values
     * in the tree, but no null values.
     */
    @Test
    public void getAtIndexNoNulls() {
        ImmutableSortedSet tree = makeTree().add("Alice").add("Bob").add("Fred").add("George");

        assertEquals("Alice", tree.getAtIndex(0));
        assertEquals("Bob", tree.getAtIndex(1));
        assertEquals("Fred", tree.getAtIndex(2));
        assertEquals("George", tree.getAtIndex(3));

//        ImmutableSortedSet tree = makeTree().add("h").add(null).add("d").add("b").add("c").add("a").add("e").add("f")
//                .add("g").add("h").add("i").add("j");
//        assertEquals(null, tree.getAtIndex(0));
//        assertEquals("a", tree.getAtIndex(1));
//        assertEquals("g", tree.getAtIndex(7));
//        assertEquals("j", tree.getAtIndex(10));

    }

    /**
     * Tests that the get index operation fails appropriately with an empty tree.
     */
    @Test(expected = NoSuchElementException.class)
    public void getIndexWithEmptyTree() {
        ImmutableSortedSet emptyTree = makeTree();

        emptyTree.getIndex("Nope");
    }

    /**
     * Tests that the get index operation fails appropriately with a non-existent
     * key.
     */
    @Test(expected = NoSuchElementException.class)
    public void getIndexWithNonExistentKey() {
        ImmutableSortedSet tree = makeTree().add("Alice").add("Bob").add("Fred").add("George");

        tree.getIndex("Nope");
    }

    /**
     * Tests that the get indx operation works with a non-empty tree and values in
     * the tree.
     */
    @Test
    public void getIndexNoNulls() {
//        ImmutableSortedSet tree = makeTree().add("Fred").add("Bob").add("Alice").add("George");
//
//        assertEquals(0, tree.getIndex("Alice"));
//        assertEquals(1, tree.getIndex("Bob"));
//        assertEquals(2, tree.getIndex("Fred"));
//        assertEquals(3, tree.getIndex("George"));

        ImmutableSortedSet tree = makeTree().add("f").add("d").add("e").add("c").add("a").add("b").add("i").add("k")
                .add("g").add("h");

        assertEquals(0, tree.getIndex("a"));
        assertEquals(1, tree.getIndex("b"));
        assertEquals(5, tree.getIndex("f"));
        assertEquals(6, tree.getIndex("g"));
        assertEquals(8, tree.getIndex("i"));
        assertEquals(9, tree.getIndex("k"));
        ImmutableSortedSet tree2 = makeTree().add("f").add("d").add("e").add("c").add("a").add("b").add("i").add("k")
                .add(null).add("h");
        assertEquals(0, tree2.getIndex(null));
        assertEquals(1, tree2.getIndex("a"));
        assertEquals(2, tree2.getIndex("b"));
        assertEquals(6, tree2.getIndex("f"));
        // assertEquals(6, tree.getIndex("g"));
        assertEquals(8, tree2.getIndex("i"));
        assertEquals(9, tree2.getIndex("k"));
        ImmutableSortedSet tree3 = makeTree().add(null);
        assertEquals(0, tree3.getIndex(null));

    }

    /**
     * Tests that the contains operation returns <code>false</code> with an empty
     * tree.
     */
    @Test
    public void containsWithEmptyTree() {
        ImmutableSortedSet emptyTree = makeTree();

        assertFalse(emptyTree.contains("Fred"));
    }

    /**
     * Tests that the contains operation returns the correct values with a non-empty
     * tree with no null keys.
     */
    @Test
    public void containsNoNulls() {
        ImmutableSortedSet tree = makeTree().add("Alice").add("Bob").add("Fred").add("George");
        assertFalse(tree.contains("Dexter"));
        assertFalse(tree.contains("Sean"));
        assertTrue(tree.contains("Alice"));
        assertTrue(tree.contains("Bob"));
        assertTrue(tree.contains("Fred"));
        assertTrue(tree.contains("George"));
    }

    /**
     * Tests that the size operation returns zero with an empty tree.
     */
    @Test
    public void sizeWithEmptyTree() {
        ImmutableSortedSet emptyTree = makeTree();

        assertEquals(0, emptyTree.size());
    }

    /**
     * Tests that the size operation returns the correct value with a non-empty
     * tree.
     */
    @Test
    public void sizeComplexTree() {
        ImmutableSortedSet tree = makeTree().add("Alice").add("Bob").add("Fred").add("George").add(null);

        assertEquals(5, tree.size());
    }

    /**
     * Tests that the size operation returns the correct value with a modified tree.
     */
    @Test
    public void sizeModifiedTree() {
        ImmutableSortedSet tree = makeTree().add("Bob").add("Alice").add("Fred").add("George").add("Anita");

        ImmutableSortedSet tree2 = tree.add("Bob").add("Mary").remove("Alice").remove("Anita");

        assertEquals(5, tree.size());
        assertEquals(4, tree2.size());
    }

    /**
     * Tests that the remove operation works on a leaf node (without breaking the
     * tree).
     */
    @Test
    public void removeWorksOnLeaf() {
//        ImmutableSortedSet tree1 = makeTree().add("George").add("Fred").add("Bob").add("Alice");
//
//        assertTrue(tree1.contains("Alice"));
//        assertTrue(tree1.contains("Bob"));
//        assertTrue(tree1.contains("Fred"));
//        assertTrue(tree1.contains("George"));
//
//        ImmutableSortedSet tree2 = tree1.remove("Alice");
//
//        assertFalse(tree2.contains("Alice"));
//        assertTrue(tree2.contains("Bob"));
//        assertTrue(tree2.contains("Fred"));
//        assertTrue(tree2.contains("George"));
        ImmutableSortedSet tree1 = makeTree().add("h").add("d").add("f").add("b").add("e").add("g").add("a").add("c")
                .add("k").add("l").add("j").add("i");
        assertTrue(tree1.contains("a"));
        assertTrue(tree1.contains("b"));
        assertTrue(tree1.contains("c"));
        assertTrue(tree1.contains("d"));
        assertTrue(tree1.contains("e"));
        assertTrue(tree1.contains("f"));
        assertTrue(tree1.contains("g"));
        assertTrue(tree1.contains("h"));
        assertTrue(tree1.contains("i"));
        assertTrue(tree1.contains("j"));
        assertTrue(tree1.contains("k"));
        assertTrue(tree1.contains("l"));
        ImmutableSortedSet tree2 = tree1.remove("d").remove("f").remove("h");

        assertTrue(tree2.contains("a"));
        assertTrue(tree2.contains("b"));
        assertTrue(tree2.contains("c"));
        assertFalse(tree2.contains("d"));
        assertTrue(tree2.contains("e"));
        assertFalse(tree2.contains("f"));
        assertTrue(tree2.contains("g"));
        assertFalse(tree2.contains("h"));
        assertTrue(tree2.contains("i"));
        assertTrue(tree2.contains("j"));
        assertTrue(tree2.contains("k"));
        assertTrue(tree2.contains("l"));

    }

    /**
     * Tests that the remove operation works on a node with one child (without
     * breaking the tree).
     */
    @Test
    public void removeWorksOnOneChild() {
        ImmutableSortedSet tree1 = makeTree().add("Alice").add("Bob").add("Fred").add("George");

        assertTrue(tree1.contains("Alice"));
        assertTrue(tree1.contains("Bob"));
        assertTrue(tree1.contains("Fred"));
        assertTrue(tree1.contains("George"));

        ImmutableSortedSet tree2 = tree1.remove("Bob");

        assertTrue(tree1.contains("Alice"));
        assertFalse(tree2.contains("Bob"));
        assertTrue(tree2.contains("Fred"));
        assertTrue(tree2.contains("George"));
//        ImmutableSortedSet tree1 = makeTree().add("b").add("a");
//        assertTrue(tree1.contains("b"));
//        assertTrue(tree1.contains("a"));
//        ImmutableSortedSet tree2 = tree1.remove("a");
//        assertTrue(tree1.contains("b"));
//        assertFalse(tree2.contains("a"));
    }

    /**
     * Tests that the remove operation returns <code>this</code> when no changes are
     * required.
     */
    @Test
    public void removeNoChangeReturnsThis() {
        ImmutableSortedSet tree1 = makeTree().add("Fred").add("Alice").add("Bob").add("George");

        ImmutableSortedSet tree2 = tree1.remove("Zara").remove("Anita").remove("Mary");

        assertSame(tree1, tree2);
    }

    /**
     * Tests that the remove operation works on a node with two children (without
     * breaking the tree).
     */
    @Test
    public void removeWorksOnTwoChildren() {
        ImmutableSortedSet tree1 = makeTree().add("Fred").add("Alice").add("Bob").add("George");

        assertTrue(tree1.contains("Alice"));
        assertTrue(tree1.contains("Bob"));
        assertTrue(tree1.contains("Fred"));
        assertTrue(tree1.contains("George"));

        ImmutableSortedSet tree2 = tree1.remove("Fred");

        assertTrue(tree2.contains("Alice"));
        assertTrue(tree2.contains("Bob"));
        assertFalse(tree2.contains("Fred"));
        assertTrue(tree2.contains("George"));

//        ImmutableSortedSet tree1 = makeTree().add("c").add("d");
//        ImmutableSortedSet tree2 = tree1.remove("d");
//        assertTrue(tree2.contains("c"));

//        ImmutableSortedSet tree1 = makeTree().add("1").add("0").add("3").add("2").add("4");
//        assertTrue(tree1.contains("0"));
//        assertTrue(tree1.contains("1"));
//        assertTrue(tree1.contains("2"));
//        assertTrue(tree1.contains("3"));
//        assertTrue(tree1.contains("4"));
//        ImmutableSortedSet tree2 = tree1.remove("1");
//        assertTrue(tree2.contains("0"));
//        assertFalse(tree2.contains("1"));
//        assertTrue(tree2.contains("2"));
//        assertTrue(tree2.contains("3"));
//        assertTrue(tree2.contains("4"));
    }

    /**
     * Tests that the keys operation works on an empty tree.
     */
    @Test
    public void keysWithEmptyTree() {
        ImmutableSortedSet emptyTree = makeTree();

        assertArrayEquals(new String[] {}, emptyTree.keys());
    }

    /**
     * Tests that the keys operation works on a tree of size one.
     */
    @Test
    public void keysWithSingleton() {
        ImmutableSortedSet emptyTree = makeTree();
        ImmutableSortedSet singleton = emptyTree.add("Alice");

        assertArrayEquals(new String[] {}, emptyTree.keys());
        assertArrayEquals(new String[] { "Alice" }, singleton.keys());
        // ensure it works twice
        assertArrayEquals(new String[] {}, emptyTree.keys());
        assertArrayEquals(new String[] { "Alice" }, singleton.keys());
    }

    /**
     * Tests that the keys operation works on a non-empty tree.
     */
    @Test
    public void keysNoNulls() {
        ImmutableSortedSet tree = makeTree().add("Alice").add("Bob").add("Fred").add("George");

        assertArrayEquals(new String[] { "Alice", "Bob", "Fred", "George" }, tree.keys());
        // ensure it works twice
        assertArrayEquals(new String[] { "Alice", "Bob", "Fred", "George" }, tree.keys());
    }

    /**
     * Tests that the keys operation works on a modified tree.
     */
    @Test
    public void keysModifiedTree() {
        ImmutableSortedSet tree = makeTree().add("Bob").add("Alice").add("Fred").add("George").add("Anita");

        ImmutableSortedSet tree2 = tree.add("Bob").add("Mary").remove("Alice");

        assertArrayEquals(new String[] { "Alice", "Anita", "Bob", "Fred", "George" }, tree.keys());
        assertArrayEquals(new String[] { "Anita", "Bob", "Fred", "George", "Mary" }, tree2.keys());
        // ensure it works twice
        assertArrayEquals(new String[] { "Alice", "Anita", "Bob", "Fred", "George" }, tree.keys());
        assertArrayEquals(new String[] { "Anita", "Bob", "Fred", "George", "Mary" }, tree2.keys());
    }

    private static class BadTree implements ImmutableSortedSet {

        @Override
        public ImmutableSortedSet add(String key) {
            return null;
        }

        @Override
        public ImmutableSortedSet remove(String key) {
            return null;
        }

        @Override
        public String getAtIndex(int index) throws NoSuchElementException {
            return null;
        }

        @Override
        public int getIndex(String key) throws NoSuchElementException {
            return 0;
        }

        @Override
        public boolean contains(String key) {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public String[] keys() {
            return null;
        }

    }

}
