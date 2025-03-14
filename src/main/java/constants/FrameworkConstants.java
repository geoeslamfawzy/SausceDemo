package constants;

public final class FrameworkConstants {
    private static final String CHROME_DRIVER_PATH = getProjectDirectory() + "drivers\\chromedriver.exe";
    private static final String CONFIG_FILE_PATH = getProjectDirectory() + "src\\test\\java\\config\\config.properties";
    private static final int EXPLICIT_WAIT = 30;
    private static final String RESOURCES_PATH = getProjectDirectory() +"src\\test\\java\\data\\";


    public static String getChromePath(){
        return CHROME_DRIVER_PATH;
    }
    public static String getProjectDirectory(){
        return System.getProperty("user.dir") + "\\";
    }
    public static String getConfigFilePath(){
        return CONFIG_FILE_PATH;
    }
    public static int getExplicitWait(){
        return EXPLICIT_WAIT;
    }
    public static String readDataFile(String fileName){
        System.out.println(RESOURCES_PATH + fileName);
        return RESOURCES_PATH + fileName;
    }



}
