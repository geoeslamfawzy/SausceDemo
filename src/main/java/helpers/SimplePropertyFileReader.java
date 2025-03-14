package helpers;

import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

public final class SimplePropertyFileReader {
    private SimplePropertyFileReader(){}

    /**
     * The Following method is to read from the property file
     * All you have to do when use this method is to pass the key you want
     *              in order to get the value in the property file (e.g pass url to get the value of url)
     *
     *         1- Stream on the property file
     *         2- Use Properties object to operate on the file
     *         3- Load The file
     *         4- use getProperty method in the Properties class to get the value of a key you passed
     *         5- Return this value
     *
     *
     * @param key
     * @return
     * @throws Exception
     */

    public static String getValue(String key) throws Exception {
        String value = "";
        //1- Stream on the Property File
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")
                +"\\src\\test\\java\\config\\config.properties");

        Properties properties = new Properties(); //This class to work on the file

        //2- Load this File
        properties.load(file);
        value = properties.getProperty(key); //this return the value of a key as a string
        if(Objects.isNull(value)){
            //If there's no value for a specific key, then throw an exception
            throw new Exception("Property Name " + key + " is not found, please check config.properties");
        }
        return value;
    }
}
