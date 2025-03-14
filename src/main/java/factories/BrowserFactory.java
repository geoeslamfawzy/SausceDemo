package factories;

import driver.DriverManager;
import enums.BrowserType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Collections;

public class BrowserFactory {
    public static void initBrowser(BrowserType browserType) {
        switch (browserType) {
            case FireFox:
                DriverManager.setDriver(new FirefoxDriver());
                break;
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-popup-blocking");
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                options.setExperimentalOption("useAutomationExtension", false);
                options.addArguments("--lang=en");
                DriverManager.setDriver(new ChromeDriver(options));
                break;
            case EDGE:
                DriverManager.setDriver(new EdgeDriver());
                break;
        }
    }
}
