package com.Comp.coreUI;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
/**Objective of this class to initialize drivers with appropriate browsers based on the parameters
 * also set RemoteDriver for running on remote along with desired capabilities. Desired capabilities are required for
 * remote drivers.
 *
 */

public class   BaseTest {
    public WebDriver driver;
    public String browserName;
    public String environment;
    public String downloadPath;
    public Properties props;
    public Properties config;
    private GetPropertyValues Getproperties;

    public  boolean runLocal;

    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
     public ExtentTest test;



    @BeforeTest
    @Parameters({"browser", "env"})
    public void Setup(@Optional("")String browser, @Optional("")String env)
    {
        try
        {
            config = GetPropertyValues.getConfigValues();
            environment = config.getProperty("env");
        }
        catch (Exception e)
        {

        }
        browserName = browser;
        environment = env;
        if (browser.equals(""))
        {
            browserName = config.getProperty("browser");
        }

        if (env.equals(""))
        {
            environment = config.getProperty("env");
        }

        Getproperties = new GetPropertyValues(environment);
        try
        {
            props = Getproperties.GetEnvironmentPropertyValues();
            runLocal = props.getProperty("RUN_ON_LOCAL").equalsIgnoreCase("TRUE");

        }
        catch (Exception ignored)
        {}
        ExtentTestManager.CreateParentTest(this.getClass().getSimpleName()+"-"+browserName+"-"+env);

    }

    /**Based on selected browser, set desired capabilites based on where to run i.e. either locally or remote
     *
     * @param method
     */
    @BeforeMethod
    public void Open(Method method)
    {
        try
        {

            if(runLocal)
            {
                if(browserName.equalsIgnoreCase("firefox"))
                {
                    driver = new FirefoxDriver();
                } else if (browserName.equalsIgnoreCase("edge"))
                {
                    driver = new EdgeDriver();
                }
                else if (browserName.equalsIgnoreCase("chrome"))
                {
                    driver = new ChromeDriver() ;
                }
                else
                {
                    driver = new ChromeDriver() ;
                }
            }
            else
            {
                /**If its remote then run on the Grid
                 *
                 *
                 */
                URL url = new URL("http://192.168.0.214:4444/wd/hub");
                if(browserName.equalsIgnoreCase("firefox"))
                {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addPreference("browser.download.folderList",2);
                    driver = new RemoteWebDriver(url,options);

                }
                if(browserName.equalsIgnoreCase("edge"))
                {
                    HashMap<String, Object> edgePrefs = new HashMap<String, Object>();
                    edgePrefs.put("download.default_directory", props.getProperty("DownloadPath_Remote"));
                    edgePrefs.put("download.prompt_for_download", false);
                    HashMap<String, Object> edgeOptions = new HashMap<String, Object>();
                    edgeOptions.put("prefs", edgePrefs);
                    EdgeOptions options = new EdgeOptions();
                    options.setCapability("ms:edgeOptions",edgeOptions);
                    driver = new RemoteWebDriver(url,options);
                }
                else
                {
                    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                    chromePrefs.put("download.directory_upgrade","True");
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs",chromePrefs);
                    driver = new RemoteWebDriver(url, options);

                }
            }
            driver.get(props.getProperty("URL"));
            driver.manage().window().maximize();
            ExtentTestManager.CreateChildTest(method.getName(),method.getAnnotation(Test.class).description());

        }
        catch (Exception e)
        {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

    }
    @AfterMethod
    public void Close(ITestResult result)
    {
        /**
         * write to report
         */
        int r = result.getStatus();
        switch (result.getStatus())
        {
            case ITestResult.FAILURE:
                String exceptions = Arrays.toString(result.getThrowable().getStackTrace());
                ExtentTestManager.GetTest().log(Status.FAIL ,exceptions);
                break;
            case ITestResult.SUCCESS:
                ExtentTestManager.GetTest().log(Status.PASS, "Test ended with status: PASS");
        }

    }
    @AfterTest
    public void Teardown()
    {
      // driver.close();
       driver.quit();

    }
    @AfterSuite
    public void reportTeardown()
    {
        /**
         * flush report and close all browsers
         *
         *
         */
        ExtentTestManager.extent.flush();
        try
        {
            if(driver != null)
                driver.quit();
        }
        catch(Exception e)
        {

        }

    }


}
