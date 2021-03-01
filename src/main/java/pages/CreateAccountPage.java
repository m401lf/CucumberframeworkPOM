package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Constant;

import java.io.IOException;

public class CreateAccountPage extends BasePage {

	public static String url = Constant.HOME_URL;

	public static String loginURL = Constant.LOGIN_URL;

	public @FindBy(xpath = "//a[contains(text(),'Login or register')]")
	WebElement button_LoginOrRegister;

	public @FindBy(id = "loginFrm_loginname")
	WebElement textField_LoginName;

	// Constructor //
	public CreateAccountPage() throws IOException {
		super();
	}
	public CreateAccountPage clickOnLoginOrRegister() throws Exception, Throwable {
		waitAndClickElement(button_LoginOrRegister);
		return new CreateAccountPage();
	}

}
