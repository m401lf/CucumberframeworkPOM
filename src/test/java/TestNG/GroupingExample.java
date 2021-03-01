package TestNG;

import org.testng.annotations.Test;

public class GroupingExample {


    @Test(groups = {"group1"})
    void test1() {
        System.out.println("This is test1...");
    }

    @Test(groups = {"group1"})
    void test2() {
        System.out.println("This is test2...");
    }

    @Test(groups = {"group2"})
    void test3() {
        System.out.println("This is test3...");
    }


    @Test(groups = {"group2"})
    void test4() {
        System.out.println("This is test4...");
    }

    @Test(groups = {"group1","group2"})
    void test5() {
        System.out.println("This is test5...");
    }

}
