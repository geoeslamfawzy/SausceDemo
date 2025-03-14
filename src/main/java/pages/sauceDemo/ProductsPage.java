package pages.sauceDemo;

import base.UIActions;
import org.openqa.selenium.By;

public class ProductsPage {
    UIActions ui = new UIActions();
    private HeaderComponents header; // Composition

    public ProductsPage() {
        this.header = new HeaderComponents(); // Composition
    }
    public ProductsPage addItemToCart(String itemName) {
        By item = By.xpath(String.format("//div[contains(text(), '%s')]/parent::a/parent::div/following-sibling::div/button", itemName));
        ui.clickOn(item);
        return new ProductsPage();
    }
    public String getItemPrice(String itemName) {
        By item = By.xpath(String.format("//div[contains(text(), '%s')]/parent::a/parent::div/following-sibling::div/div", itemName));
        return ui.getTextOfElement(item);
    }

    public HeaderComponents getHeader() {
        return header; // Allow access to common header actions
    }
}
