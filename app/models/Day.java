package models;
import java.util.Date;
import com.fasterxml.jackson.databind.JsonNode;

public class Day {
    protected Date date;
    protected Double temperatureHigh;
    protected Double temperatureLow;
    protected String description;
    private static String jsonIdentifier = "id";

    public Day(JsonNode weatherNode){
        this.description = weatherNode.get("weather_state_name").asText();
        this.temperatureHigh = weatherNode.get("max_temp").asDouble();
        this.temperatureLow = weatherNode.get("min_temp").asDouble();
    }

    public Day(){

    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Double getTemperatureHigh(){
        return this.temperatureHigh;
    }

    public void setTemperatureHigh(Double temperatureHigh){
        this.temperatureHigh = temperatureHigh;
    }

    public Double getTemperatureLow(){
        return this.temperatureLow;
    }

    public void setTemperatureLow(Double temperatureLow){
        this.temperatureLow = temperatureLow;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public static String getJsonIdentifier(){
        return jsonIdentifier;
    }
}