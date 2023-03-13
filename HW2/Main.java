/* 
   * Desc:
   * This program uses methods in Place and PlaceContainer to read and store the zip code data.
    Then, implements the user interaction in finding the place the user enter the zipcode for. 
    the program stops once q is entered.
   
   Process:
   Place and PlaceContainer is used.
   while loop, if if else else loop is used to keep
   the flow going.
   *Scanner class for user input!

   * @author Anna Nguyen
   * Last Modified: Sept 14, 2022
   */

import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        //instance of Place container stores zipcode file data
        PlaceContainer test = new PlaceContainer("https://cs.brynmawr.edu/cs151/Data/Hw2/uszipcodes.csv");
        Place location;
        Scanner scnr = new Scanner(System.in);
        
        //keeps going unless changed to false or break
        while (true){
            System.out.print("zipcode: ");
            //updates value to user input
            String l = scnr.next();
            //find match
            location = test.lookupZip(l);

            if((location==null) && !(l.equals("q"))){
                System.out.println("No such zipcode.\n");

            } else if (l.equals("q")){
                System.out.println("Goodbye!");
                break;
            }
            else {
                System.out.println(location.getTown() + ", "+ location.getState() + "\n");
            }

        }
        
    }
}

 
 