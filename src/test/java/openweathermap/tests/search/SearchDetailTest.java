package openweathermap.tests.search;

import openweathermap.helpers.MethodHelper;
import openweathermap.pageobjects.search.HomePage;
import openweathermap.pageobjects.search.SearchDetailPage;
import openweathermap.pageobjects.search.SearchListPage;
import openweathermap.tests.BaseTest;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
public class SearchDetailTest extends BaseTest {

    @Test
    public void checkResultDetailAfterSearch() {
        //1.Open new log in page
        HomePage homePage = new HomePage();
        SearchListPage searchListPage = new SearchListPage();
        SearchDetailPage searchDetailPage = new SearchDetailPage();
        // 2. Input place to search
        homePage.inputTextToSearch("Hanoi");
        Assert.assertTrue(searchDetailPage.checkAllItemOfListResultDisplay("Hanoi"));
        Assert.assertTrue(false);
    }

    @DataProvider(name = "citySearch")
    public Object[][] inputData() throws Exception {
        MethodHelper methodHelper = new MethodHelper();
        HashMap<String, String> listCountryToCheck;
        Object[][] result;
        listCountryToCheck = methodHelper.readCSV("cityname");
        result = methodHelper.hashMapToDataProvider(listCountryToCheck);
        return result;
    }

    @Test(dataProvider = "citySearch")
    public void checkResultDetailAfterSearchUseDataProvider(String citySearch, String cityDisplay) {
        //1.Open new log in page
        HomePage homePage = new HomePage();
        SearchListPage searchListPage = new SearchListPage();
        SearchDetailPage searchDetailPage = new SearchDetailPage();
        // 2. Input place to search
        homePage.inputTextToSearch(citySearch);
        // 3. Check point : Current day time, Location, and weather
        Assert.assertTrue(searchDetailPage.checkAllItemOfListResultDisplay(citySearch));
    }

}
