package testSuits;

import dataProvider.sauceDemo.DataReader;
import enums.ConfigProperties;
import helpers.JsonHelper;
import helpers.PropertyUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Try extends BaseTest {
    public static void main(String[] args) throws Exception {
//        JsonHelper jsonHelper = new JsonHelper("noopCommerceData"); // Replace "data" with your JSON file name
//       // HashMap<String, String> registrationData = jsonHelper.getJsonDataToMap("registrationData");
//        Object[][] registrationData = jsonHelper.getJsonData("registrationData");
//
//        for (Object[] obj : registrationData) {
//            System.out.println(obj);
//            System.out.println(obj[0] + ": " + obj[1]);
//        }
        System.out.println(PropertyUtils.get(ConfigProperties.nopCommerceBaseUrl));
    }

    @Test(dataProvider = "thankYouMessage", dataProviderClass = DataReader.class)
    public void tryyy(String thankYouMessage){
        System.out.println(thankYouMessage);
    }
}