/**
 * Implementation of the LinkedListInterface
 * For all public methods, see documentation in LinkedListInterface
 * 
 * @author gtowell
 * Modified by Anna Nguyen
 * Created: April 2021
 * Last modified November 2022
 * @param <J>
 */
public class SingleLinkedList<J extends Comparable<J>> implements LinkedListInterface<J> {
        
    protected class Node<H> 
    {
	    public H data;
	    public Node<H> next;
	    public Node(H data) 
	    {
            this.data = data;
            this.next = null;
	    }	
    }

    // public int indexOf(J k) {
    //     int i = 0;
    //     Node<J> here = head;
    //     Node<J> j = new Node<J>(k);
    //     while (here.next != null){
    //         int z = j.data.compareTo(here.data);
    //         if(z==0) return i;
    //         here = here.next;
    //         i++;
    //     }
    //     return -1;
    // }

    protected Node<J> head = null;
    
    /**
     * Return the number of items in the linked list
     * @return the number of items in the list
     */
    public int size() {
        int siz = 0;
        Node<J> n = head;
        while (n != null) {
            siz++;
            n = n.next;
        }
        return siz;
    }
    /**
     * true if the list is empty
     * @return true iff the list if empty
     */
    public boolean isEmpty() {
        return head == null;
    }
     /**
     * The first item in the list
     * @return the first item in the list
     */
    @Override
    public J first() {
        return head.data;
    }
    /**
     * the last node in the list
     * @return the last node
     */
    private Node<J> lastNode() {
        Node<J> n = head;
        if (n == null)
            return null;
        while (n.next != null) {
            n = n.next;
        }
        return n;
    }
    /**
     * Add a new data item at the end of the list
     * @param c the data item to be added
     */
    @Override
    public J last() {
        Node<J> n = lastNode();
        if (n == null)
            return null;
        return n.data;
    }
    /**
     * Add a new data item at the end of the list
     * @param c the data item to be added
     */
    @Override
    public void addLast(J c) {
        Node<J> n = lastNode();
        Node<J> newnode = new Node<>(c);
        if (n == null) {
            head = newnode;
            return;
        }
        n.next = newnode;
    }
    /**
     * Add a new data item at the beginning of the list
     * @param c the item to be added
     */
    @Override
    public void addFirst(J c) {
        Node<J> newnode = new Node<>(c);
        if (head == null) {
            head = newnode;
            return;
        }
        newnode.next = head;
        head = newnode;
    }
    /**
     * Remove the data item from the beginning of the list
     * @return the data item removed (or null)
     */
    @Override
    public J removeFirst() {
        if (head == null)
            return null;
        Node<J> tmp = head;
        head = head.next;
        return tmp.data;
    }
    /**
     * Remove data item from end of list 
     * @return the data item removed (or null)
     */
    @Override
    public J removeLast() {
        if (head == null)
            return null;
        Node<J> prev = head;
        Node<J> here = head.next;
        if (here == null) {
            // only one item in list
            head = null;
            return prev.data;
        }
        while (here.next != null) {
            prev = here;
            here = here.next;
        }
        prev.next = null;
        return here.data;
    }
    /**
     * Remove a data item from the list
     * @param r the item to be removed
     * @return true if the item was int he list, and therefore was removed.
     */
    @Override
    public boolean remove(J r) {
        if (head == null)
            return false;
        Node<J> prev = null;
        Node<J> here = head;
        while (here.next != null) {
            if (here.data.compareTo(r) == 0)
                break;
            prev = here;
            here = here.next;
        }
        if (here.data.compareTo(r) == 0) {
            if (prev == null)
                head = head.next;
            else
                prev.next = here.next;
            return true;
        }
        return false;
    }

        /**
        * Determine if the list contains the provided object. 
        * @param r the object to be looked for.
        * @return true iff the object is in the linked list.
        */
        public boolean contains(J r) {
            Node<J> n = head;
            Node<J> b = new Node<>(r);
            while (n != null) {
                if (n.data.compareTo(b.data) == 0) {
                    return true;
                }
                 n = n.next;
            }
            return false;
        }

    /**
     * finds a data item from the list specified
     * @param r the item to be found
     * @return item if found in list else null
     */
        public J find(J r) {
            Node<J> here = head;
            Node<J> j = new Node<J>(r);
            while (here != null){
                if(j.data.compareTo(here.data) == 0) {
                    return here.data;
                }
                here = here.next;
            }
            return null;
        }

        /**
       * Given a linked list whose items are in sorted order,
       * add the new item, r, so that the items in the linked list are 
       * still in sorted order after the addition.  
       * @param: r  the item to be added 
       * @return true iff the addition succeeded (it will always succeed).
       **/
       public boolean addSorted(J r) {
        Node<J> n = head;
        if (head == null || r.compareTo(n.data) < 0) {
                addFirst(r);
        }
        else {
            while (n != null) {
                if (n.next != null && n.next.data.compareTo(r) > 0) {
                    Node<J> nw = new Node(r);
                    nw.next = n.next;
                    n.next = nw;
                    break;
                }
                else if (n.next == null && n.data.compareTo(r) <= 0) {
                    addLast(r);
                    break;
                }
                n = n.next;
            }
        }
        return true;
    }
    /**
     * @return everything in list
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Node<J> node = head; node != null; node = node.next) {
            sb.append(node.data.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

//     public static void main(String[] args) {
//         SingleLinkedList<Integer> a = new SingleLinkedList<>();
//         a.addFirst(1);
//         a.addLast(2);
//         a.addLast(5);
//         a.addSorted(5);
//         a.addSorted(1);
//         a.addSorted(2);
//         a.addSorted(2);
//         a.addSorted(2);
//         a.addSorted(0);
       
//         System.out.println(a.toString());
//         System.out.println(a.find(0));
//     }
}