/* 
   * Desc:
   * This program stores information about 
   each zipcode that has location (longatude,latitude).

   * @author Anna Nguyen
   * Last Modified: Sept 20, 2022
*/
public class Location extends Place {
    protected String latitude;
    protected String longitude;

    // constructor takes in 7 string and puts them equal to respected
    //inherited and Location instance/varables
    public Location(String zip, String town, String state,
    String latitude, String longitude) {
        super(zip, town, state);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }

    //Rearranges and prints out values
    @Override
    public String toString() {
            return super.toString() + "Lat:" + latitude + "  " + "Lon:" + longitude + "  ";
    }
}