package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_CLOSE_BUTTON = "id:Close";
        SEARCH_RESULT_BY_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_TITLE_AND_DESC_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE_DESC}']";
    }

    public IOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}