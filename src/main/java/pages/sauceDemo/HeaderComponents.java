package pages.sauceDemo;

import base.UIActions;
import org.openqa.selenium.By;

public class HeaderComponents {
    UIActions ui = new UIActions();
    private final By pageTitle = By.cssSelector("span[class = title]");
    private final By cartIcon = By.cssSelector("a[class = shopping_cart_link]");
    private final By sideMenu = By.cssSelector("button[id*='menu-btn']");
    private final By productsPage = By.xpath("//a[contains(text(), 'All Items')]");
    private final By logOut = By.xpath("//a[contains(text(), 'Logout')]");
    private final By reset = By.xpath("//a[contains(text(), 'Reset')]");
    private final By closeMenuBtn = By.xpath("//button[contains(@id, 'cross-btn')]");

    public CartPage openCartPage() {
        ui.clickOn(cartIcon);
        return new CartPage();
    }
    public HeaderComponents openMenu() {
        ui.clickOn(sideMenu);
        return this;
    }
    public ProductsPage openProductsPage() {
        openMenu().ui.clickOn(productsPage);
        return new ProductsPage();
    }
    public LoginPage logout(){
        openMenu().ui.clickOn(logOut);
        return new LoginPage();
    }
    public HeaderComponents resetCart(){
        openMenu().ui.clickOn(reset);
        return this;
    }
    public HeaderComponents closeMenu(){
        ui.clickOn(closeMenuBtn);
        return this;
    }
    public String getPageTitle() {
        return ui.getTextOfElement(pageTitle);
    }

}
