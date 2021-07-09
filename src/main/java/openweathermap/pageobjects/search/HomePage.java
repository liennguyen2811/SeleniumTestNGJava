package openweathermap.pageobjects.search;

import openweathermap.common.Fixtures;
import openweathermap.pageobjects.AbstractPage;
import org.openqa.selenium.Keys;



public class HomePage extends AbstractPage {

    public static final String SEARCH_TEXTBOX = "//input[@placeholder='Weather in your city']";

    public void inputTextToSearch(String searchText) {
        waitToElementVisible(SEARCH_TEXTBOX);
        sendkeyToElement(SEARCH_TEXTBOX,searchText);
        long threadid = Fixtures.SetUp.threadid;
        System.out.println(" Lien check thread" + threadid);
        sendKeyCodeToElement(SEARCH_TEXTBOX,Keys.ENTER);
    }


}