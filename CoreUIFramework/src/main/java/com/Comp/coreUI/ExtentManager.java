package com.Comp.coreUI;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import java.io.File;
import java.util.zip.ZipFile;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance()
    {
        ReportFolder();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("Reports//extent.html");

        htmlReporter = new ExtentHtmlReporter("Reports//extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }
    public static ExtentReports getInstance()
    {
        if (extent == null) {
            createInstance();
        }
            return extent;


    }
    static void ReportFolder()
    {
        String filePath = "Reports//";
        //Creating the File object
        File file = new File(filePath);
        if (file.exists()){
            deleteFolder(file);
            file.mkdir();
        }
        else {
            file.mkdir();
        }
    }
    static void deleteFolder(File file){
        for (File subFile : file.listFiles()) {
            if(subFile.isDirectory()) {
                deleteFolder(subFile);
            } else {
                subFile.delete();
            }
        }
        file.delete();
    }
}
