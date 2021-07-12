package openweathermap.tests.search;
import openweathermap.pageobjects.search.HomePage;
import openweathermap.pageobjects.search.SearchDetailPage;
import openweathermap.pageobjects.search.SearchListPage;
import openweathermap.tests.BaseTest;
import org.junit.Assert;
import org.testng.annotations.Test;

public class SearchListTest extends BaseTest {
    @Test
    public void checkListResult() {
        //1.Open new log in page
        HomePage homePage = new HomePage();
        SearchListPage searchListPage = new SearchListPage();
        SearchDetailPage searchDetailPage = new SearchDetailPage();
        // 2. Input place to search
        homePage.inputTextToSearch("Hanoi");
        searchListPage.goToEachItemOfListResult();
        Assert.assertTrue(searchListPage.isSearchListResultDisplayed());
    }
}
