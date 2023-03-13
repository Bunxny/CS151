/**
 * An implementation of a stack using an underlying array
 * 
 * @param <K> Generic
 * @author gtowell 
 * Created: Feb 14, 2020
 * Modified: Oct 6, 2020
 * Modified: Mar 24, 2021 by gtowell
 * Modified: Feb 27, 2022 by gtowell
 * Modified: Oct 7, 2022 by gtowell
 */
public class ArrayStack<K> implements Stack151<K> {
    /** The default capacity of the stack */
    private static final int DEFAULT_CAPACITY = 40;
    /** The number of items in the stack */
    private int size; // alternative private int end = -1;
    /** The array used to hold the stack items */
    private K[] uArray;

    /**
     * Create a stack with the default capacity
     */
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Create a stack with the given capacity
     * 
     * @param capacity the max number items the stack can hold.
     */
    @SuppressWarnings("unchecked") // do not compain about casting to K[]
    public ArrayStack(int capacity) {
        size = 0;
        uArray = (K[]) new Object[capacity];
    }

    /**
     * Tests if this stack is empty.
     * 
     * @return true if and only if this stack contains no items; false otherwise.
     */
    @Override
    public boolean empty() {
        return size == 0;
    }

    

    /**
     * Pushes an item onto the top of this stack.
     *  Returns null if the element could
     * not be added.
     * 
     * @param e the element to be added to the stack
     * @return the added element. todo
     */
    @Override
    public K push(K e) {
        if (size == uArray.length){
            return null;
        }
        
        uArray[size] = e;
        size++;
        return e;
    }

    /**
     * Looks at the object at the top of this stack without removing it from the
     * stack. todo
     * 
     * @return the item at the top of the stack
     */
    @Override
    public K peek() {
        if (empty()) {
            return null;
        }
        K temp = uArray[size - 1];
        return temp;
    }

    /**
     * Removes the object at the top of this stack and returns that object as the
     * value of this function. todo
     * 
     * @return The removed object
     */
    @Override
    public K pop() {
        if (empty()){
            return null;
        }       
        K popped = uArray[size-1];
        uArray[size-1] = null;
        size--;
        return popped;
    }

    /**
     * Gives the number of items in the stack
     * 
     * @return The number of items in the stack.
     */
    @Override
    public int size() {
        return size;
    }

    // public  K[] deepClone(){
    //     K[] tempArray = uArray = (K[]) new Object[size];
    //     for (int i = size-1; i >= 0; i--) {
    //         tempArray[i] = uArray[i];
    //     } 
    //     return tempArray;
    // };
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = size-1; i >= 0; i--) {
            if (i == size - 1) {
                sb.append("TOP    | ");
            } else if (i == 0) {
                sb.append("BOTTOM | ");
            } else {
                sb.append("       | ");
            }
            sb.append(uArray[i].toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Illustrate stack operations and usage. 
     * This will only work correctly when the implemetation is completed
     * @param args
     */
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(5);
        System.out.println("Push 5: s" + stack);   // {5}
        stack.push(3);
        System.out.println("Push 3: s" + stack);
        System.out.println("Size: " + stack.size()); // {5,3}
        System.out.println("Pop:" + stack.pop() + " s" + stack); // {5}
        System.out.println(stack.empty()); // false
        System.out.println("Pop:" + stack.pop() + " s" + stack); //{}
        System.out.println(stack.empty()); // true
    }



    @Override
    public void clear() {
        size = 0;
    }

    
}
