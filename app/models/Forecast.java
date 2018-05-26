package models;
import java.util.List;

public class Forecast {
    protected Location location;
    protected Integer numberOfDays;
    protected List<Day> listOfDays;
    private static String jsonIdentifier = "consolidated_weather";

    public Integer getNumberOfDays(){
        return this.numberOfDays;
    }

    public void setNumberOfDays(Integer count){
        this.numberOfDays = count;
    }

    public List<Day> getDays(){
        return this.listOfDays;
    }

    public void setDays(List<Day> listOfDays){
        this.listOfDays = listOfDays;
    }

    public static String getJsonIdentifier(){
        return jsonIdentifier;
    }

    public Location getLocation(){
        return this.location;
    }

    public void setLocation(Location location){
        this.location = location;
    }
}