package models;
import java.util.List;

public class Forecast {
    protected Integer numberOfDays;
    protected List<Day> listOfDays;

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
}