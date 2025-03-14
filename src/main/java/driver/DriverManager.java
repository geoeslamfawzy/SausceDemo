package driver;
import org.openqa.selenium.WebDriver;

public final class DriverManager {
    private DriverManager(){}
    /**
     * We use TreadLocal Class to overcome the parallel execution problem
     * the variable dr access the heap memory and make a child of the parent driver to be used
     * so I can make several childs from browser and run them in parallel
     * This can be regarded as garbage collector
     * dr is the child of web driver
     *
     * Thread Local Class relay on Hash maps to avoid collisions
     *      - Thread Local Objects act as keys that search for hash code to its value
     *      -
     */
    private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();
    public static WebDriver getDriver(){
        return dr.get();
    }
    public static void setDriver(WebDriver driver){
        dr.set(driver);
    }
    /**
     * This method for clean the driver after use
     * remove the current thread after use
     */
    public static void unload(){
        dr.remove();
    }
}
