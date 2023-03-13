/* 
   * Desc:
   * This program stores information about 
   each zipcode that does not have location (longatude,latitude) and population

   * @author Anna Nguyen
   * Last Modified: Sept 28, 2022
*/
public class Place {
    protected String zip;
    protected String town;
    protected String state;

    //Takes in three strings and equals it to its respected instance of the class
    public Place(String z, String t, String s) {
        zip = z;
        town = t;
        state = s;
    }

    public String getZip() {
        return zip;
    }
    public String getTown() {
        return town;
    }
    public String getState() {
        return state;
    }
    //rearranges values
    public String toString() {
        return zip + " " + town + ", " + state + " " + "       ";
    }

    //checks if a object type String or Place zip is 
    //equal to another object's zip
    @Override
    public boolean equals(Object ob) {
        if (ob instanceof Place) {
            Place pl = (Place) ob;
            return pl.getZip().equals(this.zip);
        }
        if (ob instanceof String) {
            String st = (String) ob;
            return this.zip.equals(st);
        }
        return false;

    }
}