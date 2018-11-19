package fr.unice.polytech.si3.ps5.teamb.diceforge.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/**
 * ReadFile
 */
public class ReadFile {
    private static Logger logger = LogManager.getLogger(ReadFile.class);

    private ReadFile() {

    }

    /**
     * read a JSON file and retrieve a Array Json object using the corresponding key
     * 
     * @param path to file
     * @param key  key where data array are store
     * @return JSONArray with all the data extracted
     */
    public static JSONObject extractJSON(String path, String key) {
        try {
            JSONObject jobj = new JSONObject(readFile(path));
            return jobj.getJSONObject(key);
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    private static String readFile(String filename) throws IOException {
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
}