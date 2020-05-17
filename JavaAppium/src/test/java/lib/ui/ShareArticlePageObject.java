package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ShareArticlePageObject extends MainPageObject {

    protected static String SHARE_ARTICLE;
    protected static String SHARE_ARTICLE_BY_STR;

    public ShareArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public String getSharedTitleName(){

        return this.waitForElementAndGetAttribute(SHARE_ARTICLE_BY_STR, "name", "Unable to get name of the element", 10);
    }

}
