package controllers;
import models.*;
import play.mvc.*;
import views.html.*;
import javax.inject.Inject;
import play.libs.ws.*;
import java.util.concurrent.CompletionStage;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;

public class LocationController extends Controller implements WSBodyReadables, WSBodyWritables{

    private final WSClient ws;
    private String locationURL = "https://www.metaweather.com/api/location/search/";
    private String forecastURL = "https://www.metaweather.com/api/location/";
    private Integer daysToForecast = 5;

    @Inject 
    public LocationController(WSClient ws) {
        this.ws = ws;
    }

    public CompletionStage<Result> search(String query) {
        return ws.url(locationURL).addQueryParameter("query", query).get().thenApply((WSResponse r) -> {
            LocationSearchResult resultToSend = new LocationSearchResult();
            List<Location> locationsList = new ArrayList();

            JsonNode responseJson = r.asJson();
            List<JsonNode> listOfResults = responseJson.findParents(Location.getJsonIdentifier());

            for (JsonNode resultNode : listOfResults){
                Location resultLocation = new Location(resultNode);
                locationsList.add(resultLocation);
            }
            resultToSend.setLocations(locationsList);

            return ok(results.render(resultToSend));
        });
    }

    public CompletionStage<Result> getLocation(int locationId) {
        return ws.url(forecastURL + String.valueOf(locationId) + "/").get().thenApply((WSResponse r) -> {
            Forecast forecastToSend = new Forecast();
            forecastToSend.setNumberOfDays(daysToForecast);
            List<Day> daysList = new ArrayList();

            JsonNode responseJson = r.asJson();
            List<JsonNode> listOfDays = responseJson.get(Forecast.getJsonIdentifier()).findParents(Day.getJsonIdentifier());

            for (JsonNode dayNode : listOfDays){
                if (daysList.size() < daysToForecast) {
                    Day day = new Day(dayNode);
                    daysList.add(day);
                }
            }
            forecastToSend.setDays(daysList);

            return ok(forecast.render(forecastToSend));
        });
        
    }

    public Result home() {
        return ok(search.render());
    }
}