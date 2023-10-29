import java.util.NoSuchElementException;

/**
 * An immutable sorted set that holds string keys. None of the methods of the
 * set should ever modify the set. Any methods that would typically modify the
 * set return a new set instead.
 *
 * @author Mohammed Arab instructor Jason Heard course 2631
 */
public interface ImmutableSortedSet {

    /**
     * Returns a copy of this set with the given key added to the set. If the given
     * key is already in the set, then <code>this</code> should be returned. A
     * <code>null</code> key is acceptable.
     *
     * @param key The key to add in the new set.
     * @return The new set with the given key added or <code>this</code> if the key
     *         was already present in this set.
     */
    public ImmutableSortedSet add(String key);

    /**
     * Returns a copy of this set without the given key. If the given key is not in
     * this set, then <code>this</code> should be returned.
     *
     * @param key The key that should be removed in the returned set.
     * @return The new set without the given key or <code>this</code> if no
     *         association is present in this set.
     */
    public ImmutableSortedSet remove(String key);

    /**
     * Returns the key at the given index, throwing an exception if the index is not
     * in the range from <code>0</code> to <code>this.size() - 1</code>. A return
     * value of <code>null</code> indicates that <code>null</code> is the value at
     * the given index.
     *
     * @param index The index whose associated key is to be returned.
     * @return The associated key for the given index.
     * @throws NoSuchElementException Thrown if the given index is not in the valid
     *                                range.
     */
    public String getAtIndex(int index) throws NoSuchElementException;

    /**
     * Returns the index of the given key, throwing an exception if the key is not
     * in the set. A return value of <code>null</code> indicates that
     * <code>null</code> is associated with the given key.
     *
     * @param key The key whose index is to be returned.
     * @return The index for the given key.
     * @throws NoSuchElementException Thrown if the given key is not in the set.
     */
    public int getIndex(String key) throws NoSuchElementException;

    /**
     * Determines if the given key is in the set.
     *
     * @param key The key whose presence in the set is to be tested.
     * @return <code>true</code> if the key is in this set; <code>false</code>
     *         otherwise.
     */
    public boolean contains(String key);

    /**
     * Returns the number of keys in the set.
     *
     * @return The number of keys in the set.
     */
    public int size();

    /**
     * Returns an array with all of the keys in the set in their natural order. If
     * <code>null</code> has an associated value, then it should be first in the
     * array.
     *
     * @return An array with all of the keys in the set in their natural order.
     */
    public String[] keys();

}
