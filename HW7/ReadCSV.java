import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

/**
* A class that knows how to read CSV files; both from local files
* and from the web. 
* Note that this class does not know
* how to read general csv files, 
* but only very simple ones.
* 
* ALSO, this class strongly assumes that the file (or URL) 
* is actually a CSV file. It will happily read ANY file and try 
* to parse it as a CSV without raising any alarms about the data
* being strange if it is not actually in CSV format.
* 
* See the main method for example use.
* 
*  
* @author gtowell 
* created: August 1, 2021
* modified: by gtowell Jan 3, 2022 -- tweaks
* modified: by gtowell Jan 4, 2022 -- tweaks
* modified: by gtowell May 17, 2022 -- rewrite to do everything
*    using iterators and Iterable.  This allows two different 
*    readers of the same file doing independent reads. It is doubtful
*    that this will ever happen, but this version would handle it.
*/
public class ReadCSV implements Iterable<String[]> {
    
    // The maximum number of items allowed in a CSV Line
    private int maxSplit = 25;
    
    // The URL from which to read
    private URL uu;
    
    
    /**
    * Setup to read a CSV file.
    * @param either a file name or a URL (as a string).  The source of the CSV data.
    * @throws IOException
    */
    public ReadCSV(String name) throws IOException {
        this(name, 25);
    }
    
    /**
    * Setup to read a CSV file.
    * @param either a file name or a URL (as a string).  The source of the CSV data.
    * @param maxSplit the max number of items in a single CSV line.  If the line would slipt into more than this, then the final item will have the excess, including commas
    * @throws FileNotFoundException
    * @throws IOException
    */
    public ReadCSV(String name, int maxSplit) throws IOException {
        if (name.contains("://")) { 
            uu = new URL(name);
        } else {
            // working with a file name
            File file = new File(name);
            URI uri = file.toURI();
            uu = uri.toURL();
        }
        URLConnection huc = uu.openConnection();
        huc.setConnectTimeout(3 * 1000);
        // check that the thing can be opened.  Does not guarantee 
        // openable at use, but still helpful
        new BufferedReader(new InputStreamReader(huc.getInputStream()));
        this.maxSplit = maxSplit;
    }
    
    @Override
    public Iterator<String[]> iterator() {
        return new CSVIterator();
    }
    
    public class CSVIterator implements Iterator<String[]> {
        // the next line of the file being read, if that line exists and has not been used
        private String temp;
        // the reader
        private BufferedReader buffRead;
        
        /**
         * Create a CSV iterator. 
         */
        public CSVIterator() {
            try {
                URLConnection huc = uu.openConnection();
                huc.setConnectTimeout(3 * 1000);
                buffRead = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            } catch (IOException ioe) {
                System.err.println("Should never throw IOException but it could happen in some edges cases" + ioe);
            }
        }
        
        @Override
        public boolean hasNext() {
            if (buffRead == null)
                return false;
            try {
                if (temp == null) {
                    temp = buffRead.readLine();
                }
                return temp != null;
            } catch (IOException ioe) {
                System.err.println("Read problem: " + ioe);
                return false;
            }
        }
        
        @Override
        public String[] next() {
            if (!hasNext()) {
                return null;
            }
            String s = temp;
            temp = null;
            return s.split(",", maxSplit);
            
        }
        
    }
    
    /**
    * Example of the use of the class
    * @param args -- ignored
    */
    public static void main(String[] args) {
        ReadCSV csvReader;
        //STEP 1: create an instance of ReadCSV
        // this is within a try-catch.  If an exception occurs, just
        // give up
        try {
            // get a new instance of the class 
            //ec = new ReadCSV("code.csv");  // read from a file
            csvReader = new ReadCSV("https://cs.brynmawr.edu/cs151/Data/HW1/us.csv", 4);
        }
        catch (IOException ioe) {
            System.err.println("Ending. Cannot read. " + ioe.toString());
            return;
        }

        // STEP 2: go through the lines in the CSV file
        //    STEP 2A: do so with a for loop
        for (String[] ss : csvReader) {
            for (String s : ss) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
        
        //   STEP 2B: Do so with a while loop
        //   Does the same things as the previous loop, 
        //   just with different syntax
        Iterator<String[]> iitt = csvReader.iterator();
        while (iitt.hasNext()) {
            String[] ss = iitt.next();
            for (int i = 0; i<ss.length; i++) {
                System.out.print(ss[i] + " ");
            }
            System.out.println();
        }
    }
    
}