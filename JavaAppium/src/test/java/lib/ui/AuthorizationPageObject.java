package lib.ui;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private static final String
            LOGIN_BUTTON = "xpath://body//div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            LOGIN_BOX = "xpath://div[contains(@class,'position-fixed visible')]",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clivkAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON,"Cannot find auth button", 10);
        this.waitForElementPresent(LOGIN_BOX,"Cannot find login box button", 10);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 10);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put login to login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put password", 5);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 5);
    }

}
