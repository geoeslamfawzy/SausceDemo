package testSuits.saucedemo;

import dataProvider.sauceDemo.DataReader;
import enums.EnumMapping;
import enums.JsonEnumsObjects;
import enums.Messages;
import enums.PageTitles;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.sauceDemo.CartPage;
import pages.sauceDemo.LoginPage;
import pages.sauceDemo.ProductsPage;
import pages.sauceDemo.ThankYouPage;
import testSuits.BaseTest;

import java.util.Map;

public class PurchaseTest extends BaseTest {
    LoginPage  loginPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();
    @BeforeClass
    public void login() throws Exception {
        productsPage = loginPage
                .login("standard_user", "secret_sauce");
    }

    @AfterClass
    public void logout() throws Exception {
        productsPage.getHeader().logout();
    }

    @Test(priority=1, dataProvider = "productInfo", dataProviderClass = DataReader.class)
    public void testProductPrices(String productName, String productPrice) {
        Assert.assertTrue(productsPage.getItemPrice(productName).contains(productPrice));
        Assert.assertEquals(productsPage.getHeader().getPageTitle(), EnumMapping.map(PageTitles.Product));
    }

    @Test(priority = 2, dataProvider = "productInfo", dataProviderClass = DataReader.class)
    public void testProductPriceInCart(String productName, String productPrice) throws InterruptedException {
        cartPage = productsPage.addItemToCart(productName).getHeader().openCartPage();
        Assert.assertTrue(cartPage.getItemPrice(productName).contains(productPrice));
        Assert.assertEquals(cartPage.getHeader().getPageTitle(), EnumMapping.map(PageTitles.Cart));
        cartPage.continueShopping();
        Assert.assertEquals(cartPage.getHeader().getPageTitle(), EnumMapping.map(PageTitles.Product));
    }

    @Test(priority = 3, dataProvider = "workflowData", dataProviderClass = DataReader.class)
    public void testProductPricesWithCheckout(Map<String, String> productData, Map<String, String> checkoutData) {
        cartPage = productsPage.addItemToCart(productData.get(EnumMapping.map(JsonEnumsObjects.ProductName))).getHeader().openCartPage();
        ThankYouPage thankYouPage = cartPage.checkout()
                .fillInfoData(checkoutData.get(EnumMapping.map(JsonEnumsObjects.FirstName)),
                        checkoutData.get(EnumMapping.map(JsonEnumsObjects.LastName)),
                        checkoutData.get(EnumMapping.map(JsonEnumsObjects.ZipCode)))
                .confirmPayment()
                .finishPayment();
        Assert.assertEquals(thankYouPage.getThankYouMsg(), EnumMapping.map(Messages.ThankYou));
        productsPage = thankYouPage.backToProductsPage();
        Assert.assertEquals(productsPage.getHeader().getPageTitle(), EnumMapping.map(PageTitles.Product));
    }

}
