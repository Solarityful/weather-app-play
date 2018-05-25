package models;
import com.fasterxml.jackson.databind.JsonNode;

public class Location {
    protected String name;
    protected Integer id;
    private static String jsonIdentifier = "title";
    private static String weatherIdentifier = "woeid";

    public Location(String name, Integer id){
        this.name = name;
        this.id = id;
    }

    public Location(){

    }

    public Location(JsonNode LocationNode){
        this.name = LocationNode.get(jsonIdentifier).asText();
        this.id = LocationNode.get(weatherIdentifier).asInt();
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public static String getJsonIdentifier(){
        return jsonIdentifier;
    }

    public static String getWeatherIdentifier(){
        return weatherIdentifier;
    }
}