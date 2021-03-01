package TestNG;

import org.testng.annotations.Test;

public class PriorityExample {

    @Test(priority=0)
    public void TestOne(){
        System.out.println("This is Test1.");
    }

    @Test(priority=1)
    public void TestTwo(){
        System.out.println("This is Test2.");
    }

    @Test(priority=2)
    public void TestThree(){
        System.out.println("This is Test3.");
    }

    @Test(priority=3)
    public void TestFour(){
        System.out.println("This is Test4.");
    }
}