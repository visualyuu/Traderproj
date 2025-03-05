package markets;

import price.Price;
import userclasses.UserManager;

public class CurrentMarketTracker {
    //singleton
    public static CurrentMarketTracker instance;

    public static CurrentMarketTracker getInstance() {
        if (instance == null) {
            instance = new CurrentMarketTracker();
        }
        return instance;
    }

    //singleton
    public void updateMarket(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume) {
        int marketWidth;
        CurrentMarketSide buy;
        CurrentMarketSide sell;

        if (sellPrice == null&&buyPrice ==null) {
            buy = new CurrentMarketSide(new Price(0), buyVolume);
            sell = new CurrentMarketSide(new Price(0), sellVolume);
            marketWidth = 0;

        }else if (buyPrice == null) {
            buy = new CurrentMarketSide(new Price(0), buyVolume);
             sell = new CurrentMarketSide(sellPrice, sellVolume);
            marketWidth = 0;

        } else if (sellPrice == null) {
            buy = new CurrentMarketSide(buyPrice, buyVolume);
            sell = new CurrentMarketSide(new Price(0), sellVolume);
            marketWidth = 0;

        } else {
            buy = new CurrentMarketSide(buyPrice, buyVolume);
            sell = new CurrentMarketSide(sellPrice, sellVolume);
            marketWidth = sellPrice.compareTo(buyPrice);

        }
        System.out.println(
                "*********** Current Market ************\n" +
                        symbol + " " + buy + " - " + sell + " [" + new Price(marketWidth) + "]" + "\n" +
                        "**************************************\n"
        );
        CurrentMarketPublisher.getInstance().acceptCurrentMarket(symbol, buy, sell);
    }
}
