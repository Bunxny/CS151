/**
 * An implementation of a binary Heap
 * @param <K> type of key extends(has elements of) Comparable<K> 
 * @param <V> the type of value
 * @author gtowell
 * Created April 6, 2020
 * Modified: April 12, 2021
 * Modified: April 4, 2022
 */
@SuppressWarnings("unchecked")
public class BinaryHeap<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V>
{

	protected static final int CAPACITY = 1032;
	protected Pair<K,V>[] backArray;
    protected int size;

	//constructor sets defualt size
	public BinaryHeap() {
        this(CAPACITY);
	}
	//constructor sets custom size
	public BinaryHeap(int capacity) {
        backArray = new Pair[capacity];
	}

	/**
     * The number of items in the heap
     * @return the number of items
     */
    @Override
    public int size()
    {
		return size;
    }

	/**
     * Return true if the heap is empty
     * @return true if the heap is empty
     */
    @Override
    public boolean isEmpty()
    {
		return size==0;
    }

	/**
     * Inserts the specified element into this heap if it is possible to do so
     * immediately without violating capacity restrictions, returning true upon
     * success and false on failure
     * 
     * @param k the key of the element to be added
     * @param v the value of the element 
     * @return true on success, false on failure
     */
    @Override
    public boolean offer(K key, V value)
	{
		//cannot add anymore
		if (size >= (backArray.length - 1))
			return false;
		//otherwise add element
		int loc = size++; //put at the end of the array
		backArray[loc] = new Pair<K, V>(key, value);
		int upp = (loc - 1) / 2; //parent node
		while (loc != 0) {
			//if upper array more than added
			if (0 > backArray[loc].compareTo(backArray[upp])) { //
				Pair<K, V> tmp = backArray[upp];
				backArray[upp] = backArray[loc];
				backArray[loc] = tmp;
				loc = upp;
				upp = (loc - 1) / 2;
			} else {
				break;
			}
		}
		return true;
	}

	/**
     * Removes the root(top/has no parent) of this heap.
     */
	protected void removeTop()
    {

		// we are removing the top so the size decreases by 1
		size--;
		//The top is now replaced with the last element
		backArray[0] = backArray[size];
		//the bottom is then set to null since it went to the top
		backArray[size]=null;
		int parentLoc=0; 
		while (true) 
		{
			//varable for finding which child is smaller and get the location
	    	int bestChildLoc;
			//child locations *2 bc binary heap parentloc*2=nextgeneration
			//+1 for first child +2 second
	    	int childLoc1 = parentLoc*2+1; 
	    	int childLoc2 = parentLoc*2+2; 
			//when we reached the end
			//there are no children so we are done
	    	if (childLoc1>=size) 
				break;
			//there is only one child so the best child is child1
	    	if (childLoc2>=size)
	    	{
				bestChildLoc=childLoc1; 
	    	}
	    	else
	    	{
				//compare child1 to child2 to get best child
				//- if child2 is bigger + if child1 is bigger 0 if ==
				int cmp = backArray[childLoc1].compareTo(backArray[childLoc2]);
				//update best child to lowest
				if (cmp<=0)
		    		bestChildLoc=childLoc1;
				else //if child1 is bigger
		    		bestChildLoc=childLoc2;
			}
			//if bestchild is smaller than parent it will need to be swapped
			//and updated parent will need to find a place till heap is
			//correctly re ordered small(top) to big(bottom)
	    	if (0 > backArray[bestChildLoc].compareTo(backArray[parentLoc]))
	    	{
				//saves bestchild
				Pair<K,V> tmp = backArray[bestChildLoc];
				//parent element is swapped with bestchild since child has found its place
				backArray[bestChildLoc] = backArray[parentLoc];
				//continuation of swap child found place
				backArray[parentLoc] = tmp;
				//now we continue search for parent location till the heap order is restored
				parentLoc=bestChildLoc;
	    	}
	    	else { //if bestchild is larger than parent we are done
				break;
	    	}
		}
		// for(int i = 0; i < 9; i++){
        //     System.out.print(backArray[i]);
        // }
		// System.out.println();
    }


    /**
     * Retrieves and removes the root(has no parent) of this heap.
     * 
     * @return the removed element, or null if is empty
     */
	@Override
	public V poll() {
		// System.out.println("Going to poll!");
		// for(int i = 0; i < 9; i++){
        //     System.out.print(backArray[i]);
        // }
		if (isEmpty())
			return null;
		Pair<K,V> tmp = backArray[0];
		removeTop();
		return tmp.theV;
	}

	/**
     * Retrieves, but does not remove, the root of this heap. 
     * 
     * @return the element at the root or null if is empty
     */
	@Override
	public V peek() {
		if (isEmpty())
			return null;
		return backArray[0].theV;
	}


	public static void main(String[] args) {
		BinaryHeap<Integer, String> pq = new BinaryHeap<>(CAPACITY);
		pq.offer(1, "Jane");
		pq.offer(10, "WET");
		pq.offer(5, "WAS");
		

		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println();
		
		pq = new BinaryHeap<>(CAPACITY);
		pq.offer(2, "Jane");
		pq.offer(1, "WET");
		pq.offer(5, "WAS");
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
	}

}