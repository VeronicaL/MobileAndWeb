package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.*;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    private static final String login = "Veronica148Lap";
    private static final String password = "123VerWiki_1";

    @Test
    public void testCompareArticle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = articlePageObject.getArticleTitle();
        System.out.println("articleTitle:"+articleTitle);
        assertEquals("We see unexpected title","Java (programming language)",articleTitle);
    }

    @Test
    public void testSwipeArticle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testCheckManipulationWithTwoArticles(){
        String searchString = "Java";
        String firstArticleLongTitle = "bject-oriented programming language";
        String secondArticleLongTitle = "rogramming language";
        String shortSecondArticleTitle = "avaScript";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchString);
        searchPageObject.clickByArticleWithSubstring(firstArticleLongTitle);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();

        String firstArticleTitle = articlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";

        if(Platform.getInstance().isAndroid()){
            articlePageObject.addArticleToMyList(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clivkAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();
            articlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login.", firstArticleTitle, articlePageObject.getArticleTitle());
            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchString);
        searchPageObject.clickByArticleWithSubstring(secondArticleLongTitle);

        String secondArticleTitle = articlePageObject.getArticleTitle();
        if(Platform.getInstance().isAndroid() ) {
            articlePageObject.addNotFirstArticleToMyList();
        } else if(Platform.getInstance().isIOS()) {
            articlePageObject.addMoreThenOneArticleToMySaved();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameOfFolder);
        }
        myListsPageObject.swipeByArticleToDelete(firstArticleTitle);

        if(Platform.getInstance().isAndroid()){
            myListsPageObject.assertArticleIsPresent(secondArticleTitle);
            searchPageObject.clickByArticleWithSubstring(secondArticleTitle);
            String articleTitle = articlePageObject.getArticleTitle();
            assertTrue("Title of second saved article and opened are differ: " +
                    secondArticleTitle + " and " + articleTitle, articleTitle.equals(secondArticleTitle));
        } else if(Platform.getInstance().isIOS()){
            myListsPageObject.shareArticle(shortSecondArticleTitle);
            ShareArticlePageObject shareArticlePageObject = ShareArticleFactory.get(driver);
            String sharedTitle = shareArticlePageObject.getSharedTitleName();
            assertTrue("Title of second saved article and shared are differ: " +
                    shortSecondArticleTitle + " and " + sharedTitle, sharedTitle.contains(shortSecondArticleTitle));
        } else if(Platform.getInstance().isMW()) {
            myListsPageObject.assertArticleIsPresent(secondArticleTitle);
            myListsPageObject.clickBySavedArticle(secondArticleTitle);
            String articleTitle = articlePageObject.getArticleTitle();
            assertTrue("Title of second saved article and opened are differ: " +
                    secondArticleTitle + " and " + articleTitle, articleTitle.equals(secondArticleTitle));
        }
    }


    @Test
    public void testCheckArticleTitle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Appium";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring(searchLine);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.assertTitleIsPresent();
    }
}
