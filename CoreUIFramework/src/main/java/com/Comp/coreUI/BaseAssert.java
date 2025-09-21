package com.Comp.coreUI;

import com.aventstack.extentreports.Status;
import org.testng.asserts.SoftAssert;

public class BaseAssert {
    public SoftAssert softAssert;
    private Boolean AssertionFailed;

    public BaseAssert(SoftAssert sa)
    {
        softAssert =sa;
    }
    public Boolean GetAssertionFailed()
    {
        return  AssertionFailed;
    }
    public void SetAssertionFailed(Boolean assertionFailed)
    {
        AssertionFailed = assertionFailed;
    }
    public void SoftAssertAreEqual(final Object exp, final Object act, final String message)
    {
        TryAndLog(()->softAssert.assertEquals(exp, act, message),message);

    }
    public void SoftAssertAreTrue(boolean condition, String message)
    {
      TryAndLog(()->softAssert.assertTrue(condition),  message);
    }

    public void Log(String message)
    {
        ExtentTestManager.GetTest().log(Status.INFO,  message);

    }

    private void TryAndLog(Action assertion, String message)
    {
        try{
            assertion.invoke();
            ExtentTestManager.GetTest().log(Status.PASS, message);

        }
        catch(Exception e)
        {
            ExtentTestManager.GetTest().log(Status.FAIL,message);
            AssertionFailed = true;
        }

    }


}
