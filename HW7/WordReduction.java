/* 
   * Desc:
   * program that determines if a word (or words) provided on the command 
   * line are reducible Returns True/False for each word to be checked depending 
   * on whether there exists a reduction of that word using recursion with
   *  backtracking to search for a reduction.

   * @author Anna Nguyen
   * Last Modified: Nov 6, 2022
   */
import java.io.IOException;
import java.util.Iterator;

public class WordReduction {
    ProbeHTInc <String,Integer> dictionary = new ProbeHTInc<>(500000);
    String url = "https://cs.brynmawr.edu/cs151/Data/Hw7/words.txt";

    //takes in url or txt file and stores all data into dictionary
        private WordReduction() {
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
            System.err.println("file cannot be read");
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
                    if (dictionary.containsKey(w)) {
                        //updates key value
                        dictionary.put(w, dictionary.get(w) + 1);
                    }
                    else {
                        //adds new word to hastable
                        dictionary.put(w, 1);
                    }
                }
            }
            //System.out.println(dictionary.size());
        }
         
        catch (NullPointerException e) {
            System.err.println("unexpected error try again");
        }
    }

    /*
	* determines if a string is an english word.
	* @param aString the string to check
	* @returns true if string is word and false if not
	*/
    private boolean isInEnglish(String aString) {
        if (dictionary.containsKey(aString)) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
	* Remove the nth letter from a string
	* @param n the index of the char to remove
	* @param s the string to remove from
	* @return a string, one letter shorter.  if n is 
	* out of bounds, just return the string without its last letter
	*/
    private String removeNchar(int n, String s) {
        if (s==null) { 
            return s; }
        if (n>0 && n<s.length()-1) {
            return s.substring(0,n)+s.substring(n+1);
        }
        if (n==0) {
            return s.substring(1);
        }
            return s.substring(0, s.length()-1);
        
    }

    /*
    * user friendly method takes in a String and uses a util to return true or false
    * sets up enviroment for the util and sets up exceptions
	* @param s the string to check
	* @return true if word can be reduced or false is word cannot be reduced
	*/
    public boolean twoLetters(String s) {
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isUpperCase(ch)) {
                System.out.print("Please make sure this word has all lower case letters: ");
                return false;
            }
        }
        return twoLettersUtil(s);
    }

    /*
	* reduces word and checks if it is still a word till a two letter word is
    * found from the previous word found
	* @param s the string to reduce and check
	* @return true if word can become a two letter word or false if can not
	*/
    private boolean twoLettersUtil(String s) {
        //System.out.println("word:" + s + " " + isInEnglish(s));
        if (!isInEnglish(s)) {
            return false;
        }
        if (s.length() <= 2) {
            return true;
        }
        for(int j=0; j<s.length(); j++) {
            String updated = removeNchar(j,s);
            //System.out.println("word:" + s + " " + isInEnglish(s));
            // System.out.println("word:" + updated + " " + isInEnglish(updated));
            if (isInEnglish(updated)) {
                boolean save = twoLettersUtil(updated);
                if (save!=false)
                    return save;
            }
        }
        return false;
    }

    public static void main(String[] args){
        WordReduction grocery = new WordReduction();
        //grocery.reader("https://cs.brynmawr.edu/cs151/Data/Hw7/words.txt");
        for (int i=0; i<args.length; i++) {
            System.out.println(args[i] + " -- " + grocery.twoLetters(args[i]));
        }
    }
}