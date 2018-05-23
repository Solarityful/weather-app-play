package controllers;
import play.mvc.*;
import views.html.*;

public class LocationController extends Controller {

    public Result search() {
        return ok(("This is the search function"));
    }

    public Result getLocation(int locationId) {
        return ok(("This is the input id:"+ locationId));
    }

}