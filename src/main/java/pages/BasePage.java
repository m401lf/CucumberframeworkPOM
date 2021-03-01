package pages;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cucumber.listener.Reporter;
import com.google.common.collect.Ordering;
import utils.DriverFactory;

public class BasePage extends DriverFactory{
	protected static WebDriverWait wait;
	protected static JavascriptExecutor jsExecutor;
	protected static String screenshotName;
	protected static Logger log = LogManager.getLogger(BasePage.class.getName());
	protected static JavascriptExecutor js;

	public BasePage() throws IOException {
		BasePage.wait = new WebDriverWait(driver, 30);
		jsExecutor = ((JavascriptExecutor) driver);
	}

	public static void TypeText(WebElement element, String data) {
		element.sendKeys(data);
	}

	public static void TypeTextIfElementPresent(WebElement element, String data) {
		if (element.isDisplayed()) {
			element.sendKeys(data);
		}
	}

	public static void clearTxtBox(WebElement element) {
		element.clear();
	}

	public static void typeRandomNumber(WebElement element, int data) {
		Random random = new Random();
		int rn = random.nextInt(100);
		String randomNumber = Integer.toString(rn);
		element.sendKeys(randomNumber);
	}

	public static String getTextBoxValue(WebElement element) {
		return element.getAttribute("value");
	}

	public static String getText1(WebElement element){
		return element.getText();
	}

	public static boolean VerifyTextEquals(WebElement element,String expected){
		boolean flag=false;
		if(element.getText().equals(expected))
			return flag=true;
		return flag;
	}

