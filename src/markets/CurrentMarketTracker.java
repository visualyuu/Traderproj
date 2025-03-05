package markets;

import price.Price;
import userclasses.UserManager;

public class CurrentMarketTracker {
    //singleton
    public static CurrentMarketTracker instance;

    public static CurrentMarketTracker getInstance(){
        if (instance == null){
            instance = new CurrentMarketTracker();
        }
        return instance;
    }

    //singleton
    public void updateMarket(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume){
        int marketWidth;
        if(buyPrice.equals(0) || sellPrice.equals(0)){
            marketWidth = 0;
        }else {
            marketWidth = sellPrice.compareTo(buyPrice);
        }
        CurrentMarketSide buy =  new CurrentMarketSide(buyPrice,buyVolume);
        CurrentMarketSide sell = new CurrentMarketSide(sellPrice,sellVolume);
        System.out.println(
                "*********** Current Market ************\n" +
                       symbol + buy+" - "+ sell + "["+marketWidth+"]"+"\n" +
                        "**************************************\n"
        );
        CurrentMarketPublisher.getInstance().acceptCurrentMarket(symbol,buy,sell);
    }
}
