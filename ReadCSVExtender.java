import java.io.IOException;

public class ReadCSVExtender extends ReadCSV{
    public ReadCSVExtender(String name, int maxSplit) throws IOException {
        super(name, maxSplit);
    }
    public static void main(String[] args) {
        ReadCSVExtender csvReader;
        //STEP 1: create an instance of ReadCSV
        // this is within a try-catch.  If an exception occurs, just
        // give up
        try {
            // get a new instance of the class 
            //ec = new ReadCSV("code.csv");  // read from a file
            csvReader = new ReadCSVExtender("https://cs.brynmawr.edu/cs151/Data/Hw1/uszipcodes.csv", 200);
        }
        catch (IOException ioe) {
            System.err.println("Ending. Cannot read. " + ioe.toString());
            return;
        }
        
        int lineCount = 0;
        int entryCount = 0;
        
        // STEP 2: go through the lines in the CSV file
        //    STEP 2A: do so with a for loop
        for (String[] ss : csvReader) {
            lineCount++;
            for (String s : ss) {
                if (s.length() > 0) {
                    entryCount++;
                }
            }
        }
        
        System.out.print(lineCount + " lines\n");
        System.out.print(entryCount + " entries");
    }
    
}