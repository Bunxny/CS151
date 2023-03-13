/* 
   * Desc:
  A program that uses last In, First Out accounting; using a stack. 
  on every purchase of one product the product is pushed to the top
  of  a stack. It sells as much from the top of the stack as is required
  and adjust the amount on the top of the stack. if sold items are more
  than what is on top of the stack sell everything from the top of the stack, 
  pop the top item, then sell from the newly top item (repeat as needed to complete 
  the entire sale). On every sale, the program prints out profit and loss on the 
  sale of that product.
   
   * @author Anna Nguyen
   * Last Modified: Oct 28, 2022
   */
import java.io.IOException;
import java.util.Iterator;
public class GroceryStore2 {
    String url;
    ArrayStack <Food> storage = new ArrayStack<>();
    //overall PL
    float allPL = 0;

    //takes in url or txt file and stores all purchases into a stack called storage
    //while updating the stacks based on sales and printing out individula and overall 
    //profit as well as the updated stack
    public void store(String url) {
        //instance of csv reader
        ReadCSV csvReader;
        try {
            //tries to read the file
            csvReader = new ReadCSV(url, 24);
        }
        //If file cannot be read
        //it fails silently
        catch (IOException ioe) {
            //instance of the class with
            //no data to search.
            System.err.println("");
            return;
        }

        try {
            //itterates through lines
            Iterator<String[]> iitt = csvReader.iterator();
            //while has next line
            while (iitt.hasNext()) {
                //takes words of line
                String[] lines = iitt.next();
                //System.out.println("AAAA"+lines[0]);
                if (!lines[0].isEmpty() && !lines[1].isEmpty() && !lines[2].isEmpty()
                     && !lines[3].isEmpty() && !lines[4].isEmpty()) {
                    //if has p that means purchased 
                    //on every purchase, push that purchase onto a stack.
                    if (lines[2].equals("p")) {
                        Food purchase = new Food(Integer.parseInt((lines[0])), lines[1], 
                            Integer.parseInt(lines[3]), Float.parseFloat((lines[4])));
                        storage.push(purchase);
                        //System.out.println("storage has " + storage + " \n\n");
                        System.out.println("buy " + purchase.amount + " sell at " + purchase.price);
                        System.out.println("    stack is now at " + formatStack(storage));
                        //System.out.println(storage);
                    }
                    //if has s then sold data if sell
                    else if (lines[2].equals("s")) {
                        //ArrayStack <Food> storageHistory = new ArrayStack<>();
                        //On every sale, peek on the stack
                        Food stackTop = storage.peek();
                        Food sold = new Food(Integer.parseInt((lines[0])), lines[1], Integer.parseInt(lines[3]), Float.parseFloat((lines[4])));
                        //sold amount without change
                        int soldAmount = Integer.parseInt(lines[3]);
                        System.out.println("sell " + sold.amount + "@" + sold.price);
                        StringBuffer sb = new StringBuffer();
                        float PL = 0;

                        while (sold.amount > 0) {
                        // top stack has enough
                            if (stackTop.amount > sold.amount) {
                                stackTop.amount = stackTop.amount - sold.amount;
                                sb.append(" - " + sold.amount + "*" + stackTop.price);
                                PL = soldAmount * sold.price - sold.amount * stackTop.price - PL;
                                allPL = allPL + PL;
                                System.out.printf("    PL on this sale is " + soldAmount + "*" + sold.price + sb + " = %.2f", PL);
                                sold.amount = 0;
                            }
                            //top stack doesnt have enough
                            else if (stackTop.amount <= sold.amount) {
                                sold.amount = sold.amount - stackTop.amount;
                                if (sold.amount>0) {
                                    sb.append(" - " + stackTop.amount + "*" + stackTop.price);
                                }
                                PL = stackTop.amount * stackTop.price + PL;
                                //get next stack
                                storage.pop();
                                stackTop = storage.peek();
                            }
                        }
                        System.out.printf("\n    Overall PL on this product is %.2f",allPL);
                        System.out.println("\n    stack is now " + formatStack(storage));
                    }
                } 
            }
            //System.out.println("exit");
            //System.out.println(storage);
        }
        catch (NullPointerException e) {
            System.err.println("unexpected error try again");
        }
    }
    //takes in stack of food and copies it into a array
    //to print out in requested format for printing out
    // stack is now... aka updated stack at each sell and buy
    String formatStack(ArrayStack<Food> stack){
        int size = stack.size();
        Food[] temp = new Food[stack.size()];
        StringBuffer sb = new StringBuffer();
        for (int i = stack.size()-1; i >= 0; i--) {
            Food foodItem = stack.pop();
            sb.append(foodItem.amount + "@" + foodItem.price + ", ");
            temp[i] = foodItem;
        }
        for (int i = 0; i < size ; i++){
            stack.push(temp[i]);
        }
        return sb.toString();
    }
    //makes object and calls out store()
    public static void main(String[] args){
        GroceryStore2 grocery = new GroceryStore2();
        grocery.store(args[0]);
        //System.out.println(grocery);
    }
}