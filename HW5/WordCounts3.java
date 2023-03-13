/* 
   * Desc:
   * This program uses SepChainHT.java to store key value pairs of words of a text ,
   * to the number of that word appears in the text file or url given by user
   * input then in the main run time is recorded and some other information
   * is printed out as requested
   
   * @author Anna Nguyen
   * Last Modified: Oct 19, 2022
   */
import java.io.IOException;
import java.util.Iterator;
public class WordCounts3 {
    String url;
    SepChainHT <String,Integer> storage = new SepChainHT<>();
    //num of words
    int wNum = 0;
    //unique num
    int uNum = 0;
    //most common word
    String mWord = " ";
    int max = 0;

    //takes in url or txt file and stores all data into storage 
    // in key value pairs key = word value = number of time that word appears
    public void reader3(String url) {
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
            Iterator<String[]> iitt = csvReader.iterator();
            //per one word
            while (iitt.hasNext())  {
                //get line after line using iterator and stores parts
                //of the line into array
                String[] word = iitt.next();
                //System.out.print(word[0] + " ");
                for (String w : word) {
                    //finds if word is in hashtabe
                    if (storage.containsKey(w)) {
                        //updates key value
                        storage.put(w, storage.get(w) + 1);
                    }
                    else {
                        //adds new word to hastable
                        storage.put(w, 1);
                        uNum++;
                    }
                    //test
                    //System.out.print(storage.get( word[0]) + " ");
                }
                //Checks if the word[0] is actually a word
                if (!word[0].equals("")) {
                    wNum++; 
                }
            }
            //for every word (k) in storage
			for (String k : storage.keySet()) {
                //System.out.print(k + "\n");
                //get value of the word
                int value = storage.get(k);
                //System.out.print(value + "\n");
                //if value of the word is greater than current max num update
                if (value > max) {
                    max = value;
                    mWord = k;
                }
            }
        }
        catch (Exception ioe) {
            System.err.println("unexpected error try again");
        }
           
    }
    public static void main(String[] args) {
        long start = System.nanoTime();
        WordCounts3 allWords = new WordCounts3();
        allWords.reader3(args[0]);
        long end = System.nanoTime();
        double timer = (double)(end - start)/1000000000;
        System.out.println("Run Time: " + timer + " secs");
        System.out.println("Number of Occurance of Most Common Word: " + allWords.max);
        System.out.println("Most Common Word: " + allWords.mWord);
        System.out.println("Number of Unique Words: " + allWords.uNum);
        System.out.println("Number of words: " + allWords.wNum);
    }
}