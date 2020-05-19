package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String FOLDER_BY_NAME_TPL;
    protected static String ARTICLE_BY_TITLE_TPL;
    protected static String SHARE_ARTICLE;
    protected static String REMOVE_FROM_SAVED_BUTTON;

    private static String getFolderXpathByName(String nameOfFolder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    private static String getRemoveButtonByTitle(String articleTitle){
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }


    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder){
        String folderNameXpath = getFolderXpathByName(nameOfFolder);
        this.waitForElementAndClick(folderNameXpath,
                "Cannot find folder by name " + nameOfFolder, 5);
    }

    private void waitForArticleToAppearByTitle(String articleTitle){
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementPresent(articleXpath,"Cannot find saved article by title " + articleTitle, 15);
    }
    private void waitForArticleToDisappearByTitle(String articleTitle){
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementNotPresent(articleXpath,"Saved article still present with title " + articleTitle, 30);
    }

    public void swipeByArticleToDelete(String articleTitle){
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        if(Platform.getInstance().isMW()){
            String removeLocator = getRemoveButtonByTitle(articleTitle);
            this.waitForElementAndClick(removeLocator,"Cannot click button to remove article from saved.", 10);
        }

        if(Platform.getInstance().isAndroid()){
            this.swipeElementToLeft(articleXpath, "Cannot find saved article");
        }
        if(Platform.getInstance().isIOS()){
            this.swipeElementToLeft(articleXpath + "/..", "Cannot find saved article");
            this.clickElementToTheRightUpperCorner(articleXpath, "Cannot find saved article");
        }
        if(Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void shareArticle(String articleTitle){
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.swipeElementToLeft(articleXpath + "/..", "Cannot find saved article");
        this.waitForElementAndClick(SHARE_ARTICLE, "Here is no share button", 10);
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void assertArticleIsPresent(String articleTitle){
        this.assertElementPresent(getSavedArticleXpathByTitle(articleTitle), "Needed article title " + articleTitle + " is absent");
    }

    public void clickBySavedArticle(String subString){
        String searchResultXpath = getSavedArticleXpathByTitle(subString);
        this.waitForElementAndClick(searchResultXpath, "Cannot find and click search result with substring " + subString, 15);
    }
}