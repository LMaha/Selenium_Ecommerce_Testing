package com.Comp.coreUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Objective of this class is to read from resource files, get config values
 */
public class GetPropertyValues {
    InputStream inputStream;
    private String env;

    public GetPropertyValues(String _env)
    {
        this.env = _env;

    }
    public Properties GetEnvironmentPropertyValues()  {
        Properties prop = null ;
        String propertyFilename = env+("_config.properties");
        try {
            prop = new Properties();
            prop.load(new FileInputStream("Resources//" + propertyFilename));
            return prop;
        }
        catch (Exception e)
        {

        }
        finally {

        }
        return  prop;
    }
    public static Properties getConfigValues()
    {
        Properties prop = null;
        try
        {
            prop = new Properties();
            prop.load(new FileInputStream("Resources//config.properties"));
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }
        finally {
        }
        return prop;
    }



}
