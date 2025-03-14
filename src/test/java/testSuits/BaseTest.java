package testSuits;


import driver.DriverActions;
import driver.DriverManager;
import enums.BrowserType;
import enums.ConfigProperties;
import helpers.PropertyUtils;
import helpers.ScreenshotHelper;
import org.testng.ITestResult;
import org.testng.annotations.*;


import static factories.BrowserFactory.initBrowser;

public class BaseTest {


    @Parameters("browser")
    @BeforeClass
    public void setup(@Optional("chrome") String browser) throws Exception {
        switch (browser.toLowerCase()) {
            case "firefox":
                initBrowser(BrowserType.FireFox);
                break;
            case "edge":
                initBrowser(BrowserType.EDGE);
                break;
            case "chrome":
            default:
                initBrowser(BrowserType.CHROME);
                break;
        }
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.SauceDemoBaseurl));
    }

    @AfterClass
    protected void tearDown(){
 //       registrationPage.userLogout();
        DriverActions.quitDriver();
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
            ScreenshotHelper.captureScreenshot(DriverManager.getDriver(), result.getName());
        }
    }
}

