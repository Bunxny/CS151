 /* 
   * Desc:
   *    This program is made to be used to find and store
   loctions in a csv file. The method in this class finds
   a matching loction to the string zipCode is given.
   
   Process:
    It is done by reading a file given a url, putting its lines
    in an array(grabs size of array with first word of line)
    and finding a match value in the allZips array to zipCode.

   * @author Anna Nguyen
   * Last Modified: Sept 14, 2022
   */
import java.io.IOException;
import java.util.Iterator;
public class PlaceContainer{
  String url;
  String zipCode;
  Place[] allZips;

    public PlaceContainer(String url){
        //instance of csv reader
        ReadCSV csvReader;
        try {
            //tries to the file
            csvReader = new ReadCSV(url, 24);
        }
        //If file cannot be read
        //it fails silently
        catch (IOException ioe) {
        //instance of the class with
        //no data to search.
            csvReader = null;
            allZips = new Place[0];
            return;
        }

        Iterator<String[]> iitt = csvReader.iterator();
        //reads first line
        String[] firstLine=iitt.next();
        //first part of the line aka num of lines
        int lineAmount=Integer.parseInt(firstLine[0]);
        //yay now we can put the size into the array
        allZips= new Place[lineAmount];
        //parsing and storing every line
        int i=0;
        while (iitt.hasNext()) {
            //get line after line using iterator and stores parts
            //of the line into an array
            String[] lines=iitt.next();
            //formats the parts from line
            Place structer= new Place(lines[0],lines[1],lines[2]);
            //puts structure into array
            allZips[i]=structer;
            //for testing
            //System.out.println(allZips[i]);
            //next
            i++;
        }
    }

    /**
     * Finds a Place record given a zip code
     * @param zipCode the zip code to find.
     * @return the place, or null?? if the zip code is unknown
     */
    public Place lookupZip(String zipCode){
        //go through allZips and compare it to zipCode
        for(int i=0; i<allZips.length; i++){
            //finding a match
            if(zipCode.equals(allZips[i].getZip())){
                return allZips[i];
            }
        } 
        return null;
    }
}