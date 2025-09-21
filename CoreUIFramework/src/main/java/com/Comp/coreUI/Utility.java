package com.Comp.coreUI;

public class Utility {

    public static String GetAlphaNumericString(int size)
    {
        String alphanumericstring = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                +"0123456789"
                +"abcdefghijklmnopqurstuvwxyz";
        StringBuilder sb = new StringBuilder(size);
        for(int i=0; i< size; i++)
        {
            int index = (int) (alphanumericstring.length()*Math.random());
            sb.append(alphanumericstring.charAt(index));

        }
        return  sb.toString();
    }
}
