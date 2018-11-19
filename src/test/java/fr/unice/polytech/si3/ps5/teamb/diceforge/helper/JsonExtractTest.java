package fr.unice.polytech.si3.ps5.teamb.diceforge.helper;

import org.json.JSONObject;

public class JsonExtractTest {

    String file = "src/test/resources/config/directExploitBasic.json";
    String key = "data";

    public void extractDirectExploitTest() {
        JSONObject obj;
        obj = ReadFile.extractJSON(file, key);
        JSONObject obje = (JSONObject) obj.getJSONArray("exploit").get(1);
        System.out.println(obje.getJSONArray("action").get(1));
        JSONObject obj2 = (JSONObject) obje.getJSONArray("action").get(1);
        System.out.println(obj2.getJSONObject("extra"));
    }
}