package userclasses;

import exceptions.InvalidStringException;
import price.Price;
import regex.RegexStrings;
import trading.Tradable;
import trading.TradableDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class User {
     //package visible
    private String userId;
    private HashMap<String, TradableDTO> tradables;
    //id, tradabledto

    public User(String userId) throws InvalidStringException {
        setUserId(userId);
        this.tradables = new HashMap<>();
    }

    private void setUserId(String userId) throws InvalidStringException {
        this.userId = RegexStrings.userTest(userId);
    }

    public void updateTradable(TradableDTO o){
        if (o == null) {
            return;
        }
        tradables.put(o.tradableId(), o);
    }

    @Override
    public String toString() {
        //fix this
        StringBuilder finalStr = new StringBuilder();
        finalStr.append("User Id: ").append(userId).append("\n");
        for(Map.Entry<String, TradableDTO> entry: tradables.entrySet()){
            finalStr.append(entry.getValue().toString());
        }
        return finalStr.toString();
    }
}
