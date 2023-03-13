/* 
   * Desc:
   * This program is made to be used to Create 
   a class called Place to store information about 
   each zipcode.
   
   Process:
   Has class instance varables, put into a constructor,
   and put into getters. Finally, is rearranged in toString.

   * @author Anna Nguyen
   * Last Modified: Sept 14, 2022
   */
public class Place{
    protected String zip;
    protected String town;
    protected String state;

    public Place(String z, String t, String s){
        zip=z;
        town=t;
        state=s;
    }

    public String getZip(){
        return zip;
    }
    public String getTown(){
        return town;
    }
    public String getState(){
        return state;
    }
    //rearranges values
    public String toString(){
        return town + ", " +  state + ", "+ zip;
    }
}