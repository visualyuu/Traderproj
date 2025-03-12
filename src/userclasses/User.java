package userclasses;

import exceptions.InvalidStringException;
import markets.CurrentMarketObserver;
import markets.CurrentMarketPublisher;
import markets.CurrentMarketSide;
import price.Price;
import regex.RegexStrings;
import trading.Tradable;
import trading.TradableDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class User implements CurrentMarketObserver {
     //package visible
    private String userId;
    private HashMap<String, TradableDTO> tradables;
    //id, tradabledto

    private HashMap<String, CurrentMarketSide[]> currentMarkets = new HashMap<>();

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
    public void updateCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide) {
        CurrentMarketSide[] retval = new CurrentMarketSide[2];
        retval[0] = buySide;
        retval[1] = sellSide;
        currentMarkets.put(symbol,retval);
    }

    public String getCurrentMarkets(){
        StringBuilder end = new StringBuilder();
        for (String k : currentMarkets.keySet()){
            CurrentMarketSide [] retval = currentMarkets.get(k);

            CurrentMarketSide buy =retval[0];
            CurrentMarketSide sell =retval[1];

            end.append(k + "    "+buy +"  -  "+ sell).append("\n");
        }
        return end.toString();
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        StringBuilder finalStr = new StringBuilder();
        finalStr.append("User Id: ").append(userId).append("\n");
        for(Map.Entry<String, TradableDTO> entry: tradables.entrySet()){
            finalStr.append(entry.getValue().toString()).append("\n");
        }
        return finalStr.toString();
    }


}
