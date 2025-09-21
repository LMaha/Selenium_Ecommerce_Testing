# Ecommerce Automation Testing
This project demonstrate testing of ecommerce site(TechPanda) with selenium, java and TestNG
## Objective of this Project
To demonstrate UI webtesting of an ecommerce site. Project feature includes
- Sorting functionality using selenium findelements, Java lists, arrrays and for loops.  
- Data parameterization using TestNg @Datatprovider annotation.  
- Cross browser testing through Testng.xml classes and parameters tag names.  
- Parallel execution through Textng.xml parallel tag name.  
- To create and implement POM framework.  
### Special feature
Implementation of Core framework/package/Jar. Core framework consists of following features  
- BaseTest class which uses TestNG annotation like @BeforeTest, @BeforeMethod where browsers and drivers are set. Based on preference where to run, 
  it can set driver desired capabilites and run on remotedriver using Selenium Grid or run locally. Report methods are integrated in this class to 
  create parent, 
  child reports. 
- BasePage class defines and set WebDriver, WebdriverWait, JavascriptExecutor. Also it contains wrapper methods for Webdriver methods for easy 
  logging in html report. Explicit waits are used using Expectedconditions. For Ex.<br>
  public void SendKeys(By locator, String text)<br>
	{ wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);} 
- BaseAssert encapulates wrapper methods for TestNg softassertions and report logging methods.  
- Classes for creating One master html extent report at the end of test suite in which each test class will have parent node and each test method 
  will have child report. This results in beautiful one report consisitng all the test classes and test methods in one single report with detailed 
  logs and screenshots where ever necessary.
- Utility class for string, date manipulations, random text and number generations.
#### this package makes it very easy to implement POM framework. It is reusable. Avoids reduntant code. Plus easy to maintain!
##### Tech stack using all latest version  
* Java openjdk-21  
* TestNG 7.9  
* Selenium 4.16  
* Aventstack extentreports 3.1.5(parent-child report can be created using this version only)
###### what's coming up next  
* how to install/download.
* Jenkins declarative pipline using Groovy.
* Integrations with Dockers.




