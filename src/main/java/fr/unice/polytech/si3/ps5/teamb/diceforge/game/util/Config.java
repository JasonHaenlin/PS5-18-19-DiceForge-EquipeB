package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
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

    private List<DiceSide> dice1Config;
    private List<DiceSide> dice2Config;
    private List<Card> exploitConfig;
    private EnumMap<Resources, Integer> invConfig;
    private Map<Integer, List<DiceSide>> forgeConfig;

    public Config(String file) throws Exception {
        this.file = file;
        try {
            this.jsonConfig = extractJson();
        } catch (Exception e) {
            throw new Exception("erreur lors de l'extraction de la configuration\n" + e.toString());
        }
    }

    /**
     * compute the config only one time for all the games
     */
    public void prepareConfig() {
        this.invConfig = extractInventory();
        this.exploitConfig = extractExploit();
        this.forgeConfig = extractForge();
        this.dice1Config = extractDice(0);
        this.dice2Config = extractDice(1);
    }

    EnumMap<Resources, Integer> extractInventory() {
        JSONObject ext = jsonConfig.getJSONObject("resources");
        EnumMap<Resources, Integer> inv = new EnumMap<>(Resources.class);
        inv.put(Resources.GOLD, ext.getInt("gold"));
        inv.put(Resources.SUN_STONE, ext.getInt("sunStone"));
        inv.put(Resources.MOON_STONE, ext.getInt("moonStone"));
        inv.put(Resources.VICTORY_POINT, ext.getInt("victoryPoint"));
        return inv;
    }

    List<DiceSide> extractDice(int diceNumber) {
        JSONArray extArray = jsonConfig.getJSONArray("dices").getJSONArray(diceNumber);
        List<DiceSide> side = new ArrayList<>();
        extArray.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            side.add(new DiceSide(obj.getInt("amount"), Resources.valueOf(obj.getString("resource"))));
        });
        return side;
    }

    List<Card> extractExploit() {
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

    Map<Integer, List<DiceSide>> extractForge() {
        JSONArray extArray = jsonConfig.getJSONArray("forge");
        Map<Integer, List<DiceSide>> forgeMap = new HashMap<>();
        extArray.forEach(itemCost -> {
            JSONObject obj = (JSONObject) itemCost;
            List<DiceSide> diceList = new ArrayList<>();
            JSONArray objArray = obj.getJSONArray("side");
            objArray.forEach(item -> {
                JSONObject sideObj = (JSONObject) item;
                diceList.add(new DiceSide(sideObj.getInt("amount"), Resources.valueOf(sideObj.getString("resource")),
                        obj.getInt("cost")));
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

    /**
     * @return the dice1Config
     */
    public List<DiceSide> getDice1Config() {
        if (dice1Config == null) {
            this.dice1Config = extractDice(0);
        }
        return dice1Config;
    }

    /**
     * @return the dice2Config
     */
    public List<DiceSide> getDice2Config() {
        if (dice2Config == null) {
            this.dice2Config = extractDice(1);
        }
        return dice2Config;
    }

    /**
     * @return the exploitConfig
     */
    public List<Card> getExploitConfig() {
        if (exploitConfig == null) {
            this.exploitConfig = extractExploit();
        }
        return exploitConfig;
    }

    /**
     * @return the forgeConfig
     */
    public Map<Integer, List<DiceSide>> getForgeConfig() {
        if (forgeConfig == null) {
            this.forgeConfig = extractForge();
        }
        return forgeConfig;
    }

    /**
     * @return the invConfig
     */
    public EnumMap<Resources, Integer> getInvConfig() {
        if (invConfig == null) {
            this.invConfig = extractInventory();
        }
        return invConfig;
    }

}