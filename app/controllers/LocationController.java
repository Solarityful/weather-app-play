package controllers;
import models.*;
import play.mvc.*;
import play.data.Form;
import play.data.FormFactory;
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
    private Form<SearchData> form;

    @Inject 
    public LocationController(WSClient ws, FormFactory formFactory) {
        this.ws = ws;
        this.form = formFactory.form(SearchData.class);
    }

    public CompletionStage<Result> search(String query) {
        return ws.url(locationURL).addQueryParameter("query", query).get().thenApply((WSResponse r) -> {
            JsonNode responseJson = r.asJson();
            List<JsonNode> listOfResults = responseJson.findParents(Location.getJsonIdentifier());
            List<Location> locationsList = new ArrayList();

            for (JsonNode resultNode : listOfResults){
                Location resultLocation = new Location(resultNode);
                locationsList.add(resultLocation);
            }

            LocationSearchResult resultToSend = new LocationSearchResult();
            resultToSend.setLocations(locationsList);

            return ok(results.render(resultToSend));
        });
    }

    public CompletionStage<Result> getForecast(int locationId) {
        return ws.url(forecastURL + String.valueOf(locationId) + "/").get().thenApply((WSResponse r) -> {
            JsonNode responseJson = r.asJson();

            List<Day> daysList = new ArrayList();
            List<JsonNode> listOfDays = responseJson.get(Forecast.getJsonIdentifier()).findParents(Day.getJsonIdentifier());
            for (JsonNode dayNode : listOfDays){
                if (daysList.size() < daysToForecast) {
                    Day day = new Day(dayNode);
                    daysList.add(day);
                }
            }

            Forecast forecastToSend = new Forecast();
            Location forecastLocation = new Location(responseJson);
            
            forecastToSend.setNumberOfDays(daysToForecast);
            forecastToSend.setLocation(forecastLocation);
            forecastToSend.setDays(daysList);
           
            return ok(forecast.render(forecastToSend));
        });
        
    }

    public Result searchForm(){
        final Form<SearchData> boundForm = form.bindFromRequest();
        if (boundForm.hasErrors()){
            return badRequest(search.render(boundForm));
        }
        else{
            SearchData data = boundForm.get();
            return redirect(routes.LocationController.search(data.getQuery()));
        }
    }

    public Result home() {
        return ok(search.render(form));
    }
}