import org.junit.Test;
import play.test.WithApplication;
import play.twirl.api.Content;
import models.*;
import controllers.*;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * A functional test starts a Play application for every test.
 *
 * https://www.playframework.com/documentation/latest/JavaFunctionalTest
 */
public class FunctionalTest extends WithApplication {

    @Test
    public void renderResults() {
        LocationSearchResult mockResult = new LocationSearchResult();
        String testNameOne = "testone";
        String testNameTwo = "testtwo";
        Integer testIdOne = 12345;
        Integer testIdTwo = 54321;
        Location testLocationOne = new Location(testNameOne, testIdOne);
        Location testLocationTwo = new Location(testNameTwo, testIdTwo);
        List<Location> testList = new ArrayList();
        testList.add(testLocationOne);
        testList.add(testLocationTwo);

        mockResult.setLocations(testList);

        String message = "mock message";
        Content html = views.html.results.render(mockResult, message);
        assertThat("text/html").isEqualTo(html.contentType());
        assertThat(html.body()).contains(message);
        assertThat(html.body()).contains(testNameOne);
        assertThat(html.body()).contains(testNameTwo);
    }

    @Test
    public void renderForecast(){
        Forecast mockForecast = new Forecast();
        String testNameOne = "testone";
        Integer testIdOne = 12345;
        Integer daysToForecast = 1;

        Location testLocation = new Location(testNameOne, testIdOne);
        
        Day testDay = new Day();

        Date date = new Date(); 
        BigDecimal temperatureHigh = new BigDecimal(100.00);
        BigDecimal temperatureLow = new BigDecimal(25.25);
        String description = "test description";

        testDay.setDate(date);
        testDay.setTemperatureHigh(temperatureHigh);
        testDay.setTemperatureLow(temperatureLow);
        testDay.setDescription(description);

        List<Day> dayList = new ArrayList();
        dayList.add(testDay);

        mockForecast.setDays(dayList);
        mockForecast.setLocation(testLocation);
        mockForecast.setNumberOfDays(daysToForecast);

        Content html = views.html.forecast.render(mockForecast);
        assertThat("text/html").isEqualTo(html.contentType());
        assertThat(html.body()).contains(description);
        assertThat(html.body()).contains(testDay.getTemperatureHigh().toString());
        assertThat(html.body()).contains(testDay.getTemperatureLow().toString());
    }
}
