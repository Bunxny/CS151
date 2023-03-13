/**
 * An implementation of a Ternary Heap
 * @param <K> type of key extends(has elements of) Comparable<K> 
 * @param <V> the type of value
 * @author Anna Nguyen
 * Created November 14, 2022
 */
 @SuppressWarnings("unchecked")
public class TernaryHeap<K extends Comparable<K>, V> extends BinaryHeap<K, V> {
    //constructor sets defualt size
	public TernaryHeap() {
        this(CAPACITY);
	}
	//constructor sets custom size
	public TernaryHeap(int capacity) {
        backArray = new Pair[capacity];
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
    public boolean offer(K key, V value) {
		//cannot add anymore
		if (size >= (backArray.length))
			return false;
		//otherwise add element
		int loc = size++;
        //add to last element
		backArray[loc] = new Pair<K, V>(key, value);
        //last element's parent
		int upp = (loc - 1) / 3;
		while (loc != 0) {
			//if last element less than parent
			if (0 > backArray[loc].compareTo(backArray[upp])) {
                //saves parent
				Pair<K, V> tmp = backArray[upp];
                //swap last element to parent place
				backArray[upp] = backArray[loc];
                //last place to parents element
				backArray[loc] = tmp;
                //new loc
				loc = upp;
				upp = (loc - 1) / 3;
			} 
            else {
				break;
			}
		}
        // for(int i = 0; i < 9; i++){
        //     System.out.print(backArray[i]);
        // }
        // System.out.println();
        return true;
	}

	/**
     * Removes the root(top/has no parent) of this heap.
     */
    @Override
	protected void removeTop() {
        if (size > 0) {
            // we are removing the top so the size decreases by 1
            size--;
            //The top is now replaced with the last element
            backArray[0] = backArray[size];
            //the bottom is then set to null since it went to the top
            backArray[size]=null;
            int parentLoc=0; 
            while (true) {
                //find which child is smaller and get the location
                int bestChildLoc;
                //child locations *3 bc ternary heap parentloc*3=nextgeneration
                //+1 for first child +2 second + 3 third
                int childLoc1 = parentLoc*3+1; 
                int childLoc2 = parentLoc*3+2;
                int childLoc3 = parentLoc*3+3; 
                //when we reached the end
                if (childLoc1>=size) 
                    break;
                //there is only one child and that is child1
                if (childLoc2>=size) {
                    bestChildLoc=childLoc1; 
                }
                //there are only 2 children so compare the
                else if (childLoc3>=size) {
                    //Still want to compare child 1 and child 2
                    int cmp2 = backArray[childLoc1].compareTo(backArray[childLoc2]);
                    //update best child
                    if (cmp2<=0)
                        bestChildLoc=childLoc1;
                    else //if child1 is bigger
                        bestChildLoc=childLoc2;
                }
                else {
                    
                    //compare child1 to child2
                    //- if child2 is bigger + if child1 is bigger 0 if ==
                    int cmp = backArray[childLoc1].compareTo(backArray[childLoc2]);
                    //update best child
                    //if child1 is less than child2
                    if (cmp <= 0)
                        bestChildLoc=childLoc1;
                    else //child1 bigger
                        bestChildLoc=childLoc2;
                    //System.out.println("Best child so far is " + backArray[bestChildLoc]);
                    //compare best child to child3 and update if needed
                    int lastcmp = backArray[childLoc3].compareTo(backArray[bestChildLoc]);
                    //child 3 less than bestchild
                    if (lastcmp < 0)
                        bestChildLoc=childLoc3;
                    
                }
                //System.out.println("Parent location is " + parentLoc);
                //System.out.println("Best child "+ backArray[bestChildLoc]);
                //if bestchild is less than parent it will need to be swapped
                //and updated parent will need to find a place till heap is
                //correctly re ordered small(top) to big(bottom)
                if (0 >= backArray[bestChildLoc].compareTo(backArray[parentLoc])) {
                    //saves bestchild
                    Pair<K,V> tmp = backArray[bestChildLoc];
                    //parent element is swapped with bestchild since child has found its place
                    backArray[bestChildLoc] = backArray[parentLoc];
                    //continuation of swap
                    backArray[parentLoc] = tmp;
                    //continue reordering
                    parentLoc=bestChildLoc;
                }
                else { //if bestchild is larger than parent, parent is in right place
                    break;
                }
            }
        }
        //nothing to remove
        else
        return;
    }

	public static void main(String[] args) {
		TernaryHeap<Integer, String> pq = new TernaryHeap<>();
        //if this test is correct the offer method rearranges new adds correctly with high values at the
        //bottom and lower values at the top and poll is grabbing all the top values to remove and show
        //the removetop() method is used in poll so this test also shows that the top is being correctly
        //removed and reordered
		pq.offer(1, "Once");
		pq.offer(5, "time");
		pq.offer(2, "upon");
        pq.offer(3, "a");
		pq.offer(10, "this");
		pq.offer(15, "dog");
        pq.offer(17, "ate");
        pq.offer(101, "theEnd");
		pq.offer(19, "a");
		pq.offer(100, "blueberry");
        System.out.println("Size after offers: " + pq.size() + "\n" + "is the heap empty?: " + pq.isEmpty());
        System.out.print( "first element should be Once: " + pq.peek());
        int stop = pq.size();
        System.out.print("\n"+ "Should print: Once upon a time this dog ate a blueberry theEnd");
        System.out.print("\n" + "Prints: ");
        for (int i = 0; i < stop; i++) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println("\nSize after polling everything: " + pq.size() + "\n" + "is the heap empty?: " + pq.isEmpty() + "\n");
        //heap is empty. test if methods still work when empty
        System.out.println("when heap is empty peek will: " + pq.peek());
            System.out.println("when heap is empty poll will: " + pq.poll());
            pq.removeTop();
            System.out.println("when heap is empty and we remove top peek will still be null: " + pq.peek() + "\n");
        //heap is full test. if methods still works when full
        TernaryHeap<Integer, String> full = new TernaryHeap<>(3);
        full.offer(0, "to");
		full.offer(2, "be");
		full.offer(3, "continued");
        full.offer(4, "overfull");
        System.out.println( "Heap capacity is 3 and four items are offered the fourth one should be null");
        for (int i = 0; i < 4; i++) {
            System.out.print(full.poll() + " ");
        }
        System.out.println("\n");
        //heap has same keys
        full.offer(1, "to");
		full.offer(1, "be");
		full.offer(1, "continued");
        int end = full.size();
        System.out.println("Test shows if keys have same values each will be polled in order of when offered");
        for (int i = 0; i < end; i++) {
            System.out.print(full.poll() + " ");
        }
	}

}