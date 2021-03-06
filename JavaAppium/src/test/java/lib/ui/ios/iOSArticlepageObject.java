package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlepageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT ="id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON ="id:Save for later";
        CLOSE_ARTICLE_BUTTON ="id:Back";
        PLACES_AND_CLOSE = "id:places auth close";
    }

    public iOSArticlepageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
