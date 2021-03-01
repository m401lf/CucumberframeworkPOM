package TestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataproviderExample {


    @Test(dataProvider="LoginDataProvider",dataProviderClass= CustomDataProvider.class)
    //@Test(dataProvider="LoginDataProvider")
    public void TestLogin(String email,String password){
        System.out.println(email +"--"+password);

    }

    @DataProvider(name="LoginDataProvider")
    public Object[][] getData(){

        Object[][] data = {{"abc@gmail@com","abc"},{"xyz@gmail.com","xyz"},{"mno@gmail.com","mno"}};
        return data;
    }

}
