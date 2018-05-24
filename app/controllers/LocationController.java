package controllers;
import play.mvc.*;
import views.html.*;

public class LocationController extends Controller {

    public Result search(String query) {
        return ok(("This is the search function, you looked for " + query ));
    }

    public Result getLocation(int locationId) {
        return ok(("This is the input id: "+ locationId));
    }

    public Result home() {
        return ok(("this is the home page for locations"));
    }

}