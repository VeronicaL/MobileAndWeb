package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String SEARCH_INIT_ELEMENT;
    protected static String SEARCH_INPUT;
    protected static String SEARCH_CANCEL_BUTTON;
    protected static String SEARCH_CLOSE_BUTTON;
    protected static String SEARCH_RESULT_BY_TPL;
    protected static String SEARCH_RESULT_ELEMENT;
    protected static String SEARCH_EMPTY_RESULT_ELEMENT;
    protected static String SEARCH_RESULT_TITLE_AND_DESC_TPL;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_TPL.replace("{SUBSTRING}",substring);
    }

    private static String getDescSearchElement(String substring){
        return SEARCH_RESULT_TITLE_AND_DESC_TPL.replace("{TITLE_DESC}",substring).replace("{title_desc}","description");
    }

    private static String getTitleSearchElement(String substring){
        return SEARCH_RESULT_TITLE_AND_DESC_TPL.replace("{TITLE_DESC}",substring).replace("{title_desc}","title");
    }

    private static String getTitleDescrSearchElement(String title, String desc){
        return SEARCH_RESULT_TITLE_AND_DESC_TPL.replace("{TITLE_DESC}",desc).replace("{title}", title);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void clickOnCloseButton(){
        this.waitForElementAndClick(SEARCH_CLOSE_BUTTON,"No 'x' sign", 5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is present", 5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndClear(SEARCH_INPUT, " Cannot find and clear search input", 10);
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,
                "Cannot find and type into search input", 10);
    }

    public void waitForSearchResult(String subString){
        String searchResultXpath = getResultSearchElement(subString);
        this.waitForElementPresent(searchResultXpath, "Cannot find search result with substring " + subString);
    }

    public void clickByArticleWithSubstring(String subString){
        String searchResultXpath = getResultSearchElement(subString);
        this.waitForElementAndClick(searchResultXpath, "Cannot find and click search result with substring " + subString, 15);
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public int articlesAmount(){
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,"Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public void waitForElementByTitleAndDescription(String title, String description){
        System.out.println("We are searching for title: " + title + " and desc: " + description);
        if (Platform.getInstance().isAndroid()) {
            this.assertElementPresent(getTitleSearchElement(title),
                    "Here is no article with title '" + title + "' among search results.");
            this.assertElementPresent(getDescSearchElement(description),
                    "Here is no article with description '" + description + "' among search results.");
        } else if (Platform.getInstance().isIOS()) {
            String xpathTitle = SEARCH_RESULT_TITLE_AND_DESC_TPL.replace("{TITLE_DESC}", title);
            this.assertElementPresent(xpathTitle,
                    "Here is no article with title '" + title + "' among search results.");
            String xpathDesc = SEARCH_RESULT_TITLE_AND_DESC_TPL.replace("{TITLE_DESC}", description);
            this.assertElementPresent(xpathDesc,
                    "Here is no article with description '" + description + "' among search results.");
        } else if (Platform.getInstance().isMW()) {
            this.assertElementPresent(getTitleDescrSearchElement(title, description),
                    "Here is no article with title '" + title + "' and decription '" + description + "' among search results.");
        }
    }

}
