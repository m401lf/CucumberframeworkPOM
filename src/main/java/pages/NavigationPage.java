package pages;

import java.io.IOException;

import Utilities.CheckPoint;
import Utilities.Util;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Constant;

public class NavigationPage extends BasePage {

    public NavigationPage() throws IOException {
        super();

        js = (JavascriptExecutor) driver;
    }


    public WebDriver driver;
    private JavascriptExecutor js;

    private final String URL = Constant.HOME_URL;

    public @FindBy(xpath = "//a[contains(text(),'Login or register')]")
    WebElement LOGIN_REGISTER_LINK;

    public @FindBy(xpath = "//a[contains(text(), 'My Courses')]")
    WebElement SPECIALS_LINK;

    public @FindBy(xpath = "gravatar")
    WebElement ACCOUNT_LINK;

    public @FindBy(xpath = "//a[@href='/sign_in']")
    WebElement CART_LINK;

    public @FindBy(xpath = "//a[@href='/sign_out']")
    WebElement CHECKOUT_LINK;

    public String getHomeURL() throws InterruptedException {
        getDriver().get(URL);
        waitForSpecificPage(URL);

        return URL;
    }
    public boolean getLOGIN_REGISTER_LINK() throws IOException, InterruptedException {
        isDisplayed(LOGIN_REGISTER_LINK);
//        waitAndClickElement(LOGIN_REGISTER_LINK);
        WebElement LOGINREGISTERLINK = LOGIN_REGISTER_LINK;
        //CheckPoint.mark("Test=001", null,"Verifying Current URL");
        String text = LOGINREGISTERLINK.getText();
        //CheckPoint.markFinal("Test=001", null,"Verifying Current URL");
        return Util.verifyTextContains(text,"Register");
    }
    public WebElement getSPECIALS_LINK() throws IOException, InterruptedException {
        isDisplayed(SPECIALS_LINK);
        waitAndClickElement(SPECIALS_LINK);
        return SPECIALS_LINK;
    }
    public WebElement getACCOUNT_LINK() throws IOException, InterruptedException {
        isDisplayed(ACCOUNT_LINK);
        waitAndClickElement(ACCOUNT_LINK);
        return ACCOUNT_LINK;
    }
    public WebElement getCART_LINK() throws IOException, InterruptedException {
        isDisplayed(CART_LINK);
        waitAndClickElement(CART_LINK);
        return CART_LINK;
    }
    public WebElement getCHECKOUT_LINK() throws IOException, InterruptedException {
        isDisplayed(CHECKOUT_LINK);
        waitAndClickElement(CHECKOUT_LINK);
        return CHECKOUT_LINK;
    }

}