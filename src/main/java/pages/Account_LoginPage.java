package pages;

import java.io.IOException;

import Utilities.CheckPoint;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Constant;

public class Account_LoginPage extends BasePage {

	public static String url = Constant.HOME_URL;

	public static String loginURL = Constant.LOGIN_URL;

	public @FindBy(xpath = "//a[contains(text(),'Login or register')]")
	WebElement button_LoginOrRegister;

	public @FindBy(id = "loginFrm_loginname")
	WebElement textField_LoginName;

	public @FindBy(css = "#loginFrm_password")
    WebElement textField_Password;

	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/fieldset[1]/button[1]")
	WebElement button_Login;

	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[1]/a[1]")
	WebElement text_ConfirmatiobOFSuccessfulLogin;

     // Unsuccessful Login attempts Variables declaration //

    public @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]")
	WebElement text_ConfirmatiobOFFailedLogin;

	public @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div/div/div[1]")
	WebElement text_ConfirmatiobOFFailedLoginwithblanksCredentials;

	public @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div/div/div[1]/text()")
	WebElement text_ConfirmatiobOFFailedLoginwithBadPassword;

	// Forget Password Varibale declaration //

	public @FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
	WebElement text_ForgotYourPassword;

	public @FindBy(xpath = "//a[contains(text(),'Forgot your login?')]")
	WebElement text_ForgotYourLogin;

	public @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/h1[1]/span[1]")
	WebElement text_ReturningCustomerConfirmationMessage;

	public @FindBy(xpath = "//h2[contains(text(),'Returning Customer')]")
	WebElement text_UserGetsConfirmationMessage;

	//Register Account Variable declarartions//

	public @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/fieldset[1]/div[1]/label[1]")
	WebElement button_RegisterAccount;

	public @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/fieldset[1]/button[1]")
	WebElement button_ContinueRegisterAccount;

	// Constructor //
	public Account_LoginPage() throws IOException {
		super();
	}
	public Account_LoginPage openBrowser(String url) throws IOException, InterruptedException {
		getDriver().get(url);
		System.out.println("This is the Current URL: " + getDriver().getCurrentUrl());
		log.info("This is the Current URL:" + getDriver().getCurrentUrl());
		return new Account_LoginPage();
	}
	public Account_LoginPage clickOnLoginOrRegister() throws Exception, Throwable {
		waitAndClickElement(button_LoginOrRegister);
		return new Account_LoginPage();
	}
	public Account_LoginPage enterLoginName(String LoginName) throws Exception {
		sendKeysToWebElement(textField_LoginName, LoginName);
		return new Account_LoginPage();
	}
	public Account_LoginPage enterPassword(String Password) throws Exception {
		sendKeysToWebElement(textField_Password, Password);
		return new Account_LoginPage();
	}
	public Account_LoginPage clickLoginButton() throws IOException, InterruptedException {
		waitAndClickElement(button_Login);
		return new Account_LoginPage();
	}
	public Account_LoginPage login(String LoginName, String Password) throws Exception {
		sendKeysToWebElement(textField_LoginName, LoginName);
		sendKeysToWebElement(textField_Password, Password);
		clickLoginButton();
		return new Account_LoginPage();
	}
	public Account_LoginPage clickForgetYourLogin() throws IOException, InterruptedException {
		waitAndClickElement(text_ForgotYourLogin);
		return new Account_LoginPage();
	}
	public Account_LoginPage clickForgetYourPassword() throws IOException, InterruptedException {
		waitAndClickElement(text_ConfirmatiobOFFailedLoginwithBadPassword);
		return new Account_LoginPage();
	}

	public Account_LoginPage getText_ConfirmatiobOFFailedLogin() throws IOException {
		text_ConfirmatiobOFFailedLogin.isDisplayed();
		log.info("This is a FAILURE Confirmation");
		return new Account_LoginPage();
	}
	public Account_LoginPage getUserGetsConfirmationMessage() throws IOException {
		isDisplayed(text_UserGetsConfirmationMessage);
		String FailureConfirmationMessage = getText(text_UserGetsConfirmationMessage);
		log.info("This is a FAILURE Confirmation message: " + FailureConfirmationMessage);
		System.out.println("This is a FAILURE Confirmation message: " + FailureConfirmationMessage);
		return new Account_LoginPage();
	}

	public void getText_FailureConfirmatiobOFFailedLoginwithblanksCredentialsURL() throws IOException {
		String ExpLoginURL;
		ExpLoginURL = accountLoginPage.getCurrentURL();
		System.out.println("This is a FAILURE Confirmation: " + ExpLoginURL);
		log.info("This is a FAILURE Confirmation: " + ExpLoginURL);
		return;
	}

	public Account_LoginPage getText_ConfirmatiobOFFailedLoginwithBadPassword() throws IOException {
		String ConfirmatiobOFFailedLoginwithblanksCredentials = getText1(text_ConfirmatiobOFFailedLoginwithBadPassword);
		System.out.println("This is a FAILURE Confirmation: " + ConfirmatiobOFFailedLoginwithblanksCredentials);
		log.info("This is a FAILURE Confirmation: " + ConfirmatiobOFFailedLoginwithblanksCredentials);
		return new Account_LoginPage();
	}

	public Account_LoginPage getText_ForgotYourPassword() throws IOException{
		String ConfirmatiobOFFailedLoginwithblanksCredentials = getText1(text_ForgotYourPassword);
		System.out.println("This is a FAILURE Confirmation: " + ConfirmatiobOFFailedLoginwithblanksCredentials);
		log.info("This is a FAILURE Confirmation: " + ConfirmatiobOFFailedLoginwithblanksCredentials);
		return new Account_LoginPage();
	}

	public Account_LoginPage getText_ForgotYourLogin() throws IOException {
		String ConfirmatiobOFFailedLoginwithblanksCredentials = getText1(text_ForgotYourLogin);
		System.out.println("This is a FAILURE Confirmation: " + ConfirmatiobOFFailedLoginwithblanksCredentials);
		log.info("This is a FAILURE Confirmation: " + ConfirmatiobOFFailedLoginwithblanksCredentials);
		return new Account_LoginPage();
	}

	public Account_LoginPage getReturningCustomerConfirmationMessage(String Text_Dispalyed) throws IOException {
		String ConfirmationUserStillOnLoginPage = getText1(text_ReturningCustomerConfirmationMessage);
		verifyTextContains(ConfirmationUserStillOnLoginPage,Text_Dispalyed);
		System.out.println("This is a FAILURE Confirmation: " + ConfirmationUserStillOnLoginPage);
		log.info("This is a FAILURE Confirmation: " + ConfirmationUserStillOnLoginPage);
		return new Account_LoginPage();
	}

	public Account_LoginPage confirmationLoginPageURL() throws IOException {
		String ConfirmationUserStillOnLoginPage = getText1(text_ReturningCustomerConfirmationMessage);
		verifyTextContains(ConfirmationUserStillOnLoginPage,loginURL);
		System.out.println("This is a FAILURE Confirmation: " + ConfirmationUserStillOnLoginPage);
		log.info("This is a FAILURE Confirmation: " + ConfirmationUserStillOnLoginPage);
		return new Account_LoginPage();
	}

}
