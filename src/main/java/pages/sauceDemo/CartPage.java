package pages.sauceDemo;

import base.UIActions;
import org.openqa.selenium.By;

public class CartPage {
    private HeaderComponents header; // Composition
    UIActions ui = new UIActions();
    public CartPage() {
        header = new HeaderComponents();
    }
    final String itemName = "//div[contains(text(), '%s')]/parent::a";
    final String itemPrice = "//div[contains(text(), '%s')]/parent::a/following-sibling::div[contains(@class, 'price')]/div";
    private final By checkoutBtn = By.id("checkout");
    private final By continueShoppingBtn = By.id("continue-shopping");

    public String getItemName(String itemName) {
        return ui.getTextOfElement(By.xpath(String.format(this.itemName, itemName)));
    }
    public String getItemPrice(String itemName) {
        return ui.getTextOfElement(By.xpath(String.format(this.itemPrice, itemName)));
    }
    public CheckoutPage checkout() {
        ui.clickOn(checkoutBtn);
        return new CheckoutPage();
    }
    public ProductsPage continueShopping() {
        ui.clickOn(continueShoppingBtn);
        return new ProductsPage();
    }
    public HeaderComponents getHeader() {
        return header; // Allow access to common header actions
    }
}
