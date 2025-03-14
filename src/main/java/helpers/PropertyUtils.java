package helpers;

import constants.FrameworkConstants;
import enums.ConfigProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public final class PropertyUtils {
    private PropertyUtils(){}

    private static Properties property = new Properties();
    private static final Map<String, String> CONFIGMAP = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    static {
        try {
            FileInputStream file = new FileInputStream(FrameworkConstants.getConfigFilePath());
            property.load(file);
            property.entrySet().forEach(entry -> CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String get(ConfigProperties key) throws Exception{
        if(Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))){
            throw new Exception(key + " Property is Not found in your file, Please Check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
    }
}
