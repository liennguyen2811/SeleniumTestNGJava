package openweathermap.tests.search;

import openweathermap.helpers.MethodHelper;
import openweathermap.pageobjects.search.SearchPage;
import openweathermap.tests.BaseTest;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static openweathermap.helpers.MethodHelper.hashMapToDataProvider;

public class SearchTest extends BaseTest {
    @Test
    public void launchOpenWeatherApp() {
        //1.Open new log in page
        SearchPage searchPage = new SearchPage();
        // 2. Input place to search
        searchPage.inputTextToSearch("Hanoi");
        searchPage.goToResultDetail("Hanoi");
        // 3. Check point : Current day time, Location, and weather
        Assert.assertTrue(searchPage.checkDateTimeOfResult("Hanoi"));
        Assert.assertTrue(searchPage.checkCityNameDisplay("Hanoi"));
    }

    @DataProvider(name="citySearch")
    public Object[][] inputData() throws Exception
    {   HashMap<String, String> listCountryToCheck;
        Object[][] result ;
        listCountryToCheck = MethodHelper.readCSV();
        result = hashMapToDataProvider(listCountryToCheck);
        return result;
    }

    @Test(dataProvider="citySearch")
    public void checkHelper(String citySearch,String cityDisplay) {
        //1.Open new log in page
        SearchPage searchPage = new SearchPage();
        // 2. Input place to search
        searchPage.inputTextToSearch(citySearch);
        searchPage.goToResultDetail(citySearch);
        // 3. Check point : Current day time, Location, and weather
        Assert.assertTrue(searchPage.checkDateTimeOfResult(citySearch));
        Assert.assertTrue(searchPage.getCityFromSearchResult().equals(cityDisplay));

    }


}
