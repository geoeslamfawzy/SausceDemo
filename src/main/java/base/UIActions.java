package base;

import constants.FrameworkConstants;
import driver.DriverManager;
import enums.ConfigProperties;
import enums.WaitStrategy;
import factories.ExplicitWaitFactory;
import helpers.PropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class UIActions {
    public WebElement findElement(By locator, WaitStrategy waitStrategy) {
        return ExplicitWaitFactory.performExplicitWait(waitStrategy, locator);
    }
    public WebElement findElement(By locator) {
        return ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, locator);
    }

    public void clickOn(By locator) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(WaitStrategy.ClICKABLE, locator);
        element.click();
    }
    public void clickOn(By locator, WaitStrategy waitStrategy) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, locator);
        element.click();
    }

    public void writeIn(String text, By locator) {
        WebElement element = findElement(locator);
        element.sendKeys(text);
    }
    public void writeIn(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public List<WebElement> findElements(By locator) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }



    public void chooseFromMenu(By menuLocator, String element) {
        clickOn(menuLocator, WaitStrategy.VISIBLE);
        List<WebElement> options = findElements(menuLocator);
        for (WebElement option : options) {
            if (option.getText().contains(element)) {
                option.click();
                break;
            }
        }
    }

    public void select(By menuLocator, String option){
        Select select = new Select(findElement(menuLocator));
        select.selectByVisibleText(option);
    }

    public Boolean isElementExisted(By locator){
        return findElement(locator, WaitStrategy.VISIBLE).isDisplayed();
    }

    public void uploadImage(String imagePath, By locator) {
        WebElement file = findElement(locator);
        file.sendKeys(imagePath);
    }

    protected String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }
    public String getTextOfElement(By locator){
        return findElement(locator).getText();
    }
    public void scrollToElement(By locator){
        Actions actions = new Actions(DriverManager.getDriver());
        WebElement element = findElement(locator);
        actions.moveToElement(element).build().perform();
    }

    public void scrollToBottom(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,350)", "");
    }
    public void scrollUp(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,-350)", "");
    }
    public  void typeTextWithDelay(By locator, String text) throws InterruptedException {
        WebElement element = findElement(locator);
        element.clear();
        for (char c : text.toCharArray()) {
            element.sendKeys(Character.toString(c));
        }
    }

    public void navigateToWishlist() throws Exception {
        DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.WishList));
    }

    public void navigateToCarts() throws Exception {
        DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.Carts));
    }
}
