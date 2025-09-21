package com.Comp.coreUI;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.utils.FileUtil;
import com.sun.source.tree.WhileLoopTree;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Obejctive of this class in initialize driver class used in Pages
 * before interacting with browser for any element to make sure element is there and perform the action.
 * this will save repeatative code pages where for each element wait will be needed to perform and then the action.
 */
public class BasePage {
private WebDriver _driver;
private WebDriverWait wait;
private JavascriptExecutor jsE;
public BaseAssert coreAssert;

public BasePage(WebDriver driver)
{
    _driver = driver;
    wait = new WebDriverWait(_driver, Duration.ofSeconds(30));
    jsE = (JavascriptExecutor) _driver;

    coreAssert= new BaseAssert(new SoftAssert());

}
public void gotoyahoo()
{
    _driver.get("http://www.yahoo.com");
}
public void Sleep(int ms) throws InterruptedException {
    Thread.sleep(ms);
}

public void SendKeys(By locator, String text)
{
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);

}
public  void Click(By locator)
{
    wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

}

public List<WebElement> FindElements(By locator)
{
    return _driver.findElements(locator);
}
public void SelectByText(By locator, String text)
{
    new Select(_driver.findElement(locator)).selectByVisibleText(text);
}

public boolean IsVisible(By locator)
{
    Boolean isVisible = false;
    try {
      isVisible=  wait.until((ExpectedConditions.visibilityOfElementLocated(locator))).isDisplayed();
    }
    catch (Exception e)
    {
        throw e;
    }
    return isVisible;
}

public void SwitchToPopUpWindow()
{
    String MainWindow = _driver.getWindowHandle();
    Set<String> S1 = _driver.getWindowHandles();
    Iterator<String> il = S1.iterator();
    while(il.hasNext()) {
      String ChildWindow = il.next();
      if(!MainWindow.equalsIgnoreCase(ChildWindow))
      {
          _driver.switchTo().window(ChildWindow);

      }
    }
}
public void WaitForCondition(By locator, String message) throws IOException {
    try
    {
        wait.until((ExpectedConditions.visibilityOfElementLocated(locator)));
        TakeScreenShots(message);
    }
    catch (Exception e)
    {
        TakeScreenShots(e.getMessage());
        throw e;
    }
}
public void TakeScreenShots(String message) throws IOException {
    File src =((TakesScreenshot) _driver).getScreenshotAs(OutputType.FILE);
    String Filename = Utility.GetAlphaNumericString(4)+".png";
    Files.copy(src.toPath(),new File("Reports//"+Filename).toPath());
    ExtentTestManager.GetTest().log(Status.INFO,message, MediaEntityBuilder.createScreenCaptureFromPath(Filename).build());
}



}