	public static String getTitle1(){
		return driver.getTitle();
	}

	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}

	public boolean isSelected(WebElement element) {
		if (element.isSelected())
			return true;
		return false;
	}

	public void selectCheckBox(WebElement element) {
		if (!isSelected(element))
			element.click();
	}

	public void deSelectCheckbox(WebElement element) {
		if (isSelected(element))
			element.click();
	}

	public void selectRadioButton(WebElement element) {
		if (!isSelected(element))
			element.click();
	}

	public void deSelectRadioButton(WebElement element) {
		if (isSelected(element))
			element.click();
	}

	public boolean isEnabled(WebElement element){
		if(element.isEnabled())
			return true;
		return false;
	}

	public boolean isDisplayed1(WebElement element){
		if(element.isDisplayed())
			return true;
		return false;
	}

	public void selectByText(WebElement element,String text){
		Select select=new Select(element);
		select.selectByVisibleText(text);
	}

	public void selectByIndex(WebElement element,int index){
		Select select=new Select(element);
		select.selectByIndex(index);
	}

	public void selectByValue(WebElement element,String value){
		Select select=new Select(element);
		select.selectByValue(value);
	}

	public String getFirstSelectedOption(WebElement element){
		Select select=new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public List<WebElement> getAllSelectedOptions(WebElement element){
		Select select=new Select(element);
		return select.getAllSelectedOptions();
	}

	public List<WebElement> getAllOptions(WebElement element){
		Select select=new Select(element);
		return select.getOptions();
	}

	public void deSelectByText(WebElement element,String text){
		Select select=new Select(element);
		select.deselectByVisibleText(text);
	}

	public void deSelectByIndex(WebElement element,int index){
		Select select=new Select(element);
		select.deselectByIndex(index);
	}

	public void deSelectByValue(WebElement element,String value){
		Select select=new Select(element);
		select.deselectByValue(value);
	}

	public void clickOnlyIfElementPresent(WebElement element){
		if(isDisplayed1(element))
			element.click();
	}

	public Alert getAlert1() {
		return getDriver().switchTo().alert();
	}

	public void AcceptAlert() {
		getAlert1().accept();
	}

	public void DismissAlert() {
		getAlert1().dismiss();
	}

	public String getAlertText1() {
		String text = getAlert1().getText();
		return text;
	}

	public boolean isAlertPresent11() {
		try {
			getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public void AcceptAlertIfPresent() {
		if (!isAlertPresent11())
			return;
		AcceptAlert();
	}

	public void DismissAlertIfPresent() {

		if (!isAlertPresent11())
			return;
		DismissAlert();
	}

	public void AcceptPrompt(String text) {

		if (!isAlertPresent11())
			return;

		Alert alert = getAlert1();
		alert.sendKeys(text);
		alert.accept();

	}


	public void Select_The_Checkbox(WebElement element) {
		try {
			if (element.isSelected()) {
				System.out.println("Checkbox: " + element + "is already selected");
			} else {
				// Select the checkbox
				element.click();
			}
		} catch (Exception e) {
			System.out.println("Unable to select the checkbox: " + element);
		}

	}


	public void DeSelect_The_Checkbox(WebElement element) {
		try {
			if (element.isSelected()) {
				//De-select the checkbox
				element.click();
			} else {
				System.out.println("Checkbox: "+element+"is already deselected");
			}
		} catch (Exception e) {
			System.out.println("Unable to deselect checkbox: "+element);
		}
	}


	public void Select_The_CheckBox_from_List(WebElement element, String valueToSelect) {
		List<WebElement> allOptions = element.findElements(By.tagName("input"));
		for (WebElement option : allOptions) {
			System.out.println("Option value "+option.getText());
			if (valueToSelect.equals(option.getText())) {
				option.click();
				break;
			}
		}
	}


	/**********************************************************************************
	 **CLICK METHODS
	 * @throws IOException
	 *********************************************************************************@param element */
	public void waitAndClickElement(WebElement element) throws InterruptedException, IOException {
		boolean clicked = false;
		int attempts = 0;
		while (!clicked && attempts < 10) {
			try {
				BasePage.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				log.info("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
				System.out.println("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
				clicked = true;
			} catch (Exception e) {
				log.info("Unable to wait and click on WebElement, Exception: " + e.getMessage());
				System.out.println("Unable to wait and click on WebElement, Exception: " + e.getMessage());
				Assert.fail("Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
			}
			attempts++;
		}
	}

	public void waitAndClickElementsUsingByLocator(By by) throws InterruptedException {
		boolean clicked = false;
		int attempts = 0;
		while (!clicked && attempts < 10) {
			try {
				BasePage.wait.until(ExpectedConditions.elementToBeClickable(by)).click();
				log.info("Successfully clicked on the element using by locator: " + "<" + by.toString() + ">");
				System.out.println("Successfully clicked on the element using by locator: " + "<" + by.toString() + ">");
				clicked = true;
			} catch (Exception e) {
				log.info("Unable to wait and click on the element using the By locator, Exception: " + e.getMessage());
				System.out.println("Unable to wait and click on the element using the By locator, Exception: " + e.getMessage());
				Assert.fail("Unable to wait and click on the element using the By locator, element: " + "<"+ by.toString() + ">");
			}
			attempts++;
		}
	}

	public void clickOnTextFromDropdownList(WebElement list, String textToSearchFor) throws Exception {
		Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);
		try {
			tempWait.until(ExpectedConditions.elementToBeClickable(list)).click();
			list.sendKeys(textToSearchFor);
			list.sendKeys(Keys.ENTER);
			System.out.println("Successfully sent the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to send the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
			Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
		}
	}


	public void clickOnElementUsingCustomTimeout(WebElement locator, WebDriver driver, int timeout) {
		try {
			final WebDriverWait customWait = new WebDriverWait(driver, timeout);
			customWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
			locator.click();
			log.info("Successfully clicked on the WebElement, using locator: " + "<" + locator + ">"+ ", using a custom Timeout of: " + timeout);
			System.out.println("Successfully clicked on the WebElement, using locator: " + "<" + locator + ">"+ ", using a custom Timeout of: " + timeout);
		} catch (Exception e) {
			log.info("Unable to click on the WebElement, using locator: " + "<" + locator + ">" + ", using a custom Timeout of: " + timeout);
			System.out.println("Unable to click on the WebElement, using locator: " + "<" + locator + ">" + ", using a custom Timeout of: " + timeout);
			Assert.fail("Unable to click on the WebElement, Exception: " + e.getMessage());
		}
	}

	/**********************************************************************************/

	/**********************************************************************************/
	public static void sleep(long msec, String info) {
		if (info != null) {
			System.out.println("Waiting " + (msec * .001) + " seconds :: " + info);
			log.info("Waiting " + (msec * .001) + " seconds :: " + info);

		}
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void sleep(long msec) {
		sleep(msec, null);
	}

	public static String getScreenshotName(String methodName, String browserName) {
		String localDateTime = getCurrentDateTime();
		StringBuilder name = new StringBuilder().append(browserName)
				.append("_")
				.append(methodName)
				.append("_")
				.append(localDateTime)
				.append(".png");
		return name.toString();
	}

	public static int getRandomNumber(int min, int max) {
		int diff = max - min;
		int randomNum = (int)(min + Math.random() * diff);
		System.out.println("Random Number :: " + randomNum +
				" within range :: " + min + " and :: " + max);
		return randomNum;
	}

	public static int getRandomNumber(int number) {
		return getRandomNumber(1, number);
	}

	public static String getRandomString(int length) {
		StringBuilder sbuilder = new StringBuilder();
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for (int i = 0; i<length; i++) {
			int index = (int) (Math.random() * chars.length());
			sbuilder.append(chars.charAt(index));
		}
		String randomString = sbuilder.toString();
		System.out.println("Random string with length :: "
				+ length + " is :: " + randomString);
		return randomString;
	}

	public static String getRandomString() {
		return getRandomString(10);
	}

	public static String getSimpleDateFormat(String format){
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String formattedDate = formatter.format(date);
		System.out.println("Date with format :: "
				+ format + " :: " + formattedDate);
		return formattedDate;
	}

	public static String getCurrentDateTime(){
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		String date = formatter.format(currentDate.getTime()).replace("/", "_");
		date = date.replace(":", "_");
		System.out.println("Date and Time :: " + date);
		return date;
	}

	public static boolean verifyTextContains(String actualText, String expText) {
		if (actualText.toLowerCase().contains(expText.toLowerCase())){
			System.out.println("Actual Text From Web Application UI   --> : "+ actualText);
			System.out.println("Expected Text From Web Application UI --> : "+ expText);
			System.out.println("### Verification Contains !!!");
			return true;
		}
		else{
			System.out.println("Actual Text From Web Application UI   --> : "+ actualText);
			System.out.println("Expected Text From Web Application UI --> : "+ expText);
			System.out.println("### Verification DOES NOT Contains !!!");
			return false;
		}

	}

	public static boolean verifyTextMatch(String actualText, String expText) {
		if (actualText.equals(expText)){
			System.out.println("Actual Text From Web Application UI   --> : "+ actualText);
			System.out.println("Expected Text From Web Application UI --> : "+ expText);
			System.out.println("### Verification MATCHED !!!");
			return true;
		}else{
			System.out.println("Actual Text From Web Application UI   --> : "+ actualText);
			System.out.println("Expected Text From Web Application UI --> : "+ expText);
			System.out.println("### Verification DOES NOT MATCH !!!");
			return false;
		}
	}

	public static Boolean verifyListContains(List<String> actList, List<String> expList) {
		int expListSize = expList.size();
		for (int i = 0; i < expListSize; i++) {
			if (!actList.contains(expList.get(i))) {
				return false;
			}
		}
		System.out.println("Actual List Contains Expected List !!!");
		return true;
	}

	public static Boolean verifyListMatch(List<String> actList, List<String> expList) {
		boolean found = false;
		int actListSize = actList.size();
		int expListSize = expList.size();
		if (actListSize != expListSize) {
			return false;
		}

		for (int i = 0; i < actListSize; i++) {
			found = false;
			for (int j = 0; j < expListSize; j++) {
				if (verifyTextMatch(actList.get(i), expList.get(j))) {
					found = true;
					break;
				}
			}
		}
		if (found) {
			System.out.println("Actual List Matches Expected List !!!");
			return true;
		}
		else {
			System.out.println("Actual List DOES NOT Match Expected List !!!");
			return false;
		}
	}

	public static Boolean verifyItemPresentInList(List<String> actList, String item){
		int actListSize = actList.size();
		for (int i = 0; i < actListSize; i++) {
			if (!actList.contains(item)) {
				System.out.println("Item is NOT present in List !!!");
				return false;
			}
		}
		System.out.println("Item is present in List !!!");
		return true;
	}

	public static boolean isListAscendingOrder(List<Long> list){
		boolean sorted = Ordering.natural().isOrdered(list);
		return sorted;
	}


	/**********************************************************************************
	 **from Automationpractice.com
	 **********************************************************************************/
	public static boolean isPresent(WebDriver webdriver, By selector) {
		// try to find element by specified selector
		try {
			webdriver.findElement(selector);
		} catch (NoSuchElementException e) {
			// if element not exist return false
			return false;
		}
		return true;
	}

	public static WebElement waitToBeClickable(WebDriver driver, By selector, int waitInterval) {
		WebElement element = (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.elementToBeClickable(selector));
		return element;
	}
	public static WebElement waitForElementPresence(WebDriver driver, By selector, int waitInterval) {
		WebElement element = (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.presenceOfElementLocated(selector));
		return element;
	}

	public static void waitForTitle(WebDriver driver, String title, int waitInterval){
		(new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.titleIs(title));
	}



	public static void wait(int w) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(w, TimeUnit.SECONDS);
	}

	public static Actions getAction() {
		Actions action = new Actions(driver);
		return action;
	}

	public static WebElement chooseElement(int x,String path){

		WebElement webElement = null;

		switch (x){
			case 1:
				webElement=driver.findElement(By.id(path));
				break;
			case 2:
				webElement=driver.findElement(By.className(path));
				break;
			case 3:
				webElement=driver.findElement(By.linkText(path));
				break;
			case 4:
				webElement=driver.findElement(By.xpath(path));
				break;
			case 5:
				webElement=driver.findElement(By.cssSelector(path));
				break;
			case 6:
				webElement = driver.findElement(By.tagName(path));
				break;
		}
		return webElement;
	}


	public static Select ddElement(WebElement webElement, int dx,Object dindex){

		Select select = new Select(webElement);

		switch (dx){
			case 1:
				System.out.println("case 1");
				select.selectByVisibleText((String) dindex);
				break;
			case 2:
				System.out.println("case 2");
				select.selectByValue((String) dindex);
				break;
			case 3:
				System.out.println("case 3");
				select.selectByIndex((int) dindex);
				break;
		}
		return select;
	}

	public static void mouseOver(int x, String path) throws InterruptedException {
		WebElement mO=chooseElement(x, path);
		getAction().moveToElement(mO).perform();
	}

	public static void textBox (int x, String path, String text) throws InterruptedException {
		chooseElement(x, path).sendKeys(text);
		getAction().sendKeys(Keys.ESCAPE);
	}

	public static void click(int x, String path) throws InterruptedException {
		chooseElement(x, path).click();
	}

	public static String getTxt(int x, String path) throws InterruptedException {
		String returnText = chooseElement(x, path).getText();
		return returnText;
	}
	public static void dropDown(int x, String path, int dx,Object dindex) throws InterruptedException {
		try {
			WebElement webElement=chooseElement(x, path);
			ddElement(webElement,dx,dindex); // Value index
		}
		catch (NoSuchElementException e) {

		}

	}
	public boolean waitForElementToBeClickableBool(WebDriver    driver, By attributeValue, int waitTime) {
		boolean flag = false;
		try{
			new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(attributeValue));
			flag=true;
			return flag;

		}catch(Exception Ex){
			return flag;
		}
	}


	public boolean waitForAlertPresent(WebDriver driver, int waitTime) {
		boolean flag = false;
		new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.alertIsPresent());
		try{
			driver.switchTo().alert();
			return flag = true;
		}catch(Exception Ex){
			return flag;
		}
	}

	public boolean waitForElementToBeVisible(WebDriver driver, By attributeValue, int waitTime) {
		boolean flag = false;
		try {
			new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(attributeValue));
			flag=true;
			return flag;

		} catch (Exception Ex) {
			return flag;
		}
	}


	/**
	 * @Method:getcurrenttime This method is used to return system time in
	 *                        seconds.
	 */
	public static int getcurrenttime() {

		long currentDateMS = new Date().getTime();
		int seconds = (int) ((currentDateMS / 1000) % 3600);
		return seconds;

	}

	public static boolean closeAllOtherWindows(WebDriver driver) throws InterruptedException {
		String Parent_Window = driver.getWindowHandle();
		java.util.Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(Parent_Window)) {
				driver.switchTo().window(currentWindowHandle);
				driver.close();
				Thread.sleep(2000);
			}
		}

		driver.switchTo().window(Parent_Window);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public static void dropDownSelectionByText(WebDriver driver, By dropDownID, String dropDownValue) {
		try {
			WebElement element = null;
			new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(dropDownID));

			element = driver.findElement(dropDownID);
			Select dropDown = new Select(element);
			dropDown.selectByVisibleText(dropDownValue);
		}

		catch (StaleElementReferenceException ex) {
			System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
		}
	}

	public static void dropDownSelectionByValue(WebDriver driver, By dropDownID, String dropDownValue) {
		try {
			WebElement element = null;
			new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(dropDownID));

			element = driver.findElement(dropDownID);
			Select dropDown = new Select(element);
			dropDown.selectByValue(dropDownValue);
		}

		catch (StaleElementReferenceException ex) {
			System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
		}

	}
	public static void dropDownSelectionByIndex(WebDriver driver, By dropDownID, int dropDownValue) {
		try {
			WebElement element = null;
			new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(dropDownID));

			element = driver.findElement(dropDownID);
			Select dropDown = new Select(element);
			dropDown.selectByIndex(dropDownValue);
		}

		catch (StaleElementReferenceException ex) {
			System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
		}

	}

	/**********************************************************************************
	 **ACTION METHODS
	 **********************************************************************************/

	public void actionMoveAndClick(WebElement element) throws Exception {
		Actions ob = new Actions(driver);
		try {
			BasePage.wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			ob.moveToElement(element).click().build().perform();
			System.out.println("Successfully Action Moved and Clicked on the WebElement, using locator: " + "<" + element.toString() + ">");
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = element;
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).isEnabled();
			if (elementPresent == true) {
				ob.moveToElement(elementToClick).click().build().perform();
				log.info("(Stale Exception) - Successfully Action Moved and Clicked on the WebElement, using locator: " + "<" + element.toString() + ">");
				System.out.println("(Stale Exception) - Successfully Action Moved and Clicked on the WebElement, using locator: " + "<" + element.toString() + ">");
			}
		} catch (Exception e) {
			log.info("Unable to Action Move and Click on the WebElement, using locator: " + "<" + element.toString() + ">");
			System.out.println("Unable to Action Move and Click on the WebElement, using locator: " + "<" + element.toString() + ">");
			Assert.fail("Unable to Action Move and Click on the WebElement, Exception: " + e.getMessage());
		}
	}

	public void actionMoveAndClickByLocator(By element) throws Exception {
		Actions ob = new Actions(driver);
		try {
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			if (elementPresent == true) {
				WebElement elementToClick = driver.findElement(element);
				ob.moveToElement(elementToClick).click().build().perform();
				System.out.println("Action moved and clicked on the following element, using By locator: " + "<" + element.toString() + ">");
			}
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = driver.findElement(element);
			ob.moveToElement(elementToClick).click().build().perform();
			System.out.println("(Stale Exception) - Action moved and clicked on the following element, using By locator: "+ "<" + element.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to Action Move and Click on the WebElement using by locator: " + "<" + element.toString() + ">");
			Assert.fail("Unable to Action Move and Click on the WebElement using by locator, Exception: " + e.getMessage());
		}
	}

	/**********************************************************************************/



	/**********************************************************************************/


	/**********************************************************************************
	 **SEND KEYS METHODS /
	 **********************************************************************************/
	public void sendKeysToWebElement(WebElement element, String textToSend) throws Exception {
		try {
			this.WaitUntilWebElementIsVisible(element);
			element.clear();
			element.sendKeys(textToSend);
			log.info("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<"+ element.toString() + ">");
			System.out.println("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<"+ element.toString() + ">");
		} catch (Exception e) {
			log.info("Unable to locate WebElement: " + "<" + element.toString() + "> and send the following key: " + textToSend);
			System.out.println("Unable to locate WebElement: " + "<" + element.toString() + "> and send the following keys: " + textToSend);
			Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/


	/**********************************************************************************
	 **JS METHODS & JS SCROLL
	 **********************************************************************************/
	public void scrollToElementByWebElementLocator(WebElement element) {
		try {
			BasePage.wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -400)");
			System.out.println("Succesfully scrolled to the WebElement, using locator: " + "<" + element.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to scroll to the WebElement, using locator: " + "<" + element.toString() + ">");
			Assert.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
		}
	}

	public void jsPageScroll(int numb1, int numb2) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(" + numb1 + "," + numb2 + ")");
			System.out.println("Succesfully scrolled to the correct position! using locators: " + numb1 + ", " + numb2);
		} catch (Exception e) {
			System.out.println("Unable to scroll to element using locators: " + "<" + numb1 + "> " + " <" + numb2 + ">");
			Assert.fail("Unable to manually scroll to WebElement, Exception: " + e.getMessage());
		}
	}

	public static void waitAndclickElementUsingJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			js.executeScript("arguments[0].click();", element);
			log.info("Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
			System.out.println("Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement staleElement = element;
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(staleElement)).isEnabled();
			if (elementPresent == true) {
				js.executeScript("arguments[0].click();", elementPresent);
				log.info("(Stale Exception) Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
				System.out.println("(Stale Exception) Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Unable to JS click on the following WebElement: " + "<" + element.toString() + ">");
			Assert.fail("Unable to JS click on the WebElement, Exception: " + e.getMessage());
		}
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**********************************************************************************/
	/**********************************************************************************/
	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info("Implicit Wait has been set to: " + timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}

	public void waitForElement(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}

	/**********************************************************************************
	 **WAIT METHODS
	 *********************************************************************************
	 * @param element*/
	public void WaitUntilWebElementIsVisible(WebElement element) {
		try {
			BasePage.wait.until(ExpectedConditions.visibilityOf(element));
			log.info("WebElement is visible using locator: " + "<" + element.toString() + ">");
			System.out.println("WebElement is visible using locator: " + "<" + element.toString() + ">");
		} catch (Exception e) {
			log.info("WebElement is NOT visible, using locator: " + "<" + element.toString() + ">");
			System.out.println("WebElement is NOT visible, using locator: " + "<" + element.toString() + ">");
			Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
		}
	}

	public boolean WaitUntilWebElementIsVisibleUsingByLocator(By element) {
		try {
			BasePage.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			log.info("Element is visible using By locator: " + "<" + element.toString() + ">");
			System.out.println("Element is visible using By locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			log.info("WebElement is NOT visible, using By locator: " + "<" + element.toString() + ">");
			System.out.println("WebElement is NOT visible, using By locator: " + "<" + element.toString() + ">");
			Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
			return false;
		}
	}

	public boolean isElementClickable(WebElement element) {
		try {
			BasePage.wait.until(ExpectedConditions.elementToBeClickable(element));
			System.out.println("WebElement is clickable using locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			System.out.println("WebElement is NOT clickable using locator: " + "<" + element.toString() + ">");
			return false;
		}
	}


	public boolean waitUntilPreLoadElementDissapears(By element) {
		return BasePage.wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	/**********************************************************************************/
	/**********************************************************************************/


	/**********************************************************************************
	 **PAGE METHODS
	 **********************************************************************************/
	public BasePage loadUrl(String url) throws Exception {
		driver.get(url);
		return new BasePage();
	}


	public String getCurrentURL() {
		try {
			String url = driver.getCurrentUrl();
			log.info("Found(Got) the following URL: " + url);
			System.out.println("Found(Got) the following URL: " + url);
			return url;
		} catch (Exception e) {
			log.info("Unable to locate (Get) the current URL, Exception: " + e.getMessage());
			System.out.println("Unable to locate (Get) the current URL, Exception: " + e.getMessage());
			return e.getMessage();
		}
	}

	public String waitForSpecificPage(String urlToWaitFor) {
		try {
			String url = driver.getCurrentUrl();
			BasePage.wait.until(ExpectedConditions.urlMatches(urlToWaitFor));
			log.info("The current URL was: " + url + ", " + "navigated and waited for the following URL: "+ urlToWaitFor);
			System.out.println("The current URL was: " + url + ", " + "navigated and waited for the following URL: "+ urlToWaitFor);
			return urlToWaitFor;
		} catch (Exception e) {
			log.info("Exception! waiting for the URL: " + urlToWaitFor + ",  Exception: " + e.getMessage());
			System.out.println("Exception! waiting for the URL: " + urlToWaitFor + ",  Exception: " + e.getMessage());
			return e.getMessage();
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/


	/**********************************************************************************
	 **ALERT & POPUPS METHODS
	 **********************************************************************************/
	public void closePopups(By locator) throws InterruptedException {
		try {
			List<WebElement> elements = BasePage.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			for (WebElement element : elements) {
				if (element.isDisplayed()) {
					element.click();
					BasePage.wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
					System.out.println("The popup has been closed Successfully!");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception! - could not close the popup!, Exception: " + e.toString());
			throw (e);
		}
	}

	public boolean checkPopupIsVisible() {
		try {
			@SuppressWarnings("unused")
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			log.info("A popup has been found!");
			System.out.println("A popup has been found!");
			return true;
		} catch (Exception e) {
			log.info("Error came while waiting for the alert popup to appear. " + e.getMessage());
			System.err.println("Error came while waiting for the alert popup to appear. " + e.getMessage());
		}
		return false;
	}

	public boolean isAlertPresent1() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void closeAlertPopupBox() throws AWTException, InterruptedException {
		try {
			Alert alert = BasePage.wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
		} catch (UnhandledAlertException f) {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			log.info("closed the popup");
			System.out.println("closed the popup");
		} catch (Exception e) {
			log.info("Unable to close the popup");
			System.out.println("Unable to close the popup");
			Assert.fail("Unable to close the popup, Exception: " + e.getMessage());
		}
	}
	/**********************************************************************************/


	/**********************************************************************************/

	/**
	 * Refresh the current browser session
	 */
	public void refresh() {
		driver.navigate().refresh();
		log.info("The Current Browser location was Refreshed...");
		System.out.println("The Current Browser location was Refreshed...");
		//Util.sleep(3000, "The Current Browser location was Refreshed...");
	}

	/**
	 * @return Returns the Current Page Title
	 */
	public String getTitle2() {
		String title = driver.getTitle();
		log.info("Title of the page is :: " + title);

		return title;
	}

	/**
	 * @return Current Browser URL
	 */
	public String getURL() throws Exception {
		String url = driver.getCurrentUrl();
		log.info("Current URL is :: " + url);
		return url;
	}

	/**
	 * Navigate browser back
	 */
	public void navigateBrowserBack() {
		driver.navigate().back();
		log.info("Navigate back");
	}

	/**
	 * Navigate browser forward
	 */
	public void navigateBrowserForward() {
		driver.navigate().back();
		log.info("Navigate back");
	}


	public By getByType(String locator) {
		By by = null;
		String locatorType = locator.split("=>")[0];
		locator = locator.split("=>")[1];
		try {
			if (locatorType.contains("id")) {
				by = By.id(locator);
			} else if (locatorType.contains("name")) {
				by = By.name(locator);
			} else if (locatorType.contains("xpath")) {
				by = By.xpath(locator);
			} else if (locatorType.contains("css")) {
				by = By.cssSelector(locator);
			} else if (locatorType.contains("class")) {
				by = By.className(locator);
			} else if (locatorType.contains("tag")) {
				by = By.tagName(locator);
			} else if (locatorType.contains("link")) {
				by = By.linkText(locator);
			} else if (locatorType.contains("partiallink")) {
				by = By.partialLinkText(locator);
			} else {
				log.info("Locator type not supported");
			}
		} catch (Exception e) {
			log.error("By type not found with: " + locatorType);
		}
		return by;
	}

	public WebElement getElement(String locator, String info) {
		WebElement element = null;
		By byType = getByType(locator);
		try {
			element = driver.findElement(byType);
		} catch (Exception e) {
			log.error("Element not found with: " + locator);
			e.printStackTrace();
		}
		return element;
	}

	/***
	 *
	 * @param locator - locator strategy, id=>example, name=>example, css=>#example,
	 *      *                tag=>example, xpath=>//example, link=>example
	 * @param info - Information about element, usually text on element
	 * @return
	 */
	public List<WebElement> getElementList(String locator, String info) {
		List<WebElement> elementList = new ArrayList<WebElement>();
		By byType = getByType(locator);
		try {
			elementList = driver.findElements(byType);
			if (elementList.size() > 0) {
				log.info("Element List found with: " + locator);
			} else {
				log.info("Element List not found with: " + locator);
			}
		} catch (Exception e) {
			log.error("Element List not found with: " + locator);
			e.printStackTrace();
		}
		return elementList;
	}

	/***
	 * Check if element is present
	 * @param locator locator strategy, id=>example, name=>example, css=>#example,
	 *      *                tag=>example, xpath=>//example, link=>example
	 * @return boolean if element is present or not
	 */
	public boolean isElementPresent(String locator, String info) {
		List<WebElement> elementList = getElementList(locator, info);
		int size = elementList.size();
		if (size > 0) {
			log.info("Element " + info + " Present with locator " + locator);
			return true;
		} else {
			log.info("Element " + info + " Not Present with locator " + locator);
			return false;
		}
	}

	/**
	 * Click element with information about element and
	 * time to wait in seconds after click
	 *
	 * @param element - WebElement to perform Click operation
	 * @param info    - information about element
	 */
	public void elementClick(WebElement element, String info, long timeToWait) {
		try {
			element.click();
			if (timeToWait == 0) {
				log.info("Clicked On :: " + info);
			} else {
				sleep(timeToWait, "Clicked on :: " + info);
			}
		} catch (Exception e) {
			log.error("Cannot click on :: " + info);
			takeScreenshot("Click ERROR", "");
		}
	}

	/**
	 * Click element with no time to wait after click
	 *
	 * @param element - WebElement to perform Click operation
	 * @param info    - information about element
	 */
	public void elementClick(WebElement element, String info) {
		elementClick(element, info, 0);
	}

	/**
	 * Click element with locator
	 * @param locator - locator strategy, id=>example, name=>example, css=>#example,
	 *      *                tag=>example, xpath=>//example, link=>example
	 * @param info
	 * @param timeToWait
	 * @return
	 */
	public void elementClick(String locator, String info, long timeToWait) {
		WebElement element = this.getElement(locator, info);
		elementClick(element, info, timeToWait);
	}

	/**
	 * Click element with locator and no time to wait
	 * @param locator - locator strategy, id=>example, name=>example, css=>#example,
	 *      *                tag=>example, xpath=>//example, link=>example
	 * @param info - Information about element
	 * @return
	 */
	public void elementClick(String locator, String info) {
		WebElement element = getElement(locator, info);
		elementClick(element, info, 0);
	}

	/***
	 * Click element using JavaScript
	 * @param element - WebElement to perform Click operation
	 * @param info - Information about element
	 */
	public void javascriptClick(WebElement element, String info) {
		try {
			js.executeScript("arguments[0].click();", element);
			log.info("Clicked on :: " + info);
		} catch (Exception e) {
			log.error("Cannot click on :: " + info);
		}
	}

	/***
	 * Click element using JavaScript
	 * @param locator - locator strategy, id=>example, name=>example, css=>#example,
	 *      *                tag=>example, xpath=>//example, link=>example
	 * @param info - Information about element
	 */
	public void javascriptClick(String locator, String info) {
		WebElement element = getElement(locator, info);
		try {
			js.executeScript("arguments[0].click();", element);
			log.info("Clicked on :: " + info);
		} catch (Exception e) {
			log.error("Cannot click on :: " + info);
		}
	}

	/***
	 * Click element when element is clickable
	 * @param locator - locator strategy, id=>example, name=>example, css=>#example,
	 *      *                tag=>example, xpath=>//example, link=>example
	 * @param timeout - Duration to try before timeout
	 */
	public void clickWhenReady(By locator, int timeout) {
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebElement element = null;
			log.info("Waiting for max:: " + timeout + " seconds for element to be clickable");

			WebDriverWait wait = new WebDriverWait(driver, 15);
			element = wait.until(
					ExpectedConditions.elementToBeClickable(locator));
			element.click();
			log.info("Element clicked on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("Element not appeared on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
	}

	/***
	 * Send Keys to element
	 * @param element - WebElement to send data
	 * @param data - Data to send
	 * @param info - Information about element
	 * @param clear - True if you want to clear the field before sending data
	 */
	public void sendData(WebElement element, String data, String info, Boolean clear) {
		try {
			if (clear) {
				element.clear();
			}
			//Util.sleep(1000, "Waiting Before Entering Data");
			element.sendKeys(data);
			log.info("Send Keys on element :: " + info + " with data :: " + data);
		} catch (Exception e) {
			log.error("Cannot send keys on element :: " + info + " with data :: " + data);
		}
	}

	/***
	 * Send Keys to element with locator
	 * @param locator - locator strategy, id=>example, name=>example, css=>#example,
	 *      *                tag=>example, xpath=>//example, link=>example
	 * @param data - Data to send
	 * @param info - Information about element
	 * @param clear - True if you want to clear the field before sending data
	 */
	public void sendData(String locator, String data, String info, Boolean clear) {
		WebElement element = this.getElement(locator, info);
		sendData(element, data, info, clear);
	}

	/***
	 * Send Keys to element with clear
	 * @param element - WebElement to send data
	 * @param data - Data to send
	 * @param info - Information about element
	 */
	public void sendData(WebElement element, String data, String info) {
		sendData(element, data, info, true);
	}

	/***
	 * Send Keys to element with locator and clear
	 * @param locator - locator strategy, id=>example, name=>example, css=>#example,
	 *      *                tag=>example, xpath=>//example, link=>example
	 * @param data - Data to send
	 * @param info - Information about element
	 */
	public void sendData(String locator, String data, String info) {
		WebElement element = getElement(locator, info);
		sendData(element, data, info, true);
	}

	/**
	 * Get text of a web element
	 *
	 * @param element - WebElement to perform click action
	 * @param info    - Information about element
	 */
	public String getText(WebElement element, String info) {
		System.out.println("Getting Text on element :: " + info);
		String text = null;
		text = element.getText();
		if (text.length() == 0) {
			text = element.getAttribute("innerText");
		}
		if (!text.isEmpty()) {
			log.info("The text is : " + text);
		} else {
			log.error("Text Not Found");
		}
		return text.trim();
	}

	/**
	 * Get text of a web element with locator
	 * @param locator
	 * @param info
	 * @return
	 */
	public String getText(String locator, String info) {
		WebElement element = this.getElement(locator, info);
		return this.getText(element, info);
	}

	/**
	 * Check if element is enabled
	 * @param element
	 * @param info
	 * @return
	 */
	public Boolean isEnabled(WebElement element, String info) {
		Boolean enabled = false;
		if (element != null) {
			enabled = element.isEnabled();
			if (enabled)
				log.info("Element :: " + info + " is Enabled");
			else
				log.info("Element :: " + info + " is Disabled");
		}
		return enabled;
	}

	/***
	 * Check if element is enabled with locator
	 * @param locator
	 * @param info
	 * @return
	 */
	public Boolean isEnabled(String locator, String info) {
		WebElement element = getElement(locator, info);
		return this.isEnabled(element, info);
	}

	/**
	 * Check if element is displayed
	 * @param element
	 * @param info
	 * @return
	 */
	public Boolean isDisplayed(WebElement element, String info) {
		Boolean displayed = false;
		if (element != null) {
			displayed = element.isDisplayed();
			if (displayed)
				log.info("Element :: " + info + " is displayed");
			else
				log.info("Element :: " + info + " is NOT displayed");
		}
		return displayed;
	}

	/***
	 * Check if element is displayed with locator
	 * @param locator
	 * @param info
	 * @return
	 */
	public Boolean isDisplayed(String locator, String info) {
		WebElement element = getElement(locator, info);
		return this.isDisplayed(element, info);
	}

	/**
	 * @param element
	 * @param info
	 * @return
	 */
	public Boolean isSelected(WebElement element, String info) {
		Boolean selected = false;
		if (element != null) {
			selected = element.isSelected();
			if (selected)
				log.info("Element :: " + info + " is selected");
			else
				log.info("Element :: " + info + " is already selected");
		}
		return selected;
	}

	/**
	 * @param locator
	 * @param info
	 * @return
	 */
	public Boolean isSelected(String locator, String info) {
		WebElement element = getElement(locator, info);
		return isSelected(element, info);
	}

	/**
	 * Selects a check box irrespective of its state
	 *
	 * @param element
	 * @param info
	 */
	public void Check(WebElement element, String info) {
		if (!isSelected(element, info)) {
			elementClick(element, info);
			log.info("Element :: " + info + " is checked");
		} else
			log.info("Element :: " + info + " is already checked");
	}

	/**
	 * Selects a check box irrespective of its state, using locator
	 *
	 * @param locator
	 * @param info
	 * @return
	 */
	public void Check(String locator, String info) {
		WebElement element = getElement(locator, info);
		Check(element, info);
	}

	/**
	 * UnSelects a check box irrespective of its state
	 *
	 * @param element
	 * @param info
	 * @return
	 */
	public void UnCheck(WebElement element, String info) {
		if (isSelected(element, info)) {
			elementClick(element, info);
			log.info("Element :: " + info + " is unchecked");
		} else
			log.info("Element :: " + info + " is already unchecked");
	}

	/**
	 * UnSelects a check box irrespective of its state, using locator
	 *
	 * @param locator
	 * @param info
	 * @return
	 */
	public void UnCheck(String locator, String info) {
		WebElement element = getElement(locator, info);
		UnCheck(element, info);
	}

	/**
	 * @param element
	 * @param info
	 * @return
	 */
	public Boolean Submit(WebElement element, String info) {
		if (element != null) {
			element.submit();
			log.info("Element :: " + info + " is submitted");
			return true;
		} else
			return false;
	}

	/**
	 * @param locator
	 * @param attribute
	 * @return
	 */
	public String getElementAttributeValue(String locator, String attribute) {
		WebElement element = getElement(locator, "info");
		return element.getAttribute(attribute);
	}

	/**
	 * @param element
	 * @param attribute
	 * @return
	 */
	public String getElementAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	/**
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElement(String locator, int timeout) {
		By byType = getByType(locator);
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			log.info("Waiting for max:: " + timeout + " seconds for element to be available");
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(
					ExpectedConditions.visibilityOfElementLocated(byType));
			log.info("Element appeared on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("Element not appeared on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		return element;
	}

	/***
	 * Wait for element to be clickable
	 * @param locator - locator strategy, id=>example, name=>example, css=>#example,
	 *      *                tag=>example, xpath=>//example, link=>example
	 * @param timeout - Duration to try before timeout
	 */
	public WebElement waitForElementToBeClickable(String locator, int timeout) {
		By byType = getByType(locator);
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			log.info("Waiting for max:: " + timeout + " seconds for element to be clickable");

			WebDriverWait wait = new WebDriverWait(driver, 15);
			element = wait.until(
					ExpectedConditions.elementToBeClickable(byType));
			log.info("Element is clickable on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("Element not appeared on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		return element;
	}

	/**
	 *
	 */
	public boolean waitForLoading(String locator, long timeout) {
		By byType = getByType(locator);
		boolean elementInvisible = false;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			log.info("Waiting for max:: " + timeout + " seconds for element to be available");
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			elementInvisible = wait.until(
					ExpectedConditions.invisibilityOfElementLocated(byType));
			log.info("Element appeared on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("Element not appeared on the web page");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		return elementInvisible;
	}

	/**
	 * Mouse Hovers to an element
	 *
	 * @param locator
	 */
	public void mouseHover(String locator, String info) {
		WebElement element = getElement(locator, info);
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		//Util.sleep(5000);
	}

	/**
	 * @param element
	 * @param optionToSelect
	 */
	public void selectOption(WebElement element, String optionToSelect) {
		Select sel = new Select(element);
		sel.selectByVisibleText(optionToSelect);
		log.info("Selected option : " + optionToSelect);
	}

	/**
	 * Selects a given option in list box
	 *
	 * @param locator
	 * @param optionToSelect
	 */
	public void selectOption(String locator, String optionToSelect, String info) {
		WebElement element = getElement(locator, info);
		this.selectOption(element, optionToSelect);
	}

	/**
	 * get Selected drop down value
	 *
	 * @param element
	 * @return
	 */
	public String getSelectDropDownValue(WebElement element) {
		Select sel = new Select(element);
		return sel.getFirstSelectedOption().getText();
	}

	/**
	 * @param element
	 * @param optionToVerify
	 */
	public boolean isOptionExists(WebElement element, String optionToVerify) {
		Select sel = new Select(element);
		boolean exists = false;
		List<WebElement> optList = sel.getOptions();
		for (int i = 0; i < optList.size(); i++) {
			String text = getText(optList.get(i), "Option Text");
			if (text.matches(optionToVerify)) {
				exists = true;
				break;
			}
		}
		if (exists) {
			log.info("Selected Option : " + optionToVerify + " exist");
		} else {
			log.info("Selected Option : " + optionToVerify + " does not exist");
		}
		return exists;
	}

	/***
	 *
	 * @param methodName
	 * @param browserName
	 * @return
	 */
	public String takeScreenshot(String methodName, String browserName) {
		String fileName = getScreenshotName(methodName, browserName);
		String screenshotDir = System.getProperty("user.dir") + "//" + "test-output/screenshots";
		new File(screenshotDir).mkdirs();
		String path = screenshotDir + "//" + fileName;

		try {
			File screenshot = ((TakesScreenshot)driver).
					getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
			log.info("Screen Shot Was Stored at: "+ path);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public void DoubleClick(WebElement element, String info) {
		Actions action = new Actions(driver);
		action.doubleClick(element);
		log.info("Double Clicked on :: " + info);
		action.perform();
	}

	/**
	 * Right Click a WebElement
	 *
	 * @param locator
	 */
	public void rightClick(String locator, String info) {
		WebElement element = getElement(locator, info);
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
		log.info("Double Clicked on :: " + info);
	}

	/**
	 * Right click a WebElement and select the option
	 *
	 * @param elementLocator
	 * @param itemLocator
	 */
	public void selectItemRightClick(String elementLocator, String itemLocator) {
		WebElement element = getElement(elementLocator, "info");
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
		WebElement itemElement = getElement(itemLocator, "info");
		elementClick(itemElement, "Selected Item");
	}

	/**
	 * @param key
	 */
	public void keyPress(Keys key, String info) {
		Actions action = new Actions(driver);
		action.keyDown(key).build().perform();
		log.info("Key Pressed :: " + info);
	}


	/**********************************************************************************/
	public static void verifyText(String s1, String s2){
		log.info("veryfing test: "+ s1 + " with "+ s2);
		Assert.assertEquals(s1, s1);
	}

	public static void makeTrue(){
		log.info("making script PASS..");
		Assert.assertTrue(true);
	}

	public static void makeTrue(String message){
		log.info("making script PASS.."+ message);
		Assert.assertTrue(true);
	}

	public static void makeFalse(){
		log.info("making script FAIL..");
		Assert.assertTrue(false);
	}

	public static void makeFalse(String message){
		log.info("making script FAIL.."+message);
		Assert.assertTrue(false);
	}

	public static void verifyTrue(boolean status){
		Assert.assertTrue(status);
	}

	public static void verifyFalse(boolean status){
		Assert.assertFalse(status);
	}

	public static void verifyNull(String s1){
		log.info("verify object is null..");
		Assert.assertNull(s1);
	}

	public static void verifyNotNull(String s1){
		log.info("verify object is not null..");
		Assert.assertNotNull(s1);
	}

	public boolean isDisplayed(WebElement element){
		try{
			element.isDisplayed();
			log.info("element is Displayed.."+element.getText());
			return true;
		}
		catch(Exception e){
			log.error("element is not Displayed..", e.getCause());
			return false;
		}
	}

	public boolean isNotDisplayed(WebElement element){
		try{
			element.isDisplayed();
			log.info("element is present.."+element.getText());
			return false;
		}
		catch(Exception e){
			log.error("element is not present..");
			return true;
		}
	}

	public String getText(WebElement element){
		if(null == element){
			log.info("WebElement is null..");
			return null;
		}
		boolean status = isDisplayed1(element);
		if(status){
			log.info("element text is .."+element.getText());
			return element.getText();
		}
		else{
			return null;
		}
	}

	public String getTitle(){
		log.info("page title is: "+driver.getTitle());
		return driver.getTitle();
	}

	public Alert getAlert(){
		log.info("alert test: "+driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	}

	public void acceptAlert(){
		getAlert1().accept();
		log.info("accept Alert is done...");
	}

	public void dismissAlert(){
		getAlert1().dismiss();
		log.info("dismiss Alert is done...");
	}

	public String getAlertText(){
		String text = getAlert1().getText();
		log.info("alert text: "+text);
		return text;
	}

	public boolean isAlertPresent(){
		try{
			driver.switchTo().alert();
			log.info("alert is present");
			return true;
		}
		catch(NoAlertPresentException e){
			log.info(e.getCause());
			return false;
		}
	}

	public void acceptAlertIfPresent(){
		if(isAlertPresent11()){
			acceptAlert();
		}
		else{
			log.info("Alert is not present..");
		}
	}

	public void dismissAlertIfPresent(){
		if(isAlertPresent11()){
			dismissAlert();
		}
		else{
			log.info("Alert is not present..");
		}
	}

	public void acceptPrompt(String text){
		if(isAlertPresent11()){
			Alert alert = getAlert1();
			alert.sendKeys(text);
			alert.accept();
			log.info("alert text: "+text);
		}
	}

	public void selectUsingValue(WebElement element, String value){
		Select select = new Select(element);
		log.info("selectUsingValue and value is: "+value);
		select.selectByValue(value);
	}

	public void selectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
		log.info("selectUsingIndex and index is: "+index);
		select.selectByIndex(index);
	}

	public void selectUsingVisibleText(WebElement element, String visibleText){
		Select select = new Select(element);
		log.info("selectUsingVisibleText and visibleText is: "+visibleText);
		select.selectByVisibleText(visibleText);
	}

	public void deSelectUsingValue(WebElement element, String value){
		Select select = new Select(element);
		log.info("deSelectUsingValue and value is: "+value);
		select.deselectByValue(value);
	}

	public void deSelectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
		log.info("deSelectUsingIndex and index is: "+index);
		select.deselectByIndex(index);
	}

	public void deSelectUsingVisibleText(WebElement element, String visibleText){
		Select select = new Select(element);
		log.info("deselectByVisibleText and visibleText is: "+visibleText);
		select.deselectByVisibleText(visibleText);
	}

	public List<String> getAllDropDownData(WebElement element){
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for(WebElement ele: elementList){
			log.info(ele.getText());
			valueList.add(ele.getText());
		}
		return valueList;
	}
	// get the nuber of rows and columns and return it as Map
/*	public Map<String, Integer> getTableSize(){
		Map<String, Integer> tableSize = new HashMap<>();
		tableSize.put("rows", getRowCount());
		tableSize.put("columns", getColumnCount());
		return tableSize;
	}*/
	/*/ get the column data and return as list
public List columnData(int columnNumber) throws Exception{
	if(columnNumber == 0){
		throw new Exception("Column number starts from 1");
	}
	List<WebElement> column = table.findElements(By.xpath("//tr/td["+columnNumber+"]"));
	List<String> cData = new ArrayList<>();
	for (WebElement webElement : column) {
		cData.add(webElement.getText());
	}
	return cData;
}*
	/**
	 * This will help us to get WebDriverWait object
	 *
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	/**
	 * This method will make sure element is visible
	 *
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds,
													 int pollingEveryInMiliSec) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}

	/**
	 * This method will make sure elementToBeClickable
	 *
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForElementClickable(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("element is clickable now");
	}

	/**
	 * This method will make sure invisibilityOf element
	 *
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("element is invisibile now");
		return status;
	}

	/**
	 * This method will wait for frameToBeAvailableAndSwitchToIt
	 *
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForframeToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("frame is available and switched");
	}

	/**
	 * This method will give is fluentWait object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	private Wait<WebDriver> getfluentWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec)).ignoring(NoSuchElementException.class);
		return fWait;
	}

	/**
	 *
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec){
		Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingEveryInMiliSec);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public void pageLoadTime(long timeout, TimeUnit unit){
		log.info("waiting for page to load for : "+ unit+ " seconds");
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("page is loaded");
	}


	/**
	 * this method will switchToFrame based on frame index
	 * @param frameIndex
	 */
	public void switchToFrame(int frameIndex){
		driver.switchTo().frame(frameIndex);
		log.info("switched to :"+ frameIndex+" frame");
	}

	/**
	 * this method will switchToFrame based on frame name
	 * @param frameName
	 */
	public void switchToFrame(String frameName){
		driver.switchTo().frame(frameName);
		log.info("switched to :"+ frameName+" frame");
	}

	/**
	 * this method will switchToFrame based on frame WebElement
	 * @param element
	 */
	public void switchToFrame(WebElement element){
		driver.switchTo().frame(element);
		log.info("switched to frame "+element.toString());
	}

	/**
	 * This method will switch to parent window
	 */
	public void switchToParentWindow() {
		log.info("switching to parent window...");
		driver.switchTo().defaultContent();
	}

	/**
	 * This method will switch to child window based on index
	 * @param index
	 */
	public void switchToWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for (String window : windows) {
			if (i == index) {
				log.info("switched to : "+index + " window");
				driver.switchTo().window(window);
			} else {
				i++;
			}
		}
	}

	/**
	 * This method will close all tabbed window and
	 * switched to main window
	 */
	public void closeAllTabsAndSwitchToMainWindow() {
		Set<String> windows = driver.getWindowHandles();
		String mainwindow = driver.getWindowHandle();

		for (String window : windows) {
			if (!window.equalsIgnoreCase(mainwindow)) {
				driver.close();
			}
		}
		log.info("switched to main window");
		driver.switchTo().window(mainwindow);
	}

	/**
	 * This method will do browser back navigation
	 */
	public void navigateBack(){
		log.info("navigating back");
		driver.navigate().back();
	}

	/**
	 * This method will do browser forward navigation
	 */
	public void navigateForward(){
		log.info("navigating forward");
		driver.navigate().forward();
	}

	/**
	 * This method will execute java script
	 * @param script
	 * @return
	 */
	public Object executeScript(String script){
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		return exe.executeScript(script);
	}

	/**
	 * This method will execute Java script with multiple arguments
	 * @param script
	 * @param args
	 * @return
	 */
	public Object executeScript(String script, Object...args){
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		return exe.executeScript(script,args);
	}

	/**
	 * This method will scroll till element
	 * @param element
	 */
	public void scrollToElement(WebElement element){
		log.info("scroll to WebElement...");
		executeScript("window.scrollTo(arguments[0],arguments[1])",element.getLocation().x,element.getLocation().y);
	}

	/**
	 * Scroll till element and click
	 * @param element
	 */
	public void scrollToElementAndClick(WebElement element){
		scrollToElement(element);
		element.click();
		log.info("element is clicked: "+element.toString());
	}

	/**
	 * Scroll till element view
	 * @param element
	 */
	public void scrollIntoView(WebElement element){
		log.info("scroll till web element");
		executeScript("arguments[0].scrollIntoView()",element);
	}

	/**
	 * Scroll till element view and click
	 * @param element
	 */
	public void scrollIntoViewAndClick(WebElement element){
		scrollIntoView(element);
		element.click();
		log.info("element is clicked: "+element.toString());
	}

	/**
	 * This method will scroll down vertically
	 */
	public void scrollDownVertically(){
		log.info("scrolling down vertically...");
		executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	/**
	 * This method will scroll up vertically
	 */
	public void scrollUpVertically(){
		log.info("scrolling up vertically...");
		executeScript("window.scrollTo(0,-document.body.scrollHeight)");
	}

	/**
	 * This method will scroll till given pixel (e.g=1500)
	 * @param pixel
	 */
	public void scrollDownByPixel(int pixel){
		executeScript("window.scrollBY(0,"+pixel+")");
	}

	public void scrollUpByPixel(int pixel){
		executeScript("window.scrollBY(0,-"+pixel+")");
	}

	/**
	 * This method will zoom screen by 100%
	 */
	public void zoomInBy100Percentage(){
		executeScript("document.body.style.zoom='100%'");
	}

	/**
	 * This method will zoom screen by 60%
	 */
	public void zoomInBy60Percentage(){
		executeScript("document.body.style.zoom='60%'");
	}

	/**
	 * This method will click on element
	 * @param element
	 */
	public void clickElement(WebElement element){
		executeScript("arguments[0].click();", element);
	}



	/***EXTENT REPORT****************************************************************/
	public static String returnDateStamp(String fileExtension) {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_") + fileExtension;
		return date;
	}

	public static void captureScreenshot() throws IOException, InterruptedException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		screenshotName = returnDateStamp(".jpg");

		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\output\\imgs\\" + screenshotName));

		Reporter.addStepLog("Taking a screenshot!");
		Reporter.addStepLog("<br>");
		Reporter.addStepLog("<a target=\"_blank\", href="+ returnScreenshotName() + "><img src="+ returnScreenshotName()+ " height=200 width=300></img></a>");
	}

	public static String returnScreenshotName() {
		return (System.getProperty("user.dir") + "\\output\\imgs\\" + screenshotName).toString();
	}

	private static void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;

		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;

			while((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}

		} finally {
			is.close();
			os.close();
		}
	}

	public static void copyLatestExtentReport() throws IOException {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_");
		File source = new File(System.getProperty("user.dir") + "\\output\\report.html");
		File dest = new File(System.getProperty("user.dir") + "\\output\\" + date.toString() + ".html");
		copyFileUsingStream(source, dest);
	}
}
