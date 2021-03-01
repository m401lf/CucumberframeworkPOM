package TestNG;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import utils.DriverFactory;

public class AssertionsExamples extends DriverFactory {

    public AssertionsExamples() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


    @Test
    void logoTest()
    
    {
    	driver.get("https://opensource-demo.orangehrmlive.com/");
    	
        WebElement logos=driver.findElement(By.xpath("//*[@id=\"divLogo\"]/img"));
        //Assert.assertTrue(logos.isDisplayed(),"Logo not displayed");
     }

    @Test
    void homePageTitleTest()
    {
        //Assert.assertEquals("OrangeHRM",driver.getTitle(),"Title not matched");
    }

    @AfterClass
    void tearDown()
    {
        driver.quit();
    }


}
