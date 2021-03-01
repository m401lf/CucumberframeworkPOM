package utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import pages.AccountDashboardPage;
import pages.BasePage;
import pages.Account_LoginPage;
import pages.NavigationPage;


public class DriverFactory {
	public static WebDriver driver;
	public static BasePage basePage;
	public static Account_LoginPage accountLoginPage;
	public static AccountDashboardPage accountDashboardPage;
	public static NavigationPage NaviPage;

	

	public WebDriver getDriver() {
		try {
			// Read Config
			Properties p = new Properties();
			FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\properties\\config.properties");
			p.load(fi);
			String browserName = p.getProperty("browser");

			switch (browserName) {

			case "firefox":
				// code
				if (null == driver) {
					System.setProperty("webdriver.gecko.driver", Constant.GECKO_DRIVER_DIRECTORY);
					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
					capabilities.setCapability("marionette", true);
					driver = new FirefoxDriver();
				}
				break;

			case "chrome":
				// code
				if (null == driver) {
					System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
					// CHROME OPTIONS
					driver = new ChromeDriver();
					driver.manage().window().maximize();
				}
				break;
			}
		} catch (Exception e) {
			System.out.println("Unable to load browser: " + e.getMessage());
		} finally {
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			accountLoginPage = PageFactory.initElements(driver, Account_LoginPage.class);
			accountDashboardPage = PageFactory.initElements(driver, AccountDashboardPage.class);
			basePage = PageFactory.initElements(driver, BasePage.class);
			NaviPage = PageFactory.initElements(driver, NavigationPage.class);
		}
		return driver;
	}
}