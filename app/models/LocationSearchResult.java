package models;
import java.util.List;

public class LocationSearchResult {
    protected Integer maxResults;
    protected Integer totalResults;
    protected List<Location> locations;
    
    public List<Location> getLocations(){
        return locations;
    }

    public void setLocations(List<Location> locations){
        this.locations = locations;
        setTotalResults(locations.size());
    }

    public Integer getTotalResults(){
        return this.totalResults;
    }

    public void setTotalResults(Integer total){
        this.totalResults = total;
    }

    public Integer getmaxResults(){
        return this.maxResults;
    }

    public void setMaxResults(Integer max){
        this.maxResults = max;
    }


}