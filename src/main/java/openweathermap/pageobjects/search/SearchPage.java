package openweathermap.pageobjects.search;

import openweathermap.pageobjects.AbstractPage;
import openweathermap.helpers.MethodHelper;
import org.openqa.selenium.Keys;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class SearchPage extends AbstractPage {

    public static final String SEARCH_TEXTBOX = "//input[@placeholder='Weather in your city']";
    public static final String SEARCH_RESULT_BRIEF = "//table[@class='table']//td[2]/b/a";
    public static final String SEARCH_RESULT_DATE = "//span[@class='orange-text']";
    public static final String CITY_NAME_DETAIL = "//span[@class='orange-text']/following-sibling::h2";

    public void inputTextToSearch(String searchtext) {
        waitToElementVisible(SEARCH_TEXTBOX);
        sendkeyToElement(SEARCH_TEXTBOX,searchtext);
        sendKeyCodeToElement(SEARCH_TEXTBOX,Keys.ENTER);
    }
    public void goToResultDetail(String searchText){
        waitToElementVisible(SEARCH_RESULT_BRIEF);
        clickToElement(SEARCH_RESULT_BRIEF);
    }
    public String getDateTimeFromSearchResult(){
        waitToElementVisible(SEARCH_RESULT_DATE);
        String getDate = getTextElement(SEARCH_RESULT_DATE);
        return getDate;
    }
    public String getCityFromSearchResult(){
        waitToElementVisible(CITY_NAME_DETAIL);
        String getDate = getTextElement(CITY_NAME_DETAIL);
        return getDate;
    }
     public boolean checkDateTimeOfResult(String searchText){
        String timeStampCurrentDate = new SimpleDateFormat("MMM d, hh:mmaa").format(Calendar.getInstance().getTime());
        String timeStampCurrentDateFormat = timeStampCurrentDate.replace("AM", "am").replace("PM","pm");
        if (getDateTimeFromSearchResult().equals(timeStampCurrentDateFormat)) {
            return true;}
         else {return false;}
     }
     public boolean checkCityNameDisplay(String cityName){
         HashMap<String, String> listCountryToCheck;
         listCountryToCheck = MethodHelper.readCSV();
         String SEARCH_RESULT_BRIFT_FORMAT = String.format(CITY_NAME_DETAIL, cityName);
         waitToElementVisible(SEARCH_RESULT_BRIFT_FORMAT);
         if(getTextElement(SEARCH_RESULT_BRIFT_FORMAT).equals(listCountryToCheck.get(cityName)))
         {return true;}
         else {
             return false;
         }
     }
}