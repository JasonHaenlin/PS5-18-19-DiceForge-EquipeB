package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.SimpleCard;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.DiceSide;

/**
 * Config
 *
 */
public class Config {

    private static Logger log = LogManager.getLogger(Config.class);

    private String file;
    private JSONObject jsonConfig;

    Config(String file) {
        this.file = file;
        try {
            this.jsonConfig = extractJson();
        } catch (Exception e) {
            log.error("erreur lors de l'extraction de la configuration\n" + e.toString());
            System.exit(1);
        }
    }

    public Map<Resources, Integer> extractInventory() {
        JSONObject ext = jsonConfig.getJSONObject("resources");
        Map<Resources, Integer> inv = new HashMap<>();
        inv.put(Resources.GOLD, ext.getInt("gold"));
        inv.put(Resources.SUN_STONE, ext.getInt("sunStone"));
        inv.put(Resources.MOON_STONE, ext.getInt("moonStone"));
        return inv;
    }

    public List<DiceSide> extractDice(int diceNumber) {
        JSONArray extArray = jsonConfig.getJSONArray("dices").getJSONArray(diceNumber);
        List<DiceSide> side = new ArrayList<>();
        extArray.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            side.add(new DiceSide(obj.getInt("amount"), Resources.valueOf(obj.getString("resource"))));
        });
        return side;
    }

    public List<Card> extractExploit() {
        JSONArray extArray = jsonConfig.getJSONArray("exploit");
        List<Card> cardList = new ArrayList<>();
        extArray.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            if (obj.getString("type").equals("SimpleCard"))
                cardList.add(
                        new SimpleCard(obj.getInt("moonStone"), obj.getInt("sunStone"), obj.getInt("victoryPoint")));
        });
        return cardList;
    }

    public Map<Integer, List<DiceSide>> extractForge() {
        JSONArray extArray = jsonConfig.getJSONArray("forge");
        Map<Integer, List<DiceSide>> forgeMap = new HashMap<>();
        extArray.forEach(itemCost -> {
            JSONObject obj = (JSONObject) itemCost;
            List<DiceSide> diceList = new ArrayList<>();
            JSONArray objArray = obj.getJSONArray("side");
            objArray.forEach(item -> {
                JSONObject sideObj = (JSONObject) item;
                diceList.add(new DiceSide(sideObj.getInt("amount"), Resources.valueOf(sideObj.getString("resource"))));
            });
            forgeMap.put(obj.getInt("cost"), diceList);
        });
        return forgeMap;
    }

    private JSONObject extractJson() throws IOException {
        JSONObject jobj = new JSONObject(readFile(this.file));
        return jobj.getJSONObject("data");
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
            log.error(e);
        }
        return result;
    }
}