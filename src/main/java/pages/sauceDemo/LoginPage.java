package pages.sauceDemo;

import base.UIActions;
import org.openqa.selenium.By;

public class LoginPage {
    UIActions  ui = new UIActions();
    private final By usernameField = By.cssSelector("input[placeholder=\"Username\"]");
    private final By passwordField = By.cssSelector("input[placeholder=\"Password\"]");
    private final By loginButton = By.cssSelector("input[id=\"login-button\"]");


    public ProductsPage login(String username, String password) {
        ui.writeIn(usernameField, username);
        ui.writeIn(passwordField, password);
        ui.clickOn(loginButton);
        return new ProductsPage();
    }


}
