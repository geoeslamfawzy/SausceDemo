package driver;

import enums.BrowserType;
import enums.ConfigProperties;
import factories.BrowserFactory;
import helpers.PropertyUtils;

import java.sql.SQLOutput;
import java.util.Objects;

public final class DriverActions extends BrowserFactory {
    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload(); //clean after quit the driver
        }
    }
    public static void initDriver() throws Exception {
        if (DriverManager.getDriver() == null) {
            initBrowser(BrowserType.CHROME);
            DriverManager.getDriver().manage().window().maximize();
            DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.SauceDemoBaseurl));
        }
    }
}


