package markets;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrentMarketPublisher {

    private HashMap<String, ArrayList<CurrentMarketObserver>> filters = new HashMap<>();

    public static CurrentMarketPublisher instance;

    public static CurrentMarketPublisher getInstance(){
        if (instance == null){
            instance = new CurrentMarketPublisher();
        }
        return instance;
    }

    public void subscribeCurrentMarket(String symbol, CurrentMarketObserver cmo){
        if (!filters.containsKey(symbol)){
            filters.put(symbol, new ArrayList<>());
        }
        filters.get(symbol).add(cmo);
    }

    public void unSubscribeCurrentMarket(String symbol, CurrentMarketObserver cmo){
        if (!filters.containsKey(symbol)){
            return;
        }
        filters.get(symbol).remove(cmo);
    }

    public void acceptCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide){
        if (!filters.containsKey(symbol)){
            return;
        }
        for(CurrentMarketObserver o: filters.get(symbol)){
            o.updateCurrentMarket(symbol,buySide,sellSide);
        }
    }
}
