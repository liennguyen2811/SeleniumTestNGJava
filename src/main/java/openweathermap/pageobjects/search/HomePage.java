package openweathermap.pageobjects.search;

import openweathermap.pageobjects.AbstractPage;
import org.openqa.selenium.Keys;


public class HomePage extends AbstractPage {

    public static final String SEARCH_TEXTBOX = "//input[@placeholder='Weather in your city']";


    public void inputTextToSearch(String searchtext) {
        waitToElementVisible(SEARCH_TEXTBOX);
        sendkeyToElement(SEARCH_TEXTBOX,searchtext);
        sendKeyCodeToElement(SEARCH_TEXTBOX,Keys.ENTER);
    }


}