import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;

/**
 * An incomplete Hashtable using probing. The get and put functions both use a 
 * private function named find, for which the documentation is incomplete.
 * @param <K> the type of key
 * @param <V> the type of value 
 * @author gtowell 
 * Created:  Sep 27, 2020 
 * Modified Oct 2, 2020
 * Modified: Sep 27, 2021
 */
public class ProbeHTInc<K, V> implements Map151Interface<K, V> {

    /**
     * Small inner class to group together key,value pairs
     */
    protected class Pair<L, W> {
        /** The key, cannot be changed */
        final L key;
        /**
         * The value. It can be changed as a second put with the key will change the
         * value
         */
        W value;

        /**
         * Initialize the node
         */
        public Pair(L ll, W ww) {
            key = ll;
            value = ww;
        }

        /** Print the node, and all subsequent nodes in the linked list */
        public String toString() {
            return "<" + key + ", " + value + ">";
        }
    }

    /** A Constant .. One of the cases in which static are acceptable
     * This one specifies the maximum number of tombstones allowed before 
     * rehashing for tombstone accumulation
     */
    /** When the hashtable needs to grow, by what factor should it grow */
    private static final double GROWTH_RATE = 2.0;
    /** How full the table should be before initiating rehash/growth */
    private static final double MAX_OCCUPANCY = 0.60;
    /** The default size of the backing array */
    private static int DEFAULT_CAPACITY = 1009;
   /** The array in which the hashtable stores data */
    private Pair<K, V>[] backingArray;
    /** The number of active items in the hashtable */
    private int itemCount;
    
 
    /** Default initialization */
    public ProbeHTInc() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize a hashtable of the given size
     * 
     * @param size the size of the hashtable to create
     */
    @SuppressWarnings("unchecked")
    public ProbeHTInc(int size) {
        // Cannot make an array in which you mention a parameterized type.
        // So just make the generic array. This is a narrowing cast so it does not
        // even need to be explicitly case.
        backingArray = new Pair[size];
        itemCount = 0;
     }

     private int h(K k) {
         return objectHasher(k).mod(BigInteger.valueOf(backingArray.length)).intValue();
     }
    
    /**
     * The hash function. Just uses the java object hashvalue. 
     * @param key the Key to be hashed
     * @return the hash value
     */
    private BigInteger objectHasher(Object ob) {
        return stringHasher(ob.toString());
    }

    /**
     * Implemets Horner's on strings.
     * Since every object can be translated into a string This can be run
     * on an arbitrary object with no loss of generality.
     * @param ss the string to generate a hash value for
     * @return the hash value
     */
    private BigInteger stringHasher(String ss) {
        BigInteger mul = BigInteger.valueOf(23);
        BigInteger ll = BigInteger.valueOf(0);
        for (int i=0; i<ss.length(); i++) {
            ll = ll.multiply(mul);
            ll = ll.add(BigInteger.valueOf(ss.charAt(i)));
        }
        return ll;
    }

    /**
     * The number of active items in the hashtable
     * @return The number of active items in the hashtable
     */
    public int size() {
        return itemCount;
    }

    /**
     * Given a key, it is hashed and a value is found for it by going through
     * the hash table. if the key does not exist in the hashtable null is returned.
     * @param key
     * @return pair
     */
    private Pair<K, V> find(K key) {
        //Hashes the key given
        int hashLoc = h(key);
        int q = 0;
        // go throughs the hashtable and finds key based on 
        // where the key would be hashed and hashed again until a match is found 
        while (true) {
            //starts check at hashed key location and goes to the next index
            Pair<K, V> pair = backingArray[(hashLoc + q) % backingArray.length];
            //if key is not there return null
            if (pair == null)
                return null;
            //key match is found
            if (pair.key.equals(key))
                return pair;
            q++;
        }
    }
    /**
     * Add a key-value pair to the hashtable. If the key is already in the
     * hashtable, then the old value is replaced. Otherwise this adds a new
     * key-value pair
     * Be sure to update itemCount as needed.
     * 
     * @param key   the key
     * @param value the value
     */
    public void put(K key, V value) {
        //If hashtable is too full (more than 60% of the array) to aviod collisions the 
        // items in the array will be rehashed into a bigger hashtable
        if (itemCount > backingArray.length * MAX_OCCUPANCY) {
            //array doubles in size
            rehash((int)(backingArray.length*GROWTH_RATE));
        }
        Pair<K, V> foundPair = find(key);
        //if the key is not in the table add it
        if (foundPair == null) {
            //hash key
            int hashLoc = h(key);
            int q = 0;
            //if hash key placment is occupied move on to next index of array till 
            //find place to put new value
            while (backingArray[(hashLoc + q) % backingArray.length] != null) {
                q++;
            }
            //q + hashloc contains where new key should be placed
            //puts in key value into hashtable
            backingArray[(hashLoc + q) % backingArray.length] = new Pair<>(key, value);
            //add to item count new pair is made
            itemCount++;
            //if key is already in the table updates hashtable value of key to inputted value
        } else {
            foundPair.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * Rehash the current table. This should be done rarely as it is expensive
     * @param newSize the size of the table after rehashing
     */
    private void rehash(int newSize) {
        System.out.println("Reshashing to " + newSize);
        Pair<K, V>[] oldArray = backingArray;
        itemCount = 0;
        backingArray = new Pair[newSize];
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null) {
                put(oldArray[i].key, oldArray[i].value);
            }
        }
    }

    /**
     * Get the value associated with the key
     * @param key the key whose value is sought
     * @return the associated value, or null
     */
    public V get(K key) {
        //finds key value pair from input key
        Pair<K, V> pair = find(key);
        //Key doesnt exist returns null
        if (pair==null)
            return null;
        //key exist return value of input key
        return pair.value;
    }

    

    @Override
    /**
     * Does the hashtable contain the key
     * @param key the key
     * @return true iff the key is in the hashtable
     */
    public boolean containsKey(K key) {
        return null != get(key);
    }

    @Override
    /**
     * The complete set of keys active in the hashtable.
     * @return a set containing all of the keys in the hashtable
     */
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        for (Pair<K,V> pr : backingArray) {
            if (pr!=null) {
                set.add(pr.key);
            }
        } 
        return set;
    }


    /**
     * A very basic set of tests for the class
     */
    public static void main(String[] args) {
        ProbeHTInc<String, String> phi = new ProbeHTInc<>(10);
        phi.put("A", "B");
        phi.put("C", "D");
        phi.put("C1", "D");
        phi.put("C2", "D");
        phi.put("C3", "D");
        phi.put("C4", "D");
        phi.put("C5", "D");
        phi.put("C6", "D");
        phi.put("C7", "D");
        phi.put("C8", "D");
        phi.put("C9", "D");
        phi.put("C10", "D10");
        System.out.println(phi.get("A"));
        System.out.println(phi.get("B"));
        System.out.println(phi.get("C10"));
    }

}
