package openweathermap.pageobjects.search;

import openweathermap.helpers.MethodHelper;
import openweathermap.pageobjects.AbstractPage;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class SearchDetailPage extends AbstractPage {
    public static final String SEARCH_RESULT_DATE = "//span[@class='orange-text']";
    public static final String CITY_NAME_DETAIL = "//span[@class='orange-text']/following-sibling::h2";
    public static final String TEMPERATURE = "//span[@class='heading']";

    public String getDateTimeFromSearchResult(){
        waitToElementVisible(SEARCH_RESULT_DATE);
        return getTextElement(SEARCH_RESULT_DATE);
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
        MethodHelper methodHelper = new MethodHelper();
        listCountryToCheck = methodHelper.readCSV();
        String CITY_NAME_DETAIL_FORMAT = String.format(CITY_NAME_DETAIL, cityName);
        waitToElementVisible(CITY_NAME_DETAIL_FORMAT);
        if(getTextElement(CITY_NAME_DETAIL_FORMAT).equals(listCountryToCheck.get(cityName)))
        {return true;}
        else {
            return false;
        }
    }
    public String getTemperature(){
        waitToElementVisible(TEMPERATURE);
        return getTextElement(TEMPERATURE);
    }
    public boolean checkTemperatureDisplay(){
        MethodHelper methodHelper = new MethodHelper();
        String[] arStr = getTemperature().split("°");
        if(methodHelper.isStringInteger(arStr[0])==true && methodHelper.isStringInteger(arStr[1])==false){
            return true;}
        else {
            return false;
        }
    }

    public boolean checkAllItemOfListResultDisplay(String searchText){
        Boolean flag = false;
        waitToElementVisible(SearchListPage.SEARCH_LIST_RESULT);
        List<WebElement> webElementList = getListElements(SearchListPage.SEARCH_LIST_RESULT);
        for(int i=1; i<= webElementList.size(); i++){
            String SEARCH_ITEM_RESULT_FORMAT = String.format(SearchListPage.SEARCH_ITEM_RESULT, i);
            waitToElementVisible(SEARCH_ITEM_RESULT_FORMAT);
            clickToElement(SEARCH_ITEM_RESULT_FORMAT);
            if(checkCityNameDisplay(searchText)==true && checkDateTimeOfResult(searchText)==true && checkTemperatureDisplay()==true){
                flag=true;
            }
            else {
                flag=false;
                break;
            }
            backToPage();
        }
        System.out.println("Lien " + flag);
        return flag;
    }

}
