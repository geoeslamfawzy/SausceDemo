package helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import constants.FrameworkConstants;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonHelper {
    private final String filePath;

    public JsonHelper(String fileName) {
        this.filePath = FrameworkConstants.readDataFile(fileName + ".json");
    }

    /**
     * Extracts a single value from the JSON file.
     */
    public String extractData(String tokenName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(filePath));
        JsonNode tokenNode = rootNode.at(tokenName);
        return tokenNode.asText();
    }

    /**
     * Reads a JSON object (key-value pairs) and converts it into a HashMap.
     */
    public HashMap<String, String> getJsonDataToMap(String tokenPath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonContent);
        JsonNode dataNode = rootNode.at(tokenPath);
        return mapper.convertValue(dataNode, new TypeReference<HashMap<String, String>>() {});
    }

    /**
     * Converts JSON object key-value pairs into a 2D Object array (useful for DataProviders).
     */
    public Object[][] getJsonData(String tokenPath) throws IOException {
        HashMap<String, String> dataMap = getJsonDataToMap(tokenPath);
        Object[][] objs = new Object[dataMap.size()][2];

        int index = 0;
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            objs[index][0] = entry.getKey();
            objs[index][1] = entry.getValue();
            index++;
        }
        return objs;
    }

    /**
     * Reads a JSON array and converts it into a List of Maps.
     */
    public List<Map<String, String>> getJsonArrayData(String tokenPath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonContent);
        JsonNode dataNode = rootNode.at(tokenPath);
        return mapper.convertValue(dataNode, new TypeReference<List<Map<String, String>>>() {});
    }

    /**
     * Converts JSON array data into an Object array (for DataProviders).
     */
    public Object[][] getJsonArrayAsDataProvider(String tokenPath) throws IOException {
        List<Map<String, String>> dataList = getJsonArrayData(tokenPath);
        Object[][] objs = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            objs[i][0] = dataList.get(i);
        }
        return objs;
    }
}