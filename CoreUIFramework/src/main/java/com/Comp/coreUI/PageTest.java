package com.Comp.coreUI;

import org.testng.annotations.Test;

public class PageTest extends BaseTest {

    @Test(description = "test")
    public void test()
    {
          BasePage p1 = new BasePage(driver);
          p1.gotoyahoo();
          p1.coreAssert.Log("hi");
    }
}
