package pages.sauceDemo;

import base.UIActions;
import org.openqa.selenium.By;

public class InvoicePage {
    HeaderComponents headerComponents;
    UIActions  ui = new UIActions();
    public InvoicePage() {
        headerComponents = new HeaderComponents();
    }
    final String itemName = "//div[contains(text(), '%s')]/parent::a";
    final String itemPrice = "//div[contains(text(), '%s')]/parent::a/following-sibling::div[contains(@class, 'price')]/div";
    private final By finishBtn = By.id("finish");
    public String getItemName(String itemName) {
        return ui.getTextOfElement(By.xpath(String.format(this.itemName, itemName)));
    }
    public String getItemPrice(String itemName) {
        return ui.getTextOfElement(By.xpath(String.format(this.itemPrice, itemName)));
    }
    public ThankYouPage finishPayment() {
        ui.clickOn(finishBtn);
        return new ThankYouPage();
    }
}
