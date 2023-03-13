/* 
   * Desc:
   * This program uses methods in Place, Location, LocatedPlace, and PlaceContainer 
   * to support user interaction. User will enter a zip and all info collected
   * about zip will be shown to the reader.
   
   * @author Anna Nguyen
   * Last Modified: Sept 20, 2022
   */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //instance of Place container stores zipcode file data
        PlaceContainer test = new PlaceContainer("https://cs.brynmawr.edu/cs151/Data/Hw3/shuffzips.csv");
        Place location;
        Scanner scnr = new Scanner(System.in);
        
        //keeps going unless changed to false or break
        while (true) {
            System.out.print("Enter a zip code to lookup (q to quit): ");
            //updates value to user input
            String l = scnr.next();
            //finds match
            location = test.lookupZip(l);
            
            //invalid input
            if ((location == null) && !(l.equals("q"))) {
                System.out.println("No such zipcode.");

            } 
            //quit
            else if (l.equals("q")) {
                System.out.println("Goodbye!");
                break;
            }
            else {
                System.out.println(location);
            }
        }
    }
}
