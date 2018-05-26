package models;
import com.fasterxml.jackson.databind.JsonNode;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Day {
    protected String date;
    protected BigDecimal temperatureHigh;
    protected BigDecimal temperatureLow;
    protected String description;
    private static String jsonIdentifier = "id";

    public Day(JsonNode weatherNode){
        this.date = weatherNode.get("applicable_date").asText();
        this.description = weatherNode.get("weather_state_name").asText();
        this.temperatureHigh = weatherNode.get("max_temp").decimalValue();
        this.temperatureLow = weatherNode.get("min_temp").decimalValue();
    }

    public Day(){

    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public BigDecimal getTemperatureHigh(){
        return this.temperatureHigh.setScale(1, RoundingMode.DOWN);
    }

    public void setTemperatureHigh(BigDecimal temperatureHigh){
        this.temperatureHigh = temperatureHigh;
    }

    public BigDecimal getTemperatureLow(){
        return this.temperatureLow.setScale(1, RoundingMode.DOWN);
    }

    public void setTemperatureLow(BigDecimal temperatureLow){
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