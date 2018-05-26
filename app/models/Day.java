package models;
import com.fasterxml.jackson.databind.JsonNode;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Day {

    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    protected Date date;
    protected BigDecimal temperatureHigh;
    protected BigDecimal temperatureLow;
    protected String description;
    
    private static String jsonIdentifier = "id";

    public Day(JsonNode weatherNode){

        try {
            this.date = dateformat.parse(weatherNode.get("applicable_date").asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        this.description = weatherNode.get("weather_state_name").asText();
        this.temperatureHigh = weatherNode.get("max_temp").decimalValue();
        this.temperatureLow = weatherNode.get("min_temp").decimalValue();
    }

    public Day(){

    }

    public Date getDate(){
        return this.date;
    }

    public String getDateAsString(){
        return this.dateformat.format(date);
    }

    public void setDate(Date date){
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

    public String getDayOfTheWeek(){
        if (this.date != null) {
            String dayAsString = new SimpleDateFormat("EEEE").format(date);
            return dayAsString;
        }
        return "No Date Found";
    }
}