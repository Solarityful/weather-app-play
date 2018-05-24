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
    private String locationIdentifier = "title";
    private String weatherIdentifier = "woeid";

    @Inject 
    public LocationController(WSClient ws) {
        this.ws = ws;
    }

    public CompletionStage<Result> search(String query) {
        return ws.url(locationURL).addQueryParameter("query", query).get().thenApply((WSResponse r) -> {
            LocationSearchResult resultToSend = new LocationSearchResult();
            List<Location> locationsList = new ArrayList();
            JsonNode responseJson = r.asJson();
            List<JsonNode> listOfResults = responseJson.findParents(locationIdentifier);

            for (JsonNode resultNode : listOfResults){
                Location resultLocation = new Location(resultNode.get(locationIdentifier).asText(), resultNode.get(weatherIdentifier).asInt());
                locationsList.add(resultLocation);
            }
            resultToSend.setLocations(locationsList);

            return ok(results.render(resultToSend));
        });
    }

    public Result getLocation(int locationId) {
        return ok(("This is the input id: "+ locationId));
    }

    public Result home() {
        return ok(search.render());
    }
}