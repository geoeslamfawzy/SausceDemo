package enums;

import java.util.HashMap;
import java.util.Map;

public class EnumMapping {
    public static String map(Enum key) {
        Map<Enum, String> dict = new HashMap<>();

        dict.put(PageTitles.Product, "Products");
        dict.put(PageTitles.Cart, "Your Cart");
        dict.put(PageTitles.Checkout, "Checkout: Your Information");
        dict.put(PageTitles.Invoice, "Checkout: Overview");
        dict.put(PageTitles.ThankYou, "Checkout: Complete!");

        dict.put(Messages.ThankYou, "Thank you for your order!");

        dict.put(JsonEnumsObjects.FirstName, "firstName");
        dict.put(JsonEnumsObjects.LastName, "lastName");
        dict.put(JsonEnumsObjects.ZipCode, "zipCode");
        dict.put(JsonEnumsObjects.ProductName, "productName");
        dict.put(JsonEnumsObjects.ProductPrice, "productPrice");


        String value = dict.get(key);
        return value;
    }

}
