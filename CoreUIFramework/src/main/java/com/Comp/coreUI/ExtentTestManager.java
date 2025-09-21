package com.Comp.coreUI;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    public static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    static ThreadLocal<ExtentTest> _parentTest = new ThreadLocal<>();
    static ThreadLocal<ExtentTest> _childTest = new ThreadLocal<>();
    public static ExtentTest CreateParentTest(String classname)
    {
        ExtentTest parent = extent.createTest(classname);
        _parentTest.set(parent);
        return null;
    }

    public static ExtentTest CreateChildTest(String testname, String description)
    {
        ExtentTest child = _parentTest.get().createNode(testname,description);
        extentTest.set(child);
        return null;
    }

    public static ExtentTest GetTest()
    {
        return extentTest.get();
    }
}
