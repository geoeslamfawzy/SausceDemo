package dataProvider.sauceDemo;

import helpers.ExcelHelper;
import helpers.JsonHelper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class DataReader {

    @DataProvider(name = "productInfo")
    public Object[][] getProductInfo() throws IOException {
        ExcelHelper xl = new ExcelHelper("ItemPrices");
        return xl.getDataFromExcel("prices");
    }

    @DataProvider(name = "purchaseFlow")
    public Object[][] getPurchaseFlowInfo() throws IOException {
        ExcelHelper xl = new ExcelHelper("ItemPrices");
        return xl.getDataFromExcel("prices");
    }

    @DataProvider(name = "multiSheetData")
    public Object[][] getMultiSheetData() throws IOException {
        ExcelHelper xl = new ExcelHelper("ItemPrices");
        return xl.getDataFromMultipleSheets(Arrays.asList("prices", "checkout", "ThankyouPage"));
    }

    @DataProvider(name = "productPrices")
    public Object[][] getProductPrices() throws IOException {
        JsonHelper jsonHelper = new JsonHelper("SauceDemo");
        return jsonHelper.getJsonArrayAsDataProvider("/testData/prices");
    }

    @DataProvider(name = "checkoutData")
    public Object[][] getCheckoutData() throws IOException {
        JsonHelper jsonHelper = new JsonHelper("SauceDemo");
        Map<String, String> checkoutMap = jsonHelper.getJsonDataToMap("/testData/checkout");
        // Wrap it in an Object array to return correctly
        return new Object[][]{{checkoutMap}};
    }

    @DataProvider(name = "workflowData")
    public Object[][] getCombinedData() throws IOException {
        Object[][] priceData = getProductPrices();  // List of products
        Object[][] checkoutData = getCheckoutData();  // Single checkout map

        // Extract checkout map (since it's a single object)
        Map<String, String> checkoutMap = (Map<String, String>) checkoutData[0][0];

        Object[][] combinedData = new Object[priceData.length][2];

        for (int i = 0; i < priceData.length; i++) {
            combinedData[i][0] = priceData[i][0];  // Product Data (Map<String, String>)
            combinedData[i][1] = checkoutMap;      // Checkout Data (Same Map for all)
        }

        return combinedData;
    }


}
