/* 
   * Desc:
   * This program is made to overide methods of List151Impl for sorting data
   * in the array list
   
   * @author Anna Nguyen
   * Last Modified: Sept 28, 2022
   */
public class SortedArrayList<Y> extends List151Impl<Y> {
    //initial capacity
    public SortedArrayList() {
       super(100);
	}
@SuppressWarnings("unchecked")
//A private method named grow that increases the size of the array 
//underlying the the data structure by a factor of 2. 
//also  print an informative message every time growth occurs.
    public void grow() {
        int sizeDoubled = arra.length * 2;
        //updated array capacity
        Y []array = (Y[]) new Object[sizeDoubled];
        for (int i = 0; i < arra.length; i++) {
            array[i] = arra[i];
        }
        arra = array;

    }
//overide the method add so that it uses the grow method appropriately.
//The add method should now never return false to indicate that there 
//is no space to add a new item.
@Override
	public boolean add(Y t) {
		if (count >= arra.length - 1) {
            grow();
        }
    //Further change the method add so that items are added such that 
    //the items in the SortedArrayList are kept in sorted order.
        for (int i = 0; i < arra.length; i++) {
            if (arra[i] != null){
                //if t is more than arra[i] will be higher than 0
                // if not than lower, if the same will be 0
                int num = (arra[i].toString()).compareTo(t.toString());
                    if ((num <= 0) && (i == arra.length - 1)) {
                            arra[i] = t;
                            break;
                    }
                    else if (num > 0) {
                        count++;
                        for (int j = (count - 1); j >= i; j--) {
                            arra[j + 1] = arra[j];
                        }
                        arra[i]=t;
                        return true;
                    }
                }
        }
        arra[count] = t;
        count++;
        return true;
	}
    //searches through array starting from the middle and returning 
    //zipcode info
    @Override
	public int indexOf(Y t) {
            //int find = (String.valueOf(t)).compareTo(String.valueOf(arra[count/2]));
            int find = (t.toString()).compareTo(arra[count/2].toString());
            if (find < 0) {
                for (int j = arra.length/2; j > 0; j--) {
                    if (arra[j].equals(t)){
                        return j;
                    }
                }
                return -1;
            }
            else if (find > 0) {
                for (int k = arra.length/2; k < count-1; k++) {
                    if (arra[k].equals(t)){
                        return k;
                    }
                }
                return -1;
            }
            else if (find == 0)
				return arra.length/2;
            return -1;
	}

    //throws an exception because method can not be used due to
    //risk of disorting list
    @Override
	public boolean add(int index, Y t) throws IndexOutOfBoundsException {
		throw new IndexOutOfBoundsException("NO, you may not use this method" +
        " it could break sorting order this method has been blocked");
	}
}