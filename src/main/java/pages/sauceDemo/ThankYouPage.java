package pages.sauceDemo;

import base.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ThankYouPage {
    UIActions ui = new UIActions();
    private final By backToProductBtn = By.id("back-to-products");
    private final By thankYouMsg = By.cssSelector(".complete-header");
    private final By logo = By.cssSelector(".pony_express");

    public ProductsPage backToProductsPage() {
        ui.clickOn(backToProductBtn);
        return new ProductsPage();
    }
    public String getThankYouMsg() {return ui.getTextOfElement(thankYouMsg);}

}
