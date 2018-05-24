package controllers;
import play.mvc.*;
import views.html.*;
import javax.inject.Inject;
import play.libs.ws.*;
import java.util.concurrent.CompletionStage;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;

public class LocationController extends Controller implements WSBodyReadables, WSBodyWritables{

    private final WSClient ws;
    private String locationURL = "https://www.metaweather.com/api/location/search/";
    private String forecastURL = "https://www.metaweather.com/api/location/";
    private String locationIdentifier = "title";

    @Inject 
    public LocationController(WSClient ws) {
        this.ws = ws;
    }

    public CompletionStage<Result> search(String query) {
        return ws.url(locationURL).addQueryParameter("query", query).get().thenApply((WSResponse r) -> {
            JsonNode responseJson = r.asJson();
            List<JsonNode> listOfResults = responseJson.findParents(locationIdentifier);
            return ok("Number of elements: " + listOfResults.size());
        });
    }

    public Result getLocation(int locationId) {
        return ok(("This is the input id: "+ locationId));
    }

    public Result home() {
        return ok(search.render());
    }
}