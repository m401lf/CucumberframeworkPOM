package pages;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountDashboardPage extends BasePage {
	
	public @FindBy(css = "body.account-account:nth-child(2) div.container-fixed:nth-child(1) div.container-fluid div.col-md-9.col-xs-12.mt20 div.ct_padding_right h1.heading1 > span.maintext")
	WebElement text_ConfirmatiobOFSuccessfulLogin;

	public @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[10]/a[1]")
	WebElement button_Logoff;
	
	public AccountDashboardPage() throws IOException{
		super();
	}
	public AccountDashboardPage confirmSuccessfulLogin() throws IOException, InterruptedException {
		String exp = text_ConfirmatiobOFSuccessfulLogin.getText();
		String act = "MY ACCOUNT";
		assertEquals(act, exp);
		return new AccountDashboardPage();
	}
		public AccountDashboardPage clickLoginButton() throws IOException, InterruptedException {
		waitAndClickElement(button_Logoff);
		return new AccountDashboardPage();
    }

}
