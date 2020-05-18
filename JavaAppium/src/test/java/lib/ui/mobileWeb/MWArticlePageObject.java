package lib.ui.mobileWeb;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT ="css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON ="css:#page-actions li a#ca-watch.mw-ui-icon-wikimedia-star-base20";
        //PLACES_AND_CLOSE = "id:places auth close";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li a#ca-watch.watched";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
