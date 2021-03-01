package steps;

import Utilities.CheckPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.DriverFactory;

import java.io.IOException;

public class LoginSteps extends DriverFactory {

    public static Logger log = LogManager.getLogger(LoginSteps.class.getName());

    @Given("^User is on landing page as \"([^\"]*)\"$")
    public void user_is_on_landing_page_as(String url) throws Throwable {
        getDriver().get(url);
        log.info("Url is navigated");
    }

    @When("^User clicks the LoginRegister button$")
    public void user_clicks_the_LoginRegister_button() throws Throwable {
        accountLoginPage.clickOnLoginOrRegister();

    }

    @When("^User enters Login Name as \"([^\"]*)\"$")
    public void user_enters_Login_Name_as(String LoginName) throws Throwable {
        accountLoginPage.enterLoginName(LoginName);
    }

    @When("^User enters Password as \"([^\"]*)\"$")
    public void user_enters_Password_as(String Password) throws Throwable {
        accountLoginPage.enterPassword(Password);
    }

    @When("^User clicks on Login button$")
    public void user_clicks_on_Login_button() throws Throwable {
        accountLoginPage.clickLoginButton();
    }

    @Then("^User is on Dashboard Page$")
    public void user_is_on_Dashboard_Page() throws Throwable {
        accountDashboardPage.confirmSuccessfulLogin();
    }

    @Then("^User clicks logoff button$")
    public void user_clicks_logoff_button() throws Throwable {
        accountDashboardPage.clickLoginButton();
    }

    @Then("^User gets a confirmation message$")
    public void user_gets_a_confirmation_message() throws IOException {
        accountLoginPage.getUserGetsConfirmationMessage();
    }

    @Then("^User is still in login page as \"([^\"]*)\"$")
    public void user_is_still_in_login_page_as(String Text_Dispalyed) throws Throwable {
        accountLoginPage.getReturningCustomerConfirmationMessage(Text_Dispalyed);
        accountLoginPage.getText_FailureConfirmatiobOFFailedLoginwithblanksCredentialsURL();
    }

}
