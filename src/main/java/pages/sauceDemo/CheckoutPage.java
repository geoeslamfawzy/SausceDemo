package pages.sauceDemo;

import base.UIActions;
import org.openqa.selenium.By;

public class CheckoutPage {
    private HeaderComponents header; // Composition
    UIActions ui = new UIActions();
    public CheckoutPage() {
        header = new HeaderComponents();
    }
    private final By firstName = By.cssSelector("input[placeholder = 'First Name']");
    private final By lastName = By.cssSelector("input[placeholder = 'Last Name']");
    private final By zipCode = By.cssSelector("input[name= 'postalCode']");
    private final By continueBtn = By.cssSelector("input[type='submit']");
    private final By cancelBtn = By.cssSelector("button[name='cancel']");

    public CheckoutPage fillInfoData(String firstName, String lastName, String zipCode) {
        ui.writeIn(this.firstName, firstName);
        ui.writeIn(this.lastName, lastName);
        ui.writeIn(this.zipCode, zipCode);
        return this;
    }
    public InvoicePage confirmPayment() {
        ui.clickOn(continueBtn);
        return new InvoicePage();
    }
    public CartPage cancelCheckout() {
        ui.clickOn(cancelBtn);
        return new CartPage();
    }
    public HeaderComponents getHeader() {
        return header; // Allow access to common header actions
    }
}
