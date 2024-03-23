package org.srmzhk.crypto_bot.crypto_bot.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.srmzhk.crypto_bot.crypto_bot.model.CurrencyModel;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;

public class CurrencyService {

    public static String getCurrencyRate(String message, CurrencyModel model) throws IOException, ParseException, net.minidev.json.parser.ParseException {
        try {
            URL url = new URL("https://api.mexc.com/api/v3/ticker/price");
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(url.openStream()));

            StringBuilder answer = new StringBuilder();
            int i = 0;
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                // Assuming each object has a "symbol" and "price" key
                String symbol = (String) jsonObject.get("symbol");
                String price = (String) jsonObject.get("price");
                answer.append(symbol).append(": ").append(price).append("\n");
                i++;
                if (i == 5) {
                    break;
                }
            }
            return answer.toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}