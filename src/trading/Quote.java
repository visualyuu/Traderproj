package trading;

import price.Price;

public class Quote {
    private String user;
    private String product;
    private QuoteSide buySide;
    private QuoteSide sellSide;

    public Quote(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume, String username) {
        this.user = user;
        this.product = product;
        this.buySide = buySide;
        this.sellSide = sellSide;
    }

    public QuoteSide getQuoteSide(BookSide in){
        
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setBuySide(QuoteSide buySide) {
        this.buySide = buySide;
    }

    public void setSellSide(QuoteSide sellSide) {
        this.sellSide = sellSide;
    }
}
