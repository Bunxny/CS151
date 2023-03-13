/* 
   * Desc:
   *    This program is made to be used to find and store
   * data using classes that should hold exactly as much information 
   * as is contained in a row so some data is missing
   * from the csv file and put into an Arraylist. This program contains
   * a method which finds a matching zipcode from allZips (holds all data)
   *  to one given in the constructor lookupZip.
   
   * @author Anna Nguyen
   * Last Modified: Sept 28, 2022
   */
import java.io.IOException;
import java.util.Iterator;
public class PlaceContainer {
  String url;
  String zipCode;
  SortedArrayList<Place> allZips = new SortedArrayList<Place>();

    //takes in url and stores all data into allzips even if some 
    //data is missing
    public PlaceContainer(String url) {
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

        Iterator<String[]> iitt = csvReader.iterator();
        while (iitt.hasNext()){
            //get line after line using iterator and stores parts
            //of the line into an arraylist
            String[] lines = iitt.next();
            //test
            //System.out.println(lines[9] + "\n");

            //formats the parts from line and adds it into arraylist
            // no population or location
            if (lines[9].equals("") && lines[10].equals("") &&
             lines[5].equals("") && lines[6].equals("")) {
                Place struct = new Place(lines[0], lines[2], lines[3]);
                //System.out.println(struct);
                allZips.add(struct);
            }
            //no population
            else if (lines[9].equals("") && lines[10].equals("")) {
                Place struct2 = new Location(lines[0], lines[2], lines[3], lines[5], lines[6]);
                //System.out.println(struct2);
                allZips.add(struct2);
            }
             //has both pop and loc
            else {
                Place struct1 = new LocatedPlace(lines[0], lines[2], lines[3], lines[5],
                lines[6], lines[9], lines[10]);
                //System.out.println(struct1);
                allZips.add(struct1);
            }
        }
    }

    //given a string perferably a zipcode it will convert to type
    // place and look for a match in the allzip arraylist
    public Place lookupZip(String zipCode) {
        Place zip = new Place(zipCode, "","");
        //go through allZips and compare it to zipCode
        for (int i = 0; i < allZips.size(); i++) {
            //find if a match exist
            if (zip.equals(allZips.get(i))) {
                return allZips.getInstance(zip);
            }
        } 
        return null;
    }
    //test code
    /*
    public void getAllZips() {
        allZips.display();
    }

    public static void main(String[]args){
        PlaceContainer n = new PlaceContainer("https://cs.brynmawr.edu/cs151/Data/Hw4/shuffzips.csv");
        Place b = n.lookupZip("49104");
        n.getAllZips();
        System.out.print(b);
        System.out.println("BBBBB" + b);
    }*/
    
}