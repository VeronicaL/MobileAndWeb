package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ShareArticlePageObject;
import lib.ui.ios.iOSShareArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ShareArticleFactory {

    public static ShareArticlePageObject get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()){
            return null;//no such page
        } else {
            return new iOSShareArticlePageObject(driver);
        }
    }
}
