/* 
   * Desc:
   * This program stores information about 
   each zipcode that has both location (longatude,latitude) and population

   * @author Anna Nguyen
   * Last Modified: Sept 20, 2022
*/
public class LocatedPlace extends Location {
   protected String population;
    
    // constructor takes in 7 string and puts them equal to respected
    //inherited and LocatedPlace instance/varables
    public LocatedPlace(String zip, String town, String state, String latitude, 
    String longitude, String population1, String population2) {
        super(zip, town, state, latitude, longitude);
        int totalPop = Integer.parseInt(population1) + Integer.parseInt(population2);
        population = Integer.toString(totalPop);
    }

    public String getPopulation() {
        return population;
    }

    //Rearranges and prints out values
    @Override
    public String toString() {
        return super.toString() + "Pop:" + population;
    }
}